<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
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

<script language="javascript">
	function disable(id) {
		if (confirm("系统提示：确认要停用该用户？")) {
			window.location.href = "admin_disable.do?admin.id="+id;
		}
	}
	function enable(id) {
		if (confirm("系统提示：确认要启用该用户？")) {
			window.location.href = "admin_enable.do?admin.id="+id;
		}
	}
    function del(id) {
        if (confirm("系统提示：确认要删除该用户？")) {
            window.location.href = "admin_delete.do?admin.id="+id;
        }
    }
    function edit(id){
    	window.location.href = "admin_edit.do?admin.id="+id;
    }
    $(function() {
            //按回车键，查询数据列表
            document.onkeydown = function(event) {
                var ev = document.all ? window.event : event;
                if (ev.keyCode == 13) {
                    $("#myForm").submit();
                    ev.preventDefault();
                }
            };
    });
</script>
</head>
<body>
	<form name="myForm" id="myForm" action="admin_list.do" method="post">
		<ul class="breadcrumb">
			<li><i class="icon-home icon-2x"></i></li>
			<li>当前位置：${currentPosition}</li>
		</ul>
		<div class="widget widget-table">
			<div class="widget-content">
				<table class="pn-ftable" border="0" cellpadding="10">
					<tbody>
						<tr>
							<th>登录名:</th>
							<td class="pn-fcontent"><input name="admin.username" value="${admin.username}"
								maxlength="20" type="text" /></td>
							<th>真实姓名:</th>
							<td class="pn-fcontent"><input name="admin.name" value="${admin.name}"
								maxlength="20" type="text" /></td>
						</tr>
						<tr>
							<th>员工编号:</th>
							<td class="pn-fcontent"><input name="admin.staffNo" value="${admin.staffNo}"
								maxlength="20" type="text" /></td>
							<th>状态:</th>
							<td class="pn-fcontent"><select name="admin.valid">
									<option value="">全部</option>
									<option value="1" <c:if test="${admin.valid=='1'}">selected</c:if>>启用</option>
									<option value="0" <c:if test="${admin.valid=='0'}">selected</c:if>>停用</option>
							</select></td>
						</tr>
						<tr>
						<th>当前机构:</th>
							<td class="pn-fcontent" >
										<c:forEach items="${orgList}" var="item">
											<c:if test="${admin.org.id eq item.id}">${item.orgName}</c:if>
										</c:forEach>
								<input type="hidden" name="admin.org.id" value="${admin.org.id}"/></td>
						<th></th>
						<td class="pn-info"></td>
						</tr>
					</tbody>
				</table>
				
			</div>
			<!-- /widget-content -->
			<div class="widget-bottom">
					<a class="btn btn-s-md btn-primary pull-right"
						onclick="$(this).parents('form').submit();" href="javascript:;">查询</a>
					<a class="btn btn-s-md pull-right" href="admin_add.do">添加</a>
				</div>
		</div>
		<div class="separator line"></div>
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
										<a href="javascript:edit('${item.id}');"
											class="btn btn-info"><i class="icon-pencil"></i>&nbsp;&nbsp;修改</a>
											<c:if test="${item.username!='admin' && item.username!='stationAdmin'}">
												<a href="javascript:disable('${item.id}');"
												class="btn btn-warning"><i class="icon-lock"></i>&nbsp;&nbsp;停用</a>
											</c:if>
									</c:if> <c:if test="${item.valid==0}">
										<a href="javascript:enable('${item.id}');"
											class="btn btn-success"><i class="icon-unlock"></i>&nbsp;&nbsp;启用</a>
									</c:if>
                                    <c:if test="${item.username!='admin' && item.username!='stationAdmin'}">
                                        <a
                                            href="javascript:del('${item.id}');" class="btn btn-important"><i class="icon-trash"></i>&nbsp;&nbsp;删除</a>
                                    </c:if>
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