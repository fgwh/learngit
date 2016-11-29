var log = {};
$(function() {	
	  $(":radio[name='fanwei']").click(function(){
		  if($(this).val()==0){
			     $("#myForm").attr('action','grayList_list.do');
		     }else{
		    	 $("#myForm").attr('action','grayList_proList.do');
		     }
		     $("#myForm").submit(); 
	  });

	
	//邦定搜索按钮，添加点击事件查询数据列表
	$("#submit").on("click", function(event) {
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
			//验证查询条件合法性
			if (log.checkDate()) {
                $("#myForm").submit();
            }
			ev.preventDefault();
		}
	};
});

//验证查询条件
    log.checkDate = function() {
        var v_start = $("#startQueryDate").val();//new Date($("#startQueryDate").val().replace("-", "/"));
        var v_end = $("#endQueryDate").val();//new Date($("#endQueryDate").val().replace("-", "/"));
        if (v_start > v_end) {
            alert("结束时间不能小于开始时间");
            return false;
        }            
        return true;
    };


var fileDate = '';
function showxiangqing(id,vehPlate,vehPlateColor,vehFlag,vehClass,vehType,vehInfo,
		vehBigType,vehMidType,vehSmallType,applicant,applicateTime,applicateOrg,
		fileName,uploadTime,status,opinion,axisGroup,feeCount,feeMoney,type){
	 fileDate=uploadTime.substring(0,10);
	 $("[name='vehPlate']").text(vehPlate);
	 $("[name='vehPlateColor']").text(vehPlateColor);
	 if(vehFlag==1){
		 $("[name='vehFlag']").text('客车');
	 }else if(vehFlag==2){
		 $("[name='vehFlag']").text('货车');
	 }else{
		 $("[name='vehFlag']").text('非计重或不分客货');
	 }
	 $("[name='vehClass']").text(vehClass);
	 $("[name='vehType']").text(vehType);
	 $("[name='vehInfo']").text(vehInfo);
	 $("[name='opinion']").text(opinion);
	 $("[name='vehBigType']").text(vehBigType);
	 $("[name='vehMidType']").text(vehMidType);
	 $("[name='vehSmallType']").text(vehSmallType);
	 $("[name='applicant']").text(applicant);
	 $("[name='applicateTime']").text(applicateTime.substring(0,10));
	 $("[name='applicateOrg']").text(applicateOrg);
	 $("[name='fileName']").val(fileName);
	 $("[name='axisGroup']").text(axisGroup);
	 $("[name='feeCount']").text(feeCount+'次');
	 $("[name='feeMoney']").text(feeMoney+'元');
	 if(type=="audit"){
		 $("#auditId").val(id);
		 $("[name='opinionTr']").css("display","none");
		 $("[name='auditContent']").css("display","");
		 $("#myModalLabel4").text('可疑车辆审核');
	 }else{
		 if(""!=opinion){
			 $("[name='opinionTr']").css("display","");
		 }else{
			 $("[name='opinionTr']").css("display","none");
		 }
		 if(""==fileName){
			 $("#fileIsExit").css("display","none");
		 }else{
			 $("#fileIsExit").css("display","");
		 }
		 $("[name='auditContent']").css("display","none");
		 $("#myModalLabel4").text('显示详情');
	 }
	 $("#myModal4").modal('show');
	
}

//文件下载
function onloadFile(){
	var fileName=$("[name='fileName']").val();
	window.location.href="download_execute.do?filename="+encodeURI(encodeURI(fileName))+"&fileDate="+fileDate;
}


function save(){	
	if(dataValidate()){
		jQuery("#saveForm").find("#loading").showLoading();
		if($("#feeCount").val()==''){
			$("#feeCount").removeAttr('name');
		}								
		setConditions('saveForm');
	    $('#saveForm').submit();
	}
}

function openSaveForm(){
	$("#myModalLabel5").html("可疑车辆登记");
	$('#saveForm')[0].reset();
	$("#evidence").attr("dataValidate","证据链");
	$("#redPoint").text("*");
	$("#myModal5").modal('show');
	
}

