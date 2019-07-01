$(function(){
	initTable(1);
	$('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        // 获取已激活的标签页的名称
		console.log($(e.target).text());
        if($(e.target).text() == "Dealer Account") {
        	initTable(2);
        }
    });
});

function initTable(index) {
	var grid_selector = "#grid-table"+index;
	var pager_selector = "#grid-pager"+index;

	jQuery(grid_selector).jqGrid({
		//direction: "rtl",
		url: "getAllUser.htm?role="+index,
		postData: getFormData($(".form input")),
		rows: 1,
		datatype: "json",
		height: 250,
		colNames:['Name','Password','Contact','Wallet ID'],
		colModel:[
			{name:'realName',index:'realName', width:60, sorttype:"string", editable: true},
			{name:'password',index:'password', width:60, sorttype:"string", editable: true},
			{name:'phone',index:'phone', width:60, sorttype:"string", editable: true},
			{name:'walletId',index:'walletId', width:60, sorttype:"string", editable: true}
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