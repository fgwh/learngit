<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>查看修改个人信息</title>
<link rel="stylesheet" type="text/css"
	href="${basePath}/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="${basePath}/css/plugins/font-awesome/css/font-awesome.min.css" /> 
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link rel="stylesheet" type="text/css"
	href="${basePath}/theme/${session.theme}/main.css" />
<script language="javascript" src="${basePath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.automaxlength.js"></script>
<script language="javascript" src="${basePath}/js/util.js"></script>
<script language="javascript">
	function editmyself() {
		$("#myForm").submit();
	}
</script>
</head>
<body>
	<form id="myForm" method="post" action="admin_editmyself.do">
		<ul class="breadcrumb">
			<li><i class="icon-home icon-2x"></i></li>
			<li>当前位置：${currentPosition}</li>
		</ul>
		<div class="widget widget-edit">
			<div class="widget-content">
				<table class="pn-ftable table-bordered" border="0" cellpadding="10">
					<tbody>
						<tr>
							<th>登&nbsp;&nbsp;录&nbsp;&nbsp;名</th>
							<td class="pn-fcontent"><label class="inline">${admin.username}</label></td>
							<td class="pn-info"></td>
						</tr>
						<tr>
							<th>真实姓名</th>
							<td class="pn-fcontent"><label class="inline">${admin.name}</label></td>
							<td class="pn-info"></td>
						</tr>
						<tr>
							<th>员工编号</th>
							<td class="pn-fcontent"><label class="inline">${admin.staffNo}</label></td> 
							<td class="pn-info"></td>
						</tr>
						<tr>
							<th>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</th>
							<td class="pn-fcontent"><label class="inline">${admin.sex}</label></td>
							<td class="pn-info"></td>
						</tr>
						<tr>
							<th>邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱</th>
							<td class="pn-fcontent"><label class="inline">${admin.email}</label></td>
							<td class="pn-info"></td>
						</tr>
						<tr>
							<th>手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机</th>
							<td class="pn-fcontent"><label class="inline">${admin.phone}</label></td>
							<td class="pn-info"></td>
						</tr>
						<tr>
							<th>身&nbsp;&nbsp;份&nbsp;&nbsp;证</th>
							<td class="pn-fcontent"><label class="inline">${admin.userId}</label></td>
							<td class="pn-info"></td>
						</tr>
						<tr>
							<th>所属机构</th>
							<td class="pn-fcontent"><label class="inline">${admin.org.orgName}</label></td>
							<td class="pn-info"></td>
						</tr>
						<tr>
							<th>用户角色</th>
							<td class="pn-fcontent">
								<label class="inline">
								
									<c:forEach items="${admin.roles}" var="item">
										<div class="pull-left" >${item.name}&nbsp;&nbsp;</div>
									</c:forEach>
								
								</label>
	                        </td>
	                        <td class="pn-info"></td>
						</tr>
					</tbody>
				</table>
				
			</div>
			<!-- /widget-content -->
			<div class="widget-bottom">
					<input type="hidden" name="admin.id" value="${admin.id}" /> 
					<a onclick="editmyself()" href="javascript:void(0);"
						class="btn btn-primary pull-right">编辑</a>
				</div>
		</div>

	</form>
</body>
</html>