<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="login-logo">
	<img src="assets/images/logo.png" width="147" height="33">
</div>
<div class="widget">
	<div class="login-content">
		<div class="widget-content" style="padding-bottom: 0;">

			<s:form namespace="/" action="loginLoginAuth" method="post"
				cssClass="no-margin"
			>
				<h3 class="form-title">
					<s:text name="label.login" />
				</h3>
				<fieldset>
					<div class="form-group no-margin">
						<div class="input-group input-group-lg">
							<span class="input-group-addon"> <i class="icon-user"></i>
							</span>
							<s:textfield cssClass="form-control input-lg" name="username"
								key="label.username" placeholder="%{getText('label.username')}"
								size="20"
							></s:textfield>
						</div>
					</div>
					<div class="form-group">
						<div class="input-group input-group-lg">
							<span class="input-group-addon"> <i class="icon-lock"></i>
							</span>
							<s:password cssClass="form-control input-lg"
								placeholder="%{getText('label.password')}" name="password"
								key="label.password" size="20"
							></s:password>
						</div>
					</div>
				</fieldset>
				<s:fielderror />
				<s:actionerror />
				<div class="form-actions">
					<label class="checkbox">
						<div class="checker">
							<span> <s:checkbox name="rememberMe" /> <!-- 							<input type="checkbox" value="1" name="remember"> -->
							</span>
						</div> <s:text name="label.rememberMe"></s:text>
					</label>
					<s:submit cssClass="btn btn-warning pull-right" key="label.login"
						align="center"
					>
						<i class="m-icon-swapright m-icon-white"></i>
					</s:submit>
				</div>
			</s:form>

			<!-- 	Localization -->
			<s:url id="localeEN" namespace="/" action="locale">
				<s:param name="request_locale">en</s:param>
			</s:url>
			<s:url id="localeVI" namespace="/" action="locale">
				<s:param name="request_locale">vi_VN</s:param>
			</s:url>
			<div style="text-align: center; margin: 5px;">
				<s:a href="%{localeEN}" style="padding:4px;">
					<img src="assets/app/English.png" height="24"
						style="display: inline-block"
					/>
				</s:a>
				<s:a href="%{localeVI}" style="padding:4px;">
					<img src="assets/app/Vietnamese.png" height="24"
						style="display: inline-block"
					/>
				</s:a>
			</div>

		</div>
	</div>
</div>

