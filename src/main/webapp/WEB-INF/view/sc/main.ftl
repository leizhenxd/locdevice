<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
 	<meta name="apple-mobile-web-app-capable" content="yes" />
 	<meta name="apple-mobile-web-app-status-bar-style" content="black" />
 	<meta name="format-detection" content="telphone=no,email=no" />
 	<link href="/static/css/sc/style.css" rel="stylesheet"/>
 	<script src='/static/js/jquery-1.8.2.min.js'></script>
</head>
<body style="background: url('/static/images/sc/main.jpg');background-repeat: no-repeat;background-size: 100% 100%;">
	<div class="center"  style="height:480px">
		<div class="item" style="text-align: left;">
			<img alt="" src="/static/images/sc/logo.png" width="30%">
		</div>
		<div class="item">
			<h1>CLAND BLOCKCHAIN SYSTEM DASH BOARD</h1>
		</div>
		<div class="item">
			<table class="main">
				<tr>
					<td class="cube1" link="/sc/form">Upload Form</td>
					<td class="cube2" link="/sc/metadata">Upload Meta Data</td>
				</tr>
				<tr>
					<td class="cube3" link="/sc/history">Block History</td>
					<td class="cube4" link="/sc/report">Data Report</td>
				</tr>
			</table>
		</div>
	</div>
	<script type="text/javascript">
		$(function(){
			$(".cube1,.cube2,.cube3,.cube4").click(function(){
				window.location.href=$(this).attr("link")
			})
		})
	</script>
</body>
</html>