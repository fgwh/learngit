<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<title></title>
<link rel="stylesheet" type="text/css"
	href="${basePath}/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="${basePath}/css/plugins/font-awesome/css/font-awesome.min.css" />
<!--[if IE 7]>
  <link rel="stylesheet" href="${basePath}/css/plugins/font-awesome/css/font-awesome-ie7.min.css" />
<![endif]-->
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link rel="stylesheet" type="text/css"
	href="${basePath}/theme/${session.theme}/main.css" />
<link type="text/css" rel="stylesheet" href="${basePath}/css/dtree.css" />
<style type="text/css">
.dropdown-menu ul {
	width: 507px;
	height: 252px;
	padding: 3px;
	overflow-x: hidden;
	overflow-y: auto;
}

.dropdown-menu ul li {
	padding: 3px;
	float: left;
	width: 20px;
	text-align: center;
	line-height: 20px;
}

.dropdown-menu ul li:hover {
	cursor: pointer;
	background: #f0f0f0;
}
.col-xs-4 {
    +width: 27%; /* ie7 */ 
    _width: 27%; /* ie6 */ 
}
</style>
<script type="text/javascript" src="${basePath}/js/dtree.js"></script>
<script language="javascript" src="${basePath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${basePath}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${basePath}/js/util.js"></script>
<script type="text/javascript">
	if ('${message}' != '') {
		parent.getLeftMenu();
	}
	$(function() {
		var icon = "glass.music.search.envelope-alt.heart.star.star-empty.user.film.th-large.th.th-list.ok.remove.zoom-in.zoom-out.off.signal.cog.trash.home.file-alt.time.road.download-alt.download.upload.inbox.play-circle.repeat.refresh.list-alt.lock.flag.headphones.volume-off.volume-down.volume-up.qrcode.barcode.tag.tags.book.bookmark.print.camera.font.bold.italic.text-height.text-width.align-left.align-center.align-right.align-justify.list.indent-left.indent-right.facetime-video.picture.pencil.map-marker.adjust.tint.edit.share.check.move.step-backward.fast-backward.backward.play.pause.stop.forward.fast-forward.step-forward.eject.chevron-left.chevron-right.plus-sign.minus-sign.remove-sign.ok-sign.question-sign.info-sign.screenshot.remove-circle.ok-circle.ban-circle.arrow-left.arrow-right.arrow-up.arrow-down.share-alt.resize-full.resize-small.plus.minus.asterisk.exclamation-sign.gift.leaf.fire.eye-open.eye-close.warning-sign.plane.calendar.random.comment.magnet.chevron-up.chevron-down.retweet.shopping-cart.folder-close.folder-open.resize-vertical.resize-horizontal.bar-chart.twitter-sign.facebook-sign.camera-retro.key.cogs.comments.thumbs-up-alt.thumbs-down-alt.star-half.heart-empty.signout.linkedin-sign.pushpin.external-link.signin.trophy.github-sign.upload-alt.lemon.phone.check-empty.bookmark-empty.phone-sign.twitter.facebook.github.unlock.credit-card.rss.hdd.bullhorn.bell.certificate.hand-right.hand-left.hand-up.hand-down.circle-arrow-left.circle-arrow-right.circle-arrow-up.circle-arrow-down.globe.wrench.tasks.filter.briefcase.fullscreen.group.link.cloud.beaker.cut.copy.paper-clip.save.sign-blank.reorder.list-ul.list-ol.strikethrough.underline.table.magic.truck.pinterest.pinterest-sign.google-plus-sign.google-plus.money.caret-down.caret-up.caret-left.caret-right.columns.sort.sort-down.sort-up.envelope.linkedin.undo.legal.dashboard.comment-alt.comments-alt.bolt.sitemap.umbrella.paste.lightbulb.exchange.cloud-download.cloud-upload.user-md.stethoscope.suitcase.bell-alt.coffee.food.file-text-alt.building.hospital.ambulance.medkit.fighter-jet.beer.h-sign.plus-sign-alt.double-angle-left.double-angle-right.double-angle-up.double-angle-down.angle-left.angle-right.angle-up.angle-down.desktop.laptop.tablet.mobile-phone.circle-blank.quote-left.quote-right.spinner.circle.reply.github-alt.folder-close-alt.folder-open-alt.expand-alt.collapse-alt.smile.frown.meh.gamepad.keyboard.flag-alt.flag-checkered.terminal.code.reply-all.mail-reply-all.star-half-empty.location-arrow.crop.code-fork.unlink.question.info.exclamation.superscript.subscript.eraser.puzzle-piece.microphone.microphone-off.shield.calendar-empty.fire-extinguisher.rocket.maxcdn.chevron-sign-left.chevron-sign-right.chevron-sign-up.chevron-sign-down.html5.css3.anchor.unlock-alt.bullseye.ellipsis-horizontal.ellipsis-vertical.rss-sign.play-sign.ticket.minus-sign-alt.check-minus.level-up.level-down.check-sign.edit-sign.external-link-sign.share-sign.compass.collapse.collapse-top.expand.eur.gbp.usd.inr.jpy.cny.krw.btc.file.file-text.sort-by-alphabet.sort-by-alphabet-alt.sort-by-attributes.sort-by-attributes-alt.sort-by-order.sort-by-order-alt.thumbs-up.thumbs-down.youtube-sign.youtube.xing.xing-sign.youtube-play.dropbox.stackexchange.instagram.flickr.adn.bitbucket.bitbucket-sign.tumblr.tumblr-sign.long-arrow-down.long-arrow-up.long-arrow-left.long-arrow-right.apple.windows.android.linux.dribbble.skype.foursquare.trello.female.male.gittip.sun.moon.archive.bug.vk.weibo.renren"
				.split(".");
		var html = "";
		for ( var i = 0; i < icon.length; i++) {
			html += "<li><i class='icon-" + icon[i]  + "'></i></li>";
		}
		$(".dropdown-menu ul").html(html);
		$("body").on("click", ".dropdown-menu ul li", function() {
			var n = $(this).find("i").attr("class");
			$(".btn.dropdown-toggle i").attr("class", n);
			$("input[name='module.image']").val(n);
		});
	});
	if ("${result}" == "SUCCESS")
		alert("操作成功");
	function check() {
		if (get("module.name").value == "") {
			alert("模块名称不能为空");
			get("module.name").focus();
			return false;
		}
		return true;
	}
	function put(i) {
		$("#module_info").attr("param",i);
		get("module.id").value = dtree[i][0];
		get("module.parent.id").value = dtree[i][1];
		get("module.name").value = dtree[i][2];
		get("module.url").value = dtree[i][3];
		get("module.functions").value = dtree[i][4];
		get("module.priority").value = dtree[i][5];
		var display = document.getElementsByName("module.display");
		for ( var j = 0; j < display.length; j++) {
			if (display[j].value == dtree[i][6]) {
				display[j].checked = true;
			}
		}
		get("module.level").value = dtree[i][7];
		get("module.remark").value = dtree[i][8];
		get("module.image").value = dtree[i][11];
		$(".btn.dropdown-toggle i").attr("class", dtree[i][11]);
		var arr = dtree[i][10].substring(1, dtree[i][10].length - 1)
				.split(", ");

	}
	function add() {
		if (check()){
            get("myForm").action = "module_save.do";
            get("myForm").submit();
        }
	}
	function update() {
		if (get("module.id").value == "") {
			alert("请先选择要修改的信息");
			return;
		}
		if (!check())
			return;
		get("myForm").action = "module_update.do";
		get("myForm").submit(); 
	}
	function del() {
		var message = "";
		if (get("module.id").value == "") {
			message = "请先选择要删除的信息";
		}
		else{
			var i = $("#module_info").attr("param");
			if($("#dm"+i).find(".dTreeNode").size()>0){
				message = "该目录下还存在其他子模块，删除失败！";
			}
		}
		if(message){
			alert(message);
		}
		else{
			if (confirm("确定要删除信息吗？")) {
				get("myForm").action = "module_delete.do";
				get("myForm").submit();
			}
		}
	}
