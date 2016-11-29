<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>编辑修改个人信息</title>
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
	if ('${message}' != '') {
		alert('${message}');
	}
	function check(){
		if($("#password2").val() != $("#password").val()){
			alert("两次密码不一致！");
			return false;
		}
		if($('#password').val() != "" && $('#password').val().length < 6){
			alert("密码长度不能小于6位！");
			return;
		}
		var email = $(".email").val();
		if(email != "" && !(/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/.test(email))){
			alert('电子邮件格式不正确');
			return false;
		}
		var sMobile = $(".mobile").val()
		/* if(sMobile != "" && !/^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/i.test(sMobile)){ 							
	        alert("不是完整的11位手机号或者正确的手机号前七位"); 
	        return false; 
	    } */
		if(sMobile != "" && !(/^1[3|4|5|8][0-9]\d{4,8}$/.test(sMobile))){ 
	        alert("不是完整的11位手机号或者正确的手机号前七位"); 
	        return false; 
	    }
	    				
		$("#myForm").submit();
	}

    $(function() {
        // Add by Bruce.Zhan on 2014/08/20 截取输入的最大值范围
        /************************Start*************************************/

        $("input[type='text']").maxlen();

        /************************End*************************************/
      //密码禁用粘贴
       $("[type=password]").on("paste",function(){
       		return false;
       });
    });
</script>
</head>
<body>
	<form id="myForm" method="post" action="admin_updatemyself.do">
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
							<th>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</th>
							<td class="pn-fcontent"><input type="password"
								name="admin.password" id="password"  maxlength="32" onkeyup="this.value=this.value.replace(/^ +| +$/g,'')" /></td>
							<td class="pn-info">（若不修改则留空）</td>
						</tr>
						<tr>
							<th>重复密码</th>
							<td class="pn-fcontent"><input type="password"
								id="password2" equalTo="#password"  maxlength="32" onkeyup="this.value=this.value.replace(/^ +| +$/g,'')" /></td>
							<td class="pn-info"></td>
						</tr>
						<tr>
							<th>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</th>
							<td class="pn-fcontent"><label class="inline">${admin.sex}</label></td>
							<td class="pn-info"></td>
						</tr>
						<tr>
							<th>邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱</th>
							<td class="pn-fcontent"><input type="text"
								name="admin.email" maxlength="50" value="${admin.email}" class="email" /></td>
							<td class="pn-info"></td>
						</tr>
						<tr>
							<th>手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机</th>
							<td class="pn-fcontent"><input type="text"
								name="admin.phone" value="${admin.phone}" class="mobile" /></td>
							<td class="pn-info"></td>
						</tr>
						<tr>
							<th>身&nbsp;&nbsp;份&nbsp;&nbsp;证</th>
							<td class="pn-fcontent"><label class="inline">${admin.userId}</label></td>
							<td class="pn-info"></td>
						</tr>
						<tr>
							<th>路段中心</th>
							<td class="pn-fcontent"><label class="inline">${archi[5] }</label></td>
							<td class="pn-info"></td>
						</tr>
						<tr>
							<th>收&nbsp;&nbsp;费&nbsp;&nbsp;站</th>
							<td class="pn-fcontent"><label class="inline">${archi[3] }</label></td>
							<td class="pn-info"></td>
						</tr>
						<tr>
							<th>收费广场</th>
							<td class="pn-fcontent"><label class="inline">${archi[1] }</label></td>
							<td class="pn-info"></td>
						</tr>
						<tr>
							<th>用户角色</th>
							<td rowspan="3">
								<div style="width:98%; overflow:auto;">
								<c:forEach items="${admin.roles}" var="item">
									<div class="pull-left" ><label class="inline">${item.name}</label></div>
								</c:forEach>
								
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
					<a onclick="check()" href="javascript:void(0);" class="btn btn-primary pull-right">保 存</a>
					<a href="javascript:history.go(-1);" class="btn btn-danger pull-right">返 回</a>
				</div>
		</div>

	</form>
</body>
</html>