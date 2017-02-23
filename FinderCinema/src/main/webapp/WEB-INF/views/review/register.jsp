<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<%@include file="../include/header.jsp" %>
	
    <title>Review Register</title>
</head>

<body>
	<%@include file="../include/menu.jsp" %>
	
    <!-- Page Header -->
    <!-- Set your background image for this header on the line below. -->
    <header class="intro-header" style="width:100%; height:60px; background-image: url('/resources/img/Cinema.jpg')">
		<div class="site-heading">
		</div>
    </header>

    <!-- Main Content -->
    <div class="container">
        <div class="row">
        	<form role="form" method="post">
        	<!-- action속성이 지정되지 않으면 현재 경로를 그대로 action의 대상경로가 된다. -->
        		<div class="box-body">
	           		<div class="form-group">
	           			<label for="exampleInputEmail1">Title</label>
	           			<input type="text" name="title" class="form-control" placeholder="제목을 입력해주세요.">
	           		</div>
	           		
	           		<div class="form-group">
	           			<label for="exampleInputPassword1">Content</label>
	           			<textarea class="form-control" name="content" rows="3" placeholder="내용을 입력해주세요.">
	           			</textarea>
	           		</div>
	           		
	           		<div class="form-group">
	           			<label for="exampleInputEmail1">Writer</label>
	           			<input type="text" name="writer" class="form-control" value="${ login.member_id }" readonly>
	           		</div>
	           	</div>
           		<!-- /.box-body -->
           	
           		<div class="box-footer">
           			<button type="submit" class="btn btn-primary">등록</button>
           		</div>
           		<!-- /.box-footer -->
           	</form>
		</div>
    </div>

    <hr>

	<%@include file="../include/footer.jsp" %>
</body>

</html>

