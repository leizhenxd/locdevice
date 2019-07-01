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
				url: "getAllUser.htm",
				postData: getFormData($(".form input")),
				rows: 1,
				datatype: "json",
				height: 250,
				colNames:['ID', '姓名','电话','密码','角色','密码盐'],
				colModel:[
					{name:'id',index:'id', width:60, sorttype:"int", editable: false,editoptions:{readonly:true,size:20}},
					{name:'userName',index:'userName', width:60, sorttype:"string", editable: true},
					{name:'phone',index:'phone', width:60, sorttype:"string", editable: true},
					{name:'password',index:'password', width:60, sorttype:"string", editable: true},
					{name:'roleId',index:'roleId', width:60, editable: true,edittype:"select",formatter:'select',editoptions:{value:"0:超级管理员;1:管理员"}},
					{name:'salt',index:'salt', width:60, sorttype:"string", editable: true}
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
						styleCheckbox(table);
						
						updateActionIcons(table);
						updatePagerIcons(table);
						enableTooltips(table);
					}, 0);
				},
		
				editurl: "user/addUser.htm",//nothing is saved
				caption: "用户",
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
			)
		
			navs.navButtonAdd(pager_selector, {  
               caption: "添加用户",  
               title:"添加用户",
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
           }) 
			
			function style_edit_form(form) {
				//enable datepicker on "sdate" field and switches for "stock" field
				form.find('input[name=sdate]').datepicker({format:'yyyy-mm-dd' , autoclose:true})
					.end().find('input[name=stock]')
						  .addClass('ace ace-switch ace-switch-5').wrap('<label class="inline" />').after('<span class="lbl"></span>');
		
				//update buttons classes
				var buttons = form.next().find('.EditButton .fm-button');
				buttons.addClass('btn btn-sm').find('[class*="-icon"]').remove();//ui-icon, s-icon
				buttons.eq(0).addClass('btn-primary').prepend('<i class="icon-ok"></i>');
				buttons.eq(1).prepend('<i class="icon-remove"></i>')
				
				buttons = form.next().find('.navButton a');
				buttons.find('.ui-icon').remove();
				buttons.eq(0).append('<i class="icon-chevron-left"></i>');
				buttons.eq(1).append('<i class="icon-chevron-right"></i>');		
			}
		
			function style_delete_form(form) {
				var buttons = form.next().find('.EditButton .fm-button');
				buttons.addClass('btn btn-sm').find('[class*="-icon"]').remove();//ui-icon, s-icon
				buttons.eq(0).addClass('btn-danger').prepend('<i class="icon-trash"></i>');
				buttons.eq(1).prepend('<i class="icon-remove"></i>')
			}
			
			//it causes some flicker when reloading or navigating grid
			//it may be possible to have some custom formatter to do this as the grid is being created to prevent this
			//or go back to default browser checkbox styles for the grid
			function styleCheckbox(table) {
			/**
				$(table).find('input:checkbox').addClass('ace')
				.wrap('<label />')
				.after('<span class="lbl align-top" />')
		
		
				$('.ui-jqgrid-labels th[id*="_cb"]:first-child')
				.find('input.cbox[type=checkbox]').addClass('ace')
				.wrap('<label />').after('<span class="lbl align-top" />');
			*/
			}
			
		
			//unlike navButtons icons, action icons in rows seem to be hard-coded
			//you can change them like this in here if you want
			function updateActionIcons(table) {
				/**
				var replacement = 
				{
					'ui-icon-pencil' : 'icon-pencil blue',
					'ui-icon-trash' : 'icon-trash red',
					'ui-icon-disk' : 'icon-ok green',
					'ui-icon-cancel' : 'icon-remove red'
				};
				$(table).find('.ui-pg-div span.ui-icon').each(function(){
					var icon = $(this);
					var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
					if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
				})
				*/
			}
			
			//replace icons with FontAwesome icons like above
			function updatePagerIcons(table) {
				var replacement = 
				{
					'ui-icon-seek-first' : 'icon-double-angle-left bigger-140',
					'ui-icon-seek-prev' : 'icon-angle-left bigger-140',
					'ui-icon-seek-next' : 'icon-angle-right bigger-140',
					'ui-icon-seek-end' : 'icon-double-angle-right bigger-140'
				};
				$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
					var icon = $(this);
					var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
					
					if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
				})
			}
		
			function enableTooltips(table) {
				$('.navtable .ui-pg-button').tooltip({container:'body'});
				$(table).find('.ui-pg-div').tooltip({container:'body'});
			}
		
			//var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');
		
		
		});
		
		$(".submit").click(function(){
			console.log($(".form input").serialize());
			jQuery("#grid-table").jqGrid("setGridParam", {
				postData: getFormData($(".form input"))
			}).trigger("reloadGrid");
		});
</script>