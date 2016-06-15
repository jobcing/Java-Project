<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>음식점</title>
<meta name="author" content="Luka Cvrk (www.solucija.com)" />
<meta name="Robots" content="index,follow" />
<meta name="description" content="Description" />
<meta name="keywords" content="key, words" />
<link href="css/content.css" rel="stylesheet" type="text/css"
	media="screen" />
</head>
<body>
	<div id="content">
		<%
			request.setAttribute("current", "restaurant");
		%>
		<%@ include file="./topMenu.jsp"%>

		<div id="pitch">
			<form action="./listRestaurant.jb" method="get">
				주변역:
				<select name="station">
					<option value="범계역" selected>범계역</option>
				</select>
				<br/>
				상호명 : <input type="text" name="name"><br/>
				
				<input type="submit" value="검색">
			</form>
			<br/>
			
			<h2>'${ param.name }'이 포함된 검색결과입니다.<br/>
			
			<c:set var="avg" value="${ scoreAvg }" />
			[ 평균 평점 : ${ avg } ]</h2>

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
						<td>평점</td>
						<td>상호명</td>
						<td>한줄평</td>
						<td>닉네임</td>
					</tr>
				</thead>
				<tbody>
					<c:if test="${ restaurantPage.hasNoNotice() }">
						<tr>
							<td colspan="4">게시글이 없습니다.</td>
						</tr>
					</c:if>
					<c:forEach var="restaurant" items="${ restaurantPage.content }">
						<tr>
							<td align=center>${ restaurant.score }</td>
							<td align=center>${ restaurant.name }</td>
							<td>${ restaurant.comment }</td>
							<td>${ restaurant.getNickname() }</td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
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
					<tr height="50">
						<td align="right" colspan="4"><a href="writeRestaurant.jb">[한줄평 등록]</a></td>
					</tr>
				</tfoot>
			</table>
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