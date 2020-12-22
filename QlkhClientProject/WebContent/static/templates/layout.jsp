<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns:th="http://www.w3.org/1999/xhtml">
	<head>
		<meta charset="utf-8">
  		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>Trang chủ</title>
		<!-- Tell the browser to be responsive to screen width -->
  		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	  	<!-- shortcut icon -->
	  	<link rel="shortcut icon" href="static/dist/img/fav.ico">
	  	<!-- Bootstrap 3.3.7 -->
	  	<link rel="stylesheet" href="static/bower_components/bootstrap/dist/css/bootstrap.min.css">
	  	<!-- Font Awesome -->
	  	<link rel="stylesheet" href="static/bower_components/font-awesome/css/font-awesome.min.css">
	  	<!-- Select2 -->
  		<link rel="stylesheet" href="static/bower_components/select2/css/select2.min.css">
  		<!-- Admin LTE -->
	  	<link rel="stylesheet" href="static/dist/css/AdminLTE.min.css">
  		<!-- AdminLTE Skins. We have chosen the skin-blue for this starter
	        page. However, you can choose any other skin. Make sure you
	        apply the skin class to the body tag so the changes take effect. -->
	  	<link rel="stylesheet" href="static/dist/css/skins/skin-blue.min.css">
	  	<!-- Style -->
	  	<link rel="stylesheet" href="static/css/style.css">
	</head>
	<body class="hold-transition skin-blue sidebar-mini">
		<div class="wrapper">

		  <!-- Main Header -->
		  <header class="main-header">
		
		    <!-- Logo -->
		    <a href="." class="logo">
		      <!-- mini logo for sidebar mini 50x50 pixels -->
		      <span class="logo-mini"><img src="static/dist/img/vnpt-logo-white.png"></span>
		      <!-- logo for regular state and mobile devices -->
		      <span class="logo-lg"><img src="static/dist/img/vnpt-logo-white.png"/><b>QLKH</b><sup>&trade;</sup></span>
		    </a>
		
		    <!-- Header Navbar -->
		    <nav class="navbar navbar-static-top" role="navigation">
		      <!-- Sidebar toggle button-->
		      <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
		        <span class="sr-only">Toggle navigation</span>
		      </a>
		      <%-- <ul class="nav navbar-nav" th:if="${MODEL.nguoiDung.tinh!=null}">
		        <li>
		          <a style="text-transform:uppercase;font-weight:bold"><span th:text="'TỈNH '+${MODEL.nguoiDung.tinh.tenTinh}"></span></a>
		        </li>
		      </ul> --%>
		      <!--<div class="collapse navbar-collapse">
		        <ul class="nav navbar-nav">
		          <li>
		            <a href="">Link</a>
		          </li>
		        </ul>
		      </div>-->
		      <!-- Navbar Right Menu -->
		      <div class="navbar-custom-menu">
		        <ul class="nav navbar-nav">
		          <!-- User Account Menu -->
		          <li class="dropdown user user-menu">
		            <!-- Menu Toggle Button -->
		            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
		              <!-- The user image in the navbar-->
		              <img src="static/dist/img/user-male-160.jpg" class="user-image" alt="User Image">
		              <!-- hidden-xs hides the username on small devices so only the image appears. -->
		              <span class="hidden-xs">Huynh Thanh Du</span>
		            </a>
		            <ul class="dropdown-menu">
		              <!-- The user image in the menu -->
		              <li class="user-header">
		                <img src="static/dist/img/user-male-160.jpg" class="img-circle" alt="User Image">
		                <p>
		                  <span>Huynh Thanh Du</span>
		                  <small>Huynh Thanh Du</small>
		                </p>
		
		              </li>
		              <!-- Menu Footer-->
		              <li class="user-footer">
		                <div class="pull-left">
		                  <a href="/thong-tin-ca-nhan" class="btn btn-default btn-flat">Tài khoản</a>
		                </div>
		                <div class="pull-right">
		                  <a href="/logout" class="btn btn-default btn-flat">Đăng xuất</a>
		                </div>
		              </li>
		            </ul>
		          </li>
		          <!-- Control Sidebar Toggle Button -->
		          <!--<li>
		            <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
		          </li>-->
		        </ul>
		      </div>
		    </nav>
		  </header>
		  <!-- Left side column. contains the logo and sidebar -->
		  <aside class="main-sidebar">
		
		    <!-- sidebar: style can be found in sidebar.less -->
		    <section class="sidebar">
		
		      <!-- Sidebar user panel (optional) -->
		      <!--<div class="user-panel">
		        <div class="pull-left image">
		          <img th:src="${MODEL.nguoiDung.nu?'/dist/img/user-female-160.jpg':'/dist/img/user-male-160.jpg'}" class="img-circle" alt="User Image">
		        </div>
		        <div class="pull-left info">
		          <p th:text="${MODEL.nguoiDung.hoTen}"></p>
		          &lt;!&ndash; Status &ndash;&gt;
		          <a th:text="'@'+${MODEL.nguoiDung.maNguoiDung}"></a>
		        </div>
		      </div>-->
		
		      <!-- Sidebar Menu -->
		      <ul class="sidebar-menu tree" data-widget="tree">
		        <li class="header">CHỨC NĂNG</li>
		        <!-- Optionally, you can add icons to the links -->
		        <li class="active"><a href="#"><i class="fa fa-link"></i> <span>Link</span></a></li>
		        <li><a href="#"><i class="fa fa-link"></i> <span>Another Link</span></a></li>
		      </ul>
		      <!-- /.sidebar-menu -->
		    </section>
		    <!-- /.sidebar -->
		  </aside>
		
		  <!-- Content Wrapper. Contains page content -->
		  <div class="content-wrapper">
		    <!-- Content Header (Page header) -->
		    <section class="content-header">
		      <h1>H1 Page Title</h1>
		    </section>
		
		    <!-- Main content -->
		    <section class="content container-fluid">
		
		      <!-- Include page content here -->
		
		    </section>
		    <!-- /.content -->
		  </div>
		  <!-- /.content-wrapper -->
		
		  <!-- Main Footer -->
		  <footer class="main-footer">
		    <!-- To the right -->
		    <div class="pull-right hidden-xs">
		      Phiên bản <span>1.0.0</span>
		    </div>
		    <!-- Default to the left -->
		    <span><span>&copy; <%= Calendar.getInstance().get(Calendar.YEAR) %></span> <a href="#" target="_blank">SOA - T2D</a></span>
		  </footer>
		
		</div>
		<!-- ./wrapper -->
		
		<!-- REQUIRED JS SCRIPTS -->
		
		<!-- jQuery 3 -->
		<script src="static/bower_components/jquery/dist/jquery.min.js"></script>
		<!-- Bootstrap 3.3.7 -->
		<script src="static/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
		<!-- Select2 -->
		<script src="static/bower_components/select2/js/select2.min.js"></script>
		<!-- AdminLTE App -->
		<script src="static/dist/js/adminlte.min.js"></script>
		<!-- Script -->
		<script src="static/js/script.js"></script>
	</body>
</html>