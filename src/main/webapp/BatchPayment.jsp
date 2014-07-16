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
				<div class="tab-content" id="myTabContent">
					<s:form action="batchSaveOrUpdatePayment"
						cssClass="form-horizontal"
					>
						<fieldset>
							<legend class="section">
								<s:text name="form.legend.batchEdit.payment">
									<s:param>
										<s:property value="paymentList[0].feePolicy.label" />
									</s:param>
									<s:param>
										<s:property
											value="paymentList[0].feePolicy.associatedClass.label"
										/>
									</s:param>
									<s:param>
										<s:property value="paymentList[0].feePolicy.month.label" />
									</s:param>
								</s:text>
							</legend>
							<div class="control-group">
								<s:push value="payment">
									<s:fielderror />

									<s:hidden name="feePolicyId" />
									<s:iterator value="paymentList" var="payments" status="count">
										<s:hidden name="paymentList[%{#count.index}].paymentId" />
										<s:hidden
											name="paymentList[%{#count.index}].student.studentId"
										/>
										<s:set var="currentStudentId" name="paymentList[%{#count.index}].paymentId"/>
										<div class="col-md-2">
											<s:url id="studentUrl"
												action="editStudent"
											>
												<s:param name="studentId"><s:property value="#currentStudentId"/></s:param>
											</s:url>
											<s:a href="%{studentUrl}">
												<s:property value="student.name" />
											</s:a>
										</div>
										<div class="col-md-2">
											<s:label key="label.payment.hasBreakfast"
												cssClass="control-label"
											/>
										</div>
										<div class="col-md-2">
											<div class="form-group">
												<s:select name="paymentList[%{#count.index}].hasBreakfast"
													headerKey="-1"
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
													name="paymentList[%{#count.index}].absenceCount"
													cssClass="form-control" type="number" required=""
												/>

												<s:if test="%{paymentId}">
													<s:url id="addAlternativeFeeChargeMapUrl"
														action="autoBatchSetPaymentAlternativeFeeChargeMap"
													>
														<s:param name="paymentId" value="%{paymentId}" />
													</s:url>
													<s:a cssClass="btn btn-sm btn-info"
														href="%{addAlternativeFeeChargeMapUrl}"
													>
														<s:text name="grid.action.manageAlternativeFeeMap" />
													</s:a>
												</s:if>

											</div>
										</div>

									</s:iterator>
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
