<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String current = (String) request.getAttribute("current");

	if(current.equalsIgnoreCase("main")){
%>
	<h1>
		<a href="./downtown_main.jb"><span>Downtown</span>Score</a>
	</h1>

	<ul id="menu">
		<li><a href="./listNotice.jb">공지사항</a></li>
		<li><a href="./listRestaurant.jb">음식점</a></li>
		<li><a href="./listCafe.jb">카페</a></li>
		<li><a href="./listBar.jb">술집</a></li>
		<li><a href="./listRanking.jb">랭킹</a></li>
		<li><a href="#">|</a></li>
<%
		if(session == null || session.getAttribute("authUser") == null){
%>
			<li><a href="./login.jb">로그인</a></li>
<%	
		} else{
%>
			<li><a href="./logout.jb">로그아웃</a></li>
<%
		}
%>
	</ul>
<%
	} else if(current.equalsIgnoreCase("login")){
%>
	<h1>
		<a href="./downtown_main.jb"><span>Downtown</span>Score</a>
	</h1>

	<ul id="menu">
		<li><a href="./listNotice.jb">공지사항</a></li>
		<li><a href="./listRestaurant.jb">음식점</a></li>
		<li><a href="./listCafe.jb">카페</a></li>
		<li><a href="./listBar.jb">술집</a></li>
		<li><a href="./listRanking.jb">랭킹</a></li>
		<li><a href="#">|</a></li>
<%
		if(session == null || session.getAttribute("authUser") == null){
%>
			<li><a class="current" href="./login.jb">로그인</a></li>
<%	
		} else{
%>
			<li><a href="./logout.jb">로그아웃</a></li>
<%
		}
%>
	</ul>
<%
	} else if(current.equalsIgnoreCase("notice")){
%>
	<h1>
		<a href="./downtown_main.jb"><span>Downtown</span>Score</a>
	</h1>

	<ul id="menu">
		<li><a class="current" href="./listNotice.jb">공지사항</a></li>
		<li><a href="./listRestaurant.jb">음식점</a></li>
		<li><a href="./listCafe.jb">카페</a></li>
		<li><a href="./listBar.jb">술집</a></li>
		<li><a href="./listRanking.jb">랭킹</a></li>
		<li><a href="#">|</a></li>
<%
		if(session == null || session.getAttribute("authUser") == null){
%>
			<li><a class="current" href="./login.jb">로그인</a></li>
<%	
		} else{
%>
			<li><a href="./logout.jb">로그아웃</a></li>
<%
		}
%>
	</ul>
<%
	} else if(current.equalsIgnoreCase("restaurant")){
%>
	<h1>
		<a href="./downtown_main.jb"><span>Downtown</span>Score</a>
	</h1>

	<ul id="menu">
		<li><a href="./listNotice.jb">공지사항</a></li>
		<li><a class="current" href="./listRestaurant.jb">음식점</a></li>
		<li><a href="./listCafe.jb">카페</a></li>
		<li><a href="./listBar.jb">술집</a></li>
		<li><a href="./listRanking.jb">랭킹</a></li>
		<li><a href="#">|</a></li>
<%
		if(session == null || session.getAttribute("authUser") == null){
%>
			<li><a class="current" href="./login.jb">로그인</a></li>
<%	
		} else{
%>
			<li><a href="./logout.jb">로그아웃</a></li>
<%
		}
%>
	</ul>
<%	
	} else if(current.equalsIgnoreCase("cafe")){
%>
	<h1>
		<a href="./downtown_main.jb"><span>Downtown</span>Score</a>
	</h1>

	<ul id="menu">
		<li><a href="./listNotice.jb">공지사항</a></li>
		<li><a href="./listRestaurant.jb">음식점</a></li>
		<li><a class="current" href="./listCafe.jb">카페</a></li>
		<li><a href="./listBar.jb">술집</a></li>
		<li><a href="./listRanking.jb">랭킹</a></li>
		<li><a href="#">|</a></li>
<%
		if(session == null || session.getAttribute("authUser") == null){
%>
			<li><a class="current" href="./login.jb">로그인</a></li>
<%	
		} else{
%>
			<li><a href="./logout.jb">로그아웃</a></li>
<%
		}
%>
	</ul>
<%	
	} else if(current.equalsIgnoreCase("bar")){
%>
	<h1>
		<a href="./downtown_main.jb"><span>Downtown</span>Score</a>
	</h1>

	<ul id="menu">
		<li><a href="./listNotice.jb">공지사항</a></li>
		<li><a href="./listRestaurant.jb">음식점</a></li>
		<li><a href="./listCafe.jb">카페</a></li>
		<li><a class="current" href="./listBar.jb">술집</a></li>
		<li><a href="./listRanking.jb">랭킹</a></li>
		<li><a href="#">|</a></li>
<%
		if(session == null || session.getAttribute("authUser") == null){
%>
			<li><a class="current" href="./login.jb">로그인</a></li>
<%	
		} else{
%>
			<li><a href="./logout.jb">로그아웃</a></li>
<%
		}
%>
	</ul>
<%	
	} else if(current.equalsIgnoreCase("ranking")){
%>
	<h1>
		<a href="./downtown_main.jb"><span>Downtown</span>Score</a>
	</h1>

	<ul id="menu">
		<li><a href="./listNotice.jb">공지사항</a></li>
		<li><a href="./listRestaurant.jb">음식점</a></li>
		<li><a href="./listCafe.jb">카페</a></li>
		<li><a href="./listBar.jb">술집</a></li>
		<li><a class="current" href="./listRanking.jb">랭킹</a></li>
		<li><a href="#">|</a></li>
<%
		if(session == null || session.getAttribute("authUser") == null){
%>
			<li><a class="current" href="./login.jb">로그인</a></li>
<%	
		} else{
%>
			<li><a href="./logout.jb">로그아웃</a></li>
<%
		}
	}
%>
	</ul>
</body>
</html>