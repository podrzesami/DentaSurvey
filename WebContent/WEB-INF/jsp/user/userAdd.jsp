<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/general/includes.jsp" %>

<div id="user-add-panel" class="manage-panel">
	<h3><spring:message code="user.addTitle"/></h3>
	<form:form  modelAttribute="user" method="POST" action="/DentaSurvey/userConfigurafion/add">
		<form:input id="userId" name="userId" path="userId" type="hidden" value="${user.userId}"/>
	   	<table>
		    <tr>		    
		        <td><form:label class="form-label" path="username">
		        		<spring:message code="user.username"/>:
		        	</form:label></td>
		        <td><form:input class="form-input" type="text" 
		        		required="required" maxlength="45" 
		        		path="username"/></td>
		    </tr>
		    <tr>		    
		        <td><form:label class="form-label" path="password">
		        		<spring:message code="user.password"/>:
		        	</form:label></td>
		        <td><form:input class="form-input" type="text" 
		        		required="required" maxlength="45" 
		        		path="password"/></td>
		    </tr>	    
		    <tr>
		    	<td><form:label class="form-label"  path="role.role">
						<spring:message code="user.role"/>:
					</form:label></td>
		        <td>	
			       	<form:select class="form-input" path="role.role">
						<c:forEach var="el" items="${roles}">
							<form:option value="${el.role}"/>
						</c:forEach>
					</form:select>
				</td>
		    </tr>
		    <tr>
		        <td colspan="2">
		        	<spring:message code="button.submit" var="save"/>
		            <input id="user-submit-button" class="form-button greyButton" 
		            		type="submit" value="${save}">		           
		        </td>
		    </tr>
		</table>  			
	</form:form>
</div>
