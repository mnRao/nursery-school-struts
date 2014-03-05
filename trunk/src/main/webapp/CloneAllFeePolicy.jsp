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
				<div class="panel">
					<div class="panel-body">
						<s:form action="performCloneAllFeePolicy" cssClass="form-horizontal">
							<fieldset>
								<legend class="section">
									<s:text name="form.legend" />
								</legend>
								<div class="control-group">
									<s:fielderror />
									<s:hidden name="feePolicyIdToClone"/>
									<div class="col-md-2">
										<s:label key="label.feePolicy.classId"
											cssClass="control-label"
										/>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<s:select list="classList" listKey="classId"
												listValue="label" name="classId"
												headerValue="%{getText('select.class')}" value="%{classId}"
											/>
										</div>
									</div>
									<div class="col-md-2">
										<s:label key="label.feePolicy.monthId"
											cssClass="control-label"
										/>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<s:select list="monthList" listKey="monthId"
												listValue="label" name="monthId"
												headerValue="%{getText('select.month')}" value="%{monthId}"
											/>
										</div>
									</div>
								</div>
							</fieldset>
							<div class="form-actions">
								<div>
									<s:submit action="" key="form.submit" cssClass="btn btn-primary" />
								</div>
							</div>
						</s:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>