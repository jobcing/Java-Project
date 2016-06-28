<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%-- 로그인 성공시 열리는 페이지 --%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>로그인 성공</title>
	<meta name="author" content="Luka Cvrk (www.solucija.com)" />
	<meta name="Robots" content="index,follow" />
	<meta name="description" content="Description" />
	<meta name="keywords" content="key, words" />
	<link href="css/content.css" rel="stylesheet" type="text/css" media="screen" />
</head>

<body>
	<div id="content">
		<%-- 현재 로그인 관련 탭에 있다는 걸 알려주기 위해 request 변수 설정 --%>
		<% request.setAttribute("current", "login"); %>
		<%-- 상단 메뉴바 불러오기 --%>
		<%@ include file="./topMenu.jsp" %>
		
		<br/><br/>
		<div id="pitch">
			<%-- Session 변수 authUser가 존재한다면 --%>
			<c:if test="${! empty authUser }">
				<%-- Session autoUser에 저장된 아이디 불러오기 --%>
				<span style="font-size: 17pt"> ${ authUser.getNickname() }님, 안녕하세요. </span>
				<br/><br/><br/>
				
				<span style="font-size: 10pt">
				<a href="logout.jb">로그아웃</a>&nbsp;|&nbsp;
				<a href="changePwd.jb">암호변경</a>
				</span>
			</c:if>
			<%-- Session 변수 authUser가 존재하지 않는다면 --%>
			<c:if test="${ empty authUser }">
				<span style="font-size: 10pt">
				<a href="join.jb">회원가입</a>&nbsp;|&nbsp;
				<a href="login.jb">로그인</a>
				</span>
			</c:if>
		</div>
		
		<%-- 화면 하단 탭 부분 --%>
		<div class="line"></div>
		
		<div id="lists">
			<ul>
				<li class="title">Consulting</li>
				<li>iaculis urna vel</li>
				<li>leo a ligula euismod</li>
				<li>natoque penatibus et magnis</li>
				<li>fermentum a eleifend non</li>
				<li>quisque vel lorem eu</li>
			</ul>
			<ul>
				<li class="title">Management</li>
				<li>aliquam erat volutpat</li>
				<li>rhoncus sit amet</li>
				<li>sociis natoque penatibus</li>
				<li>erat neque vitae</li>
			</ul>		
			<ul class="last">
				<li class="title">Estimation</li>
				<li>natoque penatibus et</li>
				<li>consectetur adipiscing elit</li>
				<li>eget nunc libero</li>
			</ul>
			<div class="x"></div>
		</div>
		
		<div id="quote">
			<p>Nunc eget nunc libero. Nunc commodo euismod massa quis vestibulum. Quisque vel lorem eu libero laoreet facilisis. Aenean plac, ligula quis placerat iaculis, mi magna luctus.</p>
			<p id="author">&not; Commodo Euismod</p>
		</div>
		
		<div id="footer">
			<p id="flinks"><a href="#">Become a fan on Facebook</a> &middot; <a href="#">Follow us on Twitter</a> &middot; <a href="#">RSS</a></p>
			<p>Copyright &copy; 2009 EXPANDING PORTFOLIO &minus; Design: Luka Cvrk, <a href="http://www.solucija.com/" title="Free CSS Templates">Solucija</a></p>
		</div>
	</div>
</body>
</html>