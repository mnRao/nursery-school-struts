<%@page import="com.duke.nurseryschool.helper.Constant"%>
<%@page import="com.duke.nurseryschool.helper.Helper"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<div id="side-nav">
	<ul id="nav">
		<li class="<%=Helper.isMenuItemActive(Constant.MENU_ITEM.DASHBOARD)%>"><a
			href="<s:url action="Dashboard"/>"><i class="icon-dashboard"></i>
				<s:text
					name="headTitle.dashboard" /> </a></li>
		<li class="<%=Helper.isMenuItemActive(Constant.MENU_ITEM.COURSE)%>"><a
			href='<s:url action="listCourse"/>'> <i class="icon-time"></i> <s:text
					name="label.course" /></a></li>
		<li class="<%=Helper.isMenuItemActive(Constant.MENU_ITEM.CLASS)%>"><a
			href='<s:url action="listClasses"/>'> <i class="icon-home"></i> <s:text
					name="label.classes" /></a></li>
		<li class="<%=Helper.isMenuItemActive(Constant.MENU_ITEM.STUDENT)%>"><a
			href='<s:url action="listStudent"/>'><i class="icon-user"></i> <s:text
					name="label.student" /></a></li>
		<li class="<%=Helper.isMenuItemActive(Constant.MENU_ITEM.PARENT)%>"><a
			href='<s:url action="listParent"/>'> <i class="icon-user-md"></i>
				<s:text name="label.parent" /></a></li>



		<li><a href="#"> <i class="icon-key"></i>$$$<span
				class="label label-info pull-right">7</span> <i
				class="arrow icon-angle-left"></i></a>
			<ul class="sub-menu">
				<li class="<%=Helper.isMenuItemActive(Constant.MENU_ITEM.MONTH)%>"><a
					href='<s:url action="listMonth"/>'> <i class="icon-calendar"></i>
						<s:text name="label.month.month" /></a></li>
				<li
					class="<%=Helper.isMenuItemActive(Constant.MENU_ITEM.FEE_POLICY)%>"><a
					href='<s:url action="listFeePolicy"/>'> <i class="icon-book"></i>
						<s:text name="label.feePolicy" /></a></li>
				<li
					class="<%=Helper.isMenuItemActive(Constant.MENU_ITEM.FEE_GROUP)%>"><a
					href='<s:url action="listFeeGroup"/>'> <i
						class="icon-plus-sign"></i> <s:text name="label.feeGroup" /></a></li>
				<li class="<%=Helper.isMenuItemActive(Constant.MENU_ITEM.FEE)%>"><a
					href='<s:url action="listFee"/>'> <i class="icon-book"></i> <s:text
							name="label.fee" /></a></li>
				<li
					class="<%=Helper.isMenuItemActive(Constant.MENU_ITEM.EXTRA_FEE_MAP)%>"><a
					href='<s:url action="listFeeMap"/>'> <i
						class="icon-certificate"></i> <s:text name="label.feeMap" /></a></li>
				<li class="<%=Helper.isMenuItemActive(Constant.MENU_ITEM.PAYMENT)%>"><a
					href='<s:url action="listPayment"/>'> <i class="icon-money"></i>
						<s:text name="label.payment" /></a></li>
				<li
					class="<%=Helper
					.isMenuItemActive(Constant.MENU_ITEM.ALTERNATIVE_FEE_MAP)%>"><a
					href='<s:url action="listAlternativeFeeChargeMap"/>'> <i
						class="icon-briefcase"></i> <s:text name="label.alternativeFeeMap" /></a></li>
			</ul></li>



	</ul>
</div>