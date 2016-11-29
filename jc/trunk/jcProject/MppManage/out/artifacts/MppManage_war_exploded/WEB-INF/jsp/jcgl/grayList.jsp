<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.text.SimpleDateFormat" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>灰名单管理</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${basePath}/css/plugins/font-awesome/css/font-awesome.min.css"/>
    <!--[if IE 7]>
    <link rel="stylesheet" href="${basePath}/css/plugins/font-awesome/css/font-awesome-ie7.min.css"/>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="${basePath}/css/goods.css"/>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="${basePath}/theme/transparent/main.css"/>
    <script language="javascript" src="${basePath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${basePath}/js/quartz/plugins/My97DatePicker/WdatePicker.js"></script>
    <script src="${basePath}/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/grayList.js"></script>
    <script src="${basePath}/js/communicate.js"></script>    
    <link href="${basePath}/js/load/showLoading.css" rel="stylesheet" media="screen" /> 
    <script src="${basePath}/js/load/jquery.showLoading.min.js"></script>
    <style>
    	#myModal4 .modal-content .pn-ftable th,#myModal5 .modal-content .pn-ftable th {width:120px;} 
    	table #databody tr:hover{cursor:pointer}
    </style>
</head>
<body style="min-height:900px;">
<ul class="breadcrumb">
    <li><i class="icon-home icon-2x"></i></li>
    <li>当前位置：<c:choose>
                    <c:when test="${conditions.dataSource == 0}">
                        路段灰名单管理
                    </c:when>
                    <c:otherwise>
                        全省灰名单管理
                    </c:otherwise>
                </c:choose></li>
</ul>
<div class="widget widget-table">
    <div class="widget-content">
        <form name="myForm" id="myForm" action="grayList_list.do" method="post">
            <table class="pn-ftable" border="0" cellpadding="10">           
                <tbody id="conditionHtml">             
                <tr>
                    <th>灰名单范围：</th>
                    <td class="pn-fcontent">
                        <input type="radio" name="fanwei" value='1'
                               <c:if test="${conditions.dataSource == 1}">checked</c:if> id="quansheng"/><label
                            for="quansheng">全省</label>
                        <input type="radio" name="fanwei" value='0'
                               <c:if test="${conditions.dataSource == 0}">checked</c:if> style="margin-left:20px;"
                               id="luduan"/><label for="luduan">路段</label>
                    </td>
                    <th>车牌号：</th>
                    <td class="pn-fcontent"><select class="pull-left" name="conditions.plateNum" 
                                                    style="width:100px;">
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
                    </select> <input type="text" size="6" maxlength="6" name="conditions.carNo" 
                                     value="${conditions.carNo}"/>
                    </td>
                    <th>车型：</th>
                    <td class="pn-fcontent"><select name="conditions.vehClass" >
                        <option value="">全部</option>
                        <c:forEach items="${classList}" var="veh">
                            <option value="${veh.value }"
                                    <c:if test="${veh.value == conditions.vehClass}"><c:out value="selected"/></c:if>>
                                    ${veh.name}</option>
                        </c:forEach>
                    </select>
                    </td>
                </tr>
                <c:if test="${conditions.dataSource==0}">
                    <tr>
                        <th>逃费类型—大类：</th>
                        <td class="pn-fcontent">
                            <select name="" id="" onchange="">
                            </select>
                        </td>
                        <th>逃费类型—中类：</th>
                        <td class="pn-fcontent">
                            <select name="" id="" onchange="">
                            </select>
                        </td>
                        <th>逃费类型—细类：</th>
                        <td class="pn-fcontent">
                            <select name="" id="" onchange="">
                            </select>
                        </td>
                    </tr>
                </c:if>
                <tr>
                    <th>客货车标识别：</th>
                    <td class="pn-fcontent">
                        <select name="conditions.vehFlag" >
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
                    <th>车牌颜色：</th>
                    <td class="pn-fcontent">
                        <select name="conditions.vehPlateColor" >
                            <option value="">全部</option>
                            <c:forEach items="${colorList}" var="veh">
                                <option value="${veh.value }"
                                        <c:if test="${veh.value == conditions.vehPlateColor}"><c:out value="selected"/></c:if>>
                                        ${veh.name}</option>
                            </c:forEach>
                        </select>
                    </td>

                    <th></th>
                    <td class="pn-fcontent">
                    </td>
                </tr>

                </tbody>
            </table>
       
    </div>
    <div class="widget-bottom">
        <a class="btn btn-s-md btn-primary pull-right" id="submit">查询</a>
        <c:if test="${conditions.dataSource==0}">
            <shiro:hasPermission name="jcManage:man">
                <a class="btn btn-s-md pull-right" href="javascript:openSaveForm()"
                   id="">添加</a>
                <a class="btn btn-s-md btn-primary pull-right" data-toggle="modal" data-target="#myModal2"
                   id="">导入Excel</a>
                <a class="btn btn-s-md btn-primary pull-right"  href="grayList_downloadTemp.do"
                   id="">下载导入模板</a>
            </shiro:hasPermission>
        </c:if>
        <a class="btn btn-s-md btn-primary pull-right" data-toggle="modal" data-target="#myModal1" id="" onclick="doExcelReoprt()">导出Excel</a>
    </div>
