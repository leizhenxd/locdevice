Date.prototype.format = function(format)
{
 var o = {
 "M+" : this.getMonth()+1, //month
 "d+" : this.getDate(),    //day
 "h+" : this.getHours(),   //hour
 "m+" : this.getMinutes(), //minute
 "s+" : this.getSeconds(), //second
 "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
 "S" : this.getMilliseconds() //millisecond
 }
 if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
 (this.getFullYear()+"").substr(4 - RegExp.$1.length));
 for(var k in o)if(new RegExp("("+ k +")").test(format))
 format = format.replace(RegExp.$1,
 RegExp.$1.length==1 ? o[k] :
 ("00"+ o[k]).substr((""+ o[k]).length));
 return format;
}

var ua = navigator.userAgent.toLocaleLowerCase();
var pf = navigator.platform.toLocaleLowerCase();
var isAndroid = (/android/i).test(ua)||((/iPhone|iPod|iPad/i).test(ua) && (/linux/i).test(pf))
    || (/ucweb.*linux/i.test(ua));
var isIOS =(/iPhone|iPod|iPad/i).test(ua) && !isAndroid;
var isWinPhone = (/Windows Phone|ZuneWP7/i).test(ua);

var mobileType = {
    pc:!isAndroid && !isIOS && !isWinPhone,
    ios:isIOS,
    android:isAndroid,
    winPhone:isWinPhone
};

