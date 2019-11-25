package com.lz.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lz.crud.bean.Employee;
import com.lz.crud.bean.EmployeeExample;
import com.lz.crud.bean.EmployeeExample.Criteria;
import com.lz.crud.dao.EmployeeMapper;
@Service
public class EmployeeSerivce {
	@Autowired
	EmployeeMapper employeeMapper;
	/**
	 * 查询所有员工
	 * @return
	 */
	public List<Employee> getAll() {
		return employeeMapper.selectByExampleWithDept(null);
	}
	
	/**
	 * 员工保存
	 * @param employee
	 */
	public void saveEmp(Employee employee) {
		// TODO Auto-generated method stub
		employeeMapper.insertSelective(employee);
	}

	/**
	 * 检验用户名是否可用
	 * @param empName
	 * @return	true可用 fasle不可用
	 */
	public boolean checkUser(String empName) {
		// TODO Auto-generated method stub
		EmployeeExample employeeExample = new EmployeeExample();
		Criteria createCriteria = employeeExample.createCriteria();
		createCriteria.andEmpNameEqualTo(empName);
		long countByExample = employeeMapper.countByExample(employeeExample);
//		System.out.println(countByExample);
		return countByExample == 0;
	}

	
	/**
	 * 按照员工ID查询员工
	 * @param id
	 * @return
	 */
	public Employee getEmp(Integer id) {
		// TODO Auto-generated method stub
		Employee selectByPrimaryKey = employeeMapper.selectByPrimaryKey(id);
		return selectByPrimaryKey;
	}

	
	/**
	 * 员工的更新方法
	 * @param employee
	 * @return
	 */
	public void updateEmp(Employee employee) {
		// TODO Auto-generated method stub
		employeeMapper.updateByPrimaryKeySelective(employee);
	}

	/**
	 * 员工的删除方法
	 * @param id
	 */
	public void deleteEmp(Integer id) {
		// TODO Auto-generated method stub
		employeeMapper.deleteByPrimaryKey(id);
	}

	public void deleteBatch(List<Integer> ids) {
		// TODO Auto-generated method stub
		EmployeeExample employeeExample = new EmployeeExample();
		Criteria createCriteria = employeeExample.createCriteria();
		createCriteria.andEmpIdIn(ids);
		employeeMapper.deleteByExample(employeeExample);
		
	}



}
