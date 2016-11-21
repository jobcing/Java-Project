<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%-- 카페 한줄평 등록 FORM --%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>평점 등록</title>
	<meta name="author" content="Luka Cvrk (www.solucija.com)" />
	<meta name="Robots" content="index,follow" />
	<meta name="description" content="Description" />
	<meta name="keywords" content="key, words" />
	<link href="css/content.css" rel="stylesheet" type="text/css" media="screen" />
</head>

<body>
	<div id="content">
		<%-- 현재 카페 관련 탭에 있다는 걸 알려주기 위해 request 변수 설정 --%>
		<% request.setAttribute("current", "cafe"); %>
		<%-- 상단 메뉴바 불러오기 --%>
		<%@ include file="./topMenu.jsp" %>
		<br/>
		
		<div id="pitch">
			<%-- 버튼을 누르면 post방식으로 인자 전달 --%>
			<form action="./writeCafe.jb" method="post">
				<span style="font-size: 12pt"> 주변역 :  </span>
				<select name="station" style="width:150px;height:50px;font-size:11pt">
					<option value="범계역" selected>범계역</option>
					<option value="홍대입구">홍대입구</option>
					<option value="강남역">강남역</option>
					<option value="수원역">수원역</option>
					<option value="명동">명동</option>
				</select>
				<br/>
				<br/>
				
				<span style="font-size: 12pt"> 평점 &nbsp;&nbsp;&nbsp;: </span>
				<select name="score" style="width:150px;height:50px;font-size:11pt">
					<option value="1">★</option>
					<option value="2">★★</option>
					<option value="3">★★★</option>
					<option value="4">★★★★</option>
					<option value="5" selected>★★★★★</option>
				</select>
				<br/>
				<br/>
				<br/>
			
				<span style="font-size: 12pt"> 상호명 : </span>
				<br/>
				<br/>
				<input type="text" name="name" size="20" style="border: 1px solid #708090; font-size: 13pt" />
				<c:if test="${ errors.name }">상호명을 입력하세요.</c:if>
				<br/>
				<br/>
			
				<span style="font-size: 12pt"> 한줄평 :  </span>
				<br/>
				<br/>
				<textarea name="comment" rows="3" cols="50" style="border: 1px solid #708090; font-size: 13pt">${ param.content }</textarea>
				<br/>
				<br/>
				<br/>
			
				<input type="submit" value="평점 등록" style="height:70px; width:150px; font-size: 15pt" />
			</form>
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