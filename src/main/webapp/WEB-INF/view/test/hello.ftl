<div class="row">
	<div class="devicedetail-container">
		<div id="allmap" class="map right"></div>
	</div>
</div>
<script type="text/javascript" src="/static/js/devicedetail.js"></script>
<script type="text/javascript">
	$(".map").css("height",$(window).height()-$('.page-content').offset().top+"px");
	$(".left").css("height",$(window).height()-$('.page-content').offset().top+"px");
	var map = new BMap.Map("allmap"); 
	// 百度地图API功能
	
	$(function(){
		   // 创建Map实例
		map.centerAndZoom(new BMap.Point(116.404, 39.915), 7);  // 初始化地图,设置中心点坐标和地图级别
	//	map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
		map.setCurrentCity("安徽省");          // 设置地图显示的城市 此项是必须设置的
		map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
		
	//	locateMyPosition();
	//	getDistance();
		getBoundary("安徽省","#0000ff", 0.3);
		getBoundary("河南省","#00ff00", 0.3);
		getBoundary("江苏省","#ff0000", 0.3);
		
		$(".detail-list li").click(function(){
			addPointMarker($(this).attr("lat"),$(this).attr("lng"));
		});
	});
	function addPointMarker(lat, lng) {
		var marker = new BMap.Marker(new BMap.Point(lng, lat)); // 创建点
		map.addOverlay(marker);
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
</script>