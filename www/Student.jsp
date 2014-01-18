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
						<s:form action="addStudent" cssClass="form-horizontal">
							<fieldset>
								<legend class="section">
									<s:text name="form.legend" />
								</legend>

								<div class="control-group">
									<div class="col-md-2">
										<s:label key="label.student.name" cssClass="control-label" />
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<s:textfield key="label.student.name" name="name"
												cssClass="form-control" />
										</div>
									</div>
								</div>
								<div class="control-group">
									<div class="col-md-2">
										<s:label key="label.student.dateOfBirth"
											cssClass="control-label" />
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<%-- 									<s:textfield key="label.student.dateOfBirth" name="dateOfBirth" --%>
											<%-- 										data-type="dateIso" placeholder="YYYY-MM-DD" --%>
											<%-- 										class="form-control" /> --%>
											<s:textfield key="label.student.dateOfBirth"
												name="dateOfBirth" cssClass="form-control" />
										</div>
									</div>
								</div>
								<div class="control-group">
									<div class="col-md-2">
										<s:label key="label.student.gender" cssClass="control-label" />
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<s:textfield key="label.student.gender" name="gender"
												cssClass="form-control" />
										</div>
									</div>
								</div>
								<div class="control-group">
									<div class="col-md-2">
										<s:label key="label.student.address" cssClass="control-label" />
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<s:textfield key="label.student.address" name="address"
												cssClass="form-control" />
										</div>
									</div>
								</div>
								<div class="control-group">
									<div class="col-md-2">
										<s:label key="label.student.homePhone"
											cssClass="control-label" />
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<s:textfield key="label.student.homePhone" name="homePhone"
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
									<th><s:text name="label.student.name" /></th>
									<th><s:text name="label.student.dateOfBirth" /></th>
									<th><s:text name="label.student.gender" /></th>
									<th><s:text name="label.student.address" /></th>
									<th><s:text name="label.student.homePhone" /></th>
									<th><s:text name="label.student.isActive" /></th>
									<th><s:text name="label.student.classId" /></th>
									<!-- 								<th class="hidden-xs">###</th> -->
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>1</td>
									<td><img src="assets/images/1.jpg"></td>
									<td>abc</td>
									<td class="hidden-xs"><p>
											<small> <strong>Dimensions:</strong> &nbsp; 200x150
											</small>
										</p></td>
									<td class="hidden-xs">August 18, 2013</td>
									<td>45.6 KB</td>
									<td>45.6 KB</td>
									<td class="hidden-xs"><button
											class="btn btn-sm btn-primary">Edit</button>
										<button data-toggle="button" class="btn btn-sm btn-warning">
											Delete</button></td>
								</tr>
								<s:iterator value="students">
									<tr>
										<td><s:property value="name" /></td>
										<td><s:property value="dateOfBirth" /></td>
										<td><s:property value="gender" /></td>
										<td><s:property value="address" /></td>
										<td><s:property value="homePhone" /></td>
										<td><s:property value="isActive" /></td>
										<td><s:property value="classId" /></td>
										<td class="hidden-xs">
										<button
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
<!-- <table> -->
<!-- 	<tr> -->
<%-- 		<td><s:text name="label.student.name" /></td> --%>
<%-- 		<td><s:text name="label.student.dateOfBirth" /></td> --%>
<%-- 		<td><s:text name="label.student.gender" /></td> --%>
<%-- 		<td><s:text name="label.student.address" /></td> --%>
<%-- 		<td><s:text name="label.student.homePhone" /></td> --%>
<%-- 		<td><s:text name="label.student.isActive" /></td> --%>
<%-- 		<td><s:text name="label.student.classId" /></td> --%>
<!-- 	</tr> -->
<%-- 	<s:iterator value="students"> --%>
<!-- 		<tr> -->
<%-- 			<td><s:property value="name" /></td> --%>
<%-- 			<td><s:property value="dateOfBirth" /></td> --%>
<%-- 			<td><s:property value="gender" /></td> --%>
<%-- 			<td><s:property value="address" /></td> --%>
<%-- 			<td><s:property value="homePhone" /></td> --%>
<%-- 			<td><s:property value="isActive" /></td> --%>
<%-- 			<td><s:property value="classId" /></td> --%>
<!-- 		</tr> -->
<%-- 	</s:iterator> --%>
<!-- </table> -->
<!-- <div class="row"> -->
<!-- 	<div class="col-lg-12"> -->
<!-- 		<div class="widget"> -->
<!-- 			<div class="widget-header"> -->
<!-- 				<i class="icon-table"></i> -->
<!-- 				<h3>Table</h3> -->
<!-- 			</div> -->
<!-- 			<div class="widget-content"> -->
<!-- 				<div class="body"></div> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- </div> -->
