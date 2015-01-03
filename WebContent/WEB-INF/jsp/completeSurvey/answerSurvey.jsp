<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/general/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/general/headerSimple.jsp" %>

<div id="patient-update-panel" class="answer-panel">
	<h3><spring:message code="answerSurvey.patientTitle"/></h3>
	<form:form  modelAttribute="answeredSurvey" method="POST" action="/DentaSurvey/survey/updateAnsweredSurvey?surveyId=${surveyId}">
		<form:input id="patientData.patientId" name="patientData.patientId" 
					path="patientData.patientId" type="hidden" value="${patientId}"/>
	
	   	<table>
	   		<tr>		    
		        <td class="ans-label-column">
		        	<form:label class="answer-form-label" path="referredBy">
		        		<spring:message code="answeredSurvey.referredBy"/>:
		        	</form:label></td>
		        <td class="ans-input-column">
		        	<form:input class="answer-form-input" required="required" 
		        		maxlength="128" type="text" path="referredBy"/></td>
		    </tr>
		    <tr>		    
		        <td class="ans-label-column">
		        	<form:label class="answer-form-label" path="medicalProblem">
		        		<spring:message code="answeredSurvey.medicalProblem"/>:
		        	</form:label></td>
		        <td class="ans-input-column">
		        	<form:input class="answer-form-input" required="required" 
		        			maxlength="256" type="text" path="medicalProblem"/></td>
		    </tr>
		    <tr>
		    	<td></td>
		         <td class="ans-button-column">
		        	<spring:message code="button.next" var="save"/>
		            <input id="ansSurvey-submit-button" class="answer-form-button greyButton" 
		            			type="submit" value="${save}">		           
		        </td>
		    </tr>
		</table>  			

	</form:form>
</div>
