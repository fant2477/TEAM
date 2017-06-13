<html>
<head>
<title>Detail</title>
</head>
<body>

<form  id="detail" method="post" action="detail_page" >

<p align = "center">Document Name : <input type = "text"name= "doc_name" size = "30" value = "${doc_name}" > </p>
<p align = "center">Document code : <input type = "text"name= "doc_code" size = "30" value = "${doc_code}"  > </p>
<p align = "center">Document add date : <input type = "text"name= "add_date" size = "30" value = "${add_date}" > </p>
<p align = "center">Document add by : <input type = "text"name= "add_by" size = "30" value = "${add_by}" > </p>
<p align = "center">Document last edit : <input type = "text"name= "last_edit" size = "30" value = "${last_edit}" > </p>
<p align = "center">Document last edit by : <input type = "text"name= "last_edit_by" size = "30" value = "${last_edit_by}" > </p>

<p align = "center">Descriptions : </br><textarea  rows="6" cols="40" name="descriptions" ></textarea></p>
</br></br>
<p align = "center"><input type="submit" name = "bt" value="Save" >
<input type="submit" name = "bt" value="Delete" >
<input type="submit" name = "bt" value="Back" ></p>

</form>

</body>
</html>