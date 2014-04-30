<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page import="com.duke.nurseryschool.helper.Helper"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script src="assets/app/js/batchAlternativeFeeMap.js"></script>

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
					<s:form action="batchSaveOrUpdateAlternativeFeeChargeMap"
						cssClass="form-horizontal"
					>
						<fieldset>
							<legend class="section">
								<s:text name="form.legend.batchEdit.alternativeFeeMap">
									<s:param>
										<s:property
											value="alternativeFeeMapList[0].paymentFee.payment.label"
										/>
									</s:param>
								</s:text>
							</legend>
							<div class="control-group">
								<s:push value="alternativeFeeMap">

									<s:fielderror />
									<s:hidden name="paymentId" />

									<div class="col-md-2">
										<s:checkbox id="selectAllCheckboxes"
											name="selectAllCheckboxes"
										/>
									</div>
									<div class="col-md-4">
										<s:text name="Select all" />
									</div>
									<!-- 2 unnecessary checkboxes (just to fill the space) -->
									<div class="col-md-2">
										<s:checkbox name="unrelated" cssClass="app-hidden" />
									</div>
									<div class="col-md-4">
										<s:checkbox name="unrelated" cssClass="app-hidden" />
									</div>

									<s:iterator value="alternativeFeeMapList"
										var="alternativeFeeMap" status="count"
									>
										<s:div
											id="recordRow_%{#alternativeFeeMap.paymentFee.fee.feeId}"
											cssClass="recordRow"
										>
											<s:hidden
												name="alternativeFeeMapList[%{#count.index}].paymentId"
											/>
											<s:hidden
												name="alternativeFeeMapList[%{#count.index}].paymentFee.fee.feeId"
											/>

											<div class="col-md-2">
												<s:if
													test="%{#alternativeFeeMap.paymentFee.fee.feeIdAsString in selectAlternativeFeeMap}"
												>
													<s:checkbox
														id="selectedElement_%{#alternativeFeeMap.paymentFee.fee.feeId}"
														name="selectAlternativeFeeMap"
														fieldValue="%{#alternativeFeeMap.paymentFee.fee.feeId}"
														cssClass="selectedElement" checked="checked"
													/>
												</s:if>
												<s:else>
													<s:checkbox
														id="selectedElement_%{#alternativeFeeMap.paymentFee.fee.feeId}"
														name="selectAlternativeFeeMap"
														fieldValue="%{#alternativeFeeMap.paymentFee.fee.feeId}"
														cssClass="selectedElement"
													/>
												</s:else>
											</div>
											<div class="col-md-4">
												<label><s:property value="paymentFee.fee.name" /></label>
											</div>
											<div class="col-md-2">
												<s:label key="label.alternativeFeeMap.alternativeAmount"
													cssClass="control-label"
												/>
											</div>
											<s:div cssClass="col-md-4"
												id="amount-toggle_%{#alternativeFeeMap.paymentFee.fee.feeId}"
											>
												<div class="form-group">
													<s:textfield
														id="amount_%{#alternativeFeeMap.paymentFee.fee.feeId}"
														key="label.alternativeFeeMap.amount"
														name="alternativeFeeMapList[%{#count.index}].alternativeAmount"
														cssClass="form-control parsley-validated amount-toggle"
													/>
<%-- 													<s:hidden class="hiddenId" --%>
<%-- 														name="alternativeFeeMapList[%{#count.index}].alternativeAmount" --%>
<%-- 													></s:hidden> --%>
												</div>
											</s:div>
										</s:div>
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

