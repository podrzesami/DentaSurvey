var DentaSurvey = DentaSurvey || (function(){
	return {
		init: function(surveyId, title,  question, type, category, warning) {
			$(document).ready(function(){			 
				$("#manageQuestionGrid").jqGrid({
					url: '/DentaSurvey/manage/question/questions?id=' + surveyId,					
					datatype: 'json',
					colNames:[
					          "",
					           question,
					           category,
					           type
					],
					colModel:[
					    {name:'questionId', index:'guestionId', jsonmap:'questionId', hidden:true },    
						{name:'question', index:'question', sortable:true, jsonmap:'question', 
							width:'60%'},
						{name:'questionCategory.category', index:'questionCategory.category', 
								sortable:false, jsonmap:'questionCategory.category', width:'20%'},
						{name:'questionType.type', index:'questionType.type', 
								sortable:false, jsonmap:'questionType.type', width:'20%'}
					], 
					jsonReader:{
						id:"questionId",
					    root: "rows", 
					    page: "page", 
					    total: "total", 
					    records: "records",
					},
					pager: '#manageQuestionPager',
					sortname: 'question',
					rowNum:10, 
					height:'75%',	
					viewrecords: true, 					
					sortorder: "asc", 
					caption: title ,
					shrinkToFit:true,
					autowidth:true,
				}).navGrid('#manageQuestionPager',
						{edit:false,add:false,del:false,search:false})				
				.navButtonAdd('#manageQuestionPager',{
					   caption:"Info", 
						   buttonicon:"ui-icon-info", 
						   onClickButton: function(){ 
							   var selr = jQuery('#manageQuestionGrid').jqGrid('getGridParam', 'selrow');
							   if(selr==null) {
								   alert(warning);
							   } else {
								   window.location.href = 
									   ("/DentaSurvey/manage/question/get?id=" + selr);
							   }
						   }, 
						   position:"last"
				})
				.navButtonAdd('#manageQuestionPager',{
					   caption:"Add", 
					   buttonicon:"ui-icon-plus", 
					   onClickButton: function(){ 
						   window.location.href = ("/DentaSurvey/manage/question/add?surveyId=" + surveyId);
					   }, 
					   position:"last"
				})
				.navButtonAdd('#manageQuestionPager',{
					   caption:"Edit", 
						   buttonicon:"ui-icon-pencil", 
						   onClickButton: function(){ 
							   var selr = jQuery('#manageQuestionGrid').jqGrid('getGridParam', 'selrow');
							   if(selr==null) {
								   alert(warning);
							   } else {
								   window.location.href = ("/DentaSurvey/manage/question/update?surveyId=" + surveyId 
										   + "&id=" + selr);
							   }
						   }, 
						   position:"last"
				})
				.navButtonAdd('#manageQuestionPager',{
				   caption:"Del", 
					   buttonicon:"ui-icon-trash", 
					   onClickButton: function(){ 
						   var selr = jQuery('#manageQuestionGrid').jqGrid('getGridParam', 'selrow');
						   if(selr==null) {
							   alert(warning);
						   } else {
							   window.location.href = ("/DentaSurvey/manage/question/delete?surveyId=" + surveyId
									   + "&id=" + selr);
						   }
					   }, 
					   position:"last"
				});
				
				$(window).bind('resize', function() {
					var width = $("#manage-question-container").width();
				    if(width!=null){
				    	$("#manageQuestionGrid").jqGrid('setGridWidth', width, true);				    	
				    }
				    else {
				    	$("#manageQuestionGrid").jqGrid('setGridWidth', 900, true);
				    }
				}).trigger('resize');
			});
		},
	};
}());

