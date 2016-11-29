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
    <title>可疑车辆分析</title>
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
    <script src="${basePath}/js/AbnorCarList.js"></script>
    <link href="${basePath}/js/load/showLoading.css" rel="stylesheet" media="screen" /> 
    <script src="${basePath}/js/load/jquery.showLoading.min.js"></script>
    <script src="${basePath}/js/jquery.form.js"></script>
    <style>
        .pn-ftable.showTable tr th {text-align: right;padding-right: 10px;}
       table.yccl-table tbody tr,table.ycls-table tbody tr{cursor: pointer;}
        table.lsxx-table tbody tr th{width:9%;}
        .carLabel span{padding-right: 5px;}
        .configurationLine label{width:15%;cursor: pointer;}
        #myModal1 #Abnorlabel span a{margin-bottom:5px;color:#000;}
    </style>
</head>
<body style="min-height:900px;">
<form name="myForm" id="myForm" action="abnorCar_getAbnorCar.do" method="post">
    <ul class="breadcrumb">
        <li><i class="icon-home icon-2x"></i></li>
        <li>当前位置：可疑车辆分析</li>
    </ul>
    <div class="widget widget-table">
        <div class="widget-content">
            <table class="pn-ftable" border="0" cellpadding="10">
                <tbody>
                    <tr>
                        <th><span class="point">*</span>车牌号码</th>
                        <td class="pn-fcontent">
                            <select class="pull-left" name="conditions.plateNum" title="车牌号" id="plateNum" mandatory='false' style="width:30% !important;">
                                <option value="">全部</option>
                         <option value="粤" <c:if test="${conditions.plateNum=='粤'}">selected</c:if>>粤</option>
                        <option value="津" <c:if test="${conditions.plateNum=='津'}">selected</c:if>>津</option>
                        <option value="沪" <c:if test="${conditions.plateNum=='沪'}">selected</c:if>>沪</option>
                        <option value="渝" <c:if test="${conditions.plateNum=='渝'}">selected</c:if>>渝</option>
                        <option value="京" <c:if test="${conditions.plateNum=='京'}">selected</c:if>>京</option>
                        <option value="冀" <c:if test="${conditions.plateNum=='冀'}">selected</c:if>>冀</option>
                        <option value="豫" <c:if test="${conditions.plateNum=='豫'}">selected</c:if>>豫</option>
                        <option value="云" <c:if test="${conditions.plateNum=='云'}">selected</c:if>>云</option>
                        <option value="辽" <c:if test="${conditions.plateNum=='辽'}">selected</c:if>>辽</option>
                        <option value="黑" <c:if test="${conditions.plateNum=='黑'}">selected</c:if>>黑</option>
                        <option value="湘" <c:if test="${conditions.plateNum=='湘'}">selected</c:if>>湘</option>
                        <option value="皖" <c:if test="${conditions.plateNum=='皖'}">selected</c:if>>皖</option>
                        <option value="鲁" <c:if test="${conditions.plateNum=='鲁'}">selected</c:if>>鲁</option>
                        <option value="新" <c:if test="${conditions.plateNum=='新'}">selected</c:if>>新</option>
                        <option value="苏" <c:if test="${conditions.plateNum=='苏'}">selected</c:if>>苏</option>
                        <option value="浙" <c:if test="${conditions.plateNum=='浙'}">selected</c:if>>浙</option>
                        <option value="赣" <c:if test="${conditions.plateNum=='赣'}">selected</c:if>>赣</option>
                        <option value="鄂" <c:if test="${conditions.plateNum=='鄂'}">selected</c:if>>鄂</option>
                        <option value="桂" <c:if test="${conditions.plateNum=='桂'}">selected</c:if>>桂</option>
                        <option value="甘" <c:if test="${conditions.plateNum=='甘'}">selected</c:if>>甘</option>
                        <option value="晋" <c:if test="${conditions.plateNum=='晋'}">selected</c:if>>晋</option>
                        <option value="蒙" <c:if test="${conditions.plateNum=='蒙'}">selected</c:if>>蒙</option>
                        <option value="陕" <c:if test="${conditions.plateNum=='陕'}">selected</c:if>>陕</option>
                        <option value="吉" <c:if test="${conditions.plateNum=='吉'}">selected</c:if>>吉</option>
                        <option value="闽" <c:if test="${conditions.plateNum=='闽'}">selected</c:if>>闽</option>
                        <option value="贵" <c:if test="${conditions.plateNum=='贵'}">selected</c:if>>贵</option>
                        <option value="青" <c:if test="${conditions.plateNum=='青'}">selected</c:if>>青</option>
                        <option value="藏" <c:if test="${conditions.plateNum=='藏'}">selected</c:if>>藏</option>
                        <option value="川" <c:if test="${conditions.plateNum=='川'}">selected</c:if>>川</option>
                        <option value="宁" <c:if test="${conditions.plateNum=='宁'}">selected</c:if>>宁</option>
                        <option value="琼" <c:if test="${conditions.plateNum=='琼'}">selected</c:if>>琼</option>
                        <option value="台" <c:if test="${conditions.plateNum=='台'}">selected</c:if>>台</option>
                        <option value="港" <c:if test="${conditions.plateNum=='港'}">selected</c:if>>港</option>
                        <option value="澳" <c:if test="${conditions.plateNum=='澳'}">selected</c:if>>澳</option>
                            </select>
                            <input type="text" maxlength="6" name="conditions.carNo" id="carNo" value="${conditions.carNo}"    mandatory='false'title="车牌" style="width:50%!important "/>
                        </td>
                 
                        <th><span class="point">*</span>稽查状态 :</th>
                        <td class="pn-fcontent">
                            <select id="auditStatus" name="conditions.auditStatus">
                                <option value="0"   <c:if test="${conditions.auditStatus=='0' }">selected</c:if>>全部</option>
                                <option value="1" <c:if test="${conditions.auditStatus=='1' }">selected</c:if>>未稽查</option>
                                  <option value="2" <c:if test="${conditions.auditStatus=='2' }">selected</c:if>>已稽查</option>
                            </select>
                        </td>
                        <th></th>
                        <td></td>
                    </tr>
                    <tr>
                        <th><span class="point">*</span>异常显示类型 :</th>
                        <td class="pn-fcontent">
                            <input onclick="" type="radio" name="conditions.excepDisplayType" style="font-size: 14px;" value="1" id="car"   <c:if test="${conditions.excepDisplayType=='1' }">checked</c:if> /><label for="car">客车</label>
                            <input onclick="" type="radio" name="conditions.excepDisplayType" style="margin-left: 30px; font-size: 14px;" value="2" id="truck"  <c:if test="${conditions.excepDisplayType=='2' }">checked</c:if>/><label for="truck">货车</label>
                        </td> 
                        <th><span class="point">*</span>组合条件 :</th>
                        <td class="pn-fcontent" colspan="3">
                            <a  class="btn btn-primary btn-s" data-toggle="modal"  onclick="showModal5()"     data-keyboard="false" data-backdrop="static" tabindex="-1" >组合条件</a>
                        </td>
                    </tr>
                </tbody>
            </table>

        </div>
        <div class="widget-bottom">
            <a 	class="btn btn-s-md btn-primary pull-right" id=""  onclick="searchList()">查询</a>
        </div>
    </div>
    <div class="separator line"></div>
    <div class="widget widget-table">  <!-- style="height:580px;overflow-y: auto;" -->
        <div class="widget-header">
            <i class="icon-th-list"></i>
            <h5>异常车辆信息列表</h5>
        </div>
        <div class="widget-content widget-list widget-scroll">  <!--  style="min-height:500px;" -->
            <table class="table table-striped table-bordered yccl-table">
              <input type="hidden" id="operaterName"  value="${session.operator.name}"></input>
                <thead>
                <tr>
                    <th>车牌</th>
                    <th>通行次数</th>
                    <th>倒卡</th>
                    <th>有收费无标识</th>
                    <th>标识点异常</th>
                    <th>ETC车牌不符</th>
                    <th>ETC车型不符</th>
                    <th>轴组异常</th>
                    <th>历史性逃费</th>
                    <th>稽查状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
           <c:forEach items="${list}" var="abnor" varStatus="abnornum">
                    <tr ondblclick="showLS('${abnornum.count }',
                            '${abnor[1]}','${abnor[3]}','${abnor[9]}','${abnor[11]}','${abnor[7]}','${abnor[5]}','${abnor[13]}','${abnor[2]}')">
                        <td>${abnor[1] }</td>
                        <td>${abnor[0]} </td>
                        <td>${abnor[3] }</td>
                        <td>${abnor[9] }</td>
                        <td>${abnor[11] }</td>
                        <td>${abnor[7] }</td>
                        <td>${abnor[5] }</td>
                        <td>${abnor[13] }</td>
                        <td>
                            <c:choose>
                                <c:when test="${abnor[15]==0 }">
                                  无
                                </c:when>
                                <c:when test="${abnor[15]!=0 }">
                                  有
                                </c:when>
                                <c:otherwise>
                                </c:otherwise>
                            </c:choose>

                        </td>
                        <td>
                          <c:choose>
							<c:when test="${abnor[2]==1 }">
                                未稽查
							</c:when>
							<c:when test="${abnor[2]==2 }">
                               已稽查
							</c:when>
							<c:otherwise>
							</c:otherwise>
						</c:choose>
                        </td>
                        <td><button type="button" value="${abnornum.count }"     class="btn btn-primary"  onclick="showLS('${abnornum.count }',
                        '${abnor[1]}','${abnor[3]}','${abnor[9]}','${abnor[11]}','${abnor[7]}','${abnor[5]}','${abnor[13]}','${abnor[2]}')">详情</button></td>
                    </tr >
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <!--底部分页栏-->
        <div class="widget-bottom">
            <jsp:include page="../include/pager.jsp" />
        </div>
    </div>


