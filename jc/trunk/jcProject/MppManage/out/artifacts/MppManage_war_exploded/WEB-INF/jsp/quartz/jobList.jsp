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
<link rel="stylesheet" type="text/css"
	href="${basePath}/theme/${session.theme}/main.css" />
<script language="javascript" src="${basePath}/js/jquery.min.js"></script>
<script src="${basePath}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${basePath}/js/quartz/plugins/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${basePath}/js/communicate.js"></script>
<script type="text/javascript" src="${basePath}/js/util.js"></script>
</head>

<body>
	<form name="form" method="post" action="jobTask_update.do">
		<input type="hidden" value="" name="jobname" id="jobname"> <input
			type="hidden" value="" name="groupname" id="groupname">
	</form>

	<form name="logForm" method="post" action="jobTask_logcheck.do">
		<input type="hidden" value="" name="ljobname" id="ljobname"> <input
			type="hidden" value="" name="lgroupname" id="lgroupname"> <input
			type="hidden" value="detail" name="flag">
	</form>
	<form name="myForm" id="myForm" method="post" action="jobTask_list.do">
		<ul class="breadcrumb">
			<li><i class="icon-home icon-2x"></i></li>
			<li>当前位置：${currentPosition}</li>
		</ul>
		<div class="widget widget-table">
			<div class="widget-content" style="min-height:0px;">
				<input type="hidden" name="merLogo" value="0" />
				<table class="pn-ftable" border="0" cellpadding="10">
					<tbody>
						<tr>
							<th>任务名称 :</th>
							<td class="pn-fcontent"><input type="text" name="jobName"
								id="jobName" value="${jobName}" maxlength="200"/></td>
							<th>任务组 :</th>
							<td class="pn-fcontent"><input type="text" name="groupName"
								id="groupName" value="${groupName}"  maxlength="200"/></td>
							<th>任务类型 :</th>
							<td class="pn-fcontent"><select name="triggerType"
								id="triggerType">
									<option value="-1"
										<c:if test="${triggerType == '-1'}">selected="selected"</c:if>>全部</option>
									<option value="1"
										<c:if test="${triggerType == '1'}">selected="selected"</c:if>>循环</option>
									<option value="0"
										<c:if test="${triggerType == '0'}">selected="selected"</c:if>>一次</option>
							</select>
							</td>
						</tr>
					</tbody>
				</table>
				
			</div>
			<!-- /widget-content -->
			<div class="widget-bottom">
					<a class="btn btn-s-md btn-primary pull-right"
						onclick="$(this).parents('form').submit();" href="javascript:;">搜索</a>
					<a class="btn btn-s-md pull-right" id="addTaskBtn"name="addTaskBtn">添加</a>
					<a class="btn btn-s-md btn-primary pull-right"
						onclick="$('#myModal').modal({keyboard : true});" href="javascript:;">手动执行</a>
					<a class="btn btn-s-md btn-primary pull-right"
						onclick="$('#myModal2').modal({keyboard : true});" href="javascript:;">手动删除日志</a>
						<a class="btn btn-s-md btn-primary pull-right"
						onclick="$('#myModal3').modal({keyboard : true});" href="javascript:;">手动可疑车辆分析</a>
				</div>
		</div>
		<div class="separator line"></div>
		<div class="widget widget-table">
			<div class="widget-header">
				<i class="icon-th-list"></i>
				<h5>任务列表</h5>
			</div>
			<!-- /widget-header -->
			<div class="widget-content widget-list widget-scroll">
				<table class="table table-striped table-bordered">
					<thead>
						<tr>
							<th>序号</th>
							<th>任务名称</th>
							<th>任务组</th>
							<th>任务状态</th>
							<th>任务类型</th>
							<th>下次触发时间</th>
							<th>上次触发时间</th>
							<th>描述</th>
							<!-- <th>日志相关</th> -->
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${dwrJobs}" var="item" varStatus="status">
							<tr>
								<td style="text-align: center;">${status.count}</td>
								<td style="text-align: left;"><a href="#"
									onclick="javascript:update('${item.name}','${item.group}');return false;">
										${item.name} </a></td>
								<td style="text-align: left;">${item.group}</td>
								<td style="text-align: center;"><c:choose>
										<c:when test="${item.triggerState eq 'NORMAL'}">
															正常 
														</c:when>
										<c:when test="${item.triggerState eq 'WAITING'}">
															等待
														</c:when>
										<c:when test="${item.triggerState eq 'ACQUIRED'}">
															执行中
														</c:when>
										<c:when test="${item.triggerState eq 'PAUSED'}">
															已停止
														</c:when>
										<c:when test="${item.triggerState eq 'ERROR'}">
															错误
														</c:when>
										<c:otherwise>
															未知
														</c:otherwise>
									</c:choose></td>
								<td style="text-align: center;"><c:choose>
										<c:when test="${item.triggerType eq '1'}">
															循环
														</c:when>
										<c:when test="${item.triggerType eq '0'}">
															一次
														</c:when>
										<c:otherwise>
															未知
														</c:otherwise>
									</c:choose></td>
								<td>${item.nextFireTime}</td>
								<td>${item.prevFireTime}</td>
								<td style="text-align: left; white-space: normal;">${item.triggerDesc}</td>
								<%-- <td><a href="#"
									onclick="javascript:log('${item.name}','${item.group}');return false;">明细</a>
								</td> --%>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<!-- /widget-content -->
			<div class="widget-bottom">
					<%-- <jsp:include page="../include/pager.jsp" /> --%>
				</div>
		</div>
	</form>
	
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width:600px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only" >关闭</span>
					</button>
					<h4 class="modal-title" >获取出口流水和入口流水</h4>
				</div>
				<div class="modal-body">
					<div style="text-align:center;">
						<font style="font-size:16px;">开始执行日期:</font><input style="margin-left:20px;" name="execQueryDate" id="execQueryDate" class="Wdate" style="background-color:white;"
	                    			onClick="javascript:WdatePicker({dateFmt: 'yyyy-MM-dd',skin:'whyGreen',startDate:'%y',dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})" type="text" />
               			<font style="font-size:16px;">结束执行日期:</font><input style="margin-left:20px;" name="execQueryDate" id="execEndQueryDate" class="Wdate" style="background-color:white;"
               						onClick="javascript:WdatePicker({dateFmt: 'yyyy-MM-dd',skin:'whyGreen',startDate:'%y',dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})" type="text" />
               			<br/><h5>注：最大执行天数为7天</h5>
					</div>
				</div>
				<div class="modal-footer" id="footer5">
					<button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" style="margin-left:7%;margin-right:6%;" onclick="sureBangDing()">确认</button>
				</div>
			</div>
		</div>
	</div>
