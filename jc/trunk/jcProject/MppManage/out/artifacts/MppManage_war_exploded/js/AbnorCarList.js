  function showModal5(){
	  
    	var type=$("input[name='conditions.excepDisplayType']:checked").val();
	  if(!type){
	  	alert("请先选择异常展示类型");
		  return false;
	  }
    	if(type==2){
    		$("#etcPlate").hide();
    		$("#etcModal").hide();
    		$("#zhouzu").show();
    	}else{
    		$("#etcPlate").show();
    		$("#etcModal").show();
    		$("#zhouzu").hide();
    	}
    	$('#myModal5').modal('show');
    	
    }  
  //查询
  function searchList(){
	var plateNum= $("[name='conditions.plateNum']").val()
    var carNo=$("[name='conditions.carNo']").val();
	 if(""==carNo||""==plateNum){
	    	alert("车牌不能为空");
	    	return false;
	    }
	    var express= /^[A-Z_a-z]{1}[警京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼]{0,1}[A-Z_a-z_0-9]{4}[A-Z_a-z_0-9挂学警港澳]{1}$/;
	    if(!express.test(carNo)){
	    	alert("车牌不合法");
	    	return false;
	    }
	  

	    
	    if($("input[name='conditions.excepDisplayType']:checked").length<=0){
	    	alert("异常展示类型不能为空");
	    	return false;
	    }
	    
	    if(false==CbinaCondion()){
	    	return false;
	    }
	    
	  $("#myForm").submit();
  }
  
  
  function CbinaCondion(){

	    var switchCard=$("input[name='conditions.switchCard']").prop("checked");
	    var axisGroupExcep=$("input[name='conditions.axisGroupExcep']").prop("checked");
	    var chargesNotIndetify=$("input[name='conditions.chargesNotIndetify']").prop("checked");
	    var etcModelsNotMeet=$("input[name='conditions.etcModelsNotMeet']").prop("checked");
	    var indentifyPointExcep=$("input[name='conditions.indentifyPointExcep']").prop("checked");
	    var etcPlateNotMeet=$("input[name='conditions.etcPlateNotMeet']").prop("checked");
	    var historyEscape=$("input[name='conditions.historyEscape']").prop("checked");
	    
	    if($("input[name='conditions.excepDisplayType']:checked").val()=="1"){
	    	if(!(switchCard||chargesNotIndetify||etcModelsNotMeet||indentifyPointExcep||etcPlateNotMeet||historyEscape)){
	    		  alert("组合条件不能为空");
		           return false;
	          }
        }else if($("input[name='conditions.excepDisplayType']:checked").val()=="2"){
        	if(!(switchCard||chargesNotIndetify||indentifyPointExcep||historyEscape||axisGroupExcep)){
	    		  alert("组合条件不能为空");
		           return false;
	          }
        }
	    return true;
	  }
  
  //表单清空
  function resert(){
	  resetform($("*[resert]"));
	  $('#myForm').find("[name='conditions.switchCardNum']").val("1");
	  $('#myForm').find("[name='conditions.axisGroupExcepNum']").val("1");
	  $('#myForm').find("[name='conditions.chargesNotIndetifyNum']").val("1");
	  $('#myForm').find("[name='conditions.etcModelsNotMeetNum']").val("1");
	  $('#myForm').find("[name='conditions.indentifyPointExcepNum']").val("1");
	  $('#myForm').find("[name='conditions.etcPlateNotMeetNum']").val("1");

  }
  
  function controlLine(){
      for(var i=1; i<=13; i++){
          if($(".configurationLine .line"+i).attr('checked')){
              $(".ycls-table tr .a"+i).hide();
          }else{
              $(".ycls-table tr .a"+i).show();
          }
       }
        $('#myModal4').modal('hide');
    }

