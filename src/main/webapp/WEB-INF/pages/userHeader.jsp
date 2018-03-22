<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    
<head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="A fully featured admin theme for Temple">
        <meta name="author" content="CHARVIKENT">

		<!-- App favicon -->
        <!-- <link rel="shortcut icon" href="assets/images/favicon.ico"> -->
        <link rel="shortcut icon" href="assets/images/fav.png">
        
        <!-- App title -->
        <title>SRI SUBRAHMANYESWARA SWAMY TEMPLE</title>
        
        <!-- Sweet Alert -->
        <link href="assets/plugins/bootstrap-sweetalert/sweet-alert.css" rel="stylesheet" type="text/css">
        
<!--         <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" /> -->
<!--         <link href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css" rel="stylesheet" type="text/css" /> -->

        <!--Morris Chart CSS -->
<!-- 		<link rel="stylesheet" href="assets/plugins/morris/morris.css"> -->

        <!-- App css -->
        <link href="assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="assets/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <link href="assets/css/animate.min.css" rel="stylesheet" type="text/css" />
        <link href="assets/css/core.css" rel="stylesheet" type="text/css" />
        <link href="assets/css/components.css" rel="stylesheet" type="text/css" />
        <link href="assets/css/icons.css" rel="stylesheet" type="text/css" />
        <link href="assets/css/pages.css" rel="stylesheet" type="text/css" />
        <link href="assets/css/menu.css" rel="stylesheet" type="text/css" />
        <link href="assets/css/responsive.css" rel="stylesheet" type="text/css" />
        <link href="assets/plugins/datatables/dataTables.colVis.css" rel="stylesheet" type="text/css" />
        
        <!-- DataTable css -->
        <link href="assets/plugins/datatables/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>
        <link href="assets/plugins/datatables/buttons.bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="assets/plugins/datatables/fixedHeader.bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="assets/plugins/datatables/responsive.bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="assets/plugins/datatables/scroller.bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="assets/plugins/datatables/dataTables.colVis.css" rel="stylesheet" type="text/css"/>
        <link href="assets/plugins/datatables/dataTables.bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="assets/plugins/datatables/fixedColumns.dataTables.min.css" rel="stylesheet" type="text/css"/>
        

        <!-- HTML5 Shiv and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->

        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/js/modernizr.min.js"></script>
