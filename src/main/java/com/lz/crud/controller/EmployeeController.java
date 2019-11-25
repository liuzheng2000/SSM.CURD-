package com.lz.crud.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lz.crud.bean.Employee;
import com.lz.crud.bean.Msg;
import com.lz.crud.service.EmployeeSerivce;


/**
 * ����Ա����ɾ�Ĳ�
 * @author 15533
 *
 */
@Controller
public class EmployeeController {
	
	
	@Autowired
	EmployeeSerivce employeeService;
	
	/**
	 * ������������һ
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/emp/{ids}",method = RequestMethod.DELETE)
	public Msg deleteEmp(@PathVariable("ids") String ids) {
		//����ɾ��
		if(ids.contains("-")) {
			List<Integer> del_ids = new ArrayList<Integer>();
			String[] str_ids  = ids.split("-");
			for(String string : str_ids) {
				del_ids.add(Integer.parseInt(string));
			}
			employeeService.deleteBatch(del_ids);
		}else {
			int id = Integer.parseInt(ids);
			employeeService.deleteEmp(id);
		}
		
		return Msg.success();
		
	}
	
	
	
	
	
	/**
	 * Ա�����·���
	 * @param employee
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/emp/{empId}",method = RequestMethod.PUT)
	public Msg saveEmo(Employee employee) {
		System.out.println(employee.toString());
		employeeService.updateEmp(employee);
		return Msg.success();
		
	}
	
	
	
	
	
	/**
	 * ����id��ѯ
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/emp/{id}",method = RequestMethod.GET)
	@ResponseBody
	public Msg getEmp(@PathVariable("id") Integer id){
		
		Employee employee = employeeService.getEmp(id);
		return Msg.success().add("emp", employee);
	}
	
	
	
	/**
	 * ����û����Ƿ����
	 * @param empName
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkuser")
	public Msg checkuser(@RequestParam("empName")String empName) {
		String regx = "^[\u2E80-\u9FFFa-zA-Z0-9_-]{2,4}$|^[a-zA-Z0-9_-]{2,16}$";
		if (!empName.matches(regx)) {
			return Msg.fail().add("va_msg", "�û���������2-16λ���ֺ���ĸ����ϻ���2-4λ����");
		}
		//���ݿ��û�������У��
		boolean b = employeeService.checkUser(empName);
		if(b) {
			return Msg.success();
		}else {
			return Msg.fail().add("va_msg", "�û����ظ�");
		}
		
	}
	
	
	/**
	 * Ա������
	 * 1.֧��JSR303У��
	 * 2.�����
	 * 
	 * 
	 * @return
	 */
	@RequestMapping(value="/emp",method = RequestMethod.POST)
	@ResponseBody
	public Msg saveEmp(@Valid Employee employee ,BindingResult result) {
		if(result.hasErrors()) {
			//У��ʧ��
			Map<String, Object> map = new HashMap<String, Object>();
			List<FieldError> fieldErrors = result.getFieldErrors();
			for(FieldError fieldError: fieldErrors){
				System.out.println("������ֶ���"+fieldError.getField());
				System.out.println("�������Ϣ"+fieldError.getDefaultMessage());
				map.put(fieldError.getField(),fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
		}else {
			System.out.println(employee);
			employeeService.saveEmp(employee);
			return Msg.success();
		}	
	}
	
	
	/**
	 * ����jackson��
	 * @param pn
	 * @return
	 */
	@RequestMapping("/emps")
	@ResponseBody
	public Msg getEmpsWithJson(@RequestParam(value="pn",defaultValue = "1") Integer pn ) {
		//�����ҳ���PageHelper
		//�ڲ�ѯ֮ǰ����ҳ���Լ���ҳ
		PageHelper.startPage(pn, 5);
		//startPage�����ѯ�������ѯ���Ƿ�ҳ��ѯ
		List<Employee> emps = employeeService.getAll();
		//ʹ��pageInfo��װ��ѯ��Ľ��
		//��װ����ϸ�ķ�ҳ��Ϣ����ѯ�����ݣ�����������ʾ��ҳ��
		PageInfo page = new PageInfo(emps,5);
		return Msg.success().add("pageInfo",page);
	}
	
	
	
	
	
	/**
	 * ��ѯ��ҳ���ݣ���ҳ��ѯ��
	 * @return
	 */
//	@RequestMapping("/emps")
	public String getEmps(@RequestParam(value="pn",defaultValue = "1") Integer pn ,Model model) {
		//�����ҳ���PageHelper
		//�ڲ�ѯ֮ǰ����ҳ���Լ���ҳ
		PageHelper.startPage(pn, 5);
		//startPage�����ѯ�������ѯ���Ƿ�ҳ��ѯ
		List<Employee> emps = employeeService.getAll();
		//ʹ��pageInfo��װ��ѯ��Ľ��
		//��װ����ϸ�ķ�ҳ��Ϣ����ѯ�����ݣ�����������ʾ��ҳ��
		PageInfo page = new PageInfo(emps,5);
		model.addAttribute("pageInfo",page);
		return "list";
	}
	
	
}