//流水数据展示  
 function  showLS(num,plateNum,SwitchCard,ChargesNotIndetify,IndentifyPointExcep,
		 ETCPlateNotMeet,ETCModelsNotMeet,AxisGroupExcep,AuditStatus){

	 $("[name='saveForm']").find("input[type='checkbox']").each(function (i) {

		 $(this).attr("checked",false);
	 })
	 controlLine();

	 $("#exportplateNum").val(plateNum);
	 $("#exportForm").find("[name='auditStatusExport']").val(AuditStatus);
	 var operaterName= $("#operaterName").val();
	 $("#currentNum").val(num);//设置当前为那一条
	 $("#liushui").empty();
	  $("#Abnorlabel").empty();
	 $("#checkAll").attr("checked",false);
	 var divNode="";
		$.ajax({
			   type: "POST",
			   url: uri+"admin/abnorCar_getAbnorLiuShui.do",
			   data: {"plateNum":plateNum,"auditStatus":AuditStatus},
			   cache: false,
			   success: function(msg){
				   if(!!msg.AbnorLiuShui){
					  var als=msg.AbnorLiuShui
				   for (var i = 0; i < als.length; i++) {
					   var element = als[i];
					 divNode+='<tr name='+element.exceptionType+'  ondblclick="showDetails(\''+element.id+'\',\''+element.id+"_"+$.trim(element.exVehIdentifyPlate)+'\',\''+element.laneEnSerialNo+'\',\''+element.laneExSerialNo+'\',\''+element.exTime+'\')"><td><input type="checkbox" class="subCheckbox" laneExSeriaNo='+element.laneExSerialNo+' laneEnSeriaNo='+element.laneEnSerialNo+' exTime='+element.exTime+'  value='+element.exceptionType+'   plateID='+element.id+'_'+element.exVehIdentifyPlate+'></input>'
					if(!$("[name='hg']").find(".line1").prop("checked")){
						divNode+= ' </td><td class="a1">'+operaterName+'</td>'
					}
					 if(!$("[name='hg']").find(".line2").prop("checked")){
							divNode+= ' </td><td class="a2">'+element.miles+'</td>'
						}
					 if(!$("[name='hg']").find(".line3").prop("checked")){
							divNode+= '</td><td class="a3">'+element.totalWeight+'</td>'
						}
					 /*if(!$("[name='hg']").find(".line4").prop("checked")){
							divNode+= ' </td><td class="a4">'+element.+'</td>'
						}*/
					 if(!$("[name='hg']").find(".line5").prop("checked")){
							divNode+= '</td></td><td class="a5">'+element.enTime.replace(/T/g," ")+'</td>'
						}
					 if(!$("[name='hg']").find(".line6").prop("checked")){
							divNode+= ' </td><td class="a6">'+element.exTime.replace(/T/g," ")+'</td>'
						}
					 if(!$("[name='hg']").find(".line7").prop("checked")){
							divNode+= '</td> <td class="a7">'+element.axisGroupNum+'</td>'
						}
					 if(!$("[name='hg']").find(".line8").prop("checked")){
							divNode+= '</td><td class="a8">'+element.enVehicleClass+'</td>'
						}
					 if(!$("[name='hg']").find(".line9").prop("checked")){
							divNode+= '</td><td class="a9">'+element.exVehicleClass+'</td>'
						}
					 if(!$("[name='hg']").find(".line10").prop("checked")){
							divNode+= '</td><td class="a10">'+element.obuPlate+'</td>'
						}
					 if(!$("[name='hg']").find(".line11").prop("checked")){
							divNode+= '</td><td class="a11">'+element.exVehIdentifyPlate+'</td>'
						}
					 if(!$("[name='hg']").find(".line12").prop("checked")){
							divNode+= '</td><td class="a12">'+element.realPath+'</td>'
						}
					 if(!$("[name='hg']").find(".line13").prop("checked")){
							divNode+= '</td><td class="a13">'+element.cashMoney+'</td>'
						}
					 divNode+= ' </td><td><button type="button" class="btn btn-primary"  onclick="showDetails(\''+element.id+'\',\''+element.id+"_"+$.trim(element.exVehIdentifyPlate)+'\',\''+element.laneEnSerialNo+'\',\''+element.laneExSerialNo+'\',\''+element.exTime+'\')">详情</button></td></tr>'
				   }
					   $("#liushui").append(divNode);
					   
					   //设置可以点击的 标签
					   var  buttonNode='<span>车牌:'+plateNum+'</span>'
					   if(SwitchCard!=0){
						   buttonNode+=' <span><a class="btn btn-xs" href="javascript:void(0);" id="" onclick="addColor(1)">倒卡'+SwitchCard+'次</a></span>'
					   }
					   if(ChargesNotIndetify!=0){
						   buttonNode+='  <span><a class="btn btn-xs" href="javascript:void(0);" id="" onclick="addColor(4)">有收费无标识'+ChargesNotIndetify+'次</a></span>'
					   }
					   if(IndentifyPointExcep!=0){
						   buttonNode+=' <span><a class="btn btn-xs" href="javascript:void(0);" id="" onclick="addColor(5)">标识点异常'+IndentifyPointExcep+'次</a></span>'
					   }
					   if(ETCPlateNotMeet!=0){
						   buttonNode+='  <span><a class="btn btn-xs" href="javascript:void(0);" id="" onclick="addColor(3)">ETC车牌不符'+ETCPlateNotMeet+'次</a></span>'
					   }
					   if(ETCModelsNotMeet!=0){
						   buttonNode+='  <span><a class="btn btn-xs" href="javascript:void(0);" id="" onclick="addColor(2)">ETC车型不符'+ETCModelsNotMeet+'次</a></span>'
					   }
					   if(AxisGroupExcep!=0){
						   buttonNode+='  <span><a class="btn btn-xs" href="javascript:void(0);" id="" onclick="addColor(6)">轴组'+AxisGroupExcep+'次</a></span>'
					   }
					
                      $("#Abnorlabel").append(buttonNode);				   
					   
					   
					   
					   $("#myModal1").modal({backdrop:false});
			            $("#myModal1").modal({keyboard:false});
			            $('#myModal1').modal('show');
			}else{
			    	$("#liushui").empty();
			}
		 },
			   error:function(){
				   alert("网络异常");
			   }
		});
	 
 }

    //异常稽查流水
    $(function(){
        $('table.yccl-table tbody tr').dblclick(function(e){
        	console.dir(e.target);
            $("#myModal1").modal({backdrop:false});
            $("#myModal1").modal({keyboard:false});
            $('#myModal1').modal('show');
        });
        
        
        //异常稽查详情
        $('table.ycls-table tbody  #liushui tr').dblclick(function(){
            $("#myModal2").modal({backdrop:false});
            $("#myModal2").modal({keyboard:false});
            $('#myModal2').modal('show');
        });
        
        /*全(不)选事件*/
        $('#checkAll').click(function(){
            $('input.subCheckbox').attr("checked",this.checked);
        });
    })
    
    
    //上一条
    function beforeLiuShui(){
    	
    	 var num=parseInt($("#currentNum").val())-1;
    	 if(num<=0){
    		 alert("没有上一条了");
    		 return false;
    	 }else{
    		 $("[type='button'][value='"+num+"']").trigger("click");
    	 }
    }
    
    //下一条
    function afterLiuShui(){
    	 var num=parseInt($("#currentNum").val())+1;
    	 var ele=$("[type='button'][value='"+num+"']");
    	if(ele.length>0){
    		ele.trigger("click");
    	}else{
    		alert("没有下一条了");
    	} 
    }
    
  function addColor(type){
	  $("#liushui tr").css("color","#000");  
	  $.each($("#liushui tr[name='"+type+"']"), function(i,val){  
	 $(val).css("color","red");
	});  
  }  
    
  //导出
  function exportExcel(){
	  
	  var a=$("#liushui").html();
	  if(/^\s*?$/.test(a)){
		  alert("没有数据无法导出");
		  return false;
	  }else{
		  $("#exportForm").submit();
	  }
  }
  
 //批量处理打开
  function bachAction(){
	  var element=$("#liushui  input[type='checkbox']:checked");
	  if(element.length<=0){
		  alert("请先勾选需要批量处理的流水");
		  return false;
	  }
		  if(ifSameType()){
			  //将车牌全部设置到隐藏域
			  var batChplateID=""
				  $("#liushui  input[type='checkbox']:checked").each(function(i){ 
				     if(i==0){
				    	 batChplateID+=$(this).attr("plateID");
				     }else{
				    	 batChplateID+=","+$(this).attr("plateID");
				     }
				  })
			
			  $("#batChplateID").val(batChplateID);
			  $("#myModal3").modal({backdrop:false});
			  $("#myModal3").modal({keyboard:false});
			  $('#myModal3').modal('show');
		  }
  }
	  
	  //判断流水类型是否是同一类型
