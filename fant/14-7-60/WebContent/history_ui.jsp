<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import="java.util.List"%>
<html>
<head>
<title>Running Document</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<link rel="stylesheet" href="./history_ui_css.css">

</head>
<body>


<form id="main" method="post" action="history_page">

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

                              <li><p><input type="submit" name="bt" value="Main Page" class="btn-link" id = "submit"></p></li>

                              <li class="active"><a href="#">History Page <span class="sr-only">(current)</span></a></li>

                              <li><p><input type="submit" name="bt" value="Add Page" class="btn-link" id = "submit"></p></li>

                              <li><p><input type="submit" name="bt" value="Delete Page" class="btn-link" id = "submit"></p></li>



                        </ul>



                        <ul class="nav navbar-nav navbar-right">
                               <!-- <a class="navbar-brand" href="#">
                                <img alt="User" src="./login_img.png">
                              </a>  -->

                              <li><p><input type="submit" name="bt" value="Log Out" class="btn-link" id = "submit"></p></li>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                              <%-- <button id="User_info" class="btn btn-default btn-link" type="button"><i class="fa fa-user-circle-o"  onclick="alert('Please enter only Filename or Document ID.');"></i></button> --%>
                              <%-- <li><p><input type="submit" name="bt" value="User_info" class="btn-link fa fa-user-circle-o" id = "submit"></p></li> --%>
                              <%-- <li><p><input type="submit" name="bt"  class="btn-link fa fa-user-circle-o" id = "eiei"></p></li> --%>
                              <%-- <li class="active"><a href="#"><i class="fa fa-user-circle-o" id="user_info"></i> <span class="sr-only">(current)</span></a></li> --%>
                              <%-- <li class="btn-link"><a href="#"><i class="fa fa-user-circle-o" id="user_info"></i></a></li> --%>
                              <button type="submit" class="btn-link" name="bt" value="User_info" id="User_info"><i class="fa fa-user-circle-o"></i> </button>


                        </ul>

                  </div>

            </div><!-- /.container-fluid -->

      </nav>

<!-- ============================================ end of header tab ============================================ -->



      </br></br> <h1>Log Event</h1> </br></br>

      <div class="container">


            <div class="form-inline">
                  <!-- <p align = "center"> -->
                   <!-- <h3>Document Details</h3>
                  <hr size="4" color="gray"/>  -->

 <!-- ========================search group======================== -->
                  <div class="input-group">
                      <input type="text" id="search_input" name="search_input" class="form-control" placeholder="Search for..." aria-label="Search for...">
                      <div class="input-group-btn">
                        <!-- Buttons -->
                          <button id="search_info" class="btn btn-default" type="button"><i class="glyphicon glyphicon-info-sign" style="font-size:20px" onclick="alert('Please enter only Filename or Document ID.');"></i></button>

                          <button type="submit" name="bt" class="btn btn-default" id="search_bt" value="search_bt"><i class="glyphicon glyphicon-search" style="font-size:20px" ></i></button>
                      </div>
                  </div>

<!-- ======================== start of table ======================== -->
</br></br>
                  <div class="w3-container">
                      <table class="w3-table-all co">
                        		<tr class="w3-red">
                        			<td class="w3-rgb(255, 102, 102)">Num</td>
                        			<td>Date</td>
                        			<td>Event</td>

                        		</tr>
                        	<c:forEach items="${lg}" var="doc" varStatus="pstatus">
                        		<tr>
                        			<td class="w3-rgb(255, 102, 102)">${(pstatus.index)+1}</td>
                        			<c:forEach var = "i" begin="0" end="1">
                        				<td>${doc[i]}</td>



                        			</c:forEach>
                        		</tr>
                        	</c:forEach>
                        </table>


                        </br>

                  </div>
 <!-- ======================== end of table ========================  -->

            <!-- </form> -->

<!-- ========================start of paging========================  -->
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

                               <!-- <li><a href="#" class ="paging">4</a></li>

                              <li><a href="#" class ="paging">5</a></li> -->

                              <li><a href="#" class ="paging"> > </a></li>

                              <li>
                                  <a href="#" class ="paging" aria-label="Next"><span aria-hidden="true">&raquo;</span></a>
                              </li>

                          </ul>
                      </div>
                  </nav>



<!-- ========================end of paging======================== -->

            </div>

      </div>


</form>


</body>
<!--jsp:include page = "footer.jsp"/-->

</html>
