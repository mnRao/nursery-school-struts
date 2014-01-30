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
									<th><s:text name="label.payment.paymentId" /></th>
									<th><s:text name="label.payment.absenceCount" /></th>
									<th><s:text name="label.payment.totalNormalMealFee" /></th>
									<th><s:text name="label.payment.totalBreakfastFee" /></th>
									<th><s:text name="label.payment.totalFee" /></th>
									<th><s:text name="label.payment.isPaid" /></th>
									<th><s:text name="label.payment.note" /></th>
									<th><s:text name="label.payment.studentId" /></th>
									<th><s:text name="label.payment.feeDetailsId" /></th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="payments">
									<tr class="gradeC">
										<td><s:property value="paymentId" /></td>
										<td><s:property value="absenceCount" /></td>
										<td><s:property value="totalNormalMealFee" /></td>
										<td><s:property value="totalBreakfastFee" /></td>
										<td><s:property value="totalFee" /></td>
										<td><s:property value="isPaid" /></td>
										<td><s:property value="note" /></td>
										<td><s:property value="studentId" /></td>
										<td><s:property value="feeDetailsId" /></td>
										<td>
										   <s:url id="editUrl"
										      action="editPayment">
										      <s:param name="paymentId" value="%{paymentId}" />
										   </s:url>
										   <s:a cssClass="btn btn-sm btn-primary" href="%{editUrl}">Edit</s:a>
										   <s:url id="deleteUrl" action="deletePayment">
										      <s:param name="paymentId" value="%{paymentId}" />
										   </s:url>
										   <s:a cssClass="btn btn-sm btn-warning" href="%{deleteUrl}">Delete</s:a>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
					
					<div id="secondTab"
						class="tab-pane fade <%=Helper.getTabCss(2, true)%>">
						<s:form action="saveOrUpdatePayment" cssClass="form-horizontal">
							<fieldset>
								<legend class="section">
									<s:text name="form.legend" />
								</legend>
								<div class="control-group">
									<s:push value="payment">
										<s:hidden name="paymentId" />
										<div class="col-md-2">
											<s:label key="label.payment.absenceCount" cssClass="control-label" />
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<s:textfield key="label.payment.absenceCount" name="absenceCount"
													cssClass="form-control" />
											</div>
										</div>
										<div class="col-md-2">
											<s:label key="label.payment.isPaid" cssClass="control-label" />
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<s:textfield key="label.payment.isPaid" name="isPaid"
													cssClass="form-control" />
											</div>
										</div>
										<div class="col-md-2">
											<s:label key="label.payment.note" cssClass="control-label" />
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<s:textfield key="label.payment.note" name="note"
													cssClass="form-control" />
											</div>
										</div>
										<div class="col-md-2">
											<s:label key="label.payment.studentId" cssClass="control-label" />
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<s:textfield key="label.payment.studentId" name="payment.studentFeeDetails.studentId"
													cssClass="form-control" />
											</div>
										</div>
										<div class="col-md-2">
											<s:label key="label.payment.feeDetailsId" cssClass="control-label" />
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<s:textfield key="label.payment.feeDetailsId" name="payment.studentFeeDetails.feeDetailsId"
													cssClass="form-control" />
												
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
