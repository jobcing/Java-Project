<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%-- 공지사항 글 목록을 보여주는 페이지 --%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>공지사항</title>
<meta name="author" content="Luka Cvrk (www.solucija.com)" />
<meta name="Robots" content="index,follow" />
<meta name="description" content="Description" />
<meta name="keywords" content="key, words" />
<link href="css/content.css" rel="stylesheet" type="text/css"
	media="screen" />
</head>

<body>
	<div id="content">
		<%-- 현재 카페 관련 탭에 있다는 걸 알려주기 위해 request 변수 설정 --%>
		<% request.setAttribute("current", "notice"); %>
		<%-- 상단 메뉴바 불러오기 --%>
		<%@ include file="./topMenu.jsp"%>

		<div id="pitch">
			<%-- 가로크기, 셀과 데이터간의 간격, 셀과 셀의 간격, 테두리 굵기, 정렬위치 지정 테이블 생성 --%>
			<table width="100%" cellpadding="5" cellspacing="2"
				border="1" align="center">
				<colgroup>
					<col width="50" />
					<col width="450" />
					<col width="70" />
					<col width="70" />
 					<col width="40" />
				</colgroup>
				
				<thead>
					<tr align="center" height="50">
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>조회수</th>
					</tr>
				</thead>
				
				<tbody>
					<c:if test="${ noticePage.hasNoNotice() }">
						<tr>
							<td colspan="5">게시글이 없습니다.</td>
						</tr>
					</c:if>
					
					<%-- ArrayList<Notice>를 받아와서 notice변수로 설정 --%>
					<c:forEach var="notice" items="${ noticePage.content }">
						<tr>
							<td align=center>${ notice.number }</td>
							<td><a
								href="readNotice.jb?no=${ notice.number }&pageNo=${ noticePage.currentPage }">
									<c:out value="${ notice.title }" />
							</a></td>
							<td align=center>${ notice.getWriter().getNickname() }</td>
							<%-- JSTL fmt 라이브러리를 사용하여 날짜 포맷 --%>
							<%-- dateStyle 종류에는 full, long, medium, short등이 정의되어있다. --%>
							<%-- <td align=center><fmt:formatDate value="${ notice.getRegDate() }" type="both"
												 dateStyle="short" timeStyle="short"/></td> --%>
							<td align=center><fmt:formatDate value="${ notice.getRegDate() }" type="date"
												 dateStyle="medium" /></td>	 
							<td align=center>${ notice.readCount }</td>
						</tr>
					</c:forEach>
				</tbody>
				
				<%-- 페이지 번호 표시 --%>
				<tfoot>
					<c:if test="${ noticePage.hasNotices() }">
						<tr align=center height="50">
							<td colspan="5">
								<c:if test="${ noticePage.startPage > 5 }">
									<a href="listNotice.jb?pageNo=${ noticePage.startPage - 5 }">[이전]</a>
								</c:if>
								
								<c:forEach var="pNo" begin="${ noticePage.startPage }"
									end="${ noticePage.endPage }">
									<a href="listNotice.jb?pageNo=${ pNo }">[${ pNo }]</a>
								</c:forEach> 
								
								<c:if test="${ noticePage.endPage < noticePage.totalPage }">
									<a href="listNotice.jb?pageNo=${ noticePage.startPage + 5 }">[다음]</a>
								</c:if></td>
						</tr>
					</c:if>
					
					<%-- 게시글 등록 버튼 --%>
					<tr height="30">
						<td align="right" colspan="5">
							<%-- 버튼을 클릭하면 해당 페이지로 이동 --%>
							<input type="button" value="게시글 쓰기" onclick="location.href='writeNotice.jb'"/>
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