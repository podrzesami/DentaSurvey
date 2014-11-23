<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/general/includes.jsp" %>

<div id="survey-add-panel" class="manage-panel">
	<h3><spring:message code="survey.addTitle"/></h3>
	<form:form  modelAttribute="survey" method="POST" action="/DentaSurvey/manage/survey/add">
		<form:input id="surveyId" name="surveyId" path="surveyId" type="hidden" value="${surveyId}"/>
	   	<table>
		    <tr>		    
		        <td><form:label class="form-label" path="title">
		        		<spring:message code="survey.title"/>
		        	</form:label></td>
		        <td><form:input class="form-input" type="text" 
		        		path="title"/></td>
		    </tr>
		    <tr>
		    	<td><form:label class="form-label"  path="language.language">
						<spring:message code="survey.language"/>
					</form:label></td>
		        <td>	
			       	<form:select class="form-input" path="language.language">
						<c:forEach var="el" items="${languages}">
							<form:option value="${el.language}"/>
						</c:forEach>
					</form:select>
				</td>
		    </tr>
		    <tr>
		        <td colspan="2">
		        	<spring:message code="button.submit" var="save"/>
		            <input class="form-button greyButton" type="submit" value="${save}">		           
		        </td>
		    </tr>
		</table>  			
	</form:form>
</div>
