<!DOCTYPE html>
<html>
<title>Main Page</title>
<body>
<!-- ----------- table------------------ -->
<table border="2">
		
	<tr>
		<th>ID</th>
		<th>Name</th>
		<!-- th>Date</th>	
		<th>BY</th-->		
	</tr>	

	<!--tr>
		<  td>${doc_id}</td> 
		<td>${doc_name}</td> 
		<td>${doc_date}</td> 
		<td>${doc_by}</td> 
	</tr>
    <tr>
		<td>asfsa</td> 
		<td>asfsasa</td> 
		<td>asfsafsav</td> 
		<td>sdge</td> 
	</tr-->
	
	
	<tr>
		<td id = "table"></td>
		<td id = "table2"></td>
	</tr>
	
	
	

    
</table>

<script>
var cars = ["BMW", "Volvo", "Saab", "Ford", "Fiat", "Audi"];
var text = "";
var animal = ["cat", "dog", "ant", "bird", "monkey", "snake"];
var text2 = "";
var i;

for (i = 0; i < cars.length; i++) {
    text += cars[i] + "<br>";
}

for (i = 0; i < animal.length; i++) {
    text2 += animal[i] + "<br>";
}

document.getElementById("table").innerHTML = text;
document.getElementById("table2").innerHTML = text2;

</script>
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



