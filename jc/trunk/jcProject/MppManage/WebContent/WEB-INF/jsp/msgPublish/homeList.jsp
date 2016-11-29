<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta charset="UTF-8" />
<title>查询页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css"
	href="${basePath}/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="${basePath}/css/plugins/font-awesome/css/font-awesome.min.css" />
<!--[if IE 7]>
    <link rel="stylesheet" href="${basePath}/css/plugins/font-awesome/css/font-awesome-ie7.min.css" />
    <![endif]-->
<link rel="stylesheet" type="text/css" href="${basePath}/css/goods.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link rel="stylesheet" type="text/css"
	href="${basePath}/theme/transparent/main.css" />
<script language="javascript" src="${basePath}/js/jquery.min.js"></script>
<script type="text/javascript"
	src="${basePath}/js/quartz/plugins/My97DatePicker/WdatePicker.js"></script>
<script src="${basePath}/js/bootstrap.min.js"></script>
<style>
	table tr td{color:#000;}
</style>
</head>
<body style="min-height:600px;">
	<form name="myForm" id="myForm" action="" method="post">
		<div id="" style="display: none;">${session.operator.name}</div>
		<ul class="breadcrumb">
			<li><i class="icon-home icon-2x"></i></li>
			<li>当前位置：首页</li>
		</ul>
		<div class="widget widget-table">
			<div class="widget-header">
				<i class="icon-th-list"></i>
				<h5>数据列表</h5>
			</div>
			<div class="widget-content widget-list widget-scroll"style="min-height:200px;">
				<table class="table table-striped table-bordered"style="table-layout:fixed;">
					<thead>
						<tr>
							<th style="width:170px;">发布时间</th>
							<th style="width:220px;">主题</th>
							<th>通告内容</th>
							<th style="width:170px;">发布人</th>
							<th style="width:160px;">操作</th>
						</tr>
					</thead>
					<tbody id="databody">
						<c:forEach items="${list}" var="item" varStatus="status">
							<tr>
								<td>${item.publishTime}</td>
								<td title="${item.theme}"><a style="color:#000;"
									href="javascript:showMsg('${item.theme}','${item.content}')">${item.theme}</a></td>
								<td title="${item.content}">${item.content}</td>
								<td title="${item.publishMan}">${item.publishMan}</td>
								<td><a class="btn btn-info"
									href="javascript:showMsg('${item.theme}','${item.content}')">查看详情</a> 
									<shiro:hasPermission name="msgPublishAction:delMsg">
										<a class="btn btn-important" href="javascript:del('${item.id}')">删除</a>
									</shiro:hasPermission>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</form>
	<div class="modal fade" id="myModal5" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 960px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						主题：<font id="showThemt" style="font-weight: bold;"></font>
					</h4>
				</div>
				<div class="modal-body">  
					<div id="showContent">
						 
					</div>
				</div>
				<div class="modal-footer" id="footer5"> 
					<button type="button" class="btn btn-danger" data-dismiss="modal">返回</button>
				</div>
			</div>
		</div>
	</div>
<script>
function del(id) {
	if (confirm("确认要删除吗？")) {
		window.location.href = "msgPublish_homeDelete.do?msgPublish.id="+ id;
	}
}
function showMsg(theme,content){
	$("#showThemt").text(theme);
	$("#showContent").html(content);
	$("#myModal5").modal('show');
}
</script>
</body>
</html>