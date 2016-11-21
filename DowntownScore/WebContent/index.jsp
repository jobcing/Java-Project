<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%-- Main Page --%>

<%--
	프로젝트 기간 : 2015.12 ~ 2016.04
	
	프로젝트 진행하면서 힘들었던 점 : 
	1. MVC패턴을 이해하고 적용시키는 것
	2. 상황에 따른 DB쿼리 명령어
	3. 예기치 못한 곳에서 발생하는 오류
	4. 게시글 삭제 기능 ( 리넘버링 )
	5. 사용자 입력에 따른 메서드 실행 --> 오버로딩 사용
	6. 사용자 검색어 입력에 따른 평점 페이지 출력하는 부분 ( 평균평점구하기, 검색어를 입력하지않았을경우, 사용자가 검색한 내용 출력 )
	7. 오픈소스 css 적용 ( 그대로 적용할 수 없기에 수정하는 부분이 )
	8. html 꾸미기 
	9. 화면 페이지 이동 구현
	10. 페이지 이동 ( 검색어에 맞는 데이터만 출력하게끔 )
	11. 서블릿 설정하는 부분
--%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Downtown Score</title>
	<meta name="author" content="Luka Cvrk (www.solucija.com)" />
	<meta name="Robots" content="index,follow" />
	<meta name="description" content="Description" />
	<meta name="keywords" content="key, words" />
	<link href="css/main.css" rel="stylesheet" type="text/css" media="screen" />
</head>

<body>
	<div id="content">
		<%-- 현재 메인 관련 탭에 있다는 걸 알려주기 위해 request 변수 설정 --%>
		<% request.setAttribute("current", "main"); %>
		<%-- 상단 메뉴바 불러오기 --%>
		<%@ include file="./topMenu.jsp" %>
		
		<%-- 화면 상단 이미지 부분 --%>
		<div id="pitch">
			<div id="pitch-left">
				<img src="images/starbucks01.jpg" alt="" />
				<div id="caption">
					<span></span>
					<h2>Weekly Best</h2>
				</div>
			</div>
				
			<div id="pitch-right">
				<div class="templ">
					<img src="images/starbucks02.jpg" />
				</div>
				<div class="tempr">
					<img src="images/starbucks03.jpg" />
				</div>
				<p id="expl">이번 주 베스트는 '스타벅스 파미에스테이션' 입니다. 스타벅스 파미에스테이션은 스타벅스가 국내 론칭 15주년을 
				기념하여 오픈한 프리미엄 매장으로서 유동인구가 많은 곳에 위치하고 있어 많은 사람들이 즐겨 찾는 곳입니다. </p>
			</div>
		</div>
		
		<%-- 화면 중앙 게시글 부분 --%>
		<div class="col">
			<h2>공지사항</h2>
			<p>Phasellus diam sapien, fermentum a eleifend non, luctus non augue. Quisque scelerisque purus quis eros sollicitudin gravida. Aliquam erat volutpat. Donec a sem consequat tortor posuere dignissim sit amet at ipsum.</p>
			<a class="more" href="#">read more</a>
		</div>
		<div class="col">
			<h2>최근 게시글</h2>
			<p>Phasellus diam sapien, fermentum a eleifend non, luctus non augue. Quisque scelerisque purus quis eros sollicitudin gravida. Aliquam erat volutpat. Donec a sem consequat tortor posuere dignissim sit amet at ipsum.</p>
			<a class="more" href="#">read more</a>
		</div>
		<div class="col last">
			<h2>랭킹 1위</h2>
			<p>jobcing 님.</p>
			<a class="more" href="#">read more</a>
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