<!--组合条件界面-->
<div class="modal fade" id="myModal5" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width:960px;">
        <div class="modal-content" >
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only" >关闭</span>
                </button>
                <h4 class="modal-title" id="myModalLabel5"><span></span>组合条件</h4>
            </div>
            <div class="modal-body">
                <table class="pn-ftable" border="0" cellpadding="10" style="table-layout: fixed;" id="zhtj">
                    <tr>
                        <th>
                            <label><input name="conditions.switchCard"    type="checkbox" value="0" <c:if test="${conditions.switchCard=='0' }">checked</c:if> resert="" />倒卡 </label>
                        </th>
                        <td class="pn-fcontent">
                            <label>异常次数 </label>&nbsp;>=&nbsp;<input type="text" name="conditions.switchCardNum"  value="${empty  conditions.switchCardNum? 1: conditions.switchCardNum}"  style="width:70px;margin-right:10%;" number=""  maxlength="5"/>
                            <label>近期异常次数 </label>&nbsp;>=&nbsp;<input type="text" name="conditions.switchCardNum2" value="${conditions.switchCardNum2 }" style="width:70px;margin-right:10%;" resert="" number="" maxlength="5"/>
                            <label>异常占比 </label>&nbsp;>=&nbsp;<input type="text" name="conditions.switchCardPercent" value="${conditions.switchCardPercent }" style="width:70px;"  resert="" number="" maxlength="5"/>&nbsp;&#37;
                        </td>
                    </tr>
                    <tr   id="zhouzu">
                        <th>
                            <label><input name="conditions.axisGroupExcep" type="checkbox" value="0" <c:if test="${conditions.axisGroupExcep=='0' }">checked</c:if> resert="" />轴组异常 </label>
                        </th>
                        <td class="pn-fcontent">
                            <label>异常次数 </label>&nbsp;>=&nbsp;<input type="text" name="conditions.axisGroupExcepNum" value="${empty conditions.axisGroupExcepNum ? 1:conditions.axisGroupExcepNum}" style="width:70px;margin-right:10%;" number="" maxlength="5"/>
                            <label>近期异常次数 </label>&nbsp;>=&nbsp;<input type="text" name="conditions.axisGroupExcepNum2" value="${conditions.axisGroupExcepNum2 }" style="width:70px;margin-right:10%;" resert=""  number="" maxlength="5"/>
                            <label>异常占比 </label>&nbsp;>=&nbsp;<input type="text" name="conditions.axisGroupExcepPercent" value="${conditions.axisGroupExcepPercent}" style="width:70px;" resert=""  number=""  maxlength="5"/>&nbsp;&#37;
                        </td>
                    </tr>
                   
                    <tr>
                        <th>
                            <label><input name="conditions.chargesNotIndetify" type="checkbox" value="0" <c:if test="${conditions.chargesNotIndetify=='0' }">checked</c:if> resert="" />有收费无标识 </label>
                        </th>
                        <td class="pn-fcontent">
                            <label>异常次数 </label>&nbsp;>=&nbsp;<input type="text" name="conditions.chargesNotIndetifyNum" value="${empty conditions.chargesNotIndetifyNum ? 1:conditions.chargesNotIndetifyNum}" style="width:70px;margin-right:10%;" number="" maxlength="5"/>
                            <label>近期异常次数 </label>&nbsp;>=&nbsp;<input type="text" name="conditions.chargesNotIndetifyNum2" value="${conditions.chargesNotIndetifyNum2 }" style="width:70px;margin-right:10%;" resert="" number="" maxlength="5"/>
                            <label>异常占比 </label>&nbsp;>=&nbsp;<input type="text" name="conditions.chargesNotIndetifyPercent" value="${conditions.chargesNotIndetifyPercent }" style="width:70px;" resert=""  number="" maxlength="5"/>&nbsp;&#37;
                        </td>
                    </tr>
                    <tr  id="etcModal">
                        <th>
                            <label><input name="conditions.etcModelsNotMeet" type="checkbox" value="0" <c:if test="${conditions.etcModelsNotMeet=='0' }">checked</c:if> resert="" />ETC出入口车型不符 </label>
                        </th>
                        <td class="pn-fcontent">
                            <label>异常次数 </label>&nbsp;>=&nbsp;<input type="text" name="conditions.etcModelsNotMeetNum"  value="${empty conditions.etcModelsNotMeetNum ? 1:conditions.etcModelsNotMeetNum }" style="width:70px;margin-right:10%;" number="" maxlength="5"/>
                            <label>近期异常次数 </label>&nbsp;>=&nbsp;<input type="text" name="conditions.etcModelsNotMeetNum2"  value="${conditions.etcModelsNotMeetNum2 }" style="width:70px;margin-right:10%;" resert="" number="" maxlength="5"/>
                            <label>异常占比 </label>&nbsp;>=&nbsp;<input type="text" name="conditions.etcModelsNotMeetPercent"  value="${conditions.etcModelsNotMeetPercent }" style="width:70px;" resert=""  number="" maxlength="5"/>&nbsp;&#37;
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label><input name="conditions.indentifyPointExcep" type="checkbox" value="0" <c:if test="${conditions.indentifyPointExcep=='0' }">checked</c:if> resert="" />标识点异常 </label>
                        </th>
                        <td class="pn-fcontent">
                            <label>异常次数 </label>&nbsp;>=&nbsp;<input type="text" name="conditions.indentifyPointExcepNum"  value="${empty conditions.indentifyPointExcepNum ? 1:conditions.indentifyPointExcepNum }" style="width:70px;margin-right:10%;" number="" maxlength="5"/>
                            <label>近期异常次数 </label>&nbsp;>=&nbsp;<input type="text" name="conditions.indentifyPointExcepNum2"  value="${conditions.indentifyPointExcepNum2 }" style="width:70px;margin-right:10%;" resert=""  number="" maxlength="5"/>
                            <label>异常占比 </label>&nbsp;>=&nbsp;<input type="text" name="conditions.indentifyPointExcepPercent"  value="${ conditions.indentifyPointExcepPercent}" style="width:70px;" resert="" number="" maxlength="5"/>&nbsp;&#37;
                        </td>
                    </tr>
                    <tr id="etcPlate">
                        <th>
                            <label><input name="conditions.etcPlateNotMeet" type="checkbox" value="0" <c:if test="${conditions.etcPlateNotMeet=='0' }">checked</c:if> resert="" />ETC车牌不符 </label>
                        </th>
                        <td class="pn-fcontent">
                            <label>异常次数 </label>&nbsp;>=&nbsp;<input type="text"  name="conditions.etcPlateNotMeetNum" value="${empty conditions.etcPlateNotMeetNum? 1:conditions.etcPlateNotMeetNum }" style="width:70px;margin-right:10%;" number="" maxlength="5"/>
                            <label>近期异常次数 </label>&nbsp;>=&nbsp;<input type="text"  name="conditions.etcPlateNotMeetNum2" value="${conditions.etcPlateNotMeetNum2 }" style="width:70px;margin-right:10%;" resert="" number=""  maxlength="5"/>
                            <label>异常占比 </label>&nbsp;>=&nbsp;<input type="text"  name="conditions.etcPlateNotMeetPercent" value="${conditions.etcPlateNotMeetPercent }" style="width:70px;" resert=""  number="" maxlength="5"/>&nbsp;&#37;
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label><input name="conditions.historyEscape" type="checkbox" value="0" <c:if test="${conditions.historyEscape=='0' }">checked</c:if> resert="" />历史是否逃费 </label>
                        </th>
                        <td class="pn-fcontent">
                            <label style="margin-right:20px;"><input name="conditions.historyEscapeNum" type="radio" value="1" <c:if test="${conditions.historyEscapeNum=='1' }">checked</c:if>checked resert="" />是 </label>
                            <label><input name="conditions.historyEscapeNum" type="radio" value="2" <c:if test="${conditions.historyEscapeNum=='2' }">checked</c:if>  resert="" />否 </label>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="modal-footer" id="footer5">
                <button type="button" class="pull-right btn btn-primary" data-dismiss="modal">确定</button>
                <button type="button" class="pull-right btn btn-warning" style="margin:0 30px;"  onclick="resert()">重置</button>
                <button type="button" class="pull-right btn btn-danger" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
