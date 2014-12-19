<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/general/includes.jsp" %>

<script type="text/javascript" src="/DentaSurvey/resources/js/selectSurvey.js"></script>

<c:forEach var="el" items="${surveys}">
	<script type="text/javascript">
		DentaSurvey.init(
				'${el.title}',
				'${el.surveyId}');
	</script>
</c:forEach>

<h3><spring:message code="selectSurvey.title"/></h3>

<div id="selectSurveyPanel">

</div>