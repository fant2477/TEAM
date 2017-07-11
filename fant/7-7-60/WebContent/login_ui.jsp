<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<title>Running Document</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="./login_ui_css.css">

</head>
<body>



<form  name="loginform" method="post" action="login_page">

  <!-- ============================================ start of header tab ============================================ -->

      <nav class="navbar navbar-inverse navbar-static-top">
            <div class="container-fluid ">


                  <div class="navbar-header">

                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <h4 class="navbar-text">Running Document</h4>

                  </div>


                  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

                      <ul class="nav navbar-nav">

                        <li class="active"><a href="#">Login <span class="sr-only">(current)</span></a></li>

                        <li><p><input type="submit" name="bt" value="Register" class="btn-link" id = "submit"></p></li>

                      </ul>

                  </div><!-- /.navbar-collapse -->


            </div>
      </nav>
<!-- ============================================ end of header tab ============================================ -->
      </br></br></br></br></br>

      <div class="container">


            <div class="form-inline">


                              <p>Username  :     <input type = "text" id = "username" name= "username" class="form-inline form-control" title = "please fill your username" size = "37" value = "${username}"><small id = "small">${username_check}</small></p>
                              </br>
                              <p>Password  :     <input type = "password" id = "password" name = "password" class="form-inline form-control" title = "please fill your password" size = "37" value = "${password}"><small id = "small">${password_check}</small></p>



                  <%-- <p>Username  :     <input type = "text" id = "username" name= "username" class="form-inline form-control" title = "please fill your username" size = "37" value = "${username}"><small id = "small">${username_check}</small></p>
                  </br>
                  <p>Password  :     <input type = "password" id = "password" name = "password" class="form-inline form-control" title = "please fill your password" size = "37" value = "${password}"><small id = "small">${password_check}</small></p> --%>

                  <!-- ################################# login button ###################################### -->
                  </br>

                  <input type="submit" class="btn btn-default btn-lg bt" name = "bt"  value="Login" id = "submit"  />

            </div>

            <!-- </form> -->

      </div>

</form>

</body>
<!--jsp:include page = "footer.jsp"/-->

</html>
