<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${logedUser.firstName}'spage</title>
<link href="../resources/css/bootstrap.min.css" rel="stylesheet">
<link href="../resources/font-awesome/css/font-awesome.css"
	rel="stylesheet">

<link href="../resources/css/animate.css" rel="stylesheet">
<link href="../resources/css/plugins/clockpicker/clockpicker.css"
	rel="stylesheet">
<link href="../resources/css/style.css" rel="stylesheet">
<link
	href="//netdna.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css"
	rel="stylesheet">
<link href="../resources/css/plugins/nouslider/jquery.nouislider.css"
	rel="stylesheet">
<link href="../resources/css/plugins/toastr/toastr.min.css"
	rel="stylesheet">
<link
	href="../resources/css/plugins/touchspin/jquery.bootstrap-touchspin.min.css"
	rel="stylesheet">
<link href="../resources/css/plugins/ionRangeSlider/ion.rangeSlider.css"
	rel="stylesheet">
<link
	href="../resources/css/plugins/ionRangeSlider/ion.rangeSlider.skinFlat.css"
	rel="stylesheet">
<!-- FooTable -->
<link href="../resources/css/plugins/footable/footable.core.css"
	rel="stylesheet">

<link href="../resources/css/animate.css" rel="stylesheet">
<link
	href="../resources/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="../resources/css/dropzone.css">
<script src="../resources/js/jquery-2.1.1.js"></script>
<style type="text/css">
.starrr {
	display: inline-block;
}

