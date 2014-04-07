/*Stock intraday data chart implementation*/

			var chartData = [];
			var chart;
			
			function drawChart(args) {
				document.getElementById("chartdiv").style.display="block";
				chartData = [];// clear previous chart data
				chart = null;
				document.getElementById("chartdiv").innerHTML="";
				//generateChartData();
			    readData(args);
				createStockChart();
				deleteClearStyle();
			}
			
			function readData(args){
			    var serverData = [];
			    serverData = args.chart_data;
			    serverData = eval(serverData);
			    alert(serverData.length);
			    for(var i=0; i< serverData.length; i++){
			    	var dateVal = new Date(serverData[i].date);
			    	//alert(serverData[i].date);
			    	//alert(serverData[i].value);
			    	//alert(serverData[i].volume);
			    	var valueVal1 = serverData[i].value1;
			    	var volumeVal1 = serverData[i].volume1;
			    	var valueVal2 = serverData[i].value2;
					var volumeVal2 = serverData[i].volume2;
			    	
			    	chartData.push({
						date: dateVal,
						value1: valueVal1,
						volume1: volumeVal1,
						value2:	valueVal2,
						volume2: volumeVal2
					});
			    	
			    }
			}
			
			// delete space between parts of chart(change from clear-both to clear-none)
			function deleteClearStyle(){
				var div = document.getElementById('chartdiv');
				var chartChild = div.childNodes[0].childNodes[0].childNodes[2];
				if(chartChild.style.clear == 'both'){
					chartChild.style.clear = 'none';
				}
			}
			
			function generateChartData() {
				var firstDate = new Date(2012, 0, 1);
				firstDate.setDate(firstDate.getDate() - 1000);
				firstDate.setHours(0, 0, 0, 0);

				for (var i = 0; i < 1000; i++) {
					var newDate = new Date(firstDate);
					newDate.setHours(0, i, 0, 0);

					var a = Math.round(Math.random() * (40 + i)) + 100 + i;
					var b = Math.round(Math.random() * 100000000);

					chartData.push({
						date: newDate,
						value: a,
						volume: b
					});
				}
			}

			function createStockChart() {
				chart = AmCharts.makeChart("chartdiv", {

					type: "stock",
					pathToImages: "../amcharts/images/",

					categoryAxesSettings: {
						minPeriod: "mm",
						//dateFormat: "YYYY-MM-DD HH:NN:SS.Q"
					},

					dataSets: [{
						color: "#b0de09",
						fieldMappings: [{
							fromField: "value1",
							toField: "value1"
						}, {
							fromField: "volume1",
							toField: "volume1"
						},
						{
							fromField: "value2",
							toField: "value2"
						}
						],

						dataProvider: chartData,
						categoryField: "date"
					},
					
					],


					panels: [{
							showCategoryAxis: false,
							title: "Value",
							percentHeight: 70,

							stockGraphs: [{
								id: "g1",
								valueField: "value1",
								type: "smoothedLine",
								lineThickness: 2,
								bullet: "round",
								lineColor: "#00FF00"
							},
							{
								id: "g2",
								valueField: "value2",
								type: "smoothedLine",
								lineThickness: 2,
								bullet: "round",
								lineColor: "#FF3300"
							}
							],


							stockLegend: {
								valueTextRegular: " ",
								markerType: "none"
							}
						},

						{
							title: "Volume",
							percentHeight: 30,
							stockGraphs: [{
								valueField: "volume1",
								type: "column",
								cornerRadiusTop: 2,
								fillAlphas: 1
							}],

							stockLegend: {
								valueTextRegular: " ",
								markerType: "none"
							}
						}
					],

					chartScrollbarSettings: {
						graph: "g1",
						usePeriod: "10mm",
						position: "top"
					},

					chartCursorSettings: {
						valueBalloonsEnabled: true
					},

					periodSelector: {
						position: "top",
						dateFormat: "YYYY-MM-DD HH:NN",
						inputFieldWidth: 150,
						periods: [{
							period: "hh",
							count: 1,
							label: "1 hour",
							selected: true

						}, {
							period: "hh",
							count: 2,
							label: "2 hours"
						}, {
							period: "hh",
							count: 5,
							label: "5 hour"
						}, {
							period: "hh",
							count: 12,
							label: "12 hours"
						}, {
							period: "MAX",
							label: "MAX"
						}]
					},

					panelsSettings: {
						usePrefixes: true
					}
				});
				
				chart.invalidateSize();
				chart.validateNow();
			}