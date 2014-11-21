<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/general/includes.jsp" %>
<%@page session="true"%>
<head>
<link rel="stylesheet" type="text/css" href="/DentaSurvey/resources/css/loginStyle.css">
</head>
<body onload='document.loginForm.username.focus();'>

	<div id="login-box" class="panel">
 		<h3><spring:message code="login.login"/></h3>
 
		<c:if test="${not empty error}">
			<div class="error panel"><spring:message code="login.error"/></div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg panel"><spring:message code="login.msg"/></div>
		</c:if>
 
		<form name='loginForm'
		  action="<c:url value='/j_spring_security_check' />" method='POST'>
 		<div>
			<div class="login-lebel"><spring:message code="login.user"/></div>
			<div class="login-box">
				<input class="login-input" type='text' name='username'>
			</div>
		</div>
		<div>
			<div class="login-lebel"><spring:message code="login.password"/></div>
			<div class="login-box">
				<input class="login-input" type='password' name='password' />
			</div>
		</div>
		<spring:message code="login.submit" var="submitText"/>
		<div><input class="login-button greyButton" name="submit" type="submit" value="${submitText}"/></div>
  		<div><input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" /></div>
 
		</form>
	</div> 
</body>