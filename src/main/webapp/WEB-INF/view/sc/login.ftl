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
<body style="background: url('/static/images/sc/login.jpg');background-repeat: no-repeat;background-size: 100% 100%;">
	<div class="center" style="height:300px;width:50%;">
		<div class="item" style="text-align: left;">
			<img alt="" src="/static/images/sc/logo.png" width="40%">
		</div>
		<div class="item">
			<h1>LOGIN</h1>
		</div>
		<div class="item">
			<input type="text" name="email" placeholder="Email"/>
		</div>
		<div class="item">
			<input type="password" name="password" placeholder="Password"/>
		</div>
		<div class="item">
			<input class="left" type="button" value="SignIn">
			<input class="right float" type="button" value="Register">
			<br>
		</div>
	</div>
	<script type="text/javascript">
		$(function(){
			$(".left").click(function(){
				$.ajax({
					url: "/sc/signin",
					type: "POST",
					data: {email:$("input[name='email']").val(), password:$("input[name='password']").val()},
					dataType: 'text',
					success: function(responseData){
						if(responseData != '"ok"')
							alert(responseData);
						else
							window.location.href="/sc/main"
					}
				})
			})
			$(".right").click(function(){
				window.location.href="/sc/register"
			})
		})
	</script>
</body>
</html>