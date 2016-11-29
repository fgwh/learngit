<%--任务日志查询--%>
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
    <link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
    <link rel="stylesheet" type="text/css"
          href="${basePath}/theme/${session.theme}/main.css" />
    <script language="javascript" src="${basePath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${basePath}/js/quartz/plugins/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${basePath}/js/util.js"></script>
	<script type="text/javascript">
		var log = {};
        $(function() {
            //邦定搜索按钮，添加点击事件查询数据列表
            $("#submit").on("click", function(event) {
            	//验证查询条件合法性
                if (log.checkDate()) {
                    $("#myForm").submit();
                }
                //event.preventDefault();
                //$("#myForm").submit();
                event.preventDefault();
            });

            //按回车键，查询数据列表
            document.onkeydown = function(event) {
                var ev = document.all ? window.event : event;
                if (ev.keyCode == 13) {
                	if (log.checkDate()) {
                    	$("#myForm").submit();
                	}
                    //$("#myForm").submit();
                    ev.preventDefault();
                }
            };
        });
        
        //验证查询条件
        log.checkDate = function() {
            var v_start = stringToDate($("#startQueryDate").val());//new Date($("#startQueryDate").val().replace("-", "/"));
            var v_end = stringToDate($("#endQueryDate").val());//new Date($("#endQueryDate").val().replace("-", "/"));
            if (v_start > v_end) {
                alert("结束时间不能小于起始时间");
                return false;
            }            
            return true;
        };
	</script>
  </head>
  
  <body>
    <form name="myForm" id="myForm" action="qrtzLog_list.do" method="post">
    <ul class="breadcrumb">
        <li><i class="icon-home icon-2x"></i></li>
        <li>当前位置：${currentPosition}</li>
    </ul>
    <div class="widget widget-table">
        <div class="widget-content">
            <table class="pn-ftable" border="0" cellpadding="10">
                <tbody>
                <tr>
                    <th>任务名:</th>
                    <td class="pn-fcontent"><input name="jobName" value="${jobName}" size="20" maxlength="20" type="text" /></td>
                    <th>状态:</th>
                    <td class="pn-fcontent">
						<select name="jobStatus">
						  <option value ="">全部</option>
						  <option value ="1" <c:if test="${jobStatus=='1'}">selected</c:if>>成功</option>
						  <option value="0" <c:if test="${jobStatus=='0'}">selected</c:if>>失败</option>
						</select>
					</td>
                	<th></th>
                	<td class="pn-fcontent"></td>
                </tr>
				<tr>					
                    <th>起始时间 ：</th>
                    <td class="pn-fcontent">
                    	<input name="startQueryDate" id="startQueryDate" class="Wdate" style="background-color:white;" value="${startQueryDate}" 
                    	onClick="javascript:WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" readonly="readonly" type="text" />
                    </td>
                    <th>结束时间 ：</th>
                    <td class="pn-fcontent">
                    	<input name="endQueryDate" id="endQueryDate" class="Wdate" style="background-color:white;" value="${endQueryDate}" 
                    	onClick="javascript:WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" readonly="readonly" type="text" />
                    </td>
                	<th></th>
                	<td class="pn-fcontent"></td>
                </tr>   
                </tbody>
            </table>
            <div class="widget-bottom">
                <a class="btn btn-s-md btn-primary pull-right" id="submit" href="javascript:;">搜索</a>
            </div>
        </div>
        <!-- /widget-content -->
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
                    <th>任务名</th>
                    <th>任务组</th>
                    <th>描述</th>
                    <th>任务类名</th>
					<th>执行时间</th>
                    <th>任务状态</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${list}" var="item" varStatus="status">
                    <tr>
                        <td>${status.count}</td>
                        <td>${item[0]}</td>
                        <td>${item[1]}</td>
                        <td style="overflow: hidden; text-overflow: ellipsis; white-space: nowrap;"title="${item[2]}">${item[2]}</td>
                        <td style="text-align: left; white-space: normal; word-break: break-all;">${item[3]}</td>
						<td>${item[4]}</td>
                        <td>${item[5]}</td>
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