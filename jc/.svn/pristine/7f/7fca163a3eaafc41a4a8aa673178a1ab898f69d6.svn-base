
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>识别率分析</title>
    <link rel="stylesheet" type="text/css"
          href="${basePath}/css/bootstrap.min.css" />
    <link rel="stylesheet"
          href="${basePath}/css/plugins/font-awesome/css/font-awesome.min.css" />
    <link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
    <link rel="stylesheet" type="text/css"
          href="${basePath}/theme/${session.theme}/main.css" />
        
   <script language="javascript" src="${basePath}/js/jquery.min.js"></script>
	<script type="text/javascript" src="${basePath}/js/echarts.js"></script>
	<script type="text/javascript" src="${basePath}/js/bar.js"></script>
	<script type="text/javascript" src="${basePath}/js/charts.js"></script>
    <script type="text/javascript" src="${basePath}/js/quartz/plugins/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${basePath}/js/util.js"></script>
	<script type="text/javascript" src="${basePath}/js/communicate.js"></script>
    <script type="text/javascript" src="${basePath}/js/common.js"></script>
    <script src="${basePath}/js/bootstrap.min.js"></script>
<script type="text/javascript">

</script>
	
</script>
</head>
<body>
<form name="myForm" id="myForm" action="Recanalysis_getRecanalysis.do" method="post">
    <ul class="breadcrumb">
        <li><i class="icon-home icon-2x"></i></li>
        <li>当前位置：${currentPosition}</li>
    </ul>
    <div class="widget widget-table">
        <div class="widget-content">
            <table class="pn-ftable" border="0" cellpadding="10">
                <tbody>
				<tr>
				<th><span class="point">*</span>中心站 :</th>
					<td class="pn-fcontent"  >
						<select name="conditions.centralStation" id="centralStation" onchange="selectStation(this.value)"> 
								<option value="">-请选择-</option> 
						</select>
					</td> 
					<th><span class="point">*</span>收费站 :</th>
                    <td class="pn-fcontent">
						<select name="conditions.toll"  id="toll"  onchange="selectOperator(this.value)">
						<option value="">-请选择-</option> 
						</select>        								  
					</td> 
					
							<th>车道</th>
							<td class="pn-fcontent"><input type="text" name="admin.name" maxlength="80" /></td>
				</tr>
					 	<tr>
					<th><span class="point">*</span>出入口 :</th>
			           <td class="pn-fcontent">
						<select name="conditions.gateWay"  id="gateWay" >						  
							<option value="all">出入口</option> 
							<option value="ex">出口</option> 
							<option value="en">入口</option> 
						</select>        								  
					</td>
							<th><span class="point">*</span>时间段</th>
							<td class="pn-fcontent">
								<div style="width:100%; overflow:auto;">
									<div class="pull-left" >
										<input style="width: 30px; border: 0;" type="checkbox" name="conditions.period" value="0:00-3:00"  id="period"/>0:00-3:00
											<input style="width: 30px; border: 0;" type="checkbox" name="conditions.period" value="03:00-06:00"  id="period"/>03:00-06:00
												<input style="width: 30px; border: 0;" type="checkbox" name="conditions.period" value="06:00-09:00"  id="period"/>06:00-09:00
													<input style="width: 30px; border: 0;" type="checkbox" name="conditions.period" value="09:00-12:00"  id="period"/>09:00-12:00
														<input style="width: 30px; border: 0;" type="checkbox" name="conditions.period" value="12:00-15:00"  id="period"/>12:00-15:00
															<input style="width: 30px; border: 0;" type="checkbox" name="conditions.period" value="15:00-18:00" id="period"/>15:00-18:00
														<input style="width: 30px; border: 0;" type="checkbox" name="conditions.period" value="18:00-21:00"  id="period"/>18:00-21:00
														<input style="width: 30px; border: 0;" type="checkbox" name="conditions.period" value="21:00-24:00"  id="period"/>21:00-24:00
									</div>
								</div>
							</td>
						</tr>
					       <tr>
                    <th><span class="point">*</span>开始自然日期 :</th>
                    <td class="pn-fcontent">
                    	<input name="conditions.startQueryDate" id="startQueryDate" class="Wdate" style="background-color:white;" value="${conditions.startQueryDate}" 
                    	onClick="javascript:WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" readonly="readonly" type="text" />
                    </td>
                    <th><span class="point">*</span>结束自然日期 :</th>
                    <td class="pn-fcontent">
                    	<input name="conditions.endQueryDate" id="endQueryDate" class="Wdate" style="background-color:white;" value="${conditions.endQueryDate}" 
                    	onClick="javascript:WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" readonly="readonly" type="text" />
                    </td>
                    <th></th>
                    <td class="pn-fcontent"></td>
                </tr>   
                </tbody>
            </table>
            
        </div>
        <!-- /widget-content -->
        <div class="widget-bottom">
                <input name="uri" type="hidden" value="${uri}"/>
               <a class="btn btn-s-md btn-primary pull-right" id="submit" href="javascript:;">查询</a>
            </div>
    </div>
    <div class="separator line"></div>
