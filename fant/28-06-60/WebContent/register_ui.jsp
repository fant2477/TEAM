<!-- <%@ page import="project.*" %> -->
<html>
<head>
<title>Running Document</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="./register_ui_css.css">

</head>
<body>


<form id="register" name = "register" method="post" action="register_page" >

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


                              <li><p><input type="submit" name="bt" value="Login" class="btn-link" id = "submit"></p></li>

                              <li class="active"><a href="#">Register <span class="sr-only">(current)</span></a></li>

                        </ul>

                  </div>

            </div><!-- /.container-fluid -->

      </nav>

<!-- ============================================ end of header tab ============================================ -->



      </br></br></br></br>

      <div class="container">


            <div class="form-inline">
                  <!-- <p align = "center"> -->
                  <div class = "form-group">
                  <p>Name :          <input type= "text" id="firstname" name="firstname" class="form-inline form-control" title ="please enter your firstname" size="42" value = "${firstname}"/><small id = "small">${firstname_check}</small></p>
                  </div>
                  <div class = "form-group">
                  <p>Lastname :      <input type=  "text" id="lastname" name="lastname" class="form-inline form-control" title ="please enter your lastname" size="38" value = "${lastname}"/><small id = "small">${lastname_check}</small></p>
                  </div>
                  <div class = "form-group">
                  <p>Username  :     <input type = "text" id = "username" name= "username" class="form-inline form-control" title = "please fill your username" size = "37" value = "${username}"><small id = "small">${username_check}</small></p>
                  </div>
                  <div class = "form-group">
                  <p>Password  :     <input type = "password" id = "password" name = "password" class="form-inline form-control" title = "please fill your password" size = "37" value = "${password}"><small id = "small">${password_check}</small></p>
                  </div>
                  <div class = "form-group">
                  <p>Confirm Password  :     <input type = "password" id = "confirm_password" name = "confirm_password" class="form-inline form-control" title = "please enter your password again" size = "25" value = "${password}"><small id = "small">${confirm_password}</small></p>
                  </div>
                  <div class = "form-group">
                  <!-- Business Groups: combobox-->

                  <p>Business Groups  :    <select id="business_group" name = "business_group" class="form-inline form-control">
                      <option value="tech">Technology</option>
                      <option value="admin">Administration</option>
                      <option value="biology">Biology</option>
                      <option value="science">Science</option>
                      <option value="tech">Technology</option>
                      <option value="admin">Administration</option>
                      <option value="biology">Biology</option>
                      <option value="science">Science</option>
                      <option value="tech">Technology</option>
                      <option value="admin">Administration</option>
                      <option value="biology">Biology</option>
                      <option value="science">Science</option>
                      <option value="tech">Technology</option>
                      <option value="admin">Administration</option>
                      <option value="biology">Biology</option>
                      <option value="science">Science</option>
                      <option value="tech">Technology</option>
                      <option value="admin">Administration</option>
                      <option value="biology">Biology</option>
                      <option value="science">Science</option>
                      <option value="tech">Technology</option>
                      <option value="admin">Administration</option>
                      <option value="biology">Biology</option>
                      <option value="science">Science</option>
                      <option value="tech">Technology</option>
                      <option value="admin">Administration</option>
                      <option value="biology">Biology</option>
                      <option value="science">Science</option>
                      <option value="tech">Technology</option>
                      <option value="admin">Administration</option>
                      <option value="biology">Biology</option>
                      <option value="science">Science</option>
                      <option value="tech">Technology</option>
                      <option value="admin">Administration</option>
                      <option value="biology">Biology</option>
                      <option value="science">Science</option>
                  </select></p></div>
                  <br/>

                  <!-- ################################# Register button ###################################### $("#form_id").valid();-->


                  <input type="submit" class="btn btn-default btn-lg bt" name = "bt"  value="Register" id = "submit"  />

				  <h1>${register_done}</h1>
					<%-- <script>
					function validateForm() {
					    if ("${firstname_check}" == "OK" ) {
					        alert("Name must be filled out"+"${firstname_check}");}
					    alert("2"+"${firstname_check}");


					}

					</script> --%>

            </div>

            <!-- </form> -->

      </div>


</form>


</body>
<!--jsp:include page = "footer.jsp"/-->

</html>
