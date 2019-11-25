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
	 * ��ѯ����Ա��
	 * @return
	 */
	public List<Employee> getAll() {
		return employeeMapper.selectByExampleWithDept(null);
	}
	
	/**
	 * Ա������
	 * @param employee
	 */
	public void saveEmp(Employee employee) {
		// TODO Auto-generated method stub
		employeeMapper.insertSelective(employee);
	}

	/**
	 * �����û����Ƿ����
	 * @param empName
	 * @return	true���� fasle������
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
	 * ����Ա��ID��ѯԱ��
	 * @param id
	 * @return
	 */
	public Employee getEmp(Integer id) {
		// TODO Auto-generated method stub
		Employee selectByPrimaryKey = employeeMapper.selectByPrimaryKey(id);
		return selectByPrimaryKey;
	}

	
	/**
	 * Ա���ĸ��·���
	 * @param employee
	 * @return
	 */
	public void updateEmp(Employee employee) {
		// TODO Auto-generated method stub
		employeeMapper.updateByPrimaryKeySelective(employee);
	}

	/**
	 * Ա����ɾ������
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
