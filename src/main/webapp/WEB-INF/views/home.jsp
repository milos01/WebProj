<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${logedUser.firstName}'spage</title>
<link href="../springmvc/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link href="../springmvc/resources/font-awesome/css/font-awesome.css"
	rel="stylesheet">

<link href="../springmvc/resources/css/animate.css" rel="stylesheet">
<link href="../springmvc/resources/css/style.css" rel="stylesheet">

<link rel="stylesheet" type="text/css"
	href="../springmvc/resources/gridster/assets/css/jquery.gridster.css">
<link rel="stylesheet" type="text/css"
	href="../springmvc/resources/gridster/assets/css/styles.css">

<script src="../springmvc/resources/js/jquery-2.1.1.js"></script>

<link rel='stylesheet'
	href='../springmvc/resources/fullcalendar/fullcalendar.css' />
<link href='../springmvc/resources/fullcalendar/fullcalendar.print.css'
	rel='stylesheet' media='print' />
<link rel="stylesheet" type="text/css"
	href="../springmvc/resources/css/dropzone.css">

<script
	src='../springmvc/resources/timePicker/bootstrap-datetimepicker.min.js'></script>


<script src='../springmvc/resources/fullcalendar/lib/moment.min.js'></script>
<script src='../springmvc/resources/fullcalendar/fullcalendar.js'></script>

<script src="../springmvc/resources/chart/amcharts.js"
	type="text/javascript"></script>
<script src="../springmvc/resources/chart/serial.js"
	type="text/javascript"></script>
<link
	href="../springmvc/resources/css/plugins/footable/footable.core.css"
	rel="stylesheet">
<link href="../springmvc/resources/css/animate.css" rel="stylesheet">
<!-- FooTable -->
<link
	href="../springmvc/resources/css/plugins/footable/footable.core.css"
	rel="stylesheet">

<style type="text/css">
.panel {
	display: none;
}

.panel.active {
	display: block;
}

.panel1 {
	display: none;
}

.panel1.active1 {
	display: block;
}

.star {
	font-size: 24px;
}

#calendar {
	width: 720px;
	display: block;
	z-index: 100000;
	visibility: visible;
}

#menuChoose li {
	display: inline;
	padding: 15px;
	margin-left: 5px;
}

.promeniMeni:hover {
	cursor: pointer;
}

.sto {
	border-radius: 50%;
}

#customers {
	font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

#customers td, #customers th {
	border: 1px solid #ddd;
	text-align: left;
	padding: 8px;
}

#customers tr:nth-child(even) {
	background-color: #f2f2f2
}

#customers tr:hover {
	background-color: #ddd;
}

#customers th {
	padding-top: 12px;
	padding-bottom: 12px;
	background-color: #4CAF50;
	color: white;
}
</style>

</head>

