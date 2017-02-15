<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<%@include file="../include/header.jsp" %>
	
    <title>Review Register</title>
</head>

<body>
	<%@include file="../include/menu.jsp" %>
	
    <!-- Page Header -->
    <!-- Set your background image for this header on the line below. -->
    <header class="intro-header" style="width:100%; height:60px; background-image: url('/resources/img/Cinema.jpg')">
		<div class="site-heading">
		</div>
    </header>
    
    <!-- 수정이나 삭제 페이지로 이동시 (게시글 번호/현재 페이지 번호/검색 유형/검색어)를 전달하기 위해 input hidden 사용 -->
    <form role="form" method="post">
    	<input type="hidden" name="bno" value="${ boardVO.bno }">
    	<input type="hidden" name="page" value="${ cri.page }">
    	<input type="hidden" name="searchType" value="${ cri.searchType }">
    	<input type="hidden" name="keyword" value="${ cri.keyword }">
    </form>

    <!-- Main Content -->
    <div class="container">
        <div class="row">
			<div class="box-body">
				<div class="form-group">
					<label for="exampleInputEmail1">Title</label>
					<input type="text" name="title" class="form-control"
						value="${ boardVO.title }" readonly="readonly">
				</div>
	           		
	           	<div class="form-group">
	           		<label for="exampleInputPassword1">Content</label>
	           		<textarea class="form-control" name="content" rows="3"
	           			readonly="readonly">${ boardVO.content }
	           		</textarea>
	           	</div>
	           		
	           	<div class="form-group">
	           		<label for="exampleInputEmail1">Writer</label>
	           		<input type="text" name="writer" class="form-control"
	           			value="${ boardVO.writer }" readonly="readonly">
	           	</div>
			</div>
           	<!-- /.box-body -->
           	
           	<div class="box-footer">
           		<button type="submit" class="btn btn-warning">수정</button>
           		<button type="submit" class="btn btn-danger">삭제</button>
           		<button type="submit" class="btn btn-primary">목록</button>
           	</div>
           	<!-- /.box-footer -->
		</div>
    </div>

    <hr>

	<%@include file="../include/footer.jsp" %>
	
	<script>
	
		// 각 버튼에 jQuery를 이용해서 링크 처리
		$(document).ready(function(){
			var formObj = $("form[role='form']");
			
			console.log(formObj);
			
			// 수정버튼
			$(".btn-warning").on("click", function(){
				formObj.attr("action", "/review/modifyPage");
				formObj.attr("method", "get");
				formObj.submit();
			});
			
			// 삭제버튼
			$(".btn-danger").on("click", function(){
				formObj.attr("action", "/review/removePage");
				formObj.submit();
			});
			
			// 목록버튼
			$(".btn-primary").on("click", function(){
				formObj.attr("method", "get");
				formObj.attr("action", "/review/listPage");
				formObj.submit();
			});
		});
	
	</script>
</body>

</html>