function ifSameType(){
		  var flag=true;
		  var selectType;
		  var element=$("#liushui  input[type='checkbox']:checked");
		  $.each(element,function(i,selectNode){
			  if(i==0){
				  selectType=$(selectNode).val();
			  }else{
				  if(selectType!=$(selectNode).val()){
					  flag=false;
					  alert("请选择同一稽查类型批量处理");
					  return false;
				  }
			  }
		 })
		 return flag;
 }
	  
	  
//批量上传灰名单
function abnorToGray(){
	console.log($("#plcl #jcjg"));
	if($("#plcl #jcjg").val()!="1"){
		alert("正常流水不能上传灰名单");
		return false;
	}
	var flag=dataNotNull($("#plcl  *[dataValidate]"));
	 if(flag==false){
		 return false;
	 }
	
	if(!plateValidate($("#plcl input[name='roGrayList.vehPlate']").val())){
		return false;
	}


	jQuery("#saveGrayForm").find("#loading").showLoading();
	/*var formData = new FormData($( "#saveGrayForm" )[0]);
	$.ajax({
		   type: "POST",
		   url: uri+"admin/grayList_abnorToGray.do",
		   data: formData,
		   processData: false,
		    contentType: false,
		    cache: false,
		   success: function(msg){
			jQuery("#saveGrayForm").find("#loading").hideLoading();
		     if(msg.msg==true){
		    	 alert("上传灰名单成功");
		     }else{
		    	 alert("上传灰名单失败");
		     }
		    $('#saveGrayForm')[0].reset();
		    window.location.reload();
		   },
		   error:function(){
				jQuery("#saveGrayForm").find("#loading").hideLoading();
			   alert("网络异常");				   
		   }
	});*/
	var form =get("saveGrayForm");
	form.action="grayList_abnorToGray.do";
	form.submit();


}

