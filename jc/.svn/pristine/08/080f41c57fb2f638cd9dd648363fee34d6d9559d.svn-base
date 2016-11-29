<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${basePath}/js/plugins/jqrotate/jquery.rotate.min.js"></script>
</head>
<body>
	<!-- 幻灯片模态框  -->
	<div class="modal fade" id="modalImg" tabindex="-1" role="dialog" >
	  <div class="modal-dialog" style="width:820px;"> 
	    <div class="modal-content" >
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title">放大图片</h4>
	      </div>
	      <div class="modal-body" style="text-align:center;">
	        <div id="myCarousel" class="carousel slide" >  <!-- data-ride="carousel" -->
				  <!-- Indicators -->
				  <ol class="carousel-indicators">
				    <!-- <li data-target="#carousel-example-generic" data-slide-to="0" ></li>
				    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
				    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
				    <li data-target="#carousel-example-generic" data-slide-to="3"></li>
				    <li data-target="#carousel-example-generic" data-slide-to="4"></li> -->
				  </ol>
				
				  <!-- Wrapper for slides -->
				  <div class="carousel-inner" role="listbox" >
				    <!-- <div class="item active">
				      <img src="messagehwjc_getDocumentImg.do?imgUrl=f9079487-786c-41ce-a794-81fb23dfe31c.png&date=Wed Sep 09 2015 09:12:43 GMT+0800 (中国标准时间)&fileDate=2015-09-09" alt="...">
				    </div>
				    <div class="item">
				      <img src="messagehwjc_getDocumentImg.do?imgUrl=f9079487-786c-41ce-a794-81fb23dfe31c.png&date=Wed Sep 09 2015 09:12:43 GMT+0800 (中国标准时间)&fileDate=2015-09-09" alt="...">
				    </div> -->
				    
				  </div>
				
				  <!-- Controls -->
				  <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
				    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
				  </a>
				  <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
				    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				  </a>
			</div>
	      </div>
	      
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	<%//获得includeAction.jsp传来的值:
	    String seletor = request.getParameter("seletor");
	%>
	<input id="seletor" value="<%=seletor %>" type="hidden" /> 
	<script type="text/javascript" src="${basePath}/js/pictureHuan.js"></script>	
	
</body>
</html>