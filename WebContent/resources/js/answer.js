var DentaSurvey = DentaSurvey || (function(){
	return {
		init: function(answeredSurveyId, title,  question, answer, type, warning) {
			$(document).ready(function(){			 
				$("#manageAnswerGrid").jqGrid({
					url: '/DentaSurvey/manage/answer/answers?id=' + answeredSurveyId,					
					datatype: 'json',
					colNames:[
					          "",
					          question,
					          answer,
					          type
					],
					colModel:[
					    {name:'answerId', index:'guestionId', jsonmap:'answerId', hidden:true },
						{name:'question.question', index:'question.question', 
							sortable:true, jsonmap:'question.question', width:600},
						{name:'answer', index:'answer', sortable:false, jsonmap:'answer', 
							width:600},
						{name:'question.questionCategory.category', index:'category.category', 
							sortable:true, jsonmap:'question.questionCategory.category', width:200},
					], 
					jsonReader:{
						id:"answerId",
					    root: "rows", 
					    page: "page", 
					    total: "total", 
					    records: "records",
					},
					pager: '#manageAnswerPager',
					sortname: 'question.question',
					rowNum:10, 
					width:'900',
					height:'75%',	
					viewrecords: true, 					
					sortorder: "asc", 
					caption: title 
				}).navGrid('#manageAnswerPager',
						{edit:false,add:false,del:false,search:false});	
			});
		},
	};
}());

