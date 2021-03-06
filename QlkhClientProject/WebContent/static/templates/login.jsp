<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns:th="http://www.w3.org/1999/xhtml">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>Đăng nhập | Hệ thống quản lý khách hàng</title>
		<!-- Tell the browser to be responsive to screen width -->
	  	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	  	<!-- shortcut icon -->
	  	<link rel="shortcut icon" href="static/dist/img/fav.ico">
	  	<!-- Bootstrap 3.3.7 -->
	  	<link rel="stylesheet" href="static/bower_components/bootstrap/dist/css/bootstrap.min.css">
	  	<!-- Font Awesome -->
	  	<link rel="stylesheet" href="static/bower_components/font-awesome/css/font-awesome.min.css">
	  	<!-- Theme style -->
	  	<link rel="stylesheet" href="static/dist/css/AdminLTE.min.css">
	  	<!-- Page style -->
	  	<style type="text/css">
		    body {
		      overflow-y: hidden;
		      background-image: url(static/dist/img/background-learning.jpg) !important;
		      background-repeat: no-repeat !important;
  			  background-size: cover !important;
			}
		    .login-logo,.login-logo a {
		      color:#fff;
		      font-size:30px;
		    }
		    .login-box-msg {
		      font-size:20px;
		      font-weight:400
		    }
		    .login-box-body {
		      border-top: 4px solid #367fa9;
		      background-color: rgba(255,255,255,0.7);
		      -webkit-box-shadow: 0 2px 3px rgba(0,0,0,.55);
		      -moz-box-shadow: 0 2px 3px rgba(0,0,0,.55);
		      box-shadow: 0 2px 3px rgba(0,0,0,.55);
		    }
	  	</style>
	</head>
	<body class="hold-transition login-page">
		<div class="login-box">
		  <div class="login-logo">
			<a>HỆ THỐNG <BR/><b>QUẢN LÝ KHÁCH HÀNG</b></a>
		  </div>
		  <!-- /.login-logo -->
		  <div class="login-box-body">
		    <p class="login-box-msg">${ MSG }</p>
		
		    <form action="" method="post">
		      <div class="form-group has-feedback">
		        <input type="text" class="form-control" placeholder="Tên đăng nhập" name="username">
		        <span class="fa fa-envelope form-control-feedback"></span>
		      </div>
		      <div class="form-group has-feedback">
		        <input type="password" class="form-control" placeholder="Mật khẩu" name="password">
		        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
		      </div>
		      <div class="row">
		        <div class="col-xs-12">
		          <button type="submit" class="btn btn-primary btn-block btn-flat" style="font-weight:600">ĐĂNG NHẬP</button>
		        </div>
		        <!-- /.col -->
		      </div>
		    </form>
		  </div>
		  <!-- /.login-box-body -->
		</div>
		<!-- /.login-box -->
		<p style="position:fixed;left:0;bottom:0;width:100%;color:#fff;text-align:center;z-index:1;text-shadow: 1px 0px 2px #000000">&copy; <%= Calendar.getInstance().get(Calendar.YEAR) %> <a href="#" target="_blank" style="color:#fff">SOA - T2D</a></p>
		<!-- jQuery 3 -->
		<script src="static/bower_components/jquery/dist/jquery.min.js"></script>
		<!-- Bootstrap 3.3.7 -->
		<script src="static/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	</body>
</html>