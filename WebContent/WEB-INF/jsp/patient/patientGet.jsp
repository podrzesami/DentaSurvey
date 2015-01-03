<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/general/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/general/headerSimple.jsp" %>

<head>
	<script src="/DentaSurvey/resources/js/jqGrid/grid.locale-pl.js" type="text/javascript"></script>
	<script src="/DentaSurvey/resources/js/jqGrid/jquery.jqGrid.min.js" type="text/javascript"></script>
	<script src="/DentaSurvey/resources/js/answeredSurvey.js" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css" href="/DentaSurvey/resources/css/jqGrid/ui.jqgrid.css">
	<link rel="stylesheet" type="text/css" href="/DentaSurvey/resources/css/jqGrid/jquery-ui.css">
	
	<spring:message code="answeredSurvey.answeredSurveys" var="title"/>
	<spring:message code="answeredSurvey.date" var="date"/>
	<spring:message code="answeredSurvey.referredBy" var="refferedBy"/>
	<spring:message code="answeredSurvey.medicalProblem" var="medicalProblem"/>			
	<spring:message code="jqgrid.warning" var="warning"/>
	
	<script type="text/javascript">
		DentaSurvey.init(
				'${patient.patientId}',
				'${title}',
				'${refferedBy}',
				'${date}',
				'${medicalProblem}',				
				'${warning}'
				);
	</script>
</head>

<div id="answeredSurvey-panel" class="manage-panel">
	<h3><spring:message code="patient.patient"/></h3>
	   	<table>
		    <tr>		    
		        <td class="object-label"><spring:message code="patient.id"/>:</td>
		        <td class="object-value">${patient.patientId}</td>
		    </tr>
		    <tr>		    
		        <td class="object-label"><spring:message code="patient.surname"/>:</td>
		        <td class="object-value">${patient.surname}</td>
		    </tr>
		    <tr>		    
		        <td class="object-label"><spring:message code="patient.name"/>:</td>
		        <td class="object-value">${patient.name}</td>
		    </tr>
		    <tr>		    
		        <td class="object-label"><spring:message code="patient.age"/>:</td>
		        <td class="object-value">${patient.age}</td>
		    </tr>		    
		    <tr>		    
		        <td class="object-label"><spring:message code="patient.address"/>:</td>
		        <td class="object-value">${patient.address}</td>
		    </tr>
		    <tr>		    
		        <td class="object-label"><spring:message code="patient.phone"/>:</td>
		        <td class="object-value">${patient.phoneNumber}</td>
		    </tr>
		    <tr>		    
		        <td class="object-label"><spring:message code="patient.nrOfKids"/>:</td>
		        <td class="object-value">${patient.numberOfKids}</td>
		    </tr>
		    <tr>		    
		        <td class="object-label"><spring:message code="patient.occupation"/>:</td>
		        <td class="object-value">${patient.occupation}</td>
		    </tr>			    		    		    
		</table>	  	
	<br/>
	<div id="manage-answeredSurvey-container" class="jqgrid-container">
		<table id="manageAnsweredSurveyGrid"></table>
		<div id="manageAnsweredSurveyPager"></div>
	</div>	
</div>