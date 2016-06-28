<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%-- 공지글 등록하기 위한 FORM --%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>공지글 등록</title>
	<meta name="author" content="Luka Cvrk (www.solucija.com)" />
	<meta name="Robots" content="index,follow" />
	<meta name="description" content="Description" />
	<meta name="keywords" content="key, words" />
	<link href="css/content.css" rel="stylesheet" type="text/css" media="screen" />
</head>

<body>
	<div id="content">
		<%-- topMenu.jsp에 현재 notice관련 페이지가 열려있다는걸 알려주기 위해 request변수 설정 --%>
		<% request.setAttribute("current", "notice"); %>
		<%-- topMenu.jsp 페이지를 불러온다. --%>
		<%@ include file="./topMenu.jsp" %>
	
		<div id="pitch">
			<%-- 사용자가 입력한 데이터를 writeNotice.jb에 post방식으로 전송 --%>
			<form action="writeNotice.jb" method="post">
			<p>
				<span style="font-size: 13pt; font-weight:bold"> 제목: </span><br/>
				<%-- 제목 입력 필드 사이즈와 테두리값 설정 --%>
				<input type="text" name="title" size="65" value="${ param.title }"
										 style="width:800px; border: 1px solid #708090; font-size: 13pt"/>
				<%-- errors.title 이 존재하면 --%>
				<c:if test="${ errors.title }">제목을 입력하세요.</c:if>
			</p>
			<br/><br/>
			<p>
				<%-- 내용 입력 필드 행과 열 사이즈와 테두리값 설정 --%>
				<span style="font-size: 13pt; font-weight:bold"> 내용: </span><br/>
				<textarea name="content" rows="20" cols="65"
							 style="width:800px; border: 1px solid #708090; font-size: 13pt">${ param.content }</textarea>
			</p>
			<br/>
			<%-- 버튼 사이즈 설정 --%>
			<input type="submit" style="width:840px; font-size: 15pt" value="새 글 등록"/>
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