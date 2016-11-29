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
    <link rel="stylesheet" type="text/css" href="../../../css/bootstrap.min.css" />
    <link rel="stylesheet" href="../../../css/plugins/font-awesome/css/font-awesome.min.css" />
    <!--[if IE 7]>
    <link rel="stylesheet" href="../../../css/plugins/font-awesome/css/font-awesome-ie7.min.css" />
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="../../../css/goods.css" />
    <link rel="stylesheet" type="text/css" href="../../../css/style.css" />
    <link rel="stylesheet" type="text/css" href="../../../theme/transparent/main.css" />
    <script language="javascript" src="../../../js/jquery.min.js"></script>
    <script type="text/javascript" src="../../../js/quartz/plugins/My97DatePicker/WdatePicker.js"></script>
    <script language="javascript" src="../../../js/util.js"></script>
    <script src="../../../js/bootstrap.min.js"></script>
    <script src="../../../js/communicate.js"></script>
    <style>
        .pn-ftable.showTable tr th {
            text-align: right;
            padding-right: 10px;
        }
       table.yccl-table tbody tr,table.ycls-table tbody tr{
            cursor: pointer;
        }
        table.lsxx-table tbody tr th{width:9%;}
        .carLabel span{padding-right: 5px;}
        .configurationLine label{width:15%;cursor: pointer;}
    </style>
</head>
<body style="min-height:900px;">
<form name="myForm" id="myForm" action="" method="post">
    <ul class="breadcrumb">
        <li><i class="icon-home icon-2x"></i></li>
        <li>当前位置：可疑车辆分析</li>
    </ul>
    <div class="widget widget-table">
        <div class="widget-content">
            <table class="pn-ftable" border="0" cellpadding="10" style="table-layout: fixed;">
                <tbody>
                    <tr>
                        <th><span class="point">*</span>车牌号码</th>
                        <td class="pn-fcontent">
                            <select class="pull-left" name="roBlackList.plateNum" title="车牌号" id="plateNum" mandatory='false' style="width:30% !important;">
                                <option value="">全部</option>
                                <option value="粤">粤</option>
                                <option value="津">津</option>
                                <option value="沪">沪</option>
                                <option value="渝">渝</option>
                                <option value="京">京</option>
                                <option value="冀">冀</option>
                                <option value="豫">豫</option>
                                <option value="云">云</option>
                                <option value="辽">辽</option>
                                <option value="黑">黑</option>
                                <option value="湘">湘</option>
                                <option value="皖">皖</option>
                                <option value="鲁">鲁</option>
                                <option value="新">新</option>
                                <option value="苏">苏</option>
                                <option value="浙">浙</option>
                                <option value="赣">赣</option>
                                <option value="鄂">鄂</option>
                                <option value="桂">桂</option>
                                <option value="甘">甘</option>
                                <option value="晋">晋</option>
                                <option value="蒙">蒙</option>
                                <option value="陕">陕</option>
                                <option value="吉">吉</option>
                                <option value="闽">闽</option>
                                <option value="贵">贵</option>
                                <option value="青">青</option>
                                <option value="藏">藏</option>
                                <option value="川">川</option>
                                <option value="宁">宁</option>
                                <option value="琼">琼</option>
                                <option value="台">台</option>
                                <option value="港">港</option>
                                <option value="澳">澳</option>
                            </select>
                            <input type="text" maxlength="6" name="roBlackList.carNo" id="carNo" mandatory='false'title="车牌" style="width:50%!important"/>
                        </td>
                        <th><span class="point">*</span>车牌颜色 :</th>
                        <td class="pn-fcontent">
                            <select id="" name="">
                                <option value="">全部</option>
                                <option>选项一</option>
                            </select>
                        </td>
                        <th><span class="point">*</span>稽查状态 :</th>
                        <td class="pn-fcontent">
                            <select id="" name="">
                                <option value="">全部</option>
                                <option>选项一</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th><span class="point">*</span>异常显示类型 :</th>
                        <td class="pn-fcontent">
                            <input onclick="" type="radio" name="car" style="font-size: 14px;" value="0" id="car" /><label for="car">客车</label>
                            <input onclick="" type="radio" name="car" style="margin-left: 30px; font-size: 14px;" value="1" id="truck" /><label for="truck">货车</label>
                        </td> 
                        <th><span class="point">*</span>组合条件 :</th>
                        <td class="pn-fcontent" colspan="3">
                            <a  class="btn btn-primary btn-s" data-toggle="modal" data-target="#myModal5"data-keyboard="false" data-backdrop="static" tabindex="-1" >组合条件</a>
                        </td>
                    </tr>
                </tbody>
            </table>

        </div>
        <div class="widget-bottom">
            <a 	class="btn btn-s-md btn-primary pull-right" id="">查询</a>
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
                <thead>
                <tr>
                    <th>车牌</th>
                    <th>通行次数</th>
                    <th>短途重载</th>
                    <th>倒卡</th>
                    <th>有收费无标识</th>
                    <th>表示点异常</th>
                    <th>ETC车牌不符</th>
                    <th>ETC车型不符</th>
                    <th>轴组异常</th>
                    <th>历史性逃费</th>
                </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>粤B 634SD</td>
                        <td>9</td>
                        <td>xxx</td>
                        <td>xxx</td>
                        <td>xxx</td>
                        <td>xxx</td>
                        <td>xxx</td>
                        <td>xxx</td>
                        <td>xxx</td>
                        <td>xxx</td>
                    </tr>
                    <tr>
                        <td>粤S 626AD</td>
                        <td>9</td>
                        <td>xxx</td>
                        <td>xxx</td>
                        <td>xxx</td>
                        <td>xxx</td>
                        <td>xxx</td>
                        <td>xxx</td>
                        <td>xxx</td>
                        <td>xxx</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <!--底部分页栏-->
        <div class="widget-bottom">
            <jsp:include page="../include/pager.jsp" />
        </div>
    </div>
