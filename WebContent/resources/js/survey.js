var DentaSurvey = DentaSurvey || (function(){
	return {
		init: function(title, language, tableTitle) {
			$(document).ready(function(){			 
				$("#manageSurveyGrid").jqGrid({
					url: '/DentaSurvey/manage/survey/all',
					
					datatype: 'json',
					width: 500, 
					colNames:[
					          "",
					           title,
					           language,
					],
					colModel:[
					    {name:'surveyId', index:'surveyId', jsonmap:'surveyId', hidden:true },    
						{name:'title', index:'title', sortable:true, jsonmap:'title', 
							key: true, width:400, formatter: formatLink,},
						{name:'laguage.language', index:'language.language', 
								sortable:false, jsonmap:'language.language', width:100}
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
					width:'100%',
					height:'80%',	
					editurl: "/DentaSurvey/", 
					viewrecords: true, 					
					sortorder: "asc", 
					caption: tableTitle 
				});
				
				function formatLink(cellvalue, options, rowObject) {
		            return "<a href=/DentaSurvey/manage/survey/get?id="+ rowObject.surveyId + ">" + cellvalue + "</a>";
		        }

			});
		},
	};
}());

