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
	   	
	   	<div class="select-list-div">
	   		<c:forEach var="el" items="${possibleAnswers}">
			   	<form:radiobutton path="answer" name="answer" value="${el.possibleAnswer}"/>
			   	<form:label class="radio-label" path="answer"> ${el.possibleAnswer} </form:label>
			   	<br/>
		   	</c:forEach>

		</div>
		<br />
		<spring:message code="button.next" var="save"/>
		<input id="open-submit-button" class="form-button greyButton" 
		            			type="submit" value="${save}">		           
	</form:form>
</div>
