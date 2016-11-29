<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="${basePath}/css/plugins/font-awesome/css/font-awesome.min.css" />
	<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
	<link rel="stylesheet" type="text/css" href="${basePath}/theme/${session.theme}/main.css" />
<script language="javascript" src="${basePath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${basePath}/js/bootstrap.min.js"></script>
<script language="javascript" src="${basePath}/js/util.js"></script>
<script language="javascript" src="${basePath}/js/communicate.js"></script>
<style type="text/css">
.col-xs-4 {
    +width: 27%; /* ie7 */ 
    _width: 27%; /* ie6 */ 
}
.ztree * {font-size: 13pt}
</style>
<script type="text/javascript" src="${basePath}/js/jquery.ztree.all-3.5.min.js"></script>
<script language="javascript">
var message = "${message}";
if (message!=null && message !=""){
	alert(message);
	parent.refreshTree();
}
function check() {
	
	if (get("org.orgName").value == "") {
		alert("行政名称不能为空");
		get("org.orgName").focus();
		return false;
	}
	if (get("org.orgCode").value == "") {
		alert("机构编号不能为空");
		get("org.orgCode").focus();
		return false;
	}
	if(get("org.domain").value =="0000"){
		alert("端口需大于0");
		return false;
	}
	var password = get("org.dbPassword").value;
	var repassword = get("rePassword").value;
	var ServerIp = get("org.serverIP").value;
	var patten = /^((2[0-4]\d|25[0-5]|[01]?\d\d?)\.){3}(2[0-4]\d|25[0-5]|[01]?\d\d?)$/g;
	var result = new RegExp(patten).test(ServerIp);
	if(password!=repassword){
		alert("两次密码不同");
		return false;
	}
	if(!checkOrgName()){
		alert("机构编号已存在或出错");
		return false;
	}
	if(!checkOrgCode()){
		alert("机构名称已存在或出错");
		return false;
	}
	if(ServerIp==""){
		return true;
	}else if(result == false){
		alert("服务器ip格式不正确");
		return false;
	}
	return true;
}
function setIFrameHeight() {
	var mainheight = $("#orgchartFrame").contents().find("body").height();
	if (mainheight < 580) {
		mainheight = 580;
	}
	$("#orgchartFrame").height(mainheight);
}
function save() {
	if (get("org.id").value == "") {
		alert("请先选择要修改的信息");
		return;
	}
	 
	if (!check())
		return;
 	var url="${basePath}/admin/org_save.do";
	params={
		'org.id':get("org.id").value,
		'org.orgName':get("org.orgName").value,
		'org.orgType':get("org.orgType").value,
		'org.orgCode':get("org.orgCode").value,
		'org.serverName':get("org.serverName").value,
		'org.serverIP':get("org.serverIP").value,
		'org.domain':get("org.domain").value,
		'org.dbName':get("org.dbName").value,
		'org.dbUserName':get("org.dbUserName").value,
		'org.dbPassword':get("org.dbPassword").value,
		'org.orgStatus':get("org.orgStatus").value,
		'org.priority':get("org.priority").value,
		'org.remark':get("org.remark").value,
		'org.orgLevel':get("org.orgLevel").value,
		'org.treeCode':get("org.treeCode").value,
		'org.parent.id':get("org.parent.id").value
	};
	$.post(url,params,function(jsonTemp){
		var mapA = jsonTemp.map;
		var updateResult=mapA.updateResult;
		if(updateResult=="yes"){
			var org = mapA.org;
			console.log(org.id);
			window.parent.update(org);
			alert("修改成功");
			$("#myForm").submit(); 
		}else{
			alert("修改失败");
		}
		
	},"json"); 
	//$("#myForm").submit();	
}
$(function(){
	//密码禁用粘贴
    $("[type=password]").on("paste",function(){
    	return false;
    });
})

  function checkOrgName(){
	var orgName=$("#orgName").val();
	var orgID=$("#orgID").val();
	url = uri+"admin/org_checkOrgName.do";
	params = {"orgName":orgName,"orgID":orgID};
	 $.ajax({												
			type: "POST",
			async:false,
			dataType:'json',
			//timeout:3000,
			cache:false,
			url: url,
			data:params,
			success:function (data){
				var map = data.map;
				if(map.result =="OK"){
					$("#orgNameMap").val("OK");
					$("#checkOrgName").html("<img src='${basePath}/images/Check_yes.png' />");
				}else if(map.result =="NO"){
					$("#orgNameMap").val("NO");
					$("#checkOrgName").html("<img src='${basePath}/images/Check_no.png' />已存在");
				}
			},
			error:function(){
				$("#orgNameMap").val("NO");
				alert("核查机构名称时，服务器未响应");
			}
	 });
	var result = $("#orgNameMap").val();
	if(result=="OK"){
		 return true;
	}else if(result=="NO"){
		return false;
	}
}


function checkOrgCode(){
	var orgCode=$("#orgCode").val();
	var orgID=$("#orgID").val();
	url = uri+"admin/org_checkOrg.do";
	params = {"orgCode":orgCode,"orgID":orgID};
	 $.ajax({												
			type: "POST",
			async:false,
			dataType:'json',
			//timeout:3000,
			cache:false,
			url: url,
			data:params,
			success:function (data){
				var map = data.map;
				if(map.result =="OK"){
					$("#orgCodeMap").val("OK");
					$("#checkOrgCode").html("<img src='${basePath}/images/Check_yes.png' />");
				}else if(map.result =="NO"){
					$("#orgCodeMap").val("NO");
					$("#checkOrgCode").html("<img src='${basePath}/images/Check_no.png' />已存在");
				}
			},
			error:function(){
				$("#orgCodeMap").val("NO");
				alert("核查机构编号时，服务器未响应");
			}
	 });
	var result = $("#orgCodeMap").val();
	if(result=="OK"){
		 return true;
	}else if(result=="NO"){
		return false;
	}
} 
 
