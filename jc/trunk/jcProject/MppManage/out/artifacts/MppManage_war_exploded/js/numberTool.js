var errorMsg = new Array();		//以form 为key的表单字段错误信息 
var separator = '@#$';
var shortMsg = 'field must be short';
var positiveShortMsg = 'field must be positive short';
var intMsg = 'field must be int';
var floatMsg = 'field must be float';
var doubleMsg = 'field must be double';
var positiveDoubleMsg = 'field must be positive double';
var maxLengthMsg = 'field length is over max length';
var decimalMsg = 'field must be decimal';
/*
 * 添加字段错误信息
 */
function addErrorMsg(inputObj){
	var name = $(inputObj).parents("form").attr('name');
	var msg = $(inputObj).attr("name");
	if(errorMsg[name].indexOf(msg+separator) == -1){
		errorMsg[name] += (msg+separator);
	}
}

/*
 * 添加字段错误信息
 */
function delErrorMsg(inputObj){
	var name = $(inputObj).parents("form").attr('name');
	var msg = $(inputObj).attr("name");
	var index = errorMsg[name].indexOf(msg);
	if(index > -1){
		errorMsg[name] = errorMsg[name].replace(msg+separator,'');
	}
}

/*
 * 显示错误提示信息
 */
function showMsg(msg, inputObj){
	$(inputObj).parent("td").next().html('<font style="color:red">'+msg+'</font>');
	addErrorMsg(inputObj);
}

/*
 * 清除错误提示信息
 */
function clearMsg(inputObj){
	$(inputObj).parent("td").next().empty();
	delErrorMsg(inputObj);
}

function checkShort(obj){
	if(isShort(obj, null)){
		clearMsg(obj);
	}
}

function checkPositiveShort(obj){
	if(isPositiveShort(obj, null)){
		clearMsg(obj);
	}
}

function checkInt(obj){
	if(isInt(obj, null)){
		clearMsg(obj);
	}
}

function checkPositiveInt(obj){
	if(isPositiveInt(obj, null)){
		clearMsg(obj);
	}
}

function checkFloat(obj){
	if(isFloat(obj, null)){
		clearMsg(obj);
	}
}

function checkDouble(obj){
	if(isDouble(obj, null)){
		clearMsg(obj);
	}
}

function checkPositiveDouble(obj){
	if(isPositiveDouble(obj, null)){
		clearMsg(obj);
	}
}

function checkMaxLength(obj){
	if(isOverMaxLength(obj, null)){
		clearMsg(obj);
	}
}

function checkMinLength(obj){
	if(isLessMinLength(obj, null)){
		clearMsg(obj);
	}
}

function checkDecimal(obj){
	if(isDecimal(obj,null)){
		clearMsg(obj);
	}
}

function checkUpperCase(obj){
	if(isUpperCase(obj,null)){
		clearMsg(obj);
	}
}

function checkLowerCase(obj){
	if(isLowerCase(obj,null)){
		clearMsg(obj);
	}
}

function checkVehPlate(obj){
	if(isVehPlate(obj,null)){
		clearMsg(obj);
	}
}

function checkCardNo(obj){
	if(isCardNo(obj,null)){
		clearMsg(obj);
	}
}

/*
 * 提交表单校验
 */
function checkForm(obj){
	var name = $(obj).attr('name');
	if(errorMsg[name].length>0){
		alert('提交表单内存在字段输入错误信息');
		return false;
	}
	
	return true;
}



$(function(){

	//初始化错误信息数组
	$("form").each(function(i,n){
		var name = $(n).attr("name");
		errorMsg[name] = '';
	});

	//=========================================绑定表单校验事件开始==========================================
	//绑定校验最大长度事件
	$("[maxLength]").on("input propertychange",function(){checkMaxLength(this)});
	//绑定校验最小长度事件
	$("[minLength]").on("input propertychange",function(){checkMinLength(this)});
	//绑定校验指定精确小数事件
	$("[decimal]").on("input propertychange",function(){checkDecimal(this)});
	//绑定校验短整型数事件
	$(".nt_isShort").on("input propertychange",function(){checkShort(this)});
	//绑定校验正短整型数事件
	$(".nt_isPositiveShort").on("input propertychange",function(){checkPositiveShort(this)});
	//绑定校验整型数事件
	$(".nt_isInt").on("input propertychange",function(){checkInt(this)});
	//绑定校验正整型数事件
	$(".nt_isPositiveInt").on("input propertychange",function(){checkPositiveInt(this)});
	//绑定校验浮点型数事件
	$(".nt_isFloat").on("input propertychange",function(){checkFloat(this)});
	//绑定校验双精度型数事件
	$(".nt_isDouble").on("input propertychange",function(){checkDouble(this)});
	//绑定校验正双精度型数事件
	$(".nt_isPositiveDouble").on("input propertychange",function(){checkPositiveDouble(this)});
	//绑定校验全部大写英文字母事件
	$(".nt_isUpperCase").on("input propertychange",function(){checkUpperCase(this)});
	//绑定校验全部小写英文字母事件
	$(".nt_isLowerCase").on("input propertychange",function(){checkLowerCase(this)});
	//绑定校验车牌事件
	$(".nt_isVehPlate").on("input propertychange",function(){checkVehPlate(this)});
	//绑定校验卡号事件
	$(".nt_isCardNo").on("input propertychange",function(){checkCardNo(this)});
	 
	//=========================================绑定表单校验事件结束===================================================

});



