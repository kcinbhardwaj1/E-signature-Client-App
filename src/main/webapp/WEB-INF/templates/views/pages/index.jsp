<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Envelope API Services</title>
<meta charset="utf-8">
<meta name="viewport"
	content="initial-scale=1.0; maximum-scale=1.0; width=device-width;">
<meta name="description" content="">
<meta name="author" content="">
</head>
<link href="/assets/css1.css" rel="stylesheet">
<body>

	<h1>Welcome , Envelope Rest API Services</h1>

	<div class="table-title" align="center">

		<table class="table-fill" border="2" cellpadding="5">
			<tr>
				<th class="text-left">Envelope API Services Link's</th>
			</tr>

			<tr>
				<td class="text-left">
					<h4 id="signingViaEmailWithoutTemp">
						<a href="signingViaEmailWithoutTemp">Remote Signing Ceremony</a>
					</h4>
					<p>Create and send an envelope, the envelope includes a pdf, Word,
						and txt documents.</p>
				</td>
			</tr>

			<tr>
				<td class="text-left">
					<h4 id="embedSigningWithoutTemp">
						<a href="embedSigningWithoutTemp">Embedded Signing Ceremony</a>
					</h4>
					<p>Create an envelope and do signing within application</p>
				</td>
			</tr>

			<tr>
				<td class="text-left">
					<h4 id="remoteSigning">
						<a href="signingViaEmail">Remote Signing Ceremony Using Template</a>
					</h4>
					<p>Create and send an envelope using template, the envelope
						includes a pdf, Word and txt documents</p>
				</td>
			</tr>

			<tr>
				<td class="text-left">
					<h4 id="embedSigning">
						<a href="embedSigning">Embedded Signing Ceremony Using Template</a>
					</h4>
					<p>Create an envelope using template and do signing within
						application</p>
				</td>
			</tr>

			<tr>
				<td class="text-left">
					<h4 id="listEnvelopes">
						<a href="listEnvelopes">List Envelopes, Documents and
							Recipients</a>
					</h4>
					<p>Perform List envelopes, Filter envelopes, Document download,
						Delete document and List Recipients</p>
				</td>
			</tr>
		</table>
</body>
</html>