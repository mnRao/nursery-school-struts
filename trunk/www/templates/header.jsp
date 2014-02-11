<%@page import="com.duke.nurseryschool.helper.BusinessLogicSolver"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<div class="top-navbar header b-b">
	<a data-original-title="Toggle navigation"
		class="toggle-side-nav pull-left" href="#"
	><i class="icon-reorder"></i> </a>
	<div class="brand pull-left">
		<s:url id="homeUrl" namespace="/" action="login" />
		<s:a href="%{homeUrl}">
			<img src="assets/images/logo.png" width="147" height="33">
		</s:a>
	</div>
	<ul class="nav navbar-nav navbar-right  hidden-xs">
		<li class="dropdown hidden-xs"><a href="#" /><i
			class="icon-cogs"
		> </i> <s:text name="ui.header.currentYear" /> <%=BusinessLogicSolver.calculateCurrentAcademicYear()%>
			</a></li>
		<!-- 	Localization -->
		<s:url id="localeEN" namespace="/" action="locale">
			<s:param name="request_locale">en</s:param>
		</s:url>
		<s:url id="localeVI" namespace="/" action="locale">
			<s:param name="request_locale">vi_VN</s:param>
		</s:url>
		<li class="dropdown user  hidden-xs"><a data-toggle="dropdown"
			class="dropdown-toggle" href="#"
		> <i class="icon-flag"></i> <span class="username"><s:text
						name="ui.header.language"
					/></span> <i class="icon-caret-down small"></i>
		</a>
			<ul class="dropdown-menu">
				<li><s:a href="%{localeEN}">
						<img src="assets/app/English.png" height="24"
							style="display: inline-block"
						/>
						<s:text name="ui.header.language.english" />
					</s:a></li>
				<li><s:a href="%{localeVI}">
						<img src="assets/app/Vietnamese.png" height="24"
							style="display: inline-block"
						/>
						<s:text name="ui.header.language.vietnamese" />
					</s:a></li>
			</ul></li>
		<%-- 		<s:property value="#session.USER" /> --%>
		<!-- 		Logout -->
		<li class="dropdown user  hidden-xs"><a
			href="<s:url action="logoutAuth" />"
		><i class="icon-signout"></i> <s:text name="ui.header.logout" /></a></li>
	</ul>
	<!-- 	<form class="pull-right"> -->
	<!-- 		<input type="search" placeholder="Search..." class="search" -->
	<!-- 			id="search-input"> -->
	<!-- 	</form> -->
</div>
