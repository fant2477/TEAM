<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title></title>
  </head>
  <body>


        <input type="file" id="i_file" value="">
        <p id="fpath">here: </p>
        <input type ="button" id ="bt" onclick="myFunction()" value="sent">

  </body>

			<script type="text/javascript">    
			function myFunction(){
			    var path = (window.URL || window.webkitURL).createObjectURL(file);
			    console.log('path', path);
			    document.getElementById("fpath").innerHTML = path;}
			</script>
</html>



<!--
<p id="demo">Click the button to change the text in this paragraph.</p>

<button onclick="myFunction()">Try it</button>

<script>
function myFunction() {
    document.getElementById("demo").innerHTML = "Hello World";
}
</script> -->
