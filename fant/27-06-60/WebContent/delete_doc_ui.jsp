<!-- <%@ page import="project.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import="java.util.List"%> -->
<html>
<head>
<title>Running Document</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" href="./delete_doc_ui_css.css">

</head>
<body>


<form id="delete_doc" method="post" action="delete_doc_page">

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

                              <li class="active"><a href="#">Delete Page <span class="sr-only">(current)</span></a></li>



                        </ul>



                        <ul class="nav navbar-nav navbar-right">

                              <li><p><input type="submit" name="bt" value="Log Out" class="btn-link" id = "submit"></p></li>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                              <button id="User_info" class="btn btn-default btn-link" type="button"><i class="fa fa-user-circle-o"  onclick="alert('Please enter only Filename or Document ID.');"></i></button>


                        </ul>

                  </div>

            </div><!-- /.container-fluid -->

      </nav>

<!-- ============================================ end of header tab ============================================ -->


</br></br> <h1>Delete Document</h1> </br></br>

<div class="container">


      <div class="form-inline">

<!-- <%-- ========================search group======================== --%> -->
            <div class="input-group">
                <input type="text" id="search_input" class="form-control" placeholder="Delete file..." aria-label="Delete file..." name= "doc_name" value = "${doc_id }">
                <div class="input-group-btn">
                  <!-- Buttons -->
                    <button id="search_info" class="btn btn-default" type="button"><i class="glyphicon glyphicon-info-sign" style="font-size:20px" onclick="alert('Please enter only Filename or Document ID.');"></i></button>
                    <input type="submit" class="btn btn-default btn-lg bt" name = "bt"  value="Delete" id = "delete_bt"  />

                </div>
            </div>

      </div>



</div>

</form>
</body>
</html>
