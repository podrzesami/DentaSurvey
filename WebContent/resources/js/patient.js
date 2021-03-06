var DentaSurvey = DentaSurvey || (function(){
	return {
		init: function(tableTitle, id, name, surname, address, warning) {
			$(document).ready(function(){			 
				$("#managePatientGrid").jqGrid({
					url: '/DentaSurvey/manage/patient/all',					
					datatype: 'json',
					colNames:[
					          id,
					          surname,
					          name,					           
					          address,
					],
					colModel:[
					    {name:'patientId', index:'patientId', jsonmap:'patientId',
					    	width: '10%'},    
					    {name:'surname', index:'surname', sortable:true, jsonmap:'surname', 
							width:'25%'},
					    {name:'name', index:'name', sortable:true, jsonmap:'name', 
							width:'30%'},						
						{name:'address', index:'address', sortable:true, jsonmap:'address', 
							width:'35%'},								
					], 
					jsonReader:{
						id:"patientId",
					    root: "rows", 
					    page: "page", 
					    total: "total", 
					    records: "records",
					},
					pager: '#managePatientPager',
					sortname: 'surname',
					rowNum:10, 
					width:'900',
					height:'75%',	
					viewrecords: true, 					
					sortorder: "asc", 
					caption: tableTitle,
					shrinkToFit:true,
					autowidth:true,
				}).navGrid('#managePatientPager',
						{edit:false,add:false,del:false,search:false})				
				.navButtonAdd('#managePatientPager',{
					   caption:"Info", 
						   buttonicon:"ui-icon-info", 
						   onClickButton: function(){ 
							   var selr = jQuery('#managePatientGrid').jqGrid('getGridParam', 'selrow');
							   if(selr==null) {
								   alert(warning);
							   } else {
								   window.location.href = ("/DentaSurvey/manage/patient/get?id=" + selr);
							   }
						   }, 
						   position:"last"
				})
				.navButtonAdd('#managePatientPager',{
				   caption:"Del", 
					   buttonicon:"ui-icon-trash", 
					   onClickButton: function(){ 
						   var selr = jQuery('#managePatientGrid').jqGrid('getGridParam', 'selrow');
						   if(selr==null) {
							   alert(warning);
						   } else {
							   window.location.href = ("/DentaSurvey/manage/patient/delete?id=" + selr);
						   }
					   }, 
					   position:"last"
				})
				.navButtonAdd('#managePatientPager',{
					   caption:"Edit", 
						   buttonicon:"ui-icon-pencil", 
						   onClickButton: function(){ 
							   var selr = jQuery('#managePatientGrid').jqGrid('getGridParam', 'selrow');
							   if(selr==null) {
								   alert(warning);
							   } else {
								   window.location.href = ("/DentaSurvey/manage/patient/update?id=" + selr);
							   }
						   }, 
						   position:"last"
				});
				$(window).bind('resize', function() {
					var width = $("#managePatientContainer").width();
				    if(width!=null){
				    	$("#managePatientGrid").jqGrid('setGridWidth', width, true);
				    }
				    else {
				    	$("#managePatientGrid").jqGrid('setGridWidth', 900, true);
				    }
				}).trigger('resize');
				
			});
		},
	};
}());

