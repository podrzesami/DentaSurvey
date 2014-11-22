<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/general/includes.jsp" %>

<head>
	<script src="/DentaSurvey/resources/js/jqGrid/grid.locale-pl.js" type="text/javascript"></script>
	<script src="/DentaSurvey/resources/js/jqGrid/jquery.jqGrid.min.js" type="text/javascript"></script>
	<script src="/DentaSurvey/resources/js/survey.js" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css" href="/DentaSurvey/resources/css/jqGrid/ui.jqgrid.css">
	<link rel="stylesheet" type="text/css" href="/DentaSurvey/resources/css/jqGrid/jquery-ui.css">
	
	<spring:message code="survey.surveys" var="tableTitle"/>
	<spring:message code="survey.title" var="title"/>
	<spring:message code="survey.language" var="language"/>
	
	<script type="text/javascript">
		DentaSurvey.init(
				'${title}',
				'${language}',
				'${tableTitle}'				
				);
	</script>
</head>
<div class="manage-panel">
	<h3><spring:message code="survey.pageTitle"/></h3>
	<div class="jqgrid-container">
		<table id="manageSurveyGrid"></table>
		<div id="manageSurveyPager"></div>
	</div>
</div>