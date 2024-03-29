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
									<th><s:text name="label.fee.feeId" /></th>
									<th><s:text name="label.fee.name" /></th>
									<th><s:text name="label.fee.type" /></th>
									<th><s:text name="label.fee.feeGroupId" /></th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="fees">
									<tr class="gradeC">
										<td><s:property value="feeId" /></td>
										<td><s:property value="name" /></td>
										<td><s:property value="type.name" /></td>
										<td class="rightDir"
											original-title="<s:property value="feeGroup.tooltip" />"
										><s:url id="feeGroupUrl" action="editFeeGroup">
												<s:param name="feeGroupId" value="%{feeGroup.feeGroupId}" />
											</s:url> <s:a href="%{feeGroupUrl}">
												<s:property value="feeGroup.label" />
											</s:a></td>
										<td><s:url id="editUrl" action="editFee">
												<s:param name="feeId" value="%{feeId}" />
											</s:url> <s:a cssClass="btn btn-sm btn-primary" href="%{editUrl}">
												<s:text name="grid.action.edit" />
											</s:a> <s:url id="deleteUrl" action="deleteFee">
												<s:param name="feeId" value="%{feeId}" />
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
						<s:form action="saveOrUpdateFee" cssClass="form-horizontal">
							<fieldset>
								<legend class="section">
									<s:text name="form.legend" />
								</legend>
								<div class="control-group">
									<s:fielderror />
									<s:push value="fee">
										<!-- Mapping fields -->
										<s:set var="changingMappingKeyAllowed"><%=Helper.changingMappingKeyAllowed()%></s:set>
										<s:if test="%{#changingMappingKeyAllowed}">
											<s:set name="isReadOnly" value="false" />
										</s:if>
										<s:else>
											<s:set name="isReadOnly" value="true" />
											<s:hidden name="feeGroupId" />
										</s:else>

										<s:hidden name="feeId" />
										<div class="col-md-2">
											<s:label key="label.fee.type" cssClass="control-label" />
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<s:select list="feeTypeList" listKey="type" listValue="name"
													name="feeTypeId" headerValue="%{getText('select.feeType')}"
													value="%{feeTypeId}"
												/>
											</div>
										</div>
										<div class="col-md-2">
											<s:label key="label.fee.feeGroupId" cssClass="control-label" />
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<s:select list="feeGroupList" listKey="feeGroupId"
													listValue="label" name="feeGroupId" headerKey="-1"
													headerValue="%{getText('select.feeGroup')}"
													value="%{feeGroupId}" disabled="%{isReadOnly}"
												/>
											</div>
										</div>
										<div class="col-md-2">
											<s:label key="label.fee" cssClass="control-label" />
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<s:textfield key="label.fee" name="name"
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
