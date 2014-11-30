var DentaSurvey = DentaSurvey || (function(){
	return {
		init: function(tableTitle, username, role, warning) {
			$(document).ready(function(){			 
				$("#manageUserGrid").jqGrid({
					url: '/DentaSurvey/userConfigurafion/all',					
					datatype: 'json',
					colNames:[
					          "",
					           username,
					           role,
					],
					colModel:[
					    {name:'userId', index:'userId', jsonmap:'userId', hidden:true },    
						{name:'username', index:'username', sortable:true, jsonmap:'username', 
							width:800},
						{name:'role.role', index:'role.role', 
								sortable:false, jsonmap:'role.role', width:200}
					], 
					jsonReader:{
						id:"userId",
					    root: "rows", 
					    page: "page", 
					    total: "total", 
					    records: "records",
					},
					pager: '#manageUserPager',
					sortname: 'username',
					rowNum:10, 
					width:'900',
					height:'75%',	
					viewrecords: true, 					
					sortorder: "asc", 
					caption: tableTitle 
				}).navGrid('#manageUserPager',
						{edit:false,add:false,del:false,search:false})				
				.navButtonAdd('#manageUserPager',{
					   caption:"Add", 
					   buttonicon:"ui-icon-plus", 
					   onClickButton: function(){ 
						   window.location.href = ("/DentaSurvey/userConfigurafion/add");
					   }, 
					   position:"last"
				})
				.navButtonAdd('#manageUserPager',{
				   caption:"Del", 
					   buttonicon:"ui-icon-trash", 
					   onClickButton: function(){ 
						   var selr = jQuery('#manageUserGrid').jqGrid('getGridParam', 'selrow');
						   if(selr==null) {
							   alert(warning);
						   } else {
							   window.location.href = ("/DentaSurvey/userConfigurafion/delete?id=" + selr);
						   }
					   }, 
					   position:"last"
				})
				.navButtonAdd('#manageUserPager',{
					   caption:"Edit", 
						   buttonicon:"ui-icon-pencil", 
						   onClickButton: function(){ 
							   var selr = jQuery('#manageUserGrid').jqGrid('getGridParam', 'selrow');
							   if(selr==null) {
								   alert(warning);
							   } else {
								   window.location.href = ("/DentaSurvey/userConfigurafion/update?id=" + selr);
							   }
						   }, 
						   position:"last"
				});
			});
		},
	};
}());