</form>
<form name="exportForm" id="exportForm" action="abnorCarReport_doExportExcel.do" method="post">
<input type="hidden" id="exportplateNum" name="exportplateNum"></input>
<input type="hidden" id="auditStatusExport" name="auditStatusExport"></input>
</form>

<!--异常稽查车辆流水界面-->
<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width:90%;min-width:900px;">
        <div class="modal-content" >
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only" >关闭</span>
                </button>
                <h4 class="modal-title" id="myModalLabel1">异常稽查车辆流水</h4>
            </div>
            <div class="modal-body">
                <div class="widget widget-table">
                    <div class="widget-content" style="min-height: 60px;">
                        <table class="pn-ftable" border="0" cellpadding="10">
                            <tbody>
                            <tr>
                                <th style="width:6%;">车辆标签:</th>
                                <td class="pn-fcontent carLabel" style="white-space: normal;width: 94%;"  id="Abnorlabel">
                     
                                
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="widget-bottom">
                        <input type="hidden"  id="currentNum"></input>
                        <a 	class="btn btn-s-md btn-primary pull-left" id=""  onclick="beforeLiuShui()">上一条</a>
                        <a 	class="btn btn-s-md btn-primary pull-left" id=""   onclick="afterLiuShui()">下一条</a>
                        <a 	class="btn btn-s-md btn-primary pull-left" onclick="pictureContrast()">图片对比</a>
                        <a 	class="btn btn-s-md btn-primary pull-right" id="" data-toggle="modal" data-keyboard="false" data-backdrop="static" tabindex="-1"  onclick="bachAction()">批量处理</a>
                        <a 	class="btn btn-s-md btn-primary pull-right" id="" data-toggle="modal" data-target="#myModal4"data-keyboard="false" data-backdrop="static" tabindex="-1" >配置列</a>
                        <a 	class="btn btn-s-md btn-primary pull-right" id="" onclick="exportExcel()">导出</a>
                    </div>
                </div>
                <div class="separator line"></div>
                <div class="widget widget-table">  <!-- style="height:580px;overflow-y: auto;" -->
                    <div class="widget-header">
                        <i class="icon-th-list"></i>
                        <h5>异常车辆流水</h5>
                    </div>
                    <div class="widget-content widget-list widget-scroll">  <!--  style="min-height:500px;" -->
                        <table class="table table-striped table-bordered ycls-table">
                            <thead>
                            <tr>
                                <th><input type="checkbox" id="checkAll"/>选中</th>
                                <th class="a1">操作员</th>
                                <th class="a2">行驶公里数</th>
                                <th class="a3">重量</th>
                       <!--          <th class="a4">流水类型</th> -->
                                <th class="a5">入口时间</th>
                                <th class="a6">出口时间</th>
                                <th class="a7">轴组</th>
                                <th class="a8">入口车型</th>
                                <th class="a9">出口车型</th>
                                <th class="a10">OBU绑定车牌</th>
                                <th class="a11">识别车牌</th>
                                <th class="a12">标识点</th>
                                <th class="a13">基础金额</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody id="liushui">
                        
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="modal-footer" id="footer1">
                <button type="button" class="pull-right btn btn-danger" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<!--配置列界面-->
