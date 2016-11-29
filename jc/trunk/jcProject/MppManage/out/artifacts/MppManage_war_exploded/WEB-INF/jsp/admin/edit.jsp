<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<title>修改用户资料</title>
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
	var sMobile = $(".mobile").val();
	if(sMobile != "" && !/^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/i.test(sMobile)){ 
        alert("不是完整的11位手机号或者正确的手机号前七位"); 
        return false; 
    }
    
    /*身份证验证*/
    var userId = $('[name="admin.userId"]').val();
    if(userId != "" && !checkIdcard(userId)){
		return false;
	}
	
	/*收费站验证*/
	/* var stationNo = $('[name="admin.stationNo"]').val();
	if(stationNo == '-1') {
		alert('请选择收费站');
		return false;
	}
	
	/*收费广场验证*/
	/* var squareNo = $('[name="admin.squareNo"]').val();
	if(squareNo == '-1') {
		alert('请选择收费广场');
		return false;
	}  */
	
	/*用户角色验证*/	
	var str=document.getElementsByName("roles");
	var objarray=str.length;
	var chestr="";
	for (i=0;i<objarray;i++)
		{
		  if(str[i].checked == true)
		  {
		   chestr+=str[i].value+",";
		  }
		}
	if(chestr == "")
		{
		  alert("至少选择一个用户角色");
		  return false;
		}
	
	var staffNo = $('[name="admin.staffNo"]').val();
	if(staffNo == "") {
		alert('员工编号不能为空！');
		return false;
	}
	if($('[name="admin.ostaffNo"]').val() != staffNo){
		$.post(
			"admin_isExists.do",
			{ "admin.staffNo" : staffNo, "admin.username" : "", "admin.id" : $('[name="admin.id"]').val() },
			function(data){
				if(data.staffNo != ""){
					alert(data.staffNo); 
				    return false;
				}
				else{ $("#myForm").submit(); }
			},
			"json"
		);
	}
	else{
		//alert('edit');
		$("#myForm").submit();
	}
}
    $(function() {
        // Add by Bruce.Zhan on 2014/08/20 截取输入的最大值范围
        /************************Start*************************************/

        $("input[type='text']").maxlen();

        /************************End*************************************/
        
         /*收费站、收费广场 级联查询*/
		/* $('[name="admin.stationNo"]').change(function(){ 
			//alert($(this).children('option:selected').val()); 
			var stationNo = $(this).children('option:selected').val();//这就是selected的值 
						
			$.post(
				"admin_getSquareListByAsyn.do",
				{ "stationNo" : stationNo },
				function(data){
					 var squareSelect = $('[name="admin.squareNo"]');
					 squareSelect.empty();//清空选项
					
					 var options = '<option value="-1">选择收费广场</option>';
				     $.each(data,function(n,value) {   
				 		options += "<option value='" + value[0] + "'>" + value[4] + "</option>";
		  			});
  					squareSelect.append(options);
				},
				"json"
			);
			
		});	 */
        
		//密码禁用粘贴
        $("[type=password]").on("paste",function(){
        	return false;
        });
    });
