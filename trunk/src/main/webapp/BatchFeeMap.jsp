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
					<s:form action="batchSaveOrUpdateFeeMap"
						cssClass="form-horizontal"
					>
						<fieldset>
							<legend class="section">
								<s:text name="form.legend.batchEdit.feeMap">
									<s:param>
										<s:property value="feeMapList[0].feePolicyFee.feePolicy.label" />
									</s:param>
									<s:param>
										<s:property
											value="feeMapList[0].feePolicyFee.feePolicy.associatedClass.label"
										/>
									</s:param>
									<s:param>
										<s:property value="feeMapList[0].feePolicyFee.feePolicy.month.label" />
									</s:param>
								</s:text>
							</legend>
							<div class="control-group">
								<s:push value="feeMap">

									<s:fielderror />
									<s:hidden name="feePolicyId" />

									<div class="col-md-2">
										<s:checkbox id="selectAllFeeMaps" name="selectAllFeeMaps" />
									</div>
									<div class="col-md-4">
										<s:text name="Select all" />
									</div>
									<!-- 2 unnecessary checkboxes (just to fill the space) -->
									<div class="col-md-2">
										<s:checkbox name="selectFeeMap" cssClass="app-hidden" />
									</div>
									<div class="col-md-4">
										<s:checkbox name="selectFeeMap" cssClass="app-hidden" />
									</div>

									<s:iterator value="feeMapList" var="feeMap" status="count">
										<s:div id="recordRow_%{#feeMap.feePolicyFee.fee.feeId}" cssClass="recordRow">
											<s:hidden name="feeMapList[%{#count.index}].feePolicyId" />
											<s:hidden
												name="feeMapList[%{#count.index}].feePolicyFee.fee.feeId"
											/>

											<div class="col-md-2">
												<s:checkbox id="selectFeeMap_%{#feeMap.feePolicyFee.fee.feeId}"
													name="selectFeeMap" fieldValue="%{#feeMap.feePolicyFee.fee.feeId}" cssClass="selectFeeMap"
												/>
											</div>
											<div class="col-md-4">
												<label><s:property value="feePolicyFee.fee.name" /></label>
											</div>
											<div class="col-md-2">
												<s:label key="label.feeMap.amount" cssClass="control-label" />
											</div>
											<s:div cssClass="col-md-4" id="amount-toggle_%{#feeMap.feePolicyFee.fee.feeId}">
												<div class="form-group">
													<s:textfield id="amount_%{#feeMap.feePolicyFee.fee.feeId}" key="label.feeMap.amount"
														name="feeMapList[%{#count.index}].amount"
														cssClass="form-control parsley-validated amount-toggle"
													/>
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
