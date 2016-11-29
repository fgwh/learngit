<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>车辆信息</title>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${basePath}/css/plugins/font-awesome/css/font-awesome.min.css" />
    <!--[if IE 7]>
    <link rel="stylesheet" href="${basePath}/css/plugins/font-awesome/css/font-awesome-ie7.min.css" />
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="${basePath}/css/goods.css" />
    <link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
    <link rel="stylesheet" type="text/css" href="${basePath}/theme/transparent/main.css" />
    <script language="javascript" src="${basePath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${basePath}/js/quartz/plugins/My97DatePicker/WdatePicker.js"></script>
    <script language="javascript" src="${basePath}/js/util.js"></script>
    <script src="${basePath}/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/communicate.js"></script>
    <style>
    	.pn-ftable th {width:100px;}
    </style>
</head>
<body style="min-height:900px;">
<form name="myForm" id="myForm" action="carStatistic_list.do" method="post">
   <ul class="breadcrumb">
        <li><i class="icon-home icon-2x"></i></li>
        <li>当前位置：车辆信息查询</li>
    </ul>
    <div class="widget widget-table">
        <div class="widget-content">
           <div class="widget-header">
                <h5>车辆信息查询</h5>
            </div>
	            <table class="pn-ftable" border="0" cellpadding="10" style="table-layout: fixed;">
	                <tbody>
		                <tr>
		                    <th><span class="point">*</span>中心站 :</th>
		                    <td class="pn-fcontent">
		                        <select id="centralStation" name="carStatistic.centralStationId" mandatory='false' >
		                            <option value="">全部</option> 
									<c:forEach items="${centerStationList}" var="centerStation">
										<option value="${centerStation.id}" <c:if test="${centerStation.id==carStatistic.centralStationId}">selected</c:if>>${centerStation.orgName}</option>
									</c:forEach>
		                        </select>
		                    </td>
		                    <th>收费站 :</th>
		                    <td class="pn-fcontent">
		                    	<input type="hidden" value="${carStatistic.stationNo}" id="stationId" />
		                        <select name="carStatistic.stationNo" id="stationNo">
		                            <option value="">全部</option>
		                        </select>
		                    </td>
		                    <th>入口流水 :</th>
		                    <td class="pn-fcontent">
		                        <input type="text" name="carStatistic.laneEnSerialNo" value="${carStatistic.laneEnSerialNo}" />
		                    </td>
		                </tr>
		                <tr>
		                    <th><span class="point">*</span>车牌 :</th>
		                    <td class="pn-fcontent">
		                    	<input type="text" size="7" maxlength="7" name="carStatistic.exVehiclePlate" value="${carStatistic.exVehiclePlate}" /></td>
		                    <th>复合卡表面卡号 :</th>
		                    <td class="pn-fcontent">
		                        <input type="text"  name="carStatistic.exCPCSnNo" value="${carStatistic.exCPCSnNo}" />
		                    </td>
		                    <th>CPU卡号 :</th>
		                    <td class="pn-fcontent">
		                        <input type="text" name="carStatistic.exCPUCardSnNo" value="${carStatistic.exCPUCardSnNo}" />
		                    </td>
		                </tr>
		                <tr>
		                    <th><span class="point">*</span>开始日期:</th>
		                    <td class="pn-fcontent">
		                        <input type="text" name="carStatistic.startTime" class="Wdate" onClick="javascript:WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" readonly="readonly" value="<fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm:ss" value="${carStatistic.startTime}" />" />
		                    </td>
		                    <th><span class="point">*</span>结束日期</th>
		                    <td class="pn-fcontent">
		                        <input type="text" name="carStatistic.endTime" class="Wdate" onClick="javascript:WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" readonly="readonly" value="<fmt:formatDate value="${carStatistic.endTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss" />" />
		                    </td>
		                    <th>出口流水:</th>
		                    <td class="pn-fcontent">
		                        <input type="text"  name="carStatistic.laneExSerialNo" value="${carStatistic.laneExSerialNo}" />
		                    </td>
		                </tr>
		                <tr>
		                    <th><span class="point">*</span>异常情况:</th>
		                    <td class="pn-fcontent">
		                        <a href="javascript:selectDealStatusFunction();" class="btn btn-primary btn-s">选择异常情况</a>
		                        <input type="hidden" name="carStatistic.dealStatusArr" value="${carStatistic.dealStatusArr}" />
		                        <input type="hidden" name="carStatistic.dealStatusStr" value="${carStatistic.dealStatusStr}" />
		                    </td>
		                    <td class="pn-fcontent" colspan="4" id="showDealStatusTd" style="overflow:hidden;white-space: nowrap;text-overflow: ellipsis;" title="${carStatistic.dealStatusStr}" >
	                    	<b>${carStatistic.dealStatusStr}</b> 
	                    </td>
	                	</tr>
	          		</tbody>
	            </table>
	       
        </div>
        <div class="widget-bottom">
            <a 	class="btn btn-s-md btn-primary pull-right" id="submit">搜索</a>
        </div>
    </div>
    <div class="separator line"></div>
    <div class="widget widget-table">  <!-- style="height:580px;overflow-y: auto;" -->
        <div class="widget-header">
            <i class="icon-th-list"></i>
            <h5>数据列表</h5>
        </div>
        <div class="widget-content widget-list widget-scroll">  <!--  style="min-height:500px;" -->
            <table class="table table-striped table-bordered">
                <thead>
                <tr>
                    <th>序号 </th>
                    <th>日期</th>
                    <th>路段</th>
                    <th>车道标号</th>
                    <th>车道类型</th>
                    <th>车牌</th>
                    <th>车型</th>
                    <th>出口收费站</th>
                    <th>入口站</th>
                    <th>通行卡号</th>
                    <th>异常情况</th>
                    <th>CPU卡号</th>
                    <th>入口流水</th>
                    <th>出口流水</th>
                    
                    
                    <th class="hidden">轴组数</th>
                    <th class="hidden">入口时间</th>
                    <th class="hidden">入口车型</th>
                    <th class="hidden">出口识别车牌</th>
                    <th class="hidden">收费员编号</th>
                    <th class="hidden">收费金额</th>
                    
                    <th class="hidden">车种</th> 
                    <th class="hidden">入口路段</th>
                    <th class="hidden">出口路段</th>
                    <th class="hidden">入口入网编号</th>
                    <th class="hidden">出口入网编号</th>
                    <th class="hidden">入口站编号</th>
                    <th class="hidden">出口站编号</th>
                    <th class="hidden">入口车道编号</th>
                    <th class="hidden">出口车道编号</th>
                    <th class="hidden">工班</th>
                    <th class="hidden">入口车道编号</th>
                    <th class="hidden">出口车道编号</th>
                    
                    
                    <th>操作</th>
                </tr>
                </thead>
                <tbody id="databody">
                <c:forEach items="${list}" var="item" varStatus="status">
                <tr>
                    <td>${status.count}</td>
                    <td name="exTimeTd"><fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm:ss" value="${item[0]}" /></td>
                    <td name="exRoadNameTd">${orgMap[fn:trim(item[1])]}</td>
                    <td name="exLaneIdTd">${item[3]}</td>
                    <td name="exLaneNameTd">${laneTypeMap[fn:trim(item[4])]}</td>
                    <td name="exVehiclePlateTd">${item[5]}</td>
                    <td name="exVehicleClassTd">${vehClassMap[fn:trim(item[6])]}</td>
                    <td name="exStationNameTd">${orgMap[fn:trim(item[2])]}</td>
                    <td name="enStationNameId">${item[8]}</td>
                    <td name="exCPCSnNoTd">${item[9]}</td>
                    <td name="dealStatusNameTd" style="overflow:hidden;white-space: nowrap;text-overflow: ellipsis;" title="${fn:replace(item[20], '&&&&', ' ')}">${fn:replace(item[20], '&&&&', ' ')}</td>
                    <td name="exCPUCardSnNoTd">${item[10]}</td>
                    <td name="laneEnSerialNoTd">${item[17]}</td>
                    <td name="laneExSerialNoTd">${item[18]}</td>
                    
                    
                    
                    <td class="hidden" name="axisGroupNumTd">${item[12]}</td>
                    <td class="hidden" name="enTimeTd"><fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm:ss" value="${item[13]}" /></td>
                    <td class="hidden" name="enVehicleClassTd">${vehClassMap[fn:trim(item[14])]}</td>
                    <td class="hidden" name="exVehIdentifyPlateTd">${item[15]}</td>
                    <td class="hidden" name="exOperatorIdTd">${item[16]}</td>
                    <td class="hidden" name="cashMoneyTd">${item[19]}</td>
                    
                    <td class="hidden" name="exVehicleStatusTd">${vehTypeMap[fn:trim(item[21])]}</td>
                    <td class="hidden" name="enRoadIDTd">${item[22]}</td>
                    <th class="hidden" name="exRoadIDTd">${item[23]}</th>
                    <th class="hidden" name="enNetRoadIDTd">${item[24]}</th>
                    <th class="hidden" name="exNetRoadIDTd">${item[25]}</th>
                    <th class="hidden" name="enStationIDTd">${item[26]}</th>
                    <th class="hidden" name="exStationIDTd">${item[27]}</th>
                    <th class="hidden" name="enLaneIDTd">${item[28]}</th>
                    <th class="hidden" name="exLaneIDTd">${item[29]}</th>
                    <th class="hidden" name="squadDateTd"><fmt:formatDate value="${item[30]}" type="both" pattern="yyyy-MM-dd" /></th>
                    <th class="hidden" name="enLaneTypeTd">${item[31]}</th>
                    <th class="hidden" name="exLaneTypeTd">${item[32]}</th>
                   
                    
                    <td>
                        <a href="javascript:;" name="showMoreContent" class="btn btn-info">详情</a>
                    </td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="widget-bottom">
            <jsp:include page="../include/pager.jsp" />
        </div>
    </div>

<div class="modal fade" id="myModal4" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width:960px;">
        <div class="modal-content" >
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only" >关闭</span>
                </button>
                <h4 class="modal-title" id="myModalLabel4">请勾选异常情况(可多项)</h4>
            </div>
            <div class="modal-body" id="dealStatusTable"></div>
            <div class="modal-footer" id="footer4">
                <button type="button" class="btn btn-danger" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="selectDealStatusButton">确定</button>
            </div>
        </div>
    </div>
</div>
</form>

<div class="modal fade" id="myModal5" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width:960px;">
        <div class="modal-content" >
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only" >关闭</span>
                </button>
                <h4 class="modal-title" id="myModalLabel5">车辆信息详情显示</h4>
            </div>
            <div class="modal-body">
                <table cellpadding="1" cellspacing="1" style="border:1px solid #ddd;"  class="pn-ftable dt-table showTable">
                    <tr>
                        <th>收费流水 :</th><td class="motaiTd"><font id="laneExSerialNoFont" class="motaiTdFont"></font></td>
                        <th>收费金额:</th><td class="motaiTd"><font id="cashMoneyFont" class="motaiTdFont"></font></td>
                        <th>收费员 :</th><td class="motaiTd"><font id="exOperatorIdFont" class="motaiTdFont"></font></td>
                    </tr>
                    <tr>
                        <th>出口时间 :</th><td class="motaiTd"><font id="exTimeFont" class="motaiTdFont"></font></td>
                        <th>入口时间 :</th><td class="motaiTd"><font id="enTimeFont" class="motaiTdFont"></font></td>
                        <th>出口收费站 :</th><td class="motaiTd"><font id="exStationNameFont" class="motaiTdFont"></font></td>
                    </tr>
                    <tr>
                        <th>入口收费站:</th><td class="motaiTd"><font id="enStationNameFont" class="motaiTdFont"></font></td>
                        <th>通行卡号:</th><td class="motaiTd"><font id="exCPCSnNoFont" class="motaiTdFont"></font></td>
                        <th>车道类型:</th><td class="motaiTd"><font id="exLaneTypeFont" class="motaiTdFont"></font></td>
                    </tr>
                    <tr>
                        <th>初判车型:</th><td class="motaiTd"><font id="enVehicleClassFont" class="motaiTdFont"></font></td>
                        <th>最终车型:</th><td class="motaiTd"><font id="exVehicleClassFont" class="motaiTdFont"></font></td>
                        <th>轴组组成:</th><td class="motaiTd"><font id="axisGroupNumFont" class="motaiTdFont"></font></td>
                    </tr>
                    <tr>
                        <th>识别车牌:</th><td class="motaiTd"><font id="exVehIdentifyPlateFont" class="motaiTdFont"></font></td>
                        <th>最终车牌:</th><td class="motaiTd"><font id="exVehiclePlateFont" class="motaiTdFont"></font></td>
                        <th>车种:</th><td class="motaiTd"><font id="exVehicleStatusFont" class="motaiTdFont"></font></td>
                    </tr>
                    <tr>
                        <th>特殊事件:</th><td class="motaiTd" colspan="5"><font id="dealStatusNameFont" class="motaiTdFont"></font></td>
                    </tr>
                    <tr style="height:110px;" id="TrShow" >
                        <th>入口车辆照 :</th>
                        <td colspan="5">
                            <img src="/images/more.png" id="enVehicleImage" class="imgFirstClass" />
                        </td>
                    </tr>
                    <tr style="height:110px;" id="hwjlDiv">
                        <th>出口车辆照 :</th>
                        <td colspan="5">
                            <img src="/images/more.png" class="imgFirstClass" id="exVehicleImage" /> 
                        </td>
                    </tr>
                </table>
            </div>
            <div class="modal-footer" id="footer5">
                <button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../include/pictureHuan.jsp">
	<jsp:param value="#myModal5" name="seletor"/>
</jsp:include>

<script src="${basePath}/js/carStatistic.js"></script>
</body>
</html>