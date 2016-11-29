<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" language="javascript">
function getLeftMenu(){
	 jQuery.get("sys_leftJson.do",function(data){
		if (data == null || data.length > 1000) {
				window.location.href = "admin_logout.do";
			}
			$("#nav").html("");
			if(null!=data&&data.length!=0&&data.length<1000){
				var tableStr="";
				for(var i = 0;i<data.length;i++){
					var item = data[i];
					if(item.level == 1){
						var childMenu = getLeftChildMenu(item.id,data);
						if(childMenu){
							tableStr += '<li><a><div class="Ticon Ticon'+ i +'"></div><font size="3">' + item.name + '</font><i class="arrow"></i></a>' + childMenu;
						}
						else{
							var url = "#";
							if(item.url) url = item.url + '" target="rightFrame';
							tableStr += '<li><a href="' + url + '" ><i class="'+ item.image +'"></i><font size="3">' + item.name + '</font></a>';
						}
						tableStr += '</li>';
					}
				}
				$("#nav").html(tableStr);
			}
	}); 
}
function getLeftChildMenu(id,data){
	var flag=0;
	var tableStr = "";
	for(var i = 0;i<data.length;i++){
		var item = data[i];
		if(data[i].parent!=null&&data[i].parent.id==id){
			flag++;
			if(flag==1){
				tableStr +='<li><a target="rightFrame" href="${basePath}'+item.url+'?mid='+item.id+'"><s></s><font size="3">'+item.name+'</font></a></li>';
			}
			else{
				tableStr +='<li><a target="rightFrame" href="${basePath}'+item.url+'?mid='+item.id+'"><font size="3">'+item.name+'</font></a></li>';
			}
			
		}
	}
	if(tableStr!=""&&tableStr.length>0){
		tableStr = '<ul class="sub-menu">' + tableStr + '</ul>';
	}
	return tableStr;
}

$(function(){
	getLeftMenu();
	
	/*  $('body').on("click", "#nav a:not('.sub-menu a')", function()
	{
		var submenu = $(this).siblings('ul');
		var li = $(this).parents('li');
		var chevron = $(this).find(".arrow");
		var submenus = $('#nav li ul.sub-menu');
		if(li.hasClass('open'))
		{
			submenus.slideUp();
			li.removeClass('open').removeClass('active');
			chevron.attr("class","arrow icon-angle-left");
		} else 
		{
			$(".arrow").attr("class","arrow icon-angle-left");
			submenus.slideUp();			
			submenu.slideDown();
			$('#nav li.open').removeClass('open');
			$("#nav li.active").removeClass('active');
			li.addClass('active').addClass('open');
			chevron.attr("class","arrow icon-angle-down");
		}
	});
	
	$('body').on("click", "#nav .sub-menu a",function(){
		$("#nav .sub-menu a.active").removeClass("active");
		$(this).addClass("active");
	}); */
	
	var width = $(window).width() - $("#page-sidebar").width() - 30;
	$("#c_right").css("width",width+"px");
	
	$("#rightFrame").load(function(){ 
		try{
            var height = $(this).contents().find("body").height();
            if(!height) height = 540;
			$(this).height(height+30);
		}
		catch(e)
		{

		}
    }); 
});

</script>
	<div id="side-nav">
    	<ul id="nav">
    	</ul>
    </div>