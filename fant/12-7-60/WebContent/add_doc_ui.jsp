<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
<title>Running Document</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" href="./add_doc_ui_css.css">




</head>
<body>


<form id="main" method="post" action="add_doc_page">

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

                             <li><p><input type="submit" name="bt" value="Main Page" class="btn-link" id = "submit"></p></li>

                             <li><p><input type="submit" name="bt" value="History Page" class="btn-link" id = "submit"></p></li>

                             <li class="active"><a href="#">Add Page <span class="sr-only">(current)</span></a></li>

                             <li><p><input type="submit" name="bt" value="Delete Page" class="btn-link" id = "submit"></p></li>



                       </ul>



                       <ul class="nav navbar-nav navbar-right">
                              <!-- <a class="navbar-brand" href="#">
                               <img alt="User" src="./login_img.png">
                             </a>  -->

                             <li><p><input type="submit" name="bt" value="Log Out" class="btn-link" id = "submit"></p></li>
                       </ul>
                       <ul class="nav navbar-nav navbar-right">
                          <button type="submit" class="btn-link" name="bt" value="User_info" id="User_info"><i class="fa fa-user-circle-o"></i> </button>


                       </ul>

                 </div>

           </div><!-- /.container-fluid -->

     </nav>
</br>
<!-- ============================================ end of header tab ============================================ -->
<div class="container">

     <!-- <%-- <div class="w3-row-padding w3-grayscale" style="margin-bottom:128px">
       <div class="w3-half"> --%> -->
         <div class="form-inline">
             <p>Subject  :     <input type = "text" id = "subject" name= "subject" class="form-control form-group" title = "please fill your subject" size = "37" value = "${subject}"><small id = "small">${username_check}</small></p>
             <p>Tag  :     <input type = "text" id = "tag" name= "tag" class="form-control form-group" title = "please fill your tag" size = "37" value = "${tag}"><small id = "small">${tag_check}</small></p>
             <p>Descriptions  :     </br><textarea  id = "descriptions" rows="4" cols="50" name="descriptions" class="form-control form-group" title ="please enter your document descriptions" size="37" >${descriptions}</textarea></p>



           <div class="box form-group">
               <input type="file" name="file-6[]" id="file-6" class="inputfile inputfile-5" data-multiple-caption="{count} files selected" multiple />
               <label for="file-6"><figure><svg xmlns="http://www.w3.org/2000/svg" width="20" height="17" viewBox="0 0 20 17"><path d="M10 0l-5.2 4.9h3.3v5.1h3.8v-5.1h3.3l-5.2-4.9zm9.3 11.5l-3.2-2.1h-2l3.4 2.6h-3.5c-.1 0-.2.1-.2.1l-.8 2.3h-6l-.8-2.2c-.1-.1-.1-.2-.2-.2h-3.6l3.4-2.6h-2l-3.2 2.1c-.4.3-.7 1-.6 1.5l.6 3.1c.1.5.7.9 1.2.9h16.3c.6 0 1.1-.4 1.3-.9l.6-3.1c.1-.5-.2-1.2-.7-1.5z"/></svg></figure> <span></span></label>
           </div>





       </div>


     <input type="submit" class="btn btn-default btn-lg bt form-group" name = "bt"  value="Save" id = "save" />
<!-- end of file input -->

</div>


</form>

<!-- ===============start of script for file button=============== -->
       <script>
           var inputs = document.querySelectorAll( '.inputfile' );
           Array.prototype.forEach.call( inputs, function( input )
           {
             var label	 = input.nextElementSibling,
               labelVal = label.innerHTML;

             input.addEventListener( 'change', function( e )
             {
               var fileName = '';
               if( this.files && this.files.length > 1 )
                 fileName = ( this.getAttribute( 'data-multiple-caption' ) || '' ).replace( '{count}', this.files.length );
               else
                 fileName = e.target.value.split( '\\' ).pop();

               if( fileName )
                 label.querySelector( 'span' ).innerHTML = fileName;
               else
                 label.innerHTML = labelVal;
             });
           });
       </script>
<!-- ===============end of script for file button=============== -->

</body>
<!--jsp:include page = "footer.jsp"/-->

</html>
