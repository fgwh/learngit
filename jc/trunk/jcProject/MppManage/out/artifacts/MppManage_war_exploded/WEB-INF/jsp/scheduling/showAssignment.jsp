<!--
	显示任务 
 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>显示任务</title>
		<link rel="stylesheet" type="text/css"
			href="${basePath}/css/bootstrap.min.css" />
		<link rel="stylesheet"
			href="${basePath}/css/plugins/font-awesome/css/font-awesome.min.css" />
		<link rel="stylesheet" type="text/css"
			href="${basePath}/css/style.css" />
		<link rel="stylesheet" type="text/css"
			href="${basePath}/theme/${session.theme}/main.css" />
		<link rel="stylesheet" type="text/css"
			href="${basePath}/theme/scheduling/show.css" />

		<script language="javascript" src="${basePath}/js/jquery.min.js"></script>
		<script type="text/javascript" src="${basePath}/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="${basePath}/js/highcharts.js"></script>
		<script type="text/javascript" src="${basePath}/js/charts.js"></script>
		<script type="text/javascript" src="${basePath}/js/communicate.js"></script>
		<script type="text/javascript" src="${basePath}/js/util.js"></script>
		<script type="text/javascript" src="${basePath}/ckeditor/ckeditor.js"></script>
		<script language="javascript">
	//显示标题中字数的显示
	function cutstr(str) {
		var str1 = str;
		if (str.length > 10) {
			str1 = str.substring(0, 10);
			str1 += "...";
		}
		document.write(str1);
	}
</script>

		<style style="text/css">
a {
	cursor: pointer;
}

.showMediaContent {
	width: 550px;
	height: 400px;
}

