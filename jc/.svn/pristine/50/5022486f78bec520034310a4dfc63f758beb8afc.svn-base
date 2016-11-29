<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<link rel="stylesheet" type="text/css"
	href="${basePath}/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="${basePath}/css/plugins/font-awesome/css/font-awesome.min.css" />
<!--[if IE 7]>
  <link rel="stylesheet" href="${basePath}/css/plugins/font-awesome/css/font-awesome-ie7.min.css" />
<![endif]-->
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link id="css_main" rel="stylesheet" type="text/css"
	href="${basePath}/theme/${session.theme}/main.css" />
<script language="javascript" src="${basePath}/js/jquery.min.js"></script>
</head>
<body>
<ul class="breadcrumb">
			<li><i class="icon-home icon-2x"></i></li>
			<li>操作失败</li>
		</ul>
	<div class="widget widget-edit">
		<div class="widget-content">
			<div class="error_ex">
				<h3>非法操作：</h3>
				<h5 style="color: red"> 提交的信息包含非法字符：<%=request.getAttribute("dangerousInputValues") %> </h5>
			</div>
		</div>
	</div>
</body>
</html>