<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/general/includes.jsp" %>

<div id="answer-open-panel" class="answer-panel">
	<h3>${question.question}</h3>
	<form:form  modelAttribute="answer" method="POST" 
		action="/DentaSurvey/survey/answerSurvey?surveyId=${surveyId}
				&answeredSurveyId=${answeredSurveyId}
				&page=${page}">
		
		<form:input id="question.questionId" name="question.questionId" 
				path="question.questionId" type="hidden" value="${question.questionId}"/>
		<form:input id="answeredSurvey.answeredSurveyId" name="answeredSurvey.answeredSurveyId" 
				path="answeredSurvey.answeredSurveyId" type="hidden" value="${answeredSurveyId}"/>
	   	
	   	<spring:message code="answerSurvey.yes" var="yes"/>
	   	<spring:message code="answerSurvey.no" var="no"/>
	   	
	   	<div class="select-list-div">
		   	<form:radiobutton path="answer" name="answer" value="${yes}"/>
		   	<form:label class="radio-label" path="answer"> ${yes} </form:label>
		   	<br/>
			<form:radiobutton path="answer" name="answer" value="${no}"/>
		   	<form:label class="radio-label" path="answer"> ${no} </form:label>
		   	<br/>
		</div>
		<br />
		<spring:message code="button.next" var="save"/>
		<input id="open-submit-button" class="form-button greyButton" 
		            			type="submit" value="${save}">		           
	</form:form>
</div>
