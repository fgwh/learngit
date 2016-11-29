<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${basePath}/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="${basePath}/css/plugins/font-awesome/css/font-awesome.min.css" />
<!--[if IE 7]>
  <link rel="stylesheet" href="${basePath}/css/plugins/font-awesome/css/font-awesome-ie7.min.css" />
<![endif]-->
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link rel="stylesheet" type="text/css"
	href="${basePath}/theme/${session.theme}/main.css" />
<script language="javascript" src="${basePath}/js/jquery.min.js"></script>
<title></title>
<script language="javascript">
	function del(id) {
		if (confirm("确认要删除吗？")) {
			window.location.href = "role_delete.do?role.id=" + id;
		}
	}
</script>
</head>
<body>
	<form name="myForm" action="role_list.do" method="post">
		<ul class="breadcrumb">
			<li><i class="icon-home icon-2x"></i></li>
			<li>当前位置：${currentPosition}</li>
		</ul>
		<div class="widget widget-table">
			<div class="widget-content" style="min-height:0px;">
				<div class="widget-bottom">
					<a class="btn btn-s-md pull-right" href="role_add.do">添加</a>
				</div>
			</div>
		</div>
		<div class="separator line"></div>
		<div class="widget widget-table">
			<div class="widget-header">
				<i class="icon-th-list"></i>
				<h5>权限列表</h5>
			</div>
			<!-- /widget-header -->
			<div class="widget-content widget-list">
				<table class="table table-striped table-bordered">
					<thead>
						<tr>
							<th>序号</th>
							<th>角色名称</th>
							<th>角色类型</th>
							<th>备注</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="item" varStatus="status">
							<tr>
								<td>${status.count}</td>
								<td style="text-align: left;">${item.name}</td>
								<td style="text-align: left;">${roleTypeMap[item.type]}</td>
								<td style="text-align: left;">${item.remark}</td>
								<td>
									<a href="role_edit.do?role.id=${item.id}"
									class="btn btn-info"><i class="icon-pencil"></i>&nbsp;&nbsp;修改</a>
									<a
									href="javascript:del('${item.id}');" class="btn btn-important"><i class="icon-trash"></i>&nbsp;&nbsp;删除</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
			</div>
			<!-- /widget-content -->
			<div class="widget-bottom">
					<jsp:include page="../include/pager.jsp" />
				</div>
		</div>
	</form>
</body>
</html>