//修改
function update(id,vehPlate,vehPlateColor,vehFlag,vehClass,vehType,vehInfo,axisGroup,feeCount,feeMoney,
		vehBigType,vehMidType,vehSmallType,applicant,applicateTime,applicateOrg,
		fileName,uploadTime,updateTime,status,staffNo){
	$("#myModalLabel5").html("灰名单修改");
	$("#updateId").val(id);
	$("#plateNum").val(vehPlate.substring(0,1));
	$("#carNo").val(vehPlate.substring(1,7));
	$("#vehPlateColor").val(vehPlateColor);
	$("#vehClass").val(vehClass);
	$("#vehType").val(vehType);
	$("#vehFlag").val(vehFlag);
	$("#vehBigType").val(vehBigType);
	$("#vehMidType").val(vehMidType);
	$("#vehSmallType").val(vehSmallType);
	$("#vehInfo").val(vehInfo);
	$("#axisGroup").val(axisGroup);
	$("#feeCount").val(feeCount);
	$("#feeMoney").val(feeMoney);
	$("#evidence").val("");
	$("#evidence").removeAttr("dataValidate");
	$("#redPoint").text("");
	$("#applicant").val(applicant);
	$("#applicateTime").val(applicateTime.substring(0,10));
	$("#applicateOrg").val(applicateOrg);
	$("#fileName").val(fileName);
	$("#uploadTime").val(uploadTime);
	$("#staffNo").val(staffNo);
	$("#myModal5").modal('show');
		
}

function upload(id){
	$("#uploadId").val(id);
	$('#myModal3').modal('show');
		
}

//上传证据链
function uploadEvidence(){	
	jQuery("#uploadForm").find("#loading").showLoading();
	setConditions('uploadForm');
    $('#uploadForm').submit();
}




function audit(status){
	var id= $("#auditId").val();
	var interceptOption= $("[name='interceptOption']").val();
	var startDate= $("[name='startDate']").val();
	var endDate= $("[name='endDate']").val();
	var opnionTwo= $("[name='opnionTwo']").val();
	
	if(status==0){
    	//审核通过数据校验	 
		var inOp=$("select[name='interceptOption']").val();
		if(""==inOp){
			alert("拦截选项不能为空");
			return false;
		}
		
	     if(""==startDate){
	    	 alert("开始时间不能为空");
	    	 return false;
	     }
	     if(""==endDate){
	    	 alert("结束时间不能为空");
	    	 return false;
	     }
	     var start=new Date(startDate.replace("-", "/").replace("-", "/"));
	     var end=new Date(endDate.replace("-", "/").replace("-", "/"));
	     if(start>end){
	    	 alert("开始时间不能大于结束时间");
	    	 return false;
	     }
}
	
	$("[name='auditButton']").attr('disabled',"true");
	
	$.ajax({
		   type: "POST",
		   url: uri+"admin/grayList_audit.do",
		   data: {'conditions.id':id,
			      'conditions.interceptOption':interceptOption,
			      'conditions.startDate':startDate,
			      'conditions.endDate':endDate,
			      'conditions.opinion':opnionTwo,
			      'conditions.status':status,			      
		            },
		   success: function(json){
			 if(json.sta==true) {
			     $("#myForm").submit(); 
			 }else{
				 alert("审核失败！");
			 }
			 $("[name='auditButton']").attr('disabled',"false");
			 $("#myModal4").modal('hide');
		   }
		});
	
}

function deleteById(id){
	if(confirm("是否删除该黑名单信息？")){
		$.post(uri+"admin/grayList_delete.do?",{"conditions.id":id},function(json){
			if(json.status==true){
	            $("#myForm").submit();
			}			
		});
	}	
}


