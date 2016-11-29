<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>add</title>
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
    <script type="text/javascript" src="${basePath}/js/jquery.automaxlength.js"></script>
<script language="javascript">
if ('${message}' != '') {
	alert('${message}');
}
function check() {

	if($('[name="admin.username"]').val() == ""){
		alert("登录名不能为空！");
		return;
	}
	if($('#password').val() == ""){
		alert("密码不能为空！");
		return;
	}
	if($('#password').val() != "" && $('#password').val().length < 6){
		alert("密码长度不能小于6位！");
		return;
	}
	if($('#password2').val() != $('#password').val()){
		alert("输入密码不一致！");
		return;
	}
	if($('[name="admin.name"]').val() == ""){
		alert("真实姓名不能为空！");
		return;
	}
	var email = $(".email").val();
	if(email != "" && !(/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/.test(email))){
		alert('电子邮件格式不正确');
		return false;
	}
	var sMobile = $(".mobile").val()
	if(sMobile != "" && !(/^1[3|4|5|8][0-9]\d{4,8}$/.test(sMobile))){ 
        alert("不是完整的11位手机号或者正确的手机号前七位"); 
        return false; 
    }
	var staffNo = $('[name="admin.staffNo"]').val();
	var username = $('[name="admin.username"]').val();
	if(staffNo != ""){
		$.post(
			"admin_isExists.do",
			{ "admin.staffNo" : staffNo, "admin.username" : username },
			function(data){
				if(data.staffNo != "" || data.username != ""){
					alert(data.staffNo + " \n " +data.username); 
				    return false;
				}
				else{ $("#myForm").submit(); }
			},
			"json"
		);
	}
	else{
		$("#myForm").submit();
	}
}
    $(function() {
        // Add by Bruce.Zhan on 2014/08/20 截取输入的最大值范围
        /************************Start*************************************/

        $("input[type='text']").maxlen();

        /************************End*************************************/
    });
</script>
</head>
<body>
	<form id="myForm" name="myForm" method="post" action="admin_save.do">
		<ul class="breadcrumb">
			<li><i class="icon-home icon-2x"></i></li>
			<li>当前位置：${currentPosition}&nbsp;>&nbsp;新增系统管理员</li>
		</ul>
		<div class="widget widget-edit">
			<div class="widget-content">
				<table class="pn-ftable table-bordered" border="0" cellpadding="10">
					<tbody>
						<tr>
							<th><span class="point">*</span>登&nbsp;&nbsp;录&nbsp;&nbsp;名</th>
							<td class="pn-fcontent"><input type="text"
								name="admin.username" minlength="3" maxlength="20" /></td>
							<td class="pn-info"><c:if test="${!empty usernameIsExists}">
									<label for="admin.username" generated="true" class="error">${usernameIsExists}</label>
								</c:if></td>
						</tr>
						<tr>
							<th><span class="point">*</span>真实姓名</th>
							<td class="pn-fcontent"><input type="text" name="admin.name" maxlength="80" /></td>
							<td class="pn-info"></td>
						</tr>
						<tr>
							<th>员工编号</th>
							<td class="pn-fcontent"><input type="text"
								name="admin.staffNo" class="{required:true, maxlength:8}" maxlength="8" /></td>
							<td class="pn-info"></td>
						</tr>
						<tr>
							<th>所属机构</th>
							<td class="pn-fcontent">
								<select name="admin.org.id">
										<option value="" <c:if test="${admin.org eq null}">selected</c:if>>根目录</option>
										<c:forEach items="${orgList}" var="item">
											<option value="${item.id}" level="${item.level}" 
												<c:if test="${admin.org.id eq item.id}">selected</c:if>
											>${fn:substring("││││││││││",0,item.level-1)}├${item.name}</option>
										</c:forEach></select>
							</td>
						</tr>
						<tr>
							<th><span class="point">*</span>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</th>
							<td class="pn-fcontent"><input type="password" id="password"
								name="admin.password" minlength="6" maxlength="32" /></td>
							<td class="pn-info"></td>
						</tr>
						<tr>
							<th><span class="point">*</span>重复密码</th>
							<td class="pn-fcontent"><input type="password"
								id="password2" equalTo="#password"
								minlength="6"  maxlength="32" /></td>
							<td class="pn-info"></td>
						</tr>
						<tr>
							<th>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</th>
							<td class="pn-fcontent"><label class="inline"> <input
									 type="radio" name="admin.sex"
									value="男" checked /><i class="icon-male"></i></label> <label
								class="inline"> <input 
									type="radio" name="admin.sex" value="女" /><i
									class="icon-female"></i></label></td>
							<td class="pn-info"></td>
						</tr>
						<tr>
							<th>邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱</th>
							<td class="pn-fcontent"><input type="text"
								name="admin.email" class="email"  maxlength="50" /></td>
							<td class="pn-info"></td>
						</tr>
						<tr>
							<th>手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机</th>
							<td class="pn-fcontent"><input type="text"
								name="admin.phone" class="mobile" /></td>
							<td class="pn-info"></td>
						</tr>
						<tr>
							<th>用户角色：</th>
							<td class="pn-fcontent">
								<div style="width:98%; overflow:auto;">
								<c:forEach items="${roleList}" var="item">
									<div class="pull-left" >
										<input style="width: 30px; border: 0;" type="checkbox" name="roles" value="${item.id}" />${item.name}
									</div>
								</c:forEach>
								</div>
							</td>
							<td class="pn-info"></td>
						</tr>
						<tr>
							<th>是否禁用</th>
							<td class="pn-fcontent"><label class="inline"> <input
									 type="radio" name="admin.valid"
									value="1" checked />否
							</label> <label class="inline"> <input
									 type="radio" name="admin.valid"
									value="0" />是
							</label></td>
							<td class="pn-info"></td>
						</tr>
					</tbody>
				</table>
				<div class="widget-bottom">
					<a href="javascript:history.go(-1);" class="btn btn-danger pull-right">返 回</a> <a
						onclick="check()" href="javascript:void(0);"
						class="btn btn-primary pull-right">保 存</a>
				</div>
			</div>
			<!-- /widget-content -->
		</div>
	</form>
</body>
</html>