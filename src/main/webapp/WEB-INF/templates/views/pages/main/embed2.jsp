<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head><br />    <title>IFrame Application</title><br /> 

 <script type="text/javascript" src="https://static.eversign.com/js/embedded-signing.js"></script>
   <style type="text/css">
      body { background-color: #fefdfc;}
      p.description { width: 500px; border: 1px solid #cfc; padding: 20px; }
      .form_cont { border: 1px solid #cfc; padding: 20px; width: 1024px; background-color: #cd9;}
      .top_cont { width: 1124px; margin-left: auto; margin-right: auto; }
      #dswpf { width: 1024px; height: 700px;}
    </style>
  </head>
<link href="/assets/css.css" rel="stylesheet">
<body>
<div class="table-title" align="center">
<h2>Embedded Signing Ceremony URLs</h2>
		
                <iframe id="dswpf" src="${url}"/>   
  
<br/>
<br/>
<p><a href="/embedSigning">Continue</a></p>
 </div>
 <br/>
<br/>
 
  <p><a href="/getLatestStatus/${envelopeId}" target=_blank>Latest Status</a></p>
</body>
</html>