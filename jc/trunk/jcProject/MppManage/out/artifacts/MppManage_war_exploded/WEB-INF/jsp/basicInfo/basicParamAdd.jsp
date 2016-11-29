<%--
  新增基础参数信息页面
 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>basicParamAdd</title>
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
function limitLength(value, byteLength,attribute) {  
       var newvalue = value.replace(/[^\x00-\xff]/g, "**");  
       var length = newvalue.length;  
   
       //当填写的字节数小于设置的字节数  
      if (length * 1 <=byteLength * 1){  
            return;  
      }  
      var limitDate = newvalue.substr(0, byteLength);  
      var count = 0;  
      var limitvalue = "";  
     for (var i = 0; i < limitDate.length; i++) {  
             var flat = limitDate.substr(i, 1);  
            if (flat == "*") {  
                  count++;  
            }  
     }  
     var size = 0;  
     var istar = newvalue.substr(byteLength * 1 - 1, 1);//校验点是否为“×”  
    
    //if 基点是×; 判断在基点内有×为偶数还是奇数   
     if (count % 2 == 0) {  
              //当为偶数时  
            size = count / 2 + (byteLength * 1 - count);  
            limitvalue = value.substr(0, size);  
    } else {  
            //当为奇数时  
            size = (count - 1) / 2 + (byteLength * 1 - count);  
            limitvalue = value.substr(0, size);  
    }  
   document.getElementById(attribute).value = limitvalue;  
   return;  
}


function save() {
	if($.trim($('[name="basicParam.paramFlag"]').val())==""){
		alert("请输入参数大类");			
		return;
	}
	if($.trim($('[name="basicParam.paramValType"]').val())==""){
		alert("请输入参数值类型");			
		return;
	}
	if($.trim($('[name="basicParam.paramCode"]').val())==""){
		alert("请输入参数编码");			
		return;
	}
	var name = $.trim($('[name="basicParam.paramName"]').val());
	if(name ==""){
		alert("请输入参数名");			
		return;
	}
	var flag = false;
	$.ajax({
		   type: "POST",
		   url: "admin/basicParam_paramNameExsit.do",
		   data: {"basicParam.paramName":name},
		   async : false,
		   success: function(msg){
		      if(msg == "yes"){
		    	  flag = true;
		    	  alert("该参数名已经存在");
		      }
		   }
	});
	if(flag == true){
		return false;
	}
	if($.trim($('[name="basicParam.paramVal"]').val())==""){
		alert("请输入参数值");			
		return;
	}
  	$('#myForm').submit();
}
</script>
</head>
<body>
     <form id="myForm" name="myForm" method="post" action="basicParam_save.do">
         <ul class="breadcrumb">
			<li><i class="icon-home icon-2x"></i></li>
			<li>当前位置：${currentPosition}&nbsp;>&nbsp;新增基础参数信息</li>
		 </ul>
		 <div class="widget widget-edit">
			<div class="widget-content">
			     <table class="pn-ftable table-bordered" border="0" cellpadding="10">
			          <tbody>
			                 <tr>
			                         <th><span class="point">*</span>参数大类</th>
			                         <td class="pn-fcontent">
			                            <input type="text" name="basicParam.paramFlag" onkeyup="this.value=this.value.replace(/\D/g,'')"
			                            onafterpaste="this.value=this.value.replace(/\D/g,'')"  size="9" maxlength="9" />
			                         </td>			                         
			                 </tr>
			                 
			                 <tr>
			                         <th><span class="point">*</span>参数值类型</th>
			                         <td class="pn-fcontent">
			                         <input type="text" name="basicParam.paramValType" onkeyup="this.value=this.value.replace(/\D/g,'')"
			                         onafterpaste="this.value=this.value.replace(/\D/g,'')" size="9" maxlength="9" />
			                          </td>
			                 </tr>
			                 
			                 <tr>
			                         <th><span class="point">*</span>参数编码</th>
			                         <td class="pn-fcontent"><input type="text" name="basicParam.paramCode" id="paramCode" 
			                         onkeyup="limitLength(value,10,'paramCode');" placeholder="请输入参数编码"/></td>
			                 </tr>
			                 
			                 <tr>
			                         <th><span class="point">*</span>参数名</th>
			                         <td class="pn-fcontent"><input type="text" name="basicParam.paramName" id="paramName"
			                         onkeyup="limitLength(value,30,'paramName');"placeholder="请输入参数名" /></td>
			                 </tr>
			                 
			                 <tr>
			                         <th><span class="point">*</span>参数值<br/><font style="color: red;font-size: 12px">(必须与收费流水表中班次值相同，否则无法绑定流水)</font></th>
			                         <td class="pn-fcontent"><input type="text" name="basicParam.paramVal" id="paramVal" 
			                         onkeyup="limitLength(value,30,'paramVal');" placeholder="请输入参数值"/></td>
			                 </tr>
			                 
			                 <tr>
			                         <th><span class="point">*</span>状　态</th>
			                         <td class="pn-fcontent">
			                              <label for="enable">启用</label>
			                              <input type ="radio" name ="basicParam.status" value ="0" id="enable" checked/>	
			                              <label for="disable">停用</label>
			                              <input type ="radio" name ="basicParam.status" value ="-1" id="disable"/>	                             
									 </td>	
			                 </tr>			                 
			                
			                 <tr>
			                         <th>描     述 </th>
			                          <td class="pn-fcontent"><input type="text" name="basicParam.backupStr" id="backupStr" 
			                          onkeyup="limitLength(value,30,'backupStr');"/></td>
			                 </tr>			                
			          </tbody>
			     </table>
			     
			</div>
			<!-- /widget-content -->
			<div class="widget-bottom">
					<a href="javascript:void(0);" onclick="save();"  class="btn btn-primary pull-right">保 存</a>
					<a href="javascript:history.go(-1);" class="btn btn-danger pull-right">返 回</a> 
				 </div>
		</div>
     </form>
</body>
</html>