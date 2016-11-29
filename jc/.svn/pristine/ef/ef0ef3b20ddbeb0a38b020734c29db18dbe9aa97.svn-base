<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>

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
		
	<style>
		/* 表达式的样式 */
		tr.moreTimeDay2 td span,tr.moreTimeDay2 td input,tr.moreTimeDay2 td select{float:left;margin-right:5px;}
		tr.moreTimeDay2 td span{line-height: 26px;}
		tr.moreTimeWeek td input[type="checkbox"]{
			margin: 10px 2px 0 15px;
  		    position: relative;
    		top: 2px;
    		cursor: pointer;
		}
		tr.moreTimeWeek td input[value="1"]{margin-left:0px;}
		tr.moreTimeWeek td label{font-weight:normal;cursor:pointer}
	</style>	
		
</head>

<body style="height: 900px;">
	<form  action="jobTask_save.do" method="post" name="form">
		<ul class="breadcrumb">
			<li><i class="icon-home icon-2x"></i></li>
			<li>当前位置：${currentPosition}&nbsp;>&nbsp;查看任务</li>
		</ul>
		<div class="widget widget-edit">
			<div class="widget-content">
				<input type="hidden" value="false" name="volatility">
					<input type="hidden" value="true" name="durability">
					<input type="hidden" value="false" name="shouldRecover">
				<table id="taskTable" class="pn-ftable table-bordered" border="0" cellpadding="10">
					<tbody>
						<tr>
							<th><span  class="point">*</span>任务名称</th>
							<td class="pn-fcontent"><input type="hidden" name="jobname" id="jobname" value="${dwrJob.name }">
									${dwrJob.name }</td>
							<td class="pn-info"></td>
						</tr>
						<tr>
							<th><span class="point">*</span>任务组</th>
							<td class="pn-fcontent"><input type="hidden" name="groupname" id="groupname" value="${dwrJob.group }">
									${dwrJob.group }</td>
							<td class="pn-info"></td>
						</tr>
						<tr>
							<th><span class="point">*</span>完整类名</th>
							<td class="pn-fcontent"><input type="hidden" name="className" id="className" value="${dwrJob.className }">
									${dwrJob.className }</td>
							<td class="pn-info"></td>
						</tr>
						<tr>
								<th >
									任务状态
								</th>
								<td class="pn-fcontent">
									${dwrJob.triggerStatus}
								</td>
								<td class="pn-info"></td>
							</tr>
						<tr>
							<th>任务描述</th>
							<td class="pn-fcontent">
								<textarea rows="3" name="description" id="description">${dwrJob.description }</textarea>
								</td>
							<td class="pn-info"></td>
						</tr>
						<tr>
								<th >
									执行时刻:
								</th>
								<td class="pn-fcontent">
									${dwrJob.triggerDesc}&nbsp;
								</td>
								<td class="pn-info"></td>
							</tr>
						<tr <c:if test="${dwrJob.triggerType eq 0}">style="display:none;"</c:if>>
							<td colspan="3"><a href="#"
								class="pull-left btn btn-info" id="optionBtn"
								style="margin: 3px;"
								onclick="javascript:_option();return false;"> 切换表达式 </a></td>
						</tr>
						<tr <c:if test="${dwrJob.triggerType eq 0}">style="display:none;"</c:if> id="cronTr" >
							<th><span class="point">*</span>cron:</th>
							<td class="pn-fcontent"><input type="text"
								name="cronExpression" id="cronExpression" placeholder="直接输入表达式" />
							</td>
							<td class="pn-info"><a href="#" class="btn btn-info hide">表达式(cron)说明</a></td>
						</tr>
						<tr id="conHelpTr" style="display: none;">
														<th>cron使用说明</th>
														<td class="pn-fcontent"><span class="point">格式: [秒] [分] [小时]
																[日] [月] [周]</span><br /> <span class="point">取值范围：</span><br />
															&nbsp; &nbsp;[秒]&nbsp; 0-59 , - * / <br /> &nbsp;
															&nbsp;[分]&nbsp; 0-59 , - * / <br /> &nbsp;
															&nbsp;[小时]&nbsp; 0-23 , - * / <br /> &nbsp;
															&nbsp;[日]&nbsp; 1-31 , - * ? / L W <br /> &nbsp;
															&nbsp;[月]&nbsp; 1-12 or JAN-DEC , - * / <br /> &nbsp;
															&nbsp;[周]&nbsp; 1-7 or SUN-SAT , - * ? / L # <br /> <span
															class="point">通配符说明:</span><br /> &nbsp; &nbsp;[*]
															&nbsp;表示所有值. 例如:在分的字段上设置 "*",表示每一分钟都会触发。<br /> &nbsp;
															&nbsp;[?]
															&nbsp;表示不指定值。使用的场景为不需要关心当前设置这个字段的值。例如:要在每月的10号触发一个操作，但不关心是周几，所以需要周位置的那个字段设置为"?"
															具体设置为 0 0 0 10 * ?<br /> &nbsp; &nbsp;[-] &nbsp;表示区间。例如
															在小时上设置 "10-12",表示 10,11,12点都会触发。<br /> &nbsp; &nbsp;[,]
															&nbsp;表示指定多个值。例如在周字段上设置 "MON,WED,FRI" 表示周一，周三和周五触发<br />
															&nbsp; &nbsp;[/] &nbsp;用于递增触发。如在秒上面设置"5/15"
															表示从5秒开始，每增15秒触发(5,20,35,50)。
															在月字段上设置'1/3'所示每月1号开始，每隔三天触发一次。<br /> &nbsp; &nbsp;[L]
															&nbsp;表示最后的意思。在日字段设置上，表示当月的最后一天(依据当前月份，如果是二月还会依据是否是润年[leap]),
															在周字段上表示星期六，相当于"7"或"SAT"。如果在"L"前加上数字，则表示该数据的最后一个。例如在周字段上设置"6L"这样的格式,则表示“本月最后一个星期五"
															<br /> &nbsp; &nbsp;[W] &nbsp;表示离指定日期的最近那个工作日(周一至周五).
															例如在日字段上设置"15W"，表示离每月15号最近的那个工作日触发。如果15号正好是周六，则找最近的周五(14号)触发,
															如果15号是周未，则找最近的下周一(16号)触发.如果15号正好在工作日(周一至周五)，则就在该天触发。如果指定格式为
															"1W",它则表示每月1号往后最近的工作日触发。如果1号正是周六，则将在3号下周一触发。<br />
															&nbsp; &nbsp;[#]
															&nbsp;表示序号(每月的第几个周几)。例如在周字段上设置"6#3"表示在每月的第三个周六.注意如果指定"#5",正好第五周没有周六，则不会触发该配置。
															<br /></td>
															<td class="pn-info"></td>
													</tr>
						<c:if test="${dwrJob.triggerType eq 1}">
						<tr id="taskDetailTr" style="display: none;">
							<th><span class="point">*</span>计划类型</th>
							<td class="pn-fcontent"><select name="taskTypeSel"
								id="taskTypeSel" onchange="chooseTaskType(this)"
								disabled="disabled">
									<option value="-1">请选择</option>
									<option value="1">重复执行</option>
									<option value="2">执行一次</option>
							</select></td>
							<td class="pn-info"></td>
						</tr>
						<tr class="oneTime" style="display: none;">
							<th><span class="point">*</span>日期</th>
							<td class="pn-fcontent"><input type="text" name="taskDate"
								id="taskDate" disabled="disabled"
								onClick="javascript:WdatePicker({skin:'whyGreen',minDate:'%y-%M-%d'})"
								readonly="readonly" /></td>
							<td class="pn-info"></td>
						</tr>
						<tr class="oneTime" style="display: none;">
							<th><span class="point">*</span>时间</th>
							<td class="pn-fcontent"><input type="text" name="taskTime"
								id="taskTime" disabled="disabled"
								onClick="javascript:WdatePicker({dateFmt: 'HH:mm:ss',skin:'whyGreen',minDate:'%H:%m:%s'})"
								readonly="readonly" /></td>
							<td class="pn-info"></td>
						</tr>
						<tr class="moreTime" style="display: none;">
							<th><span class="point">*</span>执行频率</th>
							<td class="pn-fcontent"><select name="taskRate"
								id="taskRate" disabled="disabled" onchange="rateChange(this)">
									<option value="-1">请选择</option>
									<option value="day">每天</option>
									<option value="week">每周</option>
									<option value="month">每月</option>
							</select></td>
							<td class="pn-info"></td>
						</tr>
						<tr class="moreTimeWeek" style="display: none;">
							<th><span class="point">*</span>执行间隔为</th>
							<td class="pn-fcontent">
								<span>每</span> <input value="1" type="text" name="weekTimes" id="weekTimes" disabled="disabled" maxlength="3" size="3" style="width: 40px;"
								onblur="javascript:checkWeekTimes(this);" /> <span>周，在</span><br/>
								<input type="checkbox" name="weekTimesVal" value="1" disabled="disabled" id="sunday"><label for="sunday"><span>星期日</span></label>
								<input type="checkbox" name="weekTimesVal" value="2" disabled="disabled" id="monday"><label for="monday"><span>星期一</span></label>
								<input type="checkbox" name="weekTimesVal" value="3" disabled="disabled" id="tuesday"><label for="tuesday"><span>星期二</span></label>
								<input type="checkbox" name="weekTimesVal" value="4" disabled="disabled" id="wednesday"><label for="wednesday"><span>星期三</span></label>
								<input type="checkbox" name="weekTimesVal" value="5" disabled="disabled" id="thursday,"><label for="thursday,"><span>星期四</span></label>
								<input type="checkbox" name="weekTimesVal" value="6" disabled="disabled" id="friday"><label for="friday"><span>星期五</span> </label>
								<input type="checkbox" name="weekTimesVal" value="7" disabled="disabled" id="saturday"><label for="saturday"><span>星期六</span></label>
							</td>
							<td class="pn-info"></td>
						</tr>
						<tr class="moreTimeMonth" style="display: none;">
							<th><span class="point">*</span>在</th>
							<td class="pn-fcontent"><select disabled="disabled"
								id="monthFrontSel" name="monthFrontSel" style="width: 100px">
									<option value="-1">请选择</option>
									<option value="1">第一个</option>
									<option value="2">第二个</option>
									<option value="3">第三个</option>
									<option value="4">第四个</option>
									<option value="L">最后一个</option>
							</select> &nbsp; <select disabled="disabled" id="monthBackSel"
								name="monthBackSel" style="width: 100px">
									<option value="-1">请选择</option>
									<option value="1">星期日</option>
									<option value="2">星期一</option>
									<option value="3">星期二</option>
									<option value="4">星期三</option>
									<option value="5">星期四</option>
									<option value="6">星期五</option>
									<option value="7">星期六</option>

									<!--
																					<option value="workday">工作日</option>
																					<option value="restday">休息日</option>  
																					-->
							</select></td>
							<td class="pn-info"></td>
						</tr>
						<tr class="moreTimeDay" style="display: none;">
							<th><span class="point">*</span>每天频率</th>
							<td class="pn-fcontent"><select name="taskRateDaySel"
								id="taskRateDaySel" disabled="disabled"
								onchange="rateChangeDay(this)">
									<option value="-1">请选择</option>
									<option value="one">执行一次</option>
									<option value="more">间隔执行</option>
							</select></td>
							<td class="pn-info"></td>
						</tr>
						<tr class="moreTimeDay1" style="display: none;">
							<th><span class="point">*</span>执行一次，时间为</th>
							<td class="pn-fcontent"><span>在每天</span> <input
								type="text" name="oneTime" id="oneTime" disabled="disabled"
								style="width: 80px;"
								onClick="javascript:WdatePicker({dateFmt: 'HH:mm:ss',skin:'whyGreen'})"
								readonly="readonly" /> <span>执行</span></td>
							<td class="pn-info"></td>
						</tr>
						<tr class="moreTimeDay2" style="display: none;">
							<th><span class="point">*</span>执行间隔为</th>
							<td class="pn-fcontent">
							<span style="float:left">每</span><input type="text" name="betweenTimes" id="betweenTimes" disabled="disabled" style="width:50px;"
								onblur="javascript:checkBetweenTimes(this);" />
							<select id="betweenTimeSel" name="betweenTimeSel" disabled="disabled" style="width: 80px;">
									<option value="hour">小时</option>
									<option value="minute">分</option>
									<option value="second">秒</option>
							</select> <span>执行</span> &nbsp;</td>
							<td class="pn-info"></td>
						</tr>

						<tr class="moreTimeDay3" style="display: none;">
							<th><span class="point">*</span>持续时间</th>
							<td class="pn-fcontent" style="text-align: left;">开始日期： <input
								style="width: 155px;" type="text" name="startTime"
								id="startTime" disabled="disabled"
								onClick="javascript:WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})"
								readonly="readonly" /> &nbsp;<input type="radio" style="margin:0 5px 0 20px;"
								name="hasEndTime" id="hasEndTime1" value="yes"
								disabled="disabled" onclick="javascript:checkHasEndTime(this);">结束日期： <input onblur="javascript:checkEndTimeValid(this);"
									style="width: 155px;" type="text" name="endTime" id="endTime"
									disabled="disabled"
									onClick="javascript:WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})"
									readonly="readonly" /> &nbsp;
									 <input type="radio" style="margin:0 5px 0 20px;" name="hasEndTime" id="hasEndTime2"
									value="no" disabled="disabled" checked="checked"
									onclick="javascript:checkHasEndTime(this);">无结束日期 </td>
							<td class="pn-info"></td>
						</tr>
						</c:if>
						<tr>
							<th>任务参数:</th>
							<td class="pn-fcontent">
								<c:choose>
										<c:when test="${not empty dwrJob.data }">
											<c:forEach items="${dwrJob.data }" var="item" varStatus="status">
												<input type="text" name="dataKey" style="width: 130px;" value="${item.key }">
													&nbsp;-&nbsp;
												<input type="text" name="dataValue" style="width: 350px;" value="${item.value }">
												<br/>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<input type="text" name="dataKey" style="width: 130px;">
												&nbsp;-&nbsp;
											<input type="text" name="dataValue" style="width: 350px;">
										</c:otherwise>
									</c:choose>
							</td>
							<td class="pn-info"></td>
						</tr>
					</tbody>
				</table>
				
			</div>
			<!-- /widget-content -->
			<div class="widget-bottom">
					<input type="hidden" id="endTimeFlag" name="endTimeFlag" value="no" />
					<c:if test="${dwrJob.triggerType ne 0}">
		                                	<a href="#" class="btn btn-info" style="margin:3px;" onclick="javascript:_update();return false;">
		                                		保存更改
		                                	</a>&nbsp;&nbsp;
		                                	<a href="#" class="btn btn-info" style="margin:3px;" onclick="javascript:_start();return false;">
		                                		人工执行
		                                	</a>
	                                	</c:if>
										&nbsp;&nbsp;
										<a href="#" class="btn btn-info" style="margin:3px;" onclick="javascript:_delete();return false;">
	                                		删除任务
	                                	</a>
	                                	&nbsp;&nbsp;
	                                	<c:if test="${dwrJob.triggerStatus eq 'PAUSED'}">
		                                	<a href="#" class="btn btn-info" style="margin:3px;" onclick="javascript:_resume();return false;">
		                                		恢复任务
		                                	</a>
	                                	</c:if>
	                                	&nbsp;&nbsp;
	                                	<c:if test="${dwrJob.triggerStatus eq 'NORMAL'}">
		                                	<a href="#" class="btn btn-info" style="margin:3px;" onclick="javascript:_pause();return false;">
		                                		暂停任务
		                                	</a>
	                                	</c:if>
	                                	&nbsp;&nbsp;
	                                	<a href="jobTask_list.do" class="btn btn-info" style="margin:3px;" >
	                                		返回
	                                	</a>
				</div>
		</div>
	</form>
	<script type="text/javascript">
			function _update(){
				var param_flag = false;
				
				var name = $("#jobname").val();
				var group = $("#groupname").val();
				var className =  $("#className").val();
				
				var dataKeys = $("input[name='dataKey']");
				var dataValues = $("input[name='dataValue']");
				
				if(null == name || "" == name)
				{
					alert("系统提示:任务名称不能为空!");
					return;
				}
				if(null == group || "" == group)
				{
					alert("系统提示:任务组别不能为空!");
					return;
				}
				if(null == className || "" == className)
				{
					alert("系统提示:任务类名不能为空!");
					return;
				}
				

				//验证表达式
				if(!checkCronExpression())
					return;
				
				$.each(dataKeys, function(i, n){
					var dataKey = $(this).val();
					if(!checkValid(dataKey))
					{
						alert("系统提示:任务参数'"+dataKey+"'输入格式有误！");
						
						param_flag = true;
						
					}
				}); 
				
				if(param_flag)
					return;
				
				if(window.confirm("系统提示：确认修改Job？")){
					document.form.action = "jobTask_modify.do";
					document.form.submit();
				}
			}
			
			function _start(){
				if(window.confirm("系统提示：确认开始Job？")){
					document.form.action = "jobTask_start.do";
					document.form.submit();
				}
			}
			
			function _delete(){
				if(window.confirm("系统提示：确认删除Job？")){
					document.form.action = "jobTask_delete.do";
					document.form.submit();
				}
			}
			/**
			*暂停任务
			*/
			function _pause(){
				if(window.confirm("系统提示：确认暂停Job？")){
					document.form.action = "jobTask_pause.do";
					document.form.submit();
				}
			}
			/**
			*恢复任务
			*/
			function _resume(){
				if(window.confirm("系统提示：确认恢复运行Job？")){
					document.form.action = "jobTask_resume.do";
					document.form.submit();
				}
			}
			
			/**
			*增加行
			*/
			function _addData(){
				var dataHtml = "<tr><th>任务参数</th><td><input name='dataKey' style='width: 130px;'>&nbsp;-&nbsp;<input name='dataValue' style='width: 350px;'>&nbsp;";
				dataHtml += "<input type='button' style='border:1px solid black;height:21px;' value=' 删除 ' onclick='onDelete($(this))'/></td></tr>";
				$("#taskTable tr:last").before(dataHtml);
				
			}
			
			/**
			*删除行
			*/
			function onDelete(obj){
				var $tr = obj.parent().parent();
				$tr.remove();
			}
			
			/**校验合法性*/
			function checkValid(s)
			{
				if(s == null || s =="" || s =="undefined")
					return true;
				
				var patrn=/^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){0,19}$/;
				if (!patrn.exec(s)) return false;
				return true;
			}
			
			//计划类型 选择框
			var show_flag = false;
			function _option()
			{
				try{
					//隐藏所有
					$("#taskTypeSel").val("-1");//计划类型
					//执行频率
					$.each($("tr[class='moreTime']"),function(){
						$(this).hide();
						
						//启用重复执行下所有控件
						$(this).find("input").attr("disabled",true);
						
						$(this).find("select").attr("disabled",true);
						
					});
					//执行一次
					$.each($("tr[class='oneTime']"),function(){
						$(this).hide();
						//禁用执行一次下所有控件
						$(this).find("input").attr("disabled",true);
						
					});
					
					clear();
					clearAll();
					clearText();
					
				}catch(e)
				{
					
				}
				var taskDetailTr = $("#taskDetailTr");
				var taskType = $("#taskTypeSel");
				var cronTr = $("#cronTr");//表达式配置
				
				if(show_flag)
				{
					taskDetailTr.hide();
					//禁用下拉框
					taskType.attr("disabled",true);
					//启用表达式输入框
					$("#cronExpression").attr("disabled",false);
					
					cronTr.css("display","");
				}
				//显示参数配置选项
				else
				{
					taskDetailTr.show();
					//启用下拉框
					taskType.attr("disabled",false);
					//禁用表达式输入框
					$("#cronExpression").attr("disabled",true);
					
					cronTr.css("display","none");
				}
				show_flag = !show_flag;
				
			}
			//清空文本框
			function clearText()
			{
				try{
					$("#weekTimes").val("1");
					$("#betweenTimes").val("");
					$("#endTimeFlag").val("no");
					$("#endTime").val("");//结束日期
					$("#taskRate").val("-1");//执行频率
					$("#taskRateDaySel").val("-1");//每天频率
				}catch(e){}
			}
			function chooseTaskType(obj)
			{
				var val = obj.value;
				//还原下拉框的值
				try{
					$("#monthFrontSel").val("-1");
					$("#monthBackSel").val("-1");
					$("#taskRateDaySel").val("-1");
					$("#taskRate").val("-1");
				}catch(e){}
				//还原下拉框的值
				
				//隐藏所有
				clear();
				clearAll();
				clearText();
				
				$.each($("tr[class='oneTime']"),function(){
					$(this).hide();
					//禁用执行一次下所有控件
					$(this).find("input").attr("disabled",true);
					
				});
				
				$.each($("tr[class='moreTime']"),function(){
					$(this).hide();
					
					
					//启用重复执行下所有控件
					$(this).find("input").attr("disabled",true);
					
					$(this).find("select").attr("disabled",true);
					
				});
				//隐藏所有
				
				
				//执行一次
				if(val == "2")
				{
					$.each($("tr[class='oneTime']"),function(){
						$(this).show();
						//启用执行一次下所有控件
						$(this).find("input").attr("disabled",false);
					});
					
				}
				//重复执行
				else if(val == "1")
				{
					$.each($("tr[class='moreTime']"),function(){
						$(this).show();
						
						//启用重复执行下所有控件
						$(this).find("input").attr("disabled",false);
						
						$(this).find("select").attr("disabled",false);
					});
					
				}
				else if(val == "-1")
				{
					
					
				}
				else
				{
					
					
				}
					
					
				
			}
			
			//执行频率选择
			function rateChange(obj)
			{
				var val = obj.value;
				
				//还原下拉框的值
				try{
					$("#monthFrontSel").val("-1");
					$("#monthBackSel").val("-1");
					$("#taskRateDaySel").val("-1");
					//$("#taskRate").val("-1");
					$("#weekTimes").val("1");
					
					$.each($("input[name='weekTimesVal']"),function(){
						$(this).attr("checked",false);
					});
				}catch(e){}
				//还原下拉框的值
				
				//先隐藏所有
				clearAll();
				//先隐藏所有
				
				$.each($("tr[class='moreTimeDay']"),function(){
					$(this).show();
					
					//启用重复执行下所有控件
					$(this).find("select").attr("disabled",false);
				});
				
				
				$.each($("tr[class='moreTimeDay3']"),function(){
					$(this).show();
					
					//启用持续时间下所有控件
					$(this).find("input").attr("disabled",false);
				});
				//禁用结束日期
				$("#endTime").attr("disabled",true);
				
				
				//每天执行
				if(val=='day')
				{
					
				}
				//每周执行
				else if(val=='week')
				{
					$.each($("tr[class='moreTimeWeek']"),function(){
						$(this).show();
						
						//启用重复执行下所有控件
						$(this).find("input").attr("disabled",false);
					});
				}
				//每月执行
				else if(val=='month')
				{
					
					$.each($("tr[class='moreTimeMonth']"),function(){
						$(this).show();
						
						//启用重复执行下所有控件
						$(this).find("select").attr("disabled",false);
					});
				}
				else if(val=='-1')
				{
					clear();
				}
				else
				{
					
				}
				
			}
			
			function clear()
			{
				$.each($("tr[class='moreTimeDay']"),function(){
					$(this).hide();
					
					//启用重复执行下所有控件
					$(this).find("select").attr("disabled",true);
				});
				
				$.each($("tr[class='moreTimeDay3']"),function(){
					$(this).hide();
					
					//启用重复执行下所有控件
					$(this).find("input").attr("disabled",true);
				});
			}
			
			
			function clearAll()
			{
				//隐藏所有
				$.each($("tr[class='moreTimeDay']"),function(){
					$(this).hide();
					//启用重复执行下所有控件
					$(this).find("select").attr("disabled",true);
				});
				
				$.each($("tr[class='moreTimeDay3']"),function(){
					$(this).hide();
					
					//启用重复执行下所有控件
					$(this).find("input").attr("disabled",true);
				});
				
				clearDay();
				
				//周
				$.each($("tr[class='moreTimeWeek']"),function(){
					$(this).hide();
					
					//启用重复执行下所有控件
					$(this).find("input").attr("disabled",true);
				});
				//月
				$.each($("tr[class='moreTimeMonth']"),function(){
					$(this).hide();
					
					//启用重复执行下所有控件
					$(this).find("select").attr("disabled",true);
				});
				
				
				//隐藏所有
			}
			
			function clearDay()
			{
				//隐藏所有
				$.each($("tr[class='moreTimeDay1']"),function(){
					$(this).hide();
					//启用重复执行下所有控件
					//alert($(this).find("input").attr("disabled"));
					$(this).find("input").attr("disabled",true);
					$(this).find("select").attr("disabled",true);
				});
				
				$.each($("tr[class='moreTimeDay2']"),function(){
					$(this).hide();
					//启用重复执行下所有控件
					$(this).find("input").attr("disabled",true);
					$(this).find("select").attr("disabled",true);
				});
				
				//隐藏所有
			}
			
			//执行频率选择(每天)
			function rateChangeDay(obj)
			{
				var val = obj.value;
				
				clearDay();
				
				//执行一次
				if(val=='one')
				{
					$.each($("tr[class='moreTimeDay1']"),function(){
						$(this).show();
						//启用重复执行下所有控件
						$(this).find("input").attr("disabled",false);
						$(this).find("select").attr("disabled",false);
					});
					
				}
				//间隔执行
				else if(val=='more')
				{
					$.each($("tr[class='moreTimeDay2']"),function(){
						$(this).show();
						
						//启用重复执行下所有控件
						$(this).find("input").attr("disabled",false);
						$(this).find("select").attr("disabled",false);
					});
				}else
				{
					
				}
				
			}
			
			$(function(){
				var myDate=new Date();
				var year = myDate.getFullYear();
				var _month = myDate.getMonth()+1;
				var month;
				if(_month < 10){month = "0"+_month;}
				else{month = _month;}
				var date = myDate.getDate();
				var fulldate = year+"-"+month+"-"+date;
				
				myDate.setTime( myDate.getTime()+60*1000 );   //往后增加1分钟
				var hour = myDate.getHours();//获取当前小时数(0-23)
				var _min = myDate.getMinutes();     //获取当前分钟数(0-59)
				var sec = myDate.getSeconds();     //获取当前秒数(0-59)
				//alert(hour+":"+min+":"+sec);
				var min
				if(_min < 10){min = "0"+_min;}
				else{min = _min;}
				
				
				var fulltime = hour+":"+min+":00";
				
				$("#taskDate").val(fulldate);
				$("#taskTime").val(fulltime);
				
				var alldate = fulldate+" "+fulltime;
				$("#startTime").val(alldate);
				
				
				//触发按钮事件
				var optionBtn = document.getElementById("optionBtn");
				optionBtn.click();
					
			});
			
			//检查结束日期的合法性
			var clearFlag;
			var time = 3000;//3秒
			function checkEndTimeValid(obj)
			{
				if(clearFlag != null && clearFlag != 'undefined')
				{
					clearTimeout(clearFlag);
				}
				
				clearFlag = setTimeout(function(){
					var self = obj;
					if(self == null || self == "undefined")
						return;
					var val = self.value;//结束日期
					
					var hasEndTime1 = document.getElementById("hasEndTime1");
					//已经选择含结束日期
					if(hasEndTime1.checked)
					{	
						//开始日期
						var startTime = document.getElementById("startTime").value;
						
						if(startTime == null || startTime == "")
						{
							alert("系统提示：请先输入开始日期！");
							return;
						}
						
						if(!isStartEndDate(startTime,val))
						{
							alert("系统提示：结束日期不能早于开始日期！");
							return;
						}
						
					}
					
				},time);
			
			}
			
			function isStartEndDate(startDate,endDate){   
			     //alert(startDate "===" endDate);   
			     if(startDate.length>0&&endDate.length>0){   
			       var startDateTemp = startDate.split(" ");   
			       var endDateTemp = endDate.split(" ");   
			       var arrStartDate = startDateTemp[0].split("-");   
			       var arrEndDate = endDateTemp[0].split("-");   
			       var arrStartTime = startDateTemp[1].split(":");   
			       var arrEndTime = endDateTemp[1].split(":");   
			       var allStartDate = new Date(arrStartDate[0],arrStartDate[1],arrStartDate[2],arrStartTime[0],arrStartTime[1],arrStartTime[2]);   
			       var allEndDate = new Date(arrEndDate[0],arrEndDate[1],arrEndDate[2],arrEndTime[0],arrEndTime[1],arrEndTime[2]);   
			       
			       if(allStartDate.getTime()>allEndDate.getTime()){   
			        	return false;   
			       }   
			     }   
			     return true;   
			}   
			//检查结束日期的合法性
			
			//有无结束日期
			function checkHasEndTime(obj)
			{
				var self = obj;
				if(self == null || self == "undefined")
					return;
				var val = self.value;
				if(val == 'yes')
				{
					$("#endTime").attr("disabled",false);
					$("#endTimeFlag").val("yes");
				}else if(val == 'no')
				{
					$("#endTime").attr("disabled",true);
					$("#endTimeFlag").val("no");
				}else
				{
					
				}
				
			}
			//检查每周执行的合法性
			function checkWeekTimes(obj)
			{
				var self = obj;
				if(self == null || self == "undefined")
					return;
				var val = self.value;
				var patrn=/^[1-9]{0,1}[0-9]{0,1}$/; 
				if (!patrn.exec(val)) 
				{
					alert("系统提示：输入内容有误！");
					return;
				}
			}
			//检查执行间隔的合法性
			function  checkBetweenTimes(obj)
			{
				var self = obj;
				if(self == null || self == "undefined")
					return;
				var val = self.value;
				var patrn=/^[1-9]{0,1}[0-9]{0,1}$/; 
				if (!patrn.exec(val)) 
				{
					alert("系统提示：输入内容有误！");
					return;
				}
				
				var betweenTimeSelVal = $("#betweenTimeSel").val();
				
				
				//时
				if(betweenTimeSelVal=="hour")
				{
					if(Number(val) > 24)
					{
						alert("系统提示：输入内容有误,不能超过24小时！");
						return;
					}
				}
				//分
				else if(betweenTimeSelVal=="minute")
				{
					if(Number(val) > 60)
					{
						alert("系统提示：输入内容有误,不能超过60分钟！");
						return;
					}
				}
				//秒
				else if(betweenTimeSelVal=="second")
				{
					if(Number(val) > 60)
					{
						alert("系统提示：输入内容有误,不能超过60秒钟！");
						return;
					}
				}else
				{}
			}
			
			/**
			*检查表达式
			*/
			function checkCronExpression()
			{
				var cronExpression = $("#cronExpression");
				//表达式输入已禁用
				if(cronExpression.attr("disabled"))
				{
					var taskTypeSelVal = $("#taskTypeSel").val();//计划类型 
					
					if(taskTypeSelVal == "-1")
					{
						alert("系统提示：请先选择计划类型！");
						return false;
					}
					else
					{
						//重复执行
						if(taskTypeSelVal == "1")
						{
							var taskRateVal = $("#taskRate").val();//执行频率
							if(taskRateVal == "-1")
							{
								alert("系统提示：请先选择执行频率！");
								return false;
							}
							
							else
							{
								
								var taskRateDaySelVal = $("#taskRateDaySel").val();
								if(taskRateDaySelVal == "-1")
								{
									alert("系统提示：请先选择每天执行频率！");
									return false;
								}
								else
								{
									//执行一次
									if(taskRateDaySelVal == "one")
									{
										var oneTimeVal = $("#oneTime").val();
										if(oneTimeVal == null || oneTimeVal == "")
										{
											alert("系统提示：每天频率不能为空！");
											return false;
										}
									}
									//间隔执行
									else if(taskRateDaySelVal == "more")
									{
										var betweenTimesVal = $("#betweenTimes").val();
										if(betweenTimesVal == null || betweenTimesVal == "")
										{
											alert("系统提示：执行间隔不能为空！");
											return false;
										}
										
										var betweenTimeSelVal = $("#betweenTimeSel").val();
										//时
										if(betweenTimeSelVal=="hour")
										{
											if(Number(betweenTimesVal) > 24)
											{
												alert("系统提示：输入内容有误,不能超过24小时！");
												return false;
											}
										}
										//分
										else if(betweenTimeSelVal=="minute")
										{
											if(Number(betweenTimesVal) > 60)
											{
												alert("系统提示：输入内容有误,不能超过60分钟！");
												return false;
											}
										}
										//秒
										else if(betweenTimeSelVal=="second")
										{
											if(Number(betweenTimesVal) > 60)
											{
												alert("系统提示：输入内容有误,不能超过60秒钟！");
												return false;
											}
										}else
										{}
									}
									else
									{
										/**空方法*/
									}
								}
								
								
								var startTimeVal = $("#startTime").val();
								//开始日期
								if(startTimeVal == null || startTimeVal == "")
								{
									alert("系统提示：开始日期不能为空！");
									return false;
								}
								
								
								
								//每天
								if(taskRateVal == "day")
								{
									
								}
								//每周
								else if(taskRateVal == "week")
								{
									
								}
								//每月
								else if(taskRateVal == "month")
								{
									var monthFrontSelVal = $("#monthFrontSel").val();
									
									var monthBackSelval = $("#monthBackSel").val();
									
									
									if(monthFrontSelVal == null || monthFrontSelVal == "")
									{
										alert("系统提示：请先选择每月执行频率！");
										return false;
									}
									
									if(monthBackSelval == null || monthBackSelval == "")
									{
										alert("系统提示：请先选择每月执行频率！");
										return false;
									}
									
								}
								else
								{
									//空方法
								}
							}
							
							
						}
						//执行一次
						else if(taskTypeSelVal == "2")
						{
							var taskDateVal = $("#taskDate").val();
							var taskTimeVal = $("#taskTime").val();
							
							if(taskDateVal == null || taskDateVal == "")
							{
								alert("系统提示：日期不能为空！");
								return false;
							}
							
							if(taskTimeVal == null || taskTimeVal == "")
							{
								alert("系统提示：时间不能为空！");
								return false;
							}
						}
						else
						{
							//空方法
						}
					}
					
					return true;
					
				}
				else
				{
					var cronExpressionVal = cronExpression.val();
					
					if(cronExpressionVal == null || cronExpressionVal == "")
					{
						alert("系统提示：触发器表达式(cron)不能为空！");
						return false;
					}
					
					return true;
				}
				
			}
		</script>
</body>
</html>