
var map = null;

function initMapWithPoint(lat,lng,scale) {
	if($(".map"))
		$(".map").css("height",$(window).height()-$('.page-content').offset().top+"px");
	map = new BMap.Map("allmap");
	// 地图 混合地图
	var mapTypeControl = new BMap.MapTypeControl({
		mapTypes:[
	        BMAP_NORMAL_MAP,
	        BMAP_HYBRID_MAP
	    ]
	});
	mapTypeControl.setOffset(new BMap.Size($(".map").width()-100, 10));
	map.addControl(mapTypeControl);
	var top_right_navigation = new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_RIGHT, type: BMAP_NAVIGATION_CONTROL_SMALL});
	map.addControl(top_right_navigation);

	var geolocationControl = new BMap.GeolocationControl();
	map.addControl(geolocationControl);
	
	map.centerAndZoom(new BMap.Point(lng, lat), scale);
	map.enableAutoResize();
	map.enableScrollWheelZoom(true);
	map.panTo(new BMap.Point(lng,lat));
}
function initMap(city,scale) {
	if($(".map"))
		$(".map").css("height",$(window).height()-$('.page-content').offset().top+"px");
	if(map == null)
		map = new BMap.Map("allmap");
	// 地图 混合地图
	var mapTypeControl = new BMap.MapTypeControl({
		mapTypes:[
	        BMAP_NORMAL_MAP,
	        BMAP_HYBRID_MAP
	    ]
	});
	mapTypeControl.setOffset(new BMap.Size($(".map").width()-100, 10));
	map.addControl(mapTypeControl);
	// 全景图控件
//	map.addTileLayer(new BMap.PanoramaCoverageLayer());
//	var stCtrl = new BMap.PanoramaControl(); //构造全景控件
//	map.addControl(stCtrl);//添加全景控件
//	
	var geolocationControl = new BMap.GeolocationControl();
	map.addControl(geolocationControl);
	map.centerAndZoom(city, scale);
	map.enableAutoResize();
	map.enableScrollWheelZoom(true);
}

function addPointMarker(deviceType,lat, lng, infoWindow, callback) {
	var myIcon = new BMap.Icon("static/images/icon/25/device"+deviceType+".png", new BMap.Size(26,27));
	var newPoint = new BMap.Point(lng, lat);
	var marker = new BMap.Marker(newPoint,{icon:myIcon}); // 创建点
	map.addOverlay(marker);
	
	marker.addEventListener("click",function(){
		map.openInfoWindow(new BMap.InfoWindow(infoWindow,{width:100,height:100}), newPoint);
	//	map.setZoom(15);
	//	map.panTo(newPoint);
		
		if(this.circleShape == null)
			marker = addValidDistanceShape(marker,newPoint, 500);
		else {
			map.removeOverlay(marker.circleShape);
			marker.circleShape = null;
		}
	});
	if(callback)
		callback(marker, newPoint);
}

// 画区
function getBoundary(cityName, color, opacity){       
	var bdary = new BMap.Boundary();
	bdary.get(cityName, function(rs){       //获取行政区域
	//	map.clearOverlays();        //清除地图覆盖物       
		var count = rs.boundaries.length; //行政区域的点有多少个
		if (count === 0) {
			alert('未能获取当前输入行政区域');
			return ;
		}
      	var pointArray = [];
		for (var i = 0; i < count; i++) {
			var ply = new BMap.Polygon(rs.boundaries[i], {strokeWeight: 2, strokeColor: color||"#ffff00",fillColor:color||"#ffff00",fillOpacity:opacity||0.2}); //建立多边形覆盖物
			map.addOverlay(ply);  //添加覆盖物
			pointArray = pointArray.concat(ply.getPath());
		}    
	//	map.setViewport(pointArray);    //调整视野  
	});   
}

function locateMyPosition() {
	var myCity = new BMap.LocalCity();
	myCity.get(function myFun(result){
		var cityName = result.name;
		map.setCenter(cityName);
	});
}

