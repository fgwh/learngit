<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page contentType="text/html; charset=UTF-8"%>
<!-- saved from url=(0014)about:internet -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link rel="stylesheet" type="text/css" href="${basePath}/css/bootstrap.min.css" />
<link rel="stylesheet" href="${basePath}/css/plugins/font-awesome/css/font-awesome.min.css" />
<!--[if IE 7]>
  <link rel="stylesheet" href="${basePath}/css/plugins/font-awesome/css/font-awesome-ie7.min.css" />
<![endif]-->
<!--[if IE 8]>
<link rel="stylesheet" href="${basePath}/css/ie8.css" type="text/css" />
<![endif]-->
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<!-- <link id="css_main" rel="stylesheet" type="text/css" href="${basePath}/theme/amsterdam/main.css" /> -->
<link id="css_main" rel="stylesheet" type="text/css" href="${basePath}/theme/${session.theme}/main.css" />
<style type="text/css">
    .navbar-header{ overflow: hidden;}
    .fl{ float:left;}
    @font-face {
        font-family: hzgb;
        src: url('${basePath}/css/fonts/logo.TTF');
        font-weight:bold;
        font-style: normal;
    }
    .head_title{
    	font-size:28px;
        font-family:"微软雅黑";
	font-family: hzgb; margin-left:15px; margin-top: 11px; color: #fff;  float: left;
        text-stroke:0.5px #999; 
	-webkit-text-stroke:0.5px #999;
	text-shadow:2px 2px 5px #777;
       -webkit-text-shadow:2px 2px 5px #777;
     }
    </style>
<title>江肇综合防逃费系统管理平台</title>
<script language="javascript" src="${basePath}/js/jquery.min.js"></script>
<script language="javascript" src="${basePath}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${basePath}/js/communicate.js"></script>
</script>
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
  <script src="${basePath}/js/html5shiv.min.js"></script>
  <script src="${basePath}/js/respond.min.js"></script>
<![endif]-->
<script type="text/javascript" language="javascript">
//--------------------------------即时通讯----------------------------------------------

var Chat = {};
Chat.socket = null;
warnMsgType={
	"word":showNavUnreadMsg,
	"photo":showNavUnreadMsg
}

//页面离开关闭连接
window.onUnload = function () {
	//Chat.socket.close();
	//GetSend(uri+"admin/admin_logout.do",null,res,res);
}

//连接websocket
Chat.connect = (function (host) {
	Chat.socket = new WebSocket(host) || new MozWebSocket(host) || new WebSocket(host);
	if (!Chat.socket) {
		alert('你的浏览器不支持WebSocket,请更换浏览器,否则无法接收消息推送与通讯!');
		return;
	}
	Chat.socket.onopen = function () {
		console.log("open");
	};

	Chat.socket.onclose = function () {
		console.log("close");
		setTimeout(function(){
			Chat.connect(uri.replace('http://', 'ws://') + 'chat/' + $("#sponsor").text());
		},5000);
	};
	Chat.socket.onmessage = function (message) {
		if ($("#rightFrame").contents().find("#wsChat").length>0) {//如果即时通讯界面有打开
			$("#rightFrame")[0].contentWindow.msgCome(message);
			return;
		}else{
			var msg = JSON.parse(message.data);
			console.log(msg.messageType);
			//判断消息的类型 如果是视频消息直接弹出对话框
			if(msg.messageType == "video"){
				if(confirm("有实时视频消息来了,是否接收？")){
					$("#video_Contant").attr("style","display:;"); //将视频界面设置为可见
					$("#audio_Contant").attr("style","display:none;"); //将语音界面设置为不可见
					$("#connect").val("video");//修改connect的值为 video
					$("#modal-content").attr("style","width:1500px;margin-left: -450px;");//设置模态框的大小
					$("#modal-footer").attr("style","margin-top:650px;");//设置关闭按钮的位置
					$("#send_Not").attr("style","display:block;");
					
					var revicerID = msg.recipientId;//接受者ID
					var  sendID = msg.sendId;
					$("#caller-id").val(revicerID);
					$("#recipient-id").val(sendID);
					$("#connect").trigger("click");//连接服务器
					$("#other_Men").text(msg.sender);//设置对方的名字
					$("#isI").text(msg.recipientor);  //设置我的名字
					$("#audioChatModal").modal({
							keyboard: true
						});
				}else{//不接受视频
					return;
				}
			}if(msg.messageType == "audios"){
				if(confirm("有实时语音来了,是否接收？")){
					$("#audio_Contant").attr("style","display:block;"); //将语音界面设置为可见
					$("#video_Contant").attr("style","display:none;"); //将视频界面设置为不可见
					$("#connect").val("audio");//修改connect的值为 audio
					$("#modal-content").attr("style","height:550px;width:320px;");//设置模态框的大小
					$("#modal-footer").attr("style","margin-top:349px;");//设置关闭按钮的位置
					$("#send_Not").attr("style","display:block;");
					
					var revicerID = msg.recipientId;//接受者ID
					var  sendID = msg.sendId;
					$("#caller-id").val(revicerID);
					$("#recipient-id").val(sendID);
					$("#connect").trigger("click");//连接服务器
					$("#other_Men").text(msg.sender);//设置我的名字
					$("#isI").text(msg.recipientor);  //设置对方的名字
					$('#audioChatModal').modal({
							keyboard: true
						});
				}else{
					return;
				}
			}else{
				warnMsgType[msg.messageType]();
			}
		}
	};
});

