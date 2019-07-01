<div class="form">
	<input type="text" name="phone" value=""/>
	<input type="text" name="userName"/>
	<input type="button" value="submit" class="submit"/>
</div>
<div class="row">
	<table id="grid-table"></table>
	<div id="grid-pager"></div>
</div>

<div class="modal fade" id="updateStatusModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<form class="form-horizontal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					状态录入
				</h4>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<div class="col-xs-2"><input type="hidden" name="id"/>状态:</div>
					<div class="col-xs-8">
						<select name = "status">
							<option value="0">未激活</option>
							<option value="1">离线</option>
							<option value="2">在线</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-xs-2">维护人:</div>
					<div class="col-xs-8"><input type="text" name="updateUser"/></div>
				</div>
				<div class="form-group">
					<div class="col-xs-2">维护时间:</div>
					<div class="col-xs-8"><input type="text" class="datepicker" name="updateTime" size="30"></div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭
				</button>
				<button type="button" class="btn btn-primary updateStatusBtn">
					提交更改
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div>
	</form>
</div><!-- /.modal -->

<div class="modal fade" id="showPositionModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog" style="width:100%">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					查看位置
				</h4>
			</div>
			<div class="modal-body">
				<div id="allmap" class="right" style="width:100%;height:400px;"></div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div>
</div><!-- /.modal -->
<div class="modal fade" id="showStatisticModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog" style="width:70%">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					查看报表
				</h4>
			</div>
			<div class="modal-body form-horizontal">
				<div class="form-group">
					<div class="col-xs-2">维护次数:</div>
					<div class="col-xs-8" id="maintanTimes"></div>
				</div>
				<div class="form-group">
					<div class="col-xs-2">出警次数:</div>
					<div class="col-xs-8" id="patrolTimes"></div>
				</div>
				<div class="form-group">
					<div class="col-xs-2">次数:</div>
					<div class="col-xs-8" id="errorTimes"></div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div>
