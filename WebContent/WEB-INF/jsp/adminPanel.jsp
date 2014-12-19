<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/general/includes.jsp" %>

<head>
	<link rel="stylesheet" type="text/css" href="/DentaSurvey/resources/css/idStyle.css">
	<script type="text/javascript" src="/DentaSurvey/resources/js/managementPanel.js"></script>
	<script type="text/javascript">
		DentaSurvey.init();
	</script>
</head>

<c:if test="${role != '[ROLE_OWNER]'}">	
	<div class="button-1-row-div">
		<div class="leftButton-1-2">
			<button id="manageSurveys" class="blueButton">
				<spring:message code="manage.manageSurveys"/>
			</button>
		</div>
		<div class="rightButton-2-2">	
			<button id="managePatiens" class="redButton">
				<spring:message code="manage.managePatients"/>
			</button>
		</div>
	</div>
</c:if>	
<c:if test="${role == '[ROLE_OWNER]'}">	
	<div class="button-2-row-div-1">
		<div class="leftButton-1-2">
			<button id="manageSurveys2" class="blueButton">
				<spring:message code="manage.manageSurveys"/>
			</button>
		</div>
		<div class="rightButton-2-2">	
			<button id="managePatiens2" class="redButton">
				<spring:message code="manage.managePatients"/>
			</button>
		</div>
	</div>
	<div class="button-2-row-div-2">
		<button id="manageUses" class="leftButton-1-2 greenButton">
			<spring:message code="manage.manageUsers"/>
		</button>
	</div>		
</c:if>