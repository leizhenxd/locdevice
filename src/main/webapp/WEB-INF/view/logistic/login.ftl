<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>Login</title>
		<link rel="icon" href="favicon.ico" type="image/x-icon" />
		<link rel="shortcut icon" href="favicon.ico"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />

		<!-- basic styles -->

		<link href="/static/css/ace/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="/static/css/ace/font-awesome.min.css" />

		<!--[if IE 7]>
		  <link rel="stylesheet" href="/static/css/ace/font-awesome-ie7.min.css" />
		<![endif]-->

		<!-- page specific plugin styles -->


		<!-- ace styles -->

		<link rel="stylesheet" href="/static/css/ace/ace.min.css" />
		<link rel="stylesheet" href="/static/css/ace/ace-rtl.min.css" />

		<!--[if lte IE 8]>
		  <link rel="stylesheet" href="/static/css/ace/ace-ie.min.css" />
		<![endif]-->

		<!-- inline styles related to this page -->

		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

		<!--[if lt IE 9]>
		<script src="assets/js/html5shiv.js"></script>
		<script src="assets/js/respond.min.js"></script>
		<![endif]-->
	</head>

	<body class="login-layout">
		<div class="main-container">
			<div class="main-content">
				<div class="row">
					<div class="col-sm-10 col-sm-offset-1">
						<div class="login-container">
							<div class="center">
								<h1>
									<i class="icon-leaf green"></i>
									<span class="red"></span>
									<span class="white">CL Chain Nodes Management Console</span>
								</h1>
							</div>

							<div class="space-6"></div>

							<div class="position-relative">
								<div id="login-box" class="login-box visible widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header blue lighter bigger">
												<i class="icon-coffee green"></i>
												Input Username and Password
											</h4>

											<div class="space-6"></div>
											<div class="alert alert-danger" style="display:none;">
												<button type="button" class="close" data-dismiss="alert">
													<i class="icon-remove"></i>
												</button>
												 <font></font>
												<br />
											</div>
											<form action="doLogin.htm" method="post">
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" name="phone" class="form-control" placeholder="Username" />
															<i class="icon-user"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" name="password" class="form-control" placeholder="Passowrd" />
															<i class="icon-lock"></i>
														</span>
													</label>

													<div class="space"></div>

													<div class="clearfix">
														<button id="loginbutton" type="button" class="width-35 pull-right btn btn-sm btn-primary">
															<i class="icon-key"></i>
															LOGIN
														</button>
													</div>

													<div class="space-4"></div>
												</fieldset>
											</form>

										</div><!-- /widget-main -->
									</div><!-- /widget-body -->
								</div><!-- /login-box -->
							</div><!-- /position-relative -->
						</div>
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div>
		</div><!-- /.main-container -->

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

		<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='/static/js/ace/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>

		<!-- inline scripts related to this page -->

		<script type="text/javascript">
			$("#loginbutton").click(function(){
				$.ajax({
					url: "doLogin.htm",
					data: $("form").serialize(),
					type: 'post',
					dataType: 'json',
					success: function(data){
						if(data){
							if(data == 'success') {
								window.location.href="index.htm";
							}
							else{
								$(".alert-danger font").html(data);			
								$(".alert-danger").slideDown();
							}
						}
					},
					error: function(e) {
						console.log(e);
						$(".alert-danger font").html(e.responseText);			
						$(".alert-danger").slideDown();
					}
				});
			});
			
			$(".close").click(function(){
				$(this).parent().slideUp("fast");
			});
		</script>
</body>
</html>
