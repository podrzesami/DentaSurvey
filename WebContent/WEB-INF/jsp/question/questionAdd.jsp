<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/general/includes.jsp" %>

<div id="survey-add-panel" class="manage-panel">
	<h3><spring:message code="question.addTitle"/></h3>
	<form:form  modelAttribute="question" method="POST" action="/DentaSurvey/manage/question/add">
		<form:input id="survey.surveyId" name="survey.surveyId" 
				path="survey.surveyId" type="hidden" value="${surveyId}"/>
		<form:input id="questionId" name="questionId" path="questionId" type="hidden" value="${questionId}"/>
	   	<table>
		    <tr>		    
		        <td><form:label class="form-label" path="question">
		        		<spring:message code="question.question"/>:
		        	</form:label></td>
		        <td><form:input class="form-input" type="text" 
		        		required="required" maxlength="512" 
		        		path="question"/></td>
		    </tr>
		    <tr>
		    	<td><form:label class="form-label"  path="questionCategory.category">
						<spring:message code="question.category"/>:
					</form:label></td>
		        <td>	
			       	<form:select class="form-input" path="questionCategory.category">
						<c:forEach var="el" items="${categories}">
							<form:option value="${el.category}"/>
						</c:forEach>
					</form:select>
				</td>
		    </tr>
		    <tr>
		    	<td><form:label class="form-label"  path="questionType.type">
						<spring:message code="question.type"/>:
					</form:label></td>
		        <td>	
			       	<form:select class="form-input" path="questionType.type">
						<c:forEach var="el" items="${types}">
							<form:option value="${el.type}"/>
						</c:forEach>
					</form:select>
				</td>
		    </tr>
		    <tr>
		        <td colspan="2">
		        	<spring:message code="button.submit" var="save"/>
		            <input id="question-submit-button" class="form-button greyButton" 
		            			type="submit" value="${save}">		           
		        </td>
		    </tr>
		</table>  			
	</form:form>
</div>
