DentaSurvey = {
	init: function() {
		var basic_opts = {
			    mapKey: 'data-name'
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
					$('img').mapster(initial_opts)
				    .mapster('set',true,'CA', {
				        fill: true,
				        fillColor: '00ff00'
				    })
				    .mapster('snapshot')
				    .mapster('rebind',basic_opts);

		    });
	},
};   