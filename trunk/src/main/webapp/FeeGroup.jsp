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
									<th><s:text name="label.feeGroup.feeGroupId" /></th>
									<th><s:text name="label.feeGroup.name" /></th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="feeGroups">
									<tr class="gradeC">
										<td><s:property value="feeGroupId" /></td>
										<td><s:property value="name" /></td>
										<td><s:url id="editUrl" action="editFeeGroup">
												<s:param name="feeGroupId" value="%{feeGroupId}" />
											</s:url> <s:a cssClass="btn btn-sm btn-primary" href="%{editUrl}">
												<s:text name="grid.action.edit" />
											</s:a> <s:url id="deleteUrl" action="deleteFeeGroup">
												<s:param name="feeGroupId" value="%{feeGroupId}" />
											</s:url> <s:a cssClass="btn btn-sm btn-warning" href="%{deleteUrl}">
												<s:text name="grid.action.delete" />
											</s:a> <!-- 											Show Fees --> <s:a
												href="#myModal%{feeGroupId}" data-toggle="modal"
												cssClass="btn btn-sm btn-success"
											>
												<s:text name="grid.action.showFee" />
											</s:a> <s:div aria-hidden="true" aria-labelledby="myModalLabel"
												role="dialog" tabindex="-1" id="myModal%{feeGroupId}"
												cssClass="modal fade" style="display: none;"
											>
												<div class="modal-dialog">
													<div class="modal-content">
														<div class="modal-header">
															<button aria-hidden="true" data-dismiss="modal"
																class="close" type="button"
															>x</button>
															<h4 class="modal-title">
																<s:text name="modal.title.all.fee" />
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
																			<th><s:text name="label.fee.name" /></th>
																			<th><s:text name="label.fee.type" /></th>
																			<th><s:text name="label.fee.feeGroupId" /></th>
																			<th class="hidden-xs"></th>
																		</tr>
																	</thead>
																	<tbody>
																		<s:iterator value="fees">
																			<tr>
																				<td><s:property value="name" /></td>
																				<td><s:property value="type.name" /></td>
																				<td><s:property value="feeGroup.label" /></td>
																				<td class="modal-action"><s:url id="editUrl"
																						action="editFee"
																					>
																						<s:param name="feeId" value="%{feeId}" />
																					</s:url> <s:a cssClass="btn btn-sm btn-primary"
																						href="%{editUrl}"
																					>
																						<s:text name="grid.action.edit" />
																					</s:a> <s:url id="deleteUrl"
																						action="deleteFeeMapFeeGroup"
																					>
																						<s:param name="feeId" value="%{feeId}" />
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
						<s:form action="saveOrUpdateFeeGroup" cssClass="form-horizontal">
							<fieldset>
								<legend class="section">
									<s:text name="form.legend" />
								</legend>
								<div class="control-group">
									<s:fielderror />
									<s:push value="feeGroup">
										<s:hidden name="feeGroupId" />
										<div class="col-md-2">
											<s:label key="label.feeGroup" cssClass="control-label" />
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<s:textfield key="label.feeGroup" name="name"
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