</div>
<div class="separator line"></div>
<div class="widget widget-table">  <!-- style="height:580px;overflow-y: auto;" -->
    <div class="widget-header">
        <i class="icon-th-list"></i>
        <h5>灰名单列表</h5>
    </div>
    <div class="widget-content widget-list widget-scroll">  <!--  style="min-height:500px;" -->
        <table class="table table-striped table-bordered">
            <thead>
            <tr>
                <th>序号</th>
                <th>车牌号</th>
                <th>车牌颜色</th>
                <th>客货标识</th>
                <th>车型</th>
                <th>摘要信息</th>
                <th>数据源</th>
                <c:if test="${conditions.dataSource==0}">
                    <th>审核状态</th>
                    <th>逃费类型-大类</th>
                    <th>逃费类型-中类</th>
                    <th>逃费类型-细类</th>
                    <th>证据链</th>
                    <th>操作</th>
                </c:if>
            </tr>
            </thead>
            <tbody id="databody">
            <c:forEach items="${list}" var="item" varStatus="status">
                <tr <c:if test="${conditions.dataSource==0}">
                      ondblclick="showxiangqing('${item.id}','${item.vehPlate}','${colorMap[item.vehColorStr]}','${item.vehFlag}','${classMap[item.vehClassStr]}','${typeMap[item.vehTypeStr]}','${item.vehInfo}',
							        '${item.vehBigType}','${item.vehMidType}','${item.vehSmallType}','${item.applicant}','${item.applicateTime}',
							        '${orgMap[item.applicateOrg]}','${item.fileName}','${item.uploadTime}','${item.status}','${item.opinion}','${axisMap[item.axisGroupStr]}','${item.feeCount}','${item.feeMoney}','xiangqing');"
					</c:if>>
                    <td>${status.count }</td>
                    <td>${item.vehPlate}</td>
                    <td>${colorMap[item.vehColorStr]}</td>
                    <td>
                        <c:choose>
                            <c:when test="${item.vehFlag==1}">
                                客车
                            </c:when>
                            <c:when test="${item.vehFlag==2}">
                                货车
                            </c:when>
                            <c:otherwise>
                                非计重或不分客货
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${classMap[item.vehClassStr]}</td>
                    <td style="overflow:hidden;text-overflow:ellipsis;white-space:nowrap; width:100px;" title="${item.vehInfo}">${item.vehInfo}</td>
                    <td>
                        <c:choose>
                            <c:when test="${conditions.dataSource==0}">
                                路段
                            </c:when>
                            <c:otherwise>
                                全省
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <c:if test="${conditions.dataSource==0}">
                        <td>
                            <c:choose>
                                <c:when test="${item.status==0}">
                                    未审核
                                </c:when>
                                <c:otherwise>
                                    不通过
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${item.vehBigType}</td>
                        <td>${item.vehMidType}</td>
                        <td>${item.vehSmallType}</td>
                        <td>
                            <c:choose>
                                <c:when test="${item.fileName==null||item.fileName==''}">
                                    <shiro:hasPermission name="jcManage:man">
                                        <a class="btn btn-s-md btn-primary"
                                           href="javascript:upload('${item.id}')">上传</a>
                                    </shiro:hasPermission>
                                    <shiro:lacksPermission name="jcManage:man">
                                        未上传
                                    </shiro:lacksPermission>
                                </c:when>
                                <c:otherwise>
                                    已上传
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <a href="javascript:showxiangqing('${item.id}','${item.vehPlate}','${colorMap[item.vehColorStr]}','${item.vehFlag}','${classMap[item.vehClassStr]}','${typeMap[item.vehTypeStr]}','${item.vehInfo}',
							        '${item.vehBigType}','${item.vehMidType}','${item.vehSmallType}','${item.applicant}','${item.applicateTime}',
							        '${orgMap[item.applicateOrg]}','${item.fileName}','${item.uploadTime}','${item.status}','${item.opinion}','${axisMap[item.axisGroupStr]}','${item.feeCount}','${item.feeMoney}','xiangqing');"
                               class="btn btn-info">详情</a>
                            <shiro:hasPermission name="jcManage:man">
                                <c:if test="${item.status==0}">
                                    <a href="javascript:update('${item.id}','${item.vehPlate}','${item.vehColorStr}','${item.vehFlag}','${item.vehClassStr}','${item.vehTypeStr}','${item.vehInfo}','${item.axisGroupStr}','${item.feeCount}','${item.feeMoney}',
									        '${item.vehBigType}','${item.vehMidType}','${item.vehSmallType}','${item.applicant}','${item.applicateTime}',
									        '${item.applicateOrg}','${item.fileName}','${item.uploadTime}','${item.updateTime}','${item.status}','${item.staffNo}');"
		                               class="btn btn-warning">修改</a>
                                    <a href="javascript:deleteById('${item.id}')"
                                       class="btn btn-s-md btn-important ">删除</a>
                                </c:if>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="jcManage:chairman">
                                <c:if test="${item.status==0&&item.fileName!=null&&item.fileName!=''}">
                                    <a href="javascript:showxiangqing('${item.id}','${item.vehPlate}','${colorMap[item.vehColorStr]}','${item.vehFlag}','${classMap[item.vehClassStr]}','${typeMap[item.vehTypeStr]}','${item.vehInfo}',
							        '${item.vehBigType}','${item.vehMidType}','${item.vehSmallType}','${item.applicant}','${item.applicateTime}',
							        '${orgMap[item.applicateOrg]}','${item.fileName}','${item.uploadTime}','${item.status}','${item.opinion}','${axisMap[item.axisGroupStr]}','${item.feeCount}','${item.feeMoney}','audit');"
                                       class="btn btn-info">审核</a>
                                </c:if>
                            </shiro:hasPermission>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="widget-bottom">
        <jsp:include page="../include/pager.jsp"/>
    </div>
