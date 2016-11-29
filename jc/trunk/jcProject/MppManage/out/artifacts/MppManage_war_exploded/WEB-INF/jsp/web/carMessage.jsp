<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8" />
    <title>车辆信息</title>
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
<body style="min-height:900px;">
<form name="myForm" id="myForm" action="declare_list.do" method="post">
    <ul class="breadcrumb">
        <li><i class="icon-home icon-2x"></i></li>
        <li>当前位置：车辆信息查询</li>
    </ul>
    <div class="widget widget-table">
        <div class="widget-content">
            <div class="widget-header">
                <h5>车辆信息查询</h5>
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
                    <th>入口流水 :</th>
                    <td class="pn-fcontent">
                        <input type="text"  name="" onclick=""/>
                    </td>
                </tr>
                <tr>
                    <th>车牌号 :</th>
                    <td class="pn-fcontent"><select class="pull-left" name="" id="" style="width:100px;">
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
                    </select> <input type="text" size="6" maxlength="6" name="" id=""value="" seleted /></td>
                    <th>复合卡表面卡号 :</th>
                    <td class="pn-fcontent">
                        <input type="text"  name="" onclick=""/>
                    </td>
                    <th>CPU卡号 :</th>
                    <td class="pn-fcontent">
                        <input type="text"  name="" onclick=""/>
                    </td>
                </tr>
                <tr>
                    <th>开始日期:</th>
                    <td class="pn-fcontent">
                        <input style="background-color:white;" type="text" name="" id=""  class="Wdate" value=""  onClick="javascript:WdatePicker({dateFmt: 'yyyy-MM-dd',skin:'whyGreen',maxDate:'%y-%M-%d'})"/>
                    </td>
                    <th>结束日期</th>
                    <td class="pn-fcontent">
                        <input style="background-color:white; width: 40% important; " type="text" name="" id="" class="Wdate" value=""  onClick="javascript:WdatePicker({dateFmt: 'yyyy-MM-dd',skin:'whyGreen',maxDate:'%y-%M-%d'})"/>
                    </td>
                    <th>出口流水:</th>
                    <td class="pn-fcontent">
                        <input type="text"  name="" onclick=""/>
                    </td>
                </tr>
                <tr>
                    <th>异常情况:</th>
                    <td class="pn-fcontent">
                        <a href="" class="btn btn-primary btn-s" data-toggle="modal" data-target="#myModal4">选择异常情况</a>
                    </td>
                    <td class="pn-fcontent" colspan="4">
                        赔款：付款方式；车型不符； 无通行卷(无卡)；U型行驶(回头)；超时行驶(超时)；闯关(冲卡)；黑名单；灰名单；不可读通行卷(坏卡)；无效通行卷(车牌不符)；
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="widget-bottom">
            <a 	class="btn btn-s-md btn-primary pull-right" id="">搜索</a>
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
                    <th >日期</th>
                    <th>路段</th>
                    <th>车道标号</th>
                    <th>车道类型</th>
                    <th>车牌</th>
                    <th>车型</th>
                    <th>出口收费站</th>
                    <th>入口站</th>
                    <th>通行卡号</th>
                    <th>异常情况</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody id="databody">
                <tr>
                    <td>1</td>
                    <td>xxxx-xx-xx</td>
                    <td>xx</td>
                    <td>xxx</td>
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
                    <td>xxxx-xx-xx</td>
                    <td>xx</td>
                    <td>xxx</td>
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
                    <td>xxxx-xx-xx</td>
                    <td>xx</td>
                    <td>xxx</td>
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
    </div>
</form>
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
            <div class="modal-body">
                <form action="" method=" ">
                    <div>
                        <label><input name=" " type="checkbox" value="" />可逆向 </label>
                        <label><input name=" " type="checkbox" value="" />丢卡 </label>
                        <label><input name=" " type="checkbox" value="" />货物按客车收费 </label>
                        <label><input name=" " type="checkbox" value="" />重大节假日免费通行</label>
                    </div>
                    <div>
                        <label><input name=" " type="checkbox" value="" />配矿 </label>
                        <label><input name=" " type="checkbox" value="" />重新发卷 </label>
                        <label><input name=" " type="checkbox" value="" />PSAM卡黑名单 </label>
                        <label><input name=" " type="checkbox" value="" />入出口客货不符</label>
                    </div>
                </form>
            </div>
            <div class="modal-footer" id="footer4">
                <button type="button" class="btn btn-danger" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary " data-dismiss="modal">选择</button>
            </div>
        </div>
    </div>
</div>
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
                        <th>收费流水 :</th><td class="motaiTd"><font id="" class="motaiTdFont"></font>
                    </td>
                        <th>收费金额:</th><td class="motaiTd"><font id="" class="motaiTdFont"/>
                    </td>
                        <th>收费员 :</th><td class="motaiTd"><font id="" class="motaiTdFont"></font>
                    </td>
                    </tr>
                    <tr>
                        <th>出口时间 :</th><td class="motaiTd"><font id="" class="motaiTdFont"></font>
                    </td>
                        <th>入口时间 :</th><td class="motaiTd"><font id="" class="motaiTdFont"></font>
                    </td>
                        <th>出口收费站 :</th><td class="motaiTd"><font id="" class="motaiTdFont"></font>
                    </tr>
                    <tr>
                        <th>入口收费站:</th><td class="motaiTd"><font id="" class="motaiTdFont"></font>
                    </td>
                        <th>通行卡号:</th><td class="motaiTd"><font id="" class="motaiTdFont"></font>
                    </td>
                        <th>车道类型:</th><td class="motaiTd"><font id="" class="motaiTdFont"></font>
                    </td>
                    </tr>
                    <tr>
                        <th>初判车型:</th><td class="motaiTd"><font id="" class="motaiTdFont"></font>
                    </td>
                        <th>最终车型:</th><td class="motaiTd"><font id="" class="motaiTdFont"></font>
                    </td>
                        <th>轴组组成:</th><td><font id=""class="motaiTdFont"></font> <font id=""></font>
                    </td>
                    </tr>
                    <tr  >
                        <th>识别车牌:</th><td ><font id=""class="motaiTdFont"></font>
                    </td>
                        <th>最终车牌:</th><td ><font id=""class="motaiTdFont"></font>
                    </td>
                        <th>车种:</th><td class="motaiTd"><font id="" class="motaiTdFont"></font>
                    </td>
                    </tr>
                    <tr>
                        <th>特殊事件:</th><td class="motaiTd" colspan="5"><font id="" class="motaiTdFont"></font>
                    </td>
                    </tr>
                    <tr style="height:110px;" id="TrShow" >
                        <th>入口车辆照 :</th>
                        <td colspan="5" id="driverImg">
                            <img src="/images/more.png" id="qianMingShow3" class="qianMingShow2" />
                        </td>
                    </tr>
                    <tr height="110" id="hwjlDiv">
                        <th>出口车辆照 :</th>
                        <td colspan="5">
                            <div style="padding:5px 10px;">
                                <img src="/images/more.png" class="imgFirstClass displayClass" id="hwjl1" />
                                <img src="/images/more.png" class="imgsClass displayClass" id="hwjl2" />
                                <img src="/images/more.png" class="imgsClass displayClass" id="hwjl3" />
                            </div>
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
</body>
</html>