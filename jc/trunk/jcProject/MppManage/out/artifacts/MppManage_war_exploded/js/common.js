function selectOperator(id){
	var option = "<option value=''>全部</option>";
	$("#name").empty();
	$("#name").append(option);
	if(!!id){
		var stationNo = $("#stationNo").val();
		if(null == id || "" == id){
			id = stationNo;
		}
		var url=uri+"admin/dicItem_getSelectName.do";
		params = {
				'imgId':id,
		};
		//$.post(url,params,showHWSearchSucc,"json");
		var nameId = $("#nameId").val();
		$("#name").empty();
		$.ajax({
			url: url,
			data:params,
			type: "POST",
			dataType:'json',
			success:function(jsonTemp){
				var nameList = jsonTemp.result; 
				if(nameList != null){
					for (var a = 0; a < nameList.length; a++) {
						var arr = nameList[a]; 
						if(arr[0] == nameId){
							option += "<option value='"+arr[0]+"' selected>"+arr[1]+"</option>"
						}else{
							option += "<option value='"+arr[0]+"'>"+arr[1]+"</option>"
						}

					}

				} 
				$("#name").append(option);
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				//session失效
				if(XMLHttpRequest.status==200 && XMLHttpRequest.readyState==4) {
					window.location.href = uri;
				}
			}
		});
	}else{
		$("#nameId").val('');
	}
}
//查询当前操作人员下的收费站
function selectStation(id){
	var option = "<option value=''>全部</option>";
	$("#stationNo").empty();
	$("#stationNo").append(option);
	$("#name").empty();
	$("#name").append(option);
	if(!!id){
		var roadNo = $("#roadNo").val();
		if(null == id || "" == id){
			id = roadNo;
		}
		var url=uri+"admin/dicItem_getStationSelectNameZJ.do";
		params = {
				'imgId':id,
		};
		var StationId = $("#stationId").val();
		$("#stationNo").empty();

		$.get(url,params,function(jsonTemp){
			if(!!jsonTemp.stList){ 
				$.each(jsonTemp.stList,function(i,org){ 
					if(org.id == StationId){
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
		$("#nameId").val('');
	}
}
//查询所有收费站
function selectAllStation(id){
	var option = "<option value=''>全部</option>";
	$("#stationNo").empty();
	$("#stationNo").append(option);
	$("#name").empty();
	$("#name").append(option);
	if(!!id){
		var roadNo = $("#roadNo").val();
		if(null == id || "" == id){
			id = roadNo;
		}
		var url=uri+"admin/dicItem_getStationSelectNameSG.do";
		params = {
				'imgId':id,
		};
		var StationId = $("#stationId").val();
		$("#stationNo").empty();

		$.get(url,params,function(jsonTemp){
			if(!!jsonTemp.stList){ 
				$.each(jsonTemp.stList,function(i,org){ 
					if(org.id == StationId){
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
		$("#nameId").val('');
	}
}


function checkTime(){
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
    if(((end.getTime()-start.getTime())/86400000)>90){
        alert("时间跨度不能超过90天");
        return false;
    }
    return true;
}

function checkTimeLessThanWeek() {
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
    if((end.getFullYear()-start.getFullYear())>0){
        alert("时间跨度不能超过一年");
        return false;
    }
    
    if($("#plateNum").val()==""&& $("#carNo").val()==""){
        alert("请输入车牌！");
        return false;
    }
    return true;
}

function checkTimeLessThanWeek2() {
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
    if((end.getFullYear()-start.getFullYear())>0){
        alert("时间跨度不能超过一年");
        return false;
    }
    return true;
}