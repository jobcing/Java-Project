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
        	<c:forEach items="${ timetable }" var="timetable">
	            <table class="table table-bordered">
	            
	            	<c:out value="${ timetable.cinemaTitle }" />
	            	
	        		<tr>
	        			<th>상영 영화</th>
	        			<th>상영 시간</th>
	        		</tr>
	        		
	        		<c:forEach begin="0" end="${ fn:length(timetable.movie) }" step="1" var="i">
	        		
	        		<tr>
	        			<td>${ timetable.movie[i] }</td>
	        			<td>
	        			<c:forEach items="${ timetable.movietimeVO[i].time }" var="time">
	        				<c:out value="${ time }" />
	        			</c:forEach>
	        			</td>
	        		</tr>
	        		
	        		</c:forEach>
	        		
	        	</table>
        	</c:forEach>
        </div>
    </div>

    <hr>

	<%@include file="../include/footer.jsp" %>
</body>

</html>

