<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%-- 공지사항 게시글을 클릭했을 경우 게시글 내용이 표시되는 페이지 --%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>공지사항</title>
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
			<table align="center" cellpadding="5" cellspacing="2" border="1" width="100%">
			<%-- ReadNoticeHandler로 부터 noticeData 변수를 전달받음 --%>
			<%-- NoticeData : Notice + NoticeContent --%>
			<tr align="center">
				<th width="10%">No.</th>
				<td>${ noticeData.notice.number }</td>
			</tr>
			<tr align="center">
				<th>작성자</th>
				<td>${ noticeData.notice.writer.getNickname() }</td>
			</tr>
			<tr align="center">
				<th>제목</th>
				<td><c:out value="${ noticeData.notice.title }" /></td>
			</tr>
			<tr align="center">
				<th colspan="2">내용</th>
			</tr>
			<tr height="700">
				<%-- 수직정렬 top, 상하좌우 여백 50px, 폰트사이즈 15pt --%>
				<td colspan="2" valign="top" style="padding:50px; font-size:15pt"><c:out value="${ noticeData.content }" /></td>
			</tr>
			<tr align="right">
				<td colspan="2">
				<c:set var="pageNo" value="${ empty param.pageNo ? '1' : param.pageNo }" />
					<input type="button" value="목록" onclick="location.href='listNotice.jb?pageNo=${ pageNo }'"/>
					
					<%-- 접속한 아이디와 게시글 작성자와 같다면 --%>
					<c:if test="${ authUser.id == noticeData.notice.writer.id }">
					<input type="button" value="게시글 수정"
								 onclick="location.href='modifyNotice.jb?no=${ noticeData.notice.number }'"/>
					
					<%-- 게시글 삭제 확인 자바스크립트 --%>
					<script language="javascript">
						function deleteConfirm() {
							msg = "게시글을 정말 삭제하시겠습니까 ?";
							
							if (confirm(msg)!=0) {
								location.href='deleteNotice.jb?no=${ noticeData.notice.number }';
							} else {
								
							}
						} // deleteConfirm()
					</script>
					
					<input type="button" value="게시글 삭제" onclick="deleteConfirm()"/>
					</c:if>
				</td>
			</tr>
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