/**
 *  是否是浮点数值.
 *  inputObj ：输入框.  msg(可选参数)：当不符合条件时的提示信息.
 *  mandatory(可选参数): 强制的, 必须输入值. true or false,  默认是false.
 */
function isFloat(inputObj, msg){
	var value = inputObj.value;
	var mandatory = inputObj.getAttribute('mandatory');
	if((mandatory==null || mandatory=='true') && _isEmpty(value)){
		return true;
	}
	if(mandatory!='true' && _isEmpty(value)){
		if(msg == null){
			msg = inputObj.title + "必须输入";
		}
		showMsg(msg, inputObj);
		return false;
	}
	if(!_isFloat(value)) {      //  if(!isFinite(str)) 效果一样.	
		if(msg == null){
			msg = inputObj.title + "必须为数字";
		}
		showMsg(msg, inputObj);
		return false;
	}
	return true;
}

/*
 * 长度是否越界
 */
function isOverMaxLength(inputObj,msg){
	var mandatory = inputObj.getAttribute('mandatory');
	if((mandatory==null || mandatory=='true') && _isEmpty(inputObj.value)){
		return true;
	}
	if(mandatory!='true' && _isEmpty(inputObj.value)){
		if(msg == null){
			msg = inputObj.title + "必须输入";
		}
		showMsg(msg, inputObj);
		return false;
	}
	var maxLength = inputObj.getAttribute("maxLength");
	if(inputObj.value.length>maxLength){
		if(msg == null){
			msg = inputObj.title + "必须在指定长度内";
		}
		showMsg(msg, inputObj);
		return false;
	}
	return true;
}

/*
 * 长度是否不足
 */
function isLessMinLength(inputObj,msg){
	var mandatory = inputObj.getAttribute('mandatory');
	if((mandatory==null || mandatory=='true') && _isEmpty(inputObj.value)){
		return true;
	}
	if(mandatory!='true' && _isEmpty(inputObj.value)){
		if(msg == null){
			msg = inputObj.title + "必须输入";
		}
		showMsg(msg, inputObj);
		return false;
	}
	var minLength = inputObj.getAttribute("minLength");
	if(inputObj.value.length<minLength){
		if(msg == null){
			msg = inputObj.title + "必须在指定长度内";
		}
		showMsg(msg, inputObj);
		return false;
	}
	return true;
}
/*
 * 是否为精确小数
 */
function isDecimal(inputObj,msg){
	var mandatory = inputObj.getAttribute('mandatory');
	if((mandatory==null || mandatory=='true') && _isEmpty(inputObj.value)){
		return true;
	}
	if(mandatory!='true' && _isEmpty(value)){
		if(msg == null){
			msg = inputObj.title + "必须输入";
		}
		showMsg(msg, inputObj);
		return false;
	}
	var dec = $(inputObj).attr("decimal").split(",");
	var left = dec[0];
	var right = dec[1];
	var values = inputObj.value.split(".");
	if(values.length == 1){
		if(isNaN(inputObj.value) || (inputObj.value.length>left)){
			if(msg == null){
				msg = inputObj.title + "必须为精确位数的小数";
			}
			showMsg(msg, inputObj);
			return false;
		}
	}else{
		if(isNaN(values[0]) || isNaN(values[1]) || values[0].length>left || values[1].length>right){
			if(msg == null){
				msg = inputObj.title + "必须为精确位数的小数";
			}
			showMsg(msg, inputObj);
			return false;
		}
	}
	return true;
}


/**
 *  是否是数值.
 *  inputObj ：输入框.  msg(可选参数)：当不符合条件时的提示信息.
 *  mandatory(可选参数): 强制的, 必须输入值. true or false,  默认是false.
 */
