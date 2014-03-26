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
									<th><s:text name="label.feeMap.feePolicyId" /></th>
									<th><s:text name="label.feeMap.feeId" /></th>
									<th><s:text name="label.feeMap.amount" /></th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="feeMaps">
									<tr class="gradeC">
										<td class="rightDir"
											original-title="<s:property value="feePolicyFee.feePolicy.tooltip" />"
										><s:url id="feePolicyUrl" action="editFeePolicy">
												<s:param name="feePolicyId"
													value="%{feePolicyFee.feePolicy.feePolicyId}"
												/>
											</s:url> <s:a href="%{feePolicyUrl}">
												<s:property value="feePolicyFee.feePolicy.label" />
											</s:a></td>
										<td class="rightDir"
											original-title="<s:property value="feePolicyFee.fee.tooltip" />"
										><s:url id="feeUrl" action="editFee">
												<s:param name="feeId" value="%{feePolicyFee.fee.feeId}" />
											</s:url> <s:a href="%{feeUrl}">
												<s:property value="feePolicyFee.fee.name" />
											</s:a></td>
										<td><s:property value="amount" /></td>
										<td><s:url id="editUrl" action="editFeeMap">
												<s:param name="feeId" value="%{feePolicyFee.fee.feeId}" />
												<s:param name="feePolicyId"
													value="%{feePolicyFee.feePolicy.feePolicyId}"
												/>
											</s:url> <s:a cssClass="btn btn-sm btn-primary" href="%{editUrl}">
												<s:text name="grid.action.edit" />
											</s:a> <s:url id="deleteUrl" action="deleteFeeMap">
												<s:param name="feeId" value="%{feePolicyFee.fee.feeId}" />
												<s:param name="feePolicyId"
													value="%{feePolicyFee.feePolicy.feePolicyId}"
												/>
											</s:url> <s:a cssClass="btn btn-sm btn-warning" href="%{deleteUrl}">
												<s:text name="grid.action.delete" />
											</s:a></td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>

					<div id="secondTab"
						class="tab-pane fade <%=Helper.getTabCss(2, true, ActionContext.getContext()
					.getActionInvocation().getResultCode())%>"
					>
						<s:form action="saveOrUpdateFeeMap" cssClass="form-horizontal">
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
										<s:hidden name="feeId" />
										<s:hidden name="feePolicyId" />
									</s:else>

									<s:fielderror />
									<div class="col-md-2">
										<s:label key="label.fee" cssClass="control-label" />
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<s:select list="feeList" listKey="feeId" listValue="label"
												name="feeId" headerValue="%{getText('select.fee')}"
												value="%{feeId}" disabled="%{isReadOnly}"
											/>
										</div>
									</div>
									<div class="col-md-2">
										<s:label key="label.feeMap.feePolicyId"
											cssClass="control-label"
										/>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<s:select list="feePolicyList" listKey="feePolicyId"
												listValue="label" name="feePolicyId"
												headerValue="%{getText('select.feePolicy')}"
												value="%{feePolicyId}" disabled="%{isReadOnly}"
											/>
										</div>
									</div>

									<s:push value="feeMap">
										<div class="col-md-2">
											<s:label key="label.feeMap.amount" cssClass="control-label" />
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<s:textfield key="label.feeMap.amount" name="amount"
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
