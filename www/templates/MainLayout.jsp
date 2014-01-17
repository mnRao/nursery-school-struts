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
	<div class="container">
		<tiles:insertAttribute name="header" />
	</div>
	<div class="wrapper">
		<div class="left-nav">
			<tiles:insertAttribute name="menu" />
		</div>
		<div class="page-content">
			<div class="content container">
				<s:if test="hasActionErrors()">
					<s:actionerror />
				</s:if>
				<s:if test="hasActionMessages()">
					<s:actionmessage />
				</s:if>
				<div class="row">
					<div class="col-lg-12">
						<h2 class="page-title">
							<tiles:insertAttribute name="headTitle" />
						</h2>
					</div>
				</div>
				<tiles:insertAttribute name="body" />
			</div>
		</div>
	</div>
	<tiles:insertAttribute name="footer" />
	<tiles:insertAttribute name="footerResources" />
</body>
</html>