</div>
 </form>
<!--导入-->
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
     <form name="myForm1" id="myForm1" action="grayList_importExcel.do" method="post"
                  enctype="multipart/form-data">
    <div class="modal-dialog" style="width:960px;">
        <div class="modal-content" id="loading">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
                </button>
                <h4 class="modal-title" id="myModalLabel2">导入路段灰名单Excel</h4>
            </div>
            
                <div class="modal-body">
                    <table class="pn-ftable table-bordered" border="0" cellpadding="10">
                        <tbody>
                        <tr>
                            <th><span class="point">*</span>导入Excel</th>
                            <td class="pn-fcontent">
                                <input type="file" id="importFile" name="roGrayList.file" maxlength="20"style="display:inline-block;"/>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
           
            <div class="modal-footer" id="footer2">
                <button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" id="importExcel" onclick="improtExcel()">导入</button>
            </div>
        </div>
    </div>
     </form>
</div>
<!--上传证据链-->
<div class="modal fade" id="myModal3" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <form name="uploadForm" id="uploadForm" action="grayList_upload.do" method="post" enctype="multipart/form-data">
        <div class="modal-dialog" style="width:960px;">
            <div class="modal-content" id="loading">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel3">上传证据链</h4>
                </div>
                <div class="modal-body">
                    <table class="pn-ftable table-bordered" border="0" cellpadding="10">
                        <tbody>
                        <input type="hidden" name="roGrayList.id" id="uploadId"/>
                        <tr>
                            <th><span class="point">*</span>上传附件</th>
                            <td class="pn-fcontent">
	                            <input type="file" id='test1' name="roGrayList.file" onchange="fileValidate(this)"style="display:inline-block;"/>
	                            <span style="color:red;">仅支持zip和rar格式文件</span>
                            </td>
                        </tr>
                        <!-- 条件 -->
                                <input type="hidden"  name="conditions.plateNum" />
                                <input type="hidden"  name="conditions.carNo"  />
                                <input type="hidden"  name="conditions.vehClass"  />
                                <input type="hidden"  name="conditions.vehFlag"  />
                                <input type="hidden"  name="conditions.vehPlateColor"  />
                               <!-- 分页条件 --> 
                                <input type="hidden"  name="pager.pageSize" />
                                <input type="hidden"  name="pager.currentPage" /> 
                        </tbody>
                    </table>
                </div>
                <div class="modal-footer" id="footer3">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" id="uploadButton" onclick="uploadEvidence()">上传</button>
                </div>
            </div>
        </div>
    </form>
