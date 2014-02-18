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
									<th><s:text name="label.course.courseId" /></th>
									<th><s:text name="label.course.startYear" /></th>
									<th><s:text name="label.course.endYear" /></th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="courses">
									<tr class="gradeC">
										<td><s:property value="courseId" /></td>
										<td><s:property value="startYear" /></td>
										<td><s:property value="endYear" /></td>
										<td><s:url id="editUrl" action="editCourse">
												<s:param name="courseId" value="%{courseId}" />
											</s:url> <s:a cssClass="btn btn-sm btn-primary" href="%{editUrl}">
												<s:text name="grid.action.edit" />
											</s:a> <s:url id="deleteUrl" action="deleteCourse">
												<s:param name="courseId" value="%{courseId}" />
											</s:url> <s:a cssClass="btn btn-sm btn-warning" href="%{deleteUrl}">
												<s:text name="grid.action.delete" />
											</s:a> <s:url id="addClassUrl" action="autoSetCourseClasses">
												<s:param name="courseId" value="%{courseId}" />
											</s:url> <s:a cssClass="btn btn-sm btn-info" href="%{addClassUrl}">
												<s:text name="grid.action.addClass" />
											</s:a></td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>

					<div id="secondTab"
						class="tab-pane fade <%=Helper.getTabCss(2, true)%>">
						<s:form action="saveOrUpdateCourse" cssClass="form-horizontal">
							<fieldset>
								<legend class="section">
									<s:text name="form.legend" />
								</legend>
								<div class="control-group">
									<s:fielderror />
									<s:push value="course">
										<s:hidden name="courseId" />
										<div class="col-md-2">
											<s:label key="label.course.startYear"
												cssClass="control-label" />
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<s:textfield key="label.course.startYear" name="startYear"
													cssClass="form-control" type="number" />
											</div>
										</div>
										<div class="col-md-2">
											<s:label key="label.course.endYear" cssClass="control-label" />
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<s:textfield key="label.course.endYear" name="endYear"
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
