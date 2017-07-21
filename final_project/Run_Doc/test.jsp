<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!--  <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>-->
<%@ page import="java.util.*"%>
<%@ page import = "java.io.*"%>

<html>
   <head>
      <title> Tag Example</title>
   </head>

   <body>


      <c:set var="i" value="10"></c:set>
      <c:forEach var = "i" begin = "i" end = "0">
         Item <c:out value = "${i}"/><p>
      </c:forEach>


   </body>
</html>
