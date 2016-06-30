<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%-- 상단 메뉴 바 --%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<body>

	<%-- 로그아웃 확인 자바스크립트 --%>
	<script type="text/javascript">
		function logoutConfirm(){
			if(confirm("로그아웃하시겠습니까 ?")){
				location.href = "logout.jb";
				
				return true;
			} else {
				return false;
			}
		} // logoutConfirm()
	</script>
	
<%
	// request변수 current에 저장되있는 인자를 받아온다.
	// 현재 어떤 탭에 있는지 알기위해서
	String current = (String) request.getAttribute("current");

	// 현재 메인 관련 페이지라면
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
		// 세션이 존재하지 않거나 로그인된 사용자가 없다면 메뉴바가 로그인으로 표시
		if(session == null || session.getAttribute("authUser") == null){
%>
			<li><a href="./login.jb">로그인</a></li>
<%	
		} else{
%>
			
			<li><a href="#" onclick="logoutConfirm()">로그아웃</a></li>
<%
		}
%>
	</ul>
<%
	// 현재 로그인 관련 페이지라면
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
			<li><a href="#" onclick="logoutConfirm()">로그아웃</a></li>
<%
		}
%>
	</ul>
<%
	// 현재 공지사항 관련 페이지라면
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
			<li><a href="#" onclick="logoutConfirm()">로그아웃</a></li>
<%
		}
%>
	</ul>
<%
	// 현재 식당 관련 페이지라면
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
			<li><a href="#" onclick="logoutConfirm()">로그아웃</a></li>
<%
		}
%>
	</ul>
<%	
	// 현재 카페 관련 페이지라면
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
			<li><a href="#" onclick="logoutConfirm()">로그아웃</a></li>
<%
		}
%>
	</ul>
<%	
	// 현재 술집 관련 페이지라면
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
			<li><a href="#" onclick="logoutConfirm()">로그아웃</a></li>
<%
		}
%>
	</ul>
<%	
	// 현재 랭킹 관련 페이지라면
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
			<li><a href="#" onclick="logoutConfirm()">로그아웃</a></li>
<%
		}
	}
%>
	</ul>
</body>

</html>