<style>
.alert{
text-align: -webkit-center;
position: fixed;
top: 0;left: 0;right: 0;
margin: 1px auto;
max-width: 420px;
text-align: center;
}
.alert-success, .alert-warning, .alert-danger{color: white !important;}
.alert-success{background-color: #4CAF50 !important;}
.alert-warning{background-color: #ff6600 !important;}
.alert-danger{background-color: #d43f3a !important;}
th{text-align: center;}
.your-class::-webkit-input-placeholder {color: red !important;}
/* .your-class::-moz-input-placeholder {color: red !important;} */

.default-class::-webkit-input-placeholder {color: red !important;}
/* .default-class::-moz-input-placeholder {color: red !important;} */


/* .multiselect-container{display: block;} */
span.has-error, #already_exist, .subjects_error
{
  font-weight:normal;
  border-color: #e73d4a;
  color:red;
  margin:0px;
  display: block !important;
  position: absolute;
}
@media (max-width:660px)
{
/* 	.dt-buttons{padding-right: 3em;} */
	.dataTables_length{text-align: center;}
	.dataTables_filter{text-align: center;}
	.dataTables_info{text-align: center;}
	.dataTables_paginate{text-align: center;}
	.navbar-custom{height: 0px;}
}
</style>

<!-- <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script> -->


    </head>


    <body>
    <%
				 	HttpSession sess = request.getSession(false);
					 
					if (sess.getAttribute("cacheUserBean") == null) {
						  response.sendRedirect("Login"); 
						/* RequestDispatcher dispatcher = request.getRequestDispatcher("/Login");
						dispatcher.forward(request, response); */
					}else{
						 String testId = (String)sess.getAttribute("rolId");
						 System.out.println(testId);
						int rolid22 = Integer.parseInt(testId);
						 /* if (rolid22 != 2) {
							RequestDispatcher dispatcher = request.getRequestDispatcher("/UserHome");
							dispatcher.forward(request, response);
						}  */ 
					}
			%> 
        <!-- Navigation Bar-->
        <header id="topnav">
            <div class="topbar-main">
                <div class="container">

                    <!-- Logo container-->
                    <div class="logo">
                        <!-- Text Logo -->
                        <a href="UserHome" class="logo">Sri Subrahmanyeswara Swamy Temple</a>
                        
                        <!-- Image Logo -->
                        <!-- <a href="UserHome" class="logo">
                            <img src="assets/images/logo.png" alt="" height="30">
                        </a> -->

                    </div>
                    <!-- End Logo container-->


                    <div class="menu-extras">

                        <ul class="nav navbar-nav navbar-right pull-right">

                            <li class="dropdown navbar-c-items">
                                <a href="#" class="dropdown-toggle waves-effect waves-light profile" data-toggle="dropdown" aria-expanded="true">
                                	<i class="fa fa-user fa-2x img-circle"></i>
                                	<!-- <img src="assets/images/users/avatar-1.jpg" alt="user-img" class="img-circle"> -->
                                </a>
                                <ul class="dropdown-menu dropdown-menu-right arrow-dropdown-menu arrow-menu-right user-list notify-list">
                                    <li class="text-center">
                                        <h5>Hi, <%= session.getAttribute("name") %></h5>
                                    </li>
                                    <!-- <li><a href=""><i class="ti-user m-r-5"></i> Profile</a></li> -->
                                    <!-- <li><a href=""><i class="ti-settings m-r-5"></i> Change Password</a></li> -->
                                    <li><a href="Logout"><i class="ti-power-off m-r-5" style="color: red;"></i> Logout</a></li>
                                </ul>
                            </li>
                        </ul>
                        <div class="menu-item">
                            <!-- Mobile menu toggle-->
                            <a class="navbar-toggle">
                                <div class="lines">
                                    <span></span>
                                    <span></span>
                                    <span></span>
                                </div>
                            </a>
                            <!-- End mobile menu toggle-->
                        </div>
                    </div>
                    <!-- end menu-extras -->

                </div> <!-- end container -->
            </div>
            <!-- end topbar-main -->

            <div class="navbar-custom">
                <div class="container">
                    <div id="navigation">
                        <!-- Navigation Menu-->
                        <ul class="navigation-menu">

                            <li class="">
                                <a href="UserHome"><i class="mdi mdi-home-map-marker" style="color: red;"></i>Home</a>
                            </li>

                            <li class="">
                                <a href="UserDonor"><i class="mdi mdi-account-plus" style="color: red;"></i>Donors</a>
                            </li>

                            <!-- <li class="">
                                <a href="DonorList"><i class="glyphicon glyphicon-user" style="font-size: 12px;margin-bottom: 5px;"></i>Donors</a>
                            </li> -->

                            <li class="has-submenu">
                                <a href="javascript:void(0);"><i class="mdi mdi-book-multiple" style="color: red;"></i>Reports</a>
                                <ul class="submenu">
                                    <li><a href="UserDateWiseReport">Date Wise Reports</a></li>
                                    <li><a href="UserThidiWiseReport">Thidi Wise Reports</a></li>
                                </ul>
                            </li>
                            
                            <!-- <li class="">
                                <a href=""><i class="mdi mdi-view-dashboard"></i>Database Backup</a>
                            </li> -->
                        </ul>
                        <!-- End navigation menu -->
                    </div> <!-- end #navigation -->
                </div> <!-- end container -->
            </div> <!-- end navbar-custom -->
        </header>
        <!-- End Navigation Bar-->	
