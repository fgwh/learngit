/**
 * Created by linlin on 2015/03/06/0006.
 */

$(".foldingbar").live("click", function () {//折叠块图标变化
    var chenvar = $(this).find('div');
    chenvar.hasClass('icon-chevron-right') ? chenvar.attr("class", "arrow icon-chevron-down") : chenvar.attr("class", "arrow icon-chevron-right");
});

$("#closeWin,#colsewindow").live("click",function(){//关闭右边整个聊天窗口
    $("#mainContain").css("display","none");
});

$("button[name =singleOrGroupTab]").live("click", function () {//单人,多人聊天切换
        $("button[name = singleOrGroupTab]").removeClass("active");
        $(this).addClass("active");
        $("#mainContain,#menberList").css("display","none");//隐藏聊天框
        if($(this).attr("id") == "displaySingle"){
            $("#userList").css("display","block");
            $("#groupList").css("display","none");
            $("#audioChat").show();
        }else{
            $("#userList").css("display","none");
            $("#groupList").css("display","block");
            $("#audioChat").hide();
        }
    }
);

/*
$("a.icon-remove").live("click",function(){//清除搜索框
    $(".searchUser").attr("value","");
});
*/

$("li[name=user]").live("click",function(){//点击列表显示聊天界面
    $("#mainContain").css("display","block");
    $("#console-container,#moreInfo").css("width","100%");
});

$("a[name=group]").live("click",function(){//点击列表显示聊天界面
    $("#mainContain,#menberList").css("display","block");
    $("#console-container,#moreInfo").css("width","80%");
});





