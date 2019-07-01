<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Upload Meta Data</title>
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
 	<meta name="apple-mobile-web-app-capable" content="yes" />
 	<meta name="apple-mobile-web-app-status-bar-style" content="black" />
 	<meta name="format-detection" content="telphone=no,email=no" />
 	<link href="/static/css/sc/style.css" rel="stylesheet"/>
 	<script src='/static/js/jquery-1.8.2.min.js'></script>
</head>
<body style="background: url('/static/images/sc/main.jpg');background-repeat: no-repeat;background-size: 100% 100%;">
	<div class="center" style="height:300px;width:50%;">
		<div class="item" style="text-align: left;">
			<img alt="" src="/static/images/sc/logo.png" width="40%">
		</div>
		<form method="post" enctype="multipart/form-data">
			<h1>Upload Meta Data</h1>
			<div class="item">
				<input type="file" name="file">
			</div>
			<div class="item">
				<input type="submit" value="Upload"/>
			</div>
		</form>
	</div>
</body>
</html>