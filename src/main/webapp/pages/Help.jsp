<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="row">
	<div class="col-lg-12">
		<div class="widget">
			<div class="widget-header">
				<i class="icon-tasks"></i>
				<h3></h3>
			</div>
			<div class="widget-content">
				<div class="panel">
					<div class="panel-body">
						<div>
							<s:url id="fileDownload" namespace="/" action="UserGuide"></s:url>
							<s:a cssClass="btn btn-sm btn-info" href="%{fileDownload}"><s:text name="help.userGuide"/></s:a>
						</div>
						<div class="text-center mbot30">
							<h3 class="time-title">
								<s:text name="help.title" />
							</h3>
							<p class="t-info">
								<s:text name="help.info" />
							</p>
						</div>
						<img alt="SimpleProcess" src="assets/app/SimpleProcess.png"
							width="100%"
						>
						<div class="clearfix">&nbsp;</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>