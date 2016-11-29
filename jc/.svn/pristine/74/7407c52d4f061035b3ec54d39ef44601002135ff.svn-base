<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.hgsoft.main.action.DWRJobForm"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>

<title>任务调度管理平台</title>

<link rel="stylesheet" href="${basePath}/css/quartz/bootstrap.min.css" charset="utf-8"/>
<!--布局样式-->
<link rel="stylesheet" href="${basePath}/css/quartz/bootstrap-responsive.min.css" charset="utf-8"/>
<!--布局样式2-->
<link href="${basePath}/css/quartz/overview/style.css" rel="stylesheet" charset="utf-8"/>

<script type="text/javascript" src="${basePath}/js/quartz/jquery.min.js"></script>
<!--jquery-->
<!--<script type="text/javascript" src="${basePath}/js/quartz/bootstrap.min.js"></script>-->
<!--页面框架-->
<!--<script type="text/javascript" src="${basePath}/js/quartz/jquery.uniform.js"></script>-->
<!--输入框框样式-->


<script type="text/javascript" src="${basePath}/js/quartz/WebCalendar.js"></script>

</head>

<body style="background-color: #EFEFEF">
	<form name="form" method="post" action="jobTask_update.do">
		<input type="hidden" value="" name="jobname" id="jobname">
		<input type="hidden" value="" name="groupname" id="groupname">
	</form>
	
	<form name="logForm" method="post" action="jobTask_logcheck.do">
		<input type="hidden" value="" name="ljobname" id="ljobname">
		<input type="hidden" value="" name="lgroupname" id="lgroupname">
		<input type="hidden" value="detail" name="flag">
	</form>
	
	<form name="myForm" id="myForm" method="post" action="jobTask_list.do">
		<div id="content">
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12" style="margin:0 !important;">
						<div class="widget-box">
							<div class="widget-title">
								<h5>任务调度管理</h5>
							</div>


							<div class="widget-content"
								style="height:230px; overflow:visible;">
								<!--  查询条件  -->
								<div class="span12">
									<div class="widget-box" style="margin:0;">
										<div class="widget-title">
											<h5>查询条件</h5>
											<div style="float:right;">
												
												<a href="#" class=" btn btn-info" style="margin:3px;"
														id="addTaskBtn" name="addTaskBtn"> 添加 </a>
														
												<a href="#" class=" btn btn-info"
													style="margin:3px;" id="checkTaskBtn"
													name="checkTaskBtn"
													onclick="javascript:document.myForm.submit();return false;"> 查询 </a> &nbsp; 
											</div>
										</div>
										<div class="widget-content">

											<input type="hidden" name="merLogo" value="0">
											<table  class="table table-bordered" cellspacing="0" >
												<tbody>
													<tr>
														<th >任务名称</th>
														<td >
															<input type="text" name="jobName" id="jobName" value="${jobName}">
														</td>
													</tr>
													<tr>
														<th >任务组别</th>
														<td >
															<input type="text" name="groupName" id="groupName" value="${groupName}">
														</td>
													</tr>
													<tr>
														<th >任务类型</th>
														<td >
															<select name="triggerType" id="triggerType">
																<option value="-1" <c:if test="${triggerType == '-1'}">selected="selected"</c:if>>全部</option>
																<option value="1" <c:if test="${triggerType == '1'}">selected="selected"</c:if>>循环</option>
																<option value="0" <c:if test="${triggerType == '0'}">selected="selected"</c:if>>一次</option>
															</select>
														</td>
													</tr>
												</tbody>
											</table>

										</div>
									</div>
								</div>
								<!-- span6 end -->
							</div>
							<!-- widget-content end -->
						</div>
						<!-- weidget-box end -->
					</div>
					<!-- span12 end  -->
				</div>
				<!-- 标签总体成功率比 -->
				<!--  数据列表  -->
				<div class="row-fluid">
					<div class="span12">
						<div class="widget-box">
							<div class="widget-title">
								<span class="icon"><i class="icon-th"></i>
								</span>
								<h5>数据列表</h5>
								<!-- <a href="#" class="pull-right" style="color:#666;"><i class="icon-download-alt"></i>&nbsp;导出Excel</a> -->
							</div>
							<!-- widget-title end -->
							<div class="widget-content nopadding">
						
								<table id="tb"  class="table table-bordered" cellspacing="0" >
									<thead>
										<tr>
											<th>序号</th>
											<th>任务名称</th>
											<th>任务组别</th>
											<th>任务状态</th>
											<th>任务类型</th>
											<th>下次触发时间</th>
											<th>上次触发时间</th>
											<th>描述</th>
											<th>日志相关</th>
										</tr>
									</thead>
									<tbody >
										<c:forEach items="${dwrJobs}" var="item" varStatus="status">
											<tr>
												<td style="text-align: center;">${status.count}
												</td>
												<td >
													<a style="color:blue;" href="#" onclick="javascript:update('${item[0]}','${item[1]}');return false;">
													${item[0]}	
													</a>
												</td>
												<td>
													${item[1]}	
												</td>
												<td style="text-align: center;">
													<c:choose>
														<c:when test="${item[2] eq 'WAITING' or item[2] eq 'ACQUIRED'}">
															运行中
														</c:when>
														<c:when test="${item[2] eq 'PAUSED'}">
															已停止
														</c:when>
														<c:when test="${item[2] eq 'ERROR'}">
															错误
														</c:when>
														<c:otherwise>
															未知
														</c:otherwise>
													</c:choose>
												</td>
												<td style="text-align: center;">
													<c:choose>
														<c:when test="${item[3] eq '1'}">
															循环
														</c:when>
														<c:when test="${item[3] eq '0'}">
															一次
														</c:when>
														<c:otherwise>
															未知
														</c:otherwise>
													</c:choose>
												</td>
												<td style="text-align: right;">
													${item[6]}&nbsp;
												</td>
												<td style="text-align: right;">
													${item[5]}&nbsp;
												</td>
												<td >
													${item[4]}&nbsp;
												</td>
												<td style="text-align: center;">
													<a style="color:blue;" href="#" onclick="javascript:log('${item[0]}','${item[1]}');return false;">明细</a>
												</td>
											</tr>
										</c:forEach>
									<tbody >
								</table>


							</div>
		                 </div>
					  </div>
		            </div>
		         </div>
		        
		     </div>

		
 		<jsp:include page="../include/pagerInfo.jsp"></jsp:include>
	</form>
	<form name="addForm" method="post" action="jobTask_add.do" target="mainIframe">
	
	</form>
	<script type="text/javascript">
		jQuery(function($) {
			/**$('#pbilldatestart').click(function() {
				SelectDate(this, 'yyyy-MM-dd');
			});
			$('#pbilldateend').click(function() {
				SelectDate(this, 'yyyy-MM-dd');
			});*/
			
			$('#addTaskBtn').click(function(){
				//window.location.target="mainIframe";
				//window.location.href = "jobTask_add.do";
				
				document.addForm.submit();
			});
	
		});
		
		/**
		*任务更新
		*/
		function update()
		{
			$("#jobname").val(arguments[0]);
			$("#groupname").val(arguments[1]);
			document.form.submit();
		}
		
		/**
		*查看日志
		*/
		function log()
		{
			$("#ljobname").val(arguments[0]);
			$("#lgroupname").val(arguments[1]);
			document.logForm.submit();
		}
	</script>
</body>
</html>
