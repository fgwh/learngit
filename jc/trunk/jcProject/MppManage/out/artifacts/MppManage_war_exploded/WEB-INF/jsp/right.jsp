<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
<link id="css_main" rel="stylesheet" type="text/css" href="${basePath}/theme/${session.theme}/main.css" />
<style type="text/css">
h3 {
	margin-bottom:20px;
	text-align: center;
}

.page-content-wrapper {
	width:75%;
	margin:0 auto 35px;
	height: 50px;
	background: #f2f2f2;
	border-radius: 10px;
	text-align:center;
	color:#626262;
	line-height:50px;
	border:1px solid #cacaca;
}
area{ cursor:pointer; }
.allRight{
	height: 300px; 
	width: 100%;
	overflow: hidden;
	border: 1px solid #DADADA;
	text-align: center;
	vertical-align: middle; 
	float:left;
}
.partRight{
	width:50%; 
	height:100%; 
	border: 1px solid #DADADA; 
	text-align: center;
	vertical-align: middle; 
	float:left;
}
.yearpartRight{
	width:50%; 
	height:100%; 
	border: 1px solid #DADADA; 
	text-align: center;
	vertical-align: middle; 
	float:left;
}

.partRightOne{
	border-right: 1px solid #DADADA;
}


.selectRoadMathClass{
	float:right;
	margin-right:5px;
}
.selectStationClass{
	float:right;
	margin-right:5px;
}
.selectRoadMoneyClass{
	float:right;
	margin-right:5px;
}
.selectStationMoneyClass{
	float:right;
	margin-right:5px;
}

.topSpace{
	padding-top:20px;
}

.chartContent{
	height:270px; 
	width:100%;
}
.personalRight{
	height: 450px; 
	width: 50%;
	overflow: hidden;
	border: 1px solid #DADADA;
	text-align: center;	
	vertical-align: middle; 
	float:left;
}
</style>
<script language="javascript" src="${basePath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${basePath}/js/communicate.js"></script>
<script language="javascript" src="${basePath}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${basePath}/js/echarts.js"></script>
<script type="text/javascript" src="${basePath}/js/charts.js"></script>
<script type="text/javascript" src="${basePath}/js/highcharts.js"></script>
<script type="text/javascript" src="${basePath}/js/line.js"></script>
<script type="text/javascript" src="${basePath}/js/bar.js"></script>
<script type="text/javascript" src="${basePath}/js/pie.js"></script>
<script type="text/javascript" src="${basePath}/js/util.js"></script>
 
<!--[if lt IE 10]>
<script type="text/javascript" src="${basePath}/js/pie.js"></script>
<![endif]-->

<script language="javascript">
	function areaClick(hrefName){
		$("#nav li .sub-menu li a[target='rightFrame'] font", window.parent.document).each(function(){
			if($(this).html() == hrefName){
				var ul = $(this).parents(".sub-menu");
				var a = $(this).parent();
				a.addClass("active");
				ul.show().parent().addClass("active open");
				location.href = a.attr("href");
			}
		});
	}

</script>
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
  <script src="${basePath}/js/html5shiv.min.js"></script>
  <script src="${basePath}/js/respond.min.js"></script>
<![endif]-->
</head>
<body>
	<ul class="breadcrumb">
		<li><i class="icon-home icon-2x"></i></li>
		<li>当前位置：首页</li>
	</ul>
	<form name="myForm" id="myForm" action="main_right.do" method="post">
	<div class="widget">
		<div class="widget-header">
				<h5>&nbsp;&nbsp;</h5>
		</div>
		<!-- 950px -->
		<div class="widget-content" style="width: 100%;" id="mainContent">
				   
		</div>
			
		<div class="widget-content" style="text-align:center;">
			
			<div class="widget-bottom">
				&nbsp;<span class="pull-right">技术支持：广州华工信息软件有限公司</span>
			</div>
		</div>
	</div>
	</form>
  
</body>
</html>