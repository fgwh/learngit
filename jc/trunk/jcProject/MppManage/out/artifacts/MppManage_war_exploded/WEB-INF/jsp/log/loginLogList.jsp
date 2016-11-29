<%--
  Created by IntelliJ IDEA.
  User: Bruce.Zhan
  Date: 2014/7/1
  Time: 10:58
  To change this template use File | Settings | File Templates.
  登陆日志查询
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
    <script type="text/javascript" src="${basePath}/js/quartz/plugins/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${basePath}/js/util.js"></script>
	<script type="text/javascript" src="${basePath}/js/communicate.js"></script>
	<script type="text/javascript" src="${basePath}/js/common.js"></script>
	<script type="text/javascript">
		var log = {};
        $(function() {
            //邦定搜索按钮，添加点击事件查询数据列表
            $("#submit").on("click", function(event) {
            	//验证查询条件合法性
                if (log.checkDate()) {
                    $("#myForm").submit();
                }
                //event.preventDefault();
                //$("#myForm").submit();
                event.preventDefault();
            });

            //按回车键，查询数据列表
            document.onkeydown = function(event) {
                var ev = document.all ? window.event : event;
                if (ev.keyCode == 13) {
                	if (log.checkDate()) {
                    	$("#myForm").submit();
                	}
                    //$("#myForm").submit();
                    ev.preventDefault();
                }
            };
            var roadNo = $("#roadNo").val();
    		var stationId = $("#stationId").val();
    		selectStation(roadNo);
        });
        
        //验证查询条件
        log.checkDate = function() {
            var v_start = stringToDate($("#startQueryDate").val());//new Date($("#startQueryDate").val().replace("-", "/"));
            var v_end = stringToDate($("#endQueryDate").val());//new Date($("#endQueryDate").val().replace("-", "/"));
            if (v_start > v_end) {
                alert("结束时间不能小于起始时间");
                return false;
            }            
            return true;
        };
	</script>
</head>
<body>
<form name="myForm" id="myForm" action="loginLog_list.do" method="post">
    <ul class="breadcrumb">
        <li><i class="icon-home icon-2x"></i></li>
        <li>当前位置：${currentPosition}</li>
    </ul>
    <div class="widget widget-table">
        <div class="widget-content">
            <table class="pn-ftable" border="0" cellpadding="10">
                <tbody>
                <tr>
					<th>中心站 :</th>
					<td class="pn-fcontent"  >
						<%-- <input  type="hidden" id="nameId" value="${adminLogin.roadNo}"/> --%>
						<select name="adminLogin.roadNo" id="roadNo" onchange="selectStation(this.value)"> 
						
								<option value="">全部</option> 
								<c:forEach items="${centerStationList}" var="road">
									<option value="${road.id}"
										<c:if test="${road.id == adminLogin.roadNo}"><c:out value="selected"/></c:if>>
											${road.orgName}</option>
								</c:forEach>
						</select>
					</td> 
					<th>收费站 :</th>
                    <td class="pn-fcontent">
						<select name="adminLogin.stationNo"  id="stationNo" >
							<input  type="hidden" id="stationId" value="${adminLogin.stationNo}"/>
							<%-- <option value="">全部</option> 
							<c:forEach items="${stationList}" var="station">
										<option value="${ station.id }"
											<c:if test="${station.id == stationNo}"><c:out value="selected"/></c:if>>
											${station.orgName}</option>
							</c:forEach> --%>
						</select>        								  
					</td>
                	<th>登陆名:</th>
                    <td class="pn-fcontent"><input name="adminLogin.userName" value="${adminLogin.userName}" size="20" maxlength="20" type="text" /></td>
                </tr>   
                <tr>
                    <th>起始时间 :</th>
                    <td class="pn-fcontent">
                    	<input name="adminLogin.startQueryDate" id="startQueryDate" class="Wdate" style="background-color:white;" value="${adminLogin.startQueryDate}" 
                    	onClick="javascript:WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" readonly="readonly" type="text" />
                    </td>
                    <th>结束时间 :</th>
                    <td class="pn-fcontent">
                    	<input name="adminLogin.endQueryDate" id="endQueryDate" class="Wdate" style="background-color:white;" value="${adminLogin.endQueryDate}" 
                    	onClick="javascript:WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" readonly="readonly" type="text" />
                    </td>
                    <th></th>
                    <td class="pn-fcontent"></td>
                </tr>   
                </tbody>
            </table>
            
        </div>
        <!-- /widget-content -->
        <div class="widget-bottom">
         		<input name="adminLogin.uri" type="hidden" value="${adminLogin.uri}"/>
                <a class="btn btn-s-md btn-primary pull-right" id="submit" href="javascript:;">搜索</a>
            </div>
    </div>
    <div class="separator line"></div>
    <div class="widget widget-table">
        <div class="widget-header">
            <i class="icon-th-list"></i>
            <h5>数据列表</h5>
        </div>
        <!-- /widget-header -->
        <div class="widget-content widget-list">
            <table class="table table-striped table-bordered">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>登陆名</th>
                    <th>登陆时间</th>
                    <th>登陆IP</th>
                    <th>登陆结果</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${list}" var="item" varStatus="status">
                    <tr>
                        <td>${status.count}</td>
                        <td>${item.userName}</td>
                        <td>${item.loginTime}</td>
                        <td>${item.ip}</td>
                        <td>
                        	<c:if test="${item.status==1}">成功</c:if>
                        	<c:if test="${item.status==0}">失败</c:if>
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
