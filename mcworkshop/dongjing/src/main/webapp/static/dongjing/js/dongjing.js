DongJing = (function() {
	var dongjing = function() {
	};

	var DATE_PATTERN = "yyyy-mm-dd";
	var DATEPICKER_OPTION = {
		format : DATE_PATTERN,
		minView : 2,
		autoclose : true,
		initialDate : new Date(),
		pickerPosition : 'top-right'
	};

	dongjing.prototype = {
		initDatepickers : function(datePickers, options) {
			var dateOption = null;
			if (options == null) {
				dateOption = DATEPICKER_OPTION;
			}
			$("." + datePickers).datetimepicker(dateOption);
		},
		showReport : function(chartID, reportID, dataID) {
			$("#" + reportID).modal('show');
			$('#' + reportID).on(
					'shown.bs.modal',
					function(e) {
						AmCharts.makeChart(chartID, $.parseJSON($("#" + dataID)
								.html()));
					});
		}
	};
	return dongjing;
})();