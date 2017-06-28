<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.commerce.util.Constants"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@page import="java.net.*"%>
<%@page import="java.util.List"%>
<%
	int pageSize = Constants.getPageSize();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">

<title>Commerce</title>

<!-- Bootstrap core CSS -->
<link href="/Commerce/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="/Commerce/css/signin.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="/Commerce/assets/js/ie-emulation-modes-warning.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

	<div class="container">

		<form:form class="form-signin" action="/Commerce/commodityList"
			method="POST" modelAttribute="userForm">
			<h2 class="form-signin-heading">Please sign in</h2>
			<label for="inputEmail" class="sr-only">Email address</label>

			<input type="email" name="email" class="form-control"
				placeholder="Email address" required autofocus>

			<label for="inputPassword" class="sr-only">Password</label>

			<input type="password" name="password" class="form-control"
				placeholder="Password" required>
<!-- 
			<input name="answer" />
 -->

			<input type="hidden" name="pageNumber" value="1">


			<input type="hidden" name="pageSize" value="<%=pageSize%>">


			<div class="checkbox">
				<label> <input type="checkbox" value="remember-me">
					Remember me
				</label>
			</div>
	<!-- 
			<img src="<c:url value="simpleCaptcha.png" />">
			 -->
			<br />

			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
				in</button>
			<!-- 
				<FONT size=3 COLOR="#0000FF"> Instance <%=InetAddress.getLocalHost()%>
		</FONT> <FONT size=3 COLOR="#CC0000"> <br /> Session Id : <%=request.getSession().getId()%>
			<br /> Is it New Session : <%=request.getSession().isNew()%><br />
			Session Creation Date : <%=new Date(request.getSession().getCreationTime())%><br />
			Session Access Date : <%=new Date(request.getSession().getLastAccessedTime())%><br />
			<br />
		</FONT>
		 -->
		</form:form>
		
	</div>
	<!-- /container -->


	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
