<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/general/includes.jsp" %>

<head>
	<script src="/DentaSurvey/resources/js/jqGrid/grid.locale-pl.js" type="text/javascript"></script>
	<script src="/DentaSurvey/resources/js/jqGrid/jquery.jqGrid.min.js" type="text/javascript"></script>
	<script src="/DentaSurvey/resources/js/patient.js" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css" href="/DentaSurvey/resources/css/jqGrid/ui.jqgrid.css">
	<link rel="stylesheet" type="text/css" href="/DentaSurvey/resources/css/jqGrid/jquery-ui.css">
	
	<spring:message code="patient.patients" var="tableTitle"/>
	<spring:message code="patient.name" var="name"/>
	<spring:message code="patient.surname" var="surname"/>
	<spring:message code="patient.address" var="address"/>
	<spring:message code="patient.id" var="id"/>
	<spring:message code="jqgrid.warning" var="warning"/>
	
	<script type="text/javascript">
		DentaSurvey.init(				
				'${tableTitle}',
				'${id}',
				'${name}',
				'${surname}',	
				'${address}',
				'${warning}'
				);
	</script>
</head>
<div class="manage-panel">
	<h3><spring:message code="patient.pageTitle"/></h3>
	<div class="jqgrid-container">
		<table id="managePatientGrid"></table>
		<div id="managePatientPager"></div>
	</div>
</div>