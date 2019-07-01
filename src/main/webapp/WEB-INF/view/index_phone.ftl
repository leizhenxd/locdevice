<!DOCTYPE html>
<html lang="en" style="height:100%;overflow:hidden;">
	<head>
		<meta charset="utf-8" />
		<title>消防设备定位系统</title>
		<link rel="shortcut icon" href="favicon.ico"/>
		<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
		<!-- basic styles -->
		
		<link href="/static/css/ace/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="/static/css/ace/font-awesome.min.css" />

		<!--[if IE 7]>
		  <link rel="stylesheet" href="/static/css/ace/font-awesome-ie7.min.css" />
		<![endif]-->

		<!-- page specific plugin styles -->

		<link rel="stylesheet" href="/static/css/ace/jquery-ui-1.10.3.full.min.css" />
		<link rel="stylesheet" href="/static/css/ace/datepicker.css" />
		<link rel="stylesheet" href="/static/css/ace/ui.jqgrid.css" />

		<!-- ace styles -->

		<link rel="stylesheet" href="/static/css/ace/ace.min.css" />
		<link rel="stylesheet" href="/static/css/ace/ace-rtl.min.css" />
		<link rel="stylesheet" href="/static/css/ace/ace-skins.min.css" />

		<!--[if lte IE 8]>
		  <link rel="stylesheet" href="/static/css/ace/ace-ie.min.css" />
		<![endif]-->
		<link href="/static/css/global.css" rel="stylesheet" />
		<!-- inline styles related to this page -->

		<!-- ace settings handler -->

		<script src="/static/js/ace/ace-extra.min.js"></script>

		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

		<!--[if lt IE 9]>
		<script src="/static/js/ace/html5shiv.js"></script>
		<script src="/static/js/ace/respond.min.js"></script>
		<![endif]-->
		
		<!-- basic scripts -->


		<!--[if !IE]> -->

		<script type="text/javascript">
			window.jQuery || document.write("<script src='/static/js/ace/jquery-2.0.3.min.js'>"+"<"+"/script>");
		</script>

		<!-- <![endif]-->

		<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='/static/js/ace/jquery-1.10.2.min.js'>"+"<"+"/script>");
