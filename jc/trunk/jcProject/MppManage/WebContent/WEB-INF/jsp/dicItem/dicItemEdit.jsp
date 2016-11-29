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
function update() {
	if($.trim($('[name="dicItem.type"]').val())==""){
		alert("请输入字典类别");
		return;
	}
	if($.trim($('[name="dicItem.name"]').val())==""){
		alert("请输入字典名字");			
		return;
	}
	if($.trim($('[name="dicItem.value"]').val())==""){
		alert("请输入字典的值");			
		return;
	}
	if($.trim($('[name="dicItem.innerOrder"]').val())==""){
		alert("请输入字典的内部序列号");			
		return;
	}
	$.post(
			"dicItem_checkExist.do",
			{"dicItem.type" : $('[name="dicItem.type"]').val() , "dicItem.name" :  $('[name="dicItem.name"]').val() , "dicItem.id" : $('[name="dicItem.id"]').val() , "dicItem.value" :  $('[name="dicItem.value"]').val()},
			function(data){
				if(data=="one"){
					alert("有重复名字，请更改！");
					
				}else if(data=="two"){
					
					alert("有重复值，请更改！");
				}else{
					$("#myForm").submit();
				}
			},"json"
			);
	
}
if ('${message}' != '') {
	alert('${message}');
}
</script>
</head>
<body>
     <form id="myForm" name="myForm" method="post" action="dicItem_update.do">
         <ul class="breadcrumb">
			<li><i class="icon-home icon-2x"></i></li>
			<li>当前位置：${currentPosition}&nbsp;>&nbsp;修改字典信息</li>
		 </ul>
		 <div class="widget widget-edit">
			<div class="widget-content">
			     <table class="pn-ftable table-bordered" border="0" cellpadding="10">
			          <tbody>
			                 <tr>
									   <th> <span class="point">*</span>类别：</th>
			                         <td class="pn-fcontent"><input type="text" name="dicItem.type" id="type" value="${dicItem.type }"
			                         placeholder="请输入字典类别" maxlength="25"/></td>	                         
			                 </tr>

							 <tr>
									<th><span class="point">*</span>名字：</th>
			                        <td class="pn-fcontent"><input type="text" name="dicItem.name" id="name" value="${dicItem.name }"
			                          placeholder="请输入字典名字" maxlength="50"/></td>	
							 </tr>
			                 
			                 <tr>
									<th><span class="point">*</span>值  ：</th>
			                        <td class="pn-fcontent"><input type="text" name="dicItem.value" id="value" value="${dicItem.value }"
			                         placeholder="请输入字典值" maxlength="50"/></td>	
			                 </tr>

							<tr>
									<th>内部分类  <span class="point"></span>：</th>
			                         <td class="pn-fcontent"><input type="text" name="dicItem.innerType" id="innerType" value="${dicItem.innerType }"
			                         placeholder="请输入内部分类" maxlength="25"/></td>	
			                </tr>

                           <tr>
									<th><span class="point">*</span>内部序列号  ：</th>
			                         <td class="pn-fcontent"><input type="text" name="dicItem.innerOrder" id="innerOrder" value="${dicItem.innerOrder }"
			                         onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" placeholder="请输入字典内部顺序号" maxlength="9"/></td>	
			                </tr>

			                 <tr>
			                         <th>备注 ：</th>
			                          <td class="pn-fcontent">
										  <input type="hidden" name="dicItem.timeDiff" value="0"/>
									  	  <textarea id="remark" name="dicItem.note" rows="4" cols="20" maxlength="120" >${dicItem.note }</textarea>
									  </td>

									
			                 </tr>			                
			          </tbody>
			     </table>
			     <div class="widget-bottom">
			        <input id="dicItem.id" type ="hidden" name ="dicItem.id" value="${dicItem.id}"/>
					<a href="javascript:void(0);" onclick="update();"  class="btn btn-primary pull-right">保 存</a>
					<a href="dicItem_list.do" class="btn btn-danger pull-right">返 回</a> 
				 </div>
			</div>
			<!-- /widget-content -->
		</div>
     </form>
</body>
</html>