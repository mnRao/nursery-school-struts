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
									<th><s:text name="label.extraFeeMap.feeDetailsId" /></th>
									<th><s:text name="label.extraFeeMap.extraFeeTypeId" /></th>
									<th><s:text name="label.extraFeeMap.amount" /></th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="extraFeeMaps">
									<tr class="gradeC">
										<td><s:property
												value="feeDetailsExtraFee.feeDetails.label" /></td>
										<td><s:property value="feeDetailsExtraFee.extraFeeType.name" /></td>
										<td><s:property value="amount" /></td>
										<td><s:url id="editUrl" action="editExtraFeeMap">
												<s:param name="extraFeeTypeId"
													value="%{feeDetailsExtraFee.extraFeeType.extraFeeTypeId}" />
												<s:param name="feeDetailsId"
													value="%{feeDetailsExtraFee.feeDetails.feeDetailsId}" />
											</s:url> <s:a cssClass="btn btn-sm btn-primary" href="%{editUrl}">Edit</s:a>
											<s:url id="deleteUrl" action="deleteExtraFeeMap">
												<s:param name="extraFeeTypeId"
													value="%{feeDetailsExtraFee.extraFeeType.extraFeeTypeId}" />
												<s:param name="feeDetailsId"
													value="%{feeDetailsExtraFee.feeDetails.feeDetailsId}" />
											</s:url> <s:a cssClass="btn btn-sm btn-warning" href="%{deleteUrl}">Delete</s:a>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>

					<div id="secondTab"
						class="tab-pane fade <%=Helper.getTabCss(2, true)%>">
						<s:form action="saveOrUpdateExtraFeeMap"
							cssClass="form-horizontal">
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
										<s:hidden name="extraFeeTypeId"/>
										<s:hidden name="feeDetailsId"/>
									</s:else>

									<div class="col-md-2">
										<s:label key="label.extraFeeType" cssClass="control-label" />
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<s:select list="extraFeeTypeList" listKey="extraFeeTypeId"
												listValue="name" name="extraFeeTypeId" headerKey="-1"
												headerValue="%{getText('select.extraFeeType')}"
												value="%{extraFeeTypeId}" disabled="%{isReadOnly}"/>
										</div>
									</div>
									<div class="col-md-2">
										<s:label key="label.extraFeeMap.feeDetailsId"
											cssClass="control-label" />
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<s:select list="feeDetailsList" listKey="feeDetailsId"
												listValue="label" name="feeDetailsId" headerKey="-1"
												headerValue="%{getText('select.feeDetails')}"
												value="%{feeDetailsId}" disabled="%{isReadOnly}" />
										</div>
									</div>

									<s:push value="extraFeeMap">
										<div class="col-md-2">
											<s:label key="label.extraFeeMap.amount"
												cssClass="control-label" />
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<s:textfield key="label.extraFeeMap.amount" name="amount"
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
