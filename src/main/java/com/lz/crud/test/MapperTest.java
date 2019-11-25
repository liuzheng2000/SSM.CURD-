package com.lz.crud.test;

import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lz.crud.bean.Department;
import com.lz.crud.bean.DepartmentExample;
import com.lz.crud.bean.Employee;
import com.lz.crud.bean.EmployeeExample;
import com.lz.crud.bean.EmployeeExample.Criteria;
import com.lz.crud.dao.DepartmentMapper;
import com.lz.crud.dao.EmployeeMapper;

/**
 * ����dao��Ĺ���
 * @author 15533
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {
	/**
	 * ����DepartmentMapper
	 */
	
	@Autowired
	DepartmentMapper departmentMapper;
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	SqlSession sqlSession;
	@Test
	public void testCRUD() {
		//1����SpringIOC����
//		ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
//		DepartmentMapper bean = ioc.getBean(DepartmentMapper.class);
		System.out.println(departmentMapper);
		//1���뼸������
//		departmentMapper.insertSelective(new Department(null,"������"));
//		departmentMapper.insertSelective(new Department(null,"���Բ�"));
//		2����Ա�����ݣ�����Ա������
//		employeeMapper.insertSelective(new Employee(null,"Jerry","M","Jerry@163.com",1));
//		3��������Ա��
//		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
//		for(int i = 0 ;i <1000 ; i++) {
//			String uid = UUID.randomUUID().toString().substring(0,5)+1;
//			mapper.insertSelective(new Employee(null,uid,"M",uid+"@163.com",1));
//		}
//		System.out.println("�����ɹ�");
	}
	
	@Test
	public void testDeptSelect() {
//		Department selectByPrimaryKey = departmentMapper.selectByPrimaryKey(1);
//		System.out.println(selectByPrimaryKey.getDeptName());
	}
	
	
	@Test
	public void testDeptInsert() {
//		int insertSelective = departmentMapper.insertSelective(new Department(null, "������"));
//		System.out.println(insertSelective);
	}
	
	@Test
	public void testdeveltInsert() {
//		int deleteByPrimaryKey = departmentMapper.deleteByPrimaryKey(3);
//		System.out.println(deleteByPrimaryKey);
	}
	
	@Test
	public void testupdateInsert() {
//		int updateByPrimaryKey = departmentMapper.updateByPrimaryKey(new Department(2,"���Բ�"));
//		System.out.println(updateByPrimaryKey);
	}
	
	@Test
	public void testEmpSelectByExample() {
		
		EmployeeExample employeeExample = new EmployeeExample();
		employeeExample.setOrderByClause("emp_id");
//		Criteria criteria = employeeExample.createCriteria();
//		criteria.andEmpIdIsNotNull();

		List<Employee> listTeammembers =employeeMapper.selectByExampleWithDept(employeeExample);  
		for(Employee e : listTeammembers){			
			System.out.println(e);
		}
		
	}
	
}
