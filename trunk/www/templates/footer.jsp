<%@page import="com.duke.nurseryschool.helper.Helper"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="bottom-nav footer">
	<%=Helper.calculateCurrentYear()%>
	&copy; <s:text name="global.footer.developedBy" />
</div>

