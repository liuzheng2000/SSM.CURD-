<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>员工列表</title>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<script type="text/javascript"
	src="${APP_PATH}/static/js/jquery-1.12.4.min.js"></script>
<link
	href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>


	<!-- 员工修改的模态框 -->
	<div class="modal fade" id="empUpdateModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">员工修改</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-2 control-label">empName</label>
							<div class="col-sm-10">
								<p class="form-control-static" id="empName_update_static"></p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">email</label>
							<div class="col-sm-10">
								<input type="text" name="email" class="form-control"
									id="email_update_input" placeholder="email@atguigu.com">
								<span class="help-block"></span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">gender</label>
							<div class="col-sm-10">
								<label class="radio-inline"> <input type="radio"
									name="gender" id="gender1_update_input" value="M"
									checked="checked"> 男
								</label> <label class="radio-inline"> <input type="radio"
									name="gender" id="gender2_update_input" value="F"> 女
								</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">deptName</label>
							<div class="col-sm-4">
								<!-- 部门提交部门id即可 -->
								<select class="form-control" name="dId">
								</select>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="emp_update_btn">更新</button>
				</div>
			</div>
		</div>
	</div>




	<!-- 员工添加的模态框 -->
	<div class="modal fade" id="empAddModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">员工添加</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-2 control-label">empName</label>
							<div class="col-sm-10">
								<input type="text" name="empName" class="form-control"
									id="empName_add_input" placeholder="empName"> <span
									class="help-block"></span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">email</label>
							<div class="col-sm-10">
								<input type="text" name="email" class="form-control"
									id="email_add_input" placeholder="email@126.com"> <span
									class="help-block"></span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">gender</label>
							<div class="col-sm-10">
								<label class="radio-inline"> <input type="radio"
									name="gender" id="gender1_add_input" value="M"
									checked="checked"> 男
								</label> <label class="radio-inline"> <input type="radio"
									name="gender" id="gender2_add_input" value="F"> 女
								</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">deptName</label>
							<div class="col-sm-4">
								<select class="form-control" name="dId">
									<!-- 查出dId 放入此下拉列表 -->
								</select>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="emp_save_btn">保存</button>
				</div>
			</div>
		</div>
	</div>





	<!-- 显示页面 -->
	<div class="container">
		<!-- 标题 -->
		<div class="row">
			<div class="col-md-12">
				<h1>SSM-CRUD</h1>
			</div>
		</div>
		<!-- 按钮 -->
		<div class="row">
			<div class="col-md-4 col-md-offset-8">
				<button class="btn btn-primary" id="emp_add_modal_btn">新增</button>
				<button class="btn btn-danger" id="emp_delete_all_btn">删除</button>
			</div>
		</div>
		<!-- 显示表格数据 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover" id="emps_table">
					<thead>
						<tr>
							<th>
								<input type="checkbox" id="check_all"/>
							</th>
							<th>#</th>
							<th>empName</th>
							<th>gender</th>
							<th>emial</th>
							<th>deptName</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>


					</tbody>
				</table>
			</div>
		</div>


		<!-- 显示分页信息 -->
		<div class="row">
			<!--分页文字信息  -->
			<div class="col-md-6" id="page_info_area"></div>
			<!-- 分页条信息 -->
			<div class="col-md-6" id="page_nav_area"></div>
		</div>
	</div>
	<script type="text/javascript">
		var totalRecord, currentPage;
		//页面加载完成以后，直接去发送ajax请求，获取分页数据
		$(function() {
			to_page(1);
		});

		function to_page(pn) {
			$.ajax({
				url : "${APP_PATH}/emps",
				data : "pn=" + pn,
				type : "Get",
				success : function(result) {
					//console.log(result);
					//1.解析并显示员工数据
					build_emps_table(result);
					//2.解析并显示分页信息	
					build_page_info(result);
					//3解析显示分页条数据
					build_page_nav(result);
				}
			});
		}

		function build_emps_table(result) {
			//清空table表格
			$("#emps_table tbody").empty();
			var emps = result.extend.pageInfo.list;
			$.each(emps, function(index, item) {
				var checkBoxTd = $("<td><input type='checkbox' class='check_item'/></td>");
				var empIdTd = $("<td></td>").append(item.empId);
				var empNameTd = $("<td></td>").append(item.empName);
				var genderTd = $("<td></td>").append(
						item.gender == 'M' ? "男" : "女");
				var emailTd = $("<td></td>").append(item.email);
				var deptNameTd = $("<td></td>")
						.append(item.department.deptName);
				var editBtn = $("<button></button>").addClass(
						"btn btn-primary btn-sm edit_btn").append(
						$("<span></span>").addClass(
								"glyphicon glyphicon-pencil")).append("编辑");
				//为编辑按钮添加自定义的属性，来表示当前员工id
				editBtn.attr("edit-id", item.empId);
				var delBtn = $("<button></button>").addClass(
						"btn btn-danger btn-sm delete_btn").append(
						$("<span></span>")
								.addClass("glyphicon glyphicon-trash")).append(
						"删除");
				delBtn.attr("del-id", item.empId);
				var btnTd = $("<td></td>").append(editBtn).append(" ").append(
						delBtn);
				$("<tr></tr>").append(checkBoxTd).append(empIdTd).append(empNameTd).append(
						genderTd).append(emailTd).append(deptNameTd).append(
						btnTd).appendTo("#emps_table tbody");
			});
		}
		//解析分页信息
		function build_page_info(result) {
			$("#page_info_area").empty();
			var pageInfo = $("<span></span>").append(
					"当前" + result.extend.pageInfo.pageNum + "页，总"
							+ +result.extend.pageInfo.pages + "页，总"
							+ +result.extend.pageInfo.total + "条记录")
			$("#page_info_area").append(pageInfo);
			totalRecord = result.extend.pageInfo.total;
			currentPage = result.extend.pageInfo.pageNum;
		}

		//解析显示分页条
		function build_page_nav(result) {
			//page_nav_area
			$("#page_nav_area").empty();
			var ul = $("<ul></ul>").addClass("pagination");

			//构建元素
			var firstPageLi = $("<li></li>").append(
					$("<a></a>").append("首页").attr("href", "#"));
			var prePageLi = $("<li></li>").append(
					$("<a></a>").append("&laquo;"));

			if (result.extend.pageInfo.hasPreviousPage == false) {
				firstPageLi.addClass("disabled");
				prePageLi.addClass("disabled");
			} else {

				firstPageLi.click(function() {
					to_page(1);
				});

				prePageLi.click(function() {
					to_page(result.extend.pageInfo.pageNum - 1);
				});
			}

			//为元素添加点击事件
			var nextPageLi = $("<li></li>").append(
					$("<a></a>").append("&raquo;"));
			var lastPageLi = $("<li></li>").append(
					$("<a></a>").append("末页").attr("href", "#"));

			if (result.extend.pageInfo.hasNextPage == false) {
				nextPageLi.addClass("disabled");
				lastPageLi.addClass("disabled");
			} else {
				nextPageLi.click(function() {
					to_page(result.extend.pageInfo.pageNum + 1);
				});
				lastPageLi.click(function() {
					to_page(result.extend.pageInfo.pages);
				});
			}

			//添加首页和前一页
			ul.append(firstPageLi).append(prePageLi);
			//1，2，3
			$.each(result.extend.pageInfo.navigatepageNums, function(index,
					item) {

				var numLi = $("<li></li>").append($("<a></a>").append(item));
				if (result.extend.pageInfo.pageNum == item) {
					numLi.addClass("active");
				}
				numLi.click(function() {
					to_page(item);
				});
				ul.append(numLi);
			});
			//添加下一页和末页的提示
			ul.append(nextPageLi).append(lastPageLi);
			//把ul加入到nav
			var navEle = $("<nav></nav>").append(ul);
			navEle.appendTo("#page_nav_area");
		}

		//点击新增按钮，弹出模态框
		$("#emp_add_modal_btn").click(function() {

			reset_form("#empAddModal form");
			//发送ajax请求，查出部门信息，显示在下拉列表中
			getDepts("#empAddModal select");
			//弹出模态框
			$("#empAddModal").modal({
				backdrop : "static"
			})
		});

		// 清空表单样式及内容
		function reset_form(ele) {
			$(ele)[0].reset();
			$(ele).find("*").removeClass("has-error has-success");
			$(ele).find(".help-block").text("");
		}

		function getDepts(ele) {
			$(ele).empty();
			$.ajax({
				url : "${APP_PATH}/depts",
				type : "GET",
				success : function(result) {
					//显示部门信息在下拉列表中
					$.each(result.extend.depts, function() {
						var optionEle = $("<option></option>").append(
								this.deptName).attr("value", this.deptId);
						optionEle.appendTo(ele);
					});
				}
			});
		}

		//校验方法
		/**
		function vaildate_add_from(){
			//拿到校验中的数据，使用正则表达式
			
			
			var empName = $("#empName_add_input").val();
			var regName = /^[\u2E80-\u9FFFa-zA-Z0-9_-]{2,4}$|^[a-zA-Z0-9_-]{2,16}$/;
			if (!regName.test(empName)) {
				//alert("用户名可以是2-4位中文或者2-16位英文和数字的组合");	
				show_validate_msg("#empName_add_input","error","用户名可以是2-4位中文或者2-16位英文和数字的组合");
				return false;
			}else{
				show_validate_msg("#empName_add_input","success","");
			}
			//2校验Email
			var email=$("#email_add_input").val();
			var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			if(!regEmail.test(email)){
				show_validate_msg("#email_add_input","error","邮箱格式不正确");
				return false;
			}else{
				show_validate_msg("#email_add_input", "success","");
			}
			return true;
			
		}
		 */

		function show_validate_msg(ele, status, msg) {
			//清除当前元素的校验状态
			$(ele).parent().removeClass("has-error");
			$(ele).parent().removeClass("has-success");
			$(ele).next("span").text("");
			if ("success" == status) {
				$(ele).parent().addClass("has-success");
				$(ele).next("span").text(msg);
			} else if ("error" == status) {
				$(ele).parent().addClass("has-error");
				$(ele).next("span").text(msg);
			}
		}

		//判断Email
		$("#email_add_input").focusout(function() {
			//发送ajax请求校验Email是否可用
			var email = this.value;
			var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			if (regEmail.test(email)) {
				show_validate_msg("#email_add_input", "success", "邮箱格式正确");
				$("#emp_save_btn").attr("ajax-va", "success");
			} else {
				show_validate_msg("#email_add_input", "error", "邮箱格式不正确");
				$("#emp_save_btn").attr("ajax-va", "error");
			}
			/**
			$.ajax({
				url:"${APP_PATH}/checkuser",
				data:"empName="+empName,
				type:"POST",
				success:function(result){
					if(result.code==200){
						show_validate_msg("#empName_add_input","success","用户名可用");
						$("#emp_save_btn").attr("ajax-va","success");
					}else{
						show_validate_msg("#empName_add_input","error",result.extend.va_msg);
						$("#emp_save_btn").attr("ajax-va","error");
					}
				}
			});
			 */
		});

		$("#email_update_input").focusout(function() {
			//发送ajax请求校验Email是否可用
			var email = this.value;
			var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			if (regEmail.test(email)) {
				show_validate_msg("#email_update_input", "success", "邮箱格式正确");
				$("#emp_save_btn").attr("ajax-va", "success");
			} else {
				show_validate_msg("#email_update_input", "error", "邮箱格式不正确");
				$("#emp_save_btn").attr("ajax-va", "error");
			}
		});

		//1判断之前的ajax是否成功

		//校验用户名是否可用
		$("#empName_add_input").focusout(
				function() {
					//发送ajax请求校验用户名是否可用
					var empName = this.value;
					$.ajax({
						url : "${APP_PATH}/checkuser",
						data : "empName=" + empName,
						type : "POST",
						success : function(result) {
							if (result.code == 200) {
								show_validate_msg("#empName_add_input",
										"success", "用户名可用");
								$("#emp_save_btn").attr("ajax-va", "success");
							} else {
								show_validate_msg("#empName_add_input",
										"error", result.extend.va_msg);
								$("#emp_save_btn").attr("ajax-va", "error");
							}
						}
					});
				});

		//点击保存，保存员工
		$("#emp_save_btn")
				.click(
						function() {
							//1提交过数据库进行校验
							/**
							if(!vaildate_add_from()){
								return false;
							}
							 */
							// 判断之前的ajax用户名是否校验成功，若成功
							if ($(this).attr("ajax-va") == "error") {
								return false;
							}

							$
									.ajax({
										url : "${APP_PATH}/emp",
										type : "POST",
										data : $("#empAddModal form")
												.serialize(),
										success : function(result) {
											if (result.code == 200) {
												$("#empAddModal").modal('hide');
												to_page(totalRecord);
											} else {
												if (undefined != result.extend.errorFields.email) {
													show_validate_msg(
															"#email_add_input",
															"error",
															result.extend.errorFields.email);
												}
												if (undefined != result.extend.errorFields.empName) {
													show_validate_msg(
															"#empName_add_input",
															"error",
															result.extend.errorFields.empName);
												}
											}
										}
									});
						});

		$(document).on("click", ".edit_btn", function() {
			reset_form("#empUpdateModal form");
			//	alert("edit");
			//1、查出部门信息，并显示部门列表
			getDepts("#empUpdateModal select");
			//查询员工信息
			getEmp($(this).attr("edit-id"));

			//获取员工的ID
			$("#emp_update_btn").attr("edit-id", $(this).attr("edit-id"));

			$("#empUpdateModal").modal({
				backdrop : "static"
			});
		});

		function getEmp(id) {
			$.ajax({
				url : "${APP_PATH}/emp/" + id,
				type : "GET",
				success : function(result) {
					var empData = result.extend.emp;
					$("#empName_update_static").text(empData.empName);
					$("#email_update_input").val(empData.email);
					$("#empUpdateModal input[name=gender]").val(
							[ empData.gender ]);
					$("#empUpdateModal select").val([ empData.dId ]);
				}
			});
		}

		//点击更新，更新员工信息
		$("#emp_update_btn").click(function() {
			//2.发送ajax请求保存更新的员工数据
			$.ajax({
				url : "${APP_PATH}/emp/" + $(this).attr("edit-id"),
				type : "PUT",
				data : $("#empUpdateModal form").serialize(),
				success : function(result) {
					//1、关闭对话框
					$("#empUpdateModal").modal("hide");
					//2、回到本页面
					to_page(currentPage);
				}
			});
		});

		//点击删除
		$(document).on("click", ".delete_btn", function() {
			//1弹出是否删除的对话框
			var empName = $(this).parents("tr").find("td:eq(2)").text();
			var empId = $(this).attr("del-id");
			//alert($(this).parents("tr").find("td:eq(1)").text());
			if (confirm("确认删除【" + empName + "】吗？")) {
				//确认发送ajax请求
				$.ajax({
					url : "${APP_PATH}/emp/" + empId,
					type : "DELETE",
					success : function(result) {
						alert(result.msg);
						//回到本页
						to_page(currentPage);
					}
				});
			}
		});
		
		$("#check_all").click(function(){
		
			$(".check_item").prop("checked",$(this).prop("checked"));
		});
		
		$(document).on("click",".check_item",function(){
			var flag = $(".check_item:checked").length==$(".check_item").length;
			$("#check_all").prop("checked",flag);
		});
		
		//点击全部删除，就批量删除
		$("#emp_delete_all_btn").click(function(){
			var empNames = "";
			var del_idstr = "";
			$.each($(".check_item:checked"),function(){
				empNames += $(this).parents("tr").find("td:eq(2)").text() + ",";
				del_idstr += $(this).parents("tr").find("td:eq(1)").text()+"-";
			});
			empNames=empNames.substring(0, empNames.length-1);
			del_idstr=del_idstr.substring(0, del_idstr.length-1);
			if(confirm("确认删除【"+empNames+"】吗？")){
				$.ajax({
					url:"${APP_PATH}/emp/"+del_idstr,
					type:"DELETE",
					success:function(result){
						alert(result.msg);
						to_page(currentPage);
					}
				});
			}
		});
		
		
	</script>
</body>
</html>