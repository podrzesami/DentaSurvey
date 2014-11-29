<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/general/includes.jsp" %>

<head>
	<script type="text/javascript" src="/DentaSurvey/resources/js/main.js"></script>
	<script type="text/javascript">
		DentaSurvey.init();
	</script>
</head>

<div class="button-1-row-div">
	<c:if test="${role != '[ROLE_USER]'}">
	<div class="leftButton-1-2">
		<button id="adminPanelButton" class="greenButton ">
			<spring:message code="main.adminPanelButton"/>
		</button>
	</div>
	<div class="rightButton-2-2">	
		<button id="patientPanelButton" class="violetButton">
			<spring:message code="main.patientPanelButton"/>
		</button>
	</div>
	</c:if>
	
	
	<c:if test="${role == '[ROLE_USER]'}">	
		<button id="patientPanel" class="centerButton-1 redButton">
			<spring:message code="main.patientPanelButton"/>
		</button>
	</c:if>
</div>