</div>
<!--详情-->
<div class="modal fade" id="myModal4" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width:960px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
                </button>
                <h4 class="modal-title" id="myModalLabel4">显示详情</h4>
            </div>
            <div class="modal-body">
                <table class="pn-ftable" border="0" cellpadding="10"style="table-layout:fixed;white-space: normal;">
                    <tbody>
                    <input type="hidden" id="auditId"/>
                    <tr>
                        <th>车牌:</th>
                        <td class="pn-fcontent"><font name="vehPlate" class="motaiTdFont"></font></td>
                        <th>车牌颜色:</th>
                        <td class="pn-fcontent"><font name="vehPlateColor" class="motaiTdFont"/></td>
                        <th>车型:</th>
                        <td class="pn-fcontent"><font name="vehClass" class="motaiTdFont"/></td>
                    </tr>
                    <th>逃费类型-大类:</th>
                    <td class="pn-fcontent"><font name="vehBigType" class="motaiTdFont"></font></td>
                    <th>逃费类型-中类:</th>
                    <td class="pn-fcontent"><font name="vehMidType" class="motaiTdFont"></font></td>
                    <th>逃费类型-细类:</th>
                    <td class="pn-fcontent"><font name="vehSmallType" class="motaiTdFont"></font></td>
                    </tr>
                    <tr>
                        <th>申请人:</th>
                        <td class="pn-fcontent"><font name="applicant" class="motaiTdFont"></font></td>
                        <th>申请时间:</th>
                        <td class="pn-fcontent"><font name="applicateTime" class="motaiTdFont"></font></td>
                        <th>申请机构:</th>
                        <td class="pn-fcontent"><font name="applicateOrg" class="motaiTdFont"></font></td>
                    </tr>
                    <tr>
                        <th>客货标识 :</th>
                        <td class="pn-fcontent"><font name="vehFlag" class="motaiTdFont"></font></td>
                        <th>车种 :</th>
                        <td class="pn-fcontent"><font name="vehType" class="motaiTdFont"></font></td>
                        <th>轴组 :</th>
                        <td class="pn-fcontent"><font name="axisGroup" class="motaiTdFont"></font></td>
                    </tr>
                    <tr>
                        <th>逃费次数:</th>
                        <td class="pn-fcontent"><font name="feeCount" class="motaiTdFont"></font></td>
                        <th>逃费金额 :</th>
                        <td class="pn-fcontent"><font name="feeMoney" class="motaiTdFont"></font></td>
                        <th></th>
                        <td></td>
                    </tr>
                    <tr>
                        <th>说明:</th>
                        <td style="word-wrap:break-word;word-break:break-all" class="pn-fcontent" colspan="5"><font name="vehInfo" class="motaiTdFont"></font></td>
                    </tr>
                    <tr name="opinionTr">
                        <th>审核意见:</th>
                        <td style="word-wrap:break-word;word-break:break-all" class="pn-fcontent" colspan="5"><font name="opinion" class="motaiTdFont"></font></td>
                    </tr>
                    <tr id="fileIsExit">
                        <th>附件:</th>
                        <td class="pn-fcontent" colspan="5">
                            <input style="width:200px;" type="text" name="fileName" readonly/>
                            <a href="javascript:onloadFile()" class="btn btn-s-md btn-info">下载</a>
                        </td>
                    </tr>
                    <tr name="auditContent">
                        <th><span class="point">*</span>拦截选项:</th>
                        <td class="pn-fcontent">
                            <select name="interceptOption">
                                <option value="">全部</option>                                       
	                            <c:forEach items="${optionList}" var="veh">
	                                <option value="${veh.value }">
	                                        ${veh.name}</option>
	                            </c:forEach>
                            </select>
                        </td>
                        <th><span class="point">*</span>拦截开始时间:</th>
                        <td class="pn-fcontent">
                            <input style="background-color:white;" type="text" name="startDate" class="Wdate" value=""
                                   onClick="javascript:WdatePicker({dateFmt: 'yyyy-MM-dd',skin:'whyGreen'})"/>
                        </td>
                        <th><span class="point">*</span>拦截结束时间:</th>
                        <td class="pn-fcontent">
                            <input style="background-color:white;" type="text" name="endDate" class="Wdate" value=""
                                   onClick="javascript:WdatePicker({dateFmt: 'yyyy-MM-dd',skin:'whyGreen'})"/>
                        </td>

                    </tr>
                    <tr name="auditContent">
                        <th>审核意见:</th>
                        <td class="pn-fcontent" colspan="5">
                            <textarea style="width:95.5%" name="opnionTwo" rows="3" cols="50" maxlength="250"></textarea>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <div name="auditContent" style="padding:20px 0;">
                    <a style="margin-left:50px;" name="auditButton" class="btn btn-s-md btn-warning pull-right"
                       onclick="audit('1')">不通过</a>
                    <a class="btn btn-s-md btn-success pull-right" name="auditButton" onclick="audit('0')">审核通过</a>
                </div>
            </div>
            <div class="modal-footer" id="footer4">
                <button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<!--修改添加-->
