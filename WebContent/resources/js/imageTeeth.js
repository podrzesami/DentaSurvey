DentaSurvey = {
	init: function() {
		var basic_opts = {
			    mapKey: 'data-type'
			};

			var initial_opts = $.extend({},basic_opts, 
			    { 
			        staticState: true,
			        fill: false,
			        stroke: true,
			        strokeWidth: 0.5,
			        strokeColor: 'ff0000'
			    });

		$(document).ready(function() {	
			
					for(var i=1; i<9; i++) {
						var element = document.createElement("input");
						//Assign different attributes to the element. 
						element.id= "DR" + i + "_checkbox";
						element.type="checkbox";
						element.path= "answer";
						element.name="answer"; 
						element.value="DR" + i;

						var div = $("#hidden-div");
						div.append(element);

						var element = document.createElement("input");
						//Assign different attributes to the element. 
						element.id= "DL" + i + "_checkbox";
						element.type="checkbox";
						element.path= "answer";
						element.name="answer"; 
						element.value="DL" + i;

						var div = $("#hidden-div");
						div.append(element);
						
						var element = document.createElement("input");
						//Assign different attributes to the element. 
						element.id= "TR" + i + "_checkbox";
						element.type="checkbox";
						element.path= "answer";
						element.name="answer"; 
						element.value="TR" + i;

						var div = $("#hidden-div");
						div.append(element);
						
						var element = document.createElement("input");
						//Assign different attributes to the element. 
						element.id= "TL" + i + "_checkbox";
						element.type="checkbox";
						element.path= "answer";
						element.name="answer"; 
						element.value="TL" + i;

						var div = $("#hidden-div");
						div.append(element);
					}

					
					$('img').mapster(initial_opts)
					    .mapster('snapshot')
					    .mapster('rebind',basic_opts);							
		    });
	},
	
	click:function(area){
		var checkbox = "#" + area + "_checkbox";
		var checked = checkbox + ":checked";
		if( $(checked).length != 0) {
			$(checkbox).removeAttr('checked');
			return;
		}else {
			$(checkbox).prop('checked', true);
			return;
		}
	}
};   