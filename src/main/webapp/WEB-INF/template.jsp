<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%
response.setHeader("Cache-Control","max-age=0");  
response.setHeader("Pragma","no-cache");  
response.setDateHeader("Expires", 0); //prevents caching at the proxy server  
%>
<html>
<head>
</head>
<body>
<div id="header">
<tiles:insertAttribute name="header" />
</div>
<div id="body" >
<tiles:insertAttribute name="body" />
</div>
</body>
</html>