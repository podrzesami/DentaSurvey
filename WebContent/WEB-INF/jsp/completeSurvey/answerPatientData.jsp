<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/general/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/general/headerSimple.jsp" %>

<html>
	<div id="patient-update-panel" class="answer-panel">
		<h3><spring:message code="answerSurvey.patientTitle"/></h3>
		<form:form  modelAttribute="patient" method="POST" action="/DentaSurvey/survey/updatePatientData?surveyId=${surveyId}">
		   	<table>
		   		<tr>		    
			        <td class="ans-label-column">
			        	<form:label class="answer-form-label" path="patientId">
			        		<spring:message code="patient.id"/>:
			        	</form:label></td>
			        <td  class="ans-input-column">
			        	<form:input class="answer-form-input" required="required" 
			        		pattern="[0-9]*" size="11" type="text" path="patientId"/></td>
			    </tr>
			    <tr>		    
			        <td class="ans-label-column">
			        	<form:label class="answer-form-label" path="name">
			        		<spring:message code="patient.name"/>:
			        	</form:label></td>
			        <td  class="ans-input-column">
			        	<form:input class="answer-form-input" required="required" 
			        		pattern="[a-zA-ZąćęłńóśżźĄĆĘŁŃÓŚŻŹ]*" maxlength="45" type="text" path="name"/></td>
			    </tr>
			    <tr>		    
			        <td class="ans-label-column">
			        	<form:label class="answer-form-label" path="surname">
			        		<spring:message code="patient.surname"/>:
			        	</form:label></td>
			        <td class="ans-input-column">
			        	<form:input class="answer-form-input" required="required" 
			        		pattern="[a-zA-ZąćęłńóśżźĄĆĘŁŃÓŚŻŹ]*" maxlength="45" type="text" path="surname"/></td>
			    </tr>	
			    <tr>		    
			        <td class="ans-label-column">
			        	<form:label class="answer-form-label" path="age">
			        		<spring:message code="patient.age"/>:
			        	</form:label></td>
			        <td class="ans-input-column">
			        	<form:input class="answer-form-input" required="required" 
			        		min="1" max="110" type="number" path="age"/></td>
			    </tr>	
			    <tr>		    
			        <td class="ans-label-column">
			        	<form:label class="answer-form-label" path="address">
			        		<spring:message code="patient.address"/>:
			        	</form:label></td>
			        <td class="ans-input-column">
			        	<form:input class="answer-form-input" required="required" 
			        		maxlength="90" type="text" path="address"/></td>
			    </tr>		
			    <tr>		    
			        <td class="ans-label-column">
			        	<form:label class="answer-form-label" path="phoneNumber">
			        		<spring:message code="patient.phone"/>:
			        	</form:label></td>
			        <td class="ans-input-column">
			        	<form:input class="answer-form-input" required="required" 
			        		type="tel" path="phoneNumber"/></td>
			    </tr>
			    <tr>		    
			        <td class="ans-label-column">
			        	<form:label class="answer-form-label" path="numberOfKids">
			        		<spring:message code="patient.nrOfKids"/>:
			        	</form:label></td>
			        <td class="ans-input-column">
			        	<form:input class="answer-form-input" min="0" max="80" 
			        		type="number" path="numberOfKids"/></td>
			    </tr>
			    <tr>		    
			        <td class="ans-label-column">
			        	<form:label class="answer-form-label" path="occupation">
			        		<spring:message code="patient.occupation"/>:
			        	</form:label></td>
			        <td class="ans-input-column">
			        	<form:input class="answer-form-input" required="required" 
			        		maxlength="45" type="text" path="occupation"/></td>
			    </tr>
			     <tr>
			     	<td> </td>
			        <td class="ans-button-column">
				    	<spring:message code="button.next" var="save"/>
			            <input id="anspatient-submit-button" class="answer-form-button greyButton" 
			            			type="submit" value="${save}">		           
			        </td>
		    	</tr>		    	    		    
			</table>  			
		</form:form>
	</div>
</html>
