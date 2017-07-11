<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import="java.util.*"%>
<%@ page import = "java.io.*"%>
<%@ page import= "project_db.*" %>



<html>
   <head>
      <title>Tag Example</title>
      
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="./main_ui_css.css">
   </head>

   <body>
   				<nav  aria-label="Page navigation">
   					<div class="text-center">
				         <ul class="pagination pagination-sm ">
						      <c:forEach var = "i" begin = "1" end = "5">
						         <li><p><input type="submit" name="bt" value="${i}" class="link_detail" id = "submit"></p></li>
						                                    
						      </c:forEach>
      
      					</ul>
                      </div>
                    </div>
                 </nav>
                  
      
      
   </body>
</html>