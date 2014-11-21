DentaSurvey = {
	init: function() {
		$(document).ready(function() {
			$("#adminPanelButton").click(function() {
				location.href = "/DentaSurvey/manage/";
		    }); 
			$("#patientPanelButton").click(function() {
				location.href = "/DentaSurvey/survery/selectSurvery";
		    });
			$("#patientPanel").click(function() {
				location.href = "/DentaSurvey/survery/selectSurvery";
		    });
		});
	},
};

