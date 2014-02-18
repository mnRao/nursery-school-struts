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
					<li class="<%=Helper.getTabCss(1, false, ActionContext.getContext().getActionInvocation().getResultCode())%>"><a
						data-toggle="tab" href="#firstTab"><s:text
								name="tab.label.second" /></a></li>
					<li class="<%=Helper.getTabCss(2, false, ActionContext.getContext().getActionInvocation().getResultCode())%>"><a
						data-toggle="tab" href="#secondTab"><s:text
								name="tab.label.first" /></a></li>
				</ul>
				<div class="tab-content" id="myTabContent">
					<div id="firstTab"
						class="tab-pane fade <%=Helper.getTabCss(1, true, ActionContext.getContext().getActionInvocation().getResultCode())%>">
						<table cellpadding="0" cellspacing="0" border="0" class="display"
							id="dynamicTable">
							<thead>
								<tr>
									<th><s:text name="label.month.monthId" /></th>
									<th><s:text name="label.month.label" /></th>
									<th><s:text name="label.month.month" /></th>
									<th><s:text name="label.month.year" /></th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="months">
									<tr class="gradeC">
										<td><s:property value="monthId" /></td>
										<td><s:property value="label" /></td>
										<td><s:property value="monthName" /></td>
										<td><s:property value="year" /></td>
										<td><s:url id="editUrl" action="editMonth">
												<s:param name="monthId" value="%{monthId}" />
											</s:url> <s:a cssClass="btn btn-sm btn-primary" href="%{editUrl}"><s:text name="grid.action.edit" /></s:a>
											<s:url id="deleteUrl" action="deleteMonth">
												<s:param name="monthId" value="%{monthId}" />
											</s:url> <s:a cssClass="btn btn-sm btn-warning" href="%{deleteUrl}"><s:text name="grid.action.delete" /></s:a>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
					<div id="secondTab"
						class="tab-pane fade <%=Helper.getTabCss(2, true, ActionContext.getContext().getActionInvocation().getResultCode())%>">
						<s:form action="saveOrUpdateMonth" cssClass="form-horizontal">
							<fieldset>
								<legend class="section">
									<s:text name="form.legend" />
								</legend>
								<div class="control-group">
									<s:fielderror/>
									<s:push value="month">
										<s:hidden name="monthId" />
										<div class="col-md-2">
											<s:label key="label.month.month" cssClass="control-label" />
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<s:textfield key="label.month.month" name="monthName"
													cssClass="form-control" type="number" />
											</div>
										</div>
										<div class="col-md-2">
											<s:label key="label.month.year" cssClass="control-label" />
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<s:textfield key="label.month.year" name="year"
													cssClass="form-control" type="number" />
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
