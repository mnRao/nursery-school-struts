<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<head>
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<tiles:insertAttribute name="headerResources" />
</head>
<body>
	<div class="login-logo">
		<img src="assets/images/logo.png" width="147" height="33">
	</div>
	<div class="widget">
		<div class="login-content">
			<div class="widget-content" style="padding-bottom: 0;">
				<tiles:insertAttribute name="body" />
			</div>
		</div>
	</div>
	<tiles:insertAttribute name="footerResources" />
</body>
</html>
