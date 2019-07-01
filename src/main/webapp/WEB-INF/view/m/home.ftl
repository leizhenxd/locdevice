<!DOCTYPE html>
<html lang="en" style="height:100%;overflow:hidden;">
	<head>
		<meta charset="utf-8" />
		<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
		<title>消防设备定位系统</title>
		<link rel="stylesheet" href="/static/css/ace/ace.min.css" />
		<link rel="stylesheet" href="/static/css/ace/font-awesome.min.css" />
		<style>
			.col-xs-3,.col-xs-9 {float:left}
			.col-xs-3{width:20%;}
			.col-xs-9{width:80%;}
			.row {width:100%;padding: 5px 5px;font-weight:blod;line-height;1em;margin-top:0.5em;}
			.row label {margin-left:20px}
			a {text-decoration: none;margin-left:1em;}
			a:visited {color:black;}
			i {font-size:1.4em;}
		</style>	
	</head>

	<body style="height:100%;font-size:1.5em;background-color:rgba(255,180,75,0.5);font-family:'SimHei'">
		<div>
			<div>
				<div class="row">
					<div class="col-xs-3">
						<i class="icon-user" style="font-size:2.7em;"></i>
					</div>
					<div class="col-xs-9 user">
						<div>${Session.loginUser.userName}</div>
						<div>${Session.loginUserRole.roleName}</div>
					</div>
				</div>
				<hr style="width:98%;margin-left:1%;">
				<div class="row">
					<i class="icon-fire"></i>
					<a href="/index.htm">主页</a>
				</div>
				<div class="row">
					<i class="icon-fire"></i>
					<a href="/device/list.htm">设备列表</a>
				</div>
				<div class="row">
					<i class="icon-book"></i>
					<a href="/device/getMaintainInfo.htm">维护记录</a>
				</div>
				<div class="row">
					<i class="icon-book"></i>
					<a href="/static/help.html">使用指南</a>
				</div>
			</div>
		</div>
	</body>
</html>