function fileValidate(thisDom){
	  var s=thisDom.value;
	   var filesuffix=s.substring(s.lastIndexOf(".")+1);
	   if("zip"!=filesuffix&&"rar"!=filesuffix&&s!=""){
		   alert("请选择zip或rar格式文件上传");
		   thisDom.outerHTML= thisDom.outerHTML;
          return false;
	   }   
}
  
//增加一个标签属性dataValidate  用于不为空的校验
function dataNotNull(all){
 	  var flag=true;
 	   all.each(function(){
 	   if($(this).val()==""){
 			alert($(this).attr("dataValidate")+"不能为空!");
 			flag=false;
 			return false; 
 		}
 	});
 		 if(flag==false){
 			 return false;
 		 }
   }

$(function(){
	$("#plcl input[name='roGrayList.feeMoney']").keydown(function (event) {
        var eventObj = event || e;
         var keyCode = eventObj.keyCode || eventObj.which;
         if ( keyCode == 110||keyCode == 46 ||keyCode == 8 || (keyCode >= 48 && keyCode <= 57) || (keyCode >= 96 && keyCode <= 105) ) 
            return true;
         else
         return false;
    });
	
	$("#plcl input[name='roGrayList.feeMoney']").bind("input propertychange", function () {
	    if (isNaN(parseFloat($(this).val())) || parseFloat($(this).val()) <0) $(this).val("");
	});


	$("#abnorDetails input[name='roGrayList.feeMoney']").keydown(function (event) {
		var eventObj = event || e;
		var keyCode = eventObj.keyCode || eventObj.which;
		if ( keyCode == 110||keyCode == 46 ||keyCode == 8 || (keyCode >= 48 && keyCode <= 57) || (keyCode >= 96 && keyCode <= 105) )
			return true;
		else
			return false;
	});

	$("#abnorDetails input[name='roGrayList.feeMoney']").bind("input propertychange", function () {
		if (isNaN(parseFloat($(this).val())) || parseFloat($(this).val()) <0) $(this).val("");
	});








	$("#myForm").find("*[number]").each(function () {

		$(this).keydown(function (event) {
			var eventObj = event || e;
			var keyCode = eventObj.keyCode || eventObj.which;
			if ( keyCode == 46 ||keyCode == 8 || (keyCode >= 48 && keyCode <= 57) || (keyCode >= 96 && keyCode <= 105) )
				return true;
			else
				return false;
		});

		$(this).bind("input propertychange", function () {
			if (isNaN(parseFloat($(this).val())) || parseFloat($(this).val()) <0) $(this).val("");
		});
	});



})


