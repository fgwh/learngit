var ROW_SPLIT = '&&&&';
var SHOW_SPLIT = ';';

//限制输入的条件
var checkQueryCondition = function(){
	var centralStationId = $("select[name='carStatistic.centralStationId']").val();
	var exVehPlate = $("input[name='carStatistic.exVehiclePlate']").val();
	var startTime = stringToDate($("input[name='carStatistic.startTime']").val());
	var endTime = stringToDate($("input[name='carStatistic.endTime']").val());
	var showDealStatusTd = $("input[name='carStatistic.dealStatusArr']").val();
	
	
	//判断中心站必填
	/* 放开中心站
	if(!centralStationId){
		alert("中心站为必选项");
		return false;
	}
	*/
	//判断车牌必填
	if(!$.trim(exVehPlate)){
		alert("车牌为必填项");
		return false;
	}
	
	//判断开始时间必填
	if(!startTime){
		alert("开始时间为必填项");
		return false;
	}
	
	//判断结束时间必填
	if(!endTime){
		alert("结束时间为必填项");
		return false;
	}
	
	//判断异常情况为必选项
	if(!$.trim(showDealStatusTd)){
		alert("异常情况为必选项");
		return false;
	}
	//判断开始时间不能大于结束时间时间
	if (startTime > endTime) {
        alert("结束时间不能小于起始时间");
        return false;
    }   
	//开始时间和结束时间必须在同一年内
	if(startTime.getFullYear() != endTime.getFullYear()){
		alert("开始时间和结束时间必须在同一年内");
		return false;
	}
	
	return true;
}


$(function(){
	$("#submit").on("click", function(){
		if (checkQueryCondition()) {
            $("#myForm").submit();
        }
        event.preventDefault();
	});
	
	
	//中心站联动选择收费站
	$("#centralStation").on("change", function(){
		var id = $(this).val();
		var option = "<option value=''>全部</option>";
		if(!!id){
			var centralStation = $("#centralStation").val();
			if(null == id || "" == id){
				id = roadNo;
			}
			var url=uri+"admin/carStatistic_getStationSelectName.do";
			var params2 = {
				'carStatistic.selectId':id
			};
			var stationId = $("#stationId").val();
			$("#stationNo").empty();
	
			$.get(url,params2,function(jsonTemp){
				if(!!jsonTemp.stationList){ 
					$.each(jsonTemp.stationList,function(i,org){ 
						if(org.id == stationId){
							option += "<option value='"+org.id+"' selected>"+org.orgName+"</option>"
						}else{
							option += "<option value='"+org.id+"'>"+org.orgName+"</option>"
						}
					});
				} 
				$("#stationNo").append(option);
			});
		}else{
			$("#stationId").val('');
			$("#stationNo").html("<option value=''>全部</option>");
		}
	});
	/*
	function selectStation(id){
		
	}*/
	
	
	$('#centralStation').trigger('change');
});


//列出所有的异常情况选项
function selectDealStatusFunction(){
	var count = 1;
	var url=uri+"admin/carStatistic_getDealStatus.do";
	var params2 = {};

	$.get(url,params2,function(jsonTemp){
		if(!!jsonTemp.dealStatusList){ 
			var divNode = '<div>'
			$.each(jsonTemp.dealStatusList, function(i,dealStatus){ 
				if(dealStatus[1]!='付费方式'){
					divNode += '<label><input name="selectDealStatus" type="checkbox" value="'+dealStatus[1]+'" />'+dealStatus[1]+'</label>';
					if(count%4==0){
						divNode += '</div><div>';
					}
					
					count++;
				}
			});
			
			divNode += '</div>';
			
			$("#dealStatusTable").html(divNode);
		} else{
			$("#dealStatusTable").empty();
		}
	});
	
	$('#myModal4').modal('show');
}

//异常情况进行选择
$("#selectDealStatusButton").on("click", function(){
	var dealStatusResult = '';
	var showDealStatus = '选择异常情况:';
	var dealStatusNodes = $("#dealStatusTable input[name='selectDealStatus']");
	
	$.each(dealStatusNodes, function(i,dealStatus){ 
		if(dealStatus.checked){
			dealStatusResult += dealStatus.value+ROW_SPLIT;
			showDealStatus += dealStatus.value+SHOW_SPLIT;
		}
	});
	$("#showDealStatusTd").html('<b>'+$.trim(showDealStatus)+'</b>');
	$("input[name='carStatistic.dealStatusArr']").val($.trim(dealStatusResult));
	$("input[name='carStatistic.dealStatusStr']").val($.trim(showDealStatus));
	$("#showDealStatusTd").attr("title", $.trim(showDealStatus));
	
	$('#myModal4').modal('hide');
});

//单击显示详情页面内容
$("#databody a[name='showMoreContent']").on("click", function(){
	var tr = $(this).parents("tr");
	showDetails(tr);
});

//双击显示详情页面内容
$("#databody tr").on("dblclick", function(){
	var tr = $(this);
	showDetails(tr);
});