<div class="modal fade" id="myModal4" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <form name="saveForm"  action="grayList_save.do" method="post" enctype="multipart/form-data">
        <div class="modal-dialog" style="width:80%; min-width:680px;">
            <div class="modal-content" >
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel4">请勾选异常流水列表中要隐藏的列</h4>
                </div>
                <div name="hg" class="modal-body configurationLine" style="padding-bottom:0;">
                    <label><input name="line" class="line1" type="checkbox" value="55" />操作员 </label>
                    <label><input name="line" class="line2" type="checkbox" value="" />行驶公里数 </label>
                    <label><input name="line" class="line3" type="checkbox" value="" />重量 </label>
              <!--       <label><input name="line" class="line4" type="checkbox" value="" />流水类型 </label> -->
                    <label><input name="line" class="line5" type="checkbox" value="" />入口时间 </label>
                    <label><input name="line" class="line6" type="checkbox" value="" />出口时间 </label>
                    <label><input name="line" class="line7" type="checkbox" value="" />轴组 </label>
                    <label><input name="line" class="line8" type="checkbox" value="" />入口类型 </label>
                    <label><input name="line" class="line9" type="checkbox" value="" />出口类型 </label>
                    <label><input name="line" class="line10" type="checkbox" value="" />OBU绑定车牌 </label>
                    <label><input name="line" class="line11" type="checkbox" value="" />识别车牌 </label>
                    <label><input name="line" class="line12" type="checkbox" value="" />标识点 </label>
                    <label><input name="line" class="line13" type="checkbox" value="" />基础金额 </label>
                </div>
                <div class="modal-footer" id="footer4">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" onclick="controlLine()">确定</button>
                </div>
            </div>
        </div>
    </form>