//初始化连接
Chat.initialize = function () {
	if (!Chat.socket) {
		Chat.connect(uri.replace('http://', 'ws://') + 'chat/' + '${session.operator.id}');
	}
}
//关闭新消息提示
function hideNavUnreadMsg() {
	$("#msgWarn").addClass("hide");
	$("#homeUnreadMsgNum").show().text(localStorage.getItem("unreadMsgNum"));
	//localStorage.setItem("unreadMsgNum",0);
}
function showNavUnreadMsg(){//导航栏显示有新消息
	$("#msgWarn").removeClass("hide");
	var msgNum = parseInt(localStorage.getItem("unreadMsgNum"))+1;
	$("#homeUnreadMsgNum").show().text(msgNum);
	$("audio").attr("src","../music/MSG.wav");//播放提示音
	localStorage.setItem("unreadMsgNum",parseInt(localStorage.getItem("unreadMsgNum"))+1);
}

$(document).ready(function(){
	/*
	//未读消息数量查询
	var url = uri + '/admin/msg_queryIsReadCount.do';
	var params = {};
	GetSend(url, params, queryIsReadCountSucc, queryIsReadCountfail);
*/

	/*
	var unreadMsgNum = localStorage.getItem("unreadMsgNum");
	if(unreadMsgNum != null && unreadMsgNum !="" && unreadMsgNum>0){
		$("#msgWarn span").text(unreadMsgNum);
		$("#msgWarn").removeClass("hide");
	}*/
	
});

function queryIsReadCountSucc(jsonTemp){
	var map = jsonTemp.map;
	var count = map.isReadCount;
	
	localStorage.setItem("unreadMsgNum",parseInt(count));
	$("#msgWarn span").text(count);
	if(count == '0'){
		$("#msgWarn").addClass("hide");
	} else{
		$("#msgWarn").removeClass("hide");
	}
}

function queryIsReadCountfail(){
	//alert("失败了....");
}
//--------------------------------即时通讯END-----------------------------------------
	/* $(window).resize(function() {
		 var width = $(this).width() - 200;
		 if(width < 768) width = 768;
		 $(".page-content").css("width",(width-10)+"px");
	});
 */
	function setIFrameHeight() {
		var mainheight = $("#rightFrame").contents().find("body").height();//attr('scrollHeight')
		if (mainheight <= 580) {
			mainheight = 580;
		}
		$("#rightFrame").height(mainheight);
	}
	
	function setTheme(theme){
		var href = "${basePath}/theme/" + theme + "/main.css";
		$("#css_main").attr("href", href);
		$("#rightFrame").contents().find("#css_main").attr("href", href);
		$.get("admin_saveTheme.do",{theme: theme},function(data){});
		$(".theme-menu").hide();
	}
	function saveTheme(){
		$.get("admin_saveTheme.do",{theme: $('.set-theme').val()},function(data){
			if (data != null) {
				alert("保存成功");
			}
		});
	}
	
	function splitter(img){
		var side = "right";
		if($(".sidebar").css("left") == "0px"){
			$(".sidebar").animate({left:"-201px"},500);
			$(".page-content").animate({left: "0px",width: "96%"},500);
		}
		else{
			$(".sidebar").animate({left:"0px"},500);
			$(".page-content").animate({left: "200px",width: "85%"},500);
			side = "left";
		}
		img.attr("src","${basePath}/images/mini-" + side + ".gif");
	}

</script>
<script type="text/javascript" src="${basePath}/js/toTop.js"></script>
<script>
	$(function() {
		$('#dLabel').click(function() {
			$('#mask').css({
				display : 'block'
			});
			$('#quit').css({
				display : 'block'
			});
		});
		$('.quit_close').click(function() {
			$('#mask').css({
				display : 'none'
			});
			$('#quit').css({
				display : 'none'
			});
		})
	});
</script>
</head>
<body  class="menu-left full-container" theme="${session.theme}" onload="${session.chatOpen}"> 
	<div style="display:none;">
				<div id="sponsor">${session.operator.id}</div>
				<div id="sponsorName">${session.operator.name}</div>
	</div>
	<audio src="" autoplay="true"></audio>
	<div class="navbar navbar-static-top" role="navigation">
	  <div class="container">
	  	<jsp:include page="top.jsp" />
	  </div>
	</div>
	<div class="container relative main">
       <div class="sidebar">
       <%-- <img src="${basePath}/images/mini-left.gif" class="splitter" onclick="splitter($(this))" style="position:absolute; right:-11px; top:287px; cursor:pointer;" />
		 --%>	
		 <jsp:include page="left.jsp" />
		</div>
       <div class="page-content" >
			<div class="content container" style="padding-left:0px;">
				<div class="row">
					<div class="col-lg-12">
						<iframe id="rightFrame" allowTransparency="true" name="rightFrame" frameborder="0"  onLoad="setIFrameHeight()" width="99%"scrolling="no" src="${basePath }/admin/msgPublish_homeList.do"></iframe>
					</div>
				</div>
			</div>
	   </div>
  </div>
  <!-- 视频模态框 -->
   
</body>

</html>