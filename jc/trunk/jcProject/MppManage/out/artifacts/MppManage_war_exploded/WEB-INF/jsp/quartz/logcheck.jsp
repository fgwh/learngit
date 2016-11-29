<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<link rel="stylesheet" type="text/css" href="${basePath}/theme/${session.theme}/main.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/jquery.tinytooltip.css"/>

<script type="text/javascript" src="${basePath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.tinytooltip.js"></script>


<script type="text/javascript">
	var base = "${basePath}";
	function getParent(url, mid, parentId) {
		window.location.href = base + url + "?mid=" + mid + "&parentId="
				+ parentId;
	}
	
	function showDescription()
	{
		var obj = arguments[0];
		
		var msg = obj.innerHTML;
		if(msg != '')
		{
			//alert(msg);
			//$(obj).trigger(msg);	
			$(obj).tinytooltip({message: msg});
		}
		
		
	}
	
	$(document).ready(function() {
		  $('td.description').each(function(){
		  	var msg = $(this).html();
		  	$(this).tinytooltip({message: msg});
		  });
		  //$('td.description').tinytooltip({message: 'hello'});
	});
	
	$(function(){
		//按回车键，查询数据列表
         document.onkeydown = function(event) {
             var ev = document.all ? window.event : event;
             if (ev.keyCode == 13) {
                 $("#myForm").submit();
                 ev.preventDefault();
             }
         };
	});
	
</script>
</head>
<body>
<form name="myForm"  id="myForm" style="margin-top:5px" action="jobTask_logcheck.do" method="post">
	<ul class="breadcrumb">
			<li><i class="icon-home icon-2x"></i></li>
			<li>当前位置：${currentPosition}</li>
		</ul>
		<div class="widget widget-table">
			<div class="widget-content">
				<input type="hidden" name="merLogo" value="0" />
				<table class="pn-ftable" border="0" cellpadding="10">
					<tbody>
						<tr>
							<th>任务名称:</th>
							<td class="pn-fcontent"><input type="text" name="jobName"
								id="jobName" value="${jobName}" maxlength="200"/></td>
							<th>任务组:</th>
							<td class="pn-fcontent"><input type="text" name="groupName"
								id="groupName" value="${groupName}" maxlength="200"/></td>
						</tr>
					</tbody>
				</table>
				
			</div>
			<!-- /widget-content -->
			<div class="widget-bottom">
					<a class="btn btn-s-md btn-primary pull-right" id="checkTaskBtn" name="checkTaskBtn" onclick="javascript:document.myForm.submit();">搜索</a>
					<!-- <a class="btn btn-s-md pull-right" onclick="javascript:location.href='jobTask_list.do';">返回</a> -->
				</div>
		</div>
		<div class="separator line"></div>
		<div class="widget widget-table">
			<div class="widget-header">
				<i class="icon-th-list"></i>
				<h5>任务日志列表</h5>
			</div>
			<!-- /widget-header -->
			<div class="widget-content widget-list">
				<table class="table table-striped table-bordered">
					<thead >
							<tr>
								<th>编号</th>
								<th>任务名称</th>
								<th>任务组</th>
							    <!--  <th>完整类名</th>-->
							    <th>内容</th>
								<th>创建时间</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="item" varStatus="status">
								<tr class="gradeX" align="center">
									<td style="text-align: center;">${status.count}</td>
									<td>${item.jobName}</td>
									<td>${item.jobGroup}</td>
									<!-- <td>${item.jobClassName}</td>-->
									<td  class="description">${item.description}</td>
									<td style="text-align: center;">
										<c:if test="${not empty item.createtime}">
											<fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH:mm:ss" type="both" dateStyle="medium"/>
										</c:if>
									</td>
									
								</tr>
							</c:forEach>
						</tbody>
				</table>
				
			</div>
			<!-- /widget-content -->
			<div class="widget-bottom">
					<jsp:include page="../include/pager.jsp" />
				</div>
		</div>
	
	</form>
				
				
</body>
</html>