function getDistance() {
	var output = "从上地到西单驾车需要";
	var searchComplete = function (results){
		if (transit.getStatus() != BMAP_STATUS_SUCCESS){
			return ;
		}
		var plan = results.getPlan(0);
		output += plan.getDuration(true) + "\n";                //获取时间
		output += "总路程为：" ;
		output += plan.getDistance(true) + "\n";             //获取距离
	}
	var transit = new BMap.DrivingRoute(map, {renderOptions: {map: map},
		onSearchComplete: searchComplete,
		onPolylinesSet: function(){        
			//	setTimeout(function(){alert(output)},"1000");
		}});
	transit.search("川沙地铁站", "华夏东路地铁站");
}
function gps(aim) {
//	if($(aim).attr("deviceType") == 1) {
	if(true) {
		window.location.href="http://api.map.baidu.com/direction?origin=latlng:"+currentLocation.lat+","+currentLocation.lng+"|name:我的位置&destination=latlng:"+$(aim).attr("lat")+","+$(aim).attr("lng")+"|name:目的地&mode=driving&region=广州&output=html&src=yourCompanyName|yourAppName";
		return;
	}
	var output = "从上地到西单驾车需要";
	var transit;
	var searchComplete = function (results){
		var myIcon = new BMap.Icon("static/images/dirt.png", new BMap.Size(30, 30), {    //小车图片
			imageOffset: new BMap.Size(0, 0)    //图片的偏移量。为了是图片底部中心对准坐标点。
		  });
		
		var carMk = null;
		function resetMkPoint(){
			if(currentLocation){
				if(!carMk) {
					carMk = new BMap.Marker(currentLocation,{icon:myIcon});
					map.addOverlay(carMk);
				}
				carMk.setPosition(currentLocation);
		//		map.panTo(currentLocation);
				if(directionAngle != null)
					carMk.setRotation(directionAngle);
			}
		}
		setTimeout(function(){
			map.setZoom(15);
			map.panTo(currentLocation);
		}, 400);
		gpsInterval = setInterval(function(){
			resetMkPoint();
		},200);
	}
	transit = new BMap.DrivingRoute(map, {renderOptions: {map: map},
		onSearchComplete: searchComplete,
		onPolylinesSet: function(){        
			//	setTimeout(function(){alert(output)},"1000");
	}});
	setTimeout(function(){
		transit.search(currentLocation, new BMap.Point($(aim).attr("lng"),$(aim).attr("lat")));
	},400);
}
var gpsInterval;
var currentLocation = null;
function getLocation() {
	var point = {};
	var geolocation = new BMap.Geolocation();
	geolocation.getCurrentPosition(function(r){
		if(this.getStatus() == BMAP_STATUS_SUCCESS){
			console.log(r);
			currentLocation = r.point;
			setTimeout(function(){
				getLocation();
			},5000);
		}
		else {
			alert('failed'+this.getStatus());
		}        
	},{enableHighAccuracy: true})
	//关于状态码
	//BMAP_STATUS_SUCCESS	检索成功。对应数值“0”。
	//BMAP_STATUS_CITY_LIST	城市列表。对应数值“1”。
	//BMAP_STATUS_UNKNOWN_LOCATION	位置结果未知。对应数值“2”。
	//BMAP_STATUS_UNKNOWN_ROUTE	导航结果未知。对应数值“3”。
	//BMAP_STATUS_INVALID_KEY	非法密钥。对应数值“4”。
	//BMAP_STATUS_INVALID_REQUEST	非法请求。对应数值“5”。
	//BMAP_STATUS_PERMISSION_DENIED	没有权限。对应数值“6”。(自 1.1 新增)
	//BMAP_STATUS_SERVICE_UNAVAILABLE	服务不可用。对应数值“7”。(自 1.1 新增)
	//BMAP_STATUS_TIMEOUT	超时。对应数值“8”。(自 1.1 新增)
}

function addValidDistanceShape(marker,mPoint, distance) {
	var circle = new BMap.Circle(mPoint,distance,{fillColor:"blue", strokeWeight: 1 ,fillOpacity: 0.3, strokeOpacity: 0.3});
    map.addOverlay(circle);
    marker.circleShape = circle;
    return marker;
}
function removeValidDistanceShape(mPoint, distance) {
	var circle = new BMap.Circle(mPoint,distance,{fillColor:"blue", strokeWeight: 1 ,fillOpacity: 0.3, strokeOpacity: 0.3});
    map.addOverlay(circle);
}

var directionAngle;
!function getDirectionAngle() {
	console.log(gpsInterval);
	if(gpsInterval)
		clearInterval(gpsInterval);
	if(window.DeviceOrientationEvent){
	    window.addEventListener('deviceorientation',function(event) {
	    	directionAngle = event.webkitCompassHeading;
	    	console.log(directionAngle);
	    },false);
	}
	else {
		directionAngle = null;
	}
}();