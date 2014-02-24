<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="row">
	<div class="col-md-12">
		<div class="row">
			<div class="col-md-3 col-xs-12 col-sm-6">
				<a href="#" class="stats-container">
					<div class="stats-heading">
						<s:text name="dashboard.activeClasses" />
					</div>
					<div class="stats-body-alt">
						<!--i class="fa fa-bar-chart-o"></i-->
						<div class="text-center">
							<span class="text-top">#</span>
							<s:property value="classAvailableCount" />
						</div>
						<small>in total</small>
					</div>
					<div class="stats-footer"></div>
				</a>
			</div>
			<div class="col-md-3 col-xs-12 col-sm-6">
				<a href="#" class="stats-container">
					<div class="stats-heading">
						<s:text name="dashboard.activeStudents" />
					</div>
					<div class="stats-body-alt">
						<!--i class="fa fa-bar-chart-o"></i-->
						<div class="text-center">
							<span class="text-top">#</span>
							<s:property value="activeStudentsCount" />
						</div>
						<small>in total</small>
					</div>
					<div class="stats-footer"></div>
				</a>
			</div>
			<div class="col-md-3 col-xs-12 col-sm-6">
				<a href="#" class="stats-container">
					<div class="stats-heading">
						<s:text name="dashboard.previousMonthIncome" />
					</div>
					<div class="stats-body-alt">
						<!--i class="fa fa-bar-chart-o"></i-->
						<div class="text-center">
							<span class="text-top"></span>
							<s:property value="previousMonthIncome" />
						</div>
						<small>new user registered</small>
					</div>
					<div class="stats-footer">manage members</div>
				</a>
			</div>
			<div class="col-md-3 col-xs-12 col-sm-6">
				<a href="#" class="stats-container">
					<div class="stats-heading">
						<s:text name="dashboard.thisMonthIncome" />
					</div>
					<div class="stats-body-alt">
						<!--i class="fa fa-bar-chart-o"></i-->
						<div class="text-center">
							<span class="text-top">$</span>
							<s:property value="thisMonthIncome" />
						</div>
						<small>new orders received</small>
					</div>
					<div class="stats-footer">manage orders</div>
				</a>
			</div>
		</div>
	</div>
</div>
