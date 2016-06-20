<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Order your food</title>

<link href="../../../../../../resources/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="../../../../../../resources/font-awesome/css/font-awesome.css"
	rel="stylesheet">

<link href="../../../../../../resources/css/animate.css"
	rel="stylesheet">
<link href="../../../../../../resources/css/style.css" rel="stylesheet">
<link href="../../../../../../resources/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="../../../../../../resources/font-awesome/css/font-awesome.css"
	rel="stylesheet">

<!-- FooTable -->
<link
	href="../../../../../../resources/css/plugins/footable/footable.core.css"
	rel="stylesheet">

<link href="../../../../../../resources/css/animate.css"
	rel="stylesheet">
<link href="../../../../../../resources/css/style.css" rel="stylesheet">
</head>

<body class="gray-bg">
	<c:choose>
		<c:when test="${checkUser == 0}">

			<div class="lock-word animated fadeInDown">
				<span class="first-word" style="margin-left: 75px">LOGIN</span><span>SCREEN</span>
			</div>
			<div class="middle-box text-center lockscreen animated fadeInDown">
				<div>
					<div class="m-b-md">
						<img alt="image" class="img-circle circle-border"
							src="../../../../../../resources/img/def.png" width="150px">
					</div>
					<h3>${user.firstName} ${user.lastName}</h3>
					<p>${user.firstName}, you are not currently logged. Please enter
						password to proceed ordering food.</p>
					<form class="m-t" role="form" action="loginSecond" method="POST">
						<div class="form-group">
							<input type="password" name="loginPasswordSecond" class="form-control" placeholder="******"
								required="">
							<input type="hidden" name="loginEmailSecond" value="${user.email}">
						</div>
						<button type="submit" class="btn btn-primary block full-width">Login</button>
					</form>
				</div>
			</div>

		</c:when>
		<c:otherwise>
			<div class="container">
				<div class="wrapper wrapper-content animated fadeInRight ecommerce"
					style="margin: auto auto">
					<div class="row">
						<div class="col-lg-12">

							<c:choose>

								<c:when test="${user.id != logedUser.id}">
									<div class="col-lg-6">
										<span class=""><h2>Currently logged as
												${logedUser.firstName} ${logedUser.lastName}. You need to
												<a href="logoutSecond">logout</a>.</h2></span>
									</div>
								</c:when>

							</c:choose>

							<c:choose>
								<c:when test="${user.id == logedUser.id}">
									<div class="col-lg-6">
										<h2>You have been invited to join meal with your friend
											${logedUser.firstName}. Here you can choose food that will be
											served to you.</h2>
									</div>

									<div class="ibox">
										<div class="ibox-content">
											<form action="placeOrder" method="POST">

												<table
													class="footable table table-stripped toggle-arrow-tiny"
													data-page-size="15">
													<thead>

														<tr>

															<th data-toggle="true">Product Name</th>
															<th data-hide="phone">Price</th>
															<th data-hide="all">Description</th>
															<th data-sort-ignore="true"></th>
															<th data-hide="" data-sort-ignore="true"></th>
															<th data-hide="phone" data-sort-ignore="true">Quantity</th>
															<th class="text-right" data-sort-ignore="true">Select</th>

														</tr>
													</thead>
													<tbody>
													${reservation}
														<c:forEach var="menuItem"
															items="${reservation.res_restaurant.menu.appetizer}">
															<tr>
																<td>${menuItem.name}</td>
																
																<td>$ ${menuItem.price}.00</td>
																
																<td>It is a long established fact that a reader
																	will be distracted by the readable content of a page
																	when looking at its layout. The point of using Lorem
																	Ipsum is that it has a more-or-less normal distribution
																	of letters, as opposed to using 'Content here, content
																	here', making it look like readable English.</td>
																<td></td>
																<td></td>
																<td><input type="text" name="quantity"
																	style="width: 60px"></td>
																<td class="text-right">
																	<div class="btn-group">
																		<input type="checkbox" value="" name="">
																	</div>
																</td>
															</tr>
														</c:forEach>
														<c:forEach var="menuItem"
															items="${reservation.res_restaurant.menu.mainCourse}">
															<tr>
																<td>${menuItem.name}</td>
																<td>$ ${menuItem.price}.00</td>
																<td>It is a long established fact that a reader
																	will be distracted by the readable content of a page
																	when looking at its layout. The point of using Lorem
																	Ipsum is that it has a more-or-less normal distribution
																	of letters, as opposed to using 'Content here, content
																	here', making it look like readable English.</td>
																<td></td>
																<td></td>
																<td><input type="text" name="quantity"
																	style="width: 60px"></td>
																<td class="text-right">
																	<div class="btn-group">
																		<input type="checkbox" value="${menuItem.id}"
																			name="menuItemId">
																	</div>
																</td>
															</tr>
														</c:forEach>
														<c:forEach var="menuItem"
															items="${reservation.res_restaurant.menu.desert}">
															<tr>
																<td>${menuItem.name}</td>
																<td>$ ${menuItem.price}.00</td>
																<td>It is a long established fact that a reader
																	will be distracted by the readable content of a page
																	when looking at its layout. The point of using Lorem
																	Ipsum is that it has a more-or-less normal distribution
																	of letters, as opposed to using 'Content here, content
																	here', making it look like readable English.</td>
																<td></td>
																<td></td>
																<td><input type="text" name="quantity"
																	style="width: 60px"></td>
																<td class="text-right">
																	<div class="btn-group">
																		<input type="checkbox" value="" name="">
																	</div>
																</td>
															</tr>
														</c:forEach>
													</tbody>

												</table>
												<button class="btn btn-primary" type="submit">Place
													order</button>
												<div class="btn-group pull-right">
													Ready on arrival time <input type="checkbox" value="1"
														name="OnTime">
												</div>
											</form>
										</div>
									</div>
								</c:when>
							</c:choose>
						</div>
					</div>


				</div>
			</div>
		</c:otherwise>
	</c:choose>
	<!-- Mainly scripts -->
	<script src="../../../../../../resources/js/jquery-2.1.1.js"></script>
	<script src="../../../../../../resources/js/bootstrap.min.js"></script>
	<script src="../../../../../../resources/js/bootstrap.min.js"></script>
	<script
		src="../../../../../../resources/js/plugins/metisMenu/jquery.metisMenu.js"></script>
	<script
		src="../../../../../../resources/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

	<!-- Custom and plugin javascript -->
	<script src="../../../../../../resources/js/inspinia.js"></script>
	<script src="../../../../../../resources/js/plugins/pace/pace.min.js"></script>

	<!-- FooTable -->
	<script
		src="../../../../../../resources/js/plugins/footable/footable.all.min.js"></script>

	<!-- Page-Level Scripts -->
	<script>
		$(document).ready(function() {

			$('.footable').footable();

		});
	</script>

</body>
</html>