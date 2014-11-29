<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/general/includes.jsp" %>

<div id="patient-update-panel" class="answer-panel">
	<h3><spring:message code="answerSurvey.patientTitle"/></h3>
	<form:form  modelAttribute="patient" method="POST" action="/DentaSurvey/survey/updatePatientData?surveyId=${surveyId}">
	   	<table>
	   		<tr>		    
		        <td><form:label class="answer-form-label" path="patientId">
		        		<spring:message code="patient.id"/>:
		        	</form:label></td>
		        <td><form:input class="answer-form-input" type="text" path="patientId"/></td>
		    </tr>
		    <tr>		    
		        <td><form:label class="answer-form-label" path="name">
		        		<spring:message code="patient.name"/>:
		        	</form:label></td>
		        <td><form:input class="answer-form-input" type="text" path="name"/></td>
		    </tr>
		    <tr>		    
		        <td><form:label class="answer-form-label" path="surname">
		        		<spring:message code="patient.surname"/>:
		        	</form:label></td>
		        <td><form:input class="answer-form-input" type="text" path="surname"/></td>
		    </tr>	
		    <tr>		    
		        <td><form:label class="answer-form-label" path="age">
		        		<spring:message code="patient.age"/>:
		        	</form:label></td>
		        <td><form:input class="answer-form-input" type="text" path="age"/></td>
		    </tr>	
		    <tr>		    
		        <td><form:label class="answer-form-label" path="address">
		        		<spring:message code="patient.address"/>:
		        	</form:label></td>
		        <td><form:input class="answer-form-input" type="text" path="address"/></td>
		    </tr>		
		    <tr>		    
		        <td><form:label class="answer-form-label" path="phoneNumber">
		        		<spring:message code="patient.phone"/>:
		        	</form:label></td>
		        <td><form:input class="answer-form-input" type="text" path="phoneNumber"/></td>
		    </tr>
		    <tr>		    
		        <td><form:label class="answer-form-label" path="numberOfKids">
		        		<spring:message code="patient.nrOfKids"/>:
		        	</form:label></td>
		        <td><form:input class="answer-form-input" type="text" path="numberOfKids"/></td>
		    </tr>
		    <tr>		    
		        <td><form:label class="answer-form-label" path="occupation">
		        		<spring:message code="patient.occupation"/>:
		        	</form:label></td>
		        <td><form:input class="answer-form-input" type="text" path="occupation"/></td>
		    </tr>		    	    		    
		    <tr>
		        <td colspan="2">
		        	<spring:message code="button.next" var="save"/>
		            <input id="patient-submit-button" class="answer-form-button greyButton" 
		            			type="submit" value="${save}">		           
		        </td>
		    </tr>
		</table>  			
	</form:form>
</div>
