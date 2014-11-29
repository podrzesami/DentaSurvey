<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/general/includes.jsp" %>

<div id="patient-update-panel" class="answer-panel">
	<h3><spring:message code="answerSurvey.patientTitle"/></h3>
	<form:form  modelAttribute="answeredSurvey" method="POST" action="/DentaSurvey/survey/updateAnsweredSurvey?surveyId=${surveyId}">
		<form:input id="patientData.patientId" name="patientData.patientId" 
					path="patientData.patientId" type="hidden" value="${patientId}"/>
	
	   	<table>
	   		<tr>		    
		        <td><form:label class="answer-form-label" path="refferedBy">
		        		<spring:message code="answeredSurvey.refferedBy"/>:
		        	</form:label></td>
		        <td><form:input class="answer-form-input" type="text" path="refferedBy"/></td>
		    </tr>
		    <tr>		    
		        <td><form:label class="answer-form-label" path="medicalProblem">
		        		<spring:message code="answeredSurvey.medicalProblem"/>:
		        	</form:label></td>
		        <td><form:input class="answer-form-input" type="text" path="medicalProblem"/></td>
		    </tr>
		    <tr>
		        <td colspan="2">
		        	<spring:message code="button.next" var="save"/>
		            <input id="ansSurvey-submit-button" class="answer-form-button greyButton" 
		            			type="submit" value="${save}">		           
		        </td>
		    </tr>
		</table>  			
	</form:form>
</div>
