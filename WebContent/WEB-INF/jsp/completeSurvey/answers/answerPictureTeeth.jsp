<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/general/includes.jsp" %>

<script src="/DentaSurvey/resources/js/imageMapster/jquery.imagemapster.js" type="text/javascript"></script>
<script src="/DentaSurvey/resources/js/imageTeeth.js" type="text/javascript"></script>

<script type="text/javascript">
	DentaSurvey.init();
</script>

<div id="answer-open-panel" class="answer-panel">
	<h3>${question.question}</h3>
	<form:form  modelAttribute="answer" method="POST" 
		action="/DentaSurvey/survey/answerSurvey?surveyId=${surveyId}
				&answeredSurveyId=${answeredSurveyId}
				&page=${page}">
		
		<form:input id="question.questionId" name="question.questionId" 
				path="question.questionId" type="hidden" value="${question.questionId}"/>
		<form:input id="answeredSurvey.answeredSurveyId" name="answeredSurvey.answeredSurveyId" 
				path="answeredSurvey.answeredSurveyId" type="hidden" value="${answeredSurveyId}"/>
	   	
		<div id="teeth-panel">
			<img src="/DentaSurvey/resources/images/teeth2.jpg" usemap="#teeth" style="width:206px;height:359px;">
			<map id="usa_image_map" name="teeth">
			    <area href="#" data-name="TR8" shape="poly" coords="9,155,10,161,15,166,29,164,34,159,38,153,36,146,31,143,13,139,7,142,6,150,9,155">
			    <area href="#" data-name="TR7" shape="poly" coords="14,139,10,135,10,128,9,117,12,113,17,112,34,116,39,120,39,132,34,142,14,139">
			    <area href="#" data-name="TR6" shape="poly" coords="17,112,14,107,14,98,14,88,17,83,24,81,36,84,46,90,43,102,42,112,34,116,17,112">
			 	<area href="#" data-name="TR5" shape="poly" coords="24,81,22,77,21,69,27,63,44,67,33,62,48,69,50,73,48,80,45,83,36,83,24,81">
				<area href="#" data-name="TR4" shape="poly" coords="32,62,29,57,29,47,42,42,57,52,57,61,51,65,44,66,32,62">
				<area href="#" data-name="TR3" shape="poly" coords="42,42,40,33,43,24,58,23,62,29,64,41,60,47,48,45,42,42">
				<area href="#" data-name="TR2" shape="poly" coords="58,23,59,18,68,13,75,14,77,31,70,34,62,28,58,23">
				<area href="#" data-name="TR1" shape="poly" coords="75,13,79,9,96,4,101,8,101,14,96,28,86,30,77,18,75,13">
				<area href="#" data-name="TL1" shape="poly" coords="102,7,108,5,119,7,125,17,124,23,115,31,108,31,104,26,101,13, 102,7">
				<area href="#" data-name="TL2" shape="poly" coords="127,17,135,16,142,21,143,27,140,32,131,37,125,34,125,23,127,17">
				<area href="#" data-name="TL3" shape="poly" coords="145,26,154,26,161,32,161,45,154,49,142,49,138,44,141,32,145,26">
				<area href="#" data-name="TL4" shape="poly" coords="161,47,172,51,172,61,164,68,150,68,145,64,145,57,154,49,161,47">	
				<area href="#" data-name="TL5" shape="poly" coords="165,68,174,68,180,74,177,84,163,87,153,84,152,74,165,68">	
				<area href="#" data-name="TL6" shape="poly" coords="178,86,186,89,189,97,188,110,183,114,171,118,162,116,157,101,157,92,166,88,178,86">	
				<area href="#" data-name="TL7" shape="poly" coords="183,114,191,115,196,123,193,137,190,141,173,145,183,144,174,146,162,135,163,123,171,118,183,114">	
				<area href="#" data-name="TL8" shape="poly" coords="191,141,197,145,197,157,190,167,182,167,169,161,168,151,175,146,191,141">		   
		
			    <area href="#" data-name="DR8" shape="poly" coords="31,183,35,189,36,205,30,210,15,211,10,208,12,190,18,183,31,183">
			    <area href="#" data-name="DR7" shape="poly" coords="31,211,39,218,39,236,34,242,24,245,16,245,11,239,11,219,18,212,31,211">
			    <area href="#" data-name="DR6" shape="poly" coords="33,243,45,250,48,270,40,277,25,277,18,269,17,253,25,246,33,243">
			 	<area href="#" data-name="DR5" shape="poly" coords="46,277,54,288,54,293,42,301,31,300,28,292,28,284,34,278,46,277">
				<area href="#" data-name="DR4" shape="poly" coords="42,301,55,301,60,304,60,311,56,318,51,321,43,321,37,313,37,308,42,301">
				<area href="#" data-name="DR3" shape="poly" coords="56,318,66,316,70,318,71,330,68,334,59,338,52,335,53,321,56,318">
				<area href="#" data-name="DR2" shape="poly" coords="69,332,77,323,81,323,84,326,85,340,80,342,73,341,69,332">
				<area href="#" data-name="DR1" shape="poly" coords="85,336,89,326,95,325,101,336,101,340,97,343,89,343,85,340">
				<area href="#" data-name="DL1" shape="poly" coords="101,340,102,335,105,326,110,326,116,334,116,338,113,342,106,343,101,340">
				<area href="#" data-name="DL2" shape="poly" coords="117,338,117,326,120,321,125,321,131,330,130,336,123,340,117,338">
				<area href="#" data-name="DL3" shape="poly" coords="130,329,130,316,133,312,145,314,148,317,148,329,143,334,131,331,130,329">
				<area href="#" data-name="DL4" shape="poly" coords="144,314,140,307,140,302,145,296,154,296,160,299,162,304,162,311,156,317,148,317,144,314">	
				<area href="#" data-name="DL5" shape="poly" coords="158,298,146,290,145,285,149,281,150,274,158,273,167,275,171,282,171,289,163,298,158,298">	
				<area href="#" data-name="DL6" shape="poly" coords="160,273,151,268,154,247,160,241,171,241,176,243,183,250,180,268,177,272,173,274,162,274">	
				<area href="#" data-name="DL7" shape="poly" coords="164,240,160,236,163,215,167,210,184,211,189,216,188,235,186,241,183,242,176,243,164,240">	
				<area href="#" data-name="DL8" shape="poly" coords="171,209,168,205,170,188,174,182,186,182,191,187,193,203,189,210,185,212,171,209">
			</map>
		</div>

		<spring:message code="button.next" var="save"/>
		<input id="teeth-submit-button" class="form-button greyButton" 
		            			type="submit" value="${save}">		           
	</form:form>
</div>

