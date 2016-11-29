<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title></title>
    <link rel="stylesheet" type="text/css"	href="${basePath}/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="${basePath}/css/jquery-ui.min.css" />
    <link rel="stylesheet"	href="${basePath}/css/plugins/font-awesome/css/font-awesome.min.css" />
    <link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
    <link rel="stylesheet" type="text/css" href="${basePath}/css/qqFace.css"/>
    <link rel="stylesheet" type="text/css" href="${basePath}/theme/${session.theme}/main.css" />
    <link rel="stylesheet" type="text/css" href="${basePath}/css/singlechat.css" />

    <script language="javascript" src="${basePath}/js/jquery-1.7.1.min.js"></script>
    <script language="javascript" src="${basePath}/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${basePath}/js/jquery.qqFace.min.js"></script>
    <script type="text/javascript" src="${basePath}/js/communicate.js"></script>
    
    <%--<script language="javascript" src="${basePath}/js/json2.js"></script>--%>
    <script language="javascript" src="${basePath}/js/MD5.js"></script>
    <script type="text/javascript" src="${basePath}/js/main.js"></script>
    <script type="text/javascript" src="${basePath}/js/peer.js"></script>
    <script type="text/javascript" src="${basePath}/js/chatAction.js"></script>
	<script language="javascript" src="${basePath}/js/singleChat.js"></script>
	<script language="javascript" src="${basePath}/js/groupChat.js"></script>
    
    <style type="text/css">
    </style>
</head>
<body id="wsChat">
<form name="myForm" id="myForm" action="single_listMsg.do" method="post">
    <ul class="breadcrumb">
        <li><i class="icon-home icon-2x"></i>
        </li>
        <li>当前位置：${currentPosition}</li>
    </ul>
    <div class="separator line"></div>
