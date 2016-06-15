<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Downtown Score</title>
	<meta name="author" content="Luka Cvrk (www.solucija.com)" />
	<meta name="Robots" content="index,follow" />
	<meta name="description" content="Description" />
	<meta name="keywords" content="key, words" />
	<link href="css/main.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>
	<div id="content">
		<% request.setAttribute("current", "main"); %>
		<%@ include file="./topMenu.jsp" %>
	
		<div id="pitch">
			<div id="pitch-left">
				<img src="images/featured.jpg" alt="" />
				<div id="caption">
					<span></span>
					<h2>Weekly Best</h2>
				</div>
			</div>
				
			<div id="pitch-right">
				<div class="templ">
					<img src="images/templ.jpg" />
					<a class="zoom" href="" /></a>
				</div>
				<div class="tempr">
					<img src="images/templ.jpg" />
					<a class="zoom" href="" /></a>
				</div>
				<p id="expl">한글 fermentum a eleifend non, luctus non augue. Quisque scelerisque purus quis eros sollicitudin gravida.</p>
				<p><a id="browse" href="#">한글</a></p>
			</div>
		</div>
		
		<div class="col">
			<h2>한글</h2>
			<p>Phasellus diam sapien, fermentum a eleifend non, luctus non augue. Quisque scelerisque purus quis eros sollicitudin gravida. Aliquam erat volutpat. Donec a sem consequat tortor posuere dignissim sit amet at ipsum.</p>
			<a class="more" href="#">read more</a>
		</div>
		<div class="col">
			<h2>Phasellus diam sapien</h2>
			<p>Phasellus diam sapien, fermentum a eleifend non, luctus non augue. Quisque scelerisque purus quis eros sollicitudin gravida. Aliquam erat volutpat. Donec a sem consequat tortor posuere dignissim sit amet at ipsum.</p>
			<a class="more" href="#">read more</a>
		</div>
		<div class="col last">
			<h2> Quisque scelerisque purus</h2>
			<p>Phasellus diam sapien, fermentum a eleifend non, luctus non augue. Quisque scelerisque purus quis eros sollicitudin gravida. Aliquam erat volutpat. Donec a sem consequat tortor posuere dignissim sit amet at ipsum.</p>
			<a class="more" href="#">read more</a>
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