<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import="java.util.*"%>
<%@ page import = "java.io.*"%>
<%@ page import= "project_db.*" %>


<html>
<head>
<title>Running Document</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
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



      </br></br> <h1>Document Header</h1> </br></br>

      <div class="container">


            <div class="form-inline">
                  <!-- <p align = "center"> -->
                  <%-- <h3>Document Details</h3>
                  <hr size="4" color="gray"/> --%>

<%-- ========================search group======================== --%>
                  <div class="input-group">
                      <input type="text" id="search_input" name="search_input" value="${search_input}" class="form-control"  placeholder="Search for..." aria-label="Search for...">
                      <div class="input-group-btn">
                        <!-- Buttons -->
                          <button id="search_info" class="btn btn-default" type="button"><i class="glyphicon glyphicon-info-sign" style="font-size:20px" onclick="alert('Please enter only Filename or Document ID.');"></i></button>
                          <button type="submit" name="bt" class="btn btn-default" id="search_bt" value="search_bt"><i class="glyphicon glyphicon-search" style="font-size:20px" ></i></button>
                          <%-- <button type="submit" class="btn-link" name="bt" value="User_info" id="User_info"><i class="fa fa-user-circle-o"></i> </button> --%>

                          <%-- <button id="search_bt" class="btn btn-default" type="button">Go!</button> --%>
                      </div>
                  </div>
			</div>
<%-- ======================== start of table ======================== --%>
</br>
                <%-- <div class="container2"> --%>
                  <div class="row">
                      <table class="table table-striped table-bordered" >
                        		<tr>
                        			<th>Num</th>
                        			<th>ID</th>
                        			<th>Doc Header</th>
                        			<th>Created by</th>
                        			<th>Last Modified</th>
                        			<th>Descriptions</th>
                        		</tr>

                        	<c:forEach items="${doclist}" var="doc" varStatus="pstatus">
                        		<tr>

                        			<%UserManager um = new UserManager();
                        			Time ti = new Time();%>
                        			<c:set var="doc1" value="${doc}"></c:set>

                        			<td>${(pstatus.index)+1}</td>

                        			<td><p><input type="submit" name="bt" value="${doc.getDoc_header_ID()}" class="link_detail" id = "submit"></p></td>
                        			<td>${doc.getDoc_header_subject()}</td>

                        			<td><% out.print(um.getUsername( ( (DocumentHeader)pageContext.getAttribute("doc1") ).getUser_ID_created()) );%></td>
                        			<td><% out.print(ti.datetoReadableString( ( (DocumentHeader)pageContext.getAttribute("doc1") ).getDate_modified()) );%></td>

                        			<td>${doc.getDoc_header_description()}</td>




                        		</tr>
                        	</c:forEach>

                        </table>


                        </br>

                  </div>
                <%-- </div> --%>
<%-- ======================== end of table ======================== --%>

            <!-- </form> -->

<%-- ========================start of paging======================== --%>
                  <%-- <nav  aria-label="Page navigation" >
                      <div class="text-center" >

                       		<ul class="pagination pagination-sm " >

								<c:forEach var = "i"  begin = "1" end = "${page_total}">

						         	<li><input type="submit" name="pg" value="${i}" class=" paging" id = "submit"></li>

						      	</c:forEach>

							 </ul>
                      </div>
                  </nav> --%>



                  <%-- <div class="form-inline">
                    <p>Page</p>
                    <select class="" name="pg">
                      <c:forEach var = "i"  begin = "1" end = "${page_total}">

                          <c:choose>
                                <c:when test="${page}">
                                    <option value="${i}" selected>${i}</option>
                                </c:when>

                                <c:otherwise>
                                    <option value="${i}">${i}</option>
                                </c:otherwise>
                          </c:choose>
                      </c:forEach>

                    </select>

                    <p> / ${page_total}</p>

                  </div> --%>


				          <%-- <button type="submit" class="link_detail" name="bt" value="left_bt" id="left_bt"><i class="glyphicon glyphicon-chevron-right"></i> </button>
                  <input type="number" id="myNumber" value="2">
                  <button type="submit" class="link_detail" name="bt" value="right_bt" id="right_bt"><i class="glyphicon glyphicon-chevron-left"></i> </button>
           --%>



			   <div class="text-center">
  	                <div class="form-inline">
  	                      <%-- <div class="input-group">
  	                          <button type="submit" class="link_detail" ><i class="glyphicon glyphicon-chevron-left" style="font-size:26px;"></i> </button>
  	                      </div> --%>
            						  <div class="input-group">
            						      <p>Page: </p>
            						  </div>

  	                      <div class="input-group">
  	                          <input type="number" name="pg" id="pg"  class="form-control" value="${pg}"  min="1" max="${page_total}"  title="Maximum page is ${page_total}">

                              <span class="input-group-btn">
  	                              <button type="submit" name="pg_go" id="pg_go" class="btn btn-default" >Go</button>
  	                          </span>
  	                      </div>

  	                      <%-- <div class="input-group">
  	                          <button type="submit" class="link_detail"><i class="glyphicon glyphicon-chevron-right" style="font-size:26px;"></i> </button>
  	                      </div> --%>
  	                </div>
                </div>








<%--
                <script>
                function myFunction() {
                    var pg_num = document.getElementById("pg_num").innerHTML.value();
                    document.getElementById("pg_go").innerHTML.value() = pg_num;
                    this.form.submit();
                }
                </script> --%>




                  <%-- <input type="number" id="myNumber" value="2">

                  <button onclick="myFunction()">Try it</button>

                  <p id="demo"></p>

                  <script>
                  function myFunction() {
                      var x = document.getElementById("myNumber").value;
                      document.getElementById("demo").innerHTML = x;
                  }
                  </script>




                  <input type="number" id="myNumber" min="1" max="5">

                  <p>Click the button to display the value of the max attribute of the number field.</p>

                  <button onclick="myFunction()">Try it</button>

                  <p><strong>Note:</strong> input elements with type="number" are not supported in IE 9 and earlier versions.</p>

                  <p id="demo"></p>

                  <script>
                  function myFunction() {
                      var x = document.getElementById("myNumber").max;
                      document.getElementById("demo").innerHTML = x;
                  }
                  </script> --%>



<%-- ========================end of paging======================== --%>



      </div>

      </br></br>



</form>


</body>
<!--jsp:include page = "footer.jsp"/-->

</html>
