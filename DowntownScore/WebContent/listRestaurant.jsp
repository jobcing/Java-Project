<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%-- 식당 평점 리스트를 보여주는 페이지 --%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>식당</title>
<meta name="author" content="Luka Cvrk (www.solucija.com)" />
<meta name="Robots" content="index,follow" />
<meta name="description" content="Description" />
<meta name="keywords" content="key, words" />
<link href="css/content.css" rel="stylesheet" type="text/css"
	media="screen" />
</head>

<body>
	<div id="content">
		<%-- 현재 식당 관련 탭에 있다는 걸 알려주기 위해 request 변수 설정 --%>
		<% request.setAttribute("current", "restaurant"); %>
		<%-- 상단 메뉴바 불러오기 --%>
		<%@ include file="./topMenu.jsp"%>

		<div id="pitch">
			<%-- 검색버튼을 누르면 get방식으로 인자 전달 --%>
			<form action="./listRestaurant.jb" method="get">
				<%-- font size 를 13pt 변경 --%>
				<span style="font-size: 15pt"> 주변역 :  </span>
				<select name="station" style="width:150px; height:50px; font-size: 13pt">
					<option value="범계역" selected>범계역</option>
					<option value="홍대입구">홍대입구</option>
					<option value="강남역">강남역</option>
					<option value="수원역">수원역</option>
					<option value="명동">명동</option>
				</select>
				
				<br/><br/>
				<span style="font-size: 15pt"> 상호명 :  </span>
				<input type="text" name="name" size="20" style="width:200px; height:50px; font-size: 13pt" />
				<%-- 띄어쓰기 --%>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="submit" value="검색" style="height:50px; width:100px; font-size: 13pt" />
			</form>
			<br/>
			* 상호명을 입력하지않고 검색하면 전체 검색결과가 표시됨 *
			<br/>
			<br/>
			<br/>
			
			<%-- 사용자가 입력한 상호명을 표시 --%>
			<h2>'${ param.name }'이 포함된 검색결과입니다.<br/>
			
			<%-- 사용자가 입력한 상호명에 대한 평균 표시 --%>
			<c:set var="avg" value="${ scoreAvg }" />
			[ 평균 평점 : ${ avg } ]</h2>
			
			<%-- 한줄평을 보여주는 테이블 생성 --%>
			<table width="100%" cellpadding="5" cellspacing="2"
				border="1" align="center">
				<colgroup>
					<col width="50" />
					<col width="100" />
					<col width="500" />
					<col width="50" />
				</colgroup>
				
				<thead>
					<tr align="center" height="50">
						<th>평점</th>
						<th>상호명</th>
						<th>한줄평</th>
						<th>닉네임</th>
					</tr>
				</thead>
				
				<tbody>
					<%-- 해당 게시글이 없다면 표시 --%>
					<c:if test="${ restaurantPage.hasNoNotice() }">
						<tr>
							<td colspan="4">게시글이 없습니다.</td>
						</tr>
					</c:if>
					
					<%-- ArrayList<Restaurant>를 받아와서 restaurant변수로 설정 --%>
					<c:forEach var="restaurant" items="${ restaurantPage.content }">
						<tr>
							<td align=center><span style="color:blue">${ restaurant.score }</span></td>
							<td align=center>${ restaurant.name }</td>
							<td>${ restaurant.comment }</td>
							<td align=center>${ restaurant.getNickname() }</td>
						</tr>
					</c:forEach>
				</tbody>
				
				<%-- 페이지 번호 표시 --%>
				<tfoot>
					<%-- 사용자가 검색한 '주변역'과 '상호명'을 전달받아서 station, name변수로 저장 --%>
					<%-- 페이지 이동할 시 '주변역'과 '상호명'이 필요하기 때문 --%>
					<c:set var="station" value="${ station }" />
					<c:set var="name" value="${ name }" />
					
					<c:if test="${ restaurantPage.hasNotices() }">
						<tr align=center height="50">
							<td colspan="4">
								<c:if test="${ restaurantPage.startPage > 5 }">
									<a href="listRestaurant.jb?pageNo=${ restaurantPage.startPage - 5 }
												&station=${ station }&name=${ name }">[이전]</a>
								</c:if>
								<c:forEach var="pNo" begin="${ restaurantPage.startPage }"
								 end="${ restaurantPage.endPage }">
									<a href="listRestaurant.jb?pageNo=${ pNo }
												&station=${ station }&name=${ name }">[${ pNo }]</a>
								</c:forEach>
								<c:if test="${ restaurantPage.endPage < restaurantPage.totalPage }">
									<a href="listRestaurant.jb?pageNo=${ restaurantPage.startPage + 5 }
												&station=${ station }&name=${ name }">[다음]</a>
								</c:if>
							</td>
						</tr>
					</c:if>
					
					<%-- 한줄평 등록 버튼 --%>
					<tr height="50">
						<td align="right" colspan="4">
							<%-- 버튼을 클릭하면 해당 페이지로 이동 --%>
							<input type="button" value="한줄평 등록" onclick="location.href='writeRestaurant.jb'"/>
						</td>
					</tr>
				</tfoot>
			
			</table>
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
			<p>Nunc eget nunc libero. Nunc commodo euismod massa quis
				vestibulum. Quisque vel lorem eu libero laoreet facilisis. Aenean
				plac, ligula quis placerat iaculis, mi magna luctus.</p>
			<p id="author">&not; Commodo Euismod</p>
		</div>

		<div id="footer">
			<p id="flinks">
				<a href="#">Become a fan on Facebook</a> &middot; <a href="#">Follow
					us on Twitter</a> &middot; <a href="#">RSS</a>
			</p>
			<p>
				Copyright &copy; 2009 EXPANDING PORTFOLIO &minus; Design: Luka Cvrk,
				<a href="http://www.solucija.com/" title="Free CSS Templates">Solucija</a>
			</p>
		</div>
	</div>
</body>
</html>