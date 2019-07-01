$(function(){
	initTable(1);
});

function initTable(index) {
	var grid_selector = "#grid-table"+index;
	var pager_selector = "#grid-pager"+index;

	jQuery(grid_selector).jqGrid({
		//direction: "rtl",
		url: "getAllHistory.htm",
		postData: getFormData($(".form input")),
		rows: 1,
		datatype: "json",
		height: 250,
		colNames:['Time','ID','Adderss','Account','AddressID','OriginalID', "Status"],
		colModel:[
			{name:'timeFormat',index:'timeFormat', width:60, sorttype:"string", editable: true},
			{name:'code',index:'code', width:60, sorttype:"string", editable: true},
			{name:'address',index:'address', width:60, sorttype:"string", editable: true},
			{name:'userName',index:'userName', width:60, sorttype:"string", editable: true},
			{name:'walletId',index:'phone', width:60, sorttype:"string", editable: true},
			{name:'orgWalletId',index:'walletId', width:60, sorttype:"string", editable: true},
			{name:'status',index:'status', width:60, sorttype:"string", editable: true,
				formatter : function(value, grid, rows, state) {
					return value==1?"Delivering":"Delivered";
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
		
		multiselect: false,
		//multikey: "ctrlKey",
        multiboxonly: true,
		
		loadComplete : function() {
			var table = this;
			setTimeout(function(){
				updatePagerIcons(table);
			}, 0);
		},

		caption: "用户",
		autowidth: true

	});

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

}