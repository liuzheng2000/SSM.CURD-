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
 * 处理员工增删改查
 * @author 15533
 *
 */
@Controller
public class EmployeeController {
	
	
	@Autowired
	EmployeeSerivce employeeService;
	
	/**
	 * 单个批量二合一
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/emp/{ids}",method = RequestMethod.DELETE)
	public Msg deleteEmp(@PathVariable("ids") String ids) {
		//批量删除
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
	 * 员工更新方法
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
	 * 根据id查询
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
	 * 检查用户名是否可用
	 * @param empName
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkuser")
	public Msg checkuser(@RequestParam("empName")String empName) {
		String regx = "^[\u2E80-\u9FFFa-zA-Z0-9_-]{2,4}$|^[a-zA-Z0-9_-]{2,16}$";
		if (!empName.matches(regx)) {
			return Msg.fail().add("va_msg", "用户名必须是2-16位数字和字母的组合或者2-4位中文");
		}
		//数据库用户名重新校验
		boolean b = employeeService.checkUser(empName);
		if(b) {
			return Msg.success();
		}else {
			return Msg.fail().add("va_msg", "用户名重复");
		}
		
	}
	
	
	/**
	 * 员工保存
	 * 1.支持JSR303校验
	 * 2.导入包
	 * 
	 * 
	 * @return
	 */
	@RequestMapping(value="/emp",method = RequestMethod.POST)
	@ResponseBody
	public Msg saveEmp(@Valid Employee employee ,BindingResult result) {
		if(result.hasErrors()) {
			//校验失败
			Map<String, Object> map = new HashMap<String, Object>();
			List<FieldError> fieldErrors = result.getFieldErrors();
			for(FieldError fieldError: fieldErrors){
				System.out.println("错误的字段名"+fieldError.getField());
				System.out.println("错误的信息"+fieldError.getDefaultMessage());
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
	 * 导入jackson包
	 * @param pn
	 * @return
	 */
	@RequestMapping("/emps")
	@ResponseBody
	public Msg getEmpsWithJson(@RequestParam(value="pn",defaultValue = "1") Integer pn ) {
		//引入分页插件PageHelper
		//在查询之前传入页码以及分页
		PageHelper.startPage(pn, 5);
		//startPage后面查询的这个查询就是分页查询
		List<Employee> emps = employeeService.getAll();
		//使用pageInfo包装查询后的结果
		//封装了详细的分页信息，查询的数据，传入连续显示的页数
		PageInfo page = new PageInfo(emps,5);
		return Msg.success().add("pageInfo",page);
	}
	
	
	
	
	
	/**
	 * 查询分页数据（分页查询）
	 * @return
	 */
//	@RequestMapping("/emps")
	public String getEmps(@RequestParam(value="pn",defaultValue = "1") Integer pn ,Model model) {
		//引入分页插件PageHelper
		//在查询之前传入页码以及分页
		PageHelper.startPage(pn, 5);
		//startPage后面查询的这个查询就是分页查询
		List<Employee> emps = employeeService.getAll();
		//使用pageInfo包装查询后的结果
		//封装了详细的分页信息，查询的数据，传入连续显示的页数
		PageInfo page = new PageInfo(emps,5);
		model.addAttribute("pageInfo",page);
		return "list";
	}
	
	
}
