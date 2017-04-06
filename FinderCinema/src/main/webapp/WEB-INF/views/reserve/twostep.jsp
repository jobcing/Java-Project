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
    		<!-- 사용자 조건 입력 폼 -->
    		<form action="/twostep" method="post">
    			<!-- timetable을 hidden으로 다시 POST컨트롤러로 보냄 -->
				<input type="hidden" name="prevTimetable" value="${ timetable }">
    		
				<span style="font-size: 15pt"> 원하는 영화 :  </span>
				<select name="movieTitle" style="width:150px; height:50px; font-size: 13pt">
					<c:forEach items="${ nowshowing }" var="movieTitle">
					<option value="${ movieTitle }">${ movieTitle }</option>
					</c:forEach>
				</select>
				
				&nbsp;&nbsp;&nbsp;&nbsp;
				
				<span style="font-size: 15pt"> 원하는 시간대 :  </span>
				<select name="moiveTime1" style="width:150px; height:50px; font-size: 13pt">
					<option value="08:00">08:00</option>
					<option value="09:00">09:00</option>
					<option value="10:00">10:00</option>
					<option value="11:00">11:00</option>
					<option value="12:00">12:00</option>
					<option value="13:00">13:00</option>
					<option value="14:00">14:00</option>
					<option value="15:00">15:00</option>
					<option value="16:00">16:00</option>
					<option value="17:00">17:00</option>
					<option value="18:00">18:00</option>
					<option value="19:00">19:00</option>
					<option value="20:00">20:00</option>
					<option value="21:00">21:00</option>
					<option value="22:00">22:00</option>
					<option value="23:00">23:00</option>
					<option value="24:00">24:00</option>
				</select>
				&nbsp;
				<span style="font-size: 15pt"> ~ </span>
				&nbsp;
				<select name="moiveTime2" style="width:150px; height:50px; font-size: 13pt">
					<option value="09:00">09:00</option>
					<option value="10:00">10:00</option>
					<option value="11:00">11:00</option>
					<option value="12:00">12:00</option>
					<option value="13:00">13:00</option>
					<option value="14:00">14:00</option>
					<option value="15:00">15:00</option>
					<option value="16:00">16:00</option>
					<option value="17:00">17:00</option>
					<option value="18:00">18:00</option>
					<option value="19:00">19:00</option>
					<option value="20:00">20:00</option>
					<option value="21:00">21:00</option>
					<option value="22:00">22:00</option>
					<option value="23:00">23:00</option>
					<option value="24:00">24:00</option>
					<option value="24:00">25:00</option>
				</select>
				
				<%-- 띄어쓰기 --%>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="submit" value="검색" style="height:50px; width:100px; font-size: 13pt" />
				
			</form>
    	</div>
    	
    	<br/>
    	<br/>
    	
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