<%@ page import="project.*" %>
<html>
<head>
<title>Login</title>

<!--  jsp:include page = "header.html"/-->
</head>
<body>

<!-- ################################# body ###################################### -->

<form  id="login_bt"  name = "login" method="post" action="login_page" >
<!--  form  id="login" method="get" action="Run_login"-->
<!--%= login_page.bt_click("login_bt")%-->
<p align = "center">Username : <input type = "text" id = "username" name= "username" title = "please fill your username"size = "30"></p>
<p align = "center">Password : <input type = "password" id = "password" name = "password" title = "please fill your password"size = "30"></p>


<!-- ################################# login button ###################################### -->

<p align = "center"> <input type="submit"  name = "bt"  value="Login" id = "submit"  />

<!--/form-->

<!-- ################################# register button ###################################### -->

<!--form  id="register_bt" method="post" action="login_page"-->
<input type="submit"  name = "bt"  value="Register" id = "submit"  /></p>
</form>





<!-- ################################# body ###################################### -->



</body>
<!--jsp:include page = "footer.jsp"/-->

</html>