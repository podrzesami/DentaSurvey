DentaSurvey = {
	init: function() {
		$(document).ready(function() {
			$("#manageUses").click(function() {
				location.href = "/DentaSurvey/manage/user";
		    }); 
			$("#manageSurveys").click(function() {
				location.href = "/DentaSurvey/manage/survey";
		    });
			$("#managePatiens").click(function() {
				location.href = "/DentaSurvey/manage/patient";
		    });
			$("#manageSurveys2").click(function() {
				location.href = "/DentaSurvey/manage/survey";
		    });
			$("#managePatiens2").click(function() {
				location.href = "/DentaSurvey/manage/patient";
		    });
		});
	},
};

