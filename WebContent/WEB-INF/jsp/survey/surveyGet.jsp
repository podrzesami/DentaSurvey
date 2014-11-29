<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/general/includes.jsp" %>

<head>
	<script src="/DentaSurvey/resources/js/jqGrid/grid.locale-pl.js" type="text/javascript"></script>
	<script src="/DentaSurvey/resources/js/jqGrid/jquery.jqGrid.min.js" type="text/javascript"></script>
	<script src="/DentaSurvey/resources/js/question.js" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css" href="/DentaSurvey/resources/css/jqGrid/ui.jqgrid.css">
	<link rel="stylesheet" type="text/css" href="/DentaSurvey/resources/css/jqGrid/jquery-ui.css">
	
	<spring:message code="question.questions" var="title"/>
	<spring:message code="question.question" var="question"/>
	<spring:message code="question.type" var="type"/>
	<spring:message code="question.category" var="category"/>
	<spring:message code="jqgrid.warning" var="warning"/>
	
	<script type="text/javascript">
		DentaSurvey.init(
				'${survey.surveyId}',
				'${title}',
				'${question}',
				'${type}',
				'${category}',
				'${warning}'
				);
	</script>
</head>

<div id="question-panel" class="manage-panel">
	<h3><spring:message code="survey.survey"/></h3>
	   	<table>
		    <tr>		    
		        <td class="object-label"><spring:message code="survey.title"/>:</td>
		        <td class="object-value">${survey.title}</td>
		    </tr>
		    <tr>
		        <td class="object-label"><spring:message code="survey.language"/>:</td>
		        <td class="object-value">${survey.language.language}</td>
		    </tr>
		</table>  	
	<br/>
	<div id="manage-question-container" class="jqgrid-container">
		<table id="manageQuestionGrid"></table>
		<div id="manageQuestionPager"></div>
	</div>
	
</div>