</form>

<!--组合条件界面-->
<div class="modal fade" id="myModal5" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width:960px;">
        <div class="modal-content" >
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only" >关闭</span>
                </button>
                <h4 class="modal-title" id="myModalLabel5"><span>客车</span>组合条件</h4>
            </div>
            <div class="modal-body">
                <table class="pn-ftable" border="0" cellpadding="10" style="table-layout: fixed;">
                    <tr>
                        <th>
                            <label><input name="condition" type="checkbox" value="" />倒卡 </label>
                        </th>
                        <td class="pn-fcontent">
                            <label><input name="daoka" type="checkbox" value="" />异常次数 </label>&nbsp;&gt;&nbsp;<input type="text" style="width:70px;margin-right:10%;"/>
                            <label><input name="daoka" type="checkbox" value="" />近期异常次数 </label>&nbsp;&gt;&nbsp;<input type="text" style="width:70px;margin-right:10%;"/>
                            <label><input name="daoka" type="checkbox" value="" />异常占比 </label>&nbsp;&gt;&nbsp;<input type="text" style="width:70px;"/>&nbsp;&#37;
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label><input name="condition" type="checkbox" value="" />轴组异常 </label>
                        </th>
                        <td class="pn-fcontent">
                            <label><input name="daoka" type="checkbox" value="" />异常次数 </label>&nbsp;&gt;&nbsp;<input type="text" style="width:70px;margin-right:10%;"/>
                            <label><input name="daoka" type="checkbox" value="" />近期异常次数 </label>&nbsp;&gt;&nbsp;<input type="text" style="width:70px;margin-right:10%;"/>
                            <label><input name="daoka" type="checkbox" value="" />异常占比 </label>&nbsp;&gt;&nbsp;<input type="text" style="width:70px;"/>&nbsp;&#37;
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label><input name="condition" type="checkbox" value="" />短途重载 </label>
                        </th>
                        <td class="pn-fcontent">
                            <label><input name="daoka" type="checkbox" value="" />异常次数 </label>&nbsp;&gt;&nbsp;<input type="text" style="width:70px;margin-right:10%;"/>
                            <label><input name="daoka" type="checkbox" value="" />近期异常次数 </label>&nbsp;&gt;&nbsp;<input type="text" style="width:70px;margin-right:10%;"/>
                            <label><input name="daoka" type="checkbox" value="" />异常占比 </label>&nbsp;&gt;&nbsp;<input type="text" style="width:70px;"/>&nbsp;&#37;
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label><input name="condition" type="checkbox" value="" />有收费无标识 </label>
                        </th>
                        <td class="pn-fcontent">
                            <label><input name="" type="checkbox" value="" />异常次数 </label>&nbsp;&gt;&nbsp;<input type="text" style="width:70px;margin-right:10%;"/>
                            <label><input name="" type="checkbox" value="" />近期异常次数 </label>&nbsp;&gt;&nbsp;<input type="text" style="width:70px;margin-right:10%;"/>
                            <label><input name="" type="checkbox" value="" />异常占比 </label>&nbsp;&gt;&nbsp;<input type="text" style="width:70px;"/>&nbsp;&#37;
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label><input name="condition" type="checkbox" value="" />ETC出入卡车型不符 </label>
                        </th>
                        <td class="pn-fcontent">
                            <label><input name="" type="checkbox" value="" />异常次数 </label>&nbsp;&gt;&nbsp;<input type="text" style="width:70px;margin-right:10%;"/>
                            <label><input name="" type="checkbox" value="" />近期异常次数 </label>&nbsp;&gt;&nbsp;<input type="text" style="width:70px;margin-right:10%;"/>
                            <label><input name="" type="checkbox" value="" />异常占比 </label>&nbsp;&gt;&nbsp;<input type="text" style="width:70px;"/>&nbsp;&#37;
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label><input name="condition" type="checkbox" value="" />表示点异常 </label>
                        </th>
                        <td class="pn-fcontent">
                            <label><input name="" type="checkbox" value="" />异常次数 </label>&nbsp;&gt;&nbsp;<input type="text" style="width:70px;margin-right:10%;"/>
                            <label><input name="" type="checkbox" value="" />近期异常次数 </label>&nbsp;&gt;&nbsp;<input type="text" style="width:70px;margin-right:10%;"/>
                            <label><input name="" type="checkbox" value="" />异常占比 </label>&nbsp;&gt;&nbsp;<input type="text" style="width:70px;"/>&nbsp;&#37;
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label><input name="condition" type="checkbox" value="" />ETC车牌不符 </label>
                        </th>
                        <td class="pn-fcontent">
                            <label><input name="" type="checkbox" value="" />异常次数 </label>&nbsp;&gt;&nbsp;<input type="text" style="width:70px;margin-right:10%;"/>
                            <label><input name="" type="checkbox" value="" />近期异常次数 </label>&nbsp;&gt;&nbsp;<input type="text" style="width:70px;margin-right:10%;"/>
                            <label><input name="" type="checkbox" value="" />异常占比 </label>&nbsp;&gt;&nbsp;<input type="text" style="width:70px;"/>&nbsp;&#37;
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label><input name="condition" type="checkbox" value="" />历史是否逃费 </label>
                        </th>
                        <td class="pn-fcontent">
                            <label><input name="" type="checkbox" value="" />异常次数 </label>&nbsp;&gt;&nbsp;<input type="text" style="width:70px;margin-right:10%;"/>
                            <label><input name="" type="checkbox" value="" />近期异常次数 </label>&nbsp;&gt;&nbsp;<input type="text" style="width:70px;margin-right:10%;"/>
                            <label><input name="" type="checkbox" value="" />异常占比 </label>&nbsp;&gt;&nbsp;<input type="text" style="width:70px;"/>&nbsp;&#37;
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label><input name="condition" type="checkbox" value="" />历史是否逃费 </label>
                        </th>
                        <td class="pn-fcontent">
                            <label style="margin-right:20px;"><input name="history" type="radio" value="" />是 </label>
                            <label><input name="history" type="radio" value="" />否 </label>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="modal-footer" id="footer5">
                <button type="button" class="pull-right btn btn-primary">确定</button>
                <button type="button" class="pull-right btn btn-warning" style="margin:0 30px;">重置</button>
                <button type="button" class="pull-right btn btn-danger" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<!--异常稽查车辆流水界面-->
