<div class="form">
	<input type="text" name="phone" value=""/>
	<input type="text" name="userName"/>
	<input type="button" value="submit" class="submit"/>
</div>
<div class="row">
	<table id="grid-table"></table>
	<div id="grid-pager"></div>
</div>

<script>
		
		jQuery(function($) {
			console.log(getFormData($(".form input")));
			var grid_selector = "#grid-table";
			var pager_selector = "#grid-pager";
		
			jQuery("#grid-table").jqGrid({
				//direction: "rtl",
				url: "statistic/getAll.htm",
				postData: getFormData($(".form input")),
				rows: 1,
				datatype: "json",
				height: 250,
				colNames:['ID', '设备名称','IMEI','设备类型','状态','区域名称','省'],
				colModel:[
					{name:'id',index:'id', width:60, sorttype:"int", editable: false,editoptions:{readonly:true,size:20}},
					{name:'name',index:'name', width:60, sorttype:"string", editable: true},
					{name:'imei',index:'imei', width:60, sorttype:"string", editable: true},
					{name:'deviceType',index:'deviceType', width:60, sorttype:"int", editable: true, edittype:"select",formatter:'select',editoptions:{value:"1:消防栓;2:水泵接合器"}},
					{name:'status',index:'status', width:60, sorttype:"int", editable: true, edittype:"select",formatter:'select',editoptions:{value:"0:未激活;1:激活"}},
					{name:'areaName',index:'areaName', width:60, editable: true},
					{name:'province',index:'province', width:60, sorttype:"string", editable: true}
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
					var table = this;
					setTimeout(function(){
						updateActionIcons(table);
						updatePagerIcons(table);
						enableTooltips(table);
					}, 0);
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
</script>