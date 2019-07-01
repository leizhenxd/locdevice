<#macro view title="view" 
	js=[] 
	css=[] 
	>
<!doctype html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="apple-mobile-web-app-status-bar-style" content="black">
  <title>${title}</title>
  <link rel="stylesheet" type="text/css" href="/view/static/css/global.css">
  <script type="text/javascript" src="/view/static/js/jquery-1.8.2.min.js"></script>
  <#list css as file>   
		<link rel="stylesheet" href="${file}" />
  </#list>
  <#list js as file>
		<script type="text/javascript" src="${file}"></script>
  </#list>
</head>
<body>
	<#nested/>
</body>
</html>
</#macro>