//增加一个标签属性dataValidate  用于不为空的校验
function dataNotNull(){
 	  var flag=true;
 	  var all=$("*[dataValidate]");
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


//车牌校验
function plateValidate(){
	  //车牌 车牌颜色校验是否重复
    var  plateNum=$("#plateNum").val();
    var carNo=$("#carNo").val();
    if(""==carNo||""==plateNum){
    	alert("车牌不能为空");
    	return false;
    }
    var express= /^[A-Z_a-z]{1}[警京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼]{0,1}[A-Z_a-z_0-9]{4}[A-Z_a-z_0-9挂学警港澳]{1}$/;
    if(!express.test(carNo)){
    	alert("车牌不合法");
    	return false;
    }
    var vehPlateColor=$("#vehPlateColor option:selected").val();
    if(""==vehPlateColor){
    	alert("车牌颜色不能为空");
    	return false;
    }
   /* carNo=carNo.toUpperCase();
    var id=$("#updateId").val();
    if(""!=plateNum&&""!=vehPlateColor&&""!=carNo){
    	$.ajax({
    	    	async:false,
    		   type: "POST",
    		   url: uri+"admin/grayList_plateValidate.do",
    		   data: {'conditions.id':id,
    			      'conditions.vehPlate':plateNum+carNo,
    			      'conditions.vehPlateColor':vehPlateColor
    		            },
    		            success: function(json){
    		    			 if(json.status==false){ 
    		    				 alert("该车辆已加入黑名单");  
    			    			 flag=false;
    		    		      }
    		}
    		});
    	if(flag==false){
    		return false;
    	}
    }else{
    	alert("请选择车牌号和颜色");
        return false;
    }*/
        return true;
}

function setConditions(model){
	$("#"+model+" input[name='conditions.plateNum']").val($("#myForm select[name='conditions.plateNum']").val());
	$("#"+model+" input[name='conditions.carNo']").val($("#myForm input[name='conditions.carNo']").val());
	$("#"+model+" input[name='conditions.vehClass']").val($("#myForm select[name='conditions.vehClass']").val());
	$("#"+model+" input[name='conditions.vehFlag']").val($("#myForm select[name='conditions.vehFlag']").val());
	$("#"+model+" input[name='conditions.vehPlateColor']").val($("#myForm select[name='conditions.vehPlateColor']").val());
	$("#"+model+" input[name='pager.pageSize']").val($("#pagesize").val());
	$("#"+model+" input[name='pager.currentPage']").val($(".toPage input[name='pager.currentPage']").val());
}

// 数据校验
function dataValidate(){
	//车牌校验
	if(!plateValidate()){
		return false;
	}
	//不为空校验
	if(dataNotNull()==false){
		return false;
	}
	
/*	   var s=$("#saveForm").find("[name='roBlackList.file']").val();
 	   var filesuffix=s.substring(s.indexOf(".")+1);
	   if("zip"!=filesuffix&&"rar"!=filesuffix&&s!=""){
		   alert("请选择zip或rar格式文件上传");
             return false;
	   }   */
		//文件类型验证
/*	  var s=$("#saveForm").find("[name='roGrayList.file']").val();
	  var filesuffix=s.substring(s.indexOf(".")+1);
	   if("zip"!=filesuffix&&"rar"!=filesuffix&&s!=""){
		   alert("请选择zip或rar格式文件上传");
            return false;
	   }   */
	   return true;
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

function doExcelReoprt(){
	var dataSource  = $('input[name="fanwei"]:checked').val();//"1"全省，"0"路段
	var plateNum = $("select[name='conditions.plateNum'] option:selected").val();
	var carNo = $("input[name='conditions.carNo']").val();
	var vehClass = $("select[name='conditions.vehClass'] option:selected").val(); 
	var vehFlag = $("select[name='conditions.vehFlag'] option:selected").val();
	var vehPlateColor = $("select[name='conditions.vehPlateColor'] option:selected").val();
	var params = {
		'conditions.dataSource' : dataSource,
		'conditions.plateNum' : plateNum,
		'conditions.carNo' : carNo,
		'conditions.vehClass':vehClass,
		'conditions.vehFlag':vehFlag,
		'conditions.vehPlateColor':vehPlateColor
	};
	
	if (dataSource == 1) {
		params.excelFlag="progray";
		url = uri + "admin/report_excelNum.do";
		$.post(url, params, reportProExcelSucc, "json");
	} else if(dataSource == 0) {
		params.excelFlag="rogray";
		url = uri + "admin/report_excelNum.do";
		$.post(url, params, reportRoExcelSucc, "json");
	}
}

function reportProExcelSucc(jsonTemp) {
	var map = jsonTemp;
	var status = map.status;
	if (status == 0) {
		alert("暂无数据，不能导出");
	} else if (status == 1) {
		alert("暂时不支持大数据量导出");
	} else {
		var form = get("myForm");
		var url = form.action; // 保存原来的action url
		form.action = "proGrayListReport_doExportExcel.do";
		form.submit();
		form.action = url;
	}
}

function improtExcel(){
	jQuery("#myForm1").find("#loading").showLoading();
    $('#myForm1').submit();
}

function reportRoExcelSucc(jsonTemp) {
	var map = jsonTemp;
	var status = map.status;
	if (status == 0) {
		alert("暂无数据，不能导出");
	} else if (status == 1) {
		alert("暂时不支持大数据量导出");
	} else {
		var form = get("myForm");
		var url = form.action; // 保存原来的action url
		form.action = "roGrayListReport_doExportExcel.do";
		form.submit();
		form.action = url;
	}
}
$(function(){
	$("input[name='roGrayList.feeCount']").keydown(function (event) {
        var eventObj = event || e;
         var keyCode = eventObj.keyCode || eventObj.which;
         if ( keyCode == 46 ||keyCode == 8 || (keyCode >= 48 && keyCode <= 57) || (keyCode >= 96 && keyCode <= 105) ) 
            return true;
         else
         return false;
    });
	
/*	$("input[name='roGrayList.feeCount']").bind("input propertychange", function () {
	    if (isNaN(parseFloat($(this).val())) || parseFloat($(this).val()) <0) $(this).val("");
	});*/
	
	
	$("input[name='roGrayList.feeMoney']").keydown(function (event) {
        var eventObj = event || e;
         var keyCode = eventObj.keyCode || eventObj.which;
         if ( keyCode == 110||keyCode == 46 ||keyCode == 8 || (keyCode >= 48 && keyCode <= 57) || (keyCode >= 96 && keyCode <= 105) ) 
            return true;
         else
         return false;
    });
	
/*	$("input[name='roGrayList.feeMoney']").bind("input propertychange", function () {
	    if (isNaN(parseFloat($(this).val())) || parseFloat($(this).val()) <0) $(this).val("");
	});*/
});


/*//逃费金额验证
function moneyValidate(thisDom){
	 var val=thisDom.value;
	 if(""==val){
		 alert("逃费总金额不能为空");
		 return false;
	 }
	 if(! $.isNumeric(val)){
		 alert("逃费金额必须为数字");
		 thisDom.value=0;
		 return false;
	 }
	 if(parseInt(val)<0){
		 alert("逃费金额不为负数");
		 thisDom.value=0;
		 return false;
	 }
	 
}

//逃费次数验证
function numVali(thisDom){
	 var val=thisDom.value;
	 if(""==val){
		 return false;
	 }
	 if(!$.isNumeric(val)){
		 alert("逃费次数必须为数字");
		 thisDom.value=0;
		 return false;
	 }
	    if(parseInt(val)<0){
	    	alert("逃费次数不为负数")
	    	m.value=0;
	    	 return false;
	    }
	  }
*/
//文件格式验证
function fileValidate(thisDom){
	  var s=thisDom.value;
	   var filesuffix=s.substring(s.lastIndexOf(".")+1);
	   if("zip"!=filesuffix&&"rar"!=filesuffix&&s!=""){
		   alert("请选择zip或rar格式文件上传");
		   thisDom.outerHTML= thisDom.outerHTML;
          return false;
	   }   
}