</div>
<!--异常稽查明细-->
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <form name="detailSaveForm" id="detailSaveForm" action="grayList_save.do" method="post" enctype="multipart/form-data">
        <div class="modal-dialog" style="width:80%;">
            <div class="modal-content" id="loading">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel2">异常稽查明细</h4>
                </div>
                <div class="modal-body"   id="abnorDetails">
                     <input type="hidden" name="conditions.batChplateID"  value=""/>
                    <h5><b>流水信息：</b></h5>
                    <table class="pn-ftable lsxx-table" border="0" cellpadding="5" style="table-layout:fixed;white-space: normal;">
                        <tbody>
                        <tr>
                            <th>入口收费站:</th>
                            <td class="pn-fcontent"><font name="EnStationID" class="motaiTdFont"></font></td>
                            <th>出口收费站:</th>
                            <td class="pn-fcontent"><font name="ExStationID" class="motaiTdFont"/></td>
                            <th>入口时间:</th>
                            <td class="pn-fcontent"><font name="EnTime" class="motaiTdFont"/></td>
                        </tr>
                        <tr>
                            <th>出口时间:</th>
                            <td class="pn-fcontent"><font name="ExTime" class="motaiTdFont"></font></td>
                            <th>入口车型:</th>
                            <td class="pn-fcontent"><font name="EnVehicleClass" class="motaiTdFont"/></td>
                            <th>出口车型:</th>
                            <td class="pn-fcontent"><font name="ExVehicleClass" class="motaiTdFont"/></td>
                        </tr>
                        <tr>
                            <th>收费里程:</th>
                            <td class="pn-fcontent"><font name="Miles" class="motaiTdFont"></font></td>
                            <th>入口车牌:</th>
                            <td class="pn-fcontent"><font name="EnVehiclePlate" class="motaiTdFont"></font></td>
                            <th>出口车牌:</th>
                            <td class="pn-fcontent"><font name="ExVehiclePlate" class="motaiTdFont"/></td>
                        </tr>
                        <tr>
                            <th>出口识别车牌:</th>
                            <td class="pn-fcontent"><font name="ExVehIdentifyPlate" class="motaiTdFont"/></td>
                            <th>OBU车牌:</th>
                            <td class="pn-fcontent"><font name="OBUPlate" class="motaiTdFont"/></td>
                            <th>轴组数:</th>
                            <td class="pn-fcontent"><font name="AxisGroupNum" class="motaiTdFont"/></td>
                        </tr>
                        <tr>
                            <th>入口车种:</th>
                            <td class="pn-fcontent"><font name="EnVehicleStatus" class="motaiTdFont"/></td>
                            <th>出口车种:</th>
                            <td class="pn-fcontent"><font name="ExVehicleStatus" class="motaiTdFont"/></td>
                            <th>超载率:</th>
                            <td class="pn-fcontent"><font name="OverLoadPercent" class="motaiTdFont"/></td>
                        </tr>
                        <tr>
                            <th>超载重量:</th>
                            <td class="pn-fcontent"><font name="overLoadWeight" class="motaiTdFont"/></td>
                            <th>最终重量:</th>
                            <td class="pn-fcontent"><font name="totalWeight" class="motaiTdFont"/></td>
                            <th></th>
                            <td class="pn-fcontent"><font name="" class="motaiTdFont"/></td>
                        </tr>
                       <!--  <tr>
                            <th>按键信息:</th>
                            <td class="pn-fcontent" colspan="5"><font name="" class="motaiTdFont"/></td>
                        </tr> -->
                        <tr>
                            <th>标识站:</th>
                            <td class="pn-fcontent" colspan="5"><font name="realPath" class="motaiTdFont"/></td>
                        </tr>
                        <tr>
                            <th>特情事件:</th>
                            <td class="pn-fcontent" colspan="5"><font name="dealStatus" class="motaiTdFont"/></td>
                        </tr>
                        <tr>
                            <th>入口照片:</th>
                            <td class="pn-fcontent"><img src="/images/error.jpg" alt="" id="enVehicleImage" class="imgsClass"/></td>
                            <th>出口照片:</th>
                            <td class="pn-fcontent"><img src="/images/error.jpg" alt="" id="exVehicleImage" class="imgsClass"/></td>
                            <th></th>
                            <td class="pn-fcontent"></td>
                        </tr>
                      </tbody>
                    </table>
                    <br/>
                    <h5><b>流水稽查：</b></h5>
                    <table class="pn-ftable table-bordered" border="0" cellpadding="8">
                        <tbody>
                      <!--   <tr>
                            <th><span class="point">*</span>稽查结果:</th>
                            <td class="pn-fcontent">
                                <select name="" id="">
                                    <option value="">车牌不符</option>
                                </select>
                            </td>
                            <th>稽查车型:</th>
                            <td class="pn-fcontent">
                                <select name="" id="">
                                    <option value="">一型车</option>
                                </select>
                            </td>
                            <th><span class="point">*</span>稽查车牌:</th>
                            <td class="pn-fcontent">
                                    <select name="",id="">
                                       <option value="1">符合车牌规则</option>
                                    </select>
                            </td>
                        </tr>
                        <tr>
                            <th><span class="point">*</span>稽查金额:</th>
                            <td class="pn-fcontent">
                                <input type="text" id=" " maxlength="8" name=" "/>
                            </td>
                            <th>稽查车种 :</th>
                            <td class="pn-fcontent">
                                <select name="" id=" " >
                                    <option value="">全部</option>
                                </select>
                            </td>
                            <th>稽查轴组 :</th>
                            <td class="pn-fcontent">
                                  <input type="text" id=" " maxlength="8" name=" "/>
                            </td>
                        </tr>
                        <tr>
                            <th>稽查种类 :</th>
                            <td class="pn-fcontent">
                                <select name="" id=" " >
                                    <option value="">全部</option>
                                </select>
                            </td>
                            <th></th>
                            <td class="pn-fcontent"></td>
                            <th></th>
                            <td class="pn-fcontent"></td>
                        </tr>

                        <tr>
                            <th>稽查说明:</th>
                            <td class="pn-fcontent" colspan="5">
                                <textarea style="width:95.5%" id="" name="" rows="3" cols="50" maxlength="250" dataValidate="说明"></textarea>
                            </td>
                        </tr>
                        <tr>
                            <th>证据链:</th>
                            <td class="motaiTd" colspan="5">
                                 <a 	class="btn btn-s-md btn-info" id="">添加</a>
                                <input id="" type="file" name="" title="证据链" />
                            </td>
                        </tr> -->
                          <tr>
                            <th><span class="point">*</span>稽查结果:</th>
                            <td class="pn-fcontent">
                                <select name="jcjg" id="jcjg"  dataValidate="稽查结果">
                                    <option value="0">正常</option>
                                      <option value="1">灰名单</option>
                                </select>
                            </td>
                            <th>稽查车型:</th>
                             <td class="pn-fcontent"><select name="roGrayList.vehClass"  remove="">
                        <option value="">全部</option>
                        <c:forEach items="${classList}" var="veh">
                            <option value="${veh.value }"
                                    <c:if test="${veh.value == conditions.vehClass}"><c:out value="selected"/></c:if>>
                                    ${veh.name}</option>
                        </c:forEach>
                    </select>
                    </td>
                            <th><span class="point">*</span>稽查车牌:</th>
                            <td class="pn-fcontent">
                                     <input type="text" id=" " name="roGrayList.vehPlate"    maxlength="7" name=" " dataValidate="稽查车牌" remove=""/>
                            </td>
                        </tr>
                        <tr>
                            <th><span class="point">*</span>稽查金额:</th>
                            <td class="pn-fcontent">
                                <input type="text" id=" " name="roGrayList.feeMoney"    maxlength="8" name=" " dataValidate="稽查金额" remove=""/>
                            </td>
                            <th><span class="point">*</span>稽查车种 :</th>
                            <td class="pn-fcontent" >
		                                <select name="roGrayList.vehType" id="vehType" dataValidate="稽查车种" remove="">
		                                    <option value="">全部</option>
		                                    <c:forEach items="${typeList}" var="veh">
											 <option value="${veh.value }" >
												 ${veh.name}</option>
										    </c:forEach>
		                                </select>
		                            </td>
                            <th>稽查轴组 :</th>
                            <td class="pn-fcontent">
                                 <select name="roGrayList.axisGroup" id=""  remove="" >
		                                  <option value="0">全部</option>
		                                  <c:forEach items="${AxisList}" var="veh">
										 <option value="${veh.value }" >
										  ${veh.name}</option>
										  </c:forEach>
		                          </select>
                            </td>
                        </tr>
                        <tr>
                            <th><span class="point">*</span>稽查种类 :</th>
                            <td class="pn-fcontent">
                                <select name="roGrayList.vehTypeStr" id="vehTypeStr"  dataValidate="稽查种类"  remove="">
		                                    <option value="">全部</option>
		                                    <c:forEach items="${abnorTypeList}" var="veh">
											 <option value="${veh.value }" >
												 ${veh.name}</option>
										    </c:forEach>
		                                </select>
                            </td>
                            <th><span class="point">*</span>客货车标识别：</th>
                    <td class="pn-fcontent">
                        <select name="roGrayList.vehFlag"   dataValidate="客货车标识别" remove="">
                            <option value="">全部</option>
                            <option value="1" <c:if test="${conditions.vehFlag == '1'}"><c:out
                                    value="selected"/></c:if>>客车
                            </option>
                            <option value="2" <c:if test="${conditions.vehFlag == '2'}"><c:out
                                    value="selected"/></c:if>>货车
                            </option>
                            <option value="0" <c:if test="${conditions.vehFlag == '0'}"><c:out
                                    value="selected"/></c:if>>非计重或不分客货
                            </option>
                        </select>
                    </td>
                             <th></th>
                    <td class="pn-fcontent">
                       
                    </td>
                        </tr>

                        <tr>
                            <th><span class="point">*</span>稽查说明:</th>
                            <td class="pn-fcontent" colspan="5">
                                <textarea style="width:95.5%" id="" name="roGrayList.vehInfo" rows="3" cols="50" maxlength="250" dataValidate="稽查说明" remove=""></textarea>
                            </td>
                        </tr>
                        <tr>
                            <th>证据链:</th>
                            <td class="motaiTd" colspan="5">
                                <!--  <a 	class="btn btn-s-md btn-info" id="">添加</a> -->
                                <input id="" type="file" name="roGrayList.file" title="证据链" onchange="fileValidate(this)" remove="" style="display:inline-block;"/>
                                <span style="color:red;">仅支持zip和rar格式文件</span>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="modal-footer" id="footer2">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary"  onclick="abnorIsNormalDetail()">流水正常</button>
                    <button type="button" class="btn btn-primary" onclick="abnorToGrayDetails()">上传至灰名单</button>
                </div>
            </div>
        </div>
    </form>
