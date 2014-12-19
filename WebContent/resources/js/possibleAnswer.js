var DentaSurvey = DentaSurvey || (function(){
	return {
		init: function(questionId, title,  possibleAnswer, warning) {
			$(document).ready(function(){			 
				$("#managePossibleAnswerGrid").jqGrid({
					url: '/DentaSurvey/manage/possibleAnswer/answers?id=' + questionId,					
					datatype: 'json',
					colNames:[
					          "",
					           possibleAnswer,
					],
					colModel:[
					    {name:'possibleAnswerId', index:'possibleAnswerId', 
					    	jsonmap:'possibleAnswerId', hidden:true },    
						{name:'possibleAnswer', index:'possibleAnswer', 
					    		sortable:true, jsonmap:'possibleAnswer', width:'100%'}
					], 
					jsonReader:{
						id:"possibleAnswerId",
					    root: "rows", 
					    page: "page", 
					    total: "total", 
					    records: "records",
					},
					pager: '#managePossibleAnswerPager',
					sortname: 'question',
					rowNum:10, 
					width:'900',
					height:'75%',	
					viewrecords: true, 					
					sortorder: "asc", 
					caption: title,
					shrinkToFit:true,
					autowidth:true,					
				}).navGrid('#managePossibleAnswerPager',
						{edit:false,add:false,del:false,search:false})		
				.navButtonAdd('#managePossibleAnswerPager',{
					   caption:"Add", 
					   buttonicon:"ui-icon-plus", 
					   onClickButton: function(){ 
						   window.location.href = ("/DentaSurvey/manage/possibleAnswer/add?questionId=" + questionId);
					   }, 
					   position:"last"
				})
				.navButtonAdd('#managePossibleAnswerPager',{
					   caption:"Edit", 
						   buttonicon:"ui-icon-pencil", 
						   onClickButton: function(){ 
							   var selr = jQuery('#managePossibleAnswerGrid').jqGrid('getGridParam', 'selrow');
							   if(selr==null) {
								   alert(warning);
							   } else {
								   window.location.href = ("/DentaSurvey/manage/possibleAnswer/update?questionId=" + questionId 
										   + "&id=" + selr);
							   }
						   }, 
						   position:"last"
				})
				.navButtonAdd('#managePossibleAnswerPager',{
				   caption:"Del", 
					   buttonicon:"ui-icon-trash", 
					   onClickButton: function(){ 
						   var selr = jQuery('#managePossibleAnswerGrid').jqGrid('getGridParam', 'selrow');
						   if(selr==null) {
							   alert(warning);
						   } else {
							   window.location.href = ("/DentaSurvey/manage/possibleAnswer/delete?questionId=" + questionId
									   + "&id=" + selr);
						   }
					   }, 
					   position:"last"
				});
				$(window).bind('resize', function() {
					var width = $("#manage-possibleAnswer-container").width();
				    if(width!=null){
				    	$("#managePossibleAnswerGrid").jqGrid('setGridWidth', width, true);
				    }
				    else {
				    	$("#managePossibleAnswerGrid").jqGrid('setGridWidth', 900, true);
				    }
				}).trigger('resize');
			});
		},
	};
}());

