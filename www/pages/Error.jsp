<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
<title>Duke Corp</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="assets/css/bootstrap.css" rel="stylesheet" media="screen">
<link href="assets/css/thin-admin.css" rel="stylesheet" media="screen">
<link href="assets/css/font-awesome.css" rel="stylesheet" media="screen">
<link href="assets/style/style.css" rel="stylesheet">
</head>
<body>
	<div class="widget-404" style="margin-top: 50px;">
		<div class="row">
			<div class="col-md-5">
				<h1 class="text-align-center">404</h1>
			</div>
			<div class="col-md-7">
				<div class="description">
					<h3>Oops! You're lost.</h3>
					<p>We can not find the page you're looking for.</p>
					<div class="doc-buttons">
						<a class="btn btn-s-md btn-primary btn-rounded"  onclick="history.back()">Back</a>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="assets/js/jquery.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
</body>
</html>
