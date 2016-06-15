<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${logedUser.firstName}'spage</title>
<link href="../resources/css/bootstrap.min.css" rel="stylesheet">
<link href="../resources/font-awesome/css/font-awesome.css"
	rel="stylesheet">

<link href="../resources/css/animate.css" rel="stylesheet">
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
<link
	href="../resources/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css"
	rel="stylesheet">
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

#customers tr:nth-child(even){background-color: #f2f2f2}

#customers tr:hover {background-color: #ddd;}

#customers th {
    padding-top: 12px;
    padding-bottom: 12px;
    background-color: #4CAF50;
    color: white;
}
</style>
<body>

	<c:if test="${!empty addedNewOffer}">
		<div class="alert alert-success" id="errorAlert"
			style="text-align: center; position: absolute; width: 100%;z-index: 10000;">
			<strong>${addedNewOffer}</strong>
		</div>
	</c:if>
	
	<c:if test="${!empty updatedOffer}">
		<div class="alert alert-success" id="errorAlert"
			style="text-align: center; position: absolute; width: 100%;z-index: 10000;">
			<strong>${updatedOffer}</strong>
		</div>
	</c:if>
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
						name="firstName" ; value="${logedUser.firstName}" /> <input
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
	<!-- Staff list modal-->
	<div id="staffListModal" class="modal fade" role="dialog">
		<div class="modal-dialog" style="width: 490px">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Staff list</h4>
				</div>
				<c:forEach var="staff" items="${restaurant.staff}">
					<div class="row">
						<div class="col-lg-4" style="width: 500px; heigh: 300px;">
							<div class="contact-box" style="border: none">
								<a href="profile.html">
									<div class="col-sm-4" style="width: 23%">
										<div class="text-center">
											<img alt="image" class="img-circle m-t-xs img-responsive"
												src="../resources/img/${staff.picture}">
										</div>
									</div>
									<div class="col-sm-8">
										<h3>
											<strong>${staff.firstName} ${staff.lastName}</strong>

										</h3>
										<p>
											<i class="fa fa-map-marker"></i> ${staff.role.roleName}
										</p>
										<p>
											<i class="fa fa-envelope"></i> ${staff.email}
										</p>

									</div>
									<div class="clearfix"></div>
								</a>
							</div>
						</div>
					</div>
				</c:forEach>
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
									class="font-bold">${logedUser.firstName }
										${logedUser.lastName}</strong>
							</span> <span class="text-muted text-xs block">${logedUser.role.roleName}</span>
						</span>
						</a>
					</div>
					<div class="logo-element">QFC</div>
				</li>
				<!-- Admin side menu -->
				<c:choose>
					<c:when test="${logedUser.role.roleName == 'Admin'}">
						<li><a href="layouts.html"><i class="fa fa-plus"></i> <span
								class="nav-label">Add restaurant</span></a></li>
					</c:when>
				</c:choose>
				
				<c:choose>
					<c:when test="${logedUser.role.roleName == 'Bidder'}">
						<li><a data-toggle="modal"
							data-target="#bidderProfilUp"><i class="fa fa-user"></i>
								<span class="nav-label">Update profil</span></a></li>
						<li><a data-toggle="modal"
							data-target="#bidderPassword"><i class="fa fa-key"></i>
								<span class="nav-label">Change password</span></a></li>
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


						<li class="promeniCent" rel="panel1" class="active"><a><i
								class="fa fa-share-square-o"></i> <span class="nav-label">Update
									restaurant</span></a></li>
						<li class="promeniCent" rel="panel2"><a><i
								class="fa fa-users"></i> <span class="nav-label">Employees</span></a></li>
						<li class="promeniCent" rel="panel3"><a><i
								class="fa fa-line-chart"></i> <span class="nav-label">Reports</span></a></li>



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
					<li><a href="../logout"> <i class="fa fa-sign-out"></i>
							Log out
					</a></li>
				</ul>

				</nav>
			</div>

			<!-- Regular user central part -->
			<c:choose>
				<c:when test="${logedUser.role.roleName == 'Regular user'}">
					<div class="wrapper wrapper-content animated fadeInRight">
						<div class="row">
							<div class="col-lg-12">

								<div class="ibox product-detail">
									<div class="ibox-content">

										<div class="row">
											<div class="col-md-5">


												<div class="product-images">

													<div>
														<div class="image-imitation">[IMAGE 1]</div>
													</div>
												</div>

											</div>
											<div class="col-md-7">

												<h2 class="font-bold m-b-xs">${restaurant.name}</h2>
												<small>Restaurant type</small>
												<hr>
												<div class="starrr"></div>

												<div>

													<h1 class="product-main-price">${restaurant.email}</h1>
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
													<dd>${restaurant.address}</dd>
													<dt>City</dt>
													<dd>${restaurant.city}</dd>
													<dt>Phone
													</dd>
													<dd>${restaurant.phone}
													</dt>
													<dt>Open hours
													</dd>
													<dd>${restaurant.open_hours}
													</dt>
												</dl>
												<div class="text-right">
													<div class="btn-group">
														<button class="btn btn-white btn-sm" data-toggle="modal"
															data-target="#staffListModal">
															<i class="fa fa-star"></i> Staff list
														</button>
														<button class="btn btn-white btn-sm">
															<i class="fa fa-envelope"></i> Contact with author
														</button>
													</div>
												</div>


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
										<h5>
											Reservating <small>Here you can select date and time
												and see is there any available tables in this restaurant for
												you.</small>
										</h5>
									</div>

									<div class="ibox-content">
										<div class="row">
											<form action="${restaurant.id}/check" method="POST">
												<div class="col-md-4">
													<div class="text-center">
														<div class="m-r-md inline">
															<input type="text" value="0" name="guestNum"
																class="dial m-r-sm" data-fgColor="#1AB394"
																data-width="85" data-height="85" />
														</div>
													</div>
												</div>

												<div class="col-md-2">

													<p class="font-bold">Date:</p>

													<input class="form-control" name="res_date" type="date"
														value="" name="demo1">
												</div>
												<div class="col-md-2">
													<p class="font-bold">From:</p>
													<input class="touchspin1" name="res_from" type="text"
														value="16" name="demo2" id="typedFrom">
												</div>
												<div class="col-md-2">

													<p class="font-bold">To:</p>
													<input class="touchspin1" name="res_to" type="text"
														value="17" name="demo3" id="typedTo">
												</div>
												<div class="col-md-1">
													<button type="submit" class="btn btn-primary"
														style="margin-top: 27px;"check">Check</button>
												</div>
											</form>
										</div>

									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<c:forEach var="table" items="${tables}">
								<%
									int check = 0;
												pageContext.setAttribute("check", check);
								%>
								<%
									int eql = 0;
												pageContext.setAttribute("eql", eql);
								%>
								<%
									int timeFrom = 0;
												pageContext.setAttribute("timeFrom", timeFrom);
								%>
								<%
									int timeTo = 0;
												pageContext.setAttribute("timeTo", timeTo);
								%>

								<c:forEach var="checked" items="${checkedTables}">
									<c:choose>
										<c:when test="${checked.table.restaurant_id == restaurant.id}">

											<c:choose>
												<c:when test="${table.id == checked.table.id}">
													<%
														check = 1;
																						pageContext.setAttribute("check", check);
													%>
													<%
														eql = 1;
																						pageContext.setAttribute("eql", eql);
													%>
													<c:set var="from" value="${checked.reserved_from}" />
													<c:set var="to" value="${checked.reserved_to}" />
													<%
														int timeFrom1 = (Integer) pageContext.getAttribute("from");
																						int timeTo1 = (Integer) pageContext.getAttribute("to");
																						pageContext.setAttribute("timeFrom1", timeFrom1);
																						pageContext.setAttribute("timeTo1", timeTo1);
													%>

												</c:when>
											</c:choose>
										</c:when>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${eql == 1}">
										<div
											style="width: 100px; height: 100px; border-radius: 50px; background: #fff; border: 1px solid #ccc; margin-right: 10px; float: left">
											<p style="text-align: center; margin-top: 40px">
												<i>Unavailable</i> <i><p
														style="text-align: center; color: red; margin-top: -10px">${timeFrom1}h
														- ${timeTo1}h</p></i>
											</p>
										</div>

									</c:when>
									<c:otherwise>
										<div style="float: left; margin-right: 10px; z-index: -1">
											<div class="" style="width: 102px;">
												<input type="text" value="${table.guest_num}"
													name="guestNum" class="dial m-r-sm" data-fgColor="#1AB394"
													data-width="100" data-height="100" disabled />
												<button class="btn btn-primary resButtons" type="submit"
													style="margin-top: 5px;" id="reserveButton${table.id}"
													data-toggle="modal" data-target="#reserve${table.id}">Reserve</button>
											</div>
										</div>

									</c:otherwise>
								</c:choose>
								<!-- Reservating dialog -->
								<div class="modal fade reserveDiv" role="dialog"
									id="reserve${table.id}">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header" style="">
												<button type="button" class="close closeButt"
													id="closeButton${table.id}" data-dismiss="modal">&times;</button>
												<h4 class="modal-title">Table reservating</h4>
											</div>
											<div class="text-center p-lg h-150">

												<h3>Dialog will disappear after 1 minute of your inactivity</h3>
												<i class="fa fa-hand-o-up fa-4x"></i>

											</div>
											<div class="wrapper wrapper-content animated fadeIn">
												<div class="row">
													<div class="col-lg-12">
														<div class="tabs-container">
															<ul class="nav nav-tabs">
																<li class="active"><a data-toggle="tab"
																	href="#tab-3${table.id}"> <i class="fa fa-clock-o"></i></a></li>
																<li class=""><a data-toggle="tab"
																	href="#tab-4${table.id}"><i class="fa fa-desktop"></i></a></li>
																<li class=""><a data-toggle="tab"
																	href="#tab-5${table.id}"><i class="fa fa-database"></i></a></li>
															</ul>
															<div class="tab-content">
																<div id="tab-3${table.id}" class="tab-pane active">
																	<div class="panel-body">
																		<div class="col-md-6">
																			<p class="font-bold">From:</p>
																			<input class="touchspin1" name="res_from" type="text"
																				value="16" name="demo2" id="fromInput${table.id}">
																		</div>
																		<div class="col-md-6">

																			<p class="font-bold">To:</p>
																			<input class="touchspin1" name="res_to" type="text"
																				value="17" name="demo3" id="toInput${table.id}">
																		</div>
																	</div>
																</div>
																<div id="tab-4${table.id}" class="tab-pane">
																	<div class="panel-body">

																		<div class="col-md-12">
																			<div class="chat-users" style="max-height: 300px">
																				<div class="users-list">
																					<c:forEach var="friend"
																						items="${logedUser.starter_friend}">
																						<div class="chat-user" style="border-bottom: none">
																							<img class="chat-avatar"
																								src="/springmvc/resources/img/a1.jpg" alt="">
																							<div class="chat-user-name">
																								<a href="#">${friend.firstName}
																									${friend.lastName}</a>
																							</div>
																							<div class="checkbox checkbox-default pull-right"
																								style="margin-top: -25px; margin-right: -10px">
																								<input id="checkbox6" type="checkbox"> <label
																									for="checkbox6"></label>
																							</div>
																						</div>

																					</c:forEach>
																				</div>

																			</div>
																		</div>
																	</div>
																</div>
																<div id="tab-5${table.id}" class="tab-pane">
																	<div class="panel-body">
																		<strong>Donec quam felis</strong> ${table.id} c
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

							</c:forEach>
						</div>
					</div>


				</c:when>
			</c:choose>

			<!-- Manager central part -->
			<c:choose>
				<c:when test="${logedUser.role.roleName == 'Manager'}">

				</c:when>
			</c:choose>
			
			<c:choose>
				<c:when test="${logedUser.role.roleName == 'Bidder'}">

					<c:forEach var="grocary" items="${grocList}">

						<h3 style="margin-top: 15px;">Offer from ${grocary.GLfrom} to
							${grocary.GLto}</h3>
						<div style="height: 214px;">
							<div
								style="width: 426px; height: 210px; overflow-y: scroll; float: left;">
								<table id="customers">
									<tr>
										<th>Name</th>
										<th>Quantity</th>
										<th>Type</th>
									</tr>
									<c:forEach var="item" items="${ponude}">
										<c:if test="${grocary.id == item.grocaryList.id}">
											<tr>
												<td>${item.fooditem.name}</td>
												<td>${item.quantity}</td>
												<td>${item.fooditem.type}</td>
											</tr>
										</c:if>

									</c:forEach>
								</table>
							</div>
							<div
								style="margin-top: 10px;">
							<button data-toggle="modal"
								data-target="#openMenuRest1${grocary.id}"
								class="btn btn-primary btn-md" style="margin-left: 14px">Leave
								bid</button>
							</br>
							<button data-toggle="modal" data-target="#ponudaZa${grocary.id}"
								class="btn btn-primary btn-md"
								style="margin-top: 10px; margin-left: 14px">Show my
								offer</button>
							<c:forEach  var="offers" items="${listaPonuda}">
								<c:if test="${grocary.id==offers.grocaryList.id}">
									<c:if test="${logedUser.id==offers.user.id}">
										<c:choose>
										  <c:when test="${offers.accepted==0}">
										    <p style="margin-top: 10px; margin-left: 440px;color:red">  Close</p>
										  </c:when>
										  <c:when test="${offers.accepted==1}">
										    <p style="margin-top: 10px; margin-left: 440px;color:green"> Accepted</p>
										  </c:when>
										   <c:when test="${offers.accepted==2}">
										    <p style="margin-top: 10px; margin-left: 440px">    Waiting to
									answer...</p>
										  </c:when>
										  <c:otherwise>
										    <p style="margin-top: 10px; margin-left: 440px">No offer</p>
										  </c:otherwise>
										</c:choose>
									</c:if>
								</c:if>
							</c:forEach>
							</div>
						</div>
						</br>



						<div id="openMenuRest1${grocary.id}" class="modal fade"
							role="dialog">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">&times;</button>
										<h4 class="modal-title">Offer from ${grocary.GLfrom} to
							${grocary.GLto}</h4>
									</div>

									<h3 align="center">Create offer</h3>
									<form action="${restaurant.id}/createOffer" method="POST">
										<input class="form-control" name="price" type="text"
											placeholder="Price"
											style="width: 300px; height: 45px; margin: auto auto; margin-top: 15px;">
									 	<select class="form-control" name="warranty"
											style="width: 300px; height: 45px; margin: auto auto; margin-top: 15px;"
											 value=1>
											<option value="">Warranty</option>
											<option value=50>50 %</option>
											<option value=60>60 %</option>
											<option value=70>70 %</option>
											<option value=80>80 %</option>
											<option value=90>90 %</option>
											<option value=100>100 %</option>
										</select> 
										
										
									<input class="form-control" name="deadline" type="date"
											 required
											style="width: 300px; height: 45px; margin: auto auto; margin-top: 15px;">
										<input type="hidden" name="groc_id" value="${grocary.id}">
										<div class="modal-footer" style="margin-top: 115px;">
											<button type="submit" class="btn btn-success"
												style="background: #1ab394; border: 1px solid #1ab394">Create</button>
											<button type="button" class="btn btn-default"
												data-dismiss="modal">Close</button>
										</div>
									</form>
								</div>
							</div>
						</div>
						<!-- End adding items-->
						
						<!-- Proveri ponudu -->
						<c:forEach  var="offer" items="${listaPonuda}">
							<c:if test="${grocary.id==offer.grocaryList.id}">
								<c:if test="${logedUser.id==offer.user.id}">
									<div id="ponudaZa${grocary.id}" class="modal fade"
										role="dialog">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal">&times;</button>
													<h4 class="modal-title">My offer</h4>
												</div>
			
												<form action="${restaurant.id}/changeOffer" method="POST">
													<input class="form-control" name="price" type="text" id="price" placeholder="Price" required
															style="width: 300px; height: 45px; margin: auto auto; margin-top: 15px;" value="${offer.price}">
															
													<select class="form-control warr${grocary.id}" name="warranty"
															style="width: 300px; height: 45px; margin: auto auto; margin-top: 15px;"
															>
																<option value="50">50 %</option>
																<option value="60">60 %</option>
																<option value="70">70 %</option>
																<option value="80">80 %</option>
																<option value="90">90 %</option>
																<option value="100">100 %</option>
													</select> 
									
													<input class="form-control" name="deadline" type="date" id="price" placeholder="Price" required
															style="width: 300px; height: 45px; margin: auto auto; margin-top: 15px;" value="${offer.deadline}">
															
													<input type="hidden" name="grocaryId" value="${grocary.id}">
													<input type="hidden" name="accepted" value="${offer.accepted}">
													<input type="hidden" name="id" value="${offer.id}">
													
														<div class="modal-footer" style="margin-top: 115px;">
															<button type="submit" class="btn btn-success"
																style="background: #1ab394; border: 1px solid #1ab394">Change</button>
															<button type="button" class="btn btn-default"
																data-dismiss="modal">Close</button>
														</div>
												</form>
												<script type="text/javascript">
													$(".warr${grocary.id} > option").each(function() {
														text1 = "${offer.warranty}";
													   if (text1==this.value){
														   this.selected = true;
													   }
													});
												</script>
											</div>
										</div>
									</div>
								</c:if> 
							</c:if>
						</c:forEach>
					</c:forEach>

					<div id="bidderPassword" class="modal fade"
							role="dialog">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">&times;</button>
										<h4 class="modal-title">Password</h4>
									</div>

									<form action="bidderPasswordChange" method="POST">
										<input class="form-control" name="oldPass" type="password"
											placeholder="Old password" required
											style="width: 300px; height: 45px; margin: auto auto; margin-top: 15px;">
										<input class="form-control" name="newPass1" type="password"
											placeholder="New passwod" required
											style="width: 300px; height: 45px; margin: auto auto; margin-top: 15px;">
										<input class="form-control" name="newPass2" type="password"
											placeholder="Repeat new passwod" required
											style="width: 300px; height: 45px; margin: auto auto; margin-top: 15px;">
				
										<div class="modal-footer" style="margin-top: 115px;">
											<button type="submit" class="btn btn-success"
												style="background: #1ab394; border: 1px solid #1ab394">Update</button>
											<button type="button" class="btn btn-default"
												data-dismiss="modal">Close</button>
										</div>
									</form>
								</div>
							</div>
						</div>
						
						<div id="bidderProfilUp" class="modal fade"
								role="dialog">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title">Password</h4>
										</div>
	
										<form action="bidderProfUpdate" method="POST">
											<input class="form-control" name="fName" type="text"
												placeholder="First name" value="${logedUser.firstName} " required
												style="width: 300px; height: 45px; margin: auto auto; margin-top: 15px;">
											<input class="form-control" name="lName" type="text"
												placeholder="Last name" value="${logedUser.lastName} " required
												style="width: 300px; height: 45px; margin: auto auto; margin-top: 15px;">
											<input class="form-control" name="NewMail" type="email" value="${logedUser.email}"
												placeholder="Email" required
												style="width: 300px; height: 45px; margin: auto auto; margin-top: 15px;">
					
											<div class="modal-footer" style="margin-top: 115px;">
												<button type="submit" class="btn btn-success"
													style="background: #1ab394; border: 1px solid #1ab394">Update</button>
												<button type="button" class="btn btn-default"
													data-dismiss="modal">Close</button>
											</div>
										</form>
									</div>
								</div>
							</div>

				</c:when>
			</c:choose>
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
	<!-- TouchSpin -->
	<script src="../resources/js/app.js"></script>

	<script type="text/javascript">
		$(".dial").knob();

		var el = document.querySelector('#el');

		// current rating, or initial rating
		var currentRating = 0;

		// max rating, i.e. number of stars you want
		var maxRating = 5;

		// callback to run after setting the rating
		var callback = function(rating) {
			alert(rating);
		};
	</script>
	<!-- Promena centralnog diva -->
	<script type="text/javascript">
		$(".touchspin1").TouchSpin({
			verticalbuttons : true,
			buttondown_class : 'btn btn-white',
			buttonup_class : 'btn btn-white'
		});

		$(function() {
			$("#example_id").ionRangeSlider({
				min : 0,
				max : 24,
				type : 'double',
				postfix : "h",
				prettify : false,
				hasGrid : true
			});
			$("#example_id").click(function() {
				alert("aa");
			});

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

	<!-- Sparkline demo data  -->
	<script src="../springmvc/resources/js/demo/sparkline-demo.js"></script>
	<script type="text/javascript" src="../springmvc/resources/js/app.js"></script>
	<script type="text/javascript">
		
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