</form>
<div class=".container-fluid">
    <div style="display:none;">
        <div id="sponsor">${session.operator.id}</div>
        <div id="sponsorName">${session.operator.name}</div>
    </div>
    <div class="row" >
        <div class="col-md-3 panel panel-body col-cont">
            <table>
                <tr>
                    <th><img src="../images/headera.png"></th>
                    <th><h3>${session.operator.name}</h3></th>
                </tr>
            </table>
            <div id="singleOrGroupTab" class="btn-group btn-group-justified">
                <div class="btn-group " role="group">
                    <button type="button" id="displaySingle" name="singleOrGroupTab" class="btn btn-default active">单人聊天</button>
                </div>
                <div class="btn-group" role="group">
                    <button type="button" id="displayGroup" name="singleOrGroupTab" class="btn btn-default">多人聊天</button>
                </div>
            </div>
            <div>
                <input type="text" class="searchUser" id="searchUserInput" placeholder="查找" required/><a class="icon-remove clear" onclick="clearInput();" href="#"></a>
            </div>
			<div id="createGroupDiv" style="margin-left:65%; display:none;"><a href="#" id="createGroup"><font style="font-size:14px; font-weight:bloder;">创建讨论组</font></a></div><!--  style="display:none;" -->
            <!--单人聊天-->
            <div id="userList">

            </div>
			
			<!-- 聊天人员的选择 -->
			<div id="selectUserList">

			</div>

            <!--多人聊天-->
            <div id="groupList" style="display: none; overflow-y: auto; overflow-x: hidden; height: 470px!important;">
				<a class="btn btn-block foldingbar" data-toggle="collapse" href="#我的群聊组" aria-expanded="false"><div class="arrow icon-chevron-down"></div>我的群聊组&nbsp;&nbsp;<span id="groupCount"></span></a>
				<div class="in" id="我的群聊组">
                <ul class="list-group" id="groupListUl">
                    <li ><a name="group" class='list-group-item' href="#"><img src='../images/headera.png'/><span
                            class="online">AAAAA聊天组</span></a><a href="#"><div  class="icon-trash trash"></div></a></li>
                    <li ><a name="group" class='list-group-item' href="#"><img src='../images/headera.png'/><span
                            class="online">AAAAA聊天组</span></a><a href="#"><div  class="icon-trash trash"></div></a></li>
                    <li ><a name="group" class='list-group-item' href="#"><img src='../images/headera.png'/><span
                            class="online">AAAAA聊天组</span></a><a href="#"><div  class="icon-trash trash"></div></a></li>
                </ul>
				</div>
            </div>
        </div>



        <div id="mainContain" class="col-md-8 col-cont" style="margin: 0px 0px 0px 10px;display: none">
            <div class="console-header" >
                <a id="targeName" href="#" style="font-size: 30px">张三</a>
				<a id="targeId" href="#" style="display:none;"></a>
                <a id ="colsewindow" class="pull-right icon-remove" href="#"></a>
            </div>
            <div id="moreInfo" pager="1"><a href="#" id="checkMoreInfo" onclick="listChatRecord();">查看更多消息</a></div>
            <div id="console-container">
                <div id="msg-container" style="width: 100%">
                    <ul class="message-window" id="ulMsg">

                    </ul>
                </div>

                <div id="menberList" class="menberList">
                    <div class="groupName"><Strong>组成员</Strong></div>
                    <div style="overflow-y:auto;height: 365px">
                        <ul id="memberli">
                            <li ><a class='list-group-item menber' href="#"><img src='../images/headera.png' /><span class="online">张三三三</span></a></li>
                            <li><a class='list-group-item menber' href="#"><img src='../images/headera.png' /><span class="online">张三三三</span></a></li>
                        </ul>
                    </div>
                </div>
            </div>

            <div>
                <ul>
              	  
                	<form id="fileUpload"  method="post" enctype="multipart/form-data"  >
                    	<li class="col-2" id="singlePhoto" >
                    	<input type="file"  id="selectPhoto" name="selectPhoto" filename="selectPhoto" value="" accept="image/*" style="display:none" />
                 	    <a href="#" class="btn btn-success pull-left"><span class="icon-picture"></span></a> </li>
                    </form>
                    	
                    <li class="col-2" id="videoChat"><a  class="btn btn-success pull-left" ><span class="icon-facetime-video"></span></a></li>
                    <li class="col-2" id="audioChat"><a href="javascript:audioChat();" class="btn btn-success pull-left" ><span class="icon-microphone"></span></a></li>
                    <li class="col-2"><a href="#" id="face1" class="pull-left faceBtn"></a></li>
                </ul>
            </div>
            <div>
                <textarea id="chatContent" cols="12" rows="5"	placeholder="请输入..." maxLength="250"></textarea>
            </div>
            <div>
                <ul>
                    <li class="col-2" id="singleContent"><a class="btn btn-success pull-right toolBtn">发送</a></li>
                    <li class="col-2" id="closeWin"><a class="btn btn-success pull-right toolBtn">关闭</a></li>
                </ul>
            </div>
        </div>
    </div>

</div>

<!--模态窗口-->

<!-- 照片放大模态窗口 -->
<div class="modal fade bs-example-modal-lg" id="imgModal"  role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content" style="width:auto;height:auto;" >
            <div class="modal-body"  >
                <img id="modalSrc1" style="text-align:center;max-width: 560px;"  src=""/>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<!-- 视频模态框 -->
<div class="modal fade" id="audioChatModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content" id="modal-content" style="width: 1500px;margin-left: -450px;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" onclick="javascript:window.location.reload();" >
						<span aria-hidden="true" style="color: #68a341;">&times;</span><span class="sr-only">关闭</span>
					</button>
				</div>
				<div class="modal-body">
					<div id="credentials">
                	<font style="color:#68a341;font-size:15px;">温馨提示：</font><font style="font-size: 15px;color:red;">请先打开浏览器摄像头/语音</font>
				      <p style="display:none;">
				        Connect as:
				        <input type="text" id="caller-id" size="15">
				        <button id="connect" value="">Connect</button>
				      </p>
			  </div>
				
			  <div id="dialler" data-active="false" style="height: 20px;">
				    <div id="send_Not">
				        <input type="select" style="display:none;" id="recipient-id">
				        </input>
				       <font style="font-size:14px;">对方邀请你进行视频通话 ....</font><button id="dial" style = "background-color: #68a341; color: #fff;border: 1px #68a341 solid;font-size: 13px;width: 39px;height: 28px;" class="btn btn-primary">是</button>  <button type="button" class="btn btn-primary" data-dismiss="modal">否</button>
				    </div>
				
				      <hr>
				  <div id="video_Contant" style="display:none;"><!-- 视频界面 -->
				  	<div style="width: 50%;padding-left: 1px;float: left;">
				      <p id="other_Men"><strong >那是你</strong></p>
				      <video  id="remote-video" autoplay></video>
					</div>
					<div style="width: 50%;float: left;">
				      <p id="isI" ><strong >这是我</strong></p><!-- style="float: left;margin-left: 780px;margin-top: -450px;margin-top: -570px;" -->
				     	 <video  id="local-video" autoplay></video>
				 	</div>
				 </div>
				
				<div id="audio_Contant" style="display:none;"><!-- 语音界面 -->
					<audio id="local_audio" src="" autoplay></audio>
					<audio id="remote_audio" src="" autoplay></audio>
					<img src="../images/1.gif" />
					<div style="margin-top: 50px;"><font style="font-size:19px;color: #68a341;">正在进行实时语音通话。。。。</font></div>
				</div>
				    <hr>

				    <div id="messages" style="display:none;">
				    </div>
				</div>
				<div class="modal-footer" style="margin-top:650px;" id="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal" id="close_Stream" >关闭</button>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- Modal 创建群的模态窗口 -->
