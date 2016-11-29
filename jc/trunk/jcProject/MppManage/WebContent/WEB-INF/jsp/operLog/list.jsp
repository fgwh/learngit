<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
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
<link rel="stylesheet" type="text/css" href="${basePath}/theme/${session.theme}/main.css" />
<link href="${basePath}/css/select2.css" rel="stylesheet"/>
<style> 
	.select2-container--default .select2-selection--single {
		border:none !important; 
	}
	td.pn-fcontent>span.select2-container{
		width:80% !important;
		border:1px solid #ddd !important;
	}
</style>
<script language="javascript" src="${basePath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${basePath}/js/quartz/plugins/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${basePath}/js/util.js"></script>
<script src="${basePath}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${basePath}/js/communicate.js"></script>
<script type="text/javascript" src="${basePath}/js/common.js"></script>
<script src="${basePath}/js/select2.js"></script>
<script type="text/javascript">
$(document).ready(function() { $("#e1").select2(); });
 var log = {};
  //验证查询条件
   log.checkDate = function() {
		if($("#startQueryDate").val() != ""){
		 	var v_start = stringToDate($("#startQueryDate").val());
		}
		if($("#endQueryDate").val() != ""){
		    var v_end = stringToDate($("#endQueryDate").val());
		}
		if($("#startQueryDate").val() != "" &&  $("#endQueryDate").val() != ""){
	        if (v_start > v_end) {
	            alert("结束时间不能小于起始时间");
	            return false;
	        }       
		}
        return true;
    };
    
    $(function() {
    	
    	 $("#search").on("click", function(event) {
             //验证查询条件合法性
             if (log.checkDate()) {
                 $("#myForm").submit();
             }
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
        var roadNo = $("#roadNo").val();
 		var stationId = $("#stationId").val();
 		selectStation(roadNo);
 		selectOperator(stationId);
    });
    
    
	function uploadOperLog() {
		var name = $("#e1").val(); 
		if(name == ""){
			alert("必须选择导出操作人");
			return false;
		}
		var beginDate = $("#beginDate1").val();
		var endDate = $("#endDate1").val();
		if (beginDate== "") {
			alert("必须选择开始日期");
			return false;
		}
		if (endDate == "") {
			alert("必须选择结束日期");
			return false; 
		}

		var v_start = stringToDate(beginDate);
		var v_end = stringToDate(endDate); 
		if (v_start > v_end) {
			alert("结束时间不能小于起始时间");
			return false;
		}
		if ((v_end - v_start)/86400000 > 7) {
			alert("下载最大时间跨度不可超过一周");
			return false;
		}
		
		$("#uploadName").val(name);
		$("#beginDate").val(beginDate);
		$("#endDate").val(endDate);
		return true;
	}
	
	//导出报表
	function doExport() {
		if (uploadOperLog()) {
			var name = $("#e1").val();
			var beginDate = $("#beginDate1").val();
			var endDate = $("#endDate1").val();
			var params = {
				'operLog.name' : name,
				'operLog.beginDate' : beginDate,
				'operLog.endDate' : endDate,
				'excelFlag' : "operlog"
			};
			url = uri + "admin/report_excelNum.do";
			$.post(url, params, reportExcelSucc, "json");
		}
	}
	function reportExcelSucc(jsonTemp) {
		var map = jsonTemp;
		var status = map.status;
		if (status == 0) {
			alert("暂无数据，不能导出");
		} else if (status == 1) {
			alert("暂时不支持大数据量导出");
		} else {
			var form = get("myForm");
			var url = form.action; //保存原来的action url
			form.action = "operaLogReport_doExportExcel.do";
			form.submit();
			form.action = url;
		}
	}
</script>
</head>
<body>
	<form name="myForm" id="myForm" action="operLog_list.do" method="post">
	<input  type="hidden" name="operLog.uploadName" id="uploadName"></input>
	<input  type="hidden" name="operLog.beginDate" id="beginDate"></input>
	<input  type="hidden" name="operLog.endDate" id="endDate"></input> 
		<ul class="breadcrumb">
			<li><i class="icon-home icon-2x"></i></li>
			<li>当前位置：${currentPosition}</li>
		</ul>
		<div class="widget widget-table">
			<div class="widget-content">
				<table class="pn-ftable" border="0" cellpadding="10">
					<tbody>
							<tr>
							<th>中心站 :</th>
							<td class="pn-fcontent"  >
								<select name="operLog.roadNo" id="roadNo" onchange="selectStation(this.value)"> 
										<option value="">全部</option> 
										<c:forEach items="${centerList}" var="centerStation">
											<option value="${centerStation.id}"
												<c:if test="${centerStation.id == operLog.roadNo}"><c:out value="selected"/></c:if>>
													${centerStation.orgName}</option>
										</c:forEach>
								</select>
							</td> 
							<th>收费站 :</th>
		                    <td class="pn-fcontent">
								<select name="operLog.stationNo"  id="stationNo"  onchange="selectOperator(this.value)">
									<input  type="hidden" id="stationId" value="${operLog.stationNo}"/>
									<%-- <option value="">全部</option> 
									<c:forEach items="${stationList}" var="station">
												<option value="${ station.id }"
													<c:if test="${station.id == stationNo}"><c:out value="selected"/></c:if>>
													${station.orgName}</option>
									</c:forEach> --%>
								</select>        								  
							</td>
		               		<th>模块名称 :</th>
							<td class="pn-fcontent"><input name="operLog.moduleName" value="${operLog.moduleName}"
								maxlength="20" type="text" /></td>
		                </tr>   
						<tr>
							<%-- <th>操作人 :</th>
							<td class="pn-fcontent"><input name="adminName" value="${adminName}"
								maxlength="20" type="text" /></td> --%>
						
							<th>检查人 :</th>
							<td class="pn-fcontent"><select name="operLog.name" id="name" >
								<input  type="hidden" id="nameId" value="${operLog.name}"/>
								</select>
							</td>
						
		                    <th>起始时间 :</th>
		                    <td class="pn-fcontent">
		                    	<input name="operLog.startQueryDate" id="startQueryDate" class="Wdate" style="background-color:white;" value="${operLog.startQueryDate}" 
		                    	onClick="javascript:WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss',skin:'whyGreen',startDate:'%y-%M-%d 00:00:00'})" readonly="readonly" type="text" />
		                    </td>
		                    <th>结束时间 :</th>
		                    <td class="pn-fcontent">
		                    	<input name="operLog.endQueryDate" id="endQueryDate" class="Wdate" style="background-color:white;" value="${operLog.endQueryDate}" 
		                    	onClick="javascript:WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss',skin:'whyGreen',startDate:'%y-%M-%d 23:59:59'})" readonly="readonly" type="text" />
		                    </td>
		                </tr>         
						<tr>
							<th>操作类型 :</th>
							<td class="pn-fcontent"><select name="operLog.operType">
									<option value="">全部</option>
									<option value="1" <c:if test="${operLog.operType==1}">selected</c:if>>添加</option>
									<option value="2" <c:if test="${operLog.operType==2}">selected</c:if>>更新</option>
									<option value="3" <c:if test="${operLog.operType==3}">selected</c:if>>删除</option>
									<option value="4" <c:if test="${operLog.operType==4}">selected</c:if>>查询</option>
							</select></td>
							<th></th>
							<td class="pn-fcontent"></td>
							<th></th>
							<td class="pn-fcontent"></td>
						</tr>
					</tbody>
				</table>
				
			</div>
			<!-- /widget-content -->
			<div class="widget-bottom">
					<input name="uri" type="hidden" value="${uri}"/>
					<input name="excelFlag" type="hidden" value="opraLog"/>
					<a class="btn btn-s-md btn-primary pull-right" id="search" href="javascript:;">查询</a>
					<shiro:hasPermission name="operLogAction:uploadLog">
						<a class="btn btn-s-md btn-info pull-right" data-toggle="modal" data-target="#myModal4">下载</a>
					</shiro:hasPermission>
				</div>
		</div>
		<div class="separator line"></div>
		<div class="widget widget-table">
			<div class="widget-header">
				<i class="icon-th-list"></i>
				<h5>用户列表</h5>
			</div>
			<!-- /widget-header -->
			<div class="widget-content widget-list">
				<table class="table table-striped table-bordered">
					<thead>
						<tr>
							<th>序号</th>
							<th>模块</th>
							<th>操作人</th>
							<th>操作类型</th>
							<th>操作时间</th>
							<th>操作详情</th>
							<th>操作ip</th>
							<th>备注</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="item" varStatus="status">
							<tr>
								<td>${status.count}</td>
								<td>${item.moduleName}</td>
								<td>${item.adminName}</td>
								<c:choose>
									<c:when test="${item.operType==5}">
										<td>移动端操作</td>
									</c:when>
									<c:when test="${item.operType==1}">
										<td>添加</td>
									</c:when>
									<c:when test="${item.operType==2}">
										<td>更新</td>
									</c:when>
									<c:when test="${item.operType==3}">
										<td>删除</td>
									</c:when>
									<c:otherwise>
								     	<td>查询</td>
								    </c:otherwise>   
								</c:choose>
								<td>${item.operTime}</td>
								<td style="text-align: left; white-space: normal; word-break: break-all;">${item.details}</td>
								<td>${item.ip}</td>
								<td>${item.remark}</td>
								
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
	<!-- 下载弹出框 -->
 <div class="modal fade" id="myModal4" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static"data-keyboard="false">
		<div class="modal-dialog" style="width: 960px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">操作日志下载</h4>
				</div>
				<div class="modal-body">
					<form id="addForm" action="" method="post">
						<table class="pn-ftable table-bordered" border="0"cellpadding="10">
							<tr>
								<th><span class="point">*</span>操作人:</th>
								<td class="pn-fcontent">
									  <select id="e1">
									  	<option value="">全部</option>
								        <c:forEach items="${adminList}" var="admin">
								        	<option value="${admin.staffNo}">${admin.name}</option>
								        </c:forEach>
								    </select>
			                    </td>
								<th><span class="point">*</span>开始日期</th>
								<td class="pn-fcontent">
								    <input style="background-color:white; width: 40% important; " type="text" name="" id="beginDate1" class="Wdate" value=""  onClick="javascript:WdatePicker({dateFmt: 'yyyy-MM-dd',skin:'whyGreen',maxDate:'%y-%M-%d'})"/>
								</td>
								<th><span class="point">*</span>结束日期</th>
								<td class="pn-fcontent">
									<input style="background-color:white; width: 40% important; " type="text" name="" id="endDate1" class="Wdate" value=""  onClick="javascript:WdatePicker({dateFmt: 'yyyy-MM-dd',skin:'whyGreen',maxDate:'%y-%M-%d'})"/>
								</td>
							</tr>
						</table>
					</form>
					<p></p>
					<p>注：最大时间跨度不可超过一周</p>
				</div>
				<div class="modal-footer" id="footer4">
					<button type="button"  onclick="doExport()" class="btn btn-success" >下载</button>
					<button type="button" class="btn btn-danger" data-dismiss="modal">返回</button>
				</div>
			</div>
		</div>
	</div>
   <script>
	  $(document).keydown(function(event){   
                if (event.keyCode == 13) {     
                    $('form').each(function() {       
                        event.preventDefault();     
                    });  
                }
            });
   </script>
</body>
</html>