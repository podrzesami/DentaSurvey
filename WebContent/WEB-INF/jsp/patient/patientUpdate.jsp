<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/general/includes.jsp" %>

<div id="patient-update-panel" class="manage-panel">
	<h3><spring:message code="patient.updateTitle"/></h3>
	<form:form  modelAttribute="patient" method="POST" action="/DentaSurvey/manage/patient/update">
		<form:input id="patientId" name="patientId" path="patientId" type="hidden" value="${patientId}"/>
	   	<table>
		    <tr>		    
		        <td><form:label class="form-label" path="name">
		        		<spring:message code="patient.name"/>:
		        	</form:label></td>
		        <td><form:input class="form-input" type="text" 
		        		path="name" value="${patient.name}"/></td>
		    </tr>
		    <tr>		    
		        <td><form:label class="form-label" path="surname">
		        		<spring:message code="patient.surname"/>:
		        	</form:label></td>
		        <td><form:input class="form-input" type="text" 
		        		path="surname" value="${patient.surname}"/></td>
		    </tr>	
		    <tr>		    
		        <td><form:label class="form-label" path="age">
		        		<spring:message code="patient.age"/>:
		        	</form:label></td>
		        <td><form:input class="form-input" type="text" 
		        		path="age" value="${patient.age}"/></td>
		    </tr>	
		    <tr>		    
		        <td><form:label class="form-label" path="address">
		        		<spring:message code="patient.address"/>:
		        	</form:label></td>
		        <td><form:input class="form-input" type="text" 
		        		path="address" value="${patient.address}"/></td>
		    </tr>		
		    <tr>		    
		        <td><form:label class="form-label" path="phoneNumber">
		        		<spring:message code="patient.phone"/>:
		        	</form:label></td>
		        <td><form:input class="form-input" type="text" 
		        		path="phoneNumber" value="${patient.phoneNumber}"/></td>
		    </tr>
		    <tr>		    
		        <td><form:label class="form-label" path="numberOfKids">
		        		<spring:message code="patient.nrOfKids"/>:
		        	</form:label></td>
		        <td><form:input class="form-input" type="text" 
		        		path="numberOfKids" value="${patient.numberOfKids}"/></td>
		    </tr>
		    <tr>		    
		        <td><form:label class="form-label" path="occupation">
		        		<spring:message code="patient.occupation"/>:
		        	</form:label></td>
		        <td><form:input class="form-input" type="text" 
		        		path="occupation" value="${patient.occupation}"/></td>
		    </tr>		    	    		    
		    <tr>
		        <td colspan="2">
		        	<spring:message code="button.submit" var="save"/>
		            <input id="patient-submit-button" class="form-button greyButton" 
		            			type="submit" value="${save}">		           
		        </td>
		    </tr>
		</table>  			
	</form:form>
</div>
