<div class="row">
	<div class="devicedetail-container">
		<div class="left">
			<div style="position:absolute;top:0;left:0;z-index:100;width:100%;">
				<div class="sidebar-collapse" style="border-bottom:none">
					<i class="icon-double-angle-right  detail-slide" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
				</div>
			</div>
			
			<ul class="detail-list">
				<#if devices??>
				<#list devices as device>
				<li lat="${device.lat}" deviceType="${device.deviceType}" lng="${device.lng}" imei="${device.imei}">
					<div class="widget-box">
						<div class="widget-header widget-header-flat">
							<h6>${device.name}</h6>
						</div>
						<div class="widget-body">
							<div class="widget-main">
								<div class="row" style="margin:0">
									编号:${device.imei}
								</div>
								<div class="row">
									<div class="col-xs-8" style="line-height:40px;">状态:${device.statusDesc?string}</div>
									<div class="col-xs-4">
										<div class="checkbox">
											<label>
												<input name="form-field-checkbox" type="checkbox" checked="checked" class="ace devicecb" />
												<span class="lbl"></span>
											</label>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</li>
				</#list>
				</#if>
			</ul>
		</div>
		
		<div id="allmap" class="map right"></div>
		
	</div>
</div>
<script type="text/javascript" src="/static/js/devicedetail.js"></script>
<script type="text/javascript" src="/static/js/map.js?v=20170707"></script>
<script type="text/javascript">
	$(".detail-list").css("height",$(window).height()-$('.page-content').offset().top+"px");
	var infoWindow = "<div><p><h6></h6></p><p></p><p><input class='gothis' lat='' lng='' type='button' onclick='gps(this)' value='导航'/></div>"
	$(function(){
		initMap('${area.name}', 12);
		navigator.geolocation.getCurrentPosition(function (position) {  
		    longitude = position.coords.longitude;  
		    latitude = position.coords.latitude;
		}); 
		getLocation();
		
		devicecbEvent(); // 默认xuan'zhon
		$(".devicecb").change(function(){
			devicecbEvent(this);
		});
		
		
	});
	
	function devicecbEvent() {
		map.clearOverlays();
			if(gpsInterval)
				clearInterval(gpsInterval);
			$(".devicecb").each(function(){
				if($(this).prop("checked")) {
					var li = $(this).closest("li");
					var $infoWindow = $(infoWindow);
					$infoWindow.find("p:eq(0)").html(li.attr("imei"));
					$infoWindow.find(".gothis").attr("lat",li.attr("lat")).attr("lng",li.attr("lng"));
					new BMap.Geocoder().getLocation(new BMap.Point(li.attr("lng"),li.attr("lat")), function(rs){
						var addComp = rs.addressComponents;
						$infoWindow.find("p:eq(1)").html(addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber);
						addPointMarker(li.attr("deviceType"),li.attr("lat"),li.attr("lng"), $infoWindow.html());
					});
				}
			});
	}
	
</script>