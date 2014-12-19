var DentaSurvey = DentaSurvey || (function(){
	return {
		init: function(patientId, title, referedBy, date, medicalProblem, warning) {
			$(document).ready(function(){			 
				$("#manageAnsweredSurveyGrid").jqGrid({
					url: '/DentaSurvey/manage/answeredSurvey/answeredSurveys?id=' + patientId,					
					datatype: 'json',
					colNames:[
					          "",
					           date,
					           referedBy,
					           medicalProblem
					],
					colModel:[
					    {name:'answeredSurveyId', index:'guestionId', jsonmap:'answeredSurveyId', hidden:true },    
	                    {name:'date', index:'date', width: 85, align: "center", sorttype: "date", datefmt: "d-M-Y",
	                        formatoptions: {newformat:'d/m/Y'},
	                        formatter: function (cellval, opts, rowObject, action) {
	                            return $.fn.fmatter.call(
	                                      this,
	                                      "date",
	                                      new Date(cellval),
	                                      $.extend({}, $.jgrid.formatter.date, opts),
	                                      rowObject,
	                                      action);
	                        }, width:'25%'},	                            
						{name:'referredBy', index:'referredBy', sortable:true, jsonmap:'referredBy', 
							width:'35%'},
						{name:'medicalProblem', index:'medicalProblem', sortable:true, jsonmap:'medicalProblem', 
							width:'40%'},	
					], 
					jsonReader:{
						id:"answeredSurveyId",
					    root: "rows", 
					    page: "page", 
					    total: "total", 
					    records: "records",
					},
					pager: '#manageAnsweredSurveyPager',
					sortname: 'date',
					rowNum:10, 
					width:'900',
					height:'75%',	
					viewrecords: true, 					
					sortorder: "asc", 
					caption: title,
					shrinkToFit:true,
					autowidth:true, 
				}).navGrid('#manageAnsweredSurveyPager',
						{edit:false,add:false,del:false,search:false})				
				.navButtonAdd('#manageAnsweredSurveyPager',{
					   caption:"Info", 
						   buttonicon:"ui-icon-info", 
						   onClickButton: function(){ 
							   var selr = jQuery('#manageAnsweredSurveyGrid').jqGrid('getGridParam', 'selrow');
							   if(selr==null) {
								   alert(warning);
							   } else {
								   window.location.href =
									   ("/DentaSurvey/manage/answeredSurvey/get?id=" + selr);
							   }
						   }, 
						   position:"last"
				})
				.navButtonAdd('#manageAnsweredSurveyPager',{
				   caption:"Del", 
					   buttonicon:"ui-icon-trash", 
					   onClickButton: function(){ 
						   var selr = jQuery('#manageAnsweredSurveyGrid').jqGrid('getGridParam', 'selrow');
						   if(selr==null) {
							   alert(warning);
						   } else {
							   window.location.href = ("/DentaSurvey/manage/answeredSurvey/delete?patientId=" + patientId
									   + "&id=" + selr);
						   }
					   }, 
					   position:"last"
				})
				.navButtonAdd('#manageAnsweredSurveyPager',{
				   caption:"Export", 
					   buttonicon:"ui-icon-folder-open", 
					   onClickButton: function(){ 
						   var selr = jQuery('#manageAnsweredSurveyGrid').jqGrid('getGridParam', 'selrow');
						   if(selr==null) {
							   alert(warning);
						   } else {
							   window.location.href = ("/DentaSurvey/manage/answeredSurvey/export?" 
									+ "patientId=" + patientId
									+ "&id=" + selr);
						   }
					   }, 
					   position:"last"
				});
				
				$(window).bind('resize', function() {
					var width = $("#manage-answeredSurvey-container").width();
				    if(width!=null){
				    	$("#manageAnsweredSurveyGrid").jqGrid('setGridWidth', width, true);
				    }
				    else {
				    	$("#manageAnsweredSurveyGrid").jqGrid('setGridWidth', 900, true);
				    }
				}).trigger('resize');
				
				function myDateFormatter ( cellvalue, options, rowObject )
				{
					return new_formated_cellvalue;
				}
			});
			
		},
	};
}());