		$(document).ready(function(e) {
			Show(0);
		});
		function Show(a){
			switch(a){
				case 0:
				MessageBox.Show("普通1按钮对话框", "普通对话框，一个确定按钮，点击关闭窗口");
				break;
				case 1:
				MessageBox.Show("普通2个按钮对话框", "双按钮对话框，包括确定和取消按钮，可以传入不同的回调函数在点击确认或者取消的时候回调执行",false,2);
				break;
				case 3:
				MessageBox.Show("模态对话框", "模态对话框，当对话框打开后您必须关闭他才能进行其他操作。<br />点击确定查看完整js代码以及详细使用方法",true,1,function(){ location.href="http://www.pengyaou.com/LegendsZ/File/2014/08/05/20140805113048304.html";});
				break;
			}
		}
		var MessageBox = {
			mWidth: "300",
			mHeight: "180",
			isMoving: true,
			titleMaxLength: 10,
			target: null,
			bgTar:null,
			currentX: 0,
			currentY: 0,
			Show: function(aTitle, aMsg, isModal,btnNub, aYesFunction, aNoFunction) {
				MessageBox.Close();
				aTitle = aTitle.length >= this.titleMaxLength ? aTitle.substr(0, this.titleMaxLength) + "..." : aTitle;
				//浏览器宽度
				var browserWidth = $(window).width();
				var browserHeight = $(window).height();
				
				if(isModal==true){
					$bg = $("<div></div>");
					$bg.css({"width": browserWidth+"px","height": browserWidth+"px","position": "fixed","top":"0px","left":"0px","z-index": 998,"background-color": "#efefef","opacity":"0.5"});
					$("body").append($bg);
					MessageBox.bgTar=$bg;
					$bg.click(function(){
					});
				}
				
				$box = $("<div></div>");
				MessageBox.target = $box;
				$box.css({"width": "0px","height": "0px","position": "fixed","border": "1px solid #ccc","z-index": 999,"background-color": "#fff"});
				$title = $("<div><h3></h3></div>");
				$title.css({"width": "100%","border-bottom": "1px solid #ccc"});
				$title.find("h3").css({"line-height": "20px","padding": "5px 0 5px 10px","margin": "0","font": "15px \"微软雅黑\", Arial, Helvetica, sans-serif"});
				$title.find("h3").html(aTitle);
				$title.hover(function() {
					$title.css({
						"cursor": "move"
					});
				});
				var drog = false;
				$title.mousedown(function(e) {
					drog = true;
					MessageBox.currentX = e.pageX;
					MessageBox.currentY = e.pageY;
					$("body").mousemove(function(e) {
						if (drog == true) MessageBox.Drog(e);
					});
				});
				$("body").mouseup(function() {
					drog = false;
				});
				$box.append($title);
				if (MessageBox.isMoving == true) {
					$box.animate({  //弹窗位置
						width: this.mWidth + "px",
						height: this.mHeight + "px",
						"left": browserWidth  - this.mWidth + "px",
						"top": browserHeight - this.mHeight  + "px"
					}, 300);
				} else {
					$box.css("width", this.mWidth + "px").css("height", this.mHeight + "px");
				}
				$box.hover(function() {$box.css({"-webkit-box-shadow": "0px 0px 8px #ccc","-moz-box-shadow": "0px 0px 8px #ccc","box-shadow": "0px 0px 8px #ccc"});
				}, function() {
					$box.css({"-webkit-box-shadow": "0px 0px 5px #ccc","-moz-box-shadow": "0px 0px 5px #ccc","box-shadow": "0px 0px 5px #ccc"});
				});
				$center = $("<div></div>");
				$center.html(aMsg);
				$center.css({"height": MessageBox.mHeight - 80 + "px","width": "95%","margin": "0 auto","margin-top": "5px","color": "#808080","font": "13px \"微软雅黑\", Arial, Helvetica, sans-serif"});
				$box.append($center);

				$foot = $("<div></div>");
				$foot.css({"height": "39px","width": "100%","margin-top": "5px","background-color": "#efefef"});
				if (btnNub == 2) {
					$btnNo = $("<a>取消</a>");
					$btnNo.css({
						"font": "13px \"微软雅黑\", Arial, Helvetica, sans-serif",
						"-webkit-border-radius": "3px",
						"-moz-border-radius": "3px",
						"border-radius": "3px",
						"float": "right",
						"padding": "3px 10px 3px 10px",
						"margin": "5px 10px 0 0",
						"cursor": "pointer",
						"background-color": "#fff",
						"border": "1px solid #ccc"
					});
					$btnNo.hover(function() {
						$btnNo.css("background-color", "#efefef");
					}, function() {
						$btnNo.css("background-color", "#fff");
					});
					$foot.append($btnNo);
					$btnNo.click(function() {
						if (aNoFunction != null) aNoFunction();
						MessageBox.Close();
					});
				}
				$btnYes = $("<a>确定</a>");
				$btnYes.attr("class", "button");
				$btnYes.css({"font": "13px \"微软雅黑\", Arial, Helvetica, sans-serif","-webkit-border-radius": "3px","-moz-border-radius": "3px","border-radius": "3px","float": "right","padding": "3px 10px 3px 10px","margin": "5px 10px 0 0",
					"cursor": "pointer",
					"background-color": "#fff",
					"border": "1px solid #ccc"
				});
				$btnYes.hover(function() {$btnYes.css("background-color", "#efefef");}, function() {$btnYes.css("background-color", "#fff");});
				$foot.append($btnYes);
				$btnYes.click(function() {
					if (aYesFunction != null) aYesFunction();
					MessageBox.Close();
				});
				$box.append($foot);
				$("body").append($box);
			},
			//关闭
			Close: function() {
				$(this.target).remove();
				if(this.bgTar!=null)$(this.bgTar).remove();
			},
			//拖动
			Drog: function(e) {
				var x = e.pageX;
				var y = e.pageY;
				var cy = $box.offset().top - $(document.body).scrollTop() + (y - $(document.body).scrollTop() - (MessageBox.currentY - $(document.body).scrollTop()));
				$("#headMenuItema").html(cy);
				$box.css("left", $box.offset().left + (x - MessageBox.currentX) + "px").css("top", cy + "px");
				MessageBox.currentX = x;
				MessageBox.currentY = y;
			}
		}
