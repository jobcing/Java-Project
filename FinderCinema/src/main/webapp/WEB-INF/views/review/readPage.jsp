<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    	
    	<!-- 게시글 조회 div -->
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
           	
           	<!-- 해당 조회글이 로그인한 사용자가 작성한 것이라면 수정과 삭제버튼이 보이도록 설정 -->
           	<c:if test="${ login.member_id == boardVO.writer }">
           		<button type="submit" class="btn btn-warning" id="modifyBtn">수정</button>
           		<button type="submit" class="btn btn-danger" id="removeBtn">삭제</button>
           	</c:if>
           		<button type="submit" class="btn btn-primary" id="listBtn">목록</button>
           	</div>
           	<!-- /.box-footer -->
		</div>
		<!-- /.row -->
		
		<!-- 댓글 등록 div -->
		<div class="row">
			<div class="col-md-12">
				<div class="box box-success">
					<div class="box-header">
						<h3 class="box-title">댓글작성</h3>
					</div>
					<!-- 로그인을 한 경우에만 댓글 작성 폼이 보이도록 설정 -->
					<c:if test="${ not empty login }">
					<div class="box-body">
						<label for="exampleInputEmail1">작성자</label>
						<input class="form-control" type="text" placeholder="USER ID" id="newReplyWriter"
														value="${ login.member_id }" readonly="readonly">
						<label for="exampleInputEmail1">내용</label>
						<input class="form-control" type="text" placeholder="REPLY TEXT" id="newReplyText">
					</div>
					<!-- /.box-body -->
					<div class="box-footer">
						<button type="submit" class="btn btn-primary" id="replyAddBtn">댓글등록</button>
					</div>
					<!-- /.box-footer -->
					</c:if>
					
					<c:if test="${ empty login }">
					<div class="box-body">
						<div><a href="javascript:goLogin();">Login Please</a></div>
					</div>
					</c:if>
					
				</div>
				<!-- /.box box-success -->
			</div>
			<!-- /.col-md-12 -->
		</div>
		<!-- /.row -->
		
		<!-- 댓글 목록 div -->
		<ul class="timeline">
			<li class="time-label" id="repliesDiv">
				<span class="bg-green">
				댓글보기<small id="replycntSmall">[ ${ boardVO.replycnt } ]</small>
				</span>
		</ul>
		
		<div class="text-center">
			<ul id="pagination" class="pagination pagination-sm no-margin">
			
			</ul>
		</div>
		
    </div>
    <!-- /.container -->

    <hr>
    
    <!-- 댓글 수정/삭제 Modal창 -->
    <!-- Modal -->
    <div id="modifyModal" class="modal modal-primary fade" role="dialog">
    	<div class="modal-dialog">
    		<!-- Modal Content -->
    		<div class="modal-content">
    			<div class="modal-header">
    				<button type="button" class="close" data-dismiss="modal">&times;</button>
    				<h4 class="modal-title"></h4>
    			</div>
    			<div class="modal-body" data-rno>
    				<p><input type="text" id="replytext" class="form-control"></p>
    			</div>
    			<div class="modal-footer">
    				<button type="button" class="btn btn-info" id="replyModBtn">수정</button>
    				<button type="button" class="btn btn-danger" id="replyDelBtn">삭제</button>
    				<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
    			</div>
    		</div>
    		<!-- /.modal-content -->
    	</div>
    	<!-- /.modal-dialog -->
    </div> 
    <!-- /.Modal -->

	<%@include file="../include/footer.jsp" %>
	
	<!-- script -->
	
	<!-- 화면에 반복적으로 처리되는 템플릿 코드를 handlebars를 이용해서 처리 -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
	<!-- 하나의 댓글을 구성하는 부분 -->
	<script id="template" type="text/x-handlebars-template">
	{{#each .}}
	<li class="replyLi" data-rno={{rno}}>
	<i class="fa fa-comments bg-blue"></i>
		<div class="timeline-item">
			<span class="time">
				<i class="fa fa-clock-o"></i>{{prettifyDate regdate}}
			</span>
			<h3 class="timeline-header"><strong>{{rno}}</strong> -{{replyer}}</h3>
			<div class="timeline-body">{{replytext}}</div>
			<div class="timeline-footer">
			{{#eqReplyer replyer}}
				<a class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modifyModal">
				수정</a>
			{{/eqReplyer}}
			</div>
		</div>
	</li>
	{{/each}}
	</script>
	
	<!-- handlebars 기능 확장 -->
	<script>
	Handlebars.registerHelper("prettifyDate", function(timeValue){
		var dateObj = new Date(timeValue);
		var year = dateObj.getFullYear();
		var month = dateObj.getMonth() + 1;
		var date = dateObj.getDate();
		
		return year + "/" + month + "/" + date;
	});
	
	var printData = function(replyArr, target, templateObject){
		var template = Handlebars.compile(templateObject.html());
		
		var html = template(replyArr);
		
		$(".replyLi").remove();
		target.after(html);
	}
	
	// Handlebar 기능 확장을 통해 댓글 수정/삭제를 제어
	Handlebars.registerHelper("eqReplyer", function(replyer, block){
		var accum = "";
		
		if(replyer == "${ login.member_id }"){
			accum += block.fn();
		}
		
		return accum;
	});
	</script>
	
	<!-- handlebars 템플릿을 이용하여 댓글 페이지 처리 -->
	<script>
	var bno = ${boardVO.bno};
	var replyPage = 1;
	
	// 댓글 목록을 보여주는 function
	function getPage(pageInfo){
		$.getJSON(pageInfo, function(data){
			printData(data.list, $("#repliesDiv"), $("#template"));
			printPaging(data.pageMaker, $(".pagination"));
			
			$("#modifyModal").modal('hide');
			$("#replycntSmall").html("[ " + data.pageMaker.totalCount + " ]");
		});
	}
	
	var printPaging = function(pageMaker, target){
		var str = "";
		
		if(pageMaker.prev){
			str += "<li><a href='" + (pageMaker.startPage - 1) + "'> << </a></li>";
		}
		
		for(var i = pageMaker.startPage, len = pageMaker.endPage; i <= len; i++){
			var strClass = pageMaker.cri.page == i ? 'class = active' : '';
			str += "<li " + strClass + "><a href='" + i + "'>" + i + "</a></li>";
		}
		
		if(pageMaker.next){
			str += "<li><a href='" + (pageMaker.endPage + 1) + "'> >> </a></li>";
		}
		
		target.html(str);
	}
	
	</script>
	
	<!-- 각 버튼 이벤트 등록 -->
	<script>
		// 각각 댓글의 버튼
		$(".timeline").on("click", ".replyLi", function(event){
			var reply = $(this);
			
			$("#replytext").val(reply.find(".timeline-body").text());
			$(".modal-title").html(reply.attr("data-rno"));
		});
		
		// 댓글 수정 버튼
		$("#replyModBtn").on("click", function(){
			var rno = $(".modal-title").html();
			var replytext = $("#replytext").val();
			
			$.ajax({
				type: 'put',
				url: '/replies/' + rno,
				headers: {
					"Content-Type": "application/json",
					"X-HTTP-Method-Override": "PUT" },
				data: JSON.stringify({replytext: replytext}),
				dataType: 'text',
				success: function(result){
					console.log("result : " + result);
					
					if(result == 'SUCCESS'){
						alert("수정되었습니다.");
						getPage("/replies/" + bno + "/" + replyPage);
					}
				}});
		});
		
		// 댓글 삭제 버튼
		$("#replyDelBtn").on("click", function(){
			var rno = $(".modal-title").html();
			var replytext = $("#replytext").val();
			
			$.ajax({
				type: 'delete',
				url: '/replies/' + rno,
				headers: {
					"Content-Type": "application/json",
					"X-HTTP-Method-Override": "DELETE" },
				dataType: 'text',
				success: function(result){
					console.log("result : " + result);
					
					if(result == 'SUCCESS'){
						alert("삭제되었습니다.");
						getPage("/replies/" + bno + "/" + replyPage);
					}
				}});
		});
		
		// 댓글 등록 버튼
		$("#replyAddBtn").on("click", function(){
			var replyerObj = $("#newReplyWriter");
			var replytextObj = $("#newReplyText");
			var replyer = replyerObj.val();
			var replytext = replytextObj.val();
			
			$.ajax({
				type: 'post',
				url: '/replies/',
				headers: {
					"Content-Type": "application/json",
					"X-HTTP-Method-Override": "POST" },
				dataType: 'text',
				data: JSON.stringify({bno: bno, replyer: replyer, replytext: replytext}),
				success: function(result){
					console.log("result: " + result);
					
					if(result == 'SUCCESS'){
						alert("등록되었습니다.");
						replyPage = 1;
						getPage("/replies/" + bno + "/" + replyPage);
						replyerObj.val("");
						replytextObj.val("");
					}
				}});
		});
		
		// 댓글 페이징 버튼 이벤트
		$(".pagination").on("click", "li a", function(event){
			event.preventDefault();
			
			replyPage = $(this).attr("href");
			
			getPage("/replies/" + bno + "/" + replyPage);
		});
		
		// 댓글 목록 버튼
		$("#repliesDiv").on("click", function(){
			if($(".timeline li").size() > 1){
				return;
			}
			
			getPage("/replies/" + bno + "/1");
		});

		// hidden input form 이벤트 처리
		$(document).ready(function(){
			var formObj = $("form[role='form']");
			
			console.log(formObj);
			
			// 수정버튼
			$("#modifyBtn").on("click", function(){
				formObj.attr("action", "/review/modifyPage");
				formObj.attr("method", "get");
				formObj.submit();
			});
			
			// 삭제버튼
			$("#removeBtn").on("click", function(){
				formObj.attr("action", "/review/removePage");
				formObj.submit();
			});
			
			// 목록버튼
			$("#listBtn").on("click", function(){
				formObj.attr("method", "get");
				formObj.attr("action", "/review/listPage");
				formObj.submit();
			});
		});
	</script>
</body>

</html>

