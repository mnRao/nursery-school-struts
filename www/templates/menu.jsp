<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<div id="side-nav">
	<ul id="nav">
		<li class="current"><a href="<s:url action="dashboard"/>"><i
				class="icon-dashboard"></i> Dashboard </a></li>
		<li><a href='<s:url action="listCourse"/>'> <i
				class="icon-table"></i> <s:text name="label.course" /></a></li>
		<li><a href='<s:url action="listClasses"/>'> <i
				class="icon-table"></i> <s:text name="label.classes" /></a></li>
		<li><a href='<s:url action="listStudent"/>'><i
				class="icon-user"></i> <s:text name="label.student" /></a></li>
		<li><a href='<s:url action="listParent"/>'> <i
				class="icon-table"></i> <s:text name="label.parent" /></a></li>



		<li><a href="#"> <i class="icon-desktop"></i>$$$<span
				class="label label-info pull-right">4</span> <i
				class="arrow icon-angle-left"></i></a>
			<ul class="sub-menu">
				<li><a href='<s:url action="listSubject"/>'> <i
						class="icon-table"></i> <s:text name="label.subject" /></a></li>
				<li><a href='<s:url action="listExtraFeeType"/>'> <i
						class="icon-table"></i> <s:text name="label.extraFeeType" /></a></li>
				<li><a href='<s:url action="listMonth"/>'> <i
						class="icon-table"></i> <s:text name="label.month.month" /></a></li>
				<li><a href='<s:url action="listFeePolicy"/>'> <i
						class="icon-table"></i> <s:text name="label.feePolicy" /></a></li>
				<li><a href='<s:url action="listFeeDetails"/>'> <i
						class="icon-table"></i> <s:text name="label.feeDetails" /></a></li>
				<li><a href='<s:url action="listExtraFeeMap"/>'> <i
						class="icon-table"></i> <s:text name="label.extraFeeMap" /></a></li>
				<li><a href='<s:url action="listSubjectFeeMap"/>'> <i
						class="icon-table"></i> <s:text name="label.subjectFeeMap" /></a></li>
				<li><a href='<s:url action="listPayment"/>'> <i
						class="icon-table"></i> <s:text name="label.payment" /></a></li>
			</ul></li>
	</ul>
</div>