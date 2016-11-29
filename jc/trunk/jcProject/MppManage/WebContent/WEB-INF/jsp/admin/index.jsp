<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/bootstrap.min.css" />
<link rel="stylesheet" href="${basePath}/css/plugins/font-awesome/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/theme/${session.theme}/main.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/zTreeStyle/zTreeStyle.css" />
<script language="javascript" src="${basePath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${basePath}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="${basePath}/js/util.js"></script>
<style type="text/css">
.col-xs-4 {
    +width: 20%; /* ie7 */ 
    _width: 20%; /* ie6 */ 
}

</style>

<script language="javascript">
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
	iframe.location.href = "${basePath}/admin/admin_list.do?admin.org.id="+treeNode.id;	
	setIFrameHeight();
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
	var nodes = treeObj.getNodesByParam("id",selectOrgId);
	if (nodes.length>0) {
		treeObj.selectNode(nodes[0]);
	}
}
function setIFrameHeight() {
	var mainheight = $("#rightFrame1").contents().find("body").height();
	//alert($("#rightFrame1").contents().find("body").height());
	if (mainheight < 944) {
		mainheight = 944;
	}

	$("#rightFrame1").height(mainheight);
	parent.document.getElementById('rightFrame').style.height = mainheight+100 + 'px';
}
</script>
</head>
<body>
<form id="myForm" method="post">
	<ul class="breadcrumb">
		<li><i class="icon-home icon-2x"></i></li>
		<li>当前位置：${currentPosition}</li>
	</ul>
    <div class="widget widget-edit"  >
		<div class="widget-content" >
			<div>
				<table id="org_tree" class="pn-ftable table-bordered" border="0" cellpadding="10" param="0">
					<tbody>
						<tr>
							<td class="pn-fcontent" style="border-right-width: 1px;width:24%;">
								<div class="zTreeDemoBackground left" style="height: 944px;overflow: auto;">
									<ul id="treeDemo" class="ztree" style="position:relative; top:3%;"></ul>
								</div>
							</td>
							<td class="pn-fcontent" style="border-right-width: 1px;width:75%;">
								<div>					
									<iframe id="rightFrame1" name="rightFrame" frameborder="0"onLoad="setIFrameHeight()" width="100%" src="${basePath}/admin/admin_list.do?admin.org.id=${admin.org.id}"></iframe>
								</div>
							</td>
						</tr>	
												
					</tbody>
				</table>
			</div>
			
			</div>			
		</div>
	</form>
</body>
</html>