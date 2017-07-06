<!DOCTYPE html>
<html>
<body>




<p id="demo" >Click me to change my HTML content (innerHTML).</p>

<input type="button" id="bt"onclick="myFunction()" value="eiei">



<script>
function myFunction() {
    document.getElementById("demo").innerHTML = "Paragraph changed!";
    document.getElementById("bt").value = "hi";
}
</script>




</body>
</html> 