function isDouble(inputObj, msg){
	var value = inputObj.value;
	var mandatory = inputObj.getAttribute('mandatory');
	if((mandatory==null || mandatory=='true') && _isEmpty(value)){
		return true;
	}
	if(mandatory!='true' && _isEmpty(value)){
		if(msg == null){
			msg = inputObj.title + "必须输入";
		}
		showMsg(msg, inputObj);
		return false;
	}
	if(!_isDouble(value)) {      //  if(!isFinite(str)) 效果一样.	
		if(msg == null){
			msg = inputObj.title + "必须为数字";
		}
		showMsg(msg, inputObj);
		return false;
	}
	return true;
}

/**
 * 是否是正数(0算正数).
 *  inputObj ：输入框.     msg(可选参数)：当不符合条件时的提示信息.
 *  mandatory(可选参数): 强制的, 必须输入值. true or false,  默认是false.
 */
function isPositiveDouble(inputObj, msg){
	var value = inputObj.value;
	// 如果可以为空.
	var mandatory = inputObj.getAttribute('mandatory');
	if((mandatory==null || mandatory=='true') && _isEmpty(value)){
		return true;
	}
	if(mandatory!='true' && _isEmpty(value)){
		if(msg == null){
			msg = inputObj.title + "必须输入";
		}
		showMsg(msg, inputObj);
		return false;
	}
	if(!_isPositiveDouble(value)) {
		if(msg == null){
			msg = inputObj.title + "必须为正数";
		}
		showMsg(msg, inputObj);
		return false;
	}
	return true;
}

/**
 * 是否是整数.
 *  inputObj ：输入框.   msg(可选参数)：当不符合条件时的提示信息.
 *  mandatory(可选参数): 强制的, 必须输入值. true or false,  默认是false.
 */
function isInt(inputObj, msg){
	var value = inputObj.value;
	// 如果可以为空.
	var mandatory = inputObj.getAttribute('mandatory');
//	if((mandatory==null || !mandatory) && _isEmpty(value)){
	if((mandatory==null || mandatory=='true') && _isEmpty(value)){
		return true;
	}
	if(mandatory!='true' && _isEmpty(value)){
		if(msg == null){
			msg = inputObj.title + "必须输入";
		}
		showMsg(msg, inputObj);
		return false;
	}
	if(!_isInt(value)) {
		if(msg == null){
			msg = inputObj.title + "必须为整数";
		}
		showMsg(msg, inputObj);
		return false;
	}

	return true;
}

/**
 * 是否是正整数(0算正整数).
 *  inputObj ：输入框.        msg(可选参数): 当不符合条件时的提示信息.
 *  mandatory(可选参数): 强制的, 必须输入值. true or false,  默认是false.
 */
function isPositiveInt(inputObj, msg) {
	var value = inputObj.value;
	// 如果可以为空.
	var mandatory = inputObj.getAttribute('mandatory');
	if((mandatory==null || mandatory=='true') && _isEmpty(value)){
		return true;
	}
	if(mandatory!='true' && _isEmpty(value)){
		if(msg == null){
			msg = inputObj.title + "必须输入";
		}
		showMsg(msg, inputObj);
		return false;
	}
	if(!_isPositiveInt(value)) {
		if(msg == null){
			msg = inputObj.title + "必须为正整数";
		}
		showMsg(msg, inputObj);
		return false;
	}
	return true;
}

/**
 * 是否是整数.
 *  inputObj ：输入框.   msg(可选参数)：当不符合条件时的提示信息.
 *  mandatory(可选参数): 强制的, 必须输入值. true or false,  默认是false.
 */
function isShort(inputObj, msg) {
	var value = inputObj.value;
	// 如果可以为空.
	var mandatory = inputObj.getAttribute('mandatory');
	if((mandatory==null || mandatory=='true') && _isEmpty(value)){
		return true;
	}
	if(mandatory!='true' && _isEmpty(value)){
		if(msg == null){
			msg = inputObj.title + "必须输入";
		}
		showMsg(msg, inputObj);
		return false;
	}
	if(!_isShort(value)) {
		if(msg == null){
			msg = inputObj.title + "必须为数字";
		}
		showMsg(msg, inputObj);
		return false;
	}
	return true;
}

/**
 * 是否是正整数(0算正整数).
 *  inputObj ：输入框.        msg(可选参数): 当不符合条件时的提示信息.
 *  mandatory(可选参数): 强制的, 必须输入值. true or false,  默认是false.
 */
function isPositiveShort(inputObj, msg) {
	var value = inputObj.value;
	// 如果可以为空.
	var mandatory = inputObj.getAttribute('mandatory');
	if((mandatory==null || mandatory=='true') && _isEmpty(value)){
		return true;
	}
	if(mandatory!='true' && _isEmpty(value)){
		if(msg == null){
			msg = inputObj.title + "必须输入";
		}
		showMsg(msg, inputObj);
		return false;
	}
	if(!_isPositiveShort(value)) {
		if(msg == null){
			msg = inputObj.title + "必须为正数";
		}
		showMsg(msg, inputObj);
		return false;
	}
	return true;
}

