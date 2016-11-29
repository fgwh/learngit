<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
	<head>
		<style type="text/css">
/*
.loading {
	width: 160px;
	height: 56px;
	position: absolute;
	top: 50%;
	left: 50%;
	line-height: 56px;
	color: #fff;
	padding-left: 60px;
	font-size: 15px;
	background: #000 url(../images/loading.gif) no-repeat 10px 50%;
	opacity: 0.7;
	z-index: 9999;
	-moz-border-radius: 20px;
	-webkit-border-radius: 20px;
	border-radius: 20px;
	filter: progid : DXImageTransform . Microsoft . Alpha(opacity = 70);
}
*/

.fileInput {
	width: 30px;
	height: 30px;
	overflow: hidden;
	opacity: 0;
	filter: alpha(opacity = 0);
}

#attachment a {
	display: inline-block;
	float: left;
	height: 30px;
	width: 30px;
	background: url(../theme/scheduling/more.png);
}

.inputFileClass {
	display: none;
}

.usernameDiv {
	width: 93px;
	float: left;
	height: 30px;
}

.keywordInputDiv {
	width: 450px;
	height: 29px;
}

.hiddenDiv {
	display: none;
}

span {
	cursor: pointer;
}

.showMediaContent {
	width: 550px;
	height: 400px;
}

.usernameDiv {
	width: 270px;
	height: 30px;
	float: left;
}

.lowerTitle {
	width: 100%;
	height: 20px;
	float: left;
	background-color:  #CDCDCD;
	padding-left: 10px;
}

.lowerContent {
	width: 50%;
	float: left;
}

.zhddshowusername {
	height: 25px;
    padding-left: 25%;
    margin-top: -16px;
    cursor:default;
}

