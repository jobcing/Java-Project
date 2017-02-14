<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<%@include file="../include/header.jsp" %>
	
    <title>About</title>
</head>

<body>
	<%@include file="../include/menu.jsp" %>
	
    <!-- Page Header -->
    <!-- Set your background image for this header on the line below. -->
    <header class="intro-header" style="background-image: url('/resources/img/home-bg.jpg')">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                    <div class="site-heading">
                        <h1>Reviews</h1>
                        <hr class="small">
                        <span class="subheading">Movie Communication</span>
                    </div>
                </div>
            </div>
        </div>
    </header>

    <!-- Main Content -->
    <div class="container">
        <div class="row">
        	<table class="table table-bordered">
        		<!-- 게시글 상단 -->
        		<tr>
        			<th style="width: 10px">BNO</th>
        			<th>TITLE</th>
        			<th>WRITER</th>
        			<th>REGDATE</th>
        			<th style="width: 40px">VIEWCNT</th>
        		</tr>
        		<!-- 게시글 목록 -->
        		<c:forEach items="${ list }" var="boardVO">
        		
        			<tr>
        				<td>${ boardVO.bno }</td>
        				<td><a href="/review/readPage?bno=${ boardVO.bno }&page=${ pageMaker.page }">
        					${ boardVO.title }</a></td>
        				<td>${ boardVO.writer }</td>
        				<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${ boardVO.regdate }" /></td>
        				<td><span class="badge bg-red">${ boardVO.viewcnt }</span></td>
        		
        		</c:forEach>
        	</table>
        </div>
    </div>
    <!-- 페이지 링크 제공 UI -->
    <div class="text-center">
    	<ul class="pagination">
    	
    		<!-- [이전] 버튼이 있다면 -->
    		<c:if test="${ pageMaker.prev }">
    			<li><a href="listPage?page=${ pageMaker.startPage - 1 }">&laquo;</a></li>
 			</c:if>
 			
 			<!-- 페이지 번호 출력 -->
 			<c:forEach begin="${ pageMaker.startPage }" end="${ pageMaker.endPage }" var="idx">
 				<!-- 현재 페이지를 표시 -->
 				<li
 					<c:out value="${ pageMaker.page == idx ? 'class = active' : '' }"/>>
 					<a href="listPage?page=${ idx }">${ idx }</a>
 				</li>
 			</c:forEach>
 			
 			<!-- [다음] 버튼이 있다면 -->
 			<c:if test="${ pageMaker.next && pageMaker.endPage > 0 }">
 				<li><a href="listPage?page=${ pageMaker.endPage + 1 }">&raquo;</a></li>
 			</c:if>
 			
    	</ul>
    </div>

    <hr>

	<%@include file="../include/footer.jsp" %>
	
	<script>
	
		var result = '${msg}';
		
		// 컨트롤러에서 보낸 msg가 SUCCESS라면 아래와 같은 팝업창을 띄운다.
		if(result == 'SUCCESS'){
			alert("처리가 완료되었습니다.");
		}
		
	</script>
	
</body>

</html>