</script>
<![endif]-->
		<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
		<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='/static/js/ace/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="/static/js/ace/bootstrap.min.js"></script>
		<script src="/static/js/ace/typeahead-bs2.min.js"></script>

		<!-- page specific plugin scripts -->

		<script src="/static/js/ace/bootstrap-datepicker.min.js"></script>
		<script src="/static/js/ace/jquery.jqGrid.min.js"></script>
		<script src="/static/js/ace/grid.locale-en.js"></script>
		<script src="/static/js/index.js"></script>

		<!-- ace scripts -->

		<script src="/static/js/ace/ace-elements.min.js"></script>
		<script src="/static/js/ace/ace.min.js"></script>
		<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=q6oYaI1yk8GHbigag4NQIs4M"></script>
		<script type="text/javascript" src="/static/js/map.js?v=2018020702"></script>
		
		<link rel="stylesheet" href="/static/css/devicedetail.css" />
		<style>
			.phonenav {background-color:black;width:100%;height:50px;margin:0px;overflow:hidden;font-size:1.3em;}
			.wrapper {position:relative;background-color:black;color:white;text-align:center;height:50px;line-height:50px;margin:0px 10px 0px 10px;overflow:hidden;}
			.activebg {
				width:33.33%;
				height:30px;
				margin-top:10px;
				background-color: red;
				position: absolute;
				left:0%;
				border-radius:20px;
				-moz-border-radius:30%; /* 老的 Firefox */
			}
			body, html {width: 100%;height: 100%;margin:0;font-family:"微软雅黑";}
			#allmap{width:100%;height:500px;}
			p{margin-left:5px; font-size:14px;}
		</style>
		<script>
			function getFormData($form){
			    var unindexed_array = $form.serializeArray();
			    var indexed_array = {};
			
			    $.map(unindexed_array, function(n, i){
			        indexed_array[n['name']] = n['value'];
			    });
			
			    return indexed_array;
			}
		</script>
	
	</head>

	<body style="height:100%">
		<style>
			.BMap_pop>div:nth-child(1) {
				border-top-left-radius:50%;
			}
			.BMap_pop>div:nth-child(3) {
				border-top-right-radius:50%;
			}
			.BMap_pop>div:nth-child(5) {
				border-bottom-left-radius:50%;
			}
			.BMap_pop>div:nth-child(7) {
				border-bottom-right-radius:50%;
			}
		</style>
		<div class="rtl">
		<div class="ace-settings-container" style="top:85px;left:0px;">
			<div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
				<a href="home.htm"><i class="icon-cog bigger-80"></i></a>
			</div>
		</div><!-- /#ace-settings-container -->
		</div>
		
		<div class="row phonenav">
			<div class="row wrapper">
				<div class="activebg"></div>
				<div class="button col-xs-4" deviceType="1">消防栓</div>
				<div class="button col-xs-4" deviceType="2">水泵接合器</div>
				<div class="button col-xs-4" deviceType="3">重点单位</div>
			</div>
		</div>
		<div class="page-content" style="height:100%;padding:0px;">
			<div id="allmap" class="map"></div>
		</div>
		<script>
			$(function(){
				getLocation();
				var geolocation = new BMap.Geolocation();
				geolocation.getCurrentPosition(function(r){
					if(this.getStatus() == BMAP_STATUS_SUCCESS){
						initMapWithPoint(r.latitude,r.longitude, 15);
						navigator.geolocation.getCurrentPosition(function (position) {  
							console.log(position);
						    longitude = position.coords.longitude;  
						    latitude = position.coords.latitude;
						}); 
						$(".button").each(function(a){
							$(this).click(function(){
								$(".wrapper button").removeClass("active");
								$(this).addClass("active");
								$(".activebg").animate({left:33.33*a+"%"}, 200);
								var deviceType = $(this).attr("deviceType");
								$.ajax({
					  				url: "device/query.htm?devicescb="+deviceType,
					  				type: 'post',
					  				dataType: 'json',
					  				data:{'city':r.address.city},
					  				success: function(data){
					  					console.log(data);
					  					addPoints(data)
					  				}
					  			});
							});
						});
						setTimeout(function(){
							$(".button:eq(0)").trigger("click");
						}, 400);
					}
					else {
						alert('failed'+this.getStatus());
					}        
				},{enableHighAccuracy: true})
			});
			function addPoints(data) {
				map.clearOverlays();
				for(var i=0;i<data.length;i++){
					var infoWindow = "<div style='width:100px;font-size:0.7em'><p><h6></h6></p><p></p><p><input class='gothis"+i+"' lat='' lng='' deviceType='' type='button' onclick='gps(this)' value='导航'/></div>"
					var $infoWindow = $(infoWindow);
					$infoWindow.find("p:eq(0)").html("IMEI: "+data[i].imei);
					$infoWindow.find(".gothis"+i).attr("lat",data[i].lat).attr("lng",data[i].lng).attr("deviceType",data[i].deviceType);
					console.log($infoWindow.html());
					addInfoWindow($infoWindow, data[i]);
				}
			}
			function addInfoWindow(infoWindow, data) {
				var deviceType = data.deviceType,lng=data.lng,lat=data.lat;
				new BMap.Geocoder().getLocation(new BMap.Point(lng,lat), function(rs){
					
					var addComp = rs.addressComponents;
					infoWindow.find("p:eq(1)").html("地址: "+addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber);
					console.log(infoWindow.html());
					addPointMarker(deviceType,rs.point.lat,rs.point.lng, infoWindow.html());
				});
			}
		</script>
	</body>
</html>