<div class="widget widget-table">
    <div class="widget widget-table" style="width:60%;float:left">
        <div class="widget-header">
            <i class="icon-th-list"></i>
            <h5>数据列表</h5>
        </div>
        <!-- /widget-header -->
        <div class="widget-content widget-list widget-scroll dataList">
            <table class="table table-striped table-bordered">
                <thead>
                <tr>
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
                <tbody>
                <c:forEach items="${resultList}" var="item" varStatus="status">
                    <tr>
                       <a href="javascript:showxiangqing();"  class="btn btn-info" value="1" id="showxiangqing">显示详情</a>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="widget-bottom">
                <jsp:include page="../include/pager.jsp" />
            </div>
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
<script type="text/javascript">
        $(function() {
            //邦定搜索按钮，添加点击事件查询数据列表
            $("#submit").on("click", function(event) {
                //验证查询条件合法性
             if (check()) { 
                    $("#myForm").submit();
               } 
                event.preventDefault();
            });

            //按回车键，查询数据列表
            document.onkeydown = function(event) {
                var ev = document.all ? window.event : event;
                if (ev.keyCode == 13) {
                    //验证查询条件合法性
                    if (checkTime()) {
                        $("#myForm").submit();
                    }
                    ev.preventDefault();
                }
            }; 
            var roadNo = $("#roadNo").val();
    		var stationId = $("#stationId").val();
    		selectStation(roadNo);
    		selectOperator(stationId);
        });
        		
		$(function(){
			//DrawMobileBar("type_container",["一类","二类","三类","四类","五类"],[[100, 200, 220, 120, 100],[40, 80, 50, 80,80, 70]],["总数","正常"]);
		    var source = $('#statisticsString').val();//源字符串： '标题;未通过车辆,通过车辆;项目一,项目二;45&50,55&60';
		    //空数据则不显示
		    if(source != '') {
		    	source = '按车型统计柱状图;未通过车辆(辆),通过车辆(辆);' + source;
			    //drawReportBarGraph("type_container",source,"按车型统计柱状图");
			    drawRightBarGraph("type_container",source);
		    }
		    
		});
		
		
		function check(){
			if($("#centralStation").val()==""){
				alert("请选择中心站");
				return false;
			}
			if($("#toll").val()==""){
							alert("请选择收费站");
							return false;
						}
			if($("#gateWay").val()==""){
				 alert("请选择出入口");
					return false;
			}
			if($("#period").val()==""){
				alert("请选择时间段");
				return false;
			}
			
			if($("#startQueryDate").val()==""){
		        alert("请输入开始时间！");
		        return false;
		    }
		    if($("#endQueryDate").val()==""){
		        alert("请输入结束时间！");
		        return false;
		    }
		    var startTime=$("#startQueryDate").val();
		    var start=new Date(startTime.replace("-", "/").replace("-", "/"));
		    var endTime=$("#endQueryDate").val();
		    var end=new Date(endTime.replace("-", "/").replace("-", "/"));
		    if(end<start){
		        alert("开始时间要小于结束时间");
		        return false;
		    }
		  
		    /*判断是否跨年 */
		   if(0!= endTime.indexOf(startTime.substring(0,4))){
			   alert("不可跨年查询");
			   return false;
		   }
		    
		    /*判断时间段是否连续选中*/
		  var p=  $("#period:checked").val();
		    var pArr=p.split(",");
		    for (var int = 0; int < pArr.length; int++) {
				
			}
		    
		    return true;
		}
	
</script>
</html>
