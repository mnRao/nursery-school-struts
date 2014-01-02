<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/assets/css/style.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="assets/js/sampleJS.js"></script>
<title>Insert title here</title>
</head>
<body>
	<h2>Struts 2 - Login</h2>
	<s:actionerror />
	<s:form action="login.action" method="post">
		<s:textfield cssClass="demo1" name="username" key="label.username"
			size="20"></s:textfield>
		<s:password name="password" key="label.password" size="20"></s:password>
		<s:submit method="execute" key="label.login" align="center" />
	</s:form>
	<p>${pageContext.request.contextPath}</p>
	<p>${struts.demoConstant}</p>
</body>
</html>