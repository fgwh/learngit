<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div id="sidebar">
    <ul>
    	 <li class="unmenu active"><a href="admin/jobTask_list.do" target="mainIframe"><i class="icon icon-user"></i><span>我的工作台</span></a></li>
         <li class="submenu">
         	<a><i class="icon icon-dashboard"></i>任务管理平台<span class="chevron"><i class="icon-chevron-down"></i></span></a>
            <ul>
               	<li>
               		<a href="admin/jobTask_list.do" target="mainIframe">任务管理</a>
               	</li>
               	<li><a href="admin/jobTask_logcheck.do" target="mainIframe">日志查询</a></li>
            </ul>
         </li>
<%--         <li class="submenu">--%>
<%--        	<a ><i class="icon icon-cog"></i>系统设置<span class="chevron"><i class="icon-chevron-down"></i></span></a>--%>
<%--            <ul>--%>
<%--            		<li><a href="#" target="mainIframe">用户管理</a></li>--%>
<%--            		<li><a href="#" target="mainIframe">角色管理</a></li>--%>
<%--                	<li><a href="#" target="mainIframe">模块管理</a></li>--%>
<%--            </ul>--%>
<%--         </li>--%>
       
    </ul>
</div>
<div style="height: 832px;width: 8px;position: relative;display: block;float:left;" id="center_control">
	<img src="${basePath}/images/yc_icon.gif" id="center_control_img" style="position:relative;top:200px"/>
</div>
<script>
	var isHide = false;
	var sidebar_width = 200; 
	
	$("#center_control").bind("click", function() {
		
		if (!isHide) {
			$("#sidebar").hide();//隐藏菜单栏
			isHide = true;
			$("#mainIframe").css("left","0px");
			try{
				$("#mainIframe").css("width", document.body.clientWidth - 8 + "px");
			}catch(e)
			{
				
			}
			$("#center_control_img").attr("src", "${basePath}/images/xs_icon.gif");
		} else {
			$("#sidebar").show();
			isHide = false;//显示菜单栏
			$("#mainIframe").css("left","200px");
			try{
				$("#mainIframe").css("width", document.body.clientWidth - sidebar_width + "px");
			}catch(e)
			{
				
			}
			$("#center_control_img").attr("src", "${basePath}/images/yc_icon.gif");
		}
	  
	});
	
</script>