</div>

<!--批量处理界面-->
<div class="modal fade" id="myModal3" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <form name="saveGrayForm"  id="saveGrayForm" action="grayList_abnorToGray.do" method="post" enctype="multipart/form-data">
        <div class="modal-dialog" style="width:80%;">
            <div class="modal-content"  id="loading" >
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel3">流水稽查批量处理</h4>
                </div>
                <div class="modal-body"   id="plcl">
                <!--用于保存批量处理的车牌信息 -->
                <input type="hidden"  name="conditions.batChplateID"  id="batChplateID"  value=""> </input>
              
                    <table class="pn-ftable table-bordered" border="0" cellpadding="10">
                        <tbody>
                        <tr>
                            <th><span class="point">*</span>稽查结果:</th>
                            <td class="pn-fcontent">
                                <select name="jcjg" id="jcjg"  dataValidate="稽查结果" remove="">
                                    <option value="0">正常</option>
                                      <option value="1">灰名单</option>
                                </select>
                            </td>
                            <th>稽查车型:</th>
                             <td class="pn-fcontent"><select name="roGrayList.vehClass"  remove="">
                        <option value="">全部</option>
                        <c:forEach items="${classList}" var="veh">
                            <option value="${veh.value }"
                                    <c:if test="${veh.value == conditions.vehClass}"><c:out value="selected"/></c:if>>
                                    ${veh.name}</option>
                        </c:forEach>
                    </select>
                    </td>
                            <th><span class="point">*</span>稽查车牌:</th>
                            <td class="pn-fcontent">
                                     <input type="text" id=" " name="roGrayList.vehPlate"    maxlength="7" name=" " dataValidate="稽查车牌" remove=""/>
                            </td>
                        </tr>
                        <tr>
                            <th><span class="point">*</span>稽查金额:</th>
                            <td class="pn-fcontent">
                                <input type="text" id=" " name="roGrayList.feeMoney"    maxlength="8" name=" " dataValidate="稽查金额" remove=""/>
                            </td>
                            <th><span class="point">*</span>稽查车种 :</th>
                            <td class="pn-fcontent" >
		                                <select name="roGrayList.vehType" id="vehType" dataValidate="稽查车种" remove="">
		                                    <option value="">全部</option>
		                                    <c:forEach items="${typeList}" var="veh">
											 <option value="${veh.value }" >
												 ${veh.name}</option>
										    </c:forEach>
		                                </select>
		                            </td>
                            <th>稽查轴组 :</th>
                            <td class="pn-fcontent">
                                <select name="roGrayList.axisGroup" id=""   remove="">
		                                    <option value="0">全部</option>
		                                    <c:forEach items="${AxisList}" var="veh">
											 <option value="${veh.value }" >
												 ${veh.name}</option>
										    </c:forEach>
		                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th><span class="point">*</span>稽查种类 :</th>
                            <td class="pn-fcontent">
                                <select name="roGrayList.vehTypeStr" id="vehTypeStr"  dataValidate="稽查种类" remove="">
		                                    <option value="">全部</option>
		                                    <c:forEach items="${abnorTypeList}" var="veh">
											 <option value="${veh.value }" >
												 ${veh.name}</option>
										    </c:forEach>
		                                </select>
                            </td>
                            <th><span class="point">*</span>客货车标识别：</th>
                    <td class="pn-fcontent">
                        <select name="roGrayList.vehFlag"  remove="">
                            <option value="">全部</option>
                            <option value="1" <c:if test="${conditions.vehFlag == '1'}"><c:out
                                    value="selected"/></c:if>>客车
                            </option>
                            <option value="2" <c:if test="${conditions.vehFlag == '2'}"><c:out
                                    value="selected"/></c:if>>货车
                            </option>
                            <option value="0" <c:if test="${conditions.vehFlag == '0'}"><c:out
                                    value="selected"/></c:if>>非计重或不分客货
                            </option>
                        </select>
                    </td>
                             <th></th>
                    <td class="pn-fcontent">
                       
                    </td>
                        </tr>

                        <tr>
                            <th><span class="point">*</span>稽查说明:</th>
                            <td class="pn-fcontent" colspan="5">
                                <textarea style="width:95.5%" id="" name="roGrayList.vehInfo" rows="3" cols="50" maxlength="250" dataValidate="稽查说明" remove=""></textarea>
                            </td>
                        </tr>
                        <tr>
                            <th>证据链:</th>
                            <td class="motaiTd" colspan="5">
                                <!--  <a 	class="btn btn-s-md btn-info" id="">添加</a> -->
                                <input id="" type="file" name="roGrayList.file" title="证据链" onchange="fileValidate(this)" remove=""/>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="modal-footer" id="footer3">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary"  onclick="abnorIsNormal()">流水正常</button>
                    <button type="button" class="btn btn-primary" onclick="abnorToGray()">上传至灰名单</button>
                </div>
            </div>
        </div>
    </form>
</div>


<!--配置列界面-->
<div class="modal fade" id="picContrastModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog" style="width:80%; min-width:680px;">
        <div class="modal-content" >
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
                </button>
                <h4 class="modal-title">图片对比</h4>
            </div>
            <div class="modal-body configurationLine" style="padding-bottom:0;">
                <div><img src="/images/error.jpg" id="exVehicleImageOne" /></div>
                <div><img src="/images/error.jpg" id="exVehicleImageTwo" /></div>
            </div>
            <div class="modal-footer" id="footer4">
                <button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>


<jsp:include page="../include/pictureHuan.jsp">
	<jsp:param value="#myModal2" name="seletor"/>
</jsp:include>
</body>
</html>