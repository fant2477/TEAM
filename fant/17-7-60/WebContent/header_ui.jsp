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

<link rel="stylesheet" href="./header_ui_css.css">




</head>
<body>


<form id="detail" method="post" action="header_page">

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

                             <!-- <li class="active"><a href="#">Add Page <span class="sr-only">(current)</span></a></li> -->
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
                          <button type="submit" class="btn-link" name="bt" value="User_info" id="User_info"><i class="fa fa-user-circle-o"></i> </button>


                       </ul>

                 </div>

           </div><!-- /.container-fluid -->

     </nav>
</br>
<!-- ============================================ end of header tab ============================================ -->
</br> <h1>Document Header Details ${head_id}</h1> </br></br>
<div class="container">


		<div class="form-inline">
                  <!-- <p align = "center"> -->
                  <%-- <h3>Document Details</h3>
                  <hr size="4" color="gray"/> --%>

<%-- ========================search group======================== --%>
                  <div class="input-group">
                      <input type="text" id="search_input" name="search_input" value="${search_input}" class="form-control" placeholder="Search for..." aria-label="Search for...">
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
                        			<th>Doc Name</th>
                        			<th>Created by</th>
                        			<th>Last Modified</th>
                        		</tr>

                        	<c:forEach items="${doclist}" var="doc" varStatus="pstatus">
                        		<tr>

                        			<%UserManager um = new UserManager();
                        			Time ti = new Time();%>
                        			<c:set var="doc1" value="${doc}"></c:set>

                        			<td>${(pstatus.index)+1}</td>

                        			<td><p><input type="submit" name="bt" value="${doc.getDoc_ID()}" class="link_detail" id = "submit"></p></td>
                        			<td>${doc.getDoc_name()}</td>

                        			<td><% out.print(um.getUsername( ( (DocumentDetail)pageContext.getAttribute("doc1") ).getUser_ID_created()) );%></td>
                        			<td><% out.print(ti.datetoReadableString( ( (DocumentDetail)pageContext.getAttribute("doc1") ).getDate_modified()) );%></td>



                        		</tr>
                        	</c:forEach>

                        </table>


                        </br>

                  </div>
                <%-- </div> --%>
<%-- ======================== end of table ======================== --%>
<%-- ========================start of paging======================== --%>
                  <%-- <nav  aria-label="Page navigation">
                      <div class="text-center">
                          <ul class="pagination pagination-sm ">

                              <li>
                                  <a href="#" class ="paging disabled" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
                              </li>

                              <li><a href="#" class ="paging"> < </a></li>

                              <li><a href="#" class ="paging">1</a></li>

                              <li class="active"><a href="#" class ="paging">2<span class="sr-only">(current)</span></a></li>

                              <li><a href="#" class ="paging">3</a></li>--%>

                              <%-- <li><a href="#" class ="paging">4</a></li>

                              <li><a href="#" class ="paging">5</a></li> --%>

                              <%--<li><a href="#" class ="paging"> > </a></li>

                              <li>
                                  <a href="#" class ="paging" aria-label="Next"><span aria-hidden="true">&raquo;</span></a>
                              </li>

                          </ul>
                      </div>
                  </nav> --%>






				<%-- <nav  aria-label="Page navigation" >
                      <div class="text-center" >

                       		<ul class="pagination pagination-sm " >

								<c:forEach var = "i"  begin = "1" end = "${page_total}">

						         <li><input type="submit" name="pg" value="${i}" class=" paging" id = "submit"></li>

						      </c:forEach>

							</ul>
                      </div>
                  </nav> --%>



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
<!-- ===============start of  file button=============== -->

        <div class="box form-group">
            <input type="file"  name="file-6[]" id="file-6" class="inputfile inputfile-5" data-multiple-caption="{count} files selected" multiple />
            <label for="file-6"><figure><svg xmlns="http://www.w3.org/2000/svg" width="20" height="17" viewBox="0 0 20 17"><path d="M10 0l-5.2 4.9h3.3v5.1h3.8v-5.1h3.3l-5.2-4.9zm9.3 11.5l-3.2-2.1h-2l3.4 2.6h-3.5c-.1 0-.2.1-.2.1l-.8 2.3h-6l-.8-2.2c-.1-.1-.1-.2-.2-.2h-3.6l3.4-2.6h-2l-3.2 2.1c-.4.3-.7 1-.6 1.5l.6 3.1c.1.5.7.9 1.2.9h16.3c.6 0 1.1-.4 1.3-.9l.6-3.1c.1-.5-.2-1.2-.7-1.5z"/></svg></figure> <span></span></label>
        </div>


        <input type="submit" class="btn btn-default btn-lg bt form-group" name = "bt"  value="Add files" id = "addfile" />
        <input type="submit" class="btn btn-default btn-lg bt form-group" name = "bt"  value="Delete Document" id = "delete_doc" /></br>


<!-- ===============start of script for file button=============== -->
       <script>
           var inputs = document.querySelectorAll( '.inputfile' );
           Array.prototype.forEach.call( inputs, function( input )
           {
             var label	 = input.nextElementSibling,
               labelVal = label.innerHTML;

             input.addEventListener( 'change', function( e )
             {
               var fileName = '';
               if( this.files && this.files.length > 1 )
                 fileName = ( this.getAttribute( 'data-multiple-caption' ) || '' ).replace( '{count}', this.files.length );
               else
                 fileName = e.target.value.split( '\\' ).pop();

               if( fileName )
                 label.querySelector( 'span' ).innerHTML = fileName;
               else
                 label.innerHTML = labelVal;
             });
           });
       </script>
<!-- ===============end of script for file button=============== -->
</br>




</div>


</form>

<!-- ===============start of script for file button=============== -->

<!-- ===============end of script for file button=============== -->

</body>
<!--jsp:include page = "footer.jsp"/-->

</html>