//显示详情进行显示
function showDetails(tr){
	//enRoadIDTd
	//获取图片数据<>
	getPictureData($.trim(tr.find("[name=laneExSerialNoTd]").text()), $.trim(tr.find("[name=laneEnSerialNoTd]").text()), 
			$.trim(tr.find("[name=enRoadIDTd]").text()), $.trim(tr.find("[name=exRoadIDTd]").text()), $.trim(tr.find("[name=enNetRoadIDTd]").text()),
			$.trim(tr.find("[name=exNetRoadIDTd]").text()), $.trim(tr.find("[name=enStationIDTd]").text()), $.trim(tr.find("[name=exStationIDTd]").text()),
			$.trim(tr.find("[name=enLaneIDTd]").text()), $.trim(tr.find("[name=exLaneIDTd]").text()), $.trim(tr.find("[name=squadDateTd]").text()),
			$.trim(tr.find("[name=enLaneTypeTd]").text()), $.trim(tr.find("[name=exLaneTypeTd]").text()));
	
	$("#laneExSerialNoFont").text(tr.find("[name=laneExSerialNoTd]").text());  //收费流水
	$("#cashMoneyFont").text(tr.find("[name=cashMoneyTd]").text());     //收费金额
	$("#exOperatorIdFont").text(tr.find("[name=exOperatorIdTd]").text());  //收费员
	$("#exTimeFont").text(tr.find("[name=exTimeTd]").text());    //出口时间
	$("#enTimeFont").text(tr.find("[name=enTimeTd]").text()); //入口时间
	
	$("#exStationNameFont").text(tr.find("[name=exStationNameTd]").text());	//出口收费站
	$("#enStationNameFont").text(tr.find("[name=enStationNameId]").text());    //入口收费站
	$("#exCPCSnNoFont").text(tr.find("[name=exCPCSnNoTd]").text());    //通行卡号
	$("#exLaneTypeFont").text(tr.find("[name=exLaneNameTd]").text());       //车道类型
	$("#enVehicleClassFont").text(tr.find("[name=enVehicleClassTd]").text());  //初判车型
	$("#exVehicleClassFont").text(tr.find("[name=exVehicleClassTd]").text());    //最终车型
	
	
	$("#axisGroupNumFont").text(tr.find("[name=axisGroupNumTd]").text());   //轴组组成
	$("#exVehIdentifyPlateFont").text(tr.find("[name=exVehIdentifyPlateTd]").text());    //识别车牌
	$("#exVehiclePlateFont").text(tr.find("[name=exVehiclePlateTd]").text());     //最终车牌
	$("#exVehicleStatusFont").text(tr.find("[name=exVehicleStatusTd]").text());     //车种
	$("#dealStatusNameFont").text(tr.find("[name=dealStatusNameTd]").text());   //特殊事件
	
	//set
	
	$('#myModal5').modal('show');
}


function getPictureData(exSerialNo, enSerialNo, enRoadId, exRoadID, enNetRoadID, exNetRoadID, enStationID, exStationID, enLaneID, exLaneID, squadDate, enLaneType, exLaneType){
	var url=uri+"admin/carStatistic_pictureMsg.do";
	var params3 = {
		'carStatistic.exSerialNo':exSerialNo,
		'carStatistic.enSerialNo':enSerialNo,
		'carStatistic.enRoadNo':enRoadId,
		'carStatistic.exRoadID':exRoadID,
		'carStatistic.enNetRoadID':enNetRoadID,
		'carStatistic.exNetRoadID':exNetRoadID,
		'carStatistic.enStationID':enStationID,
		'carStatistic.exStationID':exStationID,
		'carStatistic.enLaneID':enLaneID,
		'carStatistic.exLaneID':exLaneID,
		'carStatistic.squadDate':squadDate,
		'carStatistic.enLaneType':enLaneType,
		'carStatistic.exLaneType':exLaneType
	};
	
	
	$.ajax({
		url : url,
		type : 'get',
		async: false,//使用同步的方式,true为异步方式
		data: params3,//这里使用json对象
		success: function(data){
			if(!!data.imageList){
				var imageList = data.imageList;
				var srcName;
				//出口车辆照片

				if(!!imageList[0] && (typeof imageList[0])==='object'){
					srcName = uri+"admin/checkPicture_getDocumentImg.do?imgUrl="+imageList[0].exListImageName+"&fileDate="+$.trim(imageList[0].squadDate);
					$("#exVehicleImage").attr("src", srcName);
				} else{
					$("#exVehicleImage").attr("src", "/images/error.jpg");
				}
				

				//入口车辆照
				if(!!imageList[1] && (typeof imageList[1])==='object'){
					srcName = uri+"admin/checkPicture_getDocumentImg.do?imgUrl="+imageList[1].exListImageName+"&fileDate="+$.trim(imageList[1].squadDate);
					$("#enVehicleImage").attr("src", srcName);
				} else{
					$("#enVehicleImage").attr("src", "/images/error.jpg");
				}
			} else{
				$("#exVehicleImage").attr("src", "/images/error.jpg");
				$("#enVehicleImage").attr("src", "/images/error.jpg");
			}
		},
		fail:function(){
			console.log("无图片数据...");
			
			$("#enVehicleImage").attr("src", "/images/more.png");
			$("#exVehicleImage").attr("src", "/images/more.png");
		}
	});
}

Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}