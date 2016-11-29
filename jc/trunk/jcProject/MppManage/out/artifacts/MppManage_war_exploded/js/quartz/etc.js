
$(document).ready(function(){
	$("#mainIframe").load(function(){ 
//	
//		var width = $(this).contents().find("#content").width() + 0; 
		var height = $(this).contents().find("#content").height() + 20;
//
//		$(this).width( width < 1200 ? 1200 : width ); 
		$(this).height( height < 1000 ? 1000 : height ); 
	}); 

	$(".dropdown .dropdown-toggle").hover(function(){
		$(this).trigger("click");
	});
	
	$('.unmenu > a').click(function(){
		if($(".open").size()>0){
			$('.open ul').slideUp();
			$('.open').removeClass("open");
			$(".chevron i").attr("class","icon-chevron-down");
		}
		$(".active").removeClass("active");
		$(this).parent().addClass("active");
	});
	
	$('.submenu > a').click(function()
	{
		var submenu = $(this).siblings('ul');
		var li = $(this).parents('li');
		var chevron = $(this).find(".chevron i");
		var submenus = $('#sidebar li.submenu ul');
		var submenus_parents = $('#sidebar li.submenu');
		if(li.hasClass('open'))
		{
			submenus.slideUp();
			li.removeClass('open').removeClass('active');
			chevron.attr("class","icon-chevron-down");
		} else 
		{
			$(".chevron i").attr("class","icon-chevron-down");
			submenus.slideUp();			
			submenu.slideDown();
			submenus_parents.removeClass('open');		
			$("#sidebar li").removeClass('active');
			li.addClass('active').addClass('open');
			chevron.attr("class","icon-chevron-up");
		}
	});
	$('.submenu > ul > li > a').click(function(){
		$('.submenu > ul > li > a').removeClass("active");
		$(this).addClass("active");
	});
});