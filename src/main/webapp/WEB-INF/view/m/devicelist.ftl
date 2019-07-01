<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>设备列表</title>
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
		
		<link rel="stylesheet" href="/static/css/devicedetail.css" />
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

	<body class="page-content">
		<div class="ace-settings-container" style="top:10px;">
			<div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
				<a href="/home.htm"><i class="icon-cog bigger-80"></i></a>
			</div>
		</div>
		<div class="form">
			<input type="text" name="imei" value="" placeholder="设备号"/>
			<input type="button" value="查询" class="submit"/>
		</div>
		<br/>
		<div class="row">
			<table id="grid-table"></table>
			<div id="grid-pager"></div>
		</div>
		
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
				 jQuery.extend(jQuery.jgrid.defaults,{
					   	recordtext: "{1}/{2}",
						emptyrecords: "没有数据",  
						loadtext: "Loading...",  
						pgtext : "{0}/{1}",
						pgbuttons: false
					   });
					   
					jQuery("#grid-table").jqGrid({
						//direction: "rtl",
						url: "getAll.htm",
						postData: getFormData($(".form input")),
						rows: 1,
						datatype: "json",
						height: $(window).height()-$("#grid-table").offset().top-100,
						colNames:['','','ID', '设备名称','IMEI','设备类型','状态'],
						colModel:[
							{name:'lat',index:'lat',width:44, sorttype:"string", editable: false,hidden:true},
							{name:'lng',index:'lng',width:44, sorttype:"string", editable: false,hidden:true},
							{name:'id',index:'id',width:30, sorttype:"int", editable: false,editoptions:{readonly:true,size:20}},
							{name:'name',index:'name',width:44, sorttype:"string", editable: true},
							{name:'imei',index:'imei',width:75, sorttype:"string", editable: true},
							{name:'deviceType',index:'deviceType',width:44, sorttype:"int", editable: true, edittype:"select",formatter:'select',editoptions:{value:"1:消防栓;2:水泵接合器;3:重点单位"}},
							{name:'status',index:'status',width:30, sorttype:"int", editable: true, edittype:"select",formatter:'select',editoptions:{value:"0:未激活;1:离线;2:在线"}}
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
						rowNum:50,
						rowList:[50,100],
						pager : pager_selector,
						altRows: true,
						//toppager: true,
						
						multiselect: false,
						//multikey: "ctrlKey",
				        multiboxonly: true,
				
						loadComplete : function() {
							
						},
					//	caption: "设备",
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
	</body>
</html>