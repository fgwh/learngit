<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8" />
    <title>统计分析</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <script src="../../../js/bootstrap.min.js"></script>
    <style>
        .table-striped >thead >tr > th {
            background: none;
            background-color: #f0f0f0;
        }
        .table-striped > tbody > tr:nth-child(2n+1) > td {
            background-color: #fff;
        }
        .table-striped > tbody > tr:nth-child(2n) > td {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body style="min-height:900px;">
<form name="myForm" id="myForm" action="declare_list.do" method="post">
    <ul class="breadcrumb">
        <li><i class="icon-home icon-2x"></i></li>
        <li>当前位置：车辆信息查询</li>
    </ul>
    <div class="widget widget-table">
        <div class="widget-content">
            <div class="widget-header">
                <h5>查询条件</h5>
            </div>
            <table class="pn-ftable" border="0" cellpadding="10">
                <tbody>
                <tr>
                    <th>交通道路信息表 :</th>
                    <td class="pn-fcontent" colspan="5">
                        <input type="radio" onclick="radioOnclick()" name="" value="1" style="font-size: 12px;"/>年表
                        <input onclick="radioOnclick()" type="radio" name="" style="margin-left: 20px; font-size: 12px;" value="2"/>月表
                        <input onclick="radioOnclick()" type="radio" name="" style="margin-left: 20px; font-size: 12px;" value="2"/>日表
                    </td>
                </tr>
                <tr>
                    <th>年份 :</th>
                    <td class="pn-fcontent">
                        <input style="background-color:white;" type="text" name="" id=""  class="Wdate" value=""  onClick="javascript:WdatePicker({dateFmt: 'yyyy-MM-dd',skin:'whyGreen',maxDate:'%y-%M-%d'})"/>
                    </td>
                    <th>月份 :</th>
                    <td class="pn-fcontent">
                        <input style="background-color:white;" type="text" name="" id=""  class="Wdate" value=""  onClick="javascript:WdatePicker({dateFmt: 'yyyy-MM-dd',skin:'whyGreen',maxDate:'%y-%M-%d'})"/>

                    </td>
                    <th>日期:</th>
                    <td class="pn-fcontent">
                        <input style="background-color:white;" type="text" name="" id=""  class="Wdate" value=""  onClick="javascript:WdatePicker({dateFmt: 'yyyy-MM-dd',skin:'whyGreen',maxDate:'%y-%M-%d'})"/>
                    </td>
                </tr>
               <tr>
                   <th>中心站：</th>
                   <td class="pn-fcontent"  >
                       <select name="" id="" onchange="">
                           <option value="">全部</option>
                           <option value="">选项二</option>
                           <option value="">选项三</option>
                       </select>
                   </td><th>收费站：</th>
                   <td class="pn-fcontent"  >
                       <select name="" id="" onchange="">
                           <option value="">全部</option>
                           <option value="">选项二</option>
                           <option value="">选项三</option>
                       </select>
                   </td><th>出入口站：</th>
                   <td class="pn-fcontent"  >
                       <select name="" id="" onchange="">
                           <option value="">全部</option>
                           <option value="">选项二</option>
                           <option value="">选项三</option>
                       </select>
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
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th rowspan="2">序号</th>
                         <th rowspan="2">路段</th>
                         <th rowspan="2">收费站</th>
                        <th rowspan="2">出入口</th>
                        <th colspan="7">客车车流量</th>
                        <th colspan="7">货车车流量</th>
                        <th rowspan="2">操作</th>
                    </tr>
                    <tr>
                        <th>一车型</th>
                        <th>二车型</th>
                        <th>三车型</th>
                        <th>四车型</th>
                        <th>五车型</th>
                        <th>其他</th>
                        <th>总计</th>
                        <th>一车型</th>
                        <th>二车型</th>
                        <th>三车型</th>
                        <th>四车型</th>
                        <th>五车型</th>
                        <th>其他</th>
                        <th>总计</th>
                    </tr>
                </thead>
                <tbody id="databody">
                <tr>
                    <td>1</td>
                    <td>路段1</td>
                    <td>收费站1</td>
                    <td>出口</td>
                    <td>11</td>
                    <td>12</td>
                    <td>13</td>
                    <td>14</td>
                    <td>15</td>
                    <td>1</td>
                    <td>67</td>
                    <td>1</td>
                    <td>2</td>
                    <td>3</td>
                    <td>4</td>
                    <td>5</td>
                    <td>1</td>
                    <td>16</td>
                    <td>
                        <a href=""class="btn btn-warning" value="1" id="showxiangqing" data-toggle="modal" data-target="#myModal5">详情</a>
                    </td>
                </tr> <tr>
                    <td>1</td>
                    <td>路段1</td>
                    <td>收费站1</td>
                    <td>出口</td>
                    <td>11</td>
                    <td>12</td>
                    <td>13</td>
                    <td>14</td>
                    <td>15</td>
                    <td>1</td>
                    <td>67</td>
                    <td>1</td>
                    <td>2</td>
                    <td>3</td>
                    <td>4</td>
                    <td>5</td>
                    <td>1</td>
                    <td>16</td>
                    <td>
                        <a href=""class="btn btn-warning" value="1" id="showxiangqing" data-toggle="modal" data-target="#myModal5">详情</a>
                    </td>
                </tr>

                </tbody>
            </table>
        </div>
        <div class="widget-bottom">
            <jsp:include page="../include/pager.jsp" />
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
                <h4 class="modal-title" id="myModalLabel5">车流量统计详情</h4>
            </div>
            <div class="modal-body">
                这里插入图表
            </div>
            <div class="modal-footer" id="footer5">
                <button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>