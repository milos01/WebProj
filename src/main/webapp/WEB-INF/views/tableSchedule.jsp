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
<link href="../springmvc/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="../springmvc/resources/font-awesome/css/font-awesome.css"
	rel="stylesheet">

<link rel="stylesheet" type="text/css" href="../springmvc/resources/gridster/assets/css/jquery.gridster.css">
<link rel="stylesheet" type="text/css" href="../springmvc/resources/gridster/assets/css/styles.css">

<link href="../springmvc/resources/css/animate.css" rel="stylesheet">
<link href="../springmvc/resources/css/style.css" rel="stylesheet">
<link
	href="//netdna.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css"
	rel="stylesheet">
<link href="../springmvc/resources/css/plugins/nouslider/jquery.nouislider.css"
	rel="stylesheet">
<link href="../springmvc/resources/css/plugins/toastr/toastr.min.css"
	rel="stylesheet">
<link
	href="../springmvc/resources/css/plugins/touchspin/jquery.bootstrap-touchspin.min.css"
	rel="stylesheet">
<link href="../springmvc/resources/css/plugins/ionRangeSlider/ion.rangeSlider.css"
	rel="stylesheet">
<link
	href="../springmvc/resources/css/plugins/ionRangeSlider/ion.rangeSlider.skinFlat.css"
	rel="stylesheet">
<link
	href="../springmvc/resources/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css"
	rel="stylesheet">
	<script src="../springmvc/resources/js/jquery-2.1.1.js"></script>
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
</head>
<body>
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
				<!-- Waiter side menu -->
				<c:choose>
					<c:when test="${logedUser.role.roleName == 'Waiter'}">
						<li><a href="home.html"><i class="fa fa-user-plus"></i>
								<span class="nav-label">Home</span></a></li>
						<li><a href="#"><i class="fa fa-user-plus"></i>
								<span class="nav-label" data-toggle="modal" data-target="#showCalendarShiftsWaiter">Shift schedule</span></a></li>
						<li><a href="layouts.html"><i class="fa fa-user-plus"></i>
								<span class="nav-label">Order's inbox</span></a></li>
						<li><a href="tableSchedule"><i class="fa fa-user-plus"></i>
								<span class="nav-label">Table schedule</span></a></li>
						<li><a href="#"><i class="fa fa-wrench"></i> <span
								class="nav-label" data-toggle="modal"
								data-target="#updateUserModal">Update profile</span></a></li>
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
				</div>
				<ul class="nav navbar-top-links navbar-right">
					<li><a href="logout"> <i class="fa fa-sign-out"></i> Log
							out
					</a></li>
				</ul>

				</nav>
			</div>
						<!-- Waiter central part -->
			<c:choose>
				<c:when test="${logedUser.role.roleName == 'Waiter'}">
					<div class="tab-panels"
						style="height: 100%; background-color: #F3F3F4">
						<div style="background-color: #F3F3F4; height: 100%" id="panel1"
							class="panel active">
							<h1 style="margin-left: 10px;">Table schedule</h1>
							<div class="wrapper wrapper-content animated fadeInRight">
								<div class="row">
									<div class="col-lg-12">

										<div class="ibox product-detail">
											<div class="ibox-content">

												<div class="row">
													<c:if test="${empty workingTables}">
														<h2 style="margin-left: 10px;">Not your working time yet</h2>
													</c:if>
													<c:if test="${not empty workingTables}">
													<section class="demo">
														<div class="gridster" style="width: 100%">
															<ul class="ull">
																<% int pom = 0;
																pageContext.setAttribute("pom", pom);%>
																	<c:forEach var="position" items="${tables}">
																		<c:forEach var="workingPosition" items="${workingTables}">
																			<c:if test="${position.id==workingPosition.id}">
																				<% pom = 1;
																				pageContext.setAttribute("pom", pom);%>
																				<li style="border-radius:50%;border-color:#1ab394;background-color:#1ab394" title="Guest number: ${position.guest_num} Reon: ${position.reon_id.reon_num}" id="${position.tableposition.id}" class="sto" data-row="${position.tableposition.row }" data-col="${position.tableposition.col}" data-sizex="${position.tableposition.size_x}" data-sizey="${position.tableposition.size_y}"><a href="<c:url value="deleteTable/${position.tableposition.id}/${restoran.id}"/>"><button class="btn btn-primary btn-md" style="border-color:#F33A59;background-color:#F33A59">Delete</button> </a></li>	
																			</c:if>								
																		</c:forEach>
																		<c:if test="${pom == 0}">
																			<li style="border-radius:50%" title="Guest number: ${position.guest_num} Reon: ${position.reon_id.reon_num}" id="${position.tableposition.id}" class="sto" data-row="${position.tableposition.row }" data-col="${position.tableposition.col}" data-sizex="${position.tableposition.size_x}" data-sizey="${position.tableposition.size_y}"><a href="<c:url value="deleteTable/${position.tableposition.id}/${restoran.id}"/>"><button class="btn btn-primary btn-md" style="border-color:#F33A59;background-color:#F33A59">Delete</button> </a></li>	
																		</c:if>
																		<c:if test="${pom == 1}">
																			<% pom = 0;
																			pageContext.setAttribute("pom", pom);%>
																		</c:if>																		
																	</c:forEach>
															</ul>
														</div>
													</section>
													</c:if>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</c:when>
			</c:choose>
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
	<script src="../springmvc/resources/js/plugins/jquery-ui/jquery-ui.min.js"></script>

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
	<script type="text/javascript" src="../springmvc/resources/gridster/assets/jquery.gridster.js"></script>
	<script type="text/javascript">
			var gridster;

			$(document).ready(function() {
				gridtster = $(".gridster > .ull").gridster({
					widget_margins: [10, 10],
					widget_base_dimensions: [110, 110],
					min_cols: 10,
					serialize_params: function($w, wgd) {
				            return {
				            	id: $($w).attr('id'),
				                col: wgd.col,
				                row: wgd.row,
				                size_x: wgd.size_x,
				                size_y: wgd.size_y
				            };
				        }
				}).data('gridster').disable();
		//		var json = JSON.stringify(gridster.serialize());
		//		alert(json);
			});
			
			function saveP(){
				gridster = $(".gridster > .ull").gridster({
			        widget_margins: [10, 10],
			        widget_base_dimensions: [110, 110],
			        min_cols: 10,
			        serialize_params: function($w, wgd) {
			            return {
			            	id: $($w).attr('id'),
			                col: wgd.col,
			                row: wgd.row,
			                size_x: wgd.size_x,
			                size_y: wgd.size_y
			            };
			        }
			    }).data('gridster');
				$.ajax({
					type: "POST",
					contentType : 'application/json',
					dataType : 'text',
					url: "savePositions",
					data: JSON.stringify(gridster.serialize()),
					success: function(data){
						if(data == "success"){
							$(".alrtTable").show();
							if ($(".alrtTable").is(":visible")) {
								$(".alrtTable").delay(1800).fadeOut(500);
								
							}
						}
					},
					error:function(){
						alert("rrr");
					}
					
				});
			}
			
			
	</script>
	<!-- Sparkline demo data  -->
	<script src="../springmvc/resources/js/demo/sparkline-demo.js"></script>
	
</body>
</html>