/*
 * 是否为大写英文字母
 */
function isUpperCase(inputObj,msg){
	var value = inputObj.value;
	var mandatory = inputObj.getAttribute('mandatory');
	if((mandatory==null || mandatory=='true') && _isEmpty(value)){
		return true;
	}
	if(mandatory!='true' && _isEmpty(value)){
		if(msg == null){
			msg = inputObj.title + "必须输入";
		}
		showMsg(msg, inputObj);
		return false;
	}
	var pattern = new RegExp("^[A-Z]+$")
	if(!pattern.test(value)) {      //  if(!isFinite(str)) 效果一样.	
		if(msg == null){
			msg = inputObj.title + "必须为大写字母";
		}
		showMsg(msg, inputObj);
		return false;
	}
	return true;
}

/*
 * 是否为小写英文字母
 */
function isLowerCase(inputObj,msg){
	var value = inputObj.value;
	var mandatory = inputObj.getAttribute('mandatory');
	if((mandatory==null || mandatory=='true') && _isEmpty(value)){
		return true;
	}
	if(mandatory!='true' && _isEmpty(value)){
		if(msg == null){
			msg = inputObj.title + "必须输入";
		}
		showMsg(msg, inputObj);
		return false;
	}
	var pattern = new RegExp("^[a-z]+$")
	if(!pattern.test(value)) {      //  if(!isFinite(str)) 效果一样.	
		if(msg == null){
			msg = inputObj.title + "必须为小写字母";
		}
		showMsg(msg, inputObj);
		return false;
	}
	return true;
}

/**
 * 验证车牌信息
 * @param inputObj
 * @param msg
 */
function isVehPlate(inputObj,msg){
	var value = inputObj.value;	
	var mandatory = inputObj.getAttribute('mandatory');
	if((mandatory==null || mandatory=='true') && _isEmpty(value)){
		return true;
	}
	$($(inputObj).parent("td").find('font')[0]).remove();
	if(mandatory!='true' && _isEmpty(value)){
		$(obj).parent("td").append('<font style="color:red;margin-left:250px" class="abc">'+$(obj).attr("title")+'必须输入 </font>');
		return false;
	}
	var pattern = new RegExp("^[A-Z|a-z]{1}[A-Z|a-z|0-9]{5}$")
	if(!pattern.test(value)) {       
		if(msg == null){
			msg = inputObj.title + "填写错误，请重新填写";
		}
		showMsg(msg, inputObj);
		return false;
	}
	return true;
}

/**
 * 验证卡号信息
 * @param inputObj
 * @param msg
 */
function isCardNo(inputObj,msg){
	var value = inputObj.value;
	var mandatory = inputObj.getAttribute('mandatory');
	if((mandatory==null || mandatory=='true') && _isEmpty(value)){
		return true;
	}
	if(mandatory!='true' && _isEmpty(value)){
		if(msg == null){
			msg = inputObj.title + "必须输入";
		}
		showMsg(msg, inputObj);
		return false;
	}
	var pattern = new RegExp("^[0-9]{6,16}$")
	if(!pattern.test(value)) {       
		if(msg == null){
			msg = inputObj.title + "填写错误，请重新填写";
		}
		showMsg(msg, inputObj);
		return false;
	}
	return true;
}

function _isEmpty(value) {
	return value == null || value.replace(/(^\s*)|(\s*$)/g, "").length == 0;
}

function _isShort(value) {
	return !_isEmpty(value) && !isNaN(value) && value.indexOf(".") == -1 && (value >= -32768 && value <= 32767);
}

function _isPositiveShort(value) {
	return _isShort(value) && value >= 0;
}

function _isInt(value) {
	return !_isEmpty(value) && !isNaN(value) && value.indexOf(".") == -1 && (value >= -0x80000000 && value <= 0x7fffffff);
}

function _isPositiveInt(value) {
	return _isInt(value) && value >= 0;
}

function _isLong(value) {
	return !_isEmpty(value) && !isNaN(value) && value.indexOf(".") == -1 && (value >= -0x8000000000000000 && value <= 0x7fffffffffffffff);
}

function _isPositiveLong(value) {
	return _isLong(value) && value >= 0;
}

function _isFloat(value) {
	return !_isEmpty(value) && !isNaN(value) && (value >= -3.4028235e+38 && value <= 3.4028235e+38);
}

function _isPositiveFloat(value) {
	return _isFloat(value) && value >= 0;
}

function _isDouble(value) {
	return !_isEmpty(value) && !isNaN(value) && (value >= -1.7976931348623157e+308 && value <= 1.7976931348623157e+308);
}

function _isPositiveDouble(value) {
	return _isDouble(value) && value >= 0;
}