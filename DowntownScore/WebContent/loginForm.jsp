<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>로그인</title>
	<meta name="author" content="Luka Cvrk (www.solucija.com)" />
	<meta name="Robots" content="index,follow" />
	<meta name="description" content="Description" />
	<meta name="keywords" content="key, words" />
	<link href="css/content.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>
	<div id="content">
		
		<% request.setAttribute("current", "login"); %>
		<%@ include file="./topMenu.jsp" %>
	
		<div id="pitch">
			<form action="login.jb" method="post">
			<c:if test="${ errors.idOrPwNotMatch }">
			아이디와 암호가 일치하지 않습니다.
			</c:if>
			<p>
				<b>ID</b>
				<input type="text" name="member_id" value="${ param.member_id }"/>
				<c:if test="${ errors.member_id }">아이디를 입력하세요.</c:if>
			</p>
			<p>
				<b>PASSWORD</b>
				<input type="password" name="member_pwd" />
				<c:if test="${ errors.member_pwd }">암호를 입력하세요.</c:if>
			</p>
			<input type="submit" value="로그인" />
			</form>
			<a href="join.jb">회원가입</a>&nbsp;<a>비밀번호찾기</a>
		</div>
		
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