<%--
   基础参数管理
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
        <script type="text/javascript">
        var log = {};
        $(function() {
            //邦定搜索按钮，添加点击事件查询数据列表
            $("#submit").on("click", function(event) {
                $("#myForm").submit();
                event.preventDefault();
            });

            //按回车键，查询数据列表
            document.onkeydown = function(event) {
                var ev = document.all ? window.event : event;
                if (ev.keyCode == 13) {
                    $("#myForm").submit();
                    ev.preventDefault();
                }
            };
        });
        function del(id) {
		    if (confirm("确认要删除吗？")) {
			     window.location.href = "basicParam_delete.do?basicParam.id=" + id;
		    }
	    }    	 
    </script>  
    </head>
    <body>
    <form name="myForm" id="myForm" action="basicParam_list.do" method="post">
    <ul class="breadcrumb">
        <li><i class="icon-home icon-2x"></i></li>
        <li>当前位置：${currentPosition}</li>
    </ul>
    <div class = "widget widget-table">
         <div class="widget-content">
             <table class="pn-ftable" border="0" cellpadding="10">
                <tbody>
                    <tr>
                        <th>参数名 :</th>
                        <td class="pn-fcontent"><input name = "basicParam.paramName" value = "${basicParam.paramName}" type = "text" size="30" maxlength="30" /></td>
                        <th>状　态 :</th>
                        <td class="pn-fcontent">
                              <select name = "basicParam.status">
                                 <option value="">全部</option>
                                 <option value="0" <c:if test="${basicParam.status=='0'}">selected</c:if>>启用</option>
                                 <option value="-1" <c:if test="${basicParam.status=='-1'}">selected</c:if>>停用</option>
                              </select>
                        </td>
                    </tr>
                </tbody>
             </table>
             
         </div>
         <!-- /widget-content -->  
         <div class="widget-bottom">
             <a class="btn btn-s-md btn-primary pull-right" id="submit" href="javascript:;">搜索</a>
             <a class="btn btn-s-md pull-right" href="basicParam_add.do">添加</a>            
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
                                <th>参数名</th>
                                <th>参数值</th>
                                <th>状态</th>                                
                                <th>参数编码</th>  
                                <th>操作</th>                              
                           </tr>
                     </thead>
                     <tbody>
                     <c:forEach items="${list}" var="item" varStatus="status">
                          <tr>
                                <td>${status.count}</td>
                                <td>${item.paramName}</td>
                                <td>${item.paramVal}</td>
                                <td> 
                                	 <c:if test="${item.status=='0'}">启用</c:if>
                                	 <c:if test="${item.status=='1'}">停用</c:if>
                                </td>
                                <td>${item.paramCode}</td>   
                                <td>
                                <a href="basicParam_edit.do?basicParam.id=${item.id}"
									class="btn btn-info"><i class="icon-pencil"></i>&nbsp;&nbsp;修改</a>
                                <a href="javascript:del('${item.id}');"
                                 class="btn btn-warning"><i class="icon-trash"></i>&nbsp;&nbsp;删除</a>                                
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