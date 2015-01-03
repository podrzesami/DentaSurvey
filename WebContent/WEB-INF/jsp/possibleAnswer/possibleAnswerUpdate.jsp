<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/general/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/general/headerSimple.jsp" %>

<div id="survey-add-panel" class="manage-panel">
	<h3><spring:message code="possibleAnswer.addTitle"/></h3>
	<form:form  modelAttribute="possibleAnswer" method="POST" action="/DentaSurvey/manage/possibleAnswer/update">
		<form:input id="question.questionId" name="question.questionId" 
				path="question.questionId" type="hidden" value="${questionId}"/>
		<form:input id="possibleAnswerId" name="possibleAnswerId" 
				path="possibleAnswerId" type="hidden" value="${possibleAnswerId}"/>
	   	<table>
		    <tr>		    
		        <td class="label-column">
		        	<form:label class="form-label" path="possibleAnswer">
		        		<spring:message code="possibleAnswer.possibleAnswer"/>:
		        	</form:label></td>
		        <td class="input-column">	
		        	<form:input class="form-input" type="text" 
		        		required="required" maxlength="100" 
		        		path="possibleAnswer" value="${possibleAnswer.possibleAnswer}"/></td>
		    </tr>
		    <tr>
		     	<td></td>
		        <td class="button-column">
		        	<spring:message code="button.submit" var="save"/>
		            <input id="possibleAnswer-update-button" class="form-button greyButton" 
		            			type="submit" value="${save}">		           
		        </td>
		    </tr>
		</table>  			
	</form:form>
</div>
