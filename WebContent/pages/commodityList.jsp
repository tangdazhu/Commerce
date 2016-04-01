
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">

<title>Commerce</title>

<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/signin.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="assets/js/ie-emulation-modes-warning.js"></script>
<script src="js/jquery-2.2.2.min.js"></script>
<script src="js/jquery.bootpag.min.js"></script>
<script src="js/bootstrap.min.js"></script>


<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

	<div class="container" id="content">

		<form class="form-signin" action="">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Description</th>
						<th>Category</th>
					</tr>
				</thead>
				<tbody id="page-result">
				
				<!-- 
					<c:forEach var="entry" items="${commodityList}" varStatus="count">
						<tr>
							<td>${count.count}</td>
							<td>${entry.name}</td>
							<td>${entry.description}</td>
							<td>${entry.categoryName}</td>
						</tr>
					</c:forEach>
 -->
				</tbody>
			</table>
			
		</form>

	</div>
<div id="page-selection"></div>	
    
    <script>
    var showData = $('#page-result');
    var pageSize=${pageSize};

    $( window ).load(function() {
      	$.ajax({
 	       url: "http://localhost:8080/Commerce/commodityList.json?pageNumber=1&pageSize="+pageSize,
 	      }).done(function(data) { 	    	  
 	     	console.log("test="+data);
 	        	var datalist=data.commodityList;
 	        	showData.empty();
 	        	var content;
 	        	for (var i = 0, len = datalist.length; i < len; i++) {
 	        		 content = content + '<tr><td>'+datalist[i].id + "</td>"+'<td>'+datalist[i].name + "</td>"+'<td>'+datalist[i].description + "</td>"+'<td>'+datalist[i].categoryName + "</td>"+ '</tr>';                
 	            }  	        
 	        	    	        
 	           showData.append(content);
 	        	    	  
 	        	    	    });
      	});
    
    // init bootpag
        $('#page-selection').bootpag({
            total:${totalPage},
            page: 1,
            maxVisible: 5,
            leaps: true,
            firstLastUse: true,
            first: '←',
            last: '→',
            wrapClass: 'pagination',
            activeClass: 'active',
            disabledClass: 'disabled',
            nextClass: 'next',
            prevClass: 'prev',
            lastClass: 'last',
            firstClass: 'first'
        }).on("page", function(event, /* page number here */ num){

        	$.ajax({
        	       url: "http://localhost:8080/Commerce/commodityList.json?pageNumber="+num+"&pageSize="+pageSize ,
        	      }).done(function(data) {
    
        	
        	var datalist=data.commodityList;
        	showData.empty();
        	var content;
        	for (var i = 0, len = datalist.length; i < len; i++) {
        		 content = content + '<tr><td>'+datalist[i].id + "</td>"+'<td>'+datalist[i].name + "</td>"+'<td>'+datalist[i].description + "</td>"+'<td>'+datalist[i].categoryName + "</td>"+ '</tr>';                
            }  	        
        	    	        
           showData.append(content);
        	    	  
        	    	    });
        	      });
     
    </script>
	<!-- /container -->


	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
