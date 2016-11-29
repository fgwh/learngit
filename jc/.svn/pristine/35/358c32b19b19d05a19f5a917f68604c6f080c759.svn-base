<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/bootstrap.min.css" />
<link rel="stylesheet" href="${basePath}/css/plugins/font-awesome/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/zTreeStyle/zTreeStyle.css" />

<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link id="css_main" rel="stylesheet" type="text/css" href="${basePath}/theme/${session.theme}/main.css" />

<style type="text/css">
.dropdown-menu ul {
	width: 507px;
	height: 252px;
	padding: 3px;
	overflow-x: hidden;
	overflow-y: auto;
}

.dropdown-menu ul li {
	padding: 3px;
	float: left;
	width: 20px;
	text-align: center;
	line-height: 20px;
}

.dropdown-menu ul li:hover {
	cursor: pointer;
	background: #f0f0f0;
}
.col-xs-4 {
    +width: 27%; /* ie7 */ 
    _width: 27%; /* ie6 */ 
}
</style>

<script language="javascript" src="${basePath}/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${basePath}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.ztree.all-3.5.min.js"></script>

<script language="javascript">
//tree begin ****************************************************
var message = "${message}";
if (message!=null && message !=""){
	alert(message);
	/* var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	treeObj.reAsyncChildNodes(null, "refresh");	  */
}
var selectOrgId ="${org.id}";
var setting = {
		data: {
			key: {
				title:"orgName",//鼠标移到上面的tip
				name:"orgName"//名字
			},
			simpleData: {
				enable:true,
				idKey:"id",//指定json中id的值
				pIdKey:"parentId"//指定json中的父节点的ID
			}
		},
		async: {
			enable: true,
			url:"org_orgTreeForJson.do",
			autoParam:["id=org.id"]
		},
		callback: {
			beforeClick: beforeClick,
			onClick: onClick,
			onAsyncSuccess: onSuccess
		}
	};
function onSuccess(event, treeId, treeNode, msg){
	$.fn.zTree.getZTreeObj(treeId).expandAll(false);
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	for (var i = 1; i < 3; i++) {
			var treeCode = treeObj.getNodesByParam("orgType", i.toString(),
					null);
			console.log(treeCode);
			if (treeCode.length == 1) {
				treeObj.expandNode(treeCode[0]);
				
			}else if(treeCode.length > 1){
				for (var i = 0; i < treeCode.length; i++) {
					treeObj.expandNode(treeCode[i]);
				}
			}

		}
	setSelectedByOrgId();
};

function beforeClick(treeId, treeNode, clickFlag) {
	return (treeNode.click != false);
}

function onClick(event, treeId, treeNode, clickFlag) {
	var iframe = ${"rightFrame"};
	selectOrgId = treeNode.id;
	iframe.location.href = "${basePath}/admin/org_edit.do?org.id="+treeNode.id;	
}		
function getSelectNodeId(){
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	var nodes = treeObj.getSelectedNodes();
	if (nodes[0]){
		return nodes[0].id;
	} else {
		return "";
	}
}

function getSelectNodeTreeCode(){
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	var nodes = treeObj.getSelectedNodes();
	if (nodes[0]){
		return nodes[0].treeCode;
	} else {
		return "";
	}
}

function getSelectNodeOrgType(){
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	var nodes = treeObj.getSelectedNodes();
	if (nodes[0]){
		return nodes[0].orgType;
	} else {
		return "";
	}
}

function addSameLevel(){
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	var nodes = treeObj.getSelectedNodes();
	//var selectOrgId = getSelectNodeId();
	if (nodes[0].id==""){
		alert("请选择一个机构");
		return;
	}
	//var t = getSelectNodeOrgType();
	if(nodes[0].orgType =="1"){
		alert("该节点不能添加同级机构");
		return;
	}
	//var selectOrgTreeCode = getSelectNodeTreeCode();
	 
	if(nodes[0].treeCode.substr(nodes[0].treeCode.length-4) == "9999"){
		alert("不能继续添加同级机构");
		return;
	}
	var params={'org.id':nodes[0].id};
	var url="${basePath}/admin/org_addSameLevel.do";
	$.post(url,params,function(jsonTemp){
		var mapA = jsonTemp.map;
		var addSameLevel=mapA.addSameLevel;
		if(addSameLevel=="yes"){
			var org = mapA.org;
				var treeCode = treeObj.getNodesByParam("id", org.parentId, null);
				treeObj.addNodes(treeCode[0], org);
			alert("添加成功");
		}else{
			alert("添加失败");
		}
		
	},"json");
	//location.href = "${basePath}/admin/org_addSameLevel.do?org.id="+selectOrgId;
}

function addLowerLevel(){
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	var nodes = treeObj.getSelectedNodes();
	//var selectOrgId = getSelectNodeId();
	if (nodes[0].id==""){
		alert("请选择一个机构");
		return;
	}
	  
	var selectOrgTreeCode = getSelectNodeTreeCode();
	if(nodes[0].treeCode.length==160){
		alert("不能继续添加下级机构");
		return;
	}
	 var params={'org.id':nodes[0].id};
		var url="${basePath}/admin/org_addLowerLevel.do";
		$.post(url,params,function(jsonTemp){
			var mapA = jsonTemp.map;
			var addLowerLevel=mapA.addLowerLevel;
			if(addLowerLevel=="yes"){
				var org = mapA.org;
					var treeCode = treeObj.getNodesByParam("id", org.parentId, null);
					treeObj.addNodes(treeCode[0], org);
				alert("添加成功");
			}else{
				alert("添加失败");
			}
			
		},"json");
	//location.href = "${basePath}/admin/org_addLowerLevel.do?org.id="+selectOrgId;
}