<body ng-app="qfcApp" ng-controller="MyCtrl">




	
	<!-- Add restaurant modal-->
	<div id="registerRestaurantModal" class="modal fade" role="dialog">
		<div class="modal-dialog" style="width: 400px">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Add restaurant</h4>
				</div>
				<form ng-submit="submitAddRes()" method="POST"
					ng-controller="addResController" novalidate>
					<input class="form-control" name="name" type="text"
						ng-model="resName" placeholder="Name"
						style="width: 300px; height: 45px; margin: auto auto; margin-top: 35px;">

					<input class="form-control" type="text" ng-model="resAddress"
						placeholder="Address"
						style="width: 300px; height: 45px; margin: auto auto; margin-top: 15px;"
						name="firstName" /> <input class="form-control" name="city"
						type="text" ng-model="resCity" placeholder="City"
						style="width: 300px; height: 45px; margin: auto auto; margin-top: 15px;" />
					<input class="form-control" type="text" ng-model="resPhone"
						name="Phone" placeholder="Phone"
						style="width: 300px; height: 45px; margin: auto auto; margin-top: 15px;" />
					<input class="form-control" type="text" ng-model="resEmail"
						name="Email" placeholder="Email"
						style="width: 300px; height: 45px; margin: auto auto; margin-top: 15px;" />
					<input class="form-control" type="text" ng-model="resSite"
						name="site" placeholder="Site"
						style="width: 300px; height: 45px; margin: auto auto; margin-top: 15px;" />
					<input class="form-control" type="text" ng-model="resPib"
						name="PIB" placeholder="PIB"
						style="width: 300px; height: 45px; margin: auto auto; margin-top: 15px;" />
					<input class="form-control" type="text" ng-model="resAcc"
						name="acc" placeholder="Acc"
						style="width: 300px; height: 45px; margin: auto auto; margin-top: 15px;" />
					<input class="form-control" type="text" ng-model="resPicture"
						name="picture" placeholder="Picture"
						style="width: 300px; height: 45px; margin: auto auto; margin-top: 15px;" />
					<div class="modal-footer" style="margin-top: 15px;">
						<button type="submit" class="btn btn-success"
							style="background: #1ab394">Add</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- End restaurant staff -->

	<div id="wrapper">
		<nav class="navbar-default navbar-static-side" role="navigation">
		<div class="sidebar-collapse">
			<ul class="nav metismenu" id="side-menu">
				<li class="nav-header">
					<div class="dropdown profile-element">
						<span> <img alt="image" class="img-circle"
							src="../springmvc/resources/img/profile_small.jpg" />
						</span> <a data-toggle="dropdown" class="dropdown-toggle" href="#"> <span
							class="clear"> <span class="block m-t-xs" ng-model="userCred">{{userCred}}<strong
									class="font-bold"></strong>
							</span> <span class="text-muted text-xs block" ng-model="userRole">{{userRole}}</span>
						</span>
						</a>
					</div>
					<div class="logo-element">QFC</div>
				</li>
				<!-- Admin side menu -->

				<!-- <li><a data-toggle="modal"
							data-target="#registerRestaurantModal"><i class="fa fa-plus"></i>
								<span class="nav-label">Add restaurant</span></a></li> -->


			</ul>

		</div>
		</nav>

		<div id="page-wrapper" class="gray-bg">
			<div class="row border-bottom">
				<nav class="navbar navbar-static-top white-bg" role="navigation"
					style="margin-bottom: 0">
				<div class="navbar-header">
					<a class="navbar-minimalize minimalize-styl-2 btn btn-primary "
						href="#"><i class="fa fa-bars"></i> </a>
					

				</div>
				<ul class="nav navbar-top-links navbar-right">
					<li><a ng-click="logout()"> <i class="fa fa-sign-out"></i> Log
							out
					</a></li>
				</ul>

				</nav>
			</div>
			<!-- Regular user central part -->


			<div class="wrapper wrapper-content">
				<div class="row">
					<div class="col-lg-12">
						<div class="ibox float-e-margins">
							<div class="ibox-content ibox-heading"
								style="background-color: #fff;height:120px">
								<h3 style="margin-top: 22px;">
									<i class="fa fa-glass"></i> Restaurant list
								</h3>
								<div class="container" style="float:left;width:100%">
										<input class="form-control" type="search" ng-model="q.name"
											placeholder="filter by name..." aria-label="filter friends"
											style="margin-bottom: 20px;width:30%;float:left;margin-left:-15px" />
										<input class="form-control" type="search" ng-model="qd.address"
											placeholder="filter by address..." aria-label="filter friends"
											style="margin-bottom: 20px;width:30%;float:left;margin-left:15px" />
										<input class="form-control" type="search" ng-model="qc.city"
											placeholder="filter by city..." aria-label="filter friends"
											style="margin-bottom: 20px;width:30%;float:left;margin-left:15px" />
										</div>
							</div>
							
							<div class="ibox-content">
								
								<div class="feed-activity-list">
									<div>
										
										<ul class="example-animate-container">

											<li class="animate-repeat"
												ng-repeat="friend in friends | filter:q | filter:qd | filter:qc" nop
												friendss="friends" idx="friend.id" fs="friend">
												<div class="feed-element">
													<div
														style="background: #f8f8f9; float: left; height: 100px; width: 100px; margin-right: 10px">
														<p style="text-align: center; margin-top: 40px">[image]</p>
													</div>
													<div>
														<small class="pull-right">1m ago</small> <strong >{{friend.name}}</strong>
														<div>Lorem Ipsum is simply dummy text of the
															printing and typesetting industry. Lorem Ipsum</div>
														<small class="text-muted"></small>
														<div id="deletea">
														
														</div>
														
													</div>
													<div class="animate-repeat" ng-repeat="event in friend.events " eveDir>
															<div id="eve{{event.id}}">{{event.description}}</div>
													</div>
											
													<!--MODAL WINDOW-->
													<script type="text/ng-template" id="myModalContent.html">
                    				<div class="modal-header">
                        				<h3>Restaurant: {{ customer.name }}</h3>
                    				</div>
									<form ng-submit="submitUpdateRes()" method="POST" ng-controller="updateResController" novalidate>
                    					<div class="modal-body">
                            			
										<input class="form-control" name="name" type="text" ng-model="resNameU"
										placeholder="Name"
									style="width: 300px; height: 45px; margin: auto auto; margin-top: 35px;"> 
						
									<input class="form-control"
									type="text" ng-model="resAddressU" placeholder="Address"
									style="width: 300px; height: 45px; margin: auto auto; margin-top: 15px;"
									name="firstName" /> 
									<input
									class="form-control" name="city" type="text" ng-model="resCityU"
									placeholder="City"
									style="width: 300px; height: 45px; margin: auto auto; margin-top: 15px;" /> 
									<input class="form-control"
									type="text" ng-model="resPhoneU" name="Phone"
									placeholder="Phone"
									style="width: 300px; height: 45px; margin: auto auto; margin-top: 15px;" />
									<input class="form-control"
									type="text" ng-model="resEmailU" name="Email"
									placeholder="Email"
									style="width: 300px; height: 45px; margin: auto auto; margin-top: 15px;" />
									<input class="form-control"
									type="text" ng-model="resSiteU" name="site"
									placeholder="Site"
									style="width: 300px; height: 45px; margin: auto auto; margin-top: 15px;" />
									<input class="form-control"
									type="text" ng-model="resPibU" name="PIB"
									placeholder="PIB"
									style="width: 300px; height: 45px; margin: auto auto; margin-top: 15px;" disabled/>
									<input class="form-control"
									type="text" ng-model="resAccU" name="acc"
									placeholder="Acc"
									style="width: 300px; height: 45px; margin: auto auto; margin-top: 15px;" disabled/>
									<input class="form-control"
									type="text" ng-model="resPictureU" name="picture"
									placeholder="Picture"
									style="width: 300px; height: 45px; margin: auto auto; margin-top: 15px;" />
									<input type="hidden" ng-model="resId"/>
				
                          
                    				</div>
                    				<div class="modal-footer" style="margin-top: 15px;">
										<button type="submit" class="btn btn-success"
										style="background: #1ab394">Update</button>
										<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
									</div>
								</form>
                				</script>
                				<!--MODAL WINDOW-->
													<script type="text/ng-template" id="addEventContent.html">
                    				<div class="modal-header">
                        				<h3>Restaurant: {{ customer.name }}</h3>
                    				</div>
									<form ng-submit="submitAddEvent()" method="POST" ng-controller="addEventController" novalidate>
                    					<div class="modal-body">
                            			
										<input class="form-control" name="name" type="text" ng-model="eventDescription"
										placeholder="Description"
									style="width: 300px; height: 45px; margin: auto auto; margin-top: 35px;"> 
						
									<input class="form-control"
									type="date" ng-model="eventDate" placeholder="Date"
									style="width: 300px; height: 45px; margin: auto auto; margin-top: 15px;"
									name="eventName" /> 
									<input
									 name="eventPicture" type="file" ng-model="eventPicture"
									placeholder="Picture"
									style="width: 300px; height: 45px; margin: auto auto; margin-top: 15px;" /> 
								
									

									<input type="hidden" ng-model="restaurantObj" />
									<input type="hidden" ng-model="restaurantId" />
                    				</div>
                    				<div class="modal-footer" style="margin-top: 15px;">
										<button type="submit" class="btn btn-success"
										style="background: #1ab394">Add</button>
										<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
									</div>
								</form>
		
                				</script>
                				
	
                                	
												</div>


											</li>
											 <p ng-show="(friends | filter:q | filter:qd | filter:qc).length == 0" style="text-align:center">No results found!</p>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</div>


				</div>


				<div class="footer">
					<div>
						<strong>Copyright</strong> Quest For Chef &copy; 2016
					</div>
				</div>
			</div>



			
			
	</div>
	<!-- Mainly scripts -->



	<script src="../springmvc/resources/js/bootstrap.min.js"></script>
	<script
		src="../springmvc/resources/js/plugins/metisMenu/jquery.metisMenu.js"></script>
	<script
		src="../springmvc/resources/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

	<!-- Flot -->
	<script src="../springmvc/resources/js/plugins/flot/jquery.flot.js"></script>
	<script
		src="../springmvc/resources/js/plugins/flot/jquery.flot.tooltip.min.js"></script>
	<script
		src="../springmvc/resources/js/plugins/flot/jquery.flot.spline.js"></script>
	<script
		src="../springmvc/resources/js/plugins/flot/jquery.flot.resize.js"></script>
	<script src="../springmvc/resources/js/plugins/flot/jquery.flot.pie.js"></script>
	<script
		src="../springmvc/resources/js/plugins/flot/jquery.flot.symbol.js"></script>
	<script
		src="../springmvc/resources/js/plugins/flot/jquery.flot.time.js"></script>

	<!-- Peity -->
	<script
		src="../springmvc/resources/js/plugins/peity/jquery.peity.min.js"></script>
	<script src="../springmvc/resources/js/demo/peity-demo.js"></script>

	<!-- Custom and plugin javascript -->
	<script src="../springmvc/resources/js/inspinia.js"></script>
	<script src="../springmvc/resources/js/plugins/pace/pace.min.js"></script>

	<!-- jQuery UI -->
	<script src="js/plugins/jquery-ui/jquery-ui.min.js"></script>

	<!-- Jvectormap -->
	<script
		src="../springmvc/resources/js/plugins/jvectormap/jquery-jvectormap-2.0.2.min.js"></script>
	<script
		src="../springmvc/resources/js/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>

	<!-- EayPIE -->
	<script
		src="../springmvc/resources/js/plugins/easypiechart/jquery.easypiechart.js"></script>

	<!-- Sparkline -->
	<script
		src="../springmvc/resources/js/plugins/sparkline/jquery.sparkline.min.js"></script>

	<!-- <script type="text/javascript" src="../springmvc/resources/gridster/assets/jquery.js"></script> -->
	<script type="text/javascript"
		src="../springmvc/resources/gridster/assets/jquery.gridster.js"></script>
	<script src="../springmvc/resources/js/plugins/pace/pace.min.js"></script>
	<!-- FooTable -->
	<script
		src="../springmvc/resources/js/plugins/footable/footable.all.min.js"></script>

	<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.9/angular.min.js"></script>
	
	<script
		src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.9/angular-route.js"></script>
	<script
		src="//angular-ui.github.io/bootstrap/ui-bootstrap-tpls-2.1.3.js"></script>
	<script type="text/javascript" src="../springmvc/resources/js/app.js"></script>
	<script type="text/javascript"
		src="../springmvc/resources/js/validationByPage/homePage.js"></script>
	
	<!-- Dropzone  -->
	<script type="text/javascript"
		src="../springmvc/resources/js/dropzone.js"></script>
	
	<script type="text/javascript">
		var gridster;

		$(document).ready(function() {
			
			/* get user */

			gridtster = $(".gridster > .ull").gridster({
				widget_margins : [ 10, 10 ],
				widget_base_dimensions : [ 110, 110 ],
				min_cols : 10,
				serialize_params : function($w, wgd) {
					return {
						id : $($w).attr('id'),
						col : wgd.col,
						row : wgd.row,
						size_x : wgd.size_x,
						size_y : wgd.size_y
					};
				}
			}).data('gridster');
			//		var json = JSON.stringify(gridster.serialize());
			//		alert(json);
			
		});

		function saveP() {
			gridster = $(".gridster > .ull").gridster({
				widget_margins : [ 10, 10 ],
				widget_base_dimensions : [ 110, 110 ],
				min_cols : 10,
				serialize_params : function($w, wgd) {
					return {
						id : $($w).attr('id'),
						col : wgd.col,
						row : wgd.row,
						size_x : wgd.size_x,
						size_y : wgd.size_y
					};
				}
			}).data('gridster');
			$.ajax({
				type : "POST",
				contentType : 'application/json',
				dataType : 'text',
				url : "savePositions",
				data : JSON.stringify(gridster.serialize()),
				success : function(data) {
					if (data == "success") {
						$(".alrtTable").show();
						if ($(".alrtTable").is(":visible")) {
							$(".alrtTable").delay(1800).fadeOut(500);

						}
					}
				},
				error : function() {
					alert("rrr");
				}

			});
		}
	</script>
	<!-- Sparkline demo data  -->
	<script src="../springmvc/resources/js/demo/sparkline-demo.js"></script>
	<!-- Promena centralnog diva -->
	<script type="text/javascript">
		$(function() {
			$('.promeniCent').on('click', function() {
				$('.promeniCent.active').removeClass('active');
				$(this).addClass('active');

				var panelToSHow = $(this).attr('rel');

				$('.panel.active').show(100, function() {
					$(this).removeClass('active');
					$('#' + panelToSHow).hide(100, function() {
						$(this).addClass('active');
					});
				});
			});
		});
	</script>

	<!-- Promena menu diva -->
	<script type="text/javascript">
		$(function() {
			$('.promeniMeni').on('click', function() {
				$('.promeniMeni.active1').removeClass('active1');
				$(this).addClass('active1');

				var panelToSHow1 = $(this).attr('rel');

				$('.panel1.active1').show(100, function() {
					$(this).removeClass('active1');
					$('#' + panelToSHow1).hide(100, function() {
						$(this).addClass('active1');
					});
				});
			});
		});
	</script>

	<script>
		var chart3;

		var chartData3 = [ {
			"country" : "January",
			"visits" : 2025,
			"color" : "#6CB9EC"
		}, {
			"country" : "February",
			"visits" : 1882,
			"color" : "#6CB9EC"
		}, {
			"country" : "March",
			"visits" : 1809,
			"color" : "#6CB9EC"
		}, {
			"country" : "April",
			"visits" : 1322,
			"color" : "#6CB9EC"
		}, {
			"country" : "May",
			"visits" : 1122,
			"color" : "#6CB9EC"
		}, {
			"country" : "June",
			"visits" : 1114,
			"color" : "#6CB9EC"
		}, {
			"country" : "July",
			"visits" : 984,
			"color" : "#6CB9EC"
		}, {
			"country" : "August",
			"visits" : 711,
			"color" : "#6CB9EC"
		}, {
			"country" : "September",
			"visits" : 665,
			"color" : "#6CB9EC"
		}, {
			"country" : "October",
			"visits" : 580,
			"color" : "#6CB9EC"
		}, {
			"country" : "November",
			"visits" : 443,
			"color" : "#6CB9EC"
		}, {
			"country" : "Canada",
			"visits" : 441,
			"color" : "#6CB9EC"
		}, {
			"country" : "December",
			"visits" : 395,
			"color" : "#6CB9EC"
		} ];

		AmCharts.ready(function() {
			// SERIAL CHART
			chart3 = new AmCharts.AmSerialChart();
			chart3.dataProvider = chartData3;
			chart3.categoryField = "country";
			chart3.startDuration = 1;

			// AXES
			// category
			var categoryAxis3 = chart3.categoryAxis;
			categoryAxis3.labelRotation = 0;
			categoryAxis3.gridPosition = "start";

			// value
			// in case you don't want to change default settings of value axis,
			// you don't need to create it, as one value axis is created automatically.

			// GRAPH
			var graph3 = new AmCharts.AmGraph();
			graph3.valueField = "visits";
			graph3.balloonText = "[[category]]: <b>[[value]]</b>";
			graph3.type = "column";
			graph3.lineAlpha = 0;
			graph3.fillAlphas = 0.8;
			graph3.colorField = "color";
			chart3.addGraph(graph3);

			// CURSOR
			var chartCursor3 = new AmCharts.ChartCursor();
			chartCursor3.cursorAlpha = 0;
			chartCursor3.zoomable = false;
			chartCursor3.categoryBalloonEnabled = false;
			chart3.addChartCursor(chartCursor3);

			chart3.creditsPosition = "top-right";

			chart3.write("Prihodi");
		});
	</script>

	<script>
		var chart;

		var chartData = [ {
			"country" : "January",
			"visits" : 4025,
			"color" : "#6CB9EC"
		}, {
			"country" : "February",
			"visits" : 1882,
			"color" : "#6CB9EC"
		}, {
			"country" : "March",
			"visits" : 1809,
			"color" : "#6CB9EC"
		}, {
			"country" : "April",
			"visits" : 1322,
			"color" : "#6CB9EC"
		}, {
			"country" : "May",
			"visits" : 1122,
			"color" : "#6CB9EC"
		}, {
			"country" : "June",
			"visits" : 1114,
			"color" : "#6CB9EC"
		}, {
			"country" : "July",
			"visits" : 984,
			"color" : "#6CB9EC"
		}, {
			"country" : "August",
			"visits" : 711,
			"color" : "#6CB9EC"
		}, {
			"country" : "September",
			"visits" : 665,
			"color" : "#6CB9EC"
		}, {
			"country" : "October",
			"visits" : 580,
			"color" : "#6CB9EC"
		}, {
			"country" : "November",
			"visits" : 443,
			"color" : "#6CB9EC"
		}, {
			"country" : "Canada",
			"visits" : 441,
			"color" : "#6CB9EC"
		}, {
			"country" : "December",
			"visits" : 395,
			"color" : "#6CB9EC"
		} ];

		AmCharts.ready(function() {
			// SERIAL CHART
			chart = new AmCharts.AmSerialChart();
			chart.dataProvider = chartData;
			chart.categoryField = "country";
			chart.startDuration = 1;

			// AXES
			// category
			var categoryAxis = chart.categoryAxis;
			categoryAxis.labelRotation = 0;
			categoryAxis.gridPosition = "start";

			// value
			// in case you don't want to change default settings of value axis,
			// you don't need to create it, as one value axis is created automatically.

			// GRAPH
			var graph = new AmCharts.AmGraph();
			graph.valueField = "visits";
			graph.balloonText = "[[category]]: <b>[[value]]</b>";
			graph.type = "column";
			graph.lineAlpha = 0;
			graph.fillAlphas = 0.8;
			graph.colorField = "color";
			chart.addGraph(graph);

			// CURSOR
			var chartCursor = new AmCharts.ChartCursor();
			chartCursor.cursorAlpha = 0;
			chartCursor.zoomable = false;
			chartCursor.categoryBalloonEnabled = false;
			chart.addChartCursor(chartCursor);

			chart.creditsPosition = "top-right";

			chart.write("chartdiv");
		});
	</script>


	<script>
		var chart2;

		var chartData2 = [ {
			"country2" : "Monday",
			"visits" : 250
		}, {
			"country2" : "Tuesday",
			"visits" : 190
		}, {
			"country2" : "Wednesday",
			"visits" : 201
		}, {
			"country2" : "Thursday",
			"visits" : 130
		}, {
			"country2" : "Friday",
			"visits" : 225
		}, {
			"country2" : "Saturday",
			"visits" : 250
		}, {
			"country2" : "Sunday",
			"visits" : 320
		} ];

		AmCharts.ready(function() {
			// SERIAL CHART
			chart2 = new AmCharts.AmSerialChart();
			chart2.dataProvider = chartData2;
			chart2.categoryField = "country2";
			chart2.startDuration = 1;

			// AXES
			// category
			var categoryAxis2 = chart2.categoryAxis;
			categoryAxis2.labelRotation = 0;
			categoryAxis2.gridPosition = "start";

			// value
			// in case you don't want to change default settings of value axis,
			// you don't need to create it, as one value axis is created automatically.

			// GRAPH
			var graph2 = new AmCharts.AmGraph();
			graph2.valueField = "visits";
			graph2.balloonText = "[[category]]: <b>[[value]]</b>";
			graph2.type = "column";
			graph2.lineAlpha = 0;
			graph2.fillAlphas = 0.8;
			chart2.addGraph(graph2);

			// CURSOR
			var chartCursor = new AmCharts.ChartCursor();
			chartCursor.cursorAlpha = 0;
			chartCursor.zoomable = false;
			chartCursor.categoryBalloonEnabled = false;
			chart2.addChartCursor(chartCursor);

			chart2.creditsPosition = "top-right";

			chart2.write("divForDays");
		});
	</script>


	<!-- Socket.IO -->



	<script>
		$(document)
				.ready(

						function() {
							$('.chart').easyPieChart({
								barColor : '#f8ac59',
								//                scaleColor: false,
								scaleLength : 5,
								lineWidth : 4,
								size : 80
							});

							$('.chart2').easyPieChart({
								barColor : '#1c84c6',
								//                scaleColor: false,
								scaleLength : 5,
								lineWidth : 4,
								size : 80
							});

							var data2 = [ [ gd(2012, 1, 1), 7 ],
									[ gd(2012, 1, 2), 6 ],
									[ gd(2012, 1, 3), 4 ],
									[ gd(2012, 1, 4), 8 ],
									[ gd(2012, 1, 5), 9 ],
									[ gd(2012, 1, 6), 7 ],
									[ gd(2012, 1, 7), 5 ],
									[ gd(2012, 1, 8), 4 ],
									[ gd(2012, 1, 9), 7 ],
									[ gd(2012, 1, 10), 8 ],
									[ gd(2012, 1, 11), 9 ],
									[ gd(2012, 1, 12), 6 ],
									[ gd(2012, 1, 13), 4 ],
									[ gd(2012, 1, 14), 5 ],
									[ gd(2012, 1, 15), 11 ],
									[ gd(2012, 1, 16), 8 ],
									[ gd(2012, 1, 17), 8 ],
									[ gd(2012, 1, 18), 11 ],
									[ gd(2012, 1, 19), 11 ],
									[ gd(2012, 1, 20), 6 ],
									[ gd(2012, 1, 21), 6 ],
									[ gd(2012, 1, 22), 8 ],
									[ gd(2012, 1, 23), 11 ],
									[ gd(2012, 1, 24), 13 ],
									[ gd(2012, 1, 25), 7 ],
									[ gd(2012, 1, 26), 9 ],
									[ gd(2012, 1, 27), 9 ],
									[ gd(2012, 1, 28), 8 ],
									[ gd(2012, 1, 29), 5 ],
									[ gd(2012, 1, 30), 8 ],
									[ gd(2012, 1, 31), 25 ] ];

							var data3 = [ [ gd(2012, 1, 1), 800 ],
									[ gd(2012, 1, 2), 500 ],
									[ gd(2012, 1, 3), 600 ],
									[ gd(2012, 1, 4), 700 ],
									[ gd(2012, 1, 5), 500 ],
									[ gd(2012, 1, 6), 456 ],
									[ gd(2012, 1, 7), 800 ],
									[ gd(2012, 1, 8), 589 ],
									[ gd(2012, 1, 9), 467 ],
									[ gd(2012, 1, 10), 876 ],
									[ gd(2012, 1, 11), 689 ],
									[ gd(2012, 1, 12), 700 ],
									[ gd(2012, 1, 13), 500 ],
									[ gd(2012, 1, 14), 600 ],
									[ gd(2012, 1, 15), 700 ],
									[ gd(2012, 1, 16), 786 ],
									[ gd(2012, 1, 17), 345 ],
									[ gd(2012, 1, 18), 888 ],
									[ gd(2012, 1, 19), 888 ],
									[ gd(2012, 1, 20), 888 ],
									[ gd(2012, 1, 21), 987 ],
									[ gd(2012, 1, 22), 444 ],
									[ gd(2012, 1, 23), 999 ],
									[ gd(2012, 1, 24), 567 ],
									[ gd(2012, 1, 25), 786 ],
									[ gd(2012, 1, 26), 666 ],
									[ gd(2012, 1, 27), 888 ],
									[ gd(2012, 1, 28), 900 ],
									[ gd(2012, 1, 29), 178 ],
									[ gd(2012, 1, 30), 555 ],
									[ gd(2012, 1, 31), 993 ] ];

							var dataset = [ {
								label : "Number of orders",
								data : data3,
								color : "#1ab394",
								bars : {
									show : true,
									align : "center",
									barWidth : 24 * 60 * 60 * 600,
									lineWidth : 0
								}

							}, {
								label : "Payments",
								data : data2,
								yaxis : 2,
								color : "#1C84C6",
								lines : {
									lineWidth : 1,
									show : true,
									fill : true,
									fillColor : {
										colors : [ {
											opacity : 0.2
										}, {
											opacity : 0.4
										} ]
									}
								},
								splines : {
									show : false,
									tension : 0.6,
									lineWidth : 1,
									fill : 0.1
								},
							} ];

							var options = {
								xaxis : {
									mode : "time",
									tickSize : [ 3, "day" ],
									tickLength : 0,
									axisLabel : "Date",
									axisLabelUseCanvas : true,
									axisLabelFontSizePixels : 12,
									axisLabelFontFamily : 'Arial',
									axisLabelPadding : 10,
									color : "#d5d5d5"
								},
								yaxes : [ {
									position : "left",
									max : 1070,
									color : "#d5d5d5",
									axisLabelUseCanvas : true,
									axisLabelFontSizePixels : 12,
									axisLabelFontFamily : 'Arial',
									axisLabelPadding : 3
								}, {
									position : "right",
									clolor : "#d5d5d5",
									axisLabelUseCanvas : true,
									axisLabelFontSizePixels : 12,
									axisLabelFontFamily : ' Arial',
									axisLabelPadding : 67
								} ],
								legend : {
									noColumns : 1,
									labelBoxBorderColor : "#000000",
									position : "nw"
								},
								grid : {
									hoverable : false,
									borderWidth : 0
								}
							};

							function gd(year, month, day) {
								return new Date(year, month - 1, day).getTime();
							}

							var previousPoint = null, previousLabel = null;

							$
									.plot($("#flot-dashboard-chart"), dataset,
											options);

							var mapData = {
								"US" : 298,
								"SA" : 200,
								"DE" : 220,
								"FR" : 540,
								"CN" : 120,
								"AU" : 760,
								"BR" : 550,
								"IN" : 200,
								"GB" : 120,
							};

							$('#world-map').vectorMap({
								map : 'world_mill_en',
								backgroundColor : "transparent",
								regionStyle : {
									initial : {
										fill : '#e4e4e4',
										"fill-opacity" : 0.9,
										stroke : 'none',
										"stroke-width" : 0,
										"stroke-opacity" : 0
									}
								},

								series : {
									regions : [ {
										values : mapData,
										scale : [ "#1ab394", "#22d6b1" ],
										normalizeFunction : 'polynomial'
									} ]
								},
							});
						});
	</script>
</body>
</html>