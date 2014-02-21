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
									<th><s:text name="label.alternativeFeeMap.paymentId" /></th>
									<th><s:text name="label.alternativeFeeMap.feeId" /></th>
									<th><s:text
											name="label.alternativeFeeMap.alternativeAmount"
										/></th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="alternativeFeeMaps">
									<tr class="gradeC">
										<td><s:property value="paymentFee.payment.label" /></td>
										<td><s:property value="paymentFee.fee.name" /></td>
										<td><s:property value="alternativeAmount" /></td>
										<td><s:url id="editUrl"
												action="editAlternativeFeeChargeMap"
											>
												<s:param name="feeId" value="%{paymentFee.fee.feeId}" />
												<s:param name="paymentId"
													value="%{paymentFee.payment.paymentId}"
												/>
											</s:url> <s:a cssClass="btn btn-sm btn-primary" href="%{editUrl}">
												<s:text name="grid.action.edit" />
											</s:a> <s:url id="deleteUrl" action="deleteAlternativeFeeChargeMap">
												<s:param name="feeId" value="%{paymentFee.fee.feeId}" />
												<s:param name="paymentId"
													value="%{paymentFee.payment.paymentId}"
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
						<s:form action="saveOrUpdateAlternativeFeeChargeMap"
							cssClass="form-horizontal"
						>
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
										<s:hidden name="paymentId" />
									</s:else>

									<s:fielderror />
									<div class="col-md-2">
										<s:label key="label.fee" cssClass="control-label" />
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<s:select list="feeList" listKey="feeId" listValue="name"
												name="feeId" headerValue="%{getText('select.fee')}"
												value="%{feeId}" disabled="%{isReadOnly}"
											/>
										</div>
									</div>
									<div class="col-md-2">
										<s:label key="label.alternativeFeeMap.paymentId"
											cssClass="control-label"
										/>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<s:select list="paymentList" listKey="paymentId"
												listValue="label" name="paymentId"
												headerValue="%{getText('select.payment')}"
												value="%{paymentId}" disabled="%{isReadOnly}"
											/>
										</div>
									</div>

									<s:push value="alternativeFeeMap">
										<div class="col-md-2">
											<s:label key="label.alternativeFeeMap.alternativeAmount"
												cssClass="control-label"
											/>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<s:textfield key="label.alternativeFeeMap.alternativeAmount"
													name="alternativeAmount" cssClass="form-control"
												/>
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
