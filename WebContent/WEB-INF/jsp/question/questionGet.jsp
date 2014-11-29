<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/general/includes.jsp" %>

<head>
	<script src="/DentaSurvey/resources/js/jqGrid/grid.locale-pl.js" type="text/javascript"></script>
	<script src="/DentaSurvey/resources/js/jqGrid/jquery.jqGrid.min.js" type="text/javascript"></script>
	<script src="/DentaSurvey/resources/js/possibleAnswer.js" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css" href="/DentaSurvey/resources/css/jqGrid/ui.jqgrid.css">
	<link rel="stylesheet" type="text/css" href="/DentaSurvey/resources/css/jqGrid/jquery-ui.css">
	
	<spring:message code="possibleAnswer.possibleAnswers" var="title"/>
	<spring:message code="possibleAnswer.possibleAnswer" var="possibleAnswer"/>
	<spring:message code="jqgrid.warning" var="warning"/>
	
	<script type="text/javascript">
		DentaSurvey.init(
				'${question.questionId}',
				'${title}',
				'${possibleAnswer}',
				'${warning}'
				);
	</script>
</head>

<div id="question-panel" class="manage-panel">
	<h3><spring:message code="question.question"/></h3>
	   	<table>
		    <tr>		    
		        <td class="object-label"><spring:message code="question.question"/>:</td>
		        <td class="object-value">${question.question}</td>
		    </tr>
		    <tr>
		        <td class="object-label"><spring:message code="question.category"/>:</td>
		        <td class="object-value">${question.questionCategory.category}</td>
		    </tr>
		    <tr>
		        <td class="object-label"><spring:message code="question.type"/>:</td>
		        <td class="object-value">${question.questionType.type}</td>
		    </tr>
		</table>  	
	<c:if test="${question.questionType.type=='multichoice' || question.questionType.type=='singlechoice'}">
		<div id="manage-possibleAnswer-container" class="jqgrid-container">
			<table id="managePossibleAnswerGrid"></table>
			<div id="managePossibleAnswerPager"></div>
		</div>
	</c:if>
	
</div>