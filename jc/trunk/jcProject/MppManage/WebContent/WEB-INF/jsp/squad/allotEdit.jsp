<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
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
<link type="text/css" rel="stylesheet" href="${basePath}/css/dtree.css" />
<script language="javascript" src="${basePath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${basePath}/js/util.js"></script>
<script type="text/javascript" src="${basePath}/js/dtree.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.automaxlength.js"></script>
<script type="text/javascript">

	function check() {
			
		$('#myForm').submit();
		
	}
	
</script>
</head>
<body>
	<form id="myForm" name="myForm" method="post" action="squad_allotUpdate.do">
		<ul class="breadcrumb">
			<li><i class="icon-home icon-2x"></i></li>
			<li>当前位置：${currentPosition}&nbsp;>&nbsp;分配员工工班</li>
		</ul>
		<div class="widget widget-edit">
			<div class="widget-content">
				<table class="pn-ftable table-bordered" border="0" cellpadding="10">
					<tbody>
						<tr>
							<th><span class="point">*</span>员工登录名</th>
							<td class="pn-fcontent"><input type="text"
								 value="${admin.username}" maxlength="50" readonly="readonly" disabled="disabled"/>
							</td>
							<td class="pn-info"></td>
						</tr>
						<tr>
							<th><span class="point">*</span>真实姓名</th>
							<td class="pn-fcontent"><input type="text"
								 value="${admin.name}" maxlength="50" readonly="readonly" disabled="disabled"/>
							</td>
							<td class="pn-info"></td>
						</tr>
						<tr>
							<th><span class="point">*</span>员工工号</th>
							<td class="pn-fcontent"><input type="text"
								 value="${admin.staffNo}" maxlength="50" readonly="readonly" disabled="disabled"/>
							</td>
							<td class="pn-info"></td>
						</tr>
						
						<tr>
							<th>工班：</th>
							<td class="pn-fcontent">
								<div class="dtree">
									<script type="text/javascript">
										var dtree = new Array();
										var i = 1;
										<c:forEach items="${list}" var="parent">
										dtree[i] = new Array();
										dtree[i][0] = "${parent.workNo}";
										dtree[i][1] = "0";
										dtree[i][2] = "${parent.workName}";
										i++;
										</c:forEach>
										d = new dTree('d');
										d.config.check = true;
										d.config.inputName = "squads";
										d.add(0, -1, "工班列表");
										for ( var i = 1; i < dtree.length; i++) {
											d.add(dtree[i][0], dtree[i][1],
													dtree[i][2]);
										}
										document.write(d);
										d.openAll();
										d.setCheck("<c:forEach items="${admin.squads}" var="parent">,${parent.workNo}</c:forEach>");
										$(".dtree img[id*='cd']").bind("click",function(){
											var cd = $(this).attr("id");
											cd = cd.replace(new RegExp("cd"), "");
											if($(this).attr("src").indexOf("checkGray")>=0){
												d.cc(cd);
											}
										});
									</script>
								</div>
							</td>
							<td class="pn-info"></td>
						</tr>
						
					</tbody>
				</table>

			
			</div>
			<!-- /widget-content -->
<div class="widget-bottom">
					<input type="hidden" name="admin.id" value="${admin.id}" />
					<!-- <input type="hidden" name="admin.username" value="${admin.username}" />
					<input type="hidden" name="admin.name" value="${admin.name}" />
					<input type="hidden" name="admin.password" value="${admin.password}" />   -->
					<a href="javascript:history.go(-1);"
						class="btn btn-danger pull-right">返 回</a> <a
						 onclick="check()" href="javascript:;"
						class="btn btn-primary pull-right">保 存</a>
				</div>
		</div>
	</form>
</body>
</html>