<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<%@include file="../include/header.jsp" %>
	
    <title>Reservation</title>

</head>

<body>
	<%@include file="../include/menu.jsp" %>
	
    <!-- Page Header -->
    <!-- Set your background image for this header on the line below. -->
    <header class="intro-header" style="width:100%; height:60px; background-image: url('/resources/img/Cinema.jpg')">
		<div class="site-heading">
		</div>
    </header>

    <!-- Main Content -->
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
  				<h5>${ data }</h5>
                <hr>
                    
                <form role="form" method="post">
                	<input type="hidden" name="data" value="&{placesData};">
                </form>           
                
            	<button type="submit" class="btn btn-primary">NEXT</button>
            	
            	<script>
            		$(document).ready(function(){
            			var formObj = $("form[role='form']");
            			
            			console.log(formObj);
            			
            			$(".btn-primary").on("click", function(){
            				formObj.attr("action", "/reserve/twostep");
            				formObj.submit();
            			});
            		});
            	</script>

            </div>
        </div>
    </div>

    <hr>

	<%@include file="../include/footer.jsp" %>
</body>

</html>

