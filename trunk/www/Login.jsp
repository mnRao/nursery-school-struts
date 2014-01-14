<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%-- <title><s:text name="global.title"></s:text></title> --%>
<title>Duke Corp</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="assets/css/bootstrap.css" rel="stylesheet" media="screen">
<link href="assets/css/thin-admin.css" rel="stylesheet" media="screen">
<link href="assets/css/font-awesome.css" rel="stylesheet" media="screen">
<link href="assets/style/style.css" rel="stylesheet">
</head>
<body onload="setFocusToLoginTextBox()">
	<div class="login-logo">
		<img src="assets/images/logo.png" width="147" height="33">
	</div>

	<div class="widget">
		<div class="login-content">
			<div class="widget-content" style="padding-bottom: 0;">
				<s:form namespace="/" action="login" method="post" cssClass="no-margin">
					<h3 class="form-title">
						<s:text name="label.login" />
					</h3>
					<fieldset>
						<div class="form-group no-margin">
							<div class="input-group input-group-lg">
								<span class="input-group-addon"> <i class="icon-user"></i>
								</span>
								<s:textfield cssClass="form-control input-lg" name="username"
									key="label.username" placeholder="%{getText('label.username')}"
									size="20"></s:textfield>
							</div>
						</div>
						<div class="form-group">
							<div class="input-group input-group-lg">
								<span class="input-group-addon"> <i class="icon-lock"></i>
								</span>
								<s:password cssClass="form-control input-lg"
									placeholder="%{getText('label.password')}" name="password"
									key="label.password" size="20"></s:password>
							</div>
						</div>
					</fieldset>
					<s:fielderror />
					<s:actionerror />
					<div class="form-actions">
						<label class="checkbox">
							<div class="checker">
								<span><input type="checkbox" value="1" name="remember"></span>
							</div> <s:text name="label.rememberMe"></s:text>
						</label>
						<s:submit cssClass="btn btn-warning pull-right" 
							key="label.login" align="center">
							<i class="m-icon-swapright m-icon-white"></i>
						</s:submit>
					</div>
				</s:form>

				<!-- 	Localization -->
				<s:url id="localeEN" namespace="/" action="locale">
					<s:param name="request_locale">en</s:param>
				</s:url>
				<s:url id="localeVI" namespace="/" action="locale">
					<s:param name="request_locale">vi_VN</s:param>
				</s:url>
				<div style="text-align: center">
					<s:a href="%{localeEN}">
						<img src="assets/app/English.png" height="24"
							style="display: inline-block" />
					</s:a>
					<s:a href="%{localeVI}">
						<img src="assets/app/Vietnamese.png" height="24"
							style="display: inline-block" />
					</s:a>
				</div>

			</div>
		</div>
	</div>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="assets/js/jquery.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/app.js"></script>
	
	<!--switcher html start-->
	<div class="demo_changer" style="right: -145px;">
		<div class="demo-icon"></div>
		<div class="form_holder">
			<div class="predefined_styles">
				<a class="styleswitch" rel="a" href="#"><img alt=""
					src="assets/images/a.jpg"></a> <a class="styleswitch" rel="b"
					href="#"><img alt="" src="assets/images/b.jpg"></a> <a
					class="styleswitch" rel="c" href="#"><img alt=""
					src="assets/images/c.jpg"></a> <a class="styleswitch" rel="d"
					href="#"><img alt="" src="assets/images/d.jpg"></a> <a
					class="styleswitch" rel="e" href="#"><img alt=""
					src="assets/images/e.jpg"></a> <a class="styleswitch" rel="f"
					href="#"><img alt="" src="assets/images/f.jpg"></a> <a
					class="styleswitch" rel="g" href="#"><img alt=""
					src="assets/images/g.jpg"></a> <a class="styleswitch" rel="h"
					href="#"><img alt="" src="assets/images/h.jpg"></a> <a
					class="styleswitch" rel="i" href="#"><img alt=""
					src="assets/images/i.jpg"></a> <a class="styleswitch" rel="j"
					href="#"><img alt="" src="assets/images/j.jpg"></a>
			</div>
		</div>
	</div>

	<!--switcher html end-->
	<script src="assets/switcher/switcher.js"></script>
	<script src="assets/switcher/moderziner.custom.js"></script>
	<link href="assets/switcher/switcher.css" rel="stylesheet">
	<link href="assets/switcher/switcher-defult.css" rel="stylesheet">
	<link rel="alternate stylesheet" type="text/css"
		href="assets/switcher/a.css" title="a" media="all" />
	<link rel="alternate stylesheet" type="text/css"
		href="assets/switcher/b.css" title="b" media="all" />
	<link rel="alternate stylesheet" type="text/css"
		href="assets/switcher/c.css" title="c" media="all" />
	<link rel="alternate stylesheet" type="text/css"
		href="assets/switcher/d.css" title="d" media="all" />
	<link rel="alternate stylesheet" type="text/css"
		href="assets/switcher/e.css" title="e" media="all" />
	<link rel="alternate stylesheet" type="text/css"
		href="assets/switcher/f.css" title="f" media="all" />
	<link rel="alternate stylesheet" type="text/css"
		href="assets/switcher/g.css" title="g" media="all" />
	<link rel="alternate stylesheet" type="text/css"
		href="assets/switcher/h.css" title="h" media="all" />
	<link rel="alternate stylesheet" type="text/css"
		href="assets/switcher/i.css" title="i" media="all" />
	<link rel="alternate stylesheet" type="text/css"
		href="assets/switcher/j.css" title="j" media="all" />
</body>
</html>


