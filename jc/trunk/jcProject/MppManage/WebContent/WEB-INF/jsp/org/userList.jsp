<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" type="text/css"
	href="${basePath}/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="${basePath}/css/plugins/font-awesome/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link rel="stylesheet" type="text/css"
	href="${basePath}/theme/${session.theme}/main.css" />
<script language="javascript" src="${basePath}/js/jquery.min.js"></script>

<script language="javascript">
	function disable(id) {
		if (confirm("系统提示：确认要停用该用户？")) {
			window.location.href = "admin_disable.do?admin.id=" + id;
		}
	}
	function enable(id) {
		if (confirm("系统提示：确认要启用该用户？")) {
			window.location.href = "admin_enable.do?admin.id=" + id;
		}
	}
    function del(id) {
        if (confirm("系统提示：确认要删除该用户？")) {
            window.location.href = "admin_delete.do?admin.id=" + id;
        }
    }
    function doAdd(){
   	 	window.location.href = "admin_add.do?admin.org.id=${'org.id'}";
    }
</script>
</head>
<body>
	<form name="myForm" id="myForm" action="admin_list.do" method="post">
		<div class="widget widget-table">
			<div class="widget-header">
				<i class="icon-th-list"></i>
				<h5>用户列表</h5>
			</div>
			<!-- /widget-header -->
			<div class="widget-content widget-list">
				<table class="table table-striped table-bordered">
					<thead>
						<tr>
							<th>序号</th>
							<th>登录名</th>
							<th>真实姓名</th>
							<th>员工编号</th>
							<th>性别</th>
							<th>启用</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="item" varStatus="status">
							<tr>
								<td>${status.count}</td>
								<td style="text-align: left;"><a href='admin_info.do?admin.id=${item.id}'>${item.username}</a></td>
								<td style="text-align: left;">${item.name}</td>
								<td style="text-align: left;">${item.staffNo}</td>
								<td>${item.sex}</td>
								<td><c:if test="${item.valid==1}">是</c:if> <c:if
										test="${item.valid!=1}">否</c:if></td>
								<td><c:if test="${item.valid==1}">
										<a href="admin_edit.do?admin.id=${item.id}"
											class="btn btn-info"><i class="icon-pencil"></i>&nbsp;&nbsp;修改</a>
											<c:if test="${item.username!='admin'}">
												<a href="javascript:disable(${item.id});"
												class="btn btn-important"><i class="icon-lock"></i>&nbsp;&nbsp;停用</a>
											</c:if>
									</c:if> <c:if test="${item.valid==0}">
										<a href="javascript:enable(${item.id});"
											class="btn btn-success"><i class="icon-unlock"></i>&nbsp;&nbsp;启用</a>
									</c:if>
                                    <c:if test="${item.username!='admin'}">
                                        <a
                                            href="javascript:del(${item.id});" class="btn btn-warning"><i class="icon-trash"></i>&nbsp;&nbsp;删除</a>
                                    </c:if>
                                </td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="widget-bottom">					
					<a class="btn btn-s-md pull-right" href="#" onclick="doAdd()">新增</a>
				</div>
			</div>				
			</div>
			<!-- /widget-content -->
			<!-- /widget-content -->
		</div>
	</form>
</body>
</html>