</script>
</head>
<body>
	<form id="myForm" method="post" action="admin_update.do">
		<ul class="breadcrumb">
			<li><i class="icon-home icon-2x"></i></li>
			<li>当前位置：${currentPosition}&nbsp;>&nbsp;修改系统管理员</li>
		</ul>
		<div class="widget widget-edit">
			<div class="widget-content">
				<table class="pn-ftable table-bordered" border="0" cellpadding="10">
					<tbody>
						<tr>
							<th>登&nbsp;&nbsp;录&nbsp;&nbsp;名</th>
							<td class="pn-fcontent"><input type="text"
								name="admin.username" value="${admin.username}"
								readonly="readonly"  maxlength="20" /></td>
							<td class="pn-info"></td>
						</tr>
						<tr>
							<th>真实姓名</th>
							<td class="pn-fcontent"><input type="text" name="admin.name"
								value="${admin.name}" readonly="readonly"  maxlength="80" /></td>
							<td class="pn-info"></td>
						</tr>
						<tr>
							<th><span class="point">*</span>员工编号</th>
							<td class="pn-fcontent"><input type="text"
								name="admin.staffNo" value="${admin.staffNo}"  maxlength="8" /> 
								<input type="hidden"
									name="ostaffNo" value="${admin.staffNo}" /></td>
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
								id="password2" equalTo="#password"  maxlength="32"  onkeyup="this.value=this.value.replace(/^ +| +$/g,'')"/></td>
							<td class="pn-info"></td>
						</tr>
						<tr>
							<th>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</th>
							<td class="pn-fcontent"><label class="inline"> <input
									type="radio" name="admin.sex" value="男" checked /><i
									class="icon-male"></i></label> <label class="inline"> <input
									type="radio" name="admin.sex" value="女"
									<c:if test="${admin.sex=='女'}">checked</c:if> /><i
									class="icon-female"></i></label></td>
							<td class="pn-info"></td>
						</tr>
						<tr>
							<th>邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱</th>
							<td class="pn-fcontent"><input type="text"
								name="admin.email" value="${admin.email}" class="email"  maxlength="50" /></td>
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
							<td class="pn-fcontent"><input type="text"
								name="admin.userId" value="${admin.userId}"/></td>
							<td class="pn-info"></td>
						</tr>
						<tr>
							<th>所属机构</th>
							<td class="pn-fcontent">
								<select name="admin.org.id">
										<c:forEach items="${orgList}" var="item">
											<option value="${item.id}" level="${item.orgLevel}" 
												<c:if test="${admin.org.id eq item.id}">selected</c:if>
											>${fn:substring("││││││││││",0,item.orgLevel-1)}├${item.orgName}</option>
										</c:forEach></select>
							</td>
						</tr>
						<%-- <tr>
							<th><span class="point">*</span>收费站</th>
							<td class="pn-fcontent">
								<select name="admin.stationNo">
									<option value="-1">选择收费站</option> 
									<c:forEach items="${stations}" var="sta">
        								<option value="${sta.stationNo }" <c:if test="${sta.stationNo == admin.stationNo}"><c:out value="selected"/></c:if>>  
            								${sta.stationName}  
        								</option>  
    								</c:forEach>
								</select>
							</td>
							<td class="pn-info"></td>
						</tr>
						<tr>
							<th><span class="point">*</span>收费广场</th>
							<td class="pn-fcontent">
								<select name="admin.squareNo">
									<option value="-1">选择收费广场</option> 
									<c:forEach items="${squares}" var="squ">
        								<option value="${squ[0] }" <c:if test="${squ[0] == admin.squareNo}"><c:out value="selected"/></c:if>>  
            								${squ[4]}  
        								</option>  
    								</c:forEach>
								</select>
							</td>
							<td class="pn-info"></td>
						</tr> --%>
						<tr>
							<th>用户角色</th>
							<td class="pn-fcontent"  colspan="2" style="border-right:1px solid #ddd;">
								<div style="width:100%; overflow:auto;">
								<c:forEach items="${roleList}" var="item">
									<div class="pull-left" style="width:20% !important;" >
								<input style="width: 15px; border: 0;" type="checkbox"
														name="roles" value="${item.id}"
														<c:forEach items="${admin.roles}" var="role">
					                                        <c:if test="${role.id==item.id}">checked</c:if>
					                                    </c:forEach> />
				                                    ${item.name}
	                        	</div>
								</c:forEach>
								</div>
	                        </td>
	                        
						</tr>
					</tbody>
				</table>
				
			</div>
			<!-- /widget-content -->
			<div class="widget-bottom">
					<input type="hidden" name="admin.id" value="${admin.id}" /> 
					<input type="hidden" name="admin.createTime" value="${admin.createTime}" />
					<input type="hidden" name="admin.lastIp" value="${admin.lastIp}" />
					<input type="hidden" name="admin.lastTime" value="${admin.lastTime}" /> 
					<input type="hidden" name="admin.valid" value="${admin.valid}" /> 
					<a onclick="check()" href="javascript:void(0);" class="btn btn-primary pull-right">保 存</a>
					<a href="javascript:history.go(-1);" class="btn btn-danger pull-right">返 回</a> 
				</div>
		</div>

	</form>
</body>
</html>