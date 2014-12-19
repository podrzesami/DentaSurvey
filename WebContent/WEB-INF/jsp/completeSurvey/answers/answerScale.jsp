<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" type="text/css" href="/DentaSurvey/resources/css/definedObStyle.css">
<link rel="stylesheet" type="text/css" href="/DentaSurvey/resources/css/classStyle.css">
<link rel="stylesheet" type="text/css" href="/DentaSurvey/resources/css/buttonStyle.css">
<link rel="stylesheet" type="text/css" href="/DentaSurvey/resources/css/idStyle.css">
<script type="text/javascript" src="/DentaSurvey/resources/js/jQuery/jquery-1.11.0.min.js"></script>

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
			<c:if test="${question.questionType.type=='scale-0-4'}">
			   	<c:forEach var="i" begin="0" end="4">
				   <form:radiobutton path="answer" name="answer" required="required" value="${i}"/>
				   <form:label class="radio-label" path="answer"> ${i} </form:label>
				</c:forEach>	
			</c:if>	   	
			<c:if test="${question.questionType.type=='scale-0-6'}">
			   	<c:forEach var="i" begin="0" end="6">
				   <form:radiobutton path="answer" name="answer" required="required" value="${i}"/>
				   <form:label class="radio-label" path="answer"> ${i} </form:label>
				</c:forEach>	
			</c:if>	
		</div>
		<br />
		<spring:message code="button.next" var="save"/>
		<input id="open-submit-button" class="form-button greyButton" 
		            			type="submit" value="${save}">		           
	</form:form>
</div>
