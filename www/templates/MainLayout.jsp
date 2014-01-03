<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html>
<html>
<head>
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="assets/css/bootstrap.css" rel="stylesheet" media="screen">
<link href="assets/css/thin-admin.css" rel="stylesheet" media="screen">
<link href="assets/css/font-awesome.css" rel="stylesheet" media="screen">
<link href="assets/style/style.css" rel="stylesheet">
<link href="assets/style/dashboard.css" rel="stylesheet">
<link href="assets/jquery-easy-pie-chart/jquery.easy-pie-chart.css"
	rel="stylesheet" type="text/css" media="screen" />
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="../../assets/js/html5shiv.js"></script>
      <script src="../../assets/js/respond.min.js"></script>
    <![endif]-->
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

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="assets/js/jquery.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="assets/js/smooth-sliding-menu.js"></script>
	<script class="include" type="text/javascript"
		src="assets/javascript/jquery191.min.js"></script>
	<script class="include" type="text/javascript"
		src="assets/javascript/jquery.jqplot.min.js"></script>

	<script src="assets/sparkline/jquery.sparkline.js"
		type="text/javascript"></script>
	<script src="assets/sparkline/jquery.customSelect.min.js"></script>
	<script src="assets/sparkline/sparkline-chart.js"></script>
	<script src="assets/sparkline/easy-pie-chart.js"></script>

	<!--switcher html start-->
	<div class="demo_changer active" style="right: 0px;">
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
