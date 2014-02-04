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
									<th><s:text name="label.parent.parentId" /></th>
									<th><s:text name="label.parent.name" /></th>
									<th><s:text name="label.parent.gender" /></th>
									<th><s:text name="label.parent.job" /></th>
									<th><s:text name="label.parent.phoneNumber" /></th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="parents">
									<tr class="gradeC">
										<td><s:property value="parentId" /></td>
										<td><s:property value="name" /></td>
										<td><s:property value="gender" /></td>
										<td><s:property value="job" /></td>
										<td><s:property value="phoneNumber" /></td>
										<td><s:url id="editUrl" action="editParent">
												<s:param name="parentId" value="%{parentId}" />
											</s:url> <s:a cssClass="btn btn-sm btn-primary" href="%{editUrl}">Edit</s:a>
											<s:url id="deleteUrl" action="deleteParent">
												<s:param name="parentId" value="%{parentId}" />
											</s:url> <s:a cssClass="btn btn-sm btn-warning" href="%{deleteUrl}">Delete</s:a>

										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>

					<div id="secondTab"
						class="tab-pane fade <%=Helper.getTabCss(2, true)%>">
						<s:form action="saveOrUpdateParent" cssClass="form-horizontal">
							<fieldset>
								<legend class="section">
									<s:text name="form.legend" />
								</legend>
								<div class="control-group">
									<!-- Mapping fields -->
									<s:set var="changingMappingKeyAllowed"><%=Helper.changingMappingKeyAllowed()%></s:set>
									<s:if test="%{#changingMappingKeyAllowed}">
										<s:set name="isReadOnly" value="false" />
									</s:if>
									<s:else>
										<s:set name="isReadOnly" value="true" />
										<s:hidden name="studentId" />
									</s:else>
									<div class="col-md-2">
										<s:label key="label.parent.studentId" cssClass="control-label" />
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<s:select list="studentList" listKey="studentId"
												listValue="label" name="studentId" headerKey="-1"
												headerValue="%{getText('select.student')}"
												value="%{studentId}" disabled="%{isReadOnly}" />
										</div>
									</div>

									<s:push value="parent">
										<s:hidden name="parentId" />


										<div class="col-md-2">
											<s:label key="label.parent.name" cssClass="control-label" />
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<s:textfield key="label.parent.name" name="name"
													cssClass="form-control" />
											</div>
										</div>
										<div class="col-md-2">
											<s:label key="label.parent.gender" cssClass="control-label" />
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<s:select name="gender" headerKey="-1"
													list="#{0: getText('form.gender.female'), '1': getText('form.gender.male')}"
													value="gender" />
											</div>
										</div>
										<div class="col-md-2">
											<s:label key="label.parent.job" cssClass="control-label" />
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<s:textfield key="label.parent.job" name="job"
													cssClass="form-control" />
											</div>
										</div>
										<div class="col-md-2">
											<s:label key="label.parent.phoneNumber"
												cssClass="control-label" />
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<s:textfield key="label.parent.phoneNumber"
													name="phoneNumber" cssClass="form-control" />
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
