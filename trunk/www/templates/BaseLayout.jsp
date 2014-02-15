<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<head>
<title><s:set var="title">
		<tiles:getAsString name="title" ignore="true" />
	</s:set> <s:text name="%{#title}" /></title>
<tiles:insertAttribute name="headerResources" />
</head>
<body>
	<tiles:insertAttribute name="body" />
	<tiles:insertAttribute name="footerResources" />
</body>
</html>
