<%--
  修改工班表信息页面
 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>basicParamEdit</title>
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
<script type="text/javascript" src="${basePath}/js/quartz/plugins/My97DatePicker/WdatePicker.js"></script>
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

function update() {
	if($.trim($('[name="squad.workName"]').val())==""){
		alert("请输入工班名称");			
		return;
	}
	if($.trim($('[name="squad.startTime"]').val())==""){
		alert("请输入开始时间");			
		return;
	}
	if($.trim($('[name="squad.endTime"]').val())==""){
		alert("请输入结束时间");			
		return;
	}
  	$('#myForm').submit();
}

$(function(){
	$('#workNo').on('blur',function(){
		if($.trim($('[name="squad.workNo"]').val())==""){
			alert("请输入工班编号");
		}else {
			$.post(
				"squad_isIdExists.do",
				{ "squad.workNo" : $('#workNo').val(),"squad.id":$('#id').val() },
				function(data){
					if(data != ""){
					    $('#workNo').val('');
						alert(data); 
					}
				},
				"json"
			);
		}
	});
	$('#workName').on('blur',function(){
		if($.trim($('[name="squad.workName"]').val())==""){
			alert("请输入工班名称");
		}else {
			$.post(
				"squad_isExists.do",
				{ "squad.workName" : $('#workName').val(),"squad.workNo":$('#workNo').val() },
				function(data){
					if(data != ""){
					    $('#workName').val('');
						alert(data); 
					}
				},
				"json"
			);
		}
	});
});
</script>
</head>
<body>
     <form id="myForm" name="myForm" method="post" action="squad_update.do">
         <ul class="breadcrumb">
			<li><i class="icon-home icon-2x"></i></li>
			<li>当前位置：${currentPosition}&nbsp;>&nbsp;修改工班信息</li>
		 </ul>
		 <div class="widget widget-edit">
			<div class="widget-content">
			     <table class="pn-ftable table-bordered" border="0" cellpadding="10">
			          <tbody>
			          		 <tr>	
									<th><span class="point">*</span>工班编号</th>
			                         <td class="pn-fcontent"><input type="text" name="squad.workNo" id="workNo" value="${squad.workNo }"
			                         onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" placeholder="请输入工班编号" maxlength="20"/></td>	                         
			                 </tr>
						 
							 <tr>
									<th><span class="point">*</span>工班名称</th>
			                         <td class="pn-fcontent"><input type="text" value="${squad.workName }" name="squad.workName" id="workName"
			                         onkeyup="limitLength(value,20,'workName');" placeholder="请输入工班名称" maxlength="20"/></td>	                         
			                 </tr>

							 <tr>
									<th><span class="point">*</span>工班类型</th>
			                        <td class="pn-fcontent">
										<select name="squad.workType" >
									    	<c:forEach items="${squadTypeList}" var="item">
										    	<option value="${item.value }" <c:if test="${squad.workType==item.value }">selected</c:if> >${item.name }</option>
										    </c:forEach>
								        </select>
									</td>
							 </tr>
			                 
			                 <tr>
									<th><span class="point">*</span>开始时间</th>
			                        <td class="pn-fcontent">
			                        <input type="text" value="${squad.startTime }" name="squad.startTime" onkeyup="this.value=this.value.replace(/\D/g,'')" style="background-color: white;"
			                         onafterpaste="this.value=this.value.replace(/\D/g,'')" placeholder="请选择开始时间" onClick="javascript:WdatePicker({dateFmt: 'HH:mm:ss',skin:'whyGreen'})" readonly="readonly"/>
			                        </td>
			                 </tr>

							<tr>
									<th><span class="point">*</span>结束时间</th>
			                        <td class="pn-fcontent">
			                        <input type="text" value="${squad.endTime }" name="squad.endTime" onkeyup="this.value=this.value.replace(/\D/g,'')" style="background-color: white;"
			                         onafterpaste="this.value=this.value.replace(/\D/g,'')" placeholder="请选择结束时间" onClick="javascript:WdatePicker({dateFmt: 'HH:mm:ss',skin:'whyGreen'})" readonly="readonly"/>
			                        </td>
			                </tr>

							<!-- 
			                 <tr>
			                         <th>日期偏移  <span class="point">*</span>：</th>
			                         <td class="pn-fcontent">
			                              <label for="enable">不偏移</label>
			                              <input type ="radio" name ="squad.timeDiff" value ="0" id="enable" <c:if test="${squad.timeDiff=='0'}">checked</c:if>/>	
			                              <label for="disable">&nbsp;往前一天</label>
			                              <input type ="radio" name ="squad.timeDiff" value ="1" id="disable" <c:if test="${squad.timeDiff=='1'}">checked</c:if>/>	  
										  <label for="disable">&nbsp;往后一天</label>
			                              <input type ="radio" name ="squad.timeDiff" value ="-1" id="disable" <c:if test="${squad.timeDiff=='-1'}">checked</c:if>/>	                           
									 </td>	
			                 </tr>	
			                  -->		                 
			                
			                 <tr>
			                         <th>描述</th>
			                          <td class="pn-fcontent">
										  <input type="hidden" name="squad.timeDiff" value="0"/>
									  	  <textarea id="remark" name="squad.remark" rows="3" cols="50" onkeyup="limitLength(value,300,'remark');">${squad.remark }</textarea>
									  </td>

									
			                 </tr>				                
			          </tbody>
			     </table>
			     
			</div>
			<!-- /widget-content -->
			<div class="widget-bottom">
			        
			        <input id="id" type="hidden" name="squad.id" value="${squad.id }" />
					<input type ="hidden" name ="squad.squadStatus" value="${squad.squadStatus}"/>
					<a href="javascript:history.go(-1);" class="btn btn-danger pull-right">返 回</a> 
					<a href="javascript:void(0);" onclick="update();"  class="btn btn-primary pull-right">保 存</a>
				 </div>
		</div>
     </form>
</body>
</html>