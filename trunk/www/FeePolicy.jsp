<%@page import="com.duke.nurseryschool.hibernate.bean.FeePolicy"%>
<%@page import="java.util.List"%>
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
									<th><s:text name="label.feePolicy.feePolicyId" /></th>
									<th><s:text name="label.feePolicy.classId" /></th>
									<th><s:text name="label.feePolicy.monthId" /></th>
									<th><s:text name="label.feePolicy.feePerNormalMeal" /></th>
									<th><s:text name="label.feePolicy.totalBreakfastFee" /></th>
									<th><s:text name="label.feePolicy.penaltyFeePerBreakfast" /></th>
									<th><s:text name="label.feePolicy.availableDays" /></th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="feePolicies">
									<tr class="gradeC">
										<td><s:property value="feePolicyId" /></td>
										<td><s:property value="associatedClass.label" /></td>
										<td><s:property value="month.label" /></td>
										<td><s:property value="feePerNormalMeal" /></td>
										<td><s:property value="totalBreakfastFee" /></td>
										<td><s:property value="penaltyFeePerBreakfast" /></td>
										<td><s:property value="availableDays" /></td>
										<td><s:url id="editUrl" action="editFeePolicy">
												<s:param name="feePolicyId" value="%{feePolicyId}" />
											</s:url> <s:a cssClass="btn btn-sm btn-primary" href="%{editUrl}">Edit</s:a>
											<s:url id="deleteUrl" action="deleteFeePolicy">
												<s:param name="feePolicyId" value="%{feePolicyId}" />
											</s:url> <s:a cssClass="btn btn-sm btn-warning" href="%{deleteUrl}">Delete</s:a>
											
											<s:url id="addFeeMapUrl" action="autoSetFeePolicyFeeMap">
												<s:param name="feePolicyId" value="%{feePolicyId}" />
											</s:url> <s:a cssClass="btn btn-sm btn-info" href="%{addFeeMapUrl}">Add Fee Map</s:a>
											<s:url id="addPaymentUrl" action="autoSetFeePolicyPayment">
												<s:param name="feePolicyId" value="%{feePolicyId}" />
											</s:url> <s:a cssClass="btn btn-sm btn-info" href="%{addPaymentUrl}">Add Payment</s:a>
											<s:url id="excelUrl" action="generateExcel">
												<s:param name="feeDetailsId"
													value="%{feeDetailsId}" />
											</s:url> <s:a cssClass="btn btn-sm btn-info" href="%{excelUrl}">Excel</s:a>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>

					<div id="secondTab"
						class="tab-pane fade <%=Helper.getTabCss(2, true)%>">
						<s:form action="saveOrUpdateFeePolicy" cssClass="form-horizontal">
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
										<s:hidden name="classId" />
										<s:hidden name="monthId" />
									</s:else>

									<div class="col-md-2">
										<s:label key="label.feePolicy.classId"
											cssClass="control-label" />
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<s:select list="classList" listKey="classId"
												listValue="label" name="classId" headerKey="-1"
												headerValue="%{getText('select.class')}" value="%{classId}"
												disabled="%{isReadOnly}" />
										</div>
									</div>
									<div class="col-md-2">
										<s:label key="label.feePolicy.monthId"
											cssClass="control-label" />
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<s:select list="monthList" listKey="monthId"
												listValue="label" name="monthId" headerKey="-1"
												headerValue="%{getText('select.month')}" value="%{monthId}"
												disabled="%{isReadOnly}" />
										</div>
									</div>

									<s:push value="feePolicy">
										<s:hidden name="feePolicyId"/>
										<div class="col-md-2">
											<s:label key="label.feePolicy.feePerNormalMeal"
												cssClass="control-label" />
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<s:textfield key="label.feePolicy.feePerNormalMeal"
													name="feePerNormalMeal" cssClass="form-control" />
											</div>
										</div>
										<div class="col-md-2">
											<s:label key="label.feePolicy.totalBreakfastFee"
												cssClass="control-label" />
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<s:textfield key="label.feePolicy.totalBreakfastFee"
													name="totalBreakfastFee" cssClass="form-control" />
											</div>
										</div>
										<div class="col-md-2">
											<s:label key="label.feePolicy.penaltyFeePerBreakfast"
												cssClass="control-label" />
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<s:textfield key="label.feePolicy.penaltyFeePerBreakfast"
													name="penaltyFeePerBreakfast" cssClass="form-control" />
											</div>
										</div>
										<div class="col-md-2">
											<s:label key="label.feePolicy.availableDays"
												cssClass="control-label" />
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<s:textfield key="label.feePolicy.availableDays"
													name="availableDays" cssClass="form-control" type="number"/>
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
