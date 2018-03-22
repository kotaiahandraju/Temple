<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
    
<head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="A fully featured admin theme for Temple">
        <meta name="author" content="CHARVIKENT">

        <!-- App favicon -->
        <!-- <link rel="shortcut icon" href="assets/images/favicon.ico"> -->
        <link rel="shortcut icon" href="assets/images/fav.png">
        
        <!-- App title -->
        <title>SRI SUBRAHMANYESWARA SWAMY TEMPLE | Login</title>

        <!-- App css -->
        <link href="assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="assets/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <link href="assets/css/animate.min.css" rel="stylesheet" type="text/css" />
        <link href="assets/css/core.css" rel="stylesheet" type="text/css" />
        <link href="assets/css/components.css" rel="stylesheet" type="text/css" />
        <link href="assets/css/icons.css" rel="stylesheet" type="text/css" />
        <link href="assets/css/pages.css" rel="stylesheet" type="text/css" />
        <link href="assets/css/menu.css" rel="stylesheet" type="text/css" />
        <link href="assets/css/responsive.css" rel="stylesheet" type="text/css" />
        
<!--         <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" /> -->
<!--         <link href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css" rel="stylesheet" type="text/css" /> -->

        <!-- HTML5 Shiv and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->

<!--         <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> -->
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/js/modernizr.min.js"></script>

<style>
.alert{
text-align: -webkit-center;
position: absolute;
top: 0;left: 0;right: 0;
margin: 1px auto;
max-width: 420px;
text-align: center;
}
.your-class::-webkit-input-placeholder {color: red !important;}
.your-class::-moz-placeholder {color: red !important;}

.default-class::-webkit-input-placeholder {color: red !important;}
.default-class::-moz-placeholder {color: red !important;}


/* .multiselect-container{display: block;} */
span.has-error, #already_exist, .subjects_error
{
  font-weight:normal;
  border-color: #e73d4a;
  color:red;
  margin:0px;
  display: block !important;
  position: absolute;
}
</style>
<script>

</script>
    </head>


    <body class="bg-transparent">
<%-- <% if(session.getAttribute("name") == "" || session.getAttribute("name") == null) {%> --%>
        <!-- HOME -->
        <section>
            <div class="container-alt">
                <div class="row">
					<% 	
// 						String error = "Invalid Credentials";
						
						/* String login = null;
						login = (String)session.getAttribute("login");
						if(login != null)
						{
							out.println("<div class='alert alert-success alert-dismissable fadeIn animated infinite'>"+login+"</div>");
							session.setAttribute("login", null);
						} 
						
						String userLogin = null;
						userLogin = (String)session.getAttribute("userLogin");
						if(userLogin != null)
						{
							out.println("<div class='alert alert-success alert-dismissable fadeIn animated infinite'>"+userLogin+"</div>");
							session.setAttribute("userLogin", null);
						} */
						
						String error = null;
						error = (String)session.getAttribute("error");
						if(error != null)
						{
							out.println("<div class='alert alert-danger alert-dismissable fadeIn animated infinite'>"+error+"</div>");
							session.setAttribute("error", null);
						}
					%>
                    <div class="col-sm-12">

                        <div class="wrapper-page">

                            <div class="m-t-40 account-pages">
                                <div class="text-center account-logo-box" style="background-color: cornflowerblue;">
                                    <h5 class="text-uppercase" style="color: white !important;">
                                    	Sri Subrahmanyeswara Swamy Temple
                                    </h5>
                                    <!--<h4 class="text-uppercase font-bold m-b-0">Sign In</h4>-->
                                </div>
                                <div class="account-content">
                                    <form class="form-horizontal" id="login" method="post" >

                                        
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                            	<select name="rollId" id="rollId" class="form-control" onchange="removeBorder(this.id);" required>
													<option value="" selected="selected" disabled="disabled">-- Select Role --</option>
													<option value="1">Admin</option>
													<option value="2">User</option>
												</select>
                                            </div>
                                        </div>
                                        
                                        <div class="form-group ">
                                            <div class="col-xs-12">
                                                <input type="text" id="name" name="name" class="form-control" onkeydown="removeBorder(this.id);" required placeholder="Enter Username" autocomplete="off">
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <input type="password" id="password" name="password" class="form-control" onkeydown="removeBorder(this.id);" required placeholder="Enter Password"  autocomplete="off">
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <input type="submit" id="submitForm" class="btn btn-bordered btn-success" value="Login">
                                                <input type="button" class="btn btn-bordered btn-danger" id="cancel" onclick="resetForm()" value="Reset"/>
                                            </div>
                                        </div>

                                    </form>

                                    <div class="clearfix"></div>

                                </div>
                            </div>
                            <!-- end card-box-->

                        </div>
                        <!-- end wrapper -->

                    </div>
                </div>
            </div>
          </section>
          <!-- END HOME -->
<%-- <% 
	}
	else if(session.getAttribute("name") != null && (session.getAttribute("name") == "admin" || session.getAttribute("name").equals("admin")))
	{
		response.sendRedirect("Home");
	}
	else if(session.getAttribute("name") != null && (session.getAttribute("name") == "user1" || session.getAttribute("name").equals("user1") || session.getAttribute("name") == "user2" || session.getAttribute("name").equals("user2")))
	{
		response.sendRedirect("UserHome.htm");
	}