function plateValidate(plateNum){
	  //车牌 车牌颜色校验是否重复
   
    if(""==plateNum){
    	alert("车牌不能为空");
    	return false;
    }
    var express= /^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z_a-z]{1}[A-Z_a-z]{1}[警京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼]{0,1}[A-Z_0-9_a-z]{4}[A-Z_a-z_0-9挂学警港澳]{1}$/;
    if(!express.test(plateNum)){
    	alert("车牌不合法");
    	return false;
    }
    return true;
}

//流水正常
function abnorIsNormal(){
	/*nameRemove($( "#saveGrayForm" ));*/
	if($("#plcl #jcjg").val()!="0"){
		alert("灰名单流水不能审核为正常");
		return false;
	}
	jQuery("#saveGrayForm").find("#loading").showLoading();
	/*var formData = new FormData($( "#saveGrayForm" )[0]);
	$.ajax({
		   type: "POST",
		   url: uri+"admin/abnorCar_updateAbnor.do",
		   data: formData,
		   processData: false,
		    contentType: false,
		    cache: false,
		   success: function(msg){
			   jQuery("#saveGrayForm").find("#loading").hideLoading();
		     if(msg.msg==true){
		    	 alert("修改成功");
		     }else{
		    	 alert("修改失败");
		     }
		     $('#saveGrayForm')[0].reset();
		    window.location.reload();
		   },
		   error:function(){
			   jQuery("#saveGrayForm").find("#loading").hideLoading();
			   alert("网络异常");				   
		   }
	});*/
	var form =get("saveGrayForm");
	form.action="abnorCar_updateAbnor.do";
	form.submit();
	
}


