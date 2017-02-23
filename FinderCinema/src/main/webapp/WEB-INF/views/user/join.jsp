<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<%@include file="../include/header.jsp" %>
	
    <title>About</title>
</head>

<body>
	<%@include file="../include/menu.jsp" %>
	
    <!-- Page Header -->
    <!-- Set your background image for this header on the line below. -->
    <header class="intro-header" style="background-image: url('/resources/img/home-bg.jpg')">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                    <div class="site-heading">
                        <h1>Reviews</h1>
                        <hr class="small">
                        <span class="subheading">Movie Communication</span>
                    </div>
                </div>
            </div>
        </div>
    </header>

    <!-- Main Content -->
    <div class="container">
    	<div class="row">
    		<form action="/user/join" method="post">
    			<div class="form-group has-feedback">
					<span style="font-size: 15pt"> <b>아이디 : </b> </span>
    				<input type="text" name="member_id" class="form-control" placeholder="USER ID" />
    				<span class="glphicon glphicon-envelope form-control-feedback"></span>
    			</div>
    			<div class="form-group has-feedback">
					<span style="font-size: 15pt"> <b>비밀번호 : </b> </span>
    				<input type="password" name="member_pw" class="form-control" placeholder="PASSWORD" />
    				<span class="glphicon glphicon-lock form-control-feedback"></span>
    			</div>
    			<div class="form-group has-feedback">
					<span style="font-size: 15pt"> <b>비밀번호 확인 : </b> </span>
    				<input type="password" name="member_pw" class="form-control" placeholder="PASSWORD" />
    				<span class="glphicon glphicon-lock form-control-feedback"></span>
    			</div>
    			<div class="form-group has-feedback">
					<span style="font-size: 15pt"> <b>닉네임 : </b> </span>
    				<input type="text" name="member_id" class="form-control" placeholder="USER ID" />
    				<span class="glphicon glphicon-envelope form-control-feedback"></span>
    			</div>
    			<div class="row">
    				<button type="submit" class="btn btn-primary btn-block btn-flat">Sign Up</button>
    			</div>
    		</form>
    	</div>
    	<!-- /.row -->

        <div class="row">

        </div>
        <!-- /.row -->
    </div>
    <!-- /.container -->


    <hr>

	<%@include file="../include/footer.jsp" %>
	
</body>

</html>

