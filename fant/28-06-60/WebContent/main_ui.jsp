<!--  %@ page import="project.*" %-->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import="java.util.List"%>
<html>
<head>
<title>Running Document</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" href="./main_ui_css.css">

</head>
<body>


<form id="main" method="post" action="main_page">

<!-- ============================================ start of header tab ============================================ -->

      <nav class="navbar navbar-inverse navbar-static-top">

            <div class="container-fluid">



                  <!-- Brand and toggle get grouped for better mobile display -->
                  <div class="navbar-header">

                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">

                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>

                        </button>

                        <h4 class="navbar-text">Running Document</h4>
                  </div>




                  <!-- Collect the nav links, forms, and other content for toggling -->
                  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">



                        <ul class="nav navbar-nav">

                              <li class="active"><a href="#">Main Page <span class="sr-only">(current)</span></a></li>

                              <li><p><input type="submit" name="bt" value="History Page" class="btn-link" id = "submit"></p></li>

                              <li><p><input type="submit" name="bt" value="Add Page" class="btn-link" id = "submit"></p></li>

                              <li><p><input type="submit" name="bt" value="Delete Page" class="btn-link" id = "submit"></p></li>



                        </ul>



                        <ul class="nav navbar-nav navbar-right">
                              <%-- <a class="navbar-brand" href="#">
                                <img alt="User" src="./login_img.png">
                              </a> --%>

                              <li><p><input type="submit" name="bt" value="Log Out" class="btn-link" id = "submit"></p></li>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                              <button type="submit" class="btn-link" name="bt" value="User_info" id="User_info"><i class="fa fa-user-circle-o"></i> </button>


                        </ul>

                  </div>

            </div><!-- /.container-fluid -->

      </nav>

<!-- ============================================ end of header tab ============================================ -->



      </br></br> <h1>Document Details</h1> </br></br>

      <div class="container">


            <div class="form-inline">
                  <!-- <p align = "center"> -->
                  <%-- <h3>Document Details</h3>
                  <hr size="4" color="gray"/> --%>

<%-- ========================search group======================== --%>
                  <div class="input-group">
                      <input type="text" id="search_input" class="form-control" placeholder="Search for..." aria-label="Search for...">
                      <div class="input-group-btn">
                        <!-- Buttons -->
                          <button id="search_info" class="btn btn-default" type="button"><i class="glyphicon glyphicon-info-sign" style="font-size:20px" onclick="alert('Please enter only Filename or Document ID.');"></i></button>
                          <button id="search_bt" class="btn btn-default" type="button"><i class="glyphicon glyphicon-search" style="font-size:20px" onclick="alert('Please enter only Filename or Document ID.');"></i></button>

                          <%-- <button id="search_bt" class="btn btn-default" type="button">Go!</button> --%>
                      </div>
                  </div>

<%-- ======================== start of table ======================== --%>
                  <div class="row">
                      <table border="2">
                        		<tr>
                        			<td>Num</td>
                        			<td>ID</td>
                        			<td>File Name</td>
                        			<td>Date</td>
                        			<td>Size</td>
                        			<td>By User</td>
                        			<td>Path</td>
                        			<td>Descriptions</td>
                        			<td>Detail</td>
                        		</tr>
                        	<c:forEach items="${docList}" var="doc" varStatus="pstatus">
                        		<tr>
                        			<td>${(pstatus.index)+1}</td>
                        			<c:forEach var = "i" begin="0" end="7">
                        				<td>${doc[i]}</td>

                        				<c:if test="${i=='0'}">
                        					<td>${doc[i]}</td>

                        					<!--  p id="demo" value = "${doc[i]}"></p-->

                        				</c:if>

                        				<c:if test="${i=='6'}">
                        					<td><input type="submit"  name = "bt" value="val_bt" ></td>
                        				</c:if>

                        			</c:forEach>
                        		</tr>
                        	</c:forEach>
                        </table>


                        </br>

                  </div>
<%-- ======================== end of table ======================== --%>

            <!-- </form> -->

<%-- ========================start of paging======================== --%>
                  <nav  aria-label="Page navigation">
                      <div class="text-center">
                          <ul class="pagination pagination-sm ">

                              <li>
                                  <a href="#" class ="paging disabled" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
                              </li>

                              <li><a href="#" class ="paging"> < </a></li>

                              <li><a href="#" class ="paging">1</a></li>

                              <li class="active"><a href="#" class ="paging">2<span class="sr-only">(current)</span></a></li>

                              <li><a href="#" class ="paging">3</a></li>

                              <%-- <li><a href="#" class ="paging">4</a></li>

                              <li><a href="#" class ="paging">5</a></li> --%>

                              <li><a href="#" class ="paging"> > </a></li>

                              <li>
                                  <a href="#" class ="paging" aria-label="Next"><span aria-hidden="true">&raquo;</span></a>
                              </li>

                          </ul>
                      </div>
                  </nav>



<%-- ========================end of paging======================== --%>

            </div>

      </div>


</form>


</body>
<!--jsp:include page = "footer.jsp"/-->

</html>
