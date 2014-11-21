<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/general/includes.jsp" %>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
	<body>
		<h3>lista ankiet:</h3>
		<table border="1">
			<tr>
				<td>id</td>
				<td>nazwa</td>
				<td>jezyk</td>
				<td></td>
			</tr>

			<c:forEach var="el" items="${surveys}">
				<tr>
					<td>${el.surveyId}</td>
					<td>${el.title}</td>
					<td>${el.language.language}</td>
					<td><a href="/DentaSurvey/main/${el.surveyId}">usuń</a></td>
				</tr>
			</c:forEach>
		</table>

		<a href="/DentaSurveyMain">powrót</a>
	</body>
</html>