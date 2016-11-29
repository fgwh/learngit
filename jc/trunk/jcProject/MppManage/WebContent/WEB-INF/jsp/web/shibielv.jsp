<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8" />
    <title>识别率</title>
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
</head>
<body>
<form name="myForm" id="myForm" action="report_type.do" method="post">
    <ul class="breadcrumb">
        <li><i class="icon-home icon-2x"></i></li>
        <li>当前位置：识别率报表</li>
    </ul>
    <div class="widget widget-table">
        <div class="widget-content">
            <div class="widget-header">
                <h5>识别率分析报表</h5>
            </div>
            <table class="pn-ftable" border="0" cellpadding="10">
                <tbody>
                <tr>
                    <th>中心站 :</th>
                    <td class="pn-fcontent"  >
                        <select name="" id="" onchange="">
                            <option value="">全部</option>
                            <option value="">选项二</option>
                            <option value="">选项三</option>
                        </select>
                    </td>
                    <th>收费站 :</th>
                    <td class="pn-fcontent"  >
                        <select name="" id="" onchange="">
                            <option value="">全部</option>
                            <option value="">选项二</option>
                            <option value="">选项三</option>
                        </select>
                    </td>
                    <th>出入口:</th>
                    <td class="pn-fcontent"  >
                        <select name="" id="" onchange="">
                            <option value="">全部</option>
                            <option value="">选项二</option>
                            <option value="">选项三</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>时间段:</th>
                    <td style="font-size:12px;" >
                        <input style="width: 15px; border: 0;" type="checkbox" name="roles" value="一个月"/>一个月
                        <input style="width: 15px; border: 0;" type="checkbox" name="roles" value="二个月"/>两个月
                        <input style="width: 15px; border: 0;" type="checkbox" name="roles" value="三个月"/>三个月
                        <input style="width: 15px; border: 0;" type="checkbox" name="roles" value="四个月"/>四个月
                    </td>
                    <th>起始时间 ：</th>
                    <td class="pn-fcontent">
                        <input name="" id="" class="Wdate" style="background-color:white;"
                               onClick="javascript:WdatePicker({dateFmt: 'yyyy-MM-dd',skin:'whyGreen',maxDate:'%y-%M-%d'})" readonly="readonly" type="text" />
                    </td>
                    <th>结束时间 ：</th>
                    <td class="pn-fcontent">
                        <input name="conditions.endQueryDate" id="" class="Wdate" style="background-color:white;"
                               onClick="javascript:WdatePicker({dateFmt: 'yyyy-MM-dd',skin:'whyGreen',maxDate:'%y-%M-%d'})" readonly="readonly" type="text" />
                    </td>
                </tr>
                </tbody>
            </table>

        </div>
        <!-- /widget-content -->
        <div class="widget-bottom">
            <a class="btn btn-s-md btn-primary pull-right" id="" href="javascript:;">查询</a>
        </div>
    </div>
    <div class="separator line"></div>
    <div class="widget widget-table">
        <div class="widget widget-table" style="width:60%;float:left">
            <div class="widget-header">
                <i class="icon-th-list"></i>
                <h5>数据列表</h5>
            </div>
            <div class="widget-content widget-list widget-scroll dataList">
                <table class="table table-striped table-bordered">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>路段</th>
                        <th>中心站</th>
                        <th>收费站</th>
                        <th>出入口</th>
                        <th>时间段</th>
                        <th>号码识别成功率</th>
                        <th>号码识别失败率</th>
                        <th>详情</th>
                    </tr>
                    </thead>
                    <tbody id="databody">
                    <tr>
                        <td>1</td>
                        <td>xx</td>
                        <td>xxxx</td>
                        <td>xx</td>
                        <td>xxx</td>
                        <td>xxx</td>
                        <td>xxx</td>
                        <td>xxx</td>
                        <td>
                            <a href=""class="btn btn-warning" value="1" id="showxiangqing" data-toggle="modal" data-target="#myModal5">详情</a>
                        </td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>xx</td>
                        <td>xxxx</td>
                        <td>xx</td>
                        <td>xxx</td>
                        <td>xxx</td>
                        <td>xxx</td>
                        <td>xxx</td>
                        <td>
                            <a href=""class="btn btn-warning" value="1" id="showxiangqing" data-toggle="modal" data-target="#myModal5">详情</a>
                        </td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td>xx</td>
                        <td>xxxx</td>
                        <td>xx</td>
                        <td>xxx</td>
                        <td>xxx</td>
                        <td>xxx</td>
                        <td>xxx</td>
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
            <!-- /widget-content -->
        </div>

        <!--图表插到这里-->
        <div id="type_container" style="height:350px;width:39%;float:left"></div>
        <input id="statisticsString" type="hidden" value="${statisticsString }"/>
        <script>
            //DrawPoeBase("考勤状态图",[ ['名字1',50] , ['名字2',40] ,['名字3',100] ]);//container
        </script>
    </div>

</form>
</body>
</html>