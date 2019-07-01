$(".updateStatusBtn").click(function(){
	console.log($("#updateStatusModal form").serialize());
	$.ajax({
		url: "/device/updateStatus.htm",
		data: $("#updateStatusModal form").serialize(),
		success: function(data){
			console.log("success");
			$("#updateStatusModal").modal("hide");
			jQuery("#grid-table").jqGrid('setGridParam', {}).trigger('reloadGrid');
		},
		error: function(data) {
			console.log(data);
			alert(data.responseText);
		}
	});
});
