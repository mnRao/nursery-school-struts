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
									<th><s:text name="label.student.name" /></th>
									<th><s:text name="label.student.dateOfBirth" /></th>
									<th><s:text name="label.student.gender" /></th>
									<th><s:text name="label.student.address" /></th>
									<th><s:text name="label.student.homePhone" /></th>
									<th><s:text name="label.student.isActive" /></th>
									<th><s:text name="label.student.classId" /></th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="students">
									<tr class="gradeC">
										<td><s:property value="name" /></td>
										<td><s:property value="dateOfBirth" /></td>
										<td><s:property value="gender" /></td>
										<td><s:property value="address" /></td>
										<td><s:property value="homePhone" /></td>
										<td><s:property value="isActive" /></td>
										<td><s:property value="associatedClass.classId" /></td>
										<td><s:url id="editUrl" action="editStudent">
												<s:param name="studentId" value="%{studentId}" />
											</s:url> <s:a cssClass="btn btn-sm btn-primary" href="%{editUrl}">Edit</s:a>
											<s:url id="deleteUrl" action="deleteStudent">
												<s:param name="studentId" value="%{studentId}" />
											</s:url> <s:a cssClass="btn btn-sm btn-warning" href="%{deleteUrl}">Delete</s:a>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
					<div id="secondTab"
						class="tab-pane fade <%=Helper.getTabCss(2, true)%>">
						<s:form action="saveOrUpdateStudent" cssClass="form-horizontal">
							<fieldset>
								<legend class="section">
									<s:text name="form.legend" />
								</legend>

								<div class="control-group">
									<div class="col-md-2">
											<s:label key="label.student.classId" cssClass="control-label" />
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<s:textfield key="label.student.classId" name="classId"
													cssClass="form-control" />
											</div>
										</div>
								
									<s:push value="student">
										<s:hidden name="studentId"/>
										<div class="col-md-2">
											<s:label key="label.student.name" cssClass="control-label" />
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<s:textfield key="label.student.name" name="name"
													cssClass="form-control" />
											</div>
										</div>
										<div class="col-md-2">
											<s:label key="label.student.dateOfBirth"
												cssClass="control-label" />
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<s:textfield key="label.student.dateOfBirth"
													name="dateOfBirth" cssClass="form-control" />
											</div>
										</div>
										<div class="col-md-2">
											<s:label key="label.student.gender" cssClass="control-label" />
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<s:textfield key="label.student.gender" name="gender"
													cssClass="form-control" />
											</div>
										</div>
										<div class="col-md-2">
											<s:label key="label.student.address" cssClass="control-label" />
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<s:textfield key="label.student.address" name="address"
													cssClass="form-control" />
											</div>
										</div>
										<div class="col-md-2">
											<s:label key="label.student.homePhone"
												cssClass="control-label" />
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<s:textfield key="label.student.homePhone" name="homePhone"
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
