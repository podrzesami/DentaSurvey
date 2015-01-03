<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/general/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/general/headerSimple.jsp" %>

<c:if test="${question.questionType.type=='multichoice'}">
	<%@ include file="/WEB-INF/jsp/completeSurvey/answers/answerMultichoice.jsp" %>		
</c:if>

<c:if test="${question.questionType.type=='open'}">
	<%@ include file="/WEB-INF/jsp/completeSurvey/answers/answerOpen.jsp" %>		
</c:if>

<c:if test="${question.questionType.type=='picture-body'}">
	<%@ include file="/WEB-INF/jsp/completeSurvey/answers/answerPictureBody.jsp" %>		
</c:if>

<c:if test="${question.questionType.type=='picture-teeth'}">
	<%@ include file="/WEB-INF/jsp/completeSurvey/answers/answerPictureTeeth.jsp" %>		
</c:if>

<c:if test="${question.questionType.type=='scale-0-4'}">
	<%@ include file="/WEB-INF/jsp/completeSurvey/answers/answerScale.jsp" %>		
</c:if>

<c:if test="${question.questionType.type=='scale-0-6'}">
	<%@ include file="/WEB-INF/jsp/completeSurvey/answers/answerScale.jsp" %>		
</c:if>

<c:if test="${question.questionType.type=='singlechoice'}">
	<%@ include file="/WEB-INF/jsp/completeSurvey/answers/answerSinglechoice.jsp" %>		
</c:if>

<c:if test="${question.questionType.type=='yes/no'}">
	<%@ include file="/WEB-INF/jsp/completeSurvey/answers/answerYesNo.jsp" %>		
</c:if>