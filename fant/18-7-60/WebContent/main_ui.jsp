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
                        			<th align="center">Num</th>
                        			<th align="center">ID</th>
                        			<th align="center">Doc Header</th>
                        			<th>Descriptions</th>
                              <th align="center">File</th>
                        			<th align="center">Created by</th>
                        			<th align="center">Business Group</th>
                        			<th align="center">Last Modified</th>
                              <th align="center">Select</th>

                        		</tr>

                        	<c:forEach items="${doclist}" var="doc" varStatus="pstatus">
                        		<tr>

                        			<%UserManager um = new UserManager();
                        			Time ti = new Time();%>
                        			<c:set var="doc1" value="${doc}"></c:set>


                              <td align="center">${(pstatus.index)+(start_pg)}</td>
                        			<%-- <td>${(pstatus.index)+1}</td> --%>


                        			<td align="center"><p><input type="submit" name="bt" value="${doc.getDoc_header_ID()}" class="link_detail" id = "submit"></p></td>
                        			<td align="center">${doc.getDoc_header_subject()}</td>
                        			<td>${doc.getDoc_header_description()}</td>

                              <td align="center"><% out.print( ( (DocumentHeader)pageContext.getAttribute("doc1") ).size() );%></td>

                        			<td align="center"><% out.print(um.getUsername( ( (DocumentHeader)pageContext.getAttribute("doc1") ).getUser_ID_created()) );%></td>
                        			<td align="center"><% out.print(um.getBusinessGroup( ( (DocumentHeader)pageContext.getAttribute("doc1") ).getUser_ID_created()) );%></td>
                        			<td><% out.print(ti.datetoReadableString( ( (DocumentHeader)pageContext.getAttribute("doc1") ).getDate_modified()) );%></td>


                              <td align="center"><input type="checkbox" name="head_cb" value="${doc.getDoc_header_ID()}" ></td>
                              <%-- <td><input type="submit" name="bt" value="Delete" class="link_detail" id = "${doc.getDoc_header_ID()}"></td> --%>
                        			<%-- </td> --%>






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



<%-- ========================end of paging======================== --%>
      <input type="submit" class="btn btn-default btn-lg bt" name = "bt"  value="Delete" id = "delete_bt"  />



      </div>


</form>


</body>
<!--jsp:include page = "footer.jsp"/-->

</html>