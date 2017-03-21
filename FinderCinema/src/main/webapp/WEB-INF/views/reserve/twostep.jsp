<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<%@include file="../include/header.jsp" %>
	
    <title>Reservation</title>

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
            <table class="table table-bordered">
            
            	<c:out value="${ test.cinemaTitle }" />
            	
            	<c:out value="${fn:length(test.movie)} " />
            	
        		<!-- 게시글 상단 -->
        		<tr>
        			<th>영화관 주소</th>
        		</tr>
        		<!-- 게시글 목록 -->
        		<!--
        		<c:forEach items="${ test.movie }" var="movie">
        		
        		<tr><td>${ movie }</td></tr>
        		
        		</c:forEach>
        		
        		<c:forEach items="${ test.movietimeVO }" var="times">
					<c:forEach items="${ times.time }" var="time">
					<tr><td>${ time }</td></tr>
					</c:forEach>
        		</c:forEach>
        		-->
        		
        	</table>
        </div>
    </div>

    <hr>

	<%@include file="../include/footer.jsp" %>
</body>

</html>