</script>
</head>
<body>
<form id="myForm" method="post" action="org_edit.do">
    <div class="widget widget-edit">
		<div class="widget-content">
			<div style="width:100%">
					<table id="org_info" class="pn-ftable table-bordered" border="0" cellpadding="10" param="0">
						<tbody>
							<tr>
								<th><span class="point">*</span>机构名称</th>
								<td class="pn-fcontent">
									<input type="text" name="org.orgName" id="orgName" maxlength="50" value="${org.orgName}" onblur="javascript:checkOrgName();"/><span id="checkOrgName"></span>
									<input type="hidden" id="orgNameMap" value=""/>
									</td>
								<td class="pn-info"></td>
								<th><span class="point">*</span>机构类型</th>
								<td class="pn-fcontent">
									<select name="org.orgType">
										<c:forEach items="${typeList}" var="item" varStatus="status">
											<option value="${item.value}"  <c:if test="${org.orgType==item.value}">selected</c:if> style="display:<c:if test="${org.orgType > item.value}">none;</c:if>">${item.name}</option>
										</c:forEach>
								</select> 
								</td>
								<td class="pn-info"></td>
							</tr>							
							<tr>
								<th><span class="point">*</span>机构编号</th>
								<td class="pn-fcontent">
									<input type="text" name="org.orgCode" id="orgCode" maxlength="50" value="${org.orgCode}" onblur="javascript:checkOrgCode();"/><span id="checkOrgCode"></span>
									<input type="hidden" id="orgCodeMap" value=""/>
									</td>
								<td class="pn-info"></td>
								<th>服务器名称</th>
								<td class="pn-fcontent">
									<input type="text" name="org.serverName" maxlength="16" value="${org.serverName}"/></td>
								<td class="pn-info"></td>
							</tr>							
							<tr>
								<th>服务器IP</th>
								<td class="pn-fcontent">
									<input type="text" name="org.serverIP" maxlength="15" value="${org.serverIP}"/></td>
								<td class="pn-info"></td>
								<th>端口号</th>
								<td class="pn-fcontent">
									<input type="text" name="org.domain"  value="${org.domain}" maxlength="4"  onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')"/></td>
								<td class="pn-info"></td>
							</tr>
							<tr>
								<th>数据库名称</th>
								<td class="pn-fcontent">
									<input type="text" name="org.dbName" maxlength="16" value="${org.dbName}" /></td>
								<td class="pn-info"></td>
								<th>数据库用户名</th>
								<td class="pn-fcontent">
									<input type="text" name="org.dbUserName" maxlength="10" value="${org.dbUserName}" /></td>
								<td class="pn-info"></td>
							</tr>														
							<tr>
								<th>数据库密码</th>
								<td class="pn-fcontent">
									<input type="password" name="org.dbPassword" maxlength="10" /></td>
								<td class="pn-info"></td>
								<th>重复密码</th>
								<td class="pn-fcontent">
									<input type="password" name="rePassword" maxlength="10" /></td>
								<td class="pn-info"></td>
							</tr>
							<tr>
								<th>状&nbsp;&nbsp;&nbsp;&nbsp;态</th>
								<td class="pn-fcontent" ><label class="inline">
										<input type="radio" name="org.orgStatus" value="0" <c:if test="${org.orgStatus==0}">checked="checked"</c:if> />启用 
								</label><label class="inline"> <input type="radio"
										name="org.orgStatus" value="1" <c:if test="${org.orgStatus==1}">checked="checked"</c:if> />禁止 </label></td>
								<td class="pn-info"></td>
							</tr>
							<tr>
								<th>排&nbsp;&nbsp;&nbsp;&nbsp;序</th>
								<td class="pn-fcontent" colspan="4">								
								<input type="text"
									name="org.priority" size="50"  value="${org.priority}" maxlength="4"  onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" /></td>
								<td class="pn-info" ></td>
							</tr>
							<tr>
								<th>备&nbsp;&nbsp;&nbsp;&nbsp;注</th>
								<td class="pn-fcontent" colspan="4">								
								<input type="text"
									name="org.remark" size="50"  maxlength="2000" value="${org.remark}" /></td>
								<td class="pn-info" ></td>
							</tr>
						</tbody>
					</table>
					<div class="widget-bottom">
						<input type="hidden" id="orgID" name="org.id" value="${org.id}" /> 
						<input type="hidden" name="org.parent.id" value="${org.parent.id}"/>
						<input type="hidden" name="org.orgLevel" value="${org.orgLevel}"/>
						<input type="hidden" name="org.treeCode" value="${org.treeCode}"/>
						<a href="javascript:save();" class="btn btn-primary pull-right">保 存 </a> 
					</div>
				</div>		
				<div id="orgchartFrame" class="widget-content" style="height: 100%">
<%-- 					<iframe id="orgchartFrame" name="orgchartFrame" frameborder="0" height="100%"  width="100%" onload="setIFrameHeight()"  scrolling="no" src="${basePath}/org_orgChart.do"></iframe> --%>
				</div>		
			</div>
			<!-- /widget-content -->
		</div>
	</form>
</body>
</html>