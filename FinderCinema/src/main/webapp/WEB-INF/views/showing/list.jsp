<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<%@include file="../include/header.jsp" %>
	
    <title>Now Showing</title>
</head>

<body>
	<%@include file="../include/menu.jsp" %>
	
    <!-- Page Header -->
    <!-- Set your background image for this header on the line below. -->
    <header class="intro-header" style="background-image: url('/resources/img/post-bg.jpg')">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                    <div class="site-heading">
                        <h1>Now Showing</h1>
                        <hr class="small">
                        <span class="subheading">More Faster, More Simply, Enjoy the movie !</span>
                    </div>
                </div>
            </div>
        </div>
    </header>

    <!-- Main Content -->
    <div class="container">
        <div class="row">
        	<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
        		<div class="row">
					<div class="col-xs-4">
						<img src="/resources/img/showing01.png" width="200" height="300"
						style="border: 3px solid #D8D8D8">
					</div>
					<div class="col-xs-8">
						<h2 class="post-title">미녀와 야수</h2>
						<hr style="border-color: black">
                        <h3 class="post-subtitle">
                        스크린에 의해 재탄생한 세기의 걸작!
                        전 세계가 기다려온 가장 아름다운 이야기!
                        </h3>
                        <p class="post-meta">Posted by <a href="#">Start Bootstrap</a> on September 24, 2014</p>
					</div>
				</div>
				
				<hr>
				
				<div class="row">
					<div class="col-xs-4">
						<img src="/resources/img/showing02.png" width="200" height="300"
						style="border: 3px solid #D8D8D8">
					</div>
					<div class="col-xs-8">
						<h2 class="post-title">프리즌</h2>
						<hr style="border-color: black">
                        <h3 class="post-subtitle">
                        흔적도 증거도 없다! 감옥에서 시작되는 완전 범죄
                        </h3>
					</div>
				</div>
				
				<hr>
				
				<div class="row">
					<div class="col-xs-4">
						<img src="/resources/img/showing03.png" width="200" height="300"
						style="border: 3px solid #D8D8D8">
					</div>
					<div class="col-xs-8">
						<h2 class="post-title">공각기동대 : 고스트 인 더 쉘</h2>
						<hr style="border-color: black">
                        <h3 class="post-subtitle">
                        인간과 로봇의 경계가 무너진 가까운 미래, 강력 범죄와 테러 사건을 담당하는
                        엘리트 특수부대 섹션9
                        </h3>
					</div>
				</div>
				
				<hr>
				
				<div class="row">
					<div class="col-xs-4">
						<img src="/resources/img/showing04.png" width="200" height="300"
						style="border: 3px solid #D8D8D8">
					</div>
					<div class="col-xs-8">
						<h2 class="post-title">원라인</h2>
						<hr style="border-color: black">
                        <h3 class="post-subtitle">
                        은행 돈, 필요하세요?
                        우리 대본대로 하면 돈 나옵니다!
                        </h3>
					</div>
				</div>
				
				<hr>
				
				<div class="row">
					<div class="col-xs-4">
						<img src="/resources/img/showing05.png" width="200" height="300"
						style="border: 3px solid #D8D8D8">
					</div>
					<div class="col-xs-8">
						<h2 class="post-title">보통사람</h2>
						<hr style="border-color: black">
                        <h3 class="post-subtitle">
                        평범하지 않았던 시대,
                        평범하게 살고 싶었던 보통사람들의 특별한 이야기가 시작된다!
                        </h3>
					</div>
				</div>
				
				<hr>
				
				<div class="row">
					<div class="col-xs-4">
						<img src="/resources/img/showing06.png" width="200" height="300"
						style="border: 3px solid #D8D8D8">
					</div>
					<div class="col-xs-8">
						<h2 class="post-title">데스노트 : 더 뉴 월드</h2>
						<hr style="border-color: black">
                        <h3 class="post-subtitle">
                        키라의 죽음, 그리고 10년 뒤....
                        전 세계를 휩쓴 데스노트가 다시 나타났다!
                        </h3>
					</div>
				</div>
				
				<hr>
			</div>
        </div> <!-- /.row -->
    </div><!-- /.container -->

    <hr>

	<%@include file="../include/footer.jsp" %>
</body>

</html>

