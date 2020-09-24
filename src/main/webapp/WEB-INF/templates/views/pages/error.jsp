<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
<h2>Error Response</h2>

You seem to have encountered an error in an 
otherwise perfect Web Site. If you would like 
to report this error, you may email the site 
administrator, or call him directly.
	<br/>
	<br/>
	<h3>Error Details below !!</h3>
	<br/>
	<br/>
	
    <div id="server_json_data" data-server-json-data='{"json": ${done}}' class="hidden"></div>
    <p>
        <pre class="json-display"><code id="json-display">${done}</code></pre>
    </p>

<br/>


<br/>
<p><a href="/index">Continue</a></p>
</body>
</html>