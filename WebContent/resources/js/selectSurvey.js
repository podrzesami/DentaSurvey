DentaSurvey = {
	init: function(title, surveyId) {
		$(document).ready(function() {
			//Create an input type dynamically.   
			var element = document.createElement("input");
			//Assign different attributes to the element. 
			element.type = "button";
			element.value = title; 
			element.className = "blueButton select-survey";
			element.name = surveyId; 
			
			element.onclick = function() { // Note this is a function
				location.href = "/DentaSurvey/survey/start?surveyId=" + surveyId;
			};				
			var div = $("#selectSurveyPanel");
			div.append(element);
			div.append("<br \>");
		});
	}
};