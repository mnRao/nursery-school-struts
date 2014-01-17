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
					<li class="active"><a data-toggle="tab" href="#firstTab"><s:text name="tab.label.first"/></a></li>
					<li class=""><a data-toggle="tab" href="#secondTab"><s:text name="tab.label.second"/></a></li>
				</ul>
				<div class="tab-content" id="myTabContent">
					<div id="firstTab" class="tab-pane fade active in">
						<s:form action="addSubject" cssClass="form-horizontal">
							<fieldset>
								<legend class="section">
									<s:text name="form.legend" />
								</legend>

								<div class="control-group">
									<div class="col-md-2">
										<s:label key="label.subject" cssClass="control-label" />
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<s:textfield key="label.subject" name="name"
												cssClass="form-control" />
										</div>
									</div>
								</div>
							</fieldset>
							<div class="form-actions">
								<div>
									<s:submit cssClass="btn btn-primary" />
									<button class="btn btn-default" type="button">Cancel</button>
								</div>
							</div>
						</s:form>
					</div>
					<div id="secondTab" class="tab-pane fade">
						<table class="table table-striped table-images">
							<thead>
								<tr>
									<th><s:text name="label.subject.subjectId" /></th>
									<th><s:text name="label.subject.name" /></th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="months">
									<tr>
										<td><s:property value="subjectId" /></td>
										<td><s:property value="name" /></td>
										<td class="hidden-xs"><button
												class="btn btn-sm btn-primary">Edit</button>
											<button data-toggle="button" class="btn btn-sm btn-warning">
												Delete</button></td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
						<div class="clearfix">
							<div class="pull-right">
								<div class="btn-group">
									<button data-toggle="dropdown"
										class="btn btn-sm btn-inverse dropdown-toggle">
										&nbsp; Clear &nbsp; <i class="icon-caret-down"></i>
									</button>
									<ul class="dropdown-menu">
										<li><a href="#">Clear</a></li>
										<li><a href="#">Move ...</a></li>
										<li><a href="#">Something else here</a></li>
										<li class="divider"></li>
										<li><a href="#">Separated link</a></li>
									</ul>
								</div>
								<button class="btn btn-default btn-sm">Send to ...</button>
							</div>
							<ul class="pagination no-margin">
								<li class="disabled"><a href="#">Prev</a></li>
								<li class="active"><a href="#">1</a></li>
								<li><a href="#">2</a></li>
								<li><a href="#">3</a></li>
								<li><a href="#">Next</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