<!-- 删除日志model开始 -->	
	<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width:600px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only" >关闭</span>
					</button>
					<h4 class="modal-title" >执行删除日志</h4>
				</div>
				<div class="modal-body">
					<div style="text-align:center;">
						<font style="font-size:16px;">开始执行日期:</font><input style="margin-left:20px;" name="StartDeleteDate" id="StartDeleteDate" class="Wdate" style="background-color:white;"
	                    			onClick="javascript:WdatePicker({dateFmt: 'yyyy-MM-dd',skin:'whyGreen',startDate:'%y',dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})" type="text" />
					</div>
					<br/><h5 style="text-align:center;">注：删除开始执行日期之前的所有日志</h5>
				</div>
				<div class="modal-footer" id="footer5">
					<button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" style="margin-left:7%;margin-right:6%;" onclick="DeleteOperLog()">确认</button>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="myModal3" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width:600px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only" >关闭</span>
					</button>
					<h4 class="modal-title" >执行可疑车辆分析</h4>
				</div>
				<div class="modal-body">
					<div style="text-align:center;">
						<font style="font-size:16px;">执行日期:</font><input style="margin-left:20px;" name="StartAbnorCarDate" id="StartAbnorCarDate" class="Wdate" style="background-color:white;"
	                    			onClick="javascript:WdatePicker({dateFmt: 'yyyy-MM-dd',skin:'whyGreen',startDate:'%y',dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})" type="text" />
					</div>
					<br/><h5 style="text-align:center;">注：分析执行日期前三个月的可疑车辆(月按照30天计算)</h5>
				</div>
				<div class="modal-footer" id="footer5">
					<button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" style="margin-left:7%;margin-right:6%;" onclick="doAbnorCarJob()">确认</button>
				</div>
			</div>
		</div>
	</div>
