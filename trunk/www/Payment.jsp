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
									<th><s:text name="label.payment.studentId" /></th>
									<th><s:text name="label.payment.feePolicyId" /></th>
									<th><s:text name="label.payment.absenceCount" /></th>
									<th><s:text name="label.payment.totalNormalMealFee" /></th>
									<th><s:text name="label.payment.totalBreakfastFee" /></th>
									<th><s:text name="label.payment.totalFee" /></th>
									<%-- 									<th><s:text name="label.payment.isPaid" /></th> --%>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="payments">
									<tr class="gradeC">
										<td><s:property value="student.name" /></td>
										<td><s:property value="feePolicy.label" /></td>
										<td><s:property value="absenceCount" /></td>
										<td><s:property value="totalNormalMealFee" /></td>
										<td><s:property value="totalBreakfastFee" /></td>
										<td><s:property value="totalFee" /></td>
										<%-- 										<td><s:property value="isPaid" /></td> --%>
										<td><s:url id="editUrl" action="editPayment">
												<s:param name="paymentId" value="%{paymentId}" />
											</s:url> <s:a cssClass="btn btn-sm btn-primary" href="%{editUrl}">
												<s:text name="grid.action.edit" />
											</s:a> <s:url id="deleteUrl" action="deletePayment">
												<s:param name="paymentId" value="%{paymentId}" />
											</s:url> <s:a cssClass="btn btn-sm btn-warning" href="%{deleteUrl}">
												<s:text name="grid.action.delete" />
											</s:a> <s:url id="addAlternativeFeeChargeMapUrl"
												action="autoSetPaymentAlternativeFeeChargeMap"
											>
												<s:param name="paymentId" value="%{paymentId}" />
											</s:url> <s:a cssClass="btn btn-sm btn-info"
												href="%{addAlternativeFeeChargeMapUrl}"
											>
												<s:text name="grid.action.addAlternativeFeeMap" />
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
						<s:form action="saveOrUpdatePayment" cssClass="form-horizontal">
							<fieldset>
								<legend class="section">
									<s:text name="form.legend" />
								</legend>
								<div class="control-group">
									<s:push value="payment">
										<!-- Mapping fields -->
										<s:set var="changingMappingKeyAllowed"><%=Helper.changingMappingKeyAllowed()%></s:set>
										<s:if test="%{#changingMappingKeyAllowed}">
											<s:set name="isReadOnly" value="false" />
										</s:if>
										<s:else>
											<s:set name="isReadOnly" value="true" />
											<s:hidden name="studentId" />
											<s:hidden name="feePolicyId" />
										</s:else>

										<s:fielderror />
										<div class="col-md-2">
											<s:label key="label.payment.studentId"
												cssClass="control-label"
											/>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<s:select list="studentList" listKey="studentId"
													listValue="label" name="studentId"
													headerValue="%{getText('select.student')}"
													value="%{studentId}" disabled="%{isReadOnly}"
												/>
											</div>
										</div>
										<div class="col-md-2">
											<s:label key="label.payment.feePolicyId"
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


										<s:hidden name="paymentId" />
										<div class="col-md-2">
											<s:label key="label.payment.hasBreakfast"
												cssClass="control-label"
											/>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<s:select name="hasBreakfast" headerKey="-1"
													list="#{0: getText('form.trueFalse.false'), '1': getText('form.trueFalse.true')}"
													value="hasBreakfast"
												/>
											</div>
										</div>
										<div class="col-md-2">
											<s:label key="label.payment.absenceCount"
												cssClass="control-label"
											/>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<s:textfield key="label.payment.absenceCount"
													name="absenceCount" cssClass="form-control" type="number"
												/>
											</div>
										</div>
										<!-- 										<div class="col-md-2"> -->
										<%-- 											<s:label key="label.payment.note" cssClass="control-label" /> --%>
										<!-- 										</div> -->
										<!-- 										<div class="col-md-4"> -->
										<!-- 											<div class="form-group"> -->
										<%-- 												<s:textfield key="label.payment.note" name="note" --%>
										<%-- 													cssClass="form-control" /> --%>
										<!-- 											</div> -->
										<!-- 										</div> -->
										<!-- 										<div class="col-md-2"> -->
										<%-- 											<s:label key="label.payment.isPaid" cssClass="control-label" /> --%>
										<!-- 										</div> -->
										<!-- 										<div class="col-md-4"> -->
										<!-- 											<div class="form-group"> -->
										<%-- 												<s:select name="isPaid" headerKey="-1" --%>
										<%-- 													list="#{0: getText('form.trueFalse.false'), '1': getText('form.trueFalse.true')}" --%>
										<%-- 													value="isPaid" /> --%>
										<!-- 											</div> -->
										<!-- 										</div> -->

										<legend class="section">
											<s:text name="form.legend.computed" />
										</legend>
										<div class="col-md-2">
											<s:label key="label.payment.totalNormalMealFee"
												cssClass="control-label"
											/>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<s:textfield key="label.payment.totalNormalMealFee"
													name="totalNormalMealFee" cssClass="form-control"
													readonly="true"
												/>
											</div>
										</div>
										<div class="col-md-2">
											<s:label key="label.payment.totalBreakfastFee"
												cssClass="control-label"
											/>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<s:textfield key="label.payment.totalBreakfastFee"
													name="totalBreakfastFee" cssClass="form-control"
													readonly="true"
												/>
											</div>
										</div>
										<div class="col-md-2">
											<s:label key="label.payment.totalFee"
												cssClass="control-label"
											/>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<s:textfield key="label.payment.totalFee" name="totalFee"
													cssClass="form-control" readonly="true"
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