<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width:90%;min-width:780px;">
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
                                <td class="pn-fcontent carLabel" style="white-space: normal;width: 94%;">
                                    <span>标识点异常:5次;</span>
                                    <span>ETC车牌不符:3次;</span>
                                    <span>ETC车牌不符:3次;</span>
                                    <span>ETC车牌不符:3次;</span>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="widget-bottom">
                        <a 	class="btn btn-s-md btn-primary pull-left" id="">上一条</a>
                        <a 	class="btn btn-s-md btn-primary pull-left" id="">下一条</a>
                        <a 	class="btn btn-s-md btn-primary pull-left" id="">图片对比</a>
                        <a 	class="btn btn-s-md btn-primary pull-right" id="" data-toggle="modal" data-target="#myModal3"data-keyboard="false" data-backdrop="static" tabindex="-1" >批量处理</a>
                        <a 	class="btn btn-s-md btn-primary pull-right" id="" data-toggle="modal" data-target="#myModal4"data-keyboard="false" data-backdrop="static" tabindex="-1" >配置列</a>
                        <a 	class="btn btn-s-md btn-primary pull-right" id="">导出</a>
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
                                <th class="a4">流水类型</th>
                                <th class="a5">入口时间</th>
                                <th class="a6">出口时间</th>
                                <th class="a7">轴组</th>
                                <th class="a8">入口类型</th>
                                <th class="a9">出口类型</th>
                                <th class="a10">OBU绑定车牌</th>
                                <th class="a11">识别车牌</th>
                                <th class="a12">标识点</th>
                                <th class="a13">基础金额</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td><input type="checkbox" class="subCheckbox"/></td>
                                <td class="a1">xxx</td>
                                <td class="a2">xxx</td>
                                <td class="a3">xxx</td>
                                <td class="a4">出口</td>
                                <td class="a5">2016-09-12</td>
                                <td class="a6">2016-08-15</td>
                                <td class="a7">xxx</td>
                                <td class="a8">xxx</td>
                                <td class="a9">xxx</td>
                                <td class="a10">xxx</td>
                                <td class="a11">xxx</td>
                                <td class="a12">xxx</td>
                                <td class="a13">xxx</td>
                            </tr>
                            <tr>
                                <td ><input type="checkbox" class="subCheckbox"/></td>
                                <td class="a1">xxx</td>
                                <td class="a2">xxx</td>
                                <td class="a3">xxx</td>
                                <td class="a4">出口</td>
                                <td class="a5">2016-09-12</td>
                                <td class="a6">2016-08-15</td>
                                <td class="a7">xxx</td>
                                <td class="a8">xxx</td>
                                <td class="a9">xxx</td>
                                <td class="a10">xxx</td>
                                <td class="a11">xxx</td>
                                <td class="a12">xxx</td>
                                <td class="a13">xxx</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <!--底部分页栏-->
                    <div class="widget-bottom">
                        <jsp:include page="../include/pager.jsp" />
                    </div>
                </div>
            </div>
            <div class="modal-footer" id="footer1">
                <button type="button" class="pull-right btn btn-primary">确定</button>
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
                <div class="modal-body configurationLine" style="padding-bottom:0;">
                    <label><input name="line" class="line1" type="checkbox" value="" />操作员 </label>
                    <label><input name="line" class="line2" type="checkbox" value="" />行驶公里数 </label>
                    <label><input name="line" class="line3" type="checkbox" value="" />重量 </label>
                    <label><input name="line" class="line4" type="checkbox" value="" />流水类型 </label>
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
    <form name="saveForm" id="saveForm" action="grayList_save.do" method="post" enctype="multipart/form-data">
        <div class="modal-dialog" style="width:80%;">
            <div class="modal-content" id="loading">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel2">异常稽查明细</h4>
                </div>
                <div class="modal-body">

                    <h5><b>流水信息：</b></h5>
                    <table class="pn-ftable lsxx-table" border="0" cellpadding="5" style="table-layout:fixed;white-space: normal;">
                        <tbody>
                        <tr>
                            <th>入口收费站:</th>
                            <td class="pn-fcontent"><font name="" class="motaiTdFont"></font></td>
                            <th>出口收费站:</th>
                            <td class="pn-fcontent"><font name="" class="motaiTdFont"/></td>
                            <th>入口时间:</th>
                            <td class="pn-fcontent"><font name="" class="motaiTdFont"/></td>
                        </tr>
                        <tr>
                            <th>出口时间:</th>
                            <td class="pn-fcontent"><font name="" class="motaiTdFont"></font></td>
                            <th>入口车型:</th>
                            <td class="pn-fcontent"><font name="" class="motaiTdFont"/></td>
                            <th>出口车型:</th>
                            <td class="pn-fcontent"><font name="" class="motaiTdFont"/></td>
                        </tr>
                        <tr>
                            <th>收费里程:</th>
                            <td class="pn-fcontent"><font name="" class="motaiTdFont"></font></td>
                            <th>入口车牌:</th>
                            <td class="pn-fcontent"><font name="" class="motaiTdFont"></font></td>
                            <th>出口车牌:</th>
                            <td class="pn-fcontent"><font name="" class="motaiTdFont"/></td>
                        </tr>
                        <tr>
                            <th>出口识别车牌:</th>
                            <td class="pn-fcontent"><font name="" class="motaiTdFont"/></td>
                            <th>OBU车牌:</th>
                            <td class="pn-fcontent"><font name="" class="motaiTdFont"/></td>
                            <th>轴组数:</th>
                            <td class="pn-fcontent"><font name="" class="motaiTdFont"/></td>
                        </tr>
                        <tr>
                            <th>入口车种:</th>
                            <td class="pn-fcontent"><font name="" class="motaiTdFont"/></td>
                            <th>出口车种:</th>
                            <td class="pn-fcontent"><font name="" class="motaiTdFont"/></td>
                            <th>超载率:</th>
                            <td class="pn-fcontent"><font name="" class="motaiTdFont"/></td>
                        </tr>
                        <tr>
                            <th>超载重量:</th>
                            <td class="pn-fcontent"><font name="" class="motaiTdFont"/></td>
                            <th>最终重量:</th>
                            <td class="pn-fcontent"><font name="" class="motaiTdFont"/></td>
                            <th></th>
                            <td class="pn-fcontent"><font name="" class="motaiTdFont"/></td>
                        </tr>
                        <tr>
                            <th>按键信息:</th>
                            <td class="pn-fcontent" colspan="5"><font name="" class="motaiTdFont"/></td>
                        </tr>
                        <tr>
                            <th>标识站:</th>
                            <td class="pn-fcontent" colspan="5"><font name="" class="motaiTdFont"/></td>
                        </tr>
                        <tr>
                            <th>特情事件:</th>
                            <td class="pn-fcontent" colspan="5"><font name="" class="motaiTdFont"/></td>
                        </tr>
                        <tr>
                            <th>入口照片:</th>
                            <td class="pn-fcontent"><img src="../../../images/icons0.png" alt="" class="imgsClass"/></td>
                            <th>出口照片:</th>
                            <td class="pn-fcontent"><img src="../../../images/icons0.png" alt="" class="imgsClass"/></td>
                            <th></th>
                            <td class="pn-fcontent"></td>
                        </tr>
                      </tbody>
                    </table>
                    <br/>
                    <h5><b>流水稽查：</b></h5>
                    <table class="pn-ftable table-bordered" border="0" cellpadding="8">
                        <tbody>
                        <tr>
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
                                <input type="text" id=" " maxlength="8" name=" "/>
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
                                <select name="" id=" " >
                                    <option value="">全部</option>
                                </select>
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
                                <!--  <a 	class="btn btn-s-md btn-info" id="">添加</a> -->
                                <input id="" type="file" name="" title="证据链" />
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="modal-footer" id="footer2">
                    <button type="button" class="btn btn-primary pull-left" >上一条</button>
                    <button type="button" class="btn btn-primary pull-left" >下一条</button>
                    <button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" >流水正常</button>
                    <button type="button" class="btn btn-primary">上传至灰名单</button>
                </div>
            </div>
        </div>
    </form>