.usernameContentDiv {
	height: 200px;
	overflow: auto;
}
#addUsernameContent input[type="checkbox"]{
	margin-left:10%;
}
</style>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>添加任务</title>
		<link rel="stylesheet" type="text/css"
			href="${basePath}/css/bootstrap.min.css" />
		<link rel="stylesheet"
			href="${basePath}/css/plugins/font-awesome/css/font-awesome.min.css" />
		<link rel="stylesheet" type="text/css"
			href="${basePath}/css/style.css" />
		<link rel="stylesheet" type="text/css"
			href="${basePath}/theme/${session.theme}/main.css" />

		<script language="javascript" src="${basePath}/js/jquery.min.js"></script>
		<script type="text/javascript" src="${basePath}/js/highcharts.js"></script>
		<script type="text/javascript" src="${basePath}/js/charts.js"></script>
		<script type="text/javascript" src="${basePath}/js/communicate.js"></script>
		<script type="text/javascript" src="${basePath}/js/bootstrap.min.js"></script>
		<script type="text/javascript"
			src="${basePath}/js/quartz/plugins/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${basePath}/js/util.js"></script>
		<script type="text/javascript" src="${basePath}/ckeditor/ckeditor.js"></script>

	</head>

	<body>
		<form name="myForm" id="myForm" method="post"
			enctype="multipart/form-data" action="">
			<ul class="breadcrumb">
				<li>
					&gt;
				</li>
				<li>
					当前位置：${currentPosition}&nbsp;>&nbsp;新建任务
				</li>
			</ul>
			<div class="widget widget-edit">
				<div class="widget-content">
					<table class="pn-ftable table-bordered" border="0" cellpadding="10">
						<tbody>
							<tr>
								<th>
									标题
									<span class="point">*</span>：
								</th>
								<td class="pn-fcontent">
									<input type="text" placeholder="请输标题，少于50字" maxlength="50"
										name="addTitle" value="${addTitle}" />
								</td>
							</tr>

							<tr>
								<th>
									收件人
									<span class="point">*</span>：
								</th>
								<td class="pn-fcontent">
									<input type="text" name="addRecipient" value="${addRecipient}"
										readonly="readonly" />
									<input type="hidden" name="addRecipients" value="" />

								</td>
							</tr>

							<tr>
								<th>
									任务开始时间
									<span class="point">*</span>：
								</th>
								<td class="pn-fcontent">
									<input class="Wdate" style="background-color: white;"
										name="addCreateTime" id="addCreateTime"
										value="${addCreateTime}"
										onclick="javascript:WdatePicker({dateFmt: 'yyyy-MM-dd',skin:'whyGreen'})"
										readonly="readonly" type="text" />
								</td>
							</tr>

							<tr>
								<th>
									建议完成时间
									<span class="point">*</span>：
								</th>
								<td class="pn-fcontent">
									<input class="Wdate" style="background-color: white;"
										name="addEndTime" id="addEndTime" value="${addEndTime}"
										onclick="javascript:WdatePicker({dateFmt: 'yyyy-MM-dd',skin:'whyGreen'})"
										readonly="readonly" type="text" />
								</td>
							</tr>



							<tr>
								<th>
									任务类型
									<span class="point">*</span>：
								</th>
								<td class="pn-fcontent">
									<input type="radio" name="addDescript" checked="checked"
										value="1" />
									正常
									<input type="radio" name="addDescript" value="2" />
									紧急
									<input type="radio" name="addDescript" value="3" />
									十分紧急
							</tr>

							<tr>
								<th>
									任务内容
									<span class="point">*</span>：
								</th>
								<td class="pn-fcontent">
									<textarea rows="25" cols="50" name="addContent"
										class="ckeditor" id="addContent"></textarea>
								</td>

							</tr>
							<tr>
								<th>
									附件（视频，音频，照片，文件）：
								</th>
								<td class="pn-fcontent" id="attachment">
									<div>
										<a><input type="file" class="fileInput" id="allFileInput"
												onchange="addSubmitFile(this.id);" />
										</a>
										<div class="down_big"
											style="font-size: 6px; width: 30px; height: 30px; float: left; display: none;">
											<span id="deleteuploadFile" style="font-size:12px"
												onclick="deleteuploadFile(this, 'file1');">删除</span>
										</div>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
					
				</div>
				<!-- /widget-content -->
				<div class="widget-bottom">
						<input type="hidden" name="sender"
							value="${session.operator.username}" />
						<a href="javascript:history.back(-1);"
							class="btn btn-danger pull-right">取消</a>
						<a href="javascript:void(0);" id="addsubmit"
							class="btn btn-primary pull-right">下达</a>
					</div>
			</div>
		</form>

		<!-- 弹窗显示 -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button id="closewindowbutton"type="button" class="close" data-dismiss="modal">  <!--  onclick="closeButton();" -->
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<div>
							<h4 class="modal-title">
								选择联系人
							</h4>
						</div>
					</div>
					<div class="modal-body">
						<div id="addUsernameContent">
							<div class="keywordDiv">
								<input class="keywordInputDiv" type="text" placeholder="请输入搜索的关键字" maxlength="20" name="keyword" style="width:100%;" />
							</div>
							<hr />
						
							<div id="usernameContent" class="usernameContentDiv">
								<c:forEach items="${adminList}" var="admin">
									<div class="usernameDiv">
										<input type="checkbox" name="everyUsername"
											value="${admin.username}">
										<font style="font-size: 16px;">${admin.username}</font>
									</div>
								</c:forEach>
								<br>
								<br>
							</div>
							<div id="serchUsernameContent" class="usernameContentDiv" style="display:none;"></div>
						</div>
					</div>
					<div class="modal-footer">
						<div id="closeButton">
							<button type="button" class="btn btn-primary" id="closeUsernameButton" data-dismiss="modal">提交联系人</button> <!--  onclick="closeButton();" -->
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- myModal -->

		<!-- 多媒体文件的显示 -->
		<div class="modal fade" id="myMediaModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">
						<div id="showMediaContent"></div>
					</div>
					<div class="modal-footer">
						<div>
							<button type="button" id="closeMediaButton" mediaId="" class="btn btn-primary" data-dismiss="modal">关闭</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 多媒体文件的显示 -->

		<!-- smallModal 接受和关闭所需要操作的功能 -->
		<div class="modal fade bs-example-modal-sm" tabindex="-1" id="mySmallModal"
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
						<div style="width: 125px; height: 60px; float: left;">
							<button id="operatorSureButton" type="button"
								class="btn btn-primary" data-dismiss="modal"
								style="margin-left: 100px; margin-top: 20px;">
								确定
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- smallModal -->

