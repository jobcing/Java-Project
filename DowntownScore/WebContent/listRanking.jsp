<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%-- 회원들의 랭킹을 보여주는 페이지 --%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>랭킹</title>
<meta name="author" content="Luka Cvrk (www.solucija.com)" />
<meta name="Robots" content="index,follow" />
<meta name="description" content="Description" />
<meta name="keywords" content="key, words" />
<link href="css/content.css" rel="stylesheet" type="text/css"
	media="screen" />
</head>

<body>
	<div id="content">
		<%-- 현재 순위 관련 탭에 있다는 걸 알려주기 위해 request 변수 설정 --%>
		<% request.setAttribute("current", "ranking"); %>
		<%-- 상단 메뉴바 불러오기 --%>
		<%@ include file="./topMenu.jsp"%>
		</br>
		<div id="pitch">
			<%-- ArrayList<Member>를 받아와서 rank변수로 설정 --%>
			<%-- DB에서 현재 접속한 아이디와 일치하는 정보를 가져오기 위해 --%>
			<c:forEach var="rank" items="${ rankingPage.content }"
				varStatus="status">
				
				<%-- 접속한 아이디와 DB에 저장되어있는 닉네임과 같다면 랭크 표시 --%>
				<c:if test="${ userNickname eq rank.getNickname() }">
					<h2>'${ userNickname }' 님의 랭킹은 ${ status.count } 위 입니다.</h2>
				</c:if>
				
			</c:forEach>
			</br>
			
			<table width="100%" cellpadding="5" cellspacing="2" border="1"
				align="center">
				<colgroup>
					<col width="50" />
					<col width="500" />
					<col width="100" />
				</colgroup>
				
				<thead>
					<tr align="center" height="50">
						<th>순위</th>
						<th>닉네임</th>
						<th>포인트</th>
					</tr>
				</thead>
			
				<tbody>
					<%-- 해당 게시글이 없다면 표시 --%>
					<c:if test="${ rankingPage.hasNoNotice() }">
						<tr>
							<td colspan="4">게시글이 없습니다.</td>
						</tr>
					</c:if>
					
					<%-- ArrayList<Member>를 받아와서 rank변수로 설정 --%>
					<c:forEach var="rank" items="${ rankingPage.content }"
						varStatus="status">
						<tr>
							<td align=center><span style="color:blue">${ status.count }</span></td>
							<td align=center>${ rank.getNickname() }</td>
							<td align=center>${ rank.getCnt() }</td>
						</tr>
					</c:forEach>
				</tbody>
				
				<%-- 페이지 번호 표시 --%>
				<tfoot>
					<c:if test="${ rankingPage.hasNotices() }">
						<tr align=center height="50">
							<td colspan="4"><c:if test="${ rankingPage.startPage > 5 }">
									<a href="listRanking.jb?pageNo=${ rankingPage.startPage - 5 }">[이전]</a>
								</c:if> <c:forEach var="pNo" begin="${ rankingPage.startPage }"
									end="${ rankingPage.endPage }">
									<a href="listRanking.jb?pageNo=${ pNo }">[${ pNo }]</a>
								</c:forEach> <c:if test="${ rankingPage.endPage < rankingPage.totalPage }">
									<a href="listRanking.jb?pageNo=${ rankingPage.startPage + 5 }">[다음]</a>
								</c:if></td>
						</tr>
					</c:if>
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