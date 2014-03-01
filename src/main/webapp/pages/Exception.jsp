<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
<title>Critical Error</title>
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
				<h1 class="text-align-center">000</h1>
			</div>
			<div class="col-md-7">
				<div class="description">
					<p>The application has malfunctioned. Please contact technical
						support with the following information:</p>
					<h2>
						<small>Exception Name:</small>
					</h2>
					<s:property value="exception" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<!-- 		This is to make sure the description is centered -->
		<div class="col-md-2"></div>
		<div class="col-md-8" style="">
			<div class="widget">
				<div class="description">
					<h2>
						<small>Exception Details:</small>
					</h2>
					<pre>
						<s:property value="exceptionStack" />
						</pre>
					<div class="doc-buttons">
						<a class="btn btn-s-md btn-primary btn-rounded"
							onclick="history.back()"
						>Back</a>
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
