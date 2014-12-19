<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/general/includes.jsp" %>

<head>
	<script src="/DentaSurvey/resources/js/jqGrid/grid.locale-pl.js" type="text/javascript"></script>
	<script src="/DentaSurvey/resources/js/jqGrid/jquery.jqGrid.min.js" type="text/javascript"></script>
	<script src="/DentaSurvey/resources/js/answer.js" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css" href="/DentaSurvey/resources/css/jqGrid/ui.jqgrid.css">
	<link rel="stylesheet" type="text/css" href="/DentaSurvey/resources/css/jqGrid/jquery-ui.css">
	
	<spring:message code="answer.answers" var="title"/>
	<spring:message code="answer.answer" var="answer"/>
	<spring:message code="question.question" var="question"/>
	<spring:message code="question.type" var="type"/>
	<spring:message code="jqgrid.warning" var="warning"/>
	
	<script type="text/javascript">
		DentaSurvey.init(
				'${answeredSurvey.answeredSurveyId}',
				'${title}',
				'${question}',
				'${answer}',
				'${type}',
				'${warning}'
				);
	</script>
</head>

<div id="question-panel" class="manage-panel">
	<h3><spring:message code="question.question"/></h3>
	   	<table>
		    <tr>		    
		        <td class="object-label"><spring:message code="answeredSurvey.date"/>:</td>
		        <td class="object-value">${answeredSurvey.date}</td>
		    </tr>
		    <tr>
		        <td class="object-label"><spring:message code="answeredSurvey.referredBy"/>:</td>
		        <td class="object-value">${answeredSurvey.referredBy}</td>
		    </tr>
		    <tr>
		        <td class="object-label"><spring:message code="answeredSurvey.medicalProblem"/>:</td>
		        <td class="object-value">${answeredSurvey.medicalProblem}</td>
		    </tr>
		</table>  
			
		<div id="manage-answer-container" class="jqgrid-container">
			<table id="manageAnswerGrid"></table>
			<div id="manageAnswerPager"></div>
		</div>	
	
</div>