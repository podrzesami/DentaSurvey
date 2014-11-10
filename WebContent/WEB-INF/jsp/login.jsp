<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@page session="true"%>
<html>
<head>
<title><spring:message code="login.title"/></title>
</head>
<body onload='document.loginForm.username.focus();'>
 
	<div id="login-box">
 		<h3><spring:message code="login.login"/></h3>
 
		<c:if test="${not empty error}">
			<div class="error"><spring:message code="login.error"/></div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg"><spring:message code="login.msg"/></div>
		</c:if>
 
		<form name='loginForm'
		  action="<c:url value='/j_spring_security_check' />" method='POST'>
 
		<table>
			<tr>
				<td><spring:message code="login.user"/></td>
				<td><input type='text' name='username'></td>
			</tr>
			<tr>
				<td><spring:message code="login.password"/></td>
				<td><input type='password' name='password' /></td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit"
				  value="submit" /></td>
			</tr>
		  </table>
 
		  <input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
 
		</form>
	</div>
 
</body>
</html>