//异常流水详情审核
function showDetails(plateid,idandcolor,laneEnSeriaNo,laneExSeriaNo,exTime){
	
	$("#abnorDetails  [name='conditions.batChplateID']").val(idandcolor);
	
	$.ajax({
		   type: "POST",
		   url: uri+"admin/abnorCar_getLaneExDetail.do",
		   data: {"plateNum":plateid},
		    cache: false,
		   success: function(msg){
		    var listDetail=msg.list;
		    $("#abnorDetails [name='EnStationID']").text(listDetail[0][1]);
		    $("#abnorDetails [name='ExStationID']").text(listDetail[0][6]);
		    $("#abnorDetails [name='EnTime']").text(listDetail[0][2].replace(/T/g," "));
		    $("#abnorDetails [name='ExTime']").text(listDetail[0][7].replace(/T/g," "));
		    $("#abnorDetails [name='EnVehicleClass']").text(listDetail[0][3]);
		    $("#abnorDetails [name='ExVehicleClass']").text(listDetail[0][8]);
		    $("#abnorDetails [name='Miles']").text(listDetail[0][17]);
		    $("#abnorDetails [name='EnVehiclePlate']").text(listDetail[0][5]);
		    $("#abnorDetails [name='ExVehiclePlate']").text(listDetail[0][10]);    
		    $("#abnorDetails [name='ExVehIdentifyPlate']").text(listDetail[0][11]);
		    $("#abnorDetails [name='OBUPlate']").text(listDetail[0][18]);
		    $("#abnorDetails [name='AxisGroupNum']").text(listDetail[0][13]);
		    $("#abnorDetails [name='EnVehicleStatus']").text(listDetail[0][4]);
		    $("#abnorDetails [name='ExVehicleStatus']").text(listDetail[0][9]);
		    $("#abnorDetails [name='OverLoadPercent']").text(listDetail[0][19]*100+"%");
		    $("#abnorDetails [name='overLoadWeight']").text(listDetail[0][15]);
		    $("#abnorDetails [name='totalWeight']").text(listDetail[0][14]);
		    $("#abnorDetails [name='realPath']").text(listDetail[0][16]);
		    $("#abnorDetails [name='dealStatus']").text(listDetail[0][12]);
		    $("#myModal2").modal({backdrop:false});
            $("#myModal2").modal({keyboard:false});
            
            //获取入口和出口图片数据
            getEntrancesAndExits(laneEnSeriaNo, laneExSeriaNo, exTime);
            
            
            $('#myModal2').modal('show');
		   },
		   error:function(){
			   alert("网络异常");				   
		   }
	});
}

