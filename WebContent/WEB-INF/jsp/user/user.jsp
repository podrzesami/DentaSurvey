<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/general/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/general/header.jsp" %>

<head>
	<script src="/DentaSurvey/resources/js/jqGrid/grid.locale-pl.js" type="text/javascript"></script>
	<script src="/DentaSurvey/resources/js/jqGrid/jquery.jqGrid.min.js" type="text/javascript"></script>
	<script src="/DentaSurvey/resources/js/user.js" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css" href="/DentaSurvey/resources/css/jqGrid/ui.jqgrid.css">
	<link rel="stylesheet" type="text/css" href="/DentaSurvey/resources/css/jqGrid/jquery-ui.css">
	
	<spring:message code="user.users" var="title"/>
	<spring:message code="user.username" var="username"/>
	<spring:message code="user.role" var="role"/>
	<spring:message code="jqgrid.warning" var="warning"/>
	
	<script type="text/javascript">
		DentaSurvey.init(
				'${title}',
				'${username}',
				'${role}',		
				'${warning}'
				);
	</script>
</head>
<div class="manage-panel">
	<h3><spring:message code="user.pageTitle"/></h3>
	<div id="manageUserContainer" class="jqgrid-container">
		<table id="manageUserGrid"></table>
		<div id="manageUserPager"></div>
	</div>
</div>