<div class="modal fade" id="myUserModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" style="margin-top: -30px;"><span aria-hidden="true"><font style="font-size:50px;">&times;</font></span></button->
      </div>
      <div class="modal-body" id="userModalBody" style="height:500px;">
		<div style="width:46%; float:left;">
			<div>
				<input style="height:36px; width:80%;" id="searchUserInputModal" class="searchUser" type="text" value="" placeholder="请输入查找关键字" required/>
				<a class="icon-remove clear" style="right:62%; margin-top:6px; cursor: pointer;" onclick="clearSearchUserInputModal();"></a>
			</div>
			<div style="height:20px;"></div>
			<div id="userListModal" style="width:80%; height:404px; overflow-y:auto; overflow-x:hidden;">
				<!-- 用户列表 -->
			</div>

			<!-- 创建群组中人员查询-->
			<div id="selectUserListModal" style="display:none; width:80%; height:404px; overflow-y:auto; overflow-x:hidden;">

			</div>
		</div>
		<div style="width:8%; height:100%; background-color:gray; float:left;"></div>
       	<div id="bodyTitle" style="width:46%; float:right;">
			<div>&nbsp;&nbsp;&nbsp;讨论组名：<input style="height:36px; width:70%;" id="groupNameInput" type="text" placeholder="请输入您创建的讨论组的名字" value="" ></div>
			<div style="height:20px; width:100%;"><div style="float:right; margin-right:3%;"><font style="color:red;" id="errorGroupName"></font></div></div>
			<div>
				<div>&nbsp;&nbsp;&nbsp;已选联系人：&nbsp;&nbsp;<font id="nowUserCount">1</font>/<font id="userCount">50</font></div>
			</div>
			<div id="selectedUserDiv" style="width:80%; height:382px; overflow-y:auto; overflow-x:hidden;  margin-left:18%; margin-top:5px;"> <!-- border:1px solid red; -->
				<ul>
					<li id="selectedUserLi" un="${session.operator.name}" as="${session.operator.username}" uid="${session.operator.id}">
						<a class="list-group-item" href="#"><img src="../images/headera.png"><span>${session.operator.name}</span></a>
					</li>
				</ul>
			</div>
		</div>
      </div>
	  <div class="modal-footer">
		<button type="button" class="btn btn-primary" id="submitGroup">确定</button>
        <button type="button" class="btn btn-default" data-dismiss="modal" id="closeMyUserModal">关闭</button>  
      </div>
    </div>
  </div>
</div>
<!-- Modal -->


<!-- smallModal 接受和关闭所需要操作的功能 -->
		<div class="modal fade bs-example-modal-sm" tabindex="-1" id="myChatSmallModalLabel"
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
					<div style="width: 250px; height: 60px;">
						<div style="width: 83px; height: 60px; float: left;">
							<button type="button"
								class="btn btn-danger" data-dismiss="modal"
								style="margin-left: 30px; margin-top: 20px;">
								取消
							</button>
						</div>
						<div style="width: 83px; height: 60px; float: left;">
							<button id="exitGroupButton" type="button" exitGroupId="" exitUserId=""
								class="btn btn-primary" data-dismiss="modal"
								style="margin-left: 80px; margin-top: 20px;">
								确定
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- smallModal -->

</body>

</html>