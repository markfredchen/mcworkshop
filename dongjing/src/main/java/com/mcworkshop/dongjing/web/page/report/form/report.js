SalesTaxReportPage = function(){};
SalesTaxReportPage.template = function(){};
SalesTaxReportPage.getSalesTaxReportTemplate = function(){
	return $.ajax({
		url: "/dongjing/sales/tax/report/template",
		dataType: "text",
		timeout : 500
	});
};
	
SalesTaxReportPage.getSalesTaxReportData = function(year, month){
	return $.ajax({
		url: "/dongjing/sales/tax/report/service",
		data : {
			year : year,
			month: month
		},
		timeout : 500
	});
};
	
	
SalesTaxReportPage.openReport = function(year, month){
	var reportDetail = null;
	if(SalesTaxReportPage.template.buildReport){
		$.when(SalesTaxReportPage.getSalesTaxReportData(year, month)).done(function(data) {
			reportDetail = SalesTaxReportPage.template.buildReport(data);
			$("#sales-tax-preview-modal-content").html(reportDetail);
			$("#sales-tax-preview-modal").modal("show");
		}).fail(function(){
			alert("defer fail");
		});
	}else{
		$.when(SalesTaxReportPage.getSalesTaxReportTemplate(), 
				SalesTaxReportPage.getSalesTaxReportData(year, month)).done(function(tmpl, data) {
			SalesTaxReportPage.template.buildReport = Handlebars.compile(tmpl[0]);
			reportDetail = SalesTaxReportPage.template.buildReport(data[0]);
			$("#sales-tax-preview-modal").modal({keyboard: false, show: false});
			$("#sales-tax-preview-modal-content").html(reportDetail);
			$("#sales-tax-preview-modal").modal("show");
		}).fail(function(){
			alert("defer fail");
		});
	}
};
