<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
<script type="text/javascript">
    /*  动态改变iframe高度    */
    function resize(){
    	$(window.parent.document).find("IFRAME").height(document.body.scrollHeight);
	}
</script>
</head>
<body>
<ul class="breadcrumb">
			<li><i class="icon-home icon-2x"></i></li>
			<li>操作失败</li>
		</ul>
	<div class="widget widget-edit">
		<div class="widget-content">
			<div class="error_ex">
				<h3>非常抱歉，您的操作失败了！原因：</h3>
				<h4> ${message}
				<c:if test="${empty message}">
                   系统内部发生错误，请联系管理员！                                                   
                </c:if>
                <c:if test='${fn:indexOf(exception,"org.springframework.dao.DataIntegrityViolationException")>-1}'>
					该信息系统正在使用中，无法删除
				</c:if>
				</h4>
				<p>您可以访问其他页面或者查看详细信息</p>
				<a class="btn btn-info btn-big" href="javascript:void(0);"
					onclick="$('.error-text').slideToggle('fast',resize);">查看错误信息</a> <br />

				<div class="error_info span12">
					<div class="error-text">
						${exception}
					</div>
				</div>
				<br /> <br />
				
			</div>
		</div>
	</div>
</body>
</html>