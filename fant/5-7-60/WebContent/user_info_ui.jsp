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

<link rel="stylesheet" href="./user_info_ui_css.css">

</head>
<body>


<form id="delete_doc" method="post" action="user_info_page">

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

<div class="container">


      <div class="form-inline">


        <div class="row">

              <div class="col-xs-2">

                  <div class="btn-group-vertical btn-block" role="group" aria-label="..." id="div_top">

                    <!-- <input type="submit" class="btn btn-default btn-lg bt" name = "bt"  value="Profile" id = "profile_bt"  /> -->
                    <!-- <input type="submit" class="btn btn-default btn-lg bt" name = "bt"  value="My History2" id = "history_bt2"  /> -->

                            <input type="button" class="btn btn-default btn-lg bt" name = "bt"  value="Profile" id = "pf"  />

                            <%-- <li class="active"><a href="#">Delete Page <span class="sr-only">(current)</span></a></li> --%>

                            <input type="submit" class="btn btn-default btn-lg bt" name = "bt"  value="My History" id = "history_bt3"  />

                  </div>

              </div>
              <div class="col-xs-3">

                    <div class="form-inline">
                          <!-- <p align = "center"> -->
                          <div class = "form-group">
                          <p>Name :          <input type= "text" id="firstname" name="firstname" class="form-inline form-control" title ="please enter your firstname" size="42.7" value = "${firstname}"/><small id = "small">${firstname_check}</small></p>
                          </div>
                          <div class = "form-group">
                          <p>Lastname :      <input type=  "text" id="lastname" name="lastname" class="form-inline form-control" title ="please enter your lastname" size="37" value = "${lastname}"/><small id = "small">${lastname_check}</small></p>
                          </div>
                          <div class = "form-group">
                          <p>Username  :     <input type = "text" id = "username" name= "username" class="form-inline form-control" title = "please fill your username" size = "37" value = "${username}"><small id = "small">${username_check}</small></p>
                          </div>
                          <div class = "form-group">
                          <p>Password  :     <input type = "password" id = "password" name = "password" class="form-inline form-control" title = "please fill your password" size = "37.7" value = "${password}"><small id = "small">${password_check}</small></p>
                          </div>
                          <div class = "form-group">
                          <p>Business group  :     ${business_group} </p></div>
                          
                          
                          </br></br>

                          <!-- ################################# Register button ###################################### $("#form_id").valid();-->


                          <input type="submit" class="btn btn-default btn-lg bt" name = "bt"  value="Save" id = "save"  />

                				  <h1>${save_done}</h1>


                  </div>

                    <!-- </form> -->
            </div>


      </div>



</div>

</form>
</body>
</html>
