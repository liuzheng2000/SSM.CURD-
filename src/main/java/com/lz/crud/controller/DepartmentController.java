package com.lz.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lz.crud.bean.Department;
import com.lz.crud.bean.Msg;
import com.lz.crud.service.DepartmentService;

/**
 * 处理和部门有关的请求
 * @author 15533
 *
 */
@Controller
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	/**
	 * 返回所有部门信息
	 */
	@RequestMapping("/depts")
	@ResponseBody
	public Msg getDepts() {
		//查出所有部门信息
		List<Department>list = departmentService.getDepts();
		return Msg.success().add("depts", list);	
	}
	
}