%> --%>
        <script>
        $('#submitForm').click(function(e){
			if( $('#name').val().length == 0 || $('#name').val() ==" " || $('#name').val()=="undefined" || $('#password').val().length == 0 || $('#password').val() ==" " || $('#password').val()=="undefined"  || $('#rollId').val() == null || $('#rollId').val() ==" " || $('#rollId').val()=="undefined")
			{
				if($('#rollId').val() == null ||  $('#rollId').val() ==" " || $('#rollId').val()=="undefined") {
				    $('#rollId').css('color','red');
				    $("#rollId").css("border-color","#e73d4a");
				    $('#rollId').addClass('your-class');
				    $('#rollId').focus();
			    }
				if($('#name').val().length == 0 ) {
				    $('#name').css('color','red');
				    $("#name").css("border-color","#e73d4a");
				    $("#name").attr("placeholder","Please Enter Username");
				    $('#name').addClass('your-class');
// 				    $('#name').focus();
			    }
				if($('#password').val().length == 0 ||  $('#password').val() ==" " || $('#password').val()=="undefined") {
				    $('#password').css('color','red');
				    $("#password").css("border-color","#e73d4a");
				    $("#password").attr("placeholder","Please Enter password");
				    $('#password').addClass('your-class');
// 				    $('#password').focus();
			    }
				
		    	return false;												  
			}
			else
			{
				 $("#submitForm").attr("disabled",true);
				 $("#submitForm").val("Please wait...");
			}
			    
		$('#login').attr('action',"LoginCheck");
		$("#login").submit();											
});

        function resetForm()
        {
        	removeBorder('rollId');
        	$('#rollId').val("");
        	$('#rollId').removeClass('your-class default-class');
        	
        	removeBorder('name');
        	$('#name').val("");
        	$('#name').removeClass('your-class default-class');
        	
        	removeBorder('password');
        	$('#password').val("");
        	$('#password').removeClass('your-class default-class');
        }
        
        function removeBorder(el){	
      	  $("#"+el).css("border", ""); 	
      	  $("#"+el).css('color','black');
      	  $('#'+el).addClass('default-class');
      	}
        
            window.setTimeout(function() {
                $(".alert").fadeTo(500, 0).slideUp(500, function(){
                    $(this).remove(); 
                });
            }, 4000);
            
        </script>

        <!-- jQuery  -->
<!--         <script src="assets/js/jquery.min.js"></script> -->
        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/detect.js"></script>
        <script src="assets/js/fastclick.js"></script>
        <script src="assets/js/jquery.blockUI.js"></script>
        <script src="assets/js/waves.js"></script>
        <script src="assets/js/jquery.slimscroll.js"></script>
        <script src="assets/js/jquery.scrollTo.min.js"></script>

        <!-- App js -->
        <script src="assets/js/jquery.core.js"></script>
        <script src="assets/js/jquery.app.js"></script>

    </body>

</html>