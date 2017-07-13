<!DOCTYPE html>
<%@ page import="projects.*" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import="java.util.*"%>
<%@ page import = "java.io.*"%>
<%@ page import= "project_db.*" %>
<html>
  <head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <link rel="stylesheet" href="./column.css">
    <title></title>
  </head>
  <body>


<form id="user_history_page" method="post" action="user_history_page">
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

                            <li><p><input type="submit" name="bt" value="History Page" class="btn-link" id = "submit"></p></li>

                            <li><p><input type="submit" name="bt" value="Add Page" class="btn-link" id = "submit"></p></li>

                            <li><p><input type="submit" name="bt" value="Delete Page" class="btn-link" id = "submit"></p></li>
                            <%-- <li class="active"><a href="#">Delete Page <span class="sr-only">(current)</span></a></li> --%>



                      </ul>



                      <ul class="nav navbar-nav navbar-right">

                            <li><p><input type="submit" name="bt" value="Log Out" class="btn-link" id = "submit"></p></li>
                      </ul>
                      <ul class="nav navbar-nav navbar-right">
                            <%-- <button id="User_info" class="btn btn-default btn-link" type="button"><i class="fa fa-user-circle-o"  onclick="alert('Please enter only Filename or Document ID.');"></i></button> --%>
                            <li class="active"><a href="#"><i class="fa fa-user-circle-o" id="User_info"></i> <span class="sr-only">(current)</span></a></li>


                      </ul>

                </div>

          </div><!-- /.container-fluid -->

    </nav>

<!-- ============================================ end of header tab ============================================ -->

</br></br>
    <div class="container">      <!-- big container -->

        <div class="col-xs-2">     <!-- left column -->

              <div class="btn-group-vertical btn-block" role="group" aria-label="..." id="div_top">   <!-- top in left column -->
                    <input type="submit" class="btn btn-default btn-lg bt" name = "bt"  value="Profile" id = "profile_bt"  />
                    <input type="button" class="btn btn-default btn-lg bt" name = "bt"  value="My History" id = "history_bt"  />
              </div>

              <!-- <div class="btn-group-vertical btn-block" role="group" aria-label="..." id="div_bottom">    -->
                <!-- bottom in left column -->
                    <!-- <input type="button" class="btn btn-default btn-lg bt" name = "bt"  value="My History" id = "history_bt3"  />
              </div> -->


        </div>


        <div class="col-xs-3 ">     <!-- right column -->
          <div class="form-inline">
<%-- ========================search group======================== --%>
                <div class="input-group">
                    <input type="text" id="search_input" name="search_input" value="${search_input}" class="form-control" placeholder="Search for..." aria-label="Search for...">
                    <div class="input-group-btn">
                      <!-- Buttons -->
                        <button id="search_info" class="btn btn-default" type="button"><i class="glyphicon glyphicon-info-sign" style="font-size:20px" onclick="alert('Please enter only Filename or Document ID.');"></i></button>
                        <button type="submit" name="bt" class="btn btn-default" id="search_bt" value="search_bt"><i class="glyphicon glyphicon-search" style="font-size:20px" ></i></button>
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
                        <nav  aria-label="Page navigation" >
                      <div class="text-center" >
                          
                       		<ul class="pagination pagination-sm " >

								<c:forEach var = "i"  begin = "1" end = "${page_total}">
								
						         <li><input type="submit" name="pg" value="${i}" class=" paging" id = "submit"></li>
						                                    
						      </c:forEach>

							</ul>
                      </div>
                  </nav>



<%-- ========================end of paging======================== --%>


        </div>

    </div>
    </br></br></br></br>
<!-- ============================================ end of container ============================================ -->
</form>
</body>
</html>
