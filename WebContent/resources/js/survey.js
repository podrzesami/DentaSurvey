var DentaSurvey = DentaSurvey || (function(){
	return {
		init: function(title, language, tableTitle, warning) {
			$(document).ready(function(){			 
				$("#manageSurveyGrid").jqGrid({
					url: '/DentaSurvey/manage/survey/all',					
					datatype: 'json',
					colNames:[
					          "",
					           title,
					           language,
					],
					colModel:[
					    {name:'surveyId', index:'surveyId', jsonmap:'surveyId', hidden:true },    
						{name:'title', index:'title', sortable:true, jsonmap:'title', 
							width:'80%'},
						{name:'laguage.language', index:'language.language', 
								sortable:false, jsonmap:'language.language', width:'20%'}
					], 
					jsonReader:{
						id:"surveyId",
					    root: "rows", 
					    page: "page", 
					    total: "total", 
					    records: "records",
					},
					pager: '#manageSurveyPager',
					sortname: 'title',
					rowNum:10, 
					height:'75%',	
					viewrecords: true, 					
					sortorder: "asc", 
					caption: tableTitle,
					shrinkToFit:true,
					autowidth:true,
				}).navGrid('#manageSurveyPager',
						{edit:false,add:false,del:false,search:false})				
				.navButtonAdd('#manageSurveyPager',{
					   caption:"Info", 
						   buttonicon:"ui-icon-info", 
						   onClickButton: function(){ 
							   var selr = jQuery('#manageSurveyGrid').jqGrid('getGridParam', 'selrow');
							   if(selr==null) {
								   alert(warning);
							   } else {
								   window.location.href = ("/DentaSurvey/manage/survey/get?id=" + selr);
							   }
						   }, 
						   position:"last"
				})
				.navButtonAdd('#manageSurveyPager',{
					   caption:"Add", 
					   buttonicon:"ui-icon-plus", 
					   onClickButton: function(){ 
						   window.location.replace("/DentaSurvey/manage/survey/add");
					   }, 
					   position:"last"
				})
				.navButtonAdd('#manageSurveyPager',{
				   caption:"Del", 
					   buttonicon:"ui-icon-trash", 
					   onClickButton: function(){ 
						   var selr = jQuery('#manageSurveyGrid').jqGrid('getGridParam', 'selrow');
						   if(selr==null) {
							   alert(warning);
						   } else {
							   window.location.href = ("/DentaSurvey/manage/survey/delete?id=" + selr);
						   }
					   }, 
					   position:"last"
				})
				.navButtonAdd('#manageSurveyPager',{
					   caption:"Edit", 
						   buttonicon:"ui-icon-pencil", 
						   onClickButton: function(){ 
							   var selr = jQuery('#manageSurveyGrid').jqGrid('getGridParam', 'selrow');
							   if(selr==null) {
								   alert(warning);
							   } else {
								   window.location.href = ("/DentaSurvey/manage/survey/update?id=" + selr);
							   }
						   }, 
						   position:"last"
				});
				$(window).bind('resize', function() {
					var width = $("#jqgrid-container").width();
				    if(width!=null){
				    	$("#manageSurveyGrid").jqGrid('setGridWidth', width, true);
				    }
				    else {
				    	$("#manageSurveyGrid").jqGrid('setGridWidth', 900, true);
				    }
				}).trigger('resize');
			});
		},
	};
}());

