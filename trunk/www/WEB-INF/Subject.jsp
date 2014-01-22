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
									<th><s:text name="label.subject.subjectId" /></th>
									<th><s:text name="label.subject.name" /></th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="subjects">
									<tr class="gradeC">
										<td><s:property value="subjectId" /></td>
										<td><s:property value="name" /></td>
										<td>
										   <s:url id="editUrl"
										      action="editSubject">
										      <s:param name="subjectId" value="%{subjectId}" />
										   </s:url>
										   <s:a cssClass="btn btn-sm btn-primary" href="%{editUrl}">Edit</s:a>
										   <s:url id="deleteUrl" action="deleteSubject">
										      <s:param name="subjectId" value="%{subjectId}" />
										   </s:url>
										   <s:a cssClass="btn btn-sm btn-warning" href="%{deleteUrl}">Delete</s:a>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
					
					<div id="secondTab"
						class="tab-pane fade <%=Helper.getTabCss(2, true)%>">
						<s:form action="saveOrUpdateSubject" cssClass="form-horizontal">
							<fieldset>
								<legend class="section">
									<s:text name="form.legend" />
								</legend>
								<div class="control-group">
									<s:push value="subject">
										<s:hidden name="subjectId" />
										<div class="col-md-2">
											<s:label key="label.subject" cssClass="control-label" />
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<s:textfield key="label.subject" name="name"
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
