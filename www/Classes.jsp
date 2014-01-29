<%@page import="com.duke.nurseryschool.helper.Helper"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="row">
	<div class="col-lg-12">
		<div class="widget">
			<div class="widget-header">
				<i class="icon-align-left"></i>
				<h3>
					<s:text name="form.widget.header" />
				</h3>
			</div>
			<div class="widget-content">
				<ul class="nav nav-tabs" id="myTab">
					<li class="<%=Helper.getTabCss(1, false)%>"><a
						data-toggle="tab" href="#firstTab"><s:text
								name="tab.label.second" /></a></li>
					<li class="<%=Helper.getTabCss(2, false)%>"><a
						data-toggle="tab" href="#secondTab"><s:text
								name="tab.label.first" /></a></li>
				</ul>
				<div class="tab-content" id="myTabContent">

					<div id="firstTab"
						class="tab-pane fade <%=Helper.getTabCss(1, true)%>">
						<table cellpadding="0" cellspacing="0" border="0" class="display"
							id="dynamicTable">
							<thead>
								<tr>
									<th><s:text name="label.classes.classesId" /></th>
									<th><s:text name="label.classes.code" /></th>
									<th><s:text name="label.classes.courseId" /></th>
									<th><s:text name="label.classes.currentName" /></th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="allClasses">
									<tr class="gradeC">
										<td><s:property value="classId" /></td>
										<td><s:property value="code" /></td>
										<td><s:property value="course.label" /></td>
										<td><s:property value="currentName" /></td>
										<td><s:url id="editUrl" action="editClasses">
												<s:param name="classId" value="%{classId}" />
											</s:url> <s:a cssClass="btn btn-sm btn-primary" href="%{editUrl}">Edit</s:a>
											<s:url id="deleteUrl" action="deleteClasses">
												<s:param name="classId" value="%{classId}" />
											</s:url> <s:a cssClass="btn btn-sm btn-warning" href="%{deleteUrl}">Delete</s:a>
											<s:url id="addFeePolicyUrl" action="autoSetClassFeePolicy">
												<s:param name="classId" value="%{classId}" />
											</s:url> <s:a cssClass="btn btn-sm btn-info" href="%{addFeePolicyUrl}">Add Fee Policy</s:a>
											<s:url id="addFeeDetailsUrl" action="autoSetClassFeeDetails">
												<s:param name="classId" value="%{classId}" />
											</s:url> <s:a cssClass="btn btn-sm btn-info" href="%{addFeeDetailsUrl}">Add Fee Details</s:a>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>

					<div id="secondTab"
						class="tab-pane fade <%=Helper.getTabCss(2, true)%>">
						<s:form action="saveOrUpdateClasses" cssClass="form-horizontal">
							<fieldset>
								<legend class="section">
									<s:text name="form.legend" />
								</legend>
								<div class="control-group">
									<s:push value="classes">
										<s:hidden name="classId" />
										<div class="col-md-2">
											<s:label key="label.classes.courseId"
												cssClass="control-label" />
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<s:select list="courseList" listKey="courseId"
													listValue="label" name="courseId" headerKey="-1"
													headerValue="%{getText('select.course')}"
													value="%{courseId}" />
											</div>
										</div>
										<div class="col-md-2">
											<s:label key="label.classes.code" cssClass="control-label" />
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<s:textfield key="label.classes.code" name="code"
													cssClass="form-control" />
											</div>
										</div>
									</s:push>
								</div>
							</fieldset>
							<div class="form-actions">
								<div>
									<s:submit cssClass="btn btn-primary" />
								</div>
							</div>
						</s:form>
					</div>

				</div>
			</div>
		</div>
	</div>
</div>
