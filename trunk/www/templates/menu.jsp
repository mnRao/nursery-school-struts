<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<div id="side-nav">
	<ul id="nav">
		<li class="current"><a href="<s:url action="dashboard"/>"><i
				class="icon-dashboard"></i> Dashboard </a></li>
		<li><a href='<s:url action="listStudents"/>'><i
				class="icon-user"></i> <s:text name="label.student" /></a></li>
		<li><a href='<s:url action="listMonths"/>'> <i
				class="icon-table"></i>
			<s:text name="label.month.month" /></a></li>
		<li><a href='<s:url action="listSubject"/>'> <i
				class="icon-table"></i>
			<s:text name="label.subject" /></a></li>
		<li><a href='<s:url action="listCourse"/>'> <i
				class="icon-table"></i>
			<s:text name="label.course" /></a></li>	
		<li><a href='<s:url action="listClasses"/>'> <i
				class="icon-table"></i>
			<s:text name="label.classes" /></a></li>	
		<li><a href='<s:url action="listParent"/>'> <i
				class="icon-table"></i>
			<s:text name="label.parent" /></a></li>	
		<li><a href='<s:url action="listFeePolicy"/>'> <i
			class="icon-table"></i>
		<s:text name="label.feePolicy" /></a></li>	
		<li><a href='<s:url action="listPayment"/>'> <i
			class="icon-table"></i>
		<s:text name="label.payment" /></a></li>	
		<li><a href='<s:url action="listExtraFeeType"/>'> <i
			class="icon-table"></i>
		<s:text name="label.extraFeeType" /></a></li>
		<li><a href='<s:url action="listFeeDetails"/>'> <i
			class="icon-table"></i>
		<s:text name="label.feeDetails" /></a></li>
			
			
		<li><a href="#"> <i class="icon-desktop"></i> UI Features <span
				class="label label-info pull-right">7</span> <i
				class="arrow icon-angle-left"></i></a>
			<ul class="sub-menu">
				<li><a href="general.html"> <i class="icon-angle-right"></i>
						General
				</a></li>
				<li><a href="buttons.html"> <i class="icon-angle-right"></i>
						Buttons
				</a></li>
				<li><a href="tabs.html"> <i class="icon-angle-right"></i>
						Tabs
				</a></li>
			</ul></li>
		<li><a href="#"> <i class="icon-edit"></i> Forms <span
				class="label label-info pull-right">4</span> <i
				class="arrow icon-angle-left"></i></a>
			<ul class="sub-menu">
				<li><a href="form_elements.html"> <i
						class="icon-angle-right"></i> Form Elements
				</a></li>
				<li><a href="form_validation.html"> <i
						class="icon-angle-right"></i> Form Validation
				</a></li>
				<li><a href="form_masks.html"> <i class="icon-angle-right"></i>
						Form Masks
				</a></li>
				<li><a href="wizard.html"> <i class="icon-angle-right"></i>
						Form Wizard
				</a></li>
				<li><a href="multipleFile_upload.html"> <i
						class="icon-angle-right"></i> Multiple File Upload
				</a></li>
				<li><a href="dropzone_upload.html"> <i
						class="icon-angle-right"></i> Dropzone File Upload
				</a></li>
			</ul></li>
		<li><a href="#"> <i class="icon-table"></i> Tables <span
				class="label label-info pull-right">2</span> <i
				class="arrow icon-angle-left"></i></a>
			<ul class="sub-menu">
				<li><a href="static_table.html"> <i
						class="icon-angle-right"></i> Static
				</a></li>
				<li><a href="dynamic_table.html"> <i
						class="icon-angle-right"></i> Dynamic
				</a></li>
			</ul></li>
	</ul>
</div>