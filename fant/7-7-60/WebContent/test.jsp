<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<% List eList = (List)session.getAttribute("empList");%>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Main Page</title>
<body>

<c:out value="Hello"></c:out>
<!-- ----------- table------------------ -->
<table border="2">
		
	<tr>
		<th>ID</th>
		<!--th>Name</th>
		< th>Date</th>	
		<th>BY</th-->		
	</tr>	

	<tr>
		<td>${doc_name}</td> 
		<c:forEach var = "i" begin = "1" end = "5">
         Item <c:out value = "${i}"/><p>
        </c:forEach>
		<!--td>${doc_name}</td> 
		<td>${doc_date}</td> 
		<td>${doc_by}</td> 
	</tr>
    <tr>
		<td>asfsa</td> 
		<td>asfsasa</td> 
		<td>asfsafsav</td> 
		<td>sdge</td> 
	</tr>
	
	
	<tr>
		<td id = "table"></td>
		<td id = "table2"></td>
	</tr>
	

    
</table>

<script>
//var cars = ["BMW", "Volvo", "Saab", "Ford", "Fiat", "Audi"];
${doc_name};
System.out.println("doc value[0] in : "+cars);
var text = "";
var animal = ["cat", "dog", "ant", "bird", "monkey", "snake"];
var text2 = "";
var i;
//(String)${doc_name}(String)
for (i = 0; i < cars.length; i++) {
    text += cars[i] + "<br>";
}

for (i = 0; i < animal.length; i++) {
    text2 += animal[i] + "<br>";
}

document.getElementById("table").innerHTML = text;
document.getElementById("table2").innerHTML = text2;

</script-->
<!-- ----------- table------------------ -->
</br>

<form  id="receive" method="post" action="main_page" >

<input type="submit" name = "bt" value="Add new document" >
<input type="submit" name = "bt" value="Delete document" >

</br>
<input type="submit" name = "bt" value="History" >

</br></br>
<input type="submit" name = "bt" value="Log out" >

</form>

</body>
</html>



