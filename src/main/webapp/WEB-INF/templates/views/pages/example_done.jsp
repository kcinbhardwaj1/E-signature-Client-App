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

<title>Remote Signing Response</title>
</head>
<link href="/assets/css2.css" rel="stylesheet">
<body>
	<h2>Envelope Response</h2>
	<br/>
	<br/>
	
    <div id="server_json_data" data-server-json-data='{"json": ${done}}' class="hidden"></div>
    <p>
        <pre class="json-display"><code id="json-display">${done}</code></pre>
    </p>
	 <p><a href="/getLatestStatus/${done.envelopeId}" target=_blank>Latest Status</a></p>
<br/>
<br/>
<c:choose>
	 <c:when test="${done.isTemplate == 'true'}">
	    <p><a href="/signingViaEmail">Continue</a></p>
	 </c:when>
	 <c:otherwise>
	  	<p><a href="/index">Continue</a></p>
	 </c:otherwise>
</c:choose>
</body>
</html>
