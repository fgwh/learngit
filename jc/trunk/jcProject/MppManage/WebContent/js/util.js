function get(name){
	var obj = document.getElementById(name);
	if(obj != null)
  		return obj;
    obj = document.getElementsByName(name);
	if(obj != null && obj.length > 0)
		return obj[0];
	return null;
}

/**
 * 讲input中输入的字符转换为大写
 * @param value 需要转换的 input dom object
 * @author Donghua Wang
 */
function changeInputUpper(value) {
    $(value).val($(value).val().toLocaleUpperCase());
}

/**
 * 身份证号码验证 
 * @param num
 * @return 
 */
function checkIdcard(num){
    num = num.toUpperCase();
    //身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X。
    if (!(/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(num)))
    {
        alert('输入的身份证号长度不对，或者号码不符合规定！\n15位号码应全为数字，18位号码末位可以为数字或X。');
        return false;
    }
    //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
    //下面分别分析出生日期和校验位
    var len, re;
    len = num.length;
    if (len == 15)
    {
        re = new RegExp(/^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/);
        var arrSplit = num.match(re);
 
        //检查生日日期是否正确
        var dtmBirth = new Date('19' + arrSplit[2] + '/' + arrSplit[3] + '/' + arrSplit[4]);
        var bGoodDay;
        bGoodDay = (dtmBirth.getYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
        if (!bGoodDay)
        {
            alert('输入的身份证号里出生日期不对！');
            return false;
        }
        else
        {
                //将15位身份证转成18位
                //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
                var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
                var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
                var nTemp = 0, i;
                num = num.substr(0, 6) + '19' + num.substr(6, num.length - 6);
                for(i = 0; i < 17; i++)
                {
                    nTemp += num.substr(i, 1) * arrInt[i];
                }
                num += arrCh[nTemp % 11];
                return true;
        }
    }
    if (len == 18)
    {
        re = new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/);
        var arrSplit = num.match(re);
 
        //检查生日日期是否正确
        var dtmBirth = new Date(arrSplit[2] + "/" + arrSplit[3] + "/" + arrSplit[4]);
        var bGoodDay;
        bGoodDay = (dtmBirth.getFullYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
        if (!bGoodDay)
        {
            alert('输入的身份证号里出生日期不对！');
            return false;
        }
    else
    {
        //检验18位身份证的校验码是否正确。
        //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
        var valnum;
        var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
        var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
        var nTemp = 0, i;
        for(i = 0; i < 17; i++)
        {
            nTemp += num.substr(i, 1) * arrInt[i];
        }
        valnum = arrCh[nTemp % 11];
        if (valnum != num.substr(17, 1))
        {
            alert('18位身份证的校验码不正确！应该为：' + valnum);
            return false;
        }
        return true;
    }
    }
    return false;
}

/**
 * 将日期字符串转换为js Date对象，兼容ie,Firefox,Chrome等浏览器
 * @author wubiao
 */
function stringToDate(dStr) {
	var reg = /^(\d{4})\-(\d{1,2})\-(\d{1,2})(\s(\d{2}):(\d{2}):(\d{2}))?$/; //like this '2014-05-29 16:47:00' or '2014-5-29'
	if(!reg.test(dStr)) {
		return undefined;
	}
	var date;
	if(window.navigator.userAgent.indexOf("Firefox") >= 0) {
		var ary1 = dStr.split(' ');
		var ary2 = ary1[0].split('-');
		if(ary1.length>1) {			
			var ary3 = ary1[1].split(':');
			date = new Date(ary2[0],parseInt(ary2[1])-1,ary2[2],ary3[0],ary3[1],ary3[2]);
		}else {
			date = new Date(ary2[0],parseInt(ary2[1])-1,ary2[2],'','','');
		}
	}else {
		date = new Date(dStr.replace('-','/'));
	}
	return date;
}

//导出报表Excel
function runReportExcel() {
	var form = get("myForm");
	var url = form.action; //保存原来的action url
	form.action = "report_excel.do";
	form.submit();
	form.action = url;
}

/**
 * 绿通报表柱状图画图工具
 * 
 * @param targetObj 目标容器
 * @param sourceString 画图源字符串
 * @param barGraphNm 柱状图名称
 * @return
 */
function drawReportBarGraph(targetObj,sourceString,barGraphNm) {
	
	var typeArray = new Array();//类型数组		    
    var passArray = new Array();//查验通过数
    var failArray = new Array();//查验未通过数
    var targetData = new Array();//柱状图展示目标数据
    var statisArray = sourceString.split(";");
    
    var temp;	
    for(var i=0; i<statisArray.length; i++) {		    
	    temp = statisArray[i].split(",");
	    //alert(temp[0] + ' ' + temp[1] + ' ' + temp[2]);
	    typeArray.push(temp[0]);				    
	    passArray.push(parseInt(temp[1]));	
	    failArray.push(parseInt(temp[2]));		   		    
    }
    
    targetData.push(passArray);
    targetData.push(failArray);
    targetData.push(barGraphNm);
    
    var desc = ['查验总数','未通过数'];
		
    DrawMobileBar(targetObj,typeArray,targetData,desc);//DrawMobileBar(domID,category,datas,ObjectName)
}

var FIELD_UTIL_SPLIT = ';';
var COMMA_UTIL_SPLIT = ',';
var AND_UTIL_SPLIT = '&';
/**
 * 登录页面首页内容的显示
 * @param targetObj
 * @param sourceString
 * @return
 */
function drawRightBarGraph(targetObj, sourceString){
	var typeArray = new Array();//类型数组		    
    var leftTagArray = new Array();//小标题的显示
    var xAxisArray = new Array();//横坐标的显示
    var dataArray = new Array();
    var strArray = sourceString.split(FIELD_UTIL_SPLIT);
    //leftTag的数组形成
    var leftTagStrs = strArray[1].split(COMMA_UTIL_SPLIT);
    var targetStrs = strArray[3].split(COMMA_UTIL_SPLIT);
    for(var i=0;i<leftTagStrs.length;i++){
    	leftTagArray.push(leftTagStrs[i]);
    	
    	var targetData = new Array();//纵坐标数量的显示  
        var dataStrs = targetStrs[i].split(AND_UTIL_SPLIT);
        for(var k=0;k<dataStrs.length;k++){
        	targetData.push(dataStrs[k]);
        }
        var params = {
        		name:leftTagStrs[i],
        		type:'bar',
        		data:targetData
        };
        dataArray.push(params);
    }
	
    //横坐标数组的形成
    var xAxisStrs = strArray[2].split(COMMA_UTIL_SPLIT);
    for(var j=0;j<xAxisStrs.length;j++){
    	xAxisArray.push(xAxisStrs[j]);
    }
    //var t = {title:'nimei',leftTag:['黄','蓝'],xAxis:['1','2','3','4','5','6','7'],data:[{name:'黄',type:'bar',data:[10,20,30,40,50,60,70]},{name:'蓝',type:'bar',data:[15,25,35,45,55,65,75]}]};
    DrawSingleBar(targetObj, {title: strArray[0], leftTag: leftTagArray, xAxis: xAxisArray, data: dataArray});
}
/**
 * 漏检流水柱状图
 * @param targetObj
 * @param sourceString
 * @return
 */
function drawRightBarGraph_zsy(targetObj, sourceString){
	var typeArray = new Array();//类型数组		    
    var leftTagArray = new Array();//小标题的显示
    var xAxisArray = new Array();//横坐标的显示
    var dataArray = new Array();
    var strArray = sourceString.split(FIELD_UTIL_SPLIT);
    //leftTag的数组形成
    var leftTagStrs = strArray[1].split(COMMA_UTIL_SPLIT);
    var targetStrs = strArray[3].split(COMMA_UTIL_SPLIT);
    for(var i=0;i<leftTagStrs.length;i++){
    	leftTagArray.push(leftTagStrs[i]);
    	
    	var targetData = new Array();//纵坐标数量的显示  
        var dataStrs = targetStrs[i].split(AND_UTIL_SPLIT);
        for(var k=0;k<dataStrs.length;k++){
        	targetData.push(dataStrs[k]);
        }
        var params={};
        if(leftTagStrs.length>1) 
          params = {
        		name:leftTagStrs[i],
        		type:'bar',
        		data:targetData,
          };
        else
        	params = {
        		name:leftTagStrs[i],
        		type:'bar',
        		data:targetData,
        		barWidth:'50'
          };
        	
        dataArray.push(params);
    }
    //横坐标数组的形成
    var xAxisStrs = strArray[2].split(COMMA_UTIL_SPLIT);
    for(var j=0;j<xAxisStrs.length;j++){
    	xAxisArray.push(xAxisStrs[j]);
    }
    if(dataArray[0].data.length<7)
    	$('#'+targetObj).width('100%');
    else if(dataArray[0].data.length<11)
    	$('#'+targetObj).width('200%');
    else if(dataArray[0].data.length>=50)
    	$('#'+targetObj).width(dataArray[0].data.length*10+'%');
    else
        $('#'+targetObj).width(dataArray[0].data.length*20+'%');
    //var t = {title:'nimei',leftTag:['黄','蓝'],xAxis:['1','2','3','4','5','6','7'],data:[{name:'黄',type:'bar',data:[10,20,30,40,50,60,70]},{name:'蓝',type:'bar',data:[15,25,35,45,55,65,75]}]};
    DrawSingleBar(targetObj, {title: strArray[0], leftTag: leftTagArray, xAxis: xAxisArray, data: dataArray});
}



/**
 * 折线图显示
 * @return
 */
function drawRightMobileLineGraph(targetObj, sourceString){
	
	var typeArray = new Array();//类型数组		    
    var leftTagArray = new Array();//小标题的显示
    var xAxisArray = new Array();//横坐标的显示
    var dataArray = new Array();
    var strArray = sourceString.split(FIELD_UTIL_SPLIT);
    
    //leftTag的数组形成
    var leftTagStrs = strArray[1].split(COMMA_UTIL_SPLIT);
    var targetStrs = strArray[3].split(COMMA_UTIL_SPLIT);
    if(targetObj.indexOf("Money") == -1){
	    for(var i=0;i<leftTagStrs.length;i++){
	    	leftTagArray.push(leftTagStrs[i]);
	    	
	    	var targetData = new Array();//纵坐标数量的显示  
	        var dataStrs = targetStrs[i].split(AND_UTIL_SPLIT);
	        
	        for(var k=0;k<dataStrs.length;k++){
	        	targetData.push(dataStrs[k]);
	        }
	        
	        var params = {
	        		name:leftTagStrs[i],
	        		type:'line',
	        		data:targetData
	        };
	        dataArray.push(params);
	    }
    } else{
    	for(var i=0;i<leftTagStrs.length;i++){
	    	leftTagArray.push(leftTagStrs[i]);
	    	
	    	var targetData = new Array();//纵坐标数量的显示  
	        var dataStrs = targetStrs[i].split(AND_UTIL_SPLIT);
	        
	        for(var k=0;k<dataStrs.length;k++){
	        	targetData.push((parseFloat(dataStrs[k])/100).toFixed(2));
	        }
	        
	        var params = {
	        		name:leftTagStrs[i],
	        		type:'line',
	        		data:targetData
	        };
	        dataArray.push(params);
	    }
    }
	
    //横坐标数组的形成
    var xAxisStrs = strArray[2].split(COMMA_UTIL_SPLIT);
    for(var j=0;j<xAxisStrs.length;j++){
    	xAxisArray.push(xAxisStrs[j]);
    }
    
//alert(strArray[0]);
    var data = new Array();
    data.push(dataArray);
    data.push(strArray[0]);
    data.push(leftTagArray);
    DrawMobileLine(targetObj, xAxisArray, data);
}

/**
 * 饼图画图工具
 * 
 * @param targetObj 目标容器
 * @param pieName 饼图名称
 * @param sourceString	画图源字符串
 * @param colorAry 饼图颜色数组
 * @param bgColor 背景颜色
 * @return
 */
function drawPieGraph(targetObj,pieName,sourceString,colorAry,bgColor) {	
	var itemArray = sourceString.split(";");
    var target = new Array();//目标数组,[ ['名字1',50] , ['名字2',40] ,['名字3',100] ]	
    var temp = new Array();	
    for(var i=0; i<itemArray.length; i++) {		    
	    temp = itemArray[i].split(",");
	    var item = new Array();
	    item.push(temp[0]);
	    item.push(parseInt(temp[1]));
	    target.push(item);
    }
    DrawPoeBase(targetObj,pieName,target,colorAry,bgColor);
}

/**
 * 饼图画图工具
 * 
 * @param targetObj 目标容器
 * @param pieName 饼图名称
 * @param sourceString	画图源字符串
 * @return
 */
function drawPieGraphTwo(targetObj,sourceString,radiu) {
    var itemArray = sourceString.split(FIELD_UTIL_SPLIT);
	var target = new Array();//目标数组,	[ {value:335, name:'直接访问'},{value:310, name:'邮件营销'}]
	var temp = new Array();
	var item;
	if(targetObj.indexOf("Money")==-1){
	    for(var i=1; i<itemArray.length; i++) {		    
		    temp = itemArray[i].split(COMMA_UTIL_SPLIT);
		    
		    item = new Object();
		    item.name = temp[0];
		    item.value = parseInt(temp[1]);
		    
		    target.push(item);
	    }
	} else{
		for(var i=1; i<itemArray.length; i++) {		    
		    temp = itemArray[i].split(COMMA_UTIL_SPLIT);
		    
		    item = new Object();
		    item.value =(parseFloat(temp[1])/100).toFixed(2);
		    item.name = temp[0]+item.value+"(元)";
		    
		    target.push(item);
	    }
	}
	
    DrawMobilePie(targetObj,itemArray[0],target,radiu);
}
/**
 * 计算两个日期之间的差值
 * @param strDateStart
 * @param strDateEnd
 * @returns
 */
function getDays(strDateStart,strDateEnd){
	   var strSeparator = "-"; //日期分隔符
	   var oDate1;
	   var oDate2;
	   var iDays;
	   oDate1= strDateStart.split(strSeparator);
	   oDate2= strDateEnd.split(strSeparator);
	   var strDateS = new Date(oDate1[0] + "-" + oDate1[1] + "-" + oDate1[2]);
	   var strDateE = new Date(oDate2[0] + "-" + oDate2[1] + "-" + oDate2[2]);
	   iDays = parseInt(Math.abs(strDateS - strDateE ) / 1000 / 60 / 60 /24)//把相差的毫秒数转换为天数 
	   return iDays ;
}

jQuery.fn.showLoading = function(overLayObjectId){
	 current = 0; 
	 //setTimeout("changeTip()", 1000); 
	 //锁屏div
	 var C=document.createElement("div");
	 C.id="overlayDiv";
	 document.body.appendChild(C);
	 $(C).addClass('loading-indicator-overlay');
	 // Set overlay position
	 var overlayDiv_width;
	 var overlayDiv_height;  
	 var border_top_width = $("#"+overLayObjectId).css('border-top-width');
	 var border_left_width = $("#"+overLayObjectId).css('border-left-width');
	 border_top_width = isNaN(parseInt(border_top_width)) ? 0 : border_top_width;
	 border_left_width = isNaN(parseInt(border_left_width)) ? 0 : border_left_width;
	 
	 var overlay_left_pos = $("#"+overLayObjectId).offset().left + parseInt(border_left_width);
	 var overlay_top_pos = $("#"+overLayObjectId).offset().top + parseInt(border_top_width);
	 overlayDiv_width = parseInt($("#"+overLayObjectId).width()) + parseInt($("#"+overLayObjectId).css('padding-right')) + parseInt($("#"+overLayObjectId).css('padding-left'));
	 overlayDiv_height = parseInt($("#"+overLayObjectId).height()) + parseInt($("#"+overLayObjectId).css('padding-top')) + parseInt($("#"+overLayObjectId).css('padding-bottom'));
	 $(C).css('width', overlayDiv_width.toString() + 'px');
	 $(C).css('height', overlayDiv_height.toString() + 'px');
	 $(C).css('left', overlay_left_pos.toString() + 'px');
	 $(C).css('position', 'absolute');
	 $(C).css('top', overlay_top_pos.toString() + 'px' );
	 $(C).css('z-index', 5000);
	 
	 // Loading div
	 var I="数据处理中，请稍后...";
	 var J=document.createElement("DIV");
	 document.body.appendChild(J);
	 J.id="loadingDiv";
	 J.innerHTML=""+I+"";
	 J.style.position="absolute";
	 J.style.textAlign="center";
	 J.style.padding="20px 15px";
	 J.style.border="2 solid #A5BADE";
	 J.style.color="#2b6188";
	 J.style.backgroundColor="#ffffff";
	 J.style.width="300px";
	 J.style.heigth="150px";
	 J.style.fontFamily="Arial, Helvetica, sans-serif";
	 J.style.fontWeight="bold";
	 J.style.fontSize="12px";
	 J.style.zIndex="9999";
	 
	 // Set top margin
	 var indicatorTop = overlay_top_pos;
	 var indicatorLeft = overlay_left_pos;
	 $(J).css('left', (indicatorLeft + (($(C).width() - parseInt($(J).width())) / 2)).toString()  + 'px');
	 $(J).css('top', (indicatorTop + (($(C).height() - parseInt($(J).height())) / 2)).toString()  + 'px');
	 
	 // Show the overlay
	 $("#overlayDiv").show();
	 $("#loadingDiv").show();
	};
