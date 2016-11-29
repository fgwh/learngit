//显示幻灯片
     var showImageKuan = function(seletor) {
    	 //$("#myModal5").find("img").on("click",function(){
    	 $(seletor).find("img").on("click",function(){
    		 console.log("aaa");
    		  var ht = ''; 
    		  var htpic = '';  //图片html
    		  var csrc = $(this).attr("src");	//当前点击的img src
    		  var num=$(this).parent().find("img[src!='/images/more.png'][src!='']").size();  //图片个数
    		  var ng; //点击图片排列次序
    		  if(num<=1){
    			  $(".carousel-control").attr("style","display:none");
    			  
    		  }else{
    			  $(".carousel-control").removeAttr("style");
    			  //添加。
        		  for(var i=0;i<num;i++){
        			  /* if(i==ng){
        				  ht+='<li data-target="#carousel-example-generic" data-slide-to="'+i+'" class="active"></li>';
        			  }else{
        				  ht+='<li data-target="#carousel-example-generic" data-slide-to="'+i+'"></li>';
        			  } */
        			  ht+='<li data-target="#myCarousel" data-slide-to="'+i+'"></li>';
        		  }
    		  }
    		  
			//添加宽灯片图片
    		  $(this).parent().find("img[src!='/images/more.png'][src!='']").each(function(i,n){
    			 if($(n).attr("src") == csrc){
    				 htpic += '<div class="item active" style="margin-left:130px;"><img src="'+ $(n).attr("src")+'" style="height:700px;" /></div>';
    				// ng=i;
    			 }else{
    				 htpic += '<div class="item" style="margin-left:130px;"><a><img src="'+ $(n).attr("src")+'" style="height:700px;" /></a></div>';
    			 }
    		  });
    		  
    		 
    		  $("#modalImg .carousel-indicators").html(ht); //添加引导圈
    		  $('#modalImg [role="listbox"]').html(htpic);  //添加图片黄灯片
			  $("#modalImg").modal("show");   		 
    		  
    	 });
     }
     
     
	 //旋转
	 $('#modalImg').on('shown.bs.modal', function (e) {
		 $('#myCarousel').carousel({
			 interval: 10000
		 });
		 var rotate = 0;
		  var imgsrc=null;
		  $('#modalImg [role="listbox"] img').rotate({
	    	    bind: {
	    	        click: function () {
	    	        	if(imgsrc != $(this).attr("src")){
	    	        		rotate = 0;
	    	        	}
	    	        	rotate += 90;
	    	        	imgsrc = $(this).attr("src");
	    	            $(this).rotate({
	    	                //duration: 1000,
	    	               	//angle: 0,
	    	                animateTo: rotate,
	    	            })
	    	        }
	    	    }
	     });
		 
	 });
	

	 var seltor = $("#seletor").val();
	/* $(seltor).on("shown.bs.modal", function (e) {
		 showImageKuan($(this));
	 });*/
	 
	 console.log(seltor);
	 showImageKuan($(seltor));
     
     