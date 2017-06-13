<!DOCTYPE html>
<html>
<title>History</title>
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
var cars = ["this", "is", "a", "history", "ui", "page"];
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

<form  id="history" method="post" action="history_page" >


<input type="submit" name = "bt" value="Back" >

</form>

</body>
</html>



