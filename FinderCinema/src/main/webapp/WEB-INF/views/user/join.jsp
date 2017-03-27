<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    		<form id="minfo" action="/user/join" method="post">
	    		<div class="box-body">
	    			<div class="form-group has-feedback">
						<span style="font-size: 15pt"> <b>아이디 : </b> </span>
	    				<input type="text" id="id" name="member_id" class="form-control" placeholder="USER ID" />
						<label for="exampleInputEmail1" id="check"></label>
	    				<span class="glphicon glphicon-envelope form-control-feedback"></span>
	    			</div>
	    			<div class="form-group has-feedback">
						<span style="font-size: 15pt"> <b>비밀번호 : </b> </span>
	    				<input type="password" id="pw" name="member_pw" class="form-control" placeholder="PASSWORD" />
	    				<span class="glphicon glphicon-lock form-control-feedback"></span>
	    			</div>
	    			<div class="form-group has-feedback">
						<span style="font-size: 15pt"> <b>비밀번호 확인 : </b> </span>
	    				<input type="password" id="confirm" name="member_confirm" class="form-control" placeholder="PASSWORD" />
	    				<span class="glphicon glphicon-lock form-control-feedback"></span>
	    			</div>
	    			<div class="form-group has-feedback">
						<span style="font-size: 15pt"> <b>닉네임 : </b> </span>
	    				<input type="text" id="nickname" name="nickname" class="form-control" placeholder="NICKNAME" />
	    				<span class="glphicon glphicon-envelope form-control-feedback"></span>
	    			</div>
	    		</div>
	    		<!-- /.box-body -->
           	
           		<div class="box-footer">
    				<button type="button" class="btn btn-primary btn-block btn-flat" onclick="javascript: sendForm()">Sign Up</button>
           		</div>
           		<!-- /.box-footer -->
    		</form>
    	</div>
    	<!-- /.row -->

        <div class="row">

        </div>
        <!-- /.row -->
    </div>
    <!-- /.container -->


    <hr>

	<%@include file="../include/footer.jsp" %>
	
	<!-- script -->
	
	<script>
	
	// 아이디 중복 체크
	$("#id").keyup(function(){
		var member_id = $("#id").val();
		var member_pw = $("#pw").val();
		var nickname = $("#nickname").val();
		
		$.ajax({
			type: 'post',
			url: '/user/check',
			headers: {
				"Content-Type": "application/json",
				"X-HTTP-Method-Override": "POST" },
			dataType: 'text',
			data: JSON.stringify({member_id: member_id, member_pw: member_pw, nickname: nickname}),
			success: function(data){
				if(data.length > 0){
					$("#check").html("해당 아이디가 이미 존재합니다.");
					//alert("해당 아이디가 이미 존재합니다.")
				} else{
					$("#check").html("");
				}
			},
			error: function(error){
				alert(error.statusText);
			}
			});
	});
	
	// 회원정보 전송 버튼 이벤트 등록
	function sendForm(){
		
		// 아이디 중복체크
		if($("#check") != ""){
			alert("아이디가 중복되었습니다.");
			
			$("#id").focus();
			
			return;
		}
		// 아이디 입력 여부 검사
		if($("#id").val() == ""){
			alert("아이디를 입력해주세요.");
			
			$("#id").focus();
			
			return;
		}
		// 아이디 공백 여부 검사
		if($("#id").val().indexOf(" ") >= 0){
			alert("아이디에 공백을 사용할 수 없습니다.");
			
			$("#id").focus();
			$("#id").select();
			
			return;
		}
		// 비밀번호 입력 여부 검사
		if($("#pw").val() == ""){
			alert("비밀번호를 입력해주세요.");
			
			$("#pw").focus();
			
			return;
		}
		// 비밀번호 확인과 일치 여부 검사
		if($("#pw").val() != $("#confirm").val()){
			alert("비밀번호가 일치하지 않습니다.");
			
			$("#pw").val() = "";
			$("#confirm").val() = "";
			$("#pw").focus();
			
			return;
		}
		// 닉네임 입력 여부 검사
		if($("#nickname").val() == ""){
			alert("닉네임을 입력해주세요.");
			
			$("#nickname").focus();
			
			return;
		}
		// 닉네임 공백 여부 검사
		if($("#nickname").val().indexOf(" ") >= 0){
			alert("닉네임에 공백을 사용할 수 없습니다.");
			
			$("#nickname").focus();
			$("#nickname").select();
			
			return;
		}
		
		$("#minfo").submit();
	}
	
	</script>
	
</body>

</html>

