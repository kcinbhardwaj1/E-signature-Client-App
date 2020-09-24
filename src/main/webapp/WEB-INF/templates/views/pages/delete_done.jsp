<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="initial-scale=1.0; maximum-scale=1.0; width=device-width;">
<meta name="description" content="">
<meta name="author" content="">
</head>
<link href="/assets/css.css" rel="stylesheet">
<body>
	<h2>Document Delete Response.</h2>
	<br/>
	<br/>
	
    <div id="server_json_data" data-server-json-data='{"json": ${done}}' class="hidden"></div>
    <p>
        <pre class="json-display"><code id="json-display">${done}</code></pre>
    </p>

<br/>
<br/>
<p><a href="/listEnvelopes">Continue</a></p>

</body>
</html>
