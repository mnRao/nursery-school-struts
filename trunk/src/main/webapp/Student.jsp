<%@page import="com.opensymphony.xwork2.ActionContext"%>
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
					<li
						class="<%=Helper.getTabCss(1, false, ActionContext.getContext()
					.getActionInvocation().getResultCode())%>"
					><a data-toggle="tab" href="#firstTab"><s:text
								name="tab.label.second"
							/></a></li>
					<li
						class="<%=Helper.getTabCss(2, false, ActionContext.getContext()
					.getActionInvocation().getResultCode())%>"
					><a data-toggle="tab" href="#secondTab"><s:text
								name="tab.label.first"
							/></a></li>
				</ul>
				<div class="tab-content" id="myTabContent">

					<div id="firstTab"
						class="tab-pane fade <%=Helper.getTabCss(1, true, ActionContext.getContext()
					.getActionInvocation().getResultCode())%>"
					>
						<table cellpadding="0" cellspacing="0" border="0" class="display"
							id="dynamicTable"
						>
							<thead>
								<tr>
									<th><s:text name="label.student.studentId" /></th>
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
								<s:iterator value="students" var="student">
									<tr class="gradeC">
										<td><s:property value="studentId" /></td>
										<td><s:property value="name" /></td>
										<td><s:date name="dateOfBirth" format="dd/MM/yyyy" /></td>
										<td><s:property value="genderText" /></td>
										<td><s:property value="address" /></td>
										<td><s:property value="homePhone" /></td>
										<td><s:property value="isActiveText" /></td>
										<td class="rightDir"
											original-title="<s:property value="associatedClass.tooltip" />"
										><s:url id="classUrl" action="editClasses">
												<s:param name="classId" value="%{associatedClass.classId}" />
											</s:url> <s:a href="%{classUrl}">
												<s:property value="associatedClass.label" />
											</s:a></td>
										<td><s:url id="editUrl" action="editStudent">
												<s:param name="studentId" value="%{studentId}" />
											</s:url> <s:a cssClass="btn btn-sm btn-primary" href="%{editUrl}">
												<s:text name="grid.action.edit" />
											</s:a> <s:url id="deleteUrl" action="deleteStudent">
												<s:param name="studentId" value="%{studentId}" />
											</s:url> <s:a cssClass="btn btn-sm btn-warning" href="%{deleteUrl}">
												<s:text name="grid.action.delete" />
											</s:a> 
											
											<s:url id="addParentUrl" action="autoSetStudentParent">
												<s:param name="studentId" value="%{studentId}" />
											</s:url> 
											<s:a cssClass="btn btn-sm btn-info" href="%{addParentUrl}">
												<s:text name="grid.action.addParent" />
											</s:a> 
											
											<!-- Enable / Disable student -->
											<s:if test="%{#student.active}">
												<s:url id="disableStudentUrl" action="disableStudent">
													<s:param name="studentId" value="%{studentId}" />
												</s:url> 
												<s:a cssClass="btn btn-sm btn-danger" href="%{disableStudentUrl}"><s:text name="grid.action.disable" /></s:a> 
											</s:if>
											<s:else>
												<s:url id="enableStudentUrl" action="enableStudent">
													<s:param name="studentId" value="%{studentId}" />
												</s:url> 
												<s:a cssClass="btn btn-sm btn-success" href="%{enableStudentUrl}"><s:text name="grid.action.enable" /></s:a>
											</s:else>
											
											<s:a href="#myModal%{studentId}" data-toggle="modal"
												cssClass="btn btn-sm btn-success"
											>
												<s:text name="grid.action.showParent" />
											</s:a> 
											
											<s:div aria-hidden="true" aria-labelledby="myModalLabel"
												role="dialog" tabindex="-1" id="myModal%{studentId}"
												cssClass="modal fade" style="display: none;"
											>
												<div class="modal-dialog">
													<div class="modal-content">
														<div class="modal-header">
															<button aria-hidden="true" data-dismiss="modal"
																class="close" type="button"
															>x</button>
															<h4 class="modal-title">
																<s:text name="modal.title.all.parent" />
															</h4>
														</div>
														<div class="modal-body">

															<%-- 														<s:property value="%{parents.size()}"/> --%>
															<s:if test="%{parents.isEmpty()}">
																<s:text name="modal.content.empty" />
															</s:if>
															<s:else>
																<table class="table table-hover">
																	<thead>
																		<tr>
																			<th>#</th>
																			<th><s:text name="label.parent.name" /></th>
																			<th><s:text name="label.parent.job" /></th>
																			<th><s:text name="label.parent.gender" /></th>
																			<th><s:text name="label.parent.phoneNumber" /></th>
																			<th class="hidden-xs"></th>
																		</tr>
																	</thead>
																	<tbody>
																		<s:iterator value="parents">
																			<tr>
																				<td><s:property value="parentId" /></td>
																				<td><s:property value="name" /></td>
																				<td><s:property value="job" /></td>
																				<td><s:property value="genderText" /></td>
																				<td><s:property value="phoneNumber" /></td>
																				<td class="modal-action"><s:url id="editUrl"
																						action="editParent"
																					>
																						<s:param name="parentId" value="%{parentId}" />
																					</s:url> <s:a cssClass="btn btn-sm btn-primary"
																						href="%{editUrl}"
																					>
																						<s:text name="grid.action.edit" />
																					</s:a> <s:url id="deleteUrl"
																						action="deleteParentMapStudent"
																					>
																						<s:param name="studentId" value="%{studentId}" />
																						<s:param name="parentId" value="%{parentId}" />
																					</s:url> <s:a cssClass="btn btn-sm btn-warning"
																						href="%{deleteUrl}"
																					>
																						<s:text name="grid.action.deleteMapping" />
																					</s:a></td>
																			</tr>
																		</s:iterator>
																	</tbody>
																</table>
															</s:else>
														</div>
														<div class="modal-footer">
															<button type="button" class="btn btn-default"
																data-dismiss="modal"
															>Close</button>
														</div>
													</div>
												</div>
											</s:div></td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
					<div id="secondTab"
						class="tab-pane fade <%=Helper.getTabCss(2, true, ActionContext.getContext()
					.getActionInvocation().getResultCode())%>"
					>
						<s:form action="saveOrUpdateStudent" cssClass="form-horizontal">
							<s:push value="student">
								<fieldset>
									<legend class="section">
										<s:text name="form.legend" />
									</legend>
									
									<div class="control-group">
										<s:fielderror />
										<div class="col-md-2">
											<s:label key="label.student.classId" cssClass="control-label" />
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<s:select list="classList" listKey="classId"
													listValue="label" name="classId"
													headerValue="%{getText('select.class')}" value="%{classId}"
												/>
											</div>
										</div>
	
										
											<s:hidden name="studentId" />
											<s:hidden name="active" />
											<div class="col-md-2">
												<s:label key="label.student.name" cssClass="control-label" />
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<s:textfield key="label.student.name" name="name"
														cssClass="form-control"
													/>
												</div>
											</div>
											<div class="col-md-2">
												<s:label key="label.student.dateOfBirth"
													cssClass="control-label"
												/>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<%-- 																								<sj:datepicker name="dateOfBirth" displayFormat="dd-MMM-yyyy"/> --%>
													<s:textfield key="label.student.dateOfBirth"
														name="dateOfBirth"
														cssClass="form-control parsley-validated"
														value="%{getText('format.date',{student.dateOfBirth})}"
														data-type="dateIso" placeholder="%{getText('global.format.date')}"
													/>
												</div>
											</div>
											<div class="col-md-2">
												<s:label key="label.student.address" cssClass="control-label" />
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<s:textfield key="label.student.address" name="address"
														cssClass="form-control"
													/>
												</div>
											</div>
											<div class="col-md-2">
												<s:label key="label.student.gender" cssClass="control-label" />
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<s:select name="gender" headerKey="-1"
														list="#{0: getText('form.gender.female'), '1': getText('form.gender.male')}"
														value="gender"
													/>
												</div>
											</div>
											<div class="col-md-2">
												<s:label key="label.student.homePhone"
													cssClass="control-label"
												/>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<s:textfield key="label.student.homePhone" name="homePhone"
														cssClass="form-control"
													/>
												</div>
											</div>
											<div class="col-md-2">
												<s:label key="label.student.description"
													cssClass="control-label"
												/>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<s:textarea key="label.student.description"
														name="description" cssClass="form-control"
													/>
												</div>
											</div>
										
									</div>
								</fieldset>
								<div class="form-actions">
									<div>
										<s:submit key="form.submit" cssClass="btn btn-primary" />

										<!-- If is update action -->										
										<s:if test="%{studentId != 0}">
											<s:url id="deleteUrl" action="deleteStudent">
												<s:param name="studentId" value="%{studentId}" />
											</s:url>
											<s:a cssClass="btn btn-sm btn-warning" href="%{deleteUrl}">
												<s:text name="grid.action.delete" />
											</s:a>
											<!-- Enable / Disable student -->
											<s:if test="%{active}">
												<s:url id="disableStudentUrl" action="disableStudent">
													<s:param name="studentId" value="%{studentId}" />
												</s:url> 
												<s:a cssClass="btn btn-sm btn-danger" href="%{disableStudentUrl}"><s:text name="grid.action.disable" /></s:a> 
											</s:if>
											<s:else>
												<s:url id="enableStudentUrl" action="enableStudent">
													<s:param name="studentId" value="%{studentId}" />
												</s:url> 
												<s:a cssClass="btn btn-sm btn-success" href="%{enableStudentUrl}"><s:text name="grid.action.enable" /></s:a>
											</s:else>
										</s:if>
									</div>
								</div>
							</s:push>
						</s:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