$(document).ready(function(){
	$.fn.zTree.init($("#treeDemo"), setting);
});

//重新加载树
function refreshTree(){
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	treeObj.reAsyncChildNodes(null, "refresh");	
}

function setSelectedByOrgId(){
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");	
	var nodes = treeObj.getNodesByParam("id",selectOrgId,null);	
	if (nodes.length>0) {
		treeObj.selectNode(nodes[0]);
	} 
}

function del(){
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	var nodes = treeObj.getSelectedNodes();
	//var t = getSelectNodeOrgType();
	if(nodes[0].orgType =="1"){
		alert("该节点不能删除");
		return;
	}
	//var selectOrgTreeCode = getSelectNodeTreeCode();
	if (nodes[0].treeCode==""){
		alert("请选择一个节点");
		return;
	}
	var params={'org.treeCode':nodes[0].treeCode};
	var url="${basePath}/admin/org_isExistStaff.do";
	$.post(url,params,function(jsonTemp){
		var mapA = jsonTemp.map;
		var dealResult=mapA.result;
		if(dealResult=="NO"){
			alert("该节点下存在人员，不能删除。");
			return;
		}
		if (confirm("确定要删除该节点吗？")) {
			 var params={'org.treeCode':nodes[0].treeCode};
				var url="${basePath}/admin/org_delete.do";
				$.post(url,params,function(jsonTemp){
					var mapA = jsonTemp.map;
					var delResult=mapA.delResult;
					if(delResult=="yes"){
							treeObj.removeNode(nodes[0]);
							$("#rightFrame").contents().find('input').each(function(){
								$(this).val('');
							});
						alert("删除成功");
					}else{
						alert("删除失败");
					}
					
				},"json");
		//location.href = "${basePath}/admin/org_delete.do?org.treeCode="+selectOrgTreeCode;
		}
	},"json");
}

//tree end ******************************************************


function add() {
	if (check()){
		$.post(
				"org_checkExist.do",
				{"org.id" : get("org.id").value , "org.orgName" :  get("org.orgName").value , "org.orgType" :  get("org.orgType").value},
				function(data){
					if(data=="yes"){
						alert("有重复名字，请更改！");
						return;
					}
				},"json"
				);
        get("myForm").action = "org_save.do";
        $("#myForm").submit();
    }
}
function check() {
	if (get("org.name").value == "") {
		alert("机构名称不能为空");
		get("org.name").focus();
		return false;
	}
	
	return true;
}
function put(i) {
	$("#org_info").attr("param",i);
	get("org.id").value = dtree[i][0];
	get("org.parent.id").value = dtree[i][1];
	get("org.name").value = dtree[i][2];
	get("org.priority").value=dtree[i][6];
	var available = document.getElementsByName("org.available");
	for ( var j = 0; j < available.length; j++) {
		if (available[j].value == dtree[i][3]) {
			available[j].checked = true;
		}
	}
	get("org.level").value = dtree[i][4];
	
	var iframe = ${"rightFrame"};
	iframe.location.href = "${basePath}/admin/org_adminList.do?org.id="+dtree[i][0];
	setIFrameHeight();
}

function setIFrameHeight() {
	var mainheight = $("#rightFrame").contents().find("body").height();
	if (mainheight < 680) {
		mainheight = 680;
	}
	$("#rightFrame").height(mainheight);
}
function update(org) {
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	var nodes = treeObj.getSelectedNodes();
	//console.log("nodes="+nodes[0].id);	
	nodes[0].name=org.orgName;
	nodes[0].orgName=org.orgName;
	nodes[0].treeCode=org.treeCode;
	nodes[0].orgType=org.orgType;
	treeObj.updateNode(nodes[0]);
	
	/* if (get("org.id").value == "") {
		alert("请先选择要修改的信息");
		return;
	}
	if (!check())
		return; */
	
	/* get("myForm").action = "org_update.do";
	$("#myForm").submit(); */
}

</script>
</head>
<body>
<form id="myForm" method="post" action="role_update.do">
	<ul class="breadcrumb">
		<li><i class="icon-home icon-2x"></i></li>
		<li>当前位置：${currentPosition}</li>
	</ul>
    <div class="widget widget-edit">
		<div class="widget-content">
			<div class="widget-bottom btn-group ">
				<a href="javascript:del();" class="btn btn-danger ">删除</a>
				<a href="javascript:addLowerLevel();" class="btn btn-success">下级 </a>
				<a href="javascript:addSameLevel();" class="btn btn-success">同级</a>
				<input type="hidden" name="org.id" value="${org.id}" />
			</div>

			<table id="org_tree" class="pn-ftable table-bordered" border="0" cellpadding="10" param="0" style="height:700px;">
				<tbody>
				<tr>
					<td class="pn-fcontent" style="border-right-width: 1px;width:24%;">
						<div class="zTreeDemoBackground left" style="height: 700px;overflow: auto;">
							<ul id="treeDemo" class="ztree" style="position:relative; top:3%;"></ul>
						</div>
					</td>
					<td class="pn-fcontent" style="border-right-width: 1px;width:73%;">
						<div>
							<iframe id="rightFrame" name="rightFrame" frameborder="0" height="100%" onload="setIFrameHeight()" width="100%" scrolling="no" src="${basePath}/admin/org_edit.do?org.id=${org.id}"></iframe>
						</div>
					</td>
				</tr>
				</tbody>
			</table>
			</div>			
		</div>
	</form>
</body>
</html>