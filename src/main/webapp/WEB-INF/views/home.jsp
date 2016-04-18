<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
</head>
<body ng-app="App">
	<!-- Update user modal-->
	<div id="updateUserModal" class="modal fade" role="dialog" ng-controller="updateUserController">
		<div class="modal-dialog" style="width: 400px">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Update info</h4>
				</div>
				<form action="updateUser/${logedUser.id}" method="POST">
					<input class="form-control" name="email" type="text" id="regEmail"
						placeholder="Email"
						style="width: 300px; height: 45px; margin: auto auto; margin-top: 35px;" value="${logedUser.email}">

					<input class="form-control" type="text" id="firstName"
						placeholder="First name"
						style="width: 300px; height: 45px; margin: auto auto; margin-top: 15px;"
						name="firstName" ; value="${logedUser.firstName}"/> <input
						class="form-control" name="lastName" type="text" id="lastName"
						placeholder="Last name"
						style="width: 300px; height: 45px; margin: auto auto; margin-top: 15px;"
						 value="${logedUser.lastName}" /> <input class="form-control"
						type="text" id="regPassword" name="password"
						placeholder="Password"
						style="width: 300px; height: 45px; margin: auto auto; margin-top: 15px;"
						 value="${logedUser.password}" />

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
							src="../springmvc/resources/img/profile_small.jpg" />
						</span> <a data-toggle="dropdown" class="dropdown-toggle" href="#"> <span
							class="clear"> <span class="block m-t-xs"> <strong
									class="font-bold">${logedUser.firstName }
										${logedUser.lastName}</strong>
							</span> <span class="text-muted text-xs block">${ logedUser.role.roleName}</span>
						</span>
						</a>
					</div>
					<div class="logo-element">QFC</div>
				</li>
				<!-- Admin side menu -->
				<c:choose>
					<c:when test="${logedUser.role.roleName == 'Admin'}">
						<li><a data-toggle="modal" data-target="#registerRestaurantModal"><i class="fa fa-plus"></i> <span
								class="nav-label">Add restaurant</span></a></li>
					</c:when>
				</c:choose>
				<!-- Regular user side menu -->
				<c:choose>
					<c:when test="${logedUser.role.roleName == 'Regular user'}">
						<li><a href="layouts.html"><i class="fa fa-user-plus"></i>
								<span class="nav-label">Add friends</span></a></li>
						<li><a href="#"><i class="fa fa-wrench"></i> <span
								class="nav-label" data-toggle="modal"
								data-target="#updateUserModal">Update profile</span></a></li>
					</c:when>
				</c:choose>
				<!-- Manager side menu -->
				<c:choose>
						<c:when test="${logedUser.role.roleName	 == 'Manager'}">
							
								
									<li class="promeniCent" rel="panel1" class="active"><a><i class="fa fa-share-square-o"></i> <span
										class="nav-label">Update restaurant</span></a></li>
									<li class="promeniCent" rel="panel2"><a ><i class="fa fa-users"></i>
									<span
										class="nav-label">Employees</span></a></li>
									<li class="promeniCent" rel="panel3"><a><i class="fa fa-line-chart"></i> <span
										class="nav-label">Reports</span></a></li>
								
	
	
						</c:when>
				</c:choose>

				<li><a href="grid_options.html"><i class="fa fa-user"></i>
						<span class="nav-label">View profile</span></a></li>
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
					<c:choose>
						<c:when test="${logedUser.role.roleName == 'Admin'}">
							<form role="search" class="navbar-form-custom"
								action="search_results.html">
								<div class="form-group">
									<input type="text" placeholder="Search for something..."
										class="form-control" name="top-search" id="top-search">
								</div>
							</form>
						</c:when>
					</c:choose>

				</div>
				<ul class="nav navbar-top-links navbar-right">
					<li><a href="logout"> <i class="fa fa-sign-out"></i> Log
							out
					</a></li>
				</ul>

				</nav>
			</div>
			<!-- Regular user central part -->
			<c:choose>
				<c:when test="${logedUser.role.roleName == 'Regular user'}">
					<div class="wrapper wrapper-content">
						<div class="row">
							<div class="col-lg-6">
								<div class="ibox float-e-margins">
									<div class="ibox-title">
										<h5>Restaurants</h5>
										<div class="ibox-tools">
											<a class="collapse-link"> <i class="fa fa-chevron-up"></i></a>
										</div>
									</div>
									<div class="ibox-content ibox-heading"
										style="background-color: #fff">
										<h3>
											<i class="fa fa-glass"></i> Restaurant list
										</h3>
									</div>
									<div class="ibox-content">
										<div class="feed-activity-list">
											<c:forEach var="restaurant" items="${restaurants}">
												<div class="feed-element">
													<div>
														<small class="pull-right">1m ago</small> <strong><a
															href="restaurant/${restaurant.id}" style="color: #676a6c">${restaurant.name}</a></strong>
														<div>Lorem Ipsum is simply dummy text of the
															printing and typesetting industry. Lorem Ipsum</div>
														<small class="text-muted">Open 9 am - 11 pm</small>
													</div>
												</div>
											</c:forEach>
										</div>
									</div>
								</div>
							</div>
							<div class="col-lg-6">
								<div class="ibox" style="margin-top: 30px">
									<div class="ibox-content">
										<div class="ibox-content ibox-heading"
											style="background-color: #fff">
											<h3>
												<i class="fa fa-history"></i> Recent visits
											</h3>
										</div>
										<div class="table-responsive">
											<table class="table table-striped">

												<tbody>
													<tr>
														<td>Testone Restaurant</td>
														<td>Inceptos Hymenaeos Ltd</td>
														<td>Jul 18, 2015</td>
														<td>4</td>
													</tr>
													<tr>
														<td>Testtwo Restaurant</td>
														<td>Nec Euismod In Company</td>
														<td>Jul 16, 2015</td>
														<td>5</td>

													</tr>
													<tr>
														<td>Testthree Restaurant</td>
														<td>Erat Volutpat</td>
														<td>Jul 18, 2015</td>
														<td>3</td>
													</tr>
													<tr>
														<td>Testfout Restaurant</td>
														<td>Tellus Ltd</td>
														<td>Jul 22, 2015</td>
														<td>5</td>
													</tr>
													<tr>
														<td>Testfive Restaurant</td>
														<td>Nec Euismod In Company</td>
														<td>Jul 16, 2015</td>
														<td>2</td>
													</tr>
												</tbody>
											</table>
										</div>

									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="navbar-header">
								<form role="search" class="navbar-form-custom"
									action="search_results.html">
									<div class="form-group">
										<input type="text" placeholder="Search for friends..."
											class="form-control" name="top-search" id="top-search"
											style="margin-left: 20px">
									</div>
								</form>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-4">
								<div class="contact-box">
									<a href="profile.html">
										<div class="col-sm-4">
											<div class="text-center">
												<img alt="image" class="img-circle m-t-xs img-responsive"
													src="../springmvc/resources/img/a2.jpg">
											</div>
										</div>
										<div class="col-sm-8">
											<h3>
												<strong>John Smith</strong>
											</h3>
											<p>
												<i class="fa fa-map-marker"></i> Riviera State 32/106
											</p>
											<address>
												<strong>CodeVsion</strong><br> 795 Folsom Ave, Suite
												600<br> San Francisco, CA 94107<br> <abbr
													title="Phone">P:</abbr> 063 456-7890
											</address>
										</div>
										<div class="clearfix"></div>
									</a>
								</div>
							</div>
							<div class="col-lg-4">
								<div class="contact-box">
									<a href="profile.html">
										<div class="col-sm-4">
											<div class="text-center">
												<img alt="image" class="img-circle m-t-xs img-responsive"
													src="../springmvc/resources/img/a1.jpg">
											</div>
										</div>
										<div class="col-sm-8">
											<h3>
												<strong>Alex Johnatan</strong>
											</h3>
											<p>
												<i class="fa fa-map-marker"></i> Riviera State 32/106
											</p>
											<address>
												<strong>CodeVision</strong><br> 795 Folsom Ave, Suite
												600<br> San Francisco, CA 94107<br> <abbr
													title="Phone">P:</abbr> 065 456-7890
											</address>
										</div>
										<div class="clearfix"></div>
									</a>
								</div>
							</div>
							<div class="col-lg-4">
								<div class="contact-box">
									<a href="profile.html">
										<div class="col-sm-4">
											<div class="text-center">
												<img alt="image" class="img-circle m-t-xs img-responsive"
													src="../springmvc/resources/img/a3.jpg">
											</div>
										</div>
										<div class="col-sm-8">
											<h3>
												<strong>Monica Smith</strong>
											</h3>
											<p>
												<i class="fa fa-map-marker"></i> Riviera State 32/106
											</p>
											<address>
												<strong>CodeVision</strong><br> 795 Folsom Ave, Suite
												600<br> San Francisco, CA 94107<br> <abbr
													title="Phone">P:</abbr> 064 456-7890
											</address>
										</div>
										<div class="clearfix"></div>
									</a>
								</div>
							</div>
							<div class="col-lg-4">
								<div class="contact-box">
									<a href="profile.html">
										<div class="col-sm-4">
											<div class="text-center">
												<img alt="image" class="img-circle m-t-xs img-responsive"
													src="../springmvc/resources/img/a4.jpg">
											</div>
										</div>
										<div class="col-sm-8">
											<h3>
												<strong>Michael Zimber</strong>
											</h3>
											<p>
												<i class="fa fa-map-marker"></i> Riviera State 32/106
											</p>
											<address>
												<strong>CodeVision</strong><br> 795 Folsom Ave, Suite
												600<br> San Francisco, CA 94107<br> <abbr
													title="Phone">P:</abbr> 061 456-7890
											</address>
										</div>
										<div class="clearfix"></div>
									</a>
								</div>
							</div>
							<div class="col-lg-4">
								<div class="contact-box">
									<a href="profile.html">
										<div class="col-sm-4">
											<div class="text-center">
												<img alt="image" class="img-circle m-t-xs img-responsive"
													src="../springmvc/resources/img/a5.jpg">
											</div>
										</div>
										<div class="col-sm-8">
											<h3>
												<strong>Sandra Smith</strong>
											</h3>
											<p>
												<i class="fa fa-map-marker"></i> Riviera State 32/106
											</p>
											<address>
												<strong>CodeVision</strong><br> 795 Folsom Ave, Suite
												600<br> San Francisco, CA 94107<br> <abbr
													title="Phone">P:</abbr> 064 456-7890
											</address>
										</div>
										<div class="clearfix"></div>
									</a>
								</div>
							</div>
						</div>
						<div class="footer">
							<div>
								<strong>Copyright</strong> Quest For Chef &copy; 2016
							</div>
						</div>
					</div>
				</c:when>
			</c:choose>
			<!-- Admin central part -->
			<c:choose>
				<c:when test="${logedUser.role.roleName == 'Admin'}">
					<!-- List of restaurants -->
					<div class="wrapper wrapper-content">
						<div class="row">
							<div class="col-lg-6">
								<div class="ibox float-e-margins">
									<div class="ibox-title">
										<h5>Restaurants</h5>
										<div class="ibox-tools">
											<a class="collapse-link"> <i class="fa fa-chevron-up"></i></a>
										</div>
									</div>
									<div class="ibox-content ibox-heading">
										<h3>
											<i class="fa fa-glass"></i> Restaurant list
										</h3>
									</div>
									<div class="ibox-content">
										<div class="feed-activity-list">

											<div class="feed-element">
												<div>
													<small class="pull-right text-navy">1m ago</small> <strong>Testone restaurant</strong>
													<div>Lorem Ipsum is simply dummy text of the printing
														and typesetting industry. Lorem Ipsum</div>
													<small class="text-muted">Open 9 am -
														11 pm</small>
												</div>
											</div>

											<div class="feed-element">
												<div>
													<small class="pull-right">2m ago</small> <strong>Testwo restaurant</strong>
													<div>There are many variations of passages of Lorem
														Ipsum available</div>
													<small class="text-muted">Open 8 am -
														12 pm</small>
												</div>
											</div>

											<div class="feed-element">
												<div>
													<small class="pull-right">5m ago</small> <strong>Testthree restaurant</strong>
													<div>Contrary to popular belief, Lorem Ipsum</div>
													<small class="text-muted">Open 10 am -
														01 am</small>
												</div>
											</div>

											<div class="feed-element">
												<div>
													<small class="pull-right">5m ago</small> <strong>Testfour restaurant</strong>
													<div>The generated Lorem Ipsum is therefore</div>
													<small class="text-muted">Open 9 am -
														11 pm</small>
												</div>
											</div>


											<div class="feed-element">
												<div>
													<small class="pull-right">5m ago</small> <strong>Testfive restaurant</strong>
													<div>All the Lorem Ipsum generators on the Internet
														tend to repeat</div>
													<small class="text-muted">Open 9 am -
														9 pm</small>
												</div>
											</div>
											<div class="feed-element">
												<div>
													<small class="pull-right">5m ago</small> <strong>Testsix restaurant</strong>
													<div>The standard chunk of Lorem Ipsum used</div>
													<small class="text-muted">Open 10 am -
														01 pm</small>
												</div>
											</div>
											<div class="feed-element">
												<div>
													<small class="pull-right">5m ago</small> <strong>Testseven restaurant</strong>
													<div>200 Latin words, combined with a handful</div>
													<small class="text-muted">Open 8 am -
														10 pm</small>
												</div>
											</div>

										</div>
									</div>
								</div>
							</div>	
						</div>
					</div>	
					
					<!-- Modal for restaurant registration -->
					<div id="registerRestaurantModal" class="modal fade" role="dialog">
						<div class="modal-dialog" style="width: 400px">
				
							<!-- Modal content-->
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title">Register restaurant</h4>
								</div>
								<form action="register" method="POST">
									<input class="form-control" type="text" id="name" name="name"
										placeholder="Restaurant name"
										style="width: 300px; height: 45px; margin: auto auto; margin-top: 35px;"/>
                                    
									<!-- <input class="form-control" type="text" id="restType"
										placeholder="Restaurant type"
										style="width: 300px; height: 45px; margin: auto auto; margin-top: 15px;"
										/> --> 
									<input class="form-control"
										type="text" id="address" placeholder="Address" name="address"
										style="width: 300px; height: 45px; margin: auto auto; margin-top: 15px;"
										/> 
									<input class="form-control"
										type="text" id="manName"
										placeholder="Manager's first name"
										style="width: 300px; height: 45px; margin: auto auto; margin-top: 15px;"
										/>
									<input class="form-control"
										type="text" id="manLastName"
										placeholder="Manager's last name"
										style="width: 300px; height: 45px; margin: auto auto; margin-top: 15px;"
										/>

									<div class="modal-footer" style="margin-top: 15px;">
										<button type="submit" class="btn btn-success"
											style="background: #1ab394">Register</button>
										<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
									</div>
								</form>
							</div>
				
						</div>
					</div>
					
				</c:when>
			</c:choose>
			<!-- Manager central part -->
			<c:choose>
				<c:when test="${logedUser.role.roleName == 'Manager'}">
					
				</c:when>
			</c:choose>
		</div>
		<!-- Mainly scripts -->
		<script src="../springmvc/resources/js/jquery-2.1.1.js"></script>
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
		<script
			src="../springmvc/resources/js/plugins/flot/jquery.flot.pie.js"></script>
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

		<!-- Sparkline demo data  -->
		<script src="../springmvc/resources/js/demo/sparkline-demo.js"></script>
		<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.9/angular.min.js"></script>
	<script type="text/javascript" src="../springmvc/resources/js/app.js"></script>

		<script>
			$(document).ready(
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
								[ gd(2012, 1, 2), 6 ], [ gd(2012, 1, 3), 4 ],
								[ gd(2012, 1, 4), 8 ], [ gd(2012, 1, 5), 9 ],
								[ gd(2012, 1, 6), 7 ], [ gd(2012, 1, 7), 5 ],
								[ gd(2012, 1, 8), 4 ], [ gd(2012, 1, 9), 7 ],
								[ gd(2012, 1, 10), 8 ], [ gd(2012, 1, 11), 9 ],
								[ gd(2012, 1, 12), 6 ], [ gd(2012, 1, 13), 4 ],
								[ gd(2012, 1, 14), 5 ],
								[ gd(2012, 1, 15), 11 ],
								[ gd(2012, 1, 16), 8 ], [ gd(2012, 1, 17), 8 ],
								[ gd(2012, 1, 18), 11 ],
								[ gd(2012, 1, 19), 11 ],
								[ gd(2012, 1, 20), 6 ], [ gd(2012, 1, 21), 6 ],
								[ gd(2012, 1, 22), 8 ],
								[ gd(2012, 1, 23), 11 ],
								[ gd(2012, 1, 24), 13 ],
								[ gd(2012, 1, 25), 7 ], [ gd(2012, 1, 26), 9 ],
								[ gd(2012, 1, 27), 9 ], [ gd(2012, 1, 28), 8 ],
								[ gd(2012, 1, 29), 5 ], [ gd(2012, 1, 30), 8 ],
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

						$.plot($("#flot-dashboard-chart"), dataset, options);

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