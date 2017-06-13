
<html>
<head>

<title>Register</title>
</head>
<body>


<!-- ############################################## form change page + function ############################################### -->

<form id="login" method="post" action="register_page">

<!-- ############################################## input ####################################################### -->

<!-- name: textbox -->
<p align = "center">Name : <input type="text" id="firstname" name="firstname" title ="please enter your firstname" size="30" />
${firstname_check}</p>


<p align = "center">LastName : <input type="text" id="lastname" name="lastname" title ="please enter your lastname" size="30" /><br></br>
${lastname_check}</p>


<p align = "center">Username : <input type="text" id="username" name="username" title ="please enter your username" size="30" />
${username_check}</p>


<p align = "center">Password : <input type="password" id="password" name="password" title ="please enter your password" size="30" />
${password_check}</p>



<p align = "center">Confirm Password : <input type="password" id="confirm_password" name="confirm_password" title ="please enter your password again" size="30" />
${confirm_password_check}</p><br></br>



<!-- ############################################## button ############################################################ -->

<!--  #### register #### -->
<!--  input type="submit" id="submit" /-->
<p align = "center"><input  type="submit"  name = "bt"  value="Register" id = "submit"  />

<!--  #### back to login #### -->

<input type="submit"  name = "bt"  value="Back" id = "submit"  /></p>


<br/>${register_done}


</form>


</body>
</html>