<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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



	<form action="/remoteSigningWithoutTemp" method="post" data-busy="form"
		enctype="multipart/form-data">
		<div class="table-title" align="center">

			<h1>Send an envelope with a remote (email) signer and cc
				recipient</h1>
			<p>The envelope includes a pdf, Word, and HTML document.</p>
			<table class="table-fill" border="2" cellpadding="5">


				<tr>
					<td class="text-left"><label for="signerEmail">Signer
							Email</label></td>
					<td class="text-left"><input type="email" id="signerEmail"
						name="signerEmail" aria-describedby="emailHelp"
						placeholder="pat@example.com" required> <small
						id="emailHelp">We'll never share your email with anyone
							else.</small></td>
				</tr>
				<tr></tr>

				<tr>

					<td><label for="signerName">Signer Name</label></td>
					<td class="text-left"><input type="text" id="signerName"
						placeholder="Pat Johnson" name="signerName" required></td>

				</tr>

				<tr></tr>
				<tr>
					<td class="text-left"><label for="signerEmail2">Signer
							Email</label></td>
					<td class="text-left"><input type="email" id="signerEmail2"
						name="signerEmail2" aria-describedby="emailHelp"
						placeholder="pat@example.com" required> <small
						id="emailHelp">We'll never share your email with anyone
							else.</small></td>
				</tr>
				<tr></tr>

				<tr>

					<td><label for="signerName2">Signer Name</label></td>
					<td class="text-left"><input type="text" id="signerName2"
						placeholder="Pat Johnson" name="signerName2" required></td>

				</tr>
				<tr></tr>
				
				<tr>
					<td><label for="ccEmail">CC Email</label></td>

					<td class="text-left"><input type="email" id="ccEmail"
						name="ccEmail" aria-describedby="emailHelp"
						placeholder="pat@example.com" required></td>
				</tr>
				<tr></tr>

				<tr>
					<td class="text-left"><label for="ccName">CC Name</label></td>
					<td class="text-left"><input type="text" id="ccName"
						placeholder="Pat Johnson" name="ccName" required></td>
				</tr>
				<tr></tr>
				<tr>
					<td class="text-left"><label for="emailSubject">Email
							Subject</label></td>
					<td class="text-left"><input type="text" id="emailSubject"
						name="emailSubject" required></td>
				</tr>
				<tr></tr>
				<tr>
					<td class="text-left"><label for="myfile">Select a
							file:</label></td>
					<td><input type="file" id="file-upload" name="file"></td>
				</tr>
				<tr></tr>

				<tr>
					<td></td>
					<td>
						<button type="submit">Submit</button>
					</td>
				</tr>
			</table>
			
		</div>
	</form>
	<br/>
	<br/>
	<div align="center">
	<form method="get" action="/index">
    		<button class="btn btn-primary" type="submit">Home</button>
		</form>
	</div>
</body>
</html>