<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%-- 회원가입 FORM --%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>회원가입</title>
	<meta name="author" content="Luka Cvrk (www.solucija.com)" />
	<meta name="Robots" content="index,follow" />
	<meta name="description" content="Description" />
	<meta name="keywords" content="key, words" />
	<link href="css/content.css" rel="stylesheet" type="text/css" media="screen" />
</head>

<body>
	<div id="content">
		<%-- 현재 메인 관련 탭에 있다는 걸 알려주기 위해 request 변수 설정 --%>
		<% request.setAttribute("current", "main"); %>
		<%-- 상단 메뉴바 불러오기 --%>
		<%@ include file="./topMenu.jsp" %>
	
		<div id="pitch">
			<br/>
			<%-- POST 방식으로 전달 --%>
			<form action="./join.jb" method="post">
				<span style="font-size: 15pt"> <b>아이디 : </b> </span>
				<br/>
				<input type="text" name="member_id" value="${ param.member_id }"
										style="width:200px; height:40px; border: 1px solid #708090; font-size: 13pt"/>
				<c:if test="${ errors.member_id }">아이디를 입력하세요.</c:if>
				<c:if test="${ errors.duplicateId }">이미 사용중인 아이디입니다.</c:if>
				<br/><br/><br/>
				
				<span style="font-size: 15pt"> <b>비밀번호 : </b> </span>
				<br/>
				<input type="password" name="member_pwd" style="width:200px; height:40px; border: 1px solid #708090; font-size: 13pt"/>
				<c:if test="${ errors.member_pwd }">암호를 입력하세요.</c:if>
				<br/><br/><br/>
				
				<span style="font-size: 15pt"> <b>비밀번호 확인 : </b> </span>
				<br/>
				<input type="password" name="confirmPassword" style="width:200px; height:40px; border: 1px solid #708090; font-size: 13pt"/>
				<c:if test="${ errors.confirmPassword }">비밀번호 확인을 입력하세요.</c:if>
				<c:if test="${ errors.notMatch }">비밀번호가 일치하지 않습니다.</c:if>
				<br/><br/><br/>
				
				<span style="font-size: 15pt"> <b>닉네임 : </b> </span>
				<br/>
				<input type="text" name="member_nickname" value="${ param.member_nickname }"
														style="width:200px; height:40px; border: 1px solid #708090; font-size: 13pt" />
				<c:if test="${ errors.member_nickname }">닉네임을 입력하세요.</c:if>
				<c:if test="${ errors.duplicatedNick }">이미 사용중인 닉네임입니다.</c:if>
				<br/><br/><br/>
				
				<span style="font-size: 15pt"> <b>나이 : </b> </span>
				<br/>
				<input type="text" name="member_age" style="width:200px; height:40px; border: 1px solid #708090; font-size: 13pt"/>
				<br/><br/><br/><br/>
				
				&emsp;&emsp;&emsp;
				<input type="submit" value="회원가입" style="height:70px; width:150px; font-size: 15pt" />
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