<script language="javascript">
	var displayId;
	var buttonId;
	var log = {};
	var editor = null;
	var inputCount = 1;
	$(function() {
		editor = CKEDITOR.replace('addContent');
		var actionUrl = uri + "/admin/scheduling_upload.do";
		$("#myForm").attr("action", actionUrl);
		//邦定搜索按钮，添加点击事件查询数据列表
		$("#addsubmit").on("click", function(event) {
			//验证查询条件合法性
				if (log.checkDate()) {
					$("#myForm").submit();
					totalFileSize = 0;
				}
				event.preventDefault();
			});

		//按回车键，查询数据列表
		document.onkeydown = function(event) {
			var ev = document.all ? window.event : event;
			if (ev.keyCode == 13) {
				//验证查询条件合法性
				if (log.checkDate()) {
					$("#myForm").submit();
					totalFileSize = 0;
				}
				ev.preventDefault();
			}
		};

		//字母显示用户内容
		sortLowerCase();
		
		$("input[name='addRecipient']").live("click", selectRecipient);
		$("#addUsernameContent .zhddshowusername").live("click", clickUsername);
		$("#addUsernameContent input[name='keyword']").live("input propertychange", checkUsername);
		$("#serchUsernameContent input[name='everyUsername']").live("change", changeUsername);
	});
	
	//验证查询条件
	log.checkDate = function() {
		var flag = true;
		var error = "";
		var v_title = $("input[name='addTitle']").val().trim();
		var v_recipient = $("input[name='addRecipient']").val().trim();
		var v_start = $("#addCreateTime").val().trim();
		var v_end = $("#addEndTime").val().trim();
		var v_content = editor.document.getBody().getText().trim(); //取得纯文本
		var totalFileSize = 0;
		
		
		
		for(i=1;i<inputCount;i++){
			var fileNode = "input_"+i;
			if($("#"+fileNode).length > 0){
				var size = document.getElementById(fileNode).files[0].size;
				totalFileSize += size; 
			}
		}
		
		//取得html文本
		//var v_content2 = editor.document.getBody().getHtml(); 

		if (v_title == null || v_title == '') {
			error = "带*的不能为空";
			flag = false;
		} else if (v_recipient == null || v_recipient == '') {
			error = "带*的不能为空";
			flag = false;
		} else if (v_content == null || v_content == '') {
			error = "带*的不能为空";
			flag = false;
		} else if (v_start == null || v_start == '') {
			error = "带*的不能为空";
			flag = false;
		} else if (v_end == null || v_end == '') {
			error = "带*的不能为空";
			flag = false;
		} else if (new Date(v_start.replace("-", "/")) > new Date(v_end.replace("-", "/"))) {
			error = "开始时间不能大于结束时间";
			flag = false;
		} else if(totalFileSize > 1024*1024*100){
			error = "总文件大小不能超过100M"
			flag = false;
		}

		if (flag) {
			return flag;
		} else{
			$("#modalHeadContent").html(
					"<h4 class='modal-title' id='myModalLabel'><center>"
							+ error + "</center></h4>");
			$('#mySmallModal').modal({
				keyboard: true
			});
			
			return;
		}
	};
	
	
	//改变内容
	function changeUsername(){
		var node = $(this);
		var flag = node.is(':checked');
		var selectName = node.attr("selectName");
		var selectUsername = node.attr("selectUsername");
		var changeNode = $("#usernameContent input[selectusername='"+selectUsername+"']");
		changeNode.attr("checked", flag);
	}
	
	
	function checkUsername(){
		var divNode = "";
		var value = $(this).val().trim();
		if(value == "" || value == null){
			$("#usernameContent").show();
			$("#serchUsernameContent").hide();	
					
		} else{
			var nodeArr = $("#usernameContent input[selectname*='"+value+"']");
			if(nodeArr.length > 0){
				for(var i=0;i<nodeArr.length;i++){
					var name = nodeArr[i].attributes.selectname.value;
					var username = nodeArr[i].attributes.selectusername.value;	
					if(nodeArr[i].checked){
						divNode += "<div class='lowerContent'><input selectName='"+name+"' selectUsername='"+username+"' type='checkbox' class='usernameInput' name='everyUsername' checked='checked'><div class='zhddshowusername'>"+name+"["+username+"]</div></div>"; 
					} else{
						divNode += "<div class='lowerContent'><input selectName='"+name+"' selectUsername='"+username+"' type='checkbox' class='usernameInput' name='everyUsername'><div class='zhddshowusername'>"+name+"["+username+"]</div></div>";
					}
				}
			} else{
				divNode += "<div class='lowerContent'>没有搜索内容...</div>";
			}
					
			$("#serchUsernameContent").html(divNode);

			$("#usernameContent").hide();
			$("#serchUsernameContent").show();	
		}
				
		divNode = "";
	}
	//选择收件人
	function selectRecipient() {
		$("input[name='keyword']").attr("value", "");
		$("#usernameContent").show();
		$("#serchUsernameContent").hide();	

		var url = uri + '/admin/scheduling_showSelectUsername.do';
		params = {};
		GetSend(url, params, querySchedulingSucc, querySchedulingfail);
	}
	

	//添加附件
	var fileCount = 1;
	var count = 1;
	var PHOTO_TYPE = "-.jpg-,-.png-,-.jpeg-,-.bmp-";
	var MUSIC_TYPE = "-.mp3-,-.wma-,-.ogg-,-.wmv-,-.amr-,-.3gpp-";
	var VIDEO_TYPE = "-.mp4-,-.rmvb-,-.avi-,-.rm-,-.mov-,-.3gp-";
	var FIELD_URL = "-";
	function addSubmitFile(obj) {
		var filepath = $('#allFileInput').val();
		var endpath = /\.[^\.]+$/.exec(filepath);

		//文件的显示
		if (typeof FileReader === 'undefined') {
			$("#modalHeadContent").html("<h4 class='modal-title' id='myModalLabel'><center>你的浏览器不支持</center></h4>");
			$('#mySmallModal').modal({
				keyboard: true
			});
			return;
		}
	
		var filesize = document.getElementById(obj).files[0].size;
		if(filesize > 50*1024*1024){
			$("#modalHeadContent").html("<h4 class='modal-title' id='myModalLabel'><center>上传的文件不能大于50M</center></h4>");
			//$("#clickMoalButton").click();
			$('#mySmallModal').modal({
				keyboard: true
			});
			return;
		}
		
		var reader = new FileReader();

		var math;
		var addDescription = '预览';

		var mediaName = 'file' + count;
		var mediaName2 = '';
		count++;
		
		filepath = filepath.split("\\")[2];
		var tempEndpath = FIELD_URL + endpath + FIELD_URL;
		if (PHOTO_TYPE.indexOf(tempEndpath) != -1) {
			math = '-138px';
			addDescription = '预览';
			reader.onload = function(e) {
				$("#showMediaContent").append(
						"<div class='hiddenDiv hiddenMediaDiv' id='" + mediaName
								+ "'><center><h3>" + filepath
								+ "</h3></center><hr /><div><img src='"
								+ this.result
								+ "' class='showMediaContent' /></div></div>");
			};
			reader.readAsDataURL(document.getElementById(obj).files[0]);
		} else if (MUSIC_TYPE.indexOf(tempEndpath) != -1) {
			math = '-178px';	
			if(endpath == '.mp3' || endpath == 'ogg'){
				mediaName2 = 'file' + count + "1";
				reader.onload = function(e) {
					$("#showMediaContent")
							.append(
									"<div class='hiddenDiv hiddenMediaDiv' id='"
											+ mediaName
											+ "'><center><h3>"
											+ filepath
											+ "</h3></center><hr /><div><audio id='"
											+ mediaName2
											+ "' class='showMediaContent mediaContent' src='"
											+ this.result
											+ "' controls='controls'>Your browser does not support the audio element.</audio></div></div>");
				};
				reader.readAsDataURL(document.getElementById(obj).files[0]);
				
				addDescription = '预览';	
			} else{
				addDescription = '';
			}
		} else if (VIDEO_TYPE.indexOf(tempEndpath) != -1) {
			math = '-220px';
			if(endpath == '.mp4'){
				mediaName2 = 'file' + count + "1";
				reader.onload = function(e) {
					$("#showMediaContent")
							.append(
									"<div class='hiddenDiv hiddenMediaDiv' id='"
											+ mediaName
											+ "'><center><h3>"
											+ filepath
											+ "</h3></center><hr /><div><video id='"
											+ mediaName2
											+ "' class='showMediaContent mediaContent' src='"
											+ this.result
											+ "' controls='controls'>your browser does not support the video tag</video></div></div>");
				};
				reader.readAsDataURL(document.getElementById(obj).files[0]);
				
				addDescription = '预览';
			} else{
				addDescription = '';
			}
		} else {
			addDescription = '';
			math = '-262px';
		}

		$('#deleteuploadFile').before(
				"<span style='font-size:12px;' onclick=\"openUploadFile('"
						+ mediaName + "', '" + mediaName2 + "');\">"
						+ addDescription + "</span><br />");

		$('#deleteuploadFile').parent().show();
		$('#allFileInput')
				.parent()
				.css(
						{
							"background" :"url(../theme/scheduling/icons.png) no-repeat 7px "
									+ math,
							"width" :"40px",
							"height" :"30px"
						});

		$('#deleteuploadFile').attr('id', '');
		$('#allFileInput').attr('onchange', '');
		$('#allFileInput').attr('name', 'uploadFile');
		$('#allFileInput').css('display', 'none');
		
		inputNode = "input_"+inputCount;
		$('#allFileInput').attr('id', inputNode);
		inputCount++;
		
		if (fileCount < 12) {
			var appendNode = "<div><a style='display: inline-block;float: left;height: 30px;width: 30px;background: url(../theme/scheduling/more.png);'><input type='file' class='fileInput' id='allFileInput' onchange='addSubmitFile(this.id);'></a><div class='down_big' style='margin-top:2px;font-size:6px; width:30px; float:left; display:none;'><span id='deleteuploadFile' onclick=\"deleteuploadFile(this, '"+ mediaName + "', '"+inputNode+"');\">删除</span></div></div>";
			$("#attachment").append(appendNode);
		}
		
		fileCount++;
	}

	//删除附件
	function deleteuploadFile(obj, id) {
		fileCount--;
		if (fileCount == 12) {
			$("#attachment")
					.append(
							"<div><a style='display: inline-block;float: left;height: 30px;width: 30px;background: url(../theme/scheduling/more.png);'><input type='file' class='fileInput' id='allFileInput' onchange='addSubmitFile(this.id);'></a><div class='down_big' style='margin-top:2px;font-size:6px; width:30px; float:left; display:none;'><span id='deleteuploadFile' onclick=\"deleteuploadFile(this, '"
									+ id + "');\">删除</span></div></div>");
		}
		
		
		$(obj).parent().parent().empty();
		//删除多媒体文件
		$(id).empty();
	}

	//在模态框中加入排序组合
	function sortLowerCase() {
		var divNode = '';
		var liLenghs = [ 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
				'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
				'x', 'y', 'z' ];
		for ( var i = 0; i < liLenghs.length; i++) {
			var id = "lowerCase_" + liLenghs[i];
			var titleId = "lowerCaseTitle_" + liLenghs[i];
			var contentId = "lowerCaseContent_" + liLenghs[i];
			divNode += "<div id='" + id + "'><div id='" + titleId
					+ "'></div><div id='" + contentId + "'></div></div>"
		}

		divNode += "<div id='lowerCase_other'><div id='lowerCaseTitle_other'></div><div id='lowerCaseContent_other'></div></div>";

		$("#usernameContent").html(divNode);
	}

	var FIELD_TYPE = "&&&&";
	var ROW_TYPE = "####";
	
	//用户显示成功			
	function querySchedulingSucc(jsonTemp) {
		$("#usernameContent").empty();
		sortLowerCase();
		var map = jsonTemp.map;

		var usernames = map.allusername;
		var usernameArr = usernames.split(ROW_TYPE);
		var divNode = "";
		if (usernames == null || '' == usernames) {

		} else {
			for ( var i = 0; i < usernameArr.length; i++) {
				var names = usernameArr[i].split(FIELD_TYPE);
				if ((names[1] >= 'a' && names[1] <= 'z') || (names[1] >= 'A' && names[1] <= 'Z')) {
					$("#lowerCaseTitle_" + names[1]).html(
							"<div class='lowerTitle'><font style='font-size:16px;'>"
									+ names[1].toUpperCase() + "</font></div>");
					$("#lowerCaseContent_" + names[1])
							.append("<div class='lowerContent'><input selectName='"+names[3]+"' selectId='"+names[0]+"' selectUsername='"+names[2]+"' type='checkbox' class='usernameInput' name='everyUsername' /><div class='zhddshowusername'>"+ names[3] + "[" + names[2]+ "]</div></div>");
				} else{
					$("#lowerCaseTitle_other").html(
							"<div class='lowerTitle'><font style='font-size:16px;'>其他</font></div>");
					$("#lowerCaseContent_other")
							.append("<div class='lowerContent'><input selectName='"+names[3]+"' selectId='"+names[0]+"' selectUsername='"+names[2]+"' type='checkbox' class='usernameInput' name='everyUsername' /><div class='zhddshowusername'>"+ names[3] + "[" + names[2]+ "]</div></div>");
				}
			}
		}
		
		
		$('#myModal').modal({
			keyboard: true
		});
	}
	//用户显示失败
	function querySchedulingfail() {
		$("#modalHeadContent").html("<h4 class='modal-title' id='myModalLabel'><center>网络异常，请刷新重试</center></h4>");
		$('#mySmallModal').modal({
			keyboard: true
		});
	}
	
	function clickUsername() {
		var node = $(this).prev();
		node.click();
	}
	

	//打开多媒体文件
	function openUploadFile(id, medId) {
		$("#closeMediaButton").attr("mediaId", medId);
		$(".hiddenMediaDiv").css("display", "none");
		$("#"+id).show();
		
		$('#myMediaModal').modal({
			keyboard: true
		});
	}
	
	var IS_MP4 = "data:video/mp4;base64";
	//提交所有选择的联系人
	$("#closeUsernameButton").bind("click", function(){
		var usernameValue = "";
		var submitValue = "";
		var nodeArr = $("#usernameContent input[name='everyUsername']");
		for(var i=0;i<nodeArr.length;i++){
			if (nodeArr[i].checked == true) {
				usernameValue += nodeArr[i].attributes.selectName.value+"[" + nodeArr[i].attributes.selectUsername.value + "];    ";
				submitValue += nodeArr[i].attributes.selectId.value + FIELD_TYPE + nodeArr[i].attributes.selectName.value + ROW_TYPE;
			}
		}
		$('input[name="addRecipient"]').attr("value", usernameValue.trim());
		$('input[name="addRecipients"]').attr("value", submitValue.substring(0, submitValue.length-4).trim());
	});
	
	$("#closeMediaButton").bind("click", function(){
		var mediaId = $(this).attr("mediaId");
		var text = $("#"+mediaId).attr("src");
		if(text == "" || text == null){
		} else{
			var flag = text.indexOf(IS_MP4);
			if(flag == 0){
				document.getElementById(mediaId).pause();
			} else{}
		}
	});
</script>

	</body>
</html>
