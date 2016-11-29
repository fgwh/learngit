<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8" />
    <title>灰名单管理</title>
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
        <li>当前位置：灰名单管理</li>
    </ul>
    <div class="widget widget-table">
        <div class="widget-content">
            <div class="widget-header">
                <h5><span>路段</span>灰名单管理</h5>
            </div>
            <table class="pn-ftable" border="0" cellpadding="10">
                <tbody>
                <tr>
                    <th>灰名单范围：</th>
                    <td class="pn-fcontent">
                        <input type="radio" name="fanwei" id="quansheng"/><label for="quansheng">全省</label>
                        <input type="radio" name="fanwei" checked style="margin-left:20px;" id="luduan"/><label for="luduan">路段</label>
                    </td>
                    <th>车牌号：</th>
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
                    <th>车型：</th>
                    <td class="pn-fcontent"  >
                        <select name="" id="" onchange="">
                            <option value="">全部</option>
                            <option value="">选项二</option>
                            <option value="">选项三</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>客货车标识别：</th>
                    <td class="pn-fcontent"  >
                        <select name="" id="" onchange="">
                            <option value="">全部</option>
                            <option value="">选项二</option>
                            <option value="">选项三</option>
                        </select>
                    </td>
                    <th>车牌颜色：</th>
                    <td class="pn-fcontent"  >
                        <select name="" id="" onchange="">
                            <option value="">全部</option>
                            <option value="">选项二</option>
                            <option value="">选项三</option>
                        </select>
                    </td>
                    <th>逃费类型—大类：</th>
                    <td class="pn-fcontent"  >
                        <select name="" id="" onchange="">
                            <option value="">全部</option>
                            <option value="">选项二</option>
                            <option value="">选项三</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>逃费类型—中类：</th>
                    <td class="pn-fcontent"  >
                        <select name="" id="" onchange="">
                            <option value="">全部</option>
                            <option value="">选项二</option>
                            <option value="">选项三</option>
                        </select>
                    </td>
                    <th>逃费类型—细类：</th>
                    <td class="pn-fcontent"  >
                        <select name="" id="" onchange="">
                            <option value="">全部</option>
                            <option value="">选项二</option>
                            <option value="">选项三</option>
                        </select>
                    </td>
                    <th></th>
                    <td class="pn-fcontent"  >
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="widget-bottom">
            <a 	class="btn btn-s-md btn-warning pull-right">查询</a>
            <a 	class="btn btn-s-md pull-right" data-toggle="modal" data-target="#myModal5"id="">添加</a>
            <a 	class="btn btn-s-md btn-primary pull-right" data-toggle="modal" data-target="#myModal2" id="">导入Excel</a>
            <a 	class="btn btn-s-md btn-primary pull-right" data-toggle="modal" data-target="#myModal1"id="">导出Excel</a>
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
                    <th>逃费类型-大类</th>
                    <th>逃费类型-中类</th>
                    <th>逃费类型-细类</th>
                    <th>审核状态</th>
                    <th>数据源</th>
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
                    <td>xx</td>
                    <td>xxx</td>
                    <td>xxx</td>
                    <td>xxx</td>
                    <td>xxx</td>
                    <td>xxx</td>
                    <td>
                        <a 	class="btn btn-s-md btn-warning" data-toggle="modal" data-target="#myModal4" id="">详情</a>
                        <a 	class="btn btn-s-md btn-primary" data-toggle="modal" data-target="#myModal5" id="">修改</a>
                        <a 	class="btn btn-s-md btn-danger"id="">删除</a>
                    </td>
                </tr>
                <tr>
                    <td>1</td>
                    <td>xxxx-xx-xx</td>
                    <td>xx</td>
                    <td>xxx</td>
                    <td>xx</td>
                    <td>xx</td>
                    <td>xxx</td>
                    <td>xxx</td>
                    <td>xxx</td>
                    <td>xxx</td>
                    <td>xxx</td>
                    <td>
                        <a 	class="btn btn-s-md btn-primary" data-toggle="modal" data-target="#myModal3" id="">审核</a>
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
    <!--导入-->
    <div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" style="width:960px;">
            <div class="modal-content" >
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">&times;</span><span class="sr-only" >关闭</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel2">导入路段灰名单Excel</h4>
                </div>
                <div class="modal-body">
                    <table class="pn-ftable table-bordered" border="0" cellpadding="10">
                        <tbody>
                        <tr>
                            <th><span class="point">*</span>导入Excel</th>
                            <td class="pn-fcontent"><input type="file"  name=""  maxlength="20"/></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="modal-footer" id="footer2">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal">导入</button>
                </div>
            </div>
        </div>
    </div>
    <!--审核-->
    <div class="modal fade" id="myModal3" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width:960px;">
        <div class="modal-content" >
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only" >关闭</span>
                </button>
                <h4 class="modal-title" id="myModalLabel3">显示详情</h4>
            </div>
            <div class="modal-body">
                <table class="pn-ftable" border="0" cellpadding="10">
                    <tbody>
                    <tr>
                        <th>车牌:</th><td class="pn-fcontent"><font id="" class="motaiTdFont"></font></td>
                        <th>车型:</th><td class="pn-fcontent"><font id="" class="motaiTdFont"/></td>
                        <th>客货标识 :</th><td class="pn-fcontent"><font id="" class="motaiTdFont"></font></td>
                    </tr>
                    <tr>
                        <th>车辆品牌:</th><td class="pn-fcontent"><font id="" class="motaiTdFont"></font></td>
                        <th>车辆型号 :</th><td class="pn-fcontent"><font id="" class="motaiTdFont"></font></td>
                        <th>车种 :</th><td class="pn-fcontent"><font id="" class="motaiTdFont"></font></tr>
                    <tr>
                        <th>逃费类型-大类:</th><td class="pn-fcontent"><font id="" class="motaiTdFont"></font></td>
                        <th>逃费类型-中类:</th><td class="pn-fcontent"><font id="" class="motaiTdFont"></font></td>
                        <th>逃费类型-细类:</th><td class="pn-fcontent"><font id="" class="motaiTdFont"></font></td>
                    </tr>
                    <tr>
                        <th>逃费总次数:</th><td class="pn-fcontent"><font id="" class="motaiTdFont"></font></td>
                        <th>逃费总金额:</th><td class="pn-fcontent"><font id="" class="motaiTdFont"></font></td>
                        <th>列入拦截名单:</th>
                        <td class="pn-fcontent" colspan="5">
                            <font id="" class="motaiTdFont"></font>
                        </td>
                    </tr>
                    <tr>
                        <th>申请人:</th>
                        <td class="pn-fcontent">
                            <font id="" class="motaiTdFont"></font>
                        </td>
                        <th>申请时间:</th>
                        <td class="pn-fcontent">
                            <font id="" class="motaiTdFont"></font>
                        </td>
                        <th>申请机构:</th>
                        <td class="pn-fcontent">
                            <font id="" class="motaiTdFont"></font>
                        </td>
                    </tr>
                    <tr>
                        <th>说明:</th>
                        <td class="pn-fcontent" colspan="5">
                            <font id="" class="motaiTdFont"></font>
                        </td>
                    </tr>
                    <tr>
                        <th>附件:</th>
                        <td class="pn-fcontent" colspan="5">
                            <input style="width:200px;" type="text"name="" readonly/>
                            <a 	class="btn btn-s-md btn-info" id="">下载</a>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <div style="padding:20px 0;">
                    <a style="margin-left:50px;" class="btn btn-s-md btn-warning pull-right"  id="">不通过</a>
                    <a 	class="btn btn-s-md btn-success pull-right"  id="">审核通过</a>
                </div>
            </div>
            <div class="modal-footer" id="footer3">
                <button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
    <!--详情-->
    <div class="modal fade" id="myModal4" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" style="width:960px;">
            <div class="modal-content" >
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">&times;</span><span class="sr-only" >关闭</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel4">显示详情</h4>
                </div>
                <div class="modal-body">
                    <table class="pn-ftable" border="0" cellpadding="10">
                        <tbody>
                        <tr>
                            <th>车牌:</th><td class="pn-fcontent"><font id="" class="motaiTdFont"></font></td>
                            <th>车型:</th><td class="pn-fcontent"><font id="" class="motaiTdFont"/></td>
                            <th>客货标识 :</th><td class="pn-fcontent"><font id="" class="motaiTdFont"></font></td>
                        </tr>
                        <tr>
                            <th>车辆品牌:</th><td class="pn-fcontent"><font id="" class="motaiTdFont"></font></td>
                            <th>车辆型号 :</th><td class="pn-fcontent"><font id="" class="motaiTdFont"></font></td>
                            <th>车种 :</th><td class="pn-fcontent"><font id="" class="motaiTdFont"></font></tr>
                        <tr>
                            <th>逃费类型-大类:</th><td class="pn-fcontent"><font id="" class="motaiTdFont"></font></td>
                            <th>逃费类型-中类:</th><td class="pn-fcontent"><font id="" class="motaiTdFont"></font></td>
                            <th>逃费类型-细类:</th><td class="pn-fcontent"><font id="" class="motaiTdFont"></font></td>
                        </tr>
                        <tr>
                            <th>逃费总次数:</th><td class="pn-fcontent"><font id="" class="motaiTdFont"></font></td>
                            <th>逃费总金额:</th><td class="pn-fcontent"><font id="" class="motaiTdFont"></font></td>
                            <th>列入拦截名单:</th>
                            <td class="pn-fcontent" colspan="5">
                                <font id="" class="motaiTdFont"></font>
                            </td>
                        </tr>
                        <tr>
                            <th>申请人:</th>
                            <td class="pn-fcontent">
                                <font id="" class="motaiTdFont"></font>
                            </td>
                            <th>申请时间:</th>
                            <td class="pn-fcontent">
                                <font id="" class="motaiTdFont"></font>
                            </td>
                            <th>申请机构:</th>
                            <td class="pn-fcontent">
                                <font id="" class="motaiTdFont"></font>
                            </td>
                        </tr>
                        <tr>
                            <th>说明:</th>
                            <td class="pn-fcontent" colspan="5">
                                <font id="" class="motaiTdFont"></font>
                            </td>
                        </tr>
                        <tr>
                            <th>附件:</th>
                            <td class="pn-fcontent" colspan="5">
                                <input style="width:200px;" type="text"name="" readonly/>
                                <a 	class="btn btn-s-md btn-info" id="">下载</a>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="modal-footer" id="footer4">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>
                </div>
            </div>
        </div>
    </div>
    <!--修改添加-->
    <div class="modal fade" id="myModal5" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" style="width:960px;">
            <div class="modal-content" >
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">&times;</span><span class="sr-only" >关闭</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel5">可疑车辆名单登记</h4>
                </div>
                <div class="modal-body">
                    <table class="pn-ftable table-bordered" border="0" cellpadding="10">
                        <tbody>
                        <tr>
                            <th><span>*</span>车牌:</th>
                            <td class="pn-fcontent"  >
                                <input type="text"/>
                            </td>
                            <th><span>*</span>车牌颜色:</th>
                            <td class="pn-fcontent">
                                <input type="text"/>
                            </td>
                            <th>车型:</th>
                            <td class="pn-fcontent">
                                <select name="" id="" onchange="">
                                    <option value="">全部</option>
                                    <option value="">选项二</option>
                                    <option value="">选项三</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th><span>*</span>客货标识:</th>
                            <td class="pn-fcontent"  >
                                <select name="" id="" onchange="">
                                    <option value="">全部</option>
                                    <option value="">选项二</option>
                                    <option value="">选项三</option>
                                </select>
                            </td>
                            <th>逃费类型-大类:</th>
                            <td class="pn-fcontent">
                                <select name="" id="" onchange="">
                                    <option value="">全部</option>
                                    <option value="">选项二</option>
                                    <option value="">选项三</option>
                                </select>
                            </td>
                            <th>逃费类型-中类:</th>
                            <td class="pn-fcontent">
                                <select name="" id="" onchange="">
                                    <option value="">全部</option>
                                    <option value="">选项二</option>
                                    <option value="">选项三</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th>逃费类型-细类:</th>
                            <td class="pn-fcontent">
                                <select name="" id="" onchange="">
                                    <option value="">全部</option>
                                    <option value="">选项二</option>
                                    <option value="">选项三</option>
                                </select>
                            </td>
                            <th></th>
                            <td class="pn-fcontent"></td>
                            <th></th>
                            <td class="pn-fcontent"></td>
                        </tr>
                        <tr>
                            <th><span>*</span>说明:</th>
                            <td class="pn-fcontent" colspan="5">
                                <textarea style="width:100%" id="" name="" rows="3" cols="50" maxlength="300" ></textarea>
                            </td>
                        </tr>
                        <tr>
                            <th><span>*</span>申请人:</th>
                            <td class="pn-fcontent">
                                <input type="text" value="" name=""/>
                            </td>
                            <th><span>*</span>申请时间:</th>
                            <td class="pn-fcontent">
                                <input style="background-color:white;" type="text" name="" id=""  class="Wdate" value=""  onClick="javascript:WdatePicker({dateFmt: 'yyyy-MM-dd',skin:'whyGreen',maxDate:'%y-%M-%d'})"/>
                            </td>
                            <th><span>*</span>申请机构:</th>
                            <td class="pn-fcontent">
                                <select name="" id="" onchange="">
                                    <option value="">全部</option>
                                    <option value="">选项二</option>
                                    <option value="">选项三</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th><span>*</span>证据链:</th>
                            <td class="motaiTd" colspan="5">
                                <a 	class="btn btn-s-md btn-info" id="">选择文件</a>
                            </td>
                        </tr>
                        <tr>
                            <th><span>*</span>审批意见:</th>
                            <td class="pn-fcontent" colspan="5">
                                <textarea style="width:100%" id="" name="" rows="3" cols="50" maxlength="300" ></textarea>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="modal-footer" id="footer5">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal">保存</button>
                </div>
            </div>
        </div>
        </div>
</body>
</html>