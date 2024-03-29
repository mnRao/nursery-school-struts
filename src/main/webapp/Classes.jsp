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
										<td class="rightDir"
											original-title="<s:property value="course.tooltip"/>"
										><s:url id="courseUrl" action="editCourse">
												<s:param name="courseId" value="%{course.courseId}" />
											</s:url> <s:a href="%{courseUrl}">
												<s:property value="course.label" />
											</s:a></td>
										<td><s:property value="currentName" /></td>
										<td><s:url id="editUrl" action="editClasses">
												<s:param name="classId" value="%{classId}" />
											</s:url> <s:a cssClass="btn btn-sm btn-primary" href="%{editUrl}">
												<s:text name="grid.action.edit" />
											</s:a> <s:url id="deleteUrl" action="deleteClasses">
												<s:param name="classId" value="%{classId}" />
											</s:url> <s:a cssClass="btn btn-sm btn-warning" href="%{deleteUrl}">
												<s:text name="grid.action.delete" />
											</s:a> <s:url id="addFeePolicyUrl" action="autoSetClassFeePolicy">
												<s:param name="classId" value="%{classId}" />
											</s:url> <s:a cssClass="btn btn-sm btn-info"
												href="%{addFeePolicyUrl}"
											>
												<s:text name="grid.action.addFeePolicy" />
											</s:a> <!-- 											Show Fee Policies --> <s:a
												href="#myModal%{classId}" data-toggle="modal"
												cssClass="btn btn-sm btn-success"
											>
												<s:text name="grid.action.showFeePolicy" />
											</s:a> <s:div aria-hidden="true" aria-labelledby="myModalLabel"
												role="dialog" tabindex="-1" id="myModal%{classId}"
												cssClass="modal fade" style="display: none;"
											>
												<div class="modal-dialog">
													<div class="modal-content">
														<div class="modal-header">
															<button aria-hidden="true" data-dismiss="modal"
																class="close" type="button"
															>x</button>
															<h4 class="modal-title">
																<s:text name="modal.title.all.feePolicy" />
															</h4>
														</div>
														<div class="modal-body">

															<s:if test="%{feePolicies.isEmpty()}">
																<s:text name="modal.content.empty" />
															</s:if>
															<s:else>
																<table class="table table-hover">
																	<thead>
																		<tr>
																			<th><s:text name="label.feePolicy.classId" /></th>
																			<th><s:text name="label.feePolicy.monthId" /></th>
																			<th><s:text
																					name="label.feePolicy.feePerNormalMeal"
																				/></th>
																			<th><s:text
																					name="label.feePolicy.totalBreakfastFee"
																				/></th>
																			<th><s:text
																					name="label.feePolicy.penaltyFeePerBreakfast"
																				/></th>
																			<th><s:text name="label.feePolicy.availableDays" /></th>
																			<th class="hidden-xs"></th>
																		</tr>
																	</thead>
																	<tbody>
																		<s:iterator value="feePolicies">
																			<tr>
																				<td><s:property value="associatedClass.label" /></td>
																				<td><s:property value="month.label" /></td>
																				<td><s:property value="feePerNormalMeal" /></td>
																				<td><s:property value="totalBreakfastFee" /></td>
																				<td><s:property value="penaltyFeePerBreakfast" /></td>
																				<td><s:property value="availableDays" /></td>
																				<td class="modal-action"><s:url id="editUrl"
																						action="editFeePolicy"
																					>
																						<s:param name="feePolicyId" value="%{feePolicyId}" />
																					</s:url> <s:a cssClass="btn btn-sm btn-primary"
																						href="%{editUrl}"
																					>
																						<s:text name="grid.action.edit" />
																					</s:a> <s:url id="deleteUrl"
																						action="deleteFeePolicyMapClasses"
																					>
																						<s:param name="feePolicyId" value="%{feePolicyId}" />
																					</s:url> <s:a cssClass="btn btn-sm btn-warning"
																						href="%{deleteUrl}"
																					>
																						<s:text name="grid.action.delete" />
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
						<s:form action="saveOrUpdateClasses" cssClass="form-horizontal">
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
										<s:hidden name="courseId" />
									</s:else>

									<s:fielderror />
									<s:push value="classes">
										<s:hidden name="classId" />
										<div class="col-md-2">
											<s:label key="label.classes.courseId"
												cssClass="control-label"
											/>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<s:select list="courseList" listKey="courseId"
													listValue="label" name="courseId"
													headerValue="%{getText('select.course')}"
													value="%{courseId}" disabled="%{isReadOnly}"
												/>
											</div>
										</div>
										<div class="col-md-2">
											<s:label key="label.classes.code" cssClass="control-label" />
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<s:textfield key="label.classes.code" name="code"
													cssClass="form-control"
												/>
											</div>
										</div>
									</s:push>
								</div>
							</fieldset>
							<div class="form-actions">
								<div>
									<s:submit key="form.submit" cssClass="btn btn-primary" />
								</div>
							</div>
						</s:form>
					</div>

				</div>
			</div>
		</div>
	</div>
</div>
