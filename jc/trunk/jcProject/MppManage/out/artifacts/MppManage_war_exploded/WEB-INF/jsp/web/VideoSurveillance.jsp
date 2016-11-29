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
    <title>视频监控</title>
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
       table.yccl-table tbody tr,table.ycls-table tr{
            cursor: pointer;
        }
    </style>
</head>
<body style="min-height:900px;">
<form name="myForm" id="myForm" action="" method="post">
    <ul class="breadcrumb">
        <li><i class="icon-home icon-2x"></i></li>
        <li>当前位置：视屏监控</li>
    </ul>
    <div class="widget widget-table">
        <div class="widget-content">
            <table class="pn-ftable" border="0" cellpadding="10" style="table-layout: fixed;">
                <tbody>
                    <tr>
                        <th><span class="point">*</span>开始时间:</th>
                        <td class="pn-fcontent">
                            <input style="background-color:white;" type="text"    name="" id="startDate" class="Wdate" value=""  onClick="javascript:WdatePicker({dateFmt: 'yyyy-MM-dd',skin:'whyGreen'})"/>
                        </td>
                        <th><span class="point">*</span>结束时间:</th>
                        <td class="pn-fcontent">
                            <input style="background-color:white;" type="text"  name="" id="endDate" class="Wdate" value=""  onClick="javascript:WdatePicker({dateFmt: 'yyyy-MM-dd',skin:'whyGreen'})"/>
                        </td>
                        <th><span class="point">*</span>中心站 :</th>
                        <td class="pn-fcontent">
                            <select id="" name="">
                                <option value="">全部</option>
                                <option>选项一</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th><span class="point">*</span>收费站 :</th>
                        <td class="pn-fcontent">
                            <select id="" name="">
                                <option value="">全部</option>
                                <option>选项一</option>
                            </select>
                        </td>

                        <th><span class="point">*</span>车道 :</th>
                        <td class="pn-fcontent">
                            <select id="" name="">
                                <option value="">全部</option>
                                <option>选项一</option>
                            </select>
                        </td>
                        <th></th>
                        <td class="pn-fcontent" colspan="">
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
            <h5>数据列表</h5>
        </div>
        <div class="widget-content widget-list widget-scroll">  <!--  style="min-height:500px;" -->
            <table class="table table-striped table-bordered yccl-table">
                <thead>
                <tr>
                    <th>收费站</th>
                    <th>车道</th>
                    <th>设备号</th>
                    <th>视屏存储路径</th>
                    <th>更新时间</th>
                    <th>开始时间</th>
                    <th>结束时间</th>
                    <th>详情</th>
                </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>xxx</td>
                        <td>xxx</td>
                        <td>xxx</td>
                        <td>xxx</td>
                        <td>xxx</td>
                        <td>xxx</td>
                        <td>xxx</td>
                        <td>
                            <a 	class="btn btn-s-md btn-info" id="" data-toggle="modal" data-target="#myModal5"data-keyboard="false" data-backdrop="static" tabindex="-1" >详情</a>
                        </td>
                    </tr>
                    <tr>
                        <td>xxx</td>
                        <td>xxx</td>
                        <td>xxx</td>
                        <td>xxx</td>
                        <td>xxx</td>
                        <td>xxx</td>
                        <td>xxx</td>
                        <td>
                            <a 	class="btn btn-s-md btn-info" id="" data-toggle="modal" data-target="#myModal5"data-keyboard="false" data-backdrop="static" tabindex="-1" >详情</a>
                        </td>
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

<!--视频详情界面-->
<div class="modal fade" id="myModal5" tabindex="-1" role="dialog" aria-labelledby="myMsodalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width:960px;">
        <div class="modal-content" >
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only" >关闭</span>
                </button>
                <h4 class="modal-title" id="myModalLabel5">视频详情</h4>
            </div>
            <div class="modal-body" style="text-align: center;">
                <video src="../../../images/demo.mp4" controls="controls" height="500px;"width="80%;" style="background-color: #000;">
                    您的浏览器暂不支持该类视屏播放，请更新浏览器！
                </video>
                <br>
                <div style="width:100%;overflow: hidden;margin-top:20px;">
                    <div style="width:40%;" class="pull-left">
                        <button type="button" class=" btn btn-lg btn-primary pull-right">截取</button>
                    </div>
                    <div style="width:40%" class="pull-right">
                        <button type="button" class=" btn btn-lg btn-primary pull-left">抓拍</button>
                    </div>
                </div>
            </div>
            <div class="modal-footer" id="footer5">
                <button type="button" class="pull-right btn btn-danger" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../include/pictureHuan.jsp">
    <jsp:param value="#myModal5" name="seletor"/>
</jsp:include>
</body>
</html>