//获取入口和出口图片数据
function getEntrancesAndExits(laneEnSeriaNo, laneExSeriaNo, exTime){
	var url=uri+"admin/carStatistic_getEntrancesAndExits.do";
	var params3 = {
		'carStatistic.exSerialNo':laneExSeriaNo,
		'carStatistic.enSerialNo':laneEnSeriaNo,
		'carStatistic.exTime':exTime
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



//详情界面流水正常
function abnorIsNormalDetail(){
/*	nameRemove($( "#detailSaveForm" ));*/
	if($("#abnorDetails #jcjg").val()=="1"){
		alert("灰名单流水不能审核为正常");
		return false;
	}
	jQuery("#detailSaveForm").find("#loading").showLoading();
	/*var formData = new FormData($( "#detailSaveForm" )[0]);
	$.ajax({
		   type: "POST",
		   url: uri+"admin/abnorCar_updateAbnor.do",
		   data: formData,
		   processData: false,
		    contentType: false,
		    cache: false,
		   success: function(msg){
			   jQuery("#detailSaveForm").find("#loading").hideLoading();
		     if(msg.msg==true){
		    	 alert("修改成功");
		     }else{
		    	 alert("修改失败");
		     }
		     $('#detailSaveForm')[0].reset();
		    window.location.reload();
		   },
		   error:function(){
			   jQuery("#detailSaveForm").find("#loading").hideLoading();
			   alert("网络异常");				   
		   }
	});*/
	var form =get("detailSaveForm");
	form.action="abnorCar_updateAbnor.do";
	form.submit();
	
}


//详情界面 灰名单
function abnorToGrayDetails(){
	
	if($("#abnorDetails #jcjg").val()!="1"){
		alert("正常流水不能上传灰名单");
		return false;
	}
	var flag=dataNotNull($("#abnorDetails  *[dataValidate]"));
	 if(flag==false){
		 return false;
	 }
	
	if(!plateValidate($("#abnorDetails input[name='roGrayList.vehPlate']").val())){
		return false;
	}
	
	/*jQuery("#detailSaveForm").find("#loading").showLoading();
	var formData = new FormData($( "#detailSaveForm" )[0]);
	$.ajax({
		   type: "POST",
		   url: uri+"admin/grayList_abnorToGray.do",
		   data: formData,
		   processData: false,
		    contentType: false,
		    cache: false,
		   success: function(msg){
			   jQuery("#detailSaveForm").find("#loading").hideLoading();
		     if(msg.msg==true){
		    	 alert("上传灰名单成功");
		     }else{
		    	 alert("上传灰名单失败");
		     }
		     $('#detailSaveForm')[0].reset();
		    window.location.reload();
		   },
		   error:function(){
			   jQuery("#detailSaveForm").find("#loading").hideLoading();
			   alert("网络异常");				   
		   }
	});*/
	jQuery("#detailSaveForm").find("#loading").showLoading();
	var form =get("detailSaveForm");
	form.action="grayList_abnorToGray.do";
	form.submit();
}

//  属性移除的方法
function  nameRemove(dom){
	  var all=dom.find("*[remove]");
	   all.each(function(){
		   $(this).removeAttr("name")
		   
	});
}
// 表单清空的方法
function resetform(dom){

  dom.each(function (i) {
	 if($(this).attr("type")=="text"){
	 	$(this).val("");
	 }else if ($(this).attr("type")=="checkbox"){
	 	$(this).attr("checked",false);
	 }else  if($(this).attr("type")=="radio"){
		 $(this).attr("checked",false);
	 }

  });
}

//图片对比
function pictureContrast(){
	var picArr = $("#liushui input:checkbox[class=subCheckbox]:checked");
	
	if(picArr.length==0){
		alert("请选择需要图片对比的流水");
		return false;
	} else if(picArr.length!=2){
		alert("请选择两条流水进行图片对比");
		return false;
	} else{
		var laneExSeriaNo = picArr.get(0).getAttribute("laneExSeriaNo");
		var laneExSeriaNoTwo = picArr.get(1).getAttribute("laneExSeriaNo");
		
		var exTime = picArr.get(0).getAttribute("exTime");
		var exTimeTwo = picArr.get(1).getAttribute("exTime");
		
		var url=uri+"admin/carStatistic_getAllExitPicture.do";
		var params3 = {
			'carStatistic.exSerialNo':laneExSeriaNo,
			'carStatistic.exSerialNoTwo':laneExSeriaNoTwo,
			'carStatistic.exTime':exTime,
			'carStatistic.exTimeTwo':exTimeTwo
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
						$("#exVehicleImageOne").attr("src", srcName);
					} else{
						$("#exVehicleImageOne").attr("src", "/images/error.jpg");
					}
					

					//出口图片2
					if(!!imageList[1] && (typeof imageList[1])==='object'){
						srcName = uri+"admin/checkPicture_getDocumentImg.do?imgUrl="+imageList[1].exListImageName+"&fileDate="+$.trim(imageList[1].squadDate);
						$("#exVehicleImageTwo").attr("src", srcName);
					} else{
						$("#exVehicleImageTwo").attr("src", "/images/error.jpg");
					}
				} else{
					$("#exVehicleImageOne").attr("src", "/images/error.jpg");
					$("#exVehicleImageTwo").attr("src", "/images/error.jpg");
				}
			},
			fail:function(){
				console.log("无图片数据...");
				
				$("#enVehicleImage").attr("src", "/images/more.png");
				$("#exVehicleImage").attr("src", "/images/more.png");
			}
		});
		
		
		$('#picContrastModal').modal('show');
	}
	
}


  function get(name){
	  var obj = document.getElementById(name);
	  if(obj != null)
		  return obj;
	  obj = document.getElementsByName(name);
	  if(obj != null && obj.length > 0)
		  return obj[0];
	  return null;
  }