</script>
</head>
<body>
	<form id="myForm" method="post" action="role_update.do">
		<ul class="breadcrumb">
			<li><i class="icon-home icon-2x"></i></li>
			<li>当前位置：${currentPosition}</li>
		</ul>
		<div class="widget widget-edit">
			<div class="widget-content">
				<div class="col-xs-4">
					<div class="dtree">
						<script type="text/javascript">
							var dtree = new Array();
							var i = 1;
							<c:forEach items="${list}" var="module">
							dtree[i] = new Array();
							dtree[i][0] = "${module.id}";
							dtree[i][1] = "${module.parent==null?0:module.parent.id}";
							dtree[i][2] = "${module.name}";
							dtree[i][3] = "${module.url}";
							dtree[i][4] = "${module.functions}";
							dtree[i][5] = "${module.priority}";
							dtree[i][6] = "${module.display}";
							dtree[i][7] = "${module.level}";
							dtree[i][8] = "${module.remark}";
							dtree[i][9] = "javascript:put(" + i + ");";
							dtree[i][10] = "${module.subsystems}";
							dtree[i][11] = "${module.image}";
							i++;
							</c:forEach>

							m = new dTree('m');
							m.add(0, -1, "根目录");
							for ( var i = 1; i < dtree.length; i++) {
								m.add(dtree[i][0], dtree[i][1], dtree[i][2],
										dtree[i][9]);
							}
							m.config.closeSameLevel = true;
							document.write(m);
							$(".nodeSel").attr("class","node");
						</script>

					</div>
				</div>
				<div class="col-xs-8">
					<table id="module_info" class="pn-ftable table-bordered" border="0" cellpadding="10" param="0">
						<tbody>
							<tr>
								<th>上级模块</th>
								<td class="pn-fcontent"><select name="module.parent.id">
										<option value="0" selected>根目录</option>
										<c:forEach items="${list}" var="item">
											<option value="${item.id}" level="${item.level}">${fn:substring("││││││││││",0,item.level-1)}├${item.name}</option>
										</c:forEach>
								</select></td>
								<td class="pn-info"></td>
							</tr>
							<tr>
								<th><span class="point">*</span>模块名称</th>
								<td class="pn-fcontent"><input type="text"
									name="module.name" maxlength="10" /></td>
								<td class="pn-info"></td>
							</tr>
							<tr>
								<th>首页地址</th>
								<td class="pn-fcontent"><input type="text"
									name="module.url" size="50" maxlength="200" /></td>
								<td class="pn-info">例：/admin/module_edit.do</td>
							</tr>
							<tr>
								<th>功能集合</th>
								<td class="pn-fcontent"><textarea name="module.functions"
										rows="5" cols="50"></textarea></td>
								<td class="pn-info">使用分号";"隔开</td>
							</tr>
							<input type="hidden" name="module.subsystems" value="" />
							<tr>
								<th>图&nbsp;&nbsp;&nbsp;&nbsp;标</th>
								<td class="pn-fcontent">
									<div class="btn-group dropup">
										<a class="btn dropdown-toggle" data-toggle="dropdown"> <span>&nbsp;<i
												class=""></i>&nbsp;
										</span>
										</a>
										<div class="dropdown-menu">
											<ul></ul>
										</div>
									</div> <input type="hidden" name="module.image" value="" />
								</td>
								<td class="pn-info">一级菜单设置图标才会显示</td>
							</tr>
							<tr>
								<th>排&nbsp;&nbsp;&nbsp;&nbsp;序</th>
								<td class="pn-fcontent"><input type="text"
									name="module.priority" value="10"  maxlength="4"  onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" /></td>
								<td class="pn-info"></td>
							</tr>
							<tr>
								<th>是否隐藏</th>
								<td class="pn-fcontent"><label class="inline"> <input
										type="radio" name="module.display" value="1" checked />否
								</label> <label class="inline"> <input type="radio"
										name="module.display" value="0" />是
								</label></td>
								<td class="pn-info"></td>
							</tr>
							<tr>
								<th>备&nbsp;&nbsp;&nbsp;&nbsp;注</th>
								<td class="pn-fcontent"><input type="text"
									name="module.remark" size="50"  maxlength="50" /></td>
								<td class="pn-info"></td>
							</tr>
						</tbody>
					</table>
					
				</div>
			</div>
			<!-- /widget-content -->
			<div class="widget-bottom">
						<input type="hidden" name="module.id" />
						<input type="hidden"name="module.level" />
						<a href="javascript:update();" class="btn btn-primary pull-right">保存</a> 
						<a href="javascript:del();" class="btn btn-danger pull-right">删除</a>
						<a href="javascript:add();" class="btn  pull-right">添 加</a> 
					</div>
		</div>
	</form>
</body>
</html>