.popupEveryFile {
	float: left;
}
</style>
	</head>

	<body>
		<form name="myForm" id="myForm" action="scheduling_eventList.do"
			method="post">
			<ul class="breadcrumb">
				<li>
					<br />
				</li>
				<li>
					当前位置：${currentPosition}&nbsp;>&nbsp;任务详细显示
				</li>
			</ul>

			<div class="widget widget-edit">
				<div class="widget-header">
					<i class="icon-th-list"></i>
					<h5>
						任务详情：
					</h5>
				</div>
				<!-- /widget-header -->
				<div class="widget-content">
					<table class="pn-ftable table-bordered" border="0" cellpadding="10">
						<tbody>
							<c:forEach items="${list}" var="item" varStatus="status">
							<tr>
								<th>
									创建者：
								</th>
								<td>
									<input type="text" value="${item[7]}" readonly="readonly" />
								</td>
								<th>
									收件人：
								</th>
								<td>
									<input type="text" value="${item[8]}" readonly="readonly" />
								</td>
							</tr>

							<tr>
								<th>
									标题：
								</th>
								<td>
									<input type="text" value="${item[1]}" readonly="readonly" />
								</td>
								<th>
									描述：
								</th>
								<td>
									<c:if test="${item[4] == 1}">正常任务</c:if>
									<c:if test="${item[4] == 2}">紧急任务</c:if>
									<c:if test="${item[4] == 3}">非常紧急任务</c:if>
								</td>
							</tr>
							<tr>
								<th>
									任务开始时间：
								</th>
								<td>
									<input type="text" value="${item[2]}"
										readonly="readonly" />
								</td>
								<th>
									任务终止时间：
								</th>
								<td>
									<input type="text" value="${item[3]}" readonly="readonly" />
								</td>
							</tr>

							<tr>
								<th>
									内容：
								</th>
								<td colspan="3">
									<div id="showAssignmentContent" style="width: 100%; height: 200px; overflow:auto;">
										${item[9]}
									</div>
								</td>
							</tr>
							</c:forEach>
							<c:if test="${!empty attachmentList}">
								<tr>
									<th>
										附件：
									</th>

									<td colspan="3">
										<a style="display:none;" id="openBigMedia" data-toggle="modal" data-target="#myModal"></a>
										<c:forEach items="${attachmentList}" var="item" varStatus="status">
											<c:if test="${item.type == 1}">
												<div class="mediaContent photo"></div>
												<div class="mediaName">
													<a onclick="showMedia('photo', '${item.filename}');">打开</a>
													<br>
													<a href="downloadzhdd_execute.do?filename=${item.filename}">下载</a>
												</div>
											</c:if>
											<c:if test="${item.type == 2}">
												<div class="mediaContent music"></div>
												<div class="mediaName">
													<br>
													<a href="downloadzhdd_execute.do?filename=${item.filename}">下载</a>
												</div>
											</c:if>
											<c:if test="${item.type == 3}">
												<div class="mediaContent video"></div>
												<div class="mediaName">
													<br>
													<a href="downloadzhdd_execute.do?filename=${item.filename}">下载</a>
												</div>
											</c:if>
											<c:if test="${item.type == 4}">
												<div class="mediaContent file">
												</div>
												<div class="mediaName">
													<br>
													<a href="downloadzhdd_execute.do?filename=${item.filename}">下载</a>
												</div>
											</c:if>
										</c:forEach>
									</td>
								</tr>
							</c:if>
							<tr>

							</tr>
							<c:if test="${!empty workFlowList[0][13]}">
								<tr>
									<th>
										原因：
									</th>
									<td colspan="3">
										${workFlowList[0][13]}
									</td>
								</tr>
							</c:if>
							<c:if test="${!empty overAttachmentList}">
								<tr>
								<tr>
									<th>
										完成附件：
									</th>

									<td colspan="3">
										<c:forEach items="${overAttachmentList}" var="item2"
											varStatus="status">
											<c:if test="${item2.overtype == 1}">
												<div class="mediaContent photo"></div>
												<div class="mediaName">
													<a onclick="showMedia('photo', '${item2.overfilename}');">打开</a>
													<br>
													<a href="downloadzhdd_execute.do?filename=${item2.overfilename}">下载</a>
												</div>
											</c:if>
											<c:if test="${item2.overtype == 2}">
												<div class="mediaContent music"></div>
												<div class="mediaName">
													<br>
													<a href="downloadzhdd_execute.do?filename=${item2.overfilename}">下载</a>
												</div>
											</c:if>
											<c:if test="${item2.overtype == 3}">
												<div class="mediaContent video"></div>
												<div class="mediaName ">
													<br>
													<a href="downloadzhdd_execute.do?filename=${item2.overfilename}">下载</a>
												</div>
											</c:if>
											<c:if test="${item2.overtype == 4}">
												<div class="mediaContent file"></div>
												<div class="mediaName">
													<br>
													<a href="downloadzhdd_execute.do?filename=${item2.overfilename}">下载</a>
												</div>
											</c:if>
											<c:if test="${item2.overtype == 9}">
												没有附件
											</c:if>
										</c:forEach>
									</td>
								</tr>
								</tr>
							</c:if>
						</tbody>
					</table>
					
				</div>
				<!-- /widget-content -->
				<div class="widget-bottom" style="border:1px solid rgb(205, 205, 205);">
						<a href="javascript:history.back(-1);" class="btn btn-danger pull-right">返回</a>

						<c:if
							test="${buttonStatus == 1 || buttonStatus == 3 || buttonStatus == 4}">
							<a onclick="setStatus(2)" class="btn btn-primary pull-right">接受</a>
						</c:if>
						<c:if test="${buttonStatus == 1}">
							<a data-toggle="modal" data-target="#myModal"
								class="btn btn-primary pull-right" onclick="setStatus(3);">挂起</a>
						</c:if>
						<c:if test="${buttonStatus == 1 || buttonStatus == 3}">
							<a data-toggle="modal" data-target="#myModal"
								class="btn btn-primary pull-right" onclick="setStatus(4);">拒绝</a>
						</c:if>
						<c:if test="${buttonStatus == 2}">
							<a data-toggle="modal" data-target="#myModal"
								class="btn btn-primary pull-right" onclick="setStatus(5);">完成</a>
						</c:if>
						<c:if test="${buttonStatus == 9 || buttonStatus == 10}">
							<a data-toggle="modal" data-target="#myModal"
								class="btn btn-primary pull-right" onclick="setStatus(1);">不同意</a>
							<a data-toggle="modal" data-target="#myModal"
								class="btn btn-primary pull-right" onclick="setStatus(${list[0][9]});">同意</a>
						</c:if>
						<c:if test="${buttonStatus == 11}">
							<a class="btn btn-primary pull-right" onclick="setStatus(6);">关闭</a>
						</c:if>
					</div>
			</div>

			<div class="separator line"></div>

			<div class="widget widget-table">
				<div class="widget-header">
					<i class="icon-th-list"></i>
					<h5>
						任务详细流程演示：
					</h5>
				</div>
				<!-- /widget-header -->
				<div class="widget-content widget-list">
					<table class="table table-striped table-bordered">
						<thead>
							<tr>
								<th>
									序号
								</th>
								<th>
									主题
								</th>
								<th>
									创建者
								</th>
								<th>
									接受人
								</th>
								<th>
									操作时间
								</th>
								<th>
									状态
								</th>
								<th>
									负责人
								</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${workFlowList}" var="item" varStatus="status">
								<tr>
									<td>
										${status.count}
									</td>
									<td>
										<script type="text/javascript">cutstr('${item[1]}')</script>
									</td>
									<td>
										${item[7]}
									</td>
									<td>
										${item[8]}
									</td>
									<td>
										${item[10]}
									</td>
									<td>
										<c:if test="${item[9] == 1}">新建</c:if>
										<c:if test="${item[9] == 2}">接受</c:if>
										<c:if test="${item[9] == 3}">挂起</c:if>
										<c:if test="${item[9] == 4}">拒绝</c:if>
										<c:if test="${item[9] == 5}">完成</c:if>
										<c:if test="${item[9] == 6}">关闭</c:if>
									</td>
									<td>
										${item[12]}
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- /widget-content -->
			</div>
		</form>


		<!-- Modal -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<form method="post" id="myModalForm" action="scheduling_operateAssignment.do"
				enctype="multipart/form-data">
				<div class="modal-dialog">
					<div class="modal-content" id="bigMoalCotent">
						<div class="modal-header">
							<!-- private String operatorPrincipalId;
						    private String operatorPrincipalName;
						    private String operatorId;
						    private String operatorStatus;
						    private String operatorReason;  -->
							<input type="hidden" value="${session.operator.id}" name="operatorPrincipalId" />
							<input type="hidden" value="${session.operator.name}" name="operatorPrincipalName" />
							<input type="hidden" value="${list[0][0]}" name="operatorId" />
							<input type="hidden" value="" name="operatorStatus" />
							
							<input name="title" value="${title}" type="hidden" />
							<input name="status" type="hidden" value="${status}" />
							<input name="senderName" type="hidden" value="${senderName}" />
							<input name="recipientName" value="${recipientName}" type="hidden" />
							<input name="senderOrRecipientName" type="hidden" value="${senderOrRecipientName}" />
							<input name="startCreateTime" type="hidden" value="${startCreateTime}" />
							<input name="endCreateTime" type="hidden" value="${endCreateTime}" />
							<input type="hidden" name="type" value="${type}" />
							
							<button type="button" id="bigModalCloseButton" class="close"
								data-dismiss="modal">
								<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">
								title
							</h4>
						</div>
						<div class="modal-body" id="inputShowContent">
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal" style="display: none" id="closeReason">
								Close
							</button>
							<input type="button" class="btn btn-primary" id="inputReason"
								value="提交"></input>
						</div>
					</div>
				</div>
			</form>
		</div>
		<!-- Modal  -->

		<!-- Modal -->
		<div class="modal fade" id="myMediaModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							<h4 class="modal-title" id="myMediaTitle">
								title
							</h4>
						</div>
						<div class="modal-body" id="myMediaContent"></div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal" id="closeMediaButton">
								关闭
							</button>

						</div>
					</div>
				</div>
		</div>
		<!-- Modal  -->


		<button type="button" id="clickMoalButton" style="display: none;"
			class="btn btn-primary" data-toggle="modal"
			data-target=".bs-example-modal-sm">
			Small modal
		</button>
		<!-- smallModal 接受和关闭所需要操作的功能 -->
		<div class="modal fade bs-example-modal-sm" tabindex="-1"
			role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-sm">
				<div class="modal-content" style="width: 250px; height: 120px;">
					<div class="modal-header" id="modalHeadContent">
						<h4 class="modal-title" id="myModalLabel">
							<center>
								是否执行该操作？
							</center>
						</h4>
					</div>
					<div id="operatorButton" style="width: 250px; height: 60px;">
						<div style="width: 83px; height: 60px; float: left;">
							<button id="operatorExitButton" type="button"
								class="btn btn-danger" data-dismiss="modal"
								style="margin-left: 15px; margin-top: 20px;">
								取消
							</button>
						</div>
						<div style="width: 84px; height: 60px; float: left;">
							<button type="button" id="operatorReturnDiv"
								class="btn btn-primary" data-dismiss="modal"
								style="margin-left: 15px; margin-top: 20px;">
								确定
							</button>
						</div>
						<div style="width: 83px; height: 60px; float: left;">
							<button id="operatorSureButton" type="button"
								class="btn btn-primary"
								style="margin-left: 15px; margin-top: 20px;">
								确定
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- smallModal -->


		<script language="javascript">
	var popupFileCount = 1;
	var pupupSubmitFile = 1;
	var nodeCount = 1;
	
	$(function(){
		var width = document.body.scrollWidth;
		width = width*0.87;
		$("#showAssignmentContent").css({"width": width});
		var url = uri + "/admin/scheduling_operateAssignment.do";
		$("#myModalForm").attr("action", url);
	});
	
	$("#inputReason").bind("click", function() {
						var totalFileSize = 0;
						for(i=1;i<nodeCount;i++){
							var fileNode = "input_"+i;
							if($("#"+fileNode).length > 0){
								var size = document.getElementById(fileNode).files[0].size;
								totalFileSize += size; 
							}
						}
						
						if(totalFileSize > 1024*1024*100){
							$("#errorMsg").html("<font style='font-size:14px;color:red;'>上传文件不超过100M</font>");
							return;
						} else if ($("#reason").val() == ''
								|| $("#reason").val() == null) {
							$("#errorMsg").html("<font style='font-size:14px;color:red;'>上述内容为必填项</font>");
							return;
						} else {
							$("#myModalForm").submit();

							$("#closeReason").click();
							popupFileCount = 1;
						}
					});

	function setStatus(obj) {
		$("#inputReason").show();
		$("#closeReason").hide();
		$("#myModalLabel").text("原因");
		if (obj == '5') {
			$("#inputShowContent")
					.html("<div><textarea maxlength='200' name='operatorReason' id='reason' placeholder='在以下输入内容（少于200字）...' style='width:550px; height:200px;'></textarea></div><div><div style='margin-top:20px;' id='popupAssignmentTitle'>附件：<img width='30' height='30' src='../theme/scheduling/more.png' style='margin-left:20px;' onclick='clickPopupAssignment();'></img></div><div style='height:40px;' id='popupAssignmentContent'><div id='popupfile_1' class='popupEveryFile' style='margin-left: 36px; margin-top: 20px;'><input type='file' id='popupSubmitFile' style='display:none;' onchange='submitPopupFile(this);' name='uploadFile'></div></div></div><br><div id='errorMsg'></div>");
		} else if (obj == '3' || obj == '4' || obj == '1') {
			$("#inputShowContent")
					.html("<div><textarea maxlength='200' name='operatorReason' id='reason' placeholder='在以下输入内容（少于200字）...' style='width:550px; height:200px;'></textarea></div><div id='errorMsg'></div>");
		} else {
			$("#modalHeadContent")
					.html(
							"<h4 class='modal-title' id='myModalLabel'><center>是否执行该操作？</center></h4>");
			$("#operatorReturnDiv").hide();
			$("#operatorExitButton").show();
			$("#operatorSureButton").show();

			$("#clickMoalButton").click();
		}
		
		$("#inputReason").val("提交");
		$("input[name='operatorStatus']").val(obj);
	}
	
	$("#inputShowContent").bind("click", function(){
		if($("#errorMsg").length > 0){
			$("#errorMsg").empty();
		}
	});


	function clickPopupAssignment() {
		$("#popupSubmitFile").click();
	}
	
	var PHOTO_TYPE = "-.jpg-,-.png-,-.jpeg-,-.bmp-";
	var MUSIC_TYPE = "-.mp3-,-.wma-,-.ogg-,-.wmv-,-.amr-,-.3gpp-";
	var VIDEO_TYPE = "-.mp4-,-.rmvb-,-.avi-,-.rm-,-.mov-,-.3gp-";
	var FIELD_URL = "-";
	function submitPopupFile(obj) {
		var filepath = $('#popupSubmitFile').val();
		var endpath = /\.[^\.]+$/.exec(filepath);
		var node = 'popupfile_' + popupFileCount;
		
		popupFileCount++;
		pupupSubmitFile++;

		$('#' + node).attr("class", "popupEveryFile");
		$('#' + node).attr("style", "margin-left: 36px; margin-top: 20px;");
		
		
		var inputNode = "input_"+nodeCount;
		nodeCount++;
		$("#popupSubmitFile").attr("id", inputNode);
		
		var tempEndpath = FIELD_URL + endpath + FIELD_URL;
		var mediaClass = 'file';
		if(PHOTO_TYPE.indexOf(tempEndpath) != -1){
			mediaClass = 'photo';
		} else if(MUSIC_TYPE.indexOf(tempEndpath) != -1){
			mediaClass = 'music';
		} else if(VIDEO_TYPE.indexOf(tempEndpath) != -1){
			mediaClass = 'video';
		} else{
			mediaClass = 'file';
		}
		
		$('#' + node).append(
						"<div><div class='"+mediaClass+"' style='width:40px; height:30px;'></div><a style='margin-left:8px;' onclick=\"deletePopupFile('"
								+ node + "');\"><font>删除</font></a></div>");

		$("#popupSubmitFile").attr("onchange", "");
		var node2 = 'popupfile_' + popupFileCount;
		$("#popupAssignmentContent").append(
						"<div class='popupEveryFile' style='margin-left: 36px; margin-top: 20px;' id='"
								+ node2
								+ "'><input type='file' id='popupSubmitFile' style='display:none;' onchange='submitPopupFile(this);' name='uploadFile'></div>");

		if (pupupSubmitFile == 5) {
			$("#popupAssignmentTitle").hide();
			return;
		}
	}

	function deletePopupFile(obj) {
		$("#" + obj).empty();
		$("#" + obj).attr("class", "");
		$("#" + obj).attr("style", "");
		$("#popupAssignmentTitle").show();

		pupupSubmitFile--;
	}

	$("#bigModalCloseButton").bind("click", function() {
		popupFileCount = 1;
		pupupSubmitFile = 1;
	});

	function showMedia(type, filename) {
		var divNode;
		var endpath = /\.[^\.]+$/.exec(filename);
		
		if(type == "photo"){
			divNode = "<img src='messagezhdd_getDocumentImg.do?imgUrl="+ filename + "' class='showMediaContent' alt='" + filename + "' />";
			$("#myMediaTitle").text(filename);
			$("#myMediaContent").html(divNode);
			
			$("#myMediaModal").modal({keyboard: true});
		} else{
			$("#modalHeadContent").html("<h4 class='modal-title' id='myModalLabel'><center>抱歉，此格式不支持打开</center></h4>");

			$("#operatorReturnDiv").show();
			$("#operatorExitButton").hide();
			$("#operatorSureButton").hide();
			$("#clickMoalButton").click();
		}
	}
	
	$("#operatorSureButton").bind("click", function(){
		var action = uri+"/admin/scheduling_operateAssignment.do?operatorStatus="
						+ $('input[name="operatorStatus"]').val() + "&operatorPrincipalName="
						+ $('input[name="operatorPrincipalName"]').val() + "&operatorPrincipalId="
						+ $('input[name="operatorPrincipalId"]').val() + "&operatorId="
						+ $('input[name="operatorId"]').val();
		
		var title = $("input[name='title']").val();
		var status = $("input[name='status']").val();
		var startCreateTime = $("input[name='startCreateTime']").val();
		var endCreateTime = $("input[name='endCreateTime']").val();
		var nodes = $("input[name='senderOrRecipientName']");

		var senderName = $("input[name='senderName']").val();
		var recipientName = $("input[name='recipientName']").val();
					
		var type = $("input[name='type']").val();
		var senderOrRecipientName = nodes.val();
		
		action  += "&title="+title+"&senderName="+senderName+"&recipientName="+recipientName+"&startCreateTime="+startCreateTime+"&endCreateTime="+endCreateTime+"&status="+status+"&senderOrRecipientName="+senderOrRecipientName+"&type="+type;				
		window.location.href = action;
	});
	
	
</script>
	</body>
</html>
