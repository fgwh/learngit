<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div id="top">
  		<img class="f_l" src="${basePath}/images/logo.png" width="330" height="65" />
        <ul class="f_r">
        	<li class="nav-tab">
        	<div id="login_user">
        		<c:if test="${not empty sessionScope.operator.name and sessionScope.operator.name ne ''}">
        	    	登录用户 【${sessionScope.operator.name}】
        	    </c:if>
        	</div>
        	<!-- websocket测试，暂时屏蔽
        	<div><input onclick="startWebSocket();" value="连接"/></div>
        	<div id="messageBox"></div>
        	 -->
        	</li>
            <li class="dropdown nav-tab" id="menu-info">
            	<a id="info-btn" href="#" role="button" class="dropdown-toggle" data-toggle="dropdown">&nbsp;<i class="icon-info-sign"></i>&nbsp;</a>
            	<ul class="dropdown-menu" role="menu" aria-labelledby="info-btn">
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="#">更新日期：2013-11-11</a></li>
                    <li role="presentation" class="divider"></li>
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="#">版本号：1.11</a></li>
                </ul>
            </li>
            <li class="nav-tab"><a id="signout-btn" href="helpCenter_list.do" target="mainIframe" title="帮助中心" onclick="javascript:helpCenter();">&nbsp;<i class="icon-question-sign"></i>&nbsp;</a></li>
            <li class="nav-tab"><a id="signout-btn" href="#" title="退出登录" onclick="javascript:logout();">&nbsp;<i class="icon-signout"></i>&nbsp;</a></li>
        </ul>
	</div><!-- top end-->
<form action="${basePath}/j_spring_security_logout" method="post" 
	 id="logoutform" name="logoutform" style="display: none;">
</form>
<script>
	function logout(){
		document.logoutform.submit();
	}
	function helpCenter(){
		$("#helpCenter").addClass("active");
		var a = $("#helpCenter").parents(".submenu").find("a");
		var submenu = a.siblings('ul');
		var li = $("#helpCenter").parents(".submenu");
		var chevron = a.find(".chevron i");
		var submenus = $('#sidebar li.submenu ul');
		var submenus_parents = $('#sidebar li.submenu');
		
		$(".chevron i").attr("class","icon-chevron-down");
		submenus.slideUp();			
		submenu.slideDown();
		submenus_parents.removeClass('open');		
		$("#sidebar li").removeClass('active');
		li.addClass('active').addClass('open');
		chevron.attr("class","icon-chevron-up");
	}
</script>