<div class="modal fade" id="myModal5" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <form name="saveForm" id="saveForm" action="grayList_save.do" method="post" enctype="multipart/form-data">
        <div class="modal-dialog" style="width:960px;">
            <div class="modal-content" id="loading">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel5">可疑车辆名单登记</h4>
                </div>
                <div class="modal-body">
                    <table class="pn-ftable table-bordered" border="0" cellpadding="10">
                        <tbody>
                        <input type="hidden"  name="roGrayList.id"  id="updateId"/>
                        <tr>
                            <th><span class="point">*</span>车牌:</th>
                            <td class="pn-fcontent"><select  class="pull-left" name="roGrayList.plateNum" title="车牌号" id="plateNum" mandatory='false'
							style="width:20%;"> 
								<option value="">全部</option>
								<option value="粤">粤</option>
								<option value="津" >津</option>
								<option value="沪" >沪</option>
								<option value="渝" >渝</option>
								<option value="京" >京</option>
								<option value="冀" >冀</option>
								<option value="豫" >豫</option>
								<option value="云" >云</option>
								<option value="辽" >辽</option>
								<option value="黑" >黑</option>
								<option value="湘" >湘</option>
								<option value="皖" >皖</option>
								<option value="鲁" >鲁</option>
								<option value="新" >新</option>
								<option value="苏" >苏</option>
								<option value="浙" >浙</option>
								<option value="赣" >赣</option>
								<option value="鄂" >鄂</option>
								<option value="桂" >桂</option>
								<option value="甘" >甘</option>
								<option value="晋" >晋</option>
								<option value="蒙" >蒙</option>
								<option value="陕" >陕</option>
								<option value="吉" >吉</option>
								<option value="闽" >闽</option>
								<option value="贵" >贵</option>
								<option value="青" >青</option>
								<option value="藏" >藏</option>
								<option value="川" >川</option>
								<option value="宁" >宁</option>
								<option value="琼" >琼</option>
								<option value="台" >台</option>
								<option value="港" >港</option>
								<option value="澳" >澳</option>
							</select>
							<input type="text"  maxlength="6" name="roGrayList.carNo" id="carNo"  mandatory='false' title="车牌"  style="width:60%!important" /></td> 
                            <th><span class="point">*</span>车牌颜色:</th>
                            <td class="pn-fcontent">
                                <select name="roGrayList.vehPlateColor" id="vehPlateColor" dataValidate="车牌颜色" >
                                    <option value="">全部</option>
                                    <c:forEach items="${colorList}" var="veh">
                                        <option value="${veh.value }">
                                                ${veh.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <th>车型:</th>
                            <td class="pn-fcontent">
                                <select name="roGrayList.vehClass" id="vehClass">
                                    <option value="0">全部</option>
                                    <c:forEach items="${classList}" var="veh">
                                        <option value="${veh.value}">
                                                ${veh.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th><span class="point">*</span>客货标识:</th>
                            <td class="pn-fcontent">
                                <select name="roGrayList.vehFlag" id="vehFlag" dataValidate="客货标识">
                                    <option value="">全部</option>
                                    <option value="1">客车</option>
                                    <option value="2">货车</option>
                                    <option value="0">非计重或不分客货</option>
                                </select>
                            </td>
                            <th><span class="point">*</span>车种 :</th>
                            <td class="pn-fcontent">
                                <select name="roGrayList.vehType" id="vehType" dataValidate="车种">
                                    <option value="">全部</option>
                                    <c:forEach items="${typeList}" var="veh">
                                        <option value="${veh.value }">
                                                ${veh.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <th>轴组 :</th>
                            <td class="pn-fcontent" >
                                <select name="roGrayList.axisGroup" id="axisGroup" >
                                <option value="0">全部</option>
                                    <c:forEach items="${axisList}" var="veh">
                                        <option value="${veh.value }">
                                                ${veh.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                          <th>逃费总次数:</th>
                          <td class="pn-fcontent">
                              <input type="text" maxlength="4" name="roGrayList.feeCount" id="feeCount" />
                          </td>
                          <th><span class="point">*</span>逃费总金额:</th>
                          <td class="pn-fcontent">
                              <input type="text"  maxlength="8" name="roGrayList.feeMoney" id="feeMoney" dataValidate="逃费总金额"  />
                          </td>
                          <th>逃费类型-大类:</th>
                            <td class="pn-fcontent">
                                <select name="roGrayList.vehBigType" id="vehBigType">
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th>逃费类型-中类:</th>
                            <td class="pn-fcontent">
                                <select name="roGrayList.vehMidType" id="vehMidType">
                                </select>
                            </td>
                            <th>逃费类型-细类:</th>
                            <td class="pn-fcontent">
                                <select name="roGrayList.vehSmallType" id="vehSmallType">
                                </select>
                            </td>
                            <th></th>
                            <td class="pn-fcontent"></td>
                        </tr>
                        <tr>
                            <th><span class="point">*</span>说明:</th>
                            <td class="pn-fcontent" colspan="5">
                                <textarea style="width:95.5%" id="vehInfo" name="roGrayList.vehInfo" rows="3" cols="50"
                                          maxlength="250" dataValidate="说明"></textarea>
                            </td>
                        </tr>
                        <tr>
                            <th><span class="point">*</span>申请人:</th>
                            <td class="pn-fcontent">
                                <input type="text" id="applicant" maxlength="8" name="roGrayList.applicant" dataValidate="申请人"/>
                            </td>
                            <th><span class="point">*</span>申请时间:</th>
                            <td class="pn-fcontent">
                                <input style="background-color:white;" type="text" id="applicateTime" name="roGrayList.applicateTime"
                                       dataValidate="申请时间" class="Wdate" value=""
                                       onClick="javascript:WdatePicker({dateFmt: 'yyyy-MM-dd',skin:'whyGreen',maxDate:'%y-%M-%d'})"/>
                            </td>
                            <th><span class="point">*</span>申请机构:</th>
                            <td class="pn-fcontent">
                                <select name="roGrayList.applicateOrg" id="applicateOrg"  dataValidate="申请机构">
                                    <c:forEach items="${orderOrgList}" var="item">
                                        <option value="${item.orgCode}"
                                                level="${item.orgLevel}">${fn:substring("││││││││││",0,item.orgLevel-1)}├${item.orgName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th><span class="point" id="redPoint">*</span>证据链:</th>
                            <td class="motaiTd" colspan="5">
                                <!--  <a 	class="btn btn-s-md btn-info" id="">添加</a> -->
                                <input id="evidence" type="file" name="roGrayList.file" title="证据链" style="display:inline-block;" onchange="fileValidate(this)"
                                       dataValidate="证据链"/>
                                <span style="color:red;">仅支持zip和rar格式文件</span>
                            </td>
                        </tr>
                        </tbody>
                        <input type="hidden"  name="roGrayList.fileName"  id="fileName"/>
                        <input type="hidden"  name="roGrayList.uploadTime"  id="uploadTime"/>
                        <input type="hidden"  name="roGrayList.staffNo"  id="staffNo"/>
                        <input type="hidden"  name="conditions.plateNum" />
                         <!-- 搜索条件 --> 
                        <input type="hidden"  name="conditions.carNo"  />
                        <input type="hidden"  name="conditions.vehClass"  />
                        <input type="hidden"  name="conditions.vehFlag"  />
                        <input type="hidden"  name="conditions.vehPlateColor"  />
                        <!-- 分页条件 --> 
                        <input type="hidden"  name="pager.pageSize" />
                        <input type="hidden"  name="pager.currentPage" /> 
                    </table>
                </div>
                <div class="modal-footer" id="footer5">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" id="saveButton" onclick="save()">保存</button>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>


<!-- 导入报表 js -->
<script language="javascript">
var status='';
$(function() {	
	message="${message}";
	if(message!=''&&message!=null){
		alert(message);
	}
	var dataSource='${conditions.dataSource}';
	if(dataSource==0){
	 $("#myForm").attr('action','grayList_list.do');
    	 
    }else{
   	 $("#myForm").attr('action','grayList_proList.do');
    }
});
    



</script>
