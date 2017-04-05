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
    		
				<span style="font-size: 15pt"> 원하는 영화 :  </span>
				<select name="station" style="width:150px; height:50px; font-size: 13pt">
					<option value="영화관">영화관</option> 
				</select>
				
				<br/><br/>
				<span style="font-size: 15pt"> 원하는 시간대 :  </span>
				<input type="text" name="name" size="20" style="width:200px; height:50px; font-size: 13pt" />
				<%-- 띄어쓰기 --%>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="submit" value="검색" style="height:50px; width:100px; font-size: 13pt" />
				
			</form>
    	</div>
    	
    	<!-- 테스트 -->
    	<c:forEach items="${ nowshowing }" var="nowshowing">
    		<c:out value="${ nowshowing }" />
    	</c:forEach>
    	
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