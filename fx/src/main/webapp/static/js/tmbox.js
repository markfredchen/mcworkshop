TMBoxRealData = function(){};

TMBoxRealData.init = function(){
	var chartData = jQuery.parseJSON($("#tmboxChartData").html()).chartData;
	var chart = new AmCharts.AmSerialChart();
	chart.dataProvider = chartData;
	chart.categoryField = "index";
	var graph = new AmCharts.AmGraph();
	graph.valueField = "profit";
	graph.type = "line";
	chart.addGraph(graph);
	chart.write("tmboxChart");
};

FutureRealData = function(){};

FutureRealData.init = function(){
	var chartData = jQuery.parseJSON($("#futureChartData").html()).chartData;
	var chart = new AmCharts.AmSerialChart();
	chart.dataProvider = chartData;
	chart.categoryField = "index";
	var graph = new AmCharts.AmGraph();
	graph.valueField = "profit";
	graph.type = "line";
	chart.addGraph(graph);
	chart.write("futureChart");
};
