<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
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
	           			<label for="exampleInputEmail1">BNO</label>
	           			<input type="text" name="bno" class="form-control" value="${ boardVO.bno }"
	           				readonly="readonly">
	           		</div>
        		
	           		<div class="form-group">
	           			<label for="exampleInputEmail1">Title</label>
	           			<input type="text" name="title" class="form-control" value="${ boardVO.title }">
	           		</div>
	           		
	           		<div class="form-group">
	           			<label for="exampleInputPassword1">Content</label>
	           			<textarea class="form-control" name="content" rows="3">${ boardVO.content }
	           			</textarea>
	           		</div>
	           		
	           		<div class="form-group">
	           			<label for="exampleInputEmail1">Writer</label>
	           			<input type="text" name="writer" class="form-control" value="${ boardVO.writer }">
	           		</div>
	           	</div>
           		<!-- /.box-body -->
			</form>
           	
           	<div class="box-footer">
           		<button type="submit" class="btn btn-primary">수정</button>
           		<button type="submit" class="btn btn-warning">취소</button>
           	</div>
           	<!-- /.box-footer -->
		</div>
    </div>

    <hr>

	<%@include file="../include/footer.jsp" %>
	
	<script>
		// 각 버튼을 링크
		$(document).ready(function(){
			var formObj = $("form[role='form']");
			
			console.log(formObj);
			
			$(".btn-primary").on("click", function(){
				formObj.submit();
				// POST방식으로 데이터를 수집하고 전달
			});
			
			$(".btn-warning").on("click", function(){
				self.location = "/review/listAll";
			});
		});
	
	</script>
</body>

</html>