</div><!-- /.modal -->
<script type="text/javascript" src="/static/js/map.js?v=20170707"></script>
<script src="/static/js/devicelist.js"></script>
<script>
		
		jQuery(function($) {
			$(".datepicker" ).datepicker({
				language: "zh-CN",
				autoclose: true,//选中之后自动隐藏日期选择框
	            clearBtn: true,//清除按钮
	            todayBtn: true,//今日按钮
				format: "yyyy-mm-dd"
			});
			
			var grid_selector = "#grid-table";
			var pager_selector = "#grid-pager";
		
			jQuery("#grid-table").jqGrid({
				//direction: "rtl",
				url: "device/getAll.htm",
				postData: getFormData($(".form input")),
				rows: 1,
				datatype: "json",
				height: 250,
				colNames:['','','ID', '设备名称','IMEI','设备类型','状态','区域名称','省','维护人','维护时间','操作'],
				colModel:[
					{name:'lat',index:'lat',width:44, sorttype:"string", editable: false,hidden:true},
					{name:'lng',index:'lng',width:44, sorttype:"string", editable: false,hidden:true},
					{name:'id',index:'id',width:30, sorttype:"int", editable: false,editoptions:{readonly:true,size:20}},
					{name:'name',index:'name',width:44, sorttype:"string", editable: true},
					{name:'imei',index:'imei',width:75, sorttype:"string", editable: true},
					{name:'deviceType',index:'deviceType',width:44, sorttype:"int", editable: true, edittype:"select",formatter:'select',editoptions:{value:"1:消防栓;2:水泵接合器;3:重点单位"}},
					{name:'status',index:'status',width:30, sorttype:"int", editable: true, edittype:"select",formatter:'select',editoptions:{value:"0:未激活;1:离线;2:在线"}},
					{name:'areaName',index:'areaName',width:35, editable: true},
					{name:'province',index:'province',width:30, sorttype:"string", editable: true},
					{name:'updateUser',index:'updateUser',width:40, sorttype:"string", editable: true},
					{name:'updateTime',index:'updateTime',width:80, sorttype:"string", editable: true,
						formatter : function(value, grid, rows, state) {
							return new Date(value).format("yyyy-MM-dd");
						}
					},
					{name: 'myac', index: '',width:300, sortable: false,
						formatter: function (value, grid, rows, state) {
							
							return "<a href='#' id='"+rows.id+"' updateUser='"+rows.updateUser+"' status='"+rows.status+"' updateTime='"+rows.updateTime+"' onclick='updateStatus(this)'>状态录入</a> " +
								   "<a href='#' onclick='showPosition("+rows.imei+","+rows.deviceType+","+rows.lat+","+rows.lng+")'>查看位置</a>  "+
								   "<a href='#' onclick='showStatistic("+rows.imei+")'>查看报表</a>  "+
								   "<a href='#' onclick='showSafeDistance("+rows.imei+","+rows.deviceType+","+rows.lat+","+rows.lng+")'>查看安全区域</a>";
						}
					}
				], 
				jsonReader : { 
			      root: "rows", 
			      page: "pageHH", 
			      total: "total", 
			      records: "records", 
			      repeatitems: true, 
			      cell: "cell", 
			      id: "id"
			   },
				viewrecords : true,
				rowNum:10,
				rowList:[10,20,30],
				pager : pager_selector,
				altRows: true,
				//toppager: true,
				
				multiselect: true,
				//multikey: "ctrlKey",
		        multiboxonly: true,
		
				loadComplete : function() {
					
				},
				caption: "设备",
				autowidth: true
		
			});
		
			//navButtons
			
			var navs = jQuery(grid_selector).jqGrid('navGrid',pager_selector,
				{ 	//navbar options
					edit: false,
					editicon : 'icon-pencil blue',
					add: false,
					addicon : 'icon-plus-sign purple',
					del: false,
					delicon : 'icon-trash red',
					search: false,
					searchicon : 'icon-search orange',
					refresh: false,
					refreshicon : 'icon-refresh green',
					view: false,
					viewicon : 'icon-zoom-in grey',
					addfunc : function(e) {
						jQuery(grid_selector).jqGrid('editGridRow',"new",{height:'auto',reloadAfterSubmit:false});
					},
					editfunc : function(e) {
						var gr = jQuery(grid_selector).jqGrid('getGridParam','selrow');
						if( gr != null ) jQuery(grid_selector).jqGrid('editGridRow',gr,{height:'auto',reloadAfterSubmit:false});
						else alert("Please Select Row");
					},
					delfunc : function(e) {
						var gr = jQuery(grid_selector).jqGrid('getGridParam','selrow');
						if( gr != null ) jQuery(grid_selector).jqGrid('delGridRow',gr,{height:'auto',reloadAfterSubmit:false});
						else alert("Please Select Row");
					},
				},
				{},
				{},
				{},
				{},
				{}
			);
		
			navs.navButtonAdd(pager_selector, {  
               caption: "添加设备",  
               title:"添加设备",
               buttonicon: "icon-plus-sign purple",  
               onClickButton: function () {  
                   jQuery(grid_selector).jqGrid('editGridRow',"new",{
                   	height:'auto',
                   	reloadAfterSubmit:false,
                   	addCaption: "test",
                   	bSubmit: "确定",
                   	bCancel: "取消",
                   	processData:"处理中",
                   	closeAfterAdd : true,
                   	reloadAfterSubmit:true,
                   	serializeEditData: function(data){ 
					    return $.param($.extend({}, data, {id:0}));
					},
                   	url: "user/userAdd.htm"
                   });
               },  
               position: "last"  
           }); 
		});
		
		$(".submit").click(function(){
			console.log($(".form input").serialize());
			jQuery("#grid-table").jqGrid("setGridParam", {
				postData: getFormData($(".form input"))
			}).trigger("reloadGrid");
		});
		
		function updateStatus(that){
			$("#updateStatusModal input[name=id]").val($(that).attr("id"));
			$("#updateStatusModal input[name=updateUser]").val($(that).attr("updateUser"));
			$("#updateStatusModal select[name=status]").val($(that).attr("status"));
			$("#updateStatusModal input[name=updateTime]").val(new Date(parseInt($(that).attr("updateTime"))).format("yyyy-MM-dd"));
			$("#updateStatusModal").modal("show");
		}
		function showPosition(imei,deviceType,lat, lng) {
			$("#showPositionModal").modal("show");
			$('#showPositionModal').on('shown.bs.modal', function () {
				if(map != null)
					map.clearOverlays();
				initMap(new BMap.Point(lng, lat), 15);
				new BMap.Geocoder().getLocation(new BMap.Point(lng,lat), function(rs){
					var infoWindow = "<div><p><h6></h6></p><p></p></div>"
					var $infoWindow = $(infoWindow);
					$infoWindow.find("p:eq(0)").html(imei);
					var addComp = rs.addressComponents;
					$infoWindow.find("p:eq(1)").html(addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber);
					addPointMarker(deviceType,lat,lng, $infoWindow.html());
				});
			});
		}
		function showSafeDistance(imei,deviceType,lat, lng) {
			$("#showPositionModal").modal("show");
			$('#showPositionModal').on('shown.bs.modal', function () {
				if(map != null)
					map.clearOverlays();
				initMap(new BMap.Point(lng, lat), 15);
				new BMap.Geocoder().getLocation(new BMap.Point(lng,lat), function(rs){
					var infoWindow = "<div><p><h6></h6></p><p></p></div>"
					var $infoWindow = $(infoWindow);
					$infoWindow.find("p:eq(0)").html(imei);
					var addComp = rs.addressComponents;
					$infoWindow.find("p:eq(1)").html(addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber);
					addPointMarker(deviceType,lat,lng, $infoWindow.html(), function(marker, newPoint) {
						console.log(marker);
						$(marker).trigger("click");
					});
				});
			});
		}
		function showStatistic(imei) {
			$("#showStatisticModal #maintanTimes").text(parseInt(Math.random()*10));
			$("#showStatisticModal #patrolTimes").text(parseInt(Math.random()*10));
			$("#showStatisticModal #errorTimes").text(parseInt(Math.random()*10));
			$("#showStatisticModal").modal("show");
		}
		
</script>