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

<script type="text/javascript">
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
	<form name="myForm" id="myForm" action="squad_listAdmin.do" method="post">
		<ul class="breadcrumb">
			<li><i class="icon-home icon-2x"></i></li>
			<li>当前位置：${currentPosition}</li>
		</ul>
		<div class="widget widget-table">
			<div class="widget-content">
				<table class="pn-ftable" border="0" cellpadding="10">
					<tbody>
						<tr>
							<th>登录名 :</th>
							<td class="pn-fcontent"><input name="admin.username" value="${admin.username}"
								maxlength="20" type="text" /></td>
							<th>真实姓名 :</th>
							<td class="pn-fcontent"><input name="admin.name" value="${admin.name}"
								maxlength="20" type="text" /></td>
						</tr>
						<tr>
							<th>员工编号 :</th>
							<td class="pn-fcontent"><input name="admin.staffNo" value="${admin.staffNo}"
								maxlength="20" type="text" /></td>
							<th></th>
							<td class="pn-fcontent">
							    <input type="hidden" name="admin.valid" value="1"/>
							</td>
						</tr>
					</tbody>
				</table>
				
			</div>
			<!-- /widget-content -->
			<div class="widget-bottom">
					<a class="btn btn-s-md btn-primary pull-right"
						onclick="$(this).parents('form').submit();" href="javascript:;">搜索</a>
					
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
							<th>工班</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="item" varStatus="status">
							<tr>
								<td>${status.count}</td>
								<td style="text-align: centre;"><a href='admin_info.do?admin.id=${item.id}'>${item.username}</a></td>
								<td style="text-align: centre;">${item.name}</td>
								<td style="text-align: centre;">${item.staffNo}</td>
								<td style="text-align: centre;">
									<c:forEach items="${item.squads}" var="ite">
					                    &nbsp;&nbsp;${ite.workName}
									</c:forEach>
								</td>
								<td>
										<a href="squad_allotEdit.do?admin.id=${item.id}"
											class="btn btn-info"><i class="icon-pencil"></i>&nbsp;&nbsp;分配</a>
                                </td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="widget-bottom">
					<jsp:include page="../include/pager.jsp" />
				</div>
			<!-- /widget-content -->
		</div>
	</form>
</body>
</html>