<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
<title>Running Document</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" href="./detail_ui_css.css">




</head>
<body>


<form id="detail" method="post" action="detail_page">

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

                             <!-- <li class="active"><a href="#">Add Page <span class="sr-only">(current)</span></a></li> -->
                             <li><p><input type="submit" name="bt" value="Add Page" class="btn-link" id = "submit"></p></li>

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
</br> <h1>Document Details</h1> </br></br>
<div class="container">

         <div class="form-inline">
         	 <p> File Name : ${file_name}</p>
         	 <p> File id : ${file_id}</p>
         	 <p> File size : ${file_size}</p>
         	 <p> File add by : ${file_add_by}</p></br>
             <p> Document Name :     <input type = "text" id = "subject" name= "subject" class="form-control form-group" title = "please fill your subject" size = "37" value = "${head_name}"></p>
             <p> Document code : ${head_id}</p>
             <p> Document add date : ${add_date} </p>
             <p> Document add by : ${add_by}</p>
             <p> Document last edit : ${last_edit} </p>
             <p> Document last edit by : ${last_edit_by} </p>
             <p>Descriptions  :     </br><textarea  id = "descriptions" rows="4" cols="65" name="descriptions" class="form-control form-group" title ="please enter your document descriptions" size="42">${descriptions}</textarea></p>
        </div>
      <div class="form-inline">

         <input type="submit" class="btn btn-default btn-lg bt form-group setbt" name = "bt"  value="Back" id = "back"  />
         <input type="submit" class="btn btn-default btn-lg bt form-group setbt" name = "bt"  value="Save" id = "save"  />
         <input type="submit" class="btn btn-default btn-lg bt form-group setbt" name = "bt"  value="Download" id = "download" />
         <input type="submit" class="btn btn-default btn-lg bt form-group setbt" name = "bt"  value="Delete" id = "delete"  />
      </div>
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
