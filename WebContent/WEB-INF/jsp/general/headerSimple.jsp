<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<link rel="stylesheet" type="text/css" href="/DentaSurvey/resources/css/definedObStyle.css">
<link rel="stylesheet" type="text/css" href="/DentaSurvey/resources/css/classStyle.css">
<link rel="stylesheet" type="text/css" href="/DentaSurvey/resources/css/buttonStyle.css">
<link rel="stylesheet" type="text/css" href="/DentaSurvey/resources/css/idStyle.css">
<script type="text/javascript" src="/DentaSurvey/resources/js/jQuery/jquery-1.11.0.min.js"></script>

<div class="header">
	<div class="top-left-corner">
		<div class="logoutUrl">
			<a href="<c:url value="/j_spring_security_logout"/>"> 
				<spring:message code="login.logout"/>
			</a>
		</div>
	</div>
	
	<form action="${logoutUrl}" method="post" id="logoutForm">
	  <input type="hidden" 
		name="${_csrf.parameterName}"
		value="${_csrf.token}" />
	</form>
	
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>
</div>
