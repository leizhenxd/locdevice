<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>消防设备定位系统</title>
		<link rel="shortcut icon" href="favicon.ico"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
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

	<body>
		<style>
			.datepicker-dropdown {z-index:2500 !important;}
			.BMap_pop div:nth-child(1) {
				border-top-left-radius:15px;
			}
			.BMap_pop div:nth-child(3) {
				border-top-right-radius:15px;
			}
			.BMap_pop div:nth-child(5) {
				border-bottom-left-radius:15px;
			}
			.BMap_pop div:nth-child(7) {
				border-bottom-right-radius:15px;
			}
		</style>
		<div class="navbar navbar-default" id="navbar">
			<script type="text/javascript">
				try{ace.settings.check('navbar' , 'fixed')}catch(e){}
			</script>

			<div class="navbar-container" id="navbar-container">
				<div class="navbar-header pull-left">
					<a href="#" class="navbar-brand">
						<small>
							<i class="icon-leaf"></i>
							管理控制台
						</small>
					</a><!-- /.brand -->
				</div><!-- /.navbar-header -->
				

				<div class="navbar-header pull-right" role="navigation">
					<ul class="nav ace-nav">
						<li class="light-blue">
							<a data-toggle="dropdown" href="#" class="dropdown-toggle">
								<img class="nav-user-photo" src="/static/css/avatars/user.jpg" alt="Jason's Photo" />
								<span class="user-info">
									<small>欢迎,</small>
									${Session.loginUser.userName}
								</span>

								<i class="icon-caret-down"></i>
							</a>

							<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
								<li>
									<a href="logout.htm">
										<i class="icon-off"></i>
										注销
									</a>
								</li>
							</ul>
						</li>
					</ul><!-- /.ace-nav -->
				</div><!-- /.navbar-header -->
			</div><!-- /.container -->
		</div>

		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

			<div class="main-container-inner">
				<a class="menu-toggler display" id="menu-toggler" href="#">
					<span class="menu-text"></span>
				</a>

				<div class="sidebar display" id="sidebar">
					<script type="text/javascript">
						try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
					</script>

					<div class="sidebar-collapse" id="sidebar-collapse">
						<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
					</div>
					<ul class="nav nav-list">
						<li>
							<a href="#" class="dropdown-toggle">
								<i class="icon-dashboard"></i>
								<span class="menu-text"> 控制台 </span>
								<b class="arrow icon-angle-down"></b>
							</a>

							<ul class="submenu">
								<#list areas as area>
									<li>
										<a href="#" class="dropdown-toggle areaItem">
											<i class="icon-group"></i>
											<span class="menu-text">${area.name}</span>
											<b class="arrow icon-angle-down"></b>
										</a>
										<ul class="submenu" areaId="${area.id}">
											<li class="link">
												<a href="#" link1="device/view.htm?areaId=${area.id}&deviceType=1" class="dropdown-toggle">
													<i class="icon-angle-right"></i>
													<span class="menu-text"><input type="checkbox" value="1" name="devicescb" checked="checked"/> 消防栓</span>
												</a>
												
											</li>
											<li class="link">
												<a href="#" link1="device/view.htm?areaId=${area.id}&deviceType=2" class="dropdown-toggle">
													<i class="icon-angle-right"></i>
													<span class="menu-text"><input type="checkbox" value="2" name="devicescb" checked="checked"/> 水泵结合器</span>
												</a>
												
											</li>
											<li class="link">
												<a href="#" link1="device/view.htm?areaId=${area.id}&deviceType=3" class="dropdown-toggle">
													<i class="icon-angle-right"></i>
													<span class="menu-text"><input type="checkbox" value="3" name="devicescb" checked="checked"/> 重点单位</span>
												</a>
											</li>
										</ul>
									</li>
								</#list>
							</ul>
						</li>
						<li class="link">
							<a href="#" link="device/list.htm">
								<i class="icon-fire"></i>
								<span class="menu-text">设备管理</span>
							</a>
						</li>
						<li class="link">
							<a href="#" link="test/grid.htm">
								<i class="icon-user"></i>
								<span class="menu-text">用户管理</span>
							</a>
						</li>
						<li class="link">
							<a href="#" class="dropdown-toggle">
								<i class="icon-book"></i>
								<span class="menu-text">统计报表</span>
								<b class="arrow icon-angle-down"></b>
							</a>
							<ul class="submenu">
								<li class="link">
									<a href="#" link="statistic/list.htm" class="dropdown-toggle">
										<i class="icon-angle-right"></i>
										<span class="menu-text">报警信息统计</span>
									</a>
								</li>
								<li class="link">
									<a href="#" link="statistic/list.htm" class="dropdown-toggle">
										<i class="icon-angle-right"></i>
										<span class="menu-text">出警次数统计</span>
									</a>
								</li>
								<li class="link">
									<a href="#" link="statistic/list.htm" class="dropdown-toggle">
										<i class="icon-angle-right"></i>
										<span class="menu-text">维护数量统计</span>
									</a>
								</li>
								<li class="link">
									<a href="#" link="statistic/list.htm" class="dropdown-toggle">
										<i class="icon-angle-right"></i>
										<span class="menu-text">维护信息统计</span>
									</a>
								</li>
							</ul>
						</li>
					</ul><!-- /.nav-list -->


					<script type="text/javascript">
						try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
					</script>
				</div>

				<div class="main-content">
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>

						<ul class="breadcrumb">
							<li>
								<i class="icon-home home-icon"></i>
								<a href="index.htm">首页</a>
							</li>
						</ul><!-- .breadcrumb -->
					</div>

					<div class="page-content">
						<#include "test/hello.ftl">
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->

				<div class="ace-settings-container" id="ace-settings-container">
					<div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
						<i class="icon-cog bigger-150"></i>
					</div>

					<div class="ace-settings-box" id="ace-settings-box">
						<div>
							<div class="pull-left">
								<select id="skin-colorpicker" class="hide">
									<option data-skin="default" value="#438EB9">#438EB9</option>
									<option data-skin="skin-1" value="#222A2D">#222A2D</option>
									<option data-skin="skin-2" value="#C6487E">#C6487E</option>
									<option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
								</select>
							</div>
							<span>&nbsp; Choose Skin</span>
						</div>

						<div>
							<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-navbar" />
							<label class="lbl" for="ace-settings-navbar"> Fixed Navbar</label>
						</div>

						<div>
							<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-sidebar" />
							<label class="lbl" for="ace-settings-sidebar"> Fixed Sidebar</label>
						</div>

						<div>
							<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-breadcrumbs" />
							<label class="lbl" for="ace-settings-breadcrumbs"> Fixed Breadcrumbs</label>
						</div>

						<div>
							<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-rtl" />
							<label class="lbl" for="ace-settings-rtl"> Right To Left (rtl)</label>
						</div>

						<div>
							<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-add-container" />
							<label class="lbl" for="ace-settings-add-container">
								Inside
								<b>.container</b>
							</label>
						</div>
					</div>
				</div><!-- /#ace-settings-container -->
			</div><!-- /.main-container-inner -->

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="icon-double-angle-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->

		
		<!-- inline scripts related to this page -->
  	<script>
  		
  		function devicescbEvent (ul) {
  			var areaId = ul.attr("areaId");
  			$.ajax({
  				url: "device/view.htm?areaId="+areaId,
  				type: 'post',
  				data: ul.find("input[name='devicescb']").serialize(),
  				dataType: 'text',
  				success: function(data){
  					$(".page-content").html(data);
  				}
  			});
  		}
  		
  		$(function(){
	  		$(".nav .link a").click(function(){
	  			if($(this).attr("link")) {
		  			$.ajax({
		  				url: $(this).attr("link"),
		  				type: 'post',
		  				dataType: 'text',
		  				success: function(data){
		  					$(".page-content").html(data);
		  				}
		  			});
	  			}
	  		});
	  		$("input[name='devicescb']").change(function(){
	  			devicescbEvent($(this).closest("ul"));
	  		});
	  		
	  		$(".areaItem").on("click tap",function(){
	  			if(!$(this).closest("li").hasClass("open")) {
	  				devicescbEvent($(this).closest("li").find("ul:eq(0)"));
	  			}
	  		});
  		});
  	</script>
</body>
</html>