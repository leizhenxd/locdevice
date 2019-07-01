$(function(){
	$(".detail-slide").click(function(){
		$(this).toggleClass("icon-double-angle-left icon-double-angle-right");
		$(".detail-list").toggleClass("hide");
		if($(this).hasClass("icon-double-angle-left")) {
			$(this).closest(".left").width(30);
		}
		else {
			$(this).closest(".left").width(200);
		}
	});
	
	var nScrollHight = 0; //滚动距离总长(注意不是滚动条的长度)
	var nScrollTop = 0;   //滚动到的当前位置
	var clientHeight = 0;
	$(".detail-list").scroll(function(){
		console.log($(this));
		clientHeight = parseInt($(this)[0].clientHeight); //div窗口高度
		nScrollHight = parseInt($(this)[0].scrollHeight); // 最大高度
		nScrollTop = parseInt($(this)[0].scrollTop);//滚动高度
		
		console.log($(this)[0].clientHeight+"-" + $(this)[0].scrollTop+"-" + $(this)[0].scrollHeight);
		console.log(clientHeight+"-" + nScrollTop+"-" + nScrollHight);
		if(clientHeight+nScrollTop>=nScrollHight)
			console.log("滚动条到底部了");
	});
});