.starrr i {
	font-size: 16px;
	padding: 0 1px;
	cursor: pointer;
	color: #ffd119;
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

.menuButton {
	cursor: pointer;
}

.resSpan {
	cursor: pointer;
}
</style>
<body ng-app="qfcApp" ng-controller="MyCtrl">

	<!-- Update user modal-->
	<div id="updateUserModal" class="modal fade" role="dialog">
		<div class="modal-dialog" style="width: 400px">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Update info</h4>
				</div>
				<form action="updateUser" method="POST">
					<input class="form-control" name="email" type="text" id="regEmail"
						placeholder="Email"
						style="width: 300px; height: 45px; margin: auto auto; margin-top: 35px;"
						value="${logedUser.email}"> <input class="form-control"
						type="text" id="firstName" placeholder="First name"
						style="width: 300px; height: 45px; margin: auto auto; margin-top: 15px;"
						name="firstName" ; value="${logedUser.firstName} aaaaa" /> <input
						class="form-control" name="lastName" type="text" id="lastName"
						placeholder="Last name"
						style="width: 300px; height: 45px; margin: auto auto; margin-top: 15px;"
						ng-model="username_model" value="${logedUser.lastName}" /> <input
						class="form-control" type="text" id="regPassword" name="password"
						placeholder="Password"
						style="width: 300px; height: 45px; margin: auto auto; margin-top: 15px;"
						ng-model="reg_password_model" value="${logedUser.password}" />

					<div class="modal-footer" style="margin-top: 15px;">
						<button type="submit" class="btn btn-success"
							style="background: #1ab394">Update</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- End update user -->
	
	<div id="wrapper">
		<nav class="navbar-default navbar-static-side" role="navigation">
		<div class="sidebar-collapse">
			<ul class="nav metismenu" id="side-menu">
				<li class="nav-header">
					<div class="dropdown profile-element">
						<span> <img alt="image" class="img-circle"
							src="../resources/img/profile_small.jpg" />
						</span> <a data-toggle="dropdown" class="dropdown-toggle" href="#"> <span
							class="clear"> <span class="block m-t-xs"> <strong
									class="font-bold" ng-model="userCred">{{userCred}}
										</strong>
							</span> <span class="text-muted text-xs block" ng-model="userRole">{{userRole}}</span>
						</span>
						</a>
					</div>
					<div class="logo-element">QFC</div>
				</li>
			

			
				

			

				
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
					<li><a href="../logout"> <i class="fa fa-sign-out"></i>
							Log out
					</a></li>
				</ul>

				</nav>
			</div>

			<!-- Regular user central part -->
					<div class="wrapper wrapper-content animated fadeInRight">
						<div class="row">
							<div class="col-lg-12">

								<div class="ibox product-detail">
									<div class="ibox-content">

										<div class="row">
											<div class="col-md-5">


												<div class="product-images">

													<div>
														<div class="image-imitation" id="map">[IMAGE 1]</div>
													</div>
												</div>

											</div>
											<div class="col-md-7">

												<h2 class="font-bold m-b-xs" ng-model="resName">{{resName}}</h2>
												<small>Restaurant type</small>
												<hr>
												<div class="starrr"></div>

												<div>

													<h1 class="product-main-price" ng-model="resEmail">{{resEmail}}</h1>
												</div>
												<hr>
												<h4>Restaurant description</h4>

												<div class="small text-muted">
													It is a long established fact that a reader will be
													distracted by the readable content of a page when looking
													at its layout. The point of using Lorem Ipsum is that it
													has a more-or-less normal distribution of letters, as
													opposed to using 'Content here, content here', making it
													look like readable English. <br /> <br /> There are many
													variations of passages of Lorem Ipsum available, but the
													majority have suffered alteration in some form, by injected
													humour, or randomised words which don't look even slightly
													believable.
												</div>
												<dl class="dl-horizontal m-t-md small">
													<dt>Address</dt>
													<dd ng-model="resAddress">{{resAddress}}</dd>
													<dt>City</dt>
													<dd ng-model="resCity">{{resCity}}</dd>
													<dt>Phone</dt>
													<dd ng-model="resPhone">{{resPhone}}<dd>
													
													
												</dl>
												<div class="text-right">
													
											</div>
											 <div class="dropzone" id="dropzoneFileUpload">
                                    
                                			</div>
										</div>

									</div>
								</div>

							</div>
						</div>
						<div class="row">
							<div class="col-lg-12">
								<div class="ibox">
									<div class="ibox-title">
									
											<input class="form-control" type="search" ng-model="rc.ocena"
											placeholder="filter by rate..." aria-label="filter friends"
											style="margin-top: -7px;width:30%;float:right;margin-left:15px" />
									
									</div>

									<div class="ibox-content">
										<div class="row">
											
											<div id="inputHolder">
												
											</div>
											<li class="animate-repeat"
														ng-repeat="friend in friends | filter:rc" nop fs="friend" friendss="friends">
														<div class="feed-element">
															<div
																style="background: #f8f8f9; float: left; height: 100px; width: 100px; margin-right: 10px;overflow:hidden" id="userPic{{friend.id}}">
																
															</div>
															<div>
																<big class="pull-right" style="padding:15px">{{friend.ocena}}</big> <strong ng-model="userRecName" id="userDet{{friend.id}}">{{userRecName}}</strong>
																<div>{{friend.text}}</div>
																
																<small class="text-muted"></small>
																<div id="deletea{{friend.id}}">
																
																</div>
																
															</div>
															
														</div>
		
													</li>
													<p ng-show="(friends | filter:rc).length == 0" style="text-align:center">No results found!</p>

</div>
										</div>

									</div>
								</div>
							</div>
						</div>
						
						</div>
					</div>


		

			

		</div>
	</div>
	<!-- Mainly scripts -->
	<script src="../resources/js/bootstrap.min.js"></script>
	<script src="../resources/js/plugins/metisMenu/jquery.metisMenu.js"></script>
	<script
		src="../resources/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

	<!-- Flot -->
	<script src="/resources/js/plugins/flot/jquery.flot.js"></script>
	<script src="../resources/js/plugins/flot/jquery.flot.tooltip.min.js"></script>
	<script src="../resources/js/plugins/flot/jquery.flot.spline.js"></script>
	<script src="../resources/js/plugins/flot/jquery.flot.resize.js"></script>
	<script src="../resources/js/plugins/flot/jquery.flot.pie.js"></script>
	<script src="../resources/js/plugins/flot/jquery.flot.symbol.js"></script>
	<script src="../resources/js/plugins/flot/jquery.flot.time.js"></script>

	<!-- Peity -->
	<script src="../resources/js/plugins/peity/jquery.peity.min.js"></script>
	<script src="../resources/js/demo/peity-demo.js"></script>

	<!-- Custom and plugin javascript -->
	<script src="../resources/js/inspinia.js"></script>
	<script src="../resources/js/plugins/pace/pace.min.js"></script>

	<!-- jQuery UI -->
	<script src="/resources/js/plugins/jquery-ui/jquery-ui.min.js"></script>

	<!-- Jvectormap -->
	<script
		src="../resources/js/plugins/jvectormap/jquery-jvectormap-2.0.2.min.js"></script>
	<script
		src="../resources/js/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>

	<!-- EayPIE -->
	<script
		src="../resources/js/plugins/easypiechart/jquery.easypiechart.js"></script>

	<!-- Sparkline -->
	<script src="../resources/js/plugins/sparkline/jquery.sparkline.min.js"></script>


	<script src="../resources/five-star-rating-master/dist/starrr.js"></script>
	<!-- JSKnob -->
	<script src="../resources/js/plugins/jsKnob/jquery.knob.js"></script>
	<!-- NouSlider -->
	<script
		src="../resources/js/plugins/nouslider/jquery.nouislider.min.js"></script>
	<!-- IonRangeSlider -->
	<script
		src="../resources/js/plugins/ionRangeSlider/ion.rangeSlider.min.js"></script>


	<script
		src="../resources/js/plugins/touchspin/jquery.bootstrap-touchspin.min.js"></script>
	<!-- Socket.IO -->
	<script src="https://cdn.socket.io/socket.io-1.4.5.js"></script>
	<script src="http://localhost:3000/socket.io/socket.io.js"></script>
	<!-- Idle Timer plugin -->
	<script src="../resources/js/plugins/idle-timer/idle-timer.min.js"></script>

	<!-- Toastr script -->
	<script src="../resources/js/plugins/toastr/toastr.min.js"></script>
		<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.9/angular.min.js"></script>
	
	<script
		src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.9/angular-route.js"></script>
	<script
		src="//angular-ui.github.io/bootstrap/ui-bootstrap-tpls-2.1.3.js"></script>
	<!-- TouchSpin -->
	<script>
      var map;
      function initMap() {
        map = new google.maps.Map(document.getElementById('map'), {
          center: {lat: -34.397, lng: 150.644},
          zoom: 9
        });
        
        var marker = new google.maps.Marker({
            position: {lat: -34, lng: 150.644},
            map: map,
            title: 'Hello World!'
          });
      }
    </script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBC9R42hOf-nWbmlKe_CWuBvV8G3RMzzkI&callback=initMap"
    async defer></script>
	<script src="../resources/js/validationByPage/restaurantPage.js"></script>
	
	
	<script type="text/javascript"
		src="../resources/js/dropzone.js"></script>

	
	
	 <script type="text/javascript">
	 	
        var baseUrl = "/springmvc/saveImage";
        Dropzone.autoDiscover = false;
        var myDropzone = new Dropzone("div#dropzoneFileUpload", {
            url: baseUrl,
            params: {
           		id: 2
            }
        });
        Dropzone.options.myAwesomeDropzone = {
            paramName: "file", // The name that will be used to transfer the file
            maxFilesize: 2, // MB
            addRemoveLinks: true,
            maxFiles: 1,
            accept: function(file, done) {
 
            },
        };
    </script>
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