<!-- 删除日志model结束 -->
	<form name="addForm" method="post" action="jobTask_add.do"></form>
	<script type="text/javascript">
		jQuery(function($) {
			/**$('#pbilldatestart').click(function() {
				SelectDate(this, 'yyyy-MM-dd');
			});
			$('#pbilldateend').click(function() {
				SelectDate(this, 'yyyy-MM-dd');
			});*/

			$('#addTaskBtn').click(function() {
				//window.location.target="mainIframe";
				//window.location.href = "jobTask_add.do";

				document.addForm.submit();
			});

		});

		/**
		 *任务更新
		 */
		function update() {
			$("#jobname").val(arguments[0]);
			$("#groupname").val(arguments[1]);
			document.form.submit();
		}

		/**
		 *查看日志
		 */
		function log() {
			$("#ljobname").val(arguments[0]);
			$("#lgroupname").val(arguments[1]);
			document.logForm.submit();
		}
		
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
	
	function sureBangDing(){
		var execQueryDate = $("#execQueryDate").val();
		var execEndQueryDate = $("#execEndQueryDate").val();
		if(execQueryDate == ""){
			alert("请选择开始执行时间");
			return false;
		}else if(execEndQueryDate == ""){
			alert("请选择结束执行时间");
			return false;
		}else if(execQueryDate>execEndQueryDate){
			alert("开始执行时间必须小于结束执行时间");
			return false;
		}
		var QueryDate = execQueryDate.split("-");
		var EndQueryDate = execEndQueryDate.split("-");
		var sRDate = new Date(QueryDate[0], QueryDate[1]-1, QueryDate[2]);
		var eRDate = new Date(EndQueryDate[0], EndQueryDate[1]-1, EndQueryDate[2]);
		var days = parseInt(Math.abs((eRDate-sRDate)/(24*60*60*1000))+1);
		if(days>7){
			alert("执行时间大于7天，请重新选择");
			return false;
		}
		var url=uri+"admin/jobTask_manualBindingLiuShui.do";
		var params = {
			'conditions.startQueryDate':execQueryDate,
			'conditions.days':days
		};
		$("#myModal").modal("hide");
		$().showLoading("myForm");
		$.ajax({
			url: url,
			data:params,
			type: "POST",
			dataType:'json',
			success:function(temp){
				var map = temp;
//				var LaneResult = map.LaneResult;
//				var BindResult = map.BindResult;
//				var Str = "";
//				for(var i = 0;i<LaneResult.length;i++){
//					if(i==LaneResult.length-1){
//						Str = Str + LaneResult[i]+"；"
//					}else{
//						Str = Str + LaneResult[i]+"，"
//					}
//				}
//				for(var i = 0;i<BindResult.length;i++){
//					if(i==BindResult.length-1){
//						Str = Str + BindResult[i]+"。"
//					}else{
//						Str = Str + BindResult[i]+"，"
//					}
//				}
				if (map.msg) {
					alert(map.msg);
				}

				$("#myForm").submit();
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				//session失效
				if(XMLHttpRequest.status==200 && XMLHttpRequest.readyState==4) {
					window.location.href = uri;
				}
		 }
		});
	}
	
	function DeleteOperLog(){
		var StartDeleteDate = $("#StartDeleteDate").val();
		if(StartDeleteDate == ""){
			alert("请选择开始执行时间");
			return false;
		}
		var url=uri+"admin/jobTask_deleteOperLog.do";
		var params = {
			'conditions.startQueryDate':StartDeleteDate
		};
		$("#myModal2").modal("hide");
		$().showLoading("myForm");
		$.ajax({
			url: url,
			data:params,
			type: "POST",
			dataType:'json',
			success:function(temp){
				$("#myForm").submit();
				alert("删除成功!");
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				//session失效
				if(XMLHttpRequest.status==200 && XMLHttpRequest.readyState==4) {
					window.location.href = uri;
				}
		 }
		});  
	}
	
	function doAbnorCarJob(){
		var StartAbnorCarDate = $("#StartAbnorCarDate").val();
		if(StartAbnorCarDate == ""){
			alert("请选择开始执行时间");
			return false;
		}
		var url=uri+"admin/jobTask_doAbnorCarJob.do";
		var params = {
			'conditions.startQueryDate':StartAbnorCarDate
		};
		$("#myModal3").modal("hide");
		$().showLoading("myForm");
		$.ajax({
			url: url,
			data:params,
			type: "POST",
			dataType:'json',
			success:function(temp){
				$("#myForm").submit();
				alert("分析可疑车辆数据成功!");
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				//session失效
				if(XMLHttpRequest.status==200 && XMLHttpRequest.readyState==4) {
					window.location.href = uri;
				}
		 }
		});  
	}
	</script>
</body>
</html>