</div>

<!--批量处理界面-->
<div class="modal fade" id="myModal3" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <form name="saveForm"  action="grayList_save.do" method="post" enctype="multipart/form-data">
        <div class="modal-dialog" style="width:80%;">
            <div class="modal-content" >
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel3">流水稽查批量处理</h4>
                </div>
                <div class="modal-body">
                    <table class="pn-ftable table-bordered" border="0" cellpadding="10">
                        <tbody>
                        <tr>
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
                                <input type="text" id=" " maxlength="8" name=" "/>
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
                                <select name="" id=" " >
                                    <option value="">全部</option>
                                </select>
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
                                <textarea style="width:95.5%" id="" name="roGrayList.vehInfo" rows="3" cols="50" maxlength="250" dataValidate="说明"></textarea>
                            </td>
                        </tr>
                        <tr>
                            <th>证据链:</th>
                            <td class="motaiTd" colspan="5">
                                <!--  <a 	class="btn btn-s-md btn-info" id="">添加</a> -->
                                <input id="" type="file" name="" title="证据链" />
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="modal-footer" id="footer3">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary">流水正常</button>
                    <button type="button" class="btn btn-primary">上传至灰名单</button>
                </div>
            </div>
        </div>
    </form>
</div>



<script>
    function controlLine(){
      for(var i=1; i<=13; i++){
          if($(".configurationLine .line"+i).attr('checked')){
              $(".ycls-table tr .a"+i).hide();
          }else{
              $(".ycls-table tr .a"+i).show();
          }
       }
        $('#myModal4').modal('hide');
    }

    $(function(){
        $('table.yccl-table tbody tr').dblclick(function(){
            $("#myModal1").modal({backdrop:false});
            $("#myModal1").modal({keyboard:false});
            $('#myModal1').modal('show');
        });
        $('table.ycls-table tbody  tr').dblclick(function(){
            $("#myModal2").modal({backdrop:false});
            $("#myModal2").modal({keyboard:false});
            $('#myModal2').modal('show');
        });
        /*全(不)选事件*/
        $('#checkAll').click(function(){
            $('input.subCheckbox').attr("checked",this.checked);
        });
    })
</script>
<jsp:include page="../include/pictureHuan.jsp">
    <jsp:param value="#myModal5" name="seletor"/>
</jsp:include>
</body>
</html>