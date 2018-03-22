<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

	<!-- Body Starts Here -->
        <div class="wrapper">
									<% 	
	                                	String changed = null;
										changed = (String)session.getAttribute("changed");
						            	if(changed != null)
						            	{
						            		out.println("<div class='alert alert-success alert-dismissable alert-dismissable fadeIn animated infinite' style='z-index: 10006;'><a class='close' data-dismiss='alert' aria-label='close'>×</a>"+changed+"</div>");
						                	session.setAttribute("changed", null);
									 	}
						            	
						            	String mismatch = null;
						            	mismatch = (String)session.getAttribute("mismatch");
						            	if(mismatch != null)
						            	{
						            		out.println("<div class='alert alert-warning alert-dismissable alert-dismissable fadeIn animated infinite' style='z-index: 10006;'><a class='close' data-dismiss='alert' aria-label='close'>×</a>"+mismatch+"</div>");
						                	session.setAttribute("mismatch", null);
									 	}
                                		
						            	String invalid = null;
						            	invalid = (String)session.getAttribute("invalid");
						            	if(invalid != null)
						            	{
						            		out.println("<div class='alert alert-danger alert-dismissable alert-dismissable fadeIn animated infinite' style='z-index: 10006;'><a class='close' data-dismiss='alert' aria-label='close'>×</a>"+invalid+"</div>");
						                	session.setAttribute("invalid", null);
									 	}
									 %>
        
            <div class="container">

                <!-- Page-Title -->
                <div class="row">
                    <div class="col-sm-12">
                        <div class="page-title-box">
                            <div class="btn-group pull-right">
                                <ol class="breadcrumb hide-phone p-0 m-0">
                                    <li><a href="">Temple</a></li>
                                    <li class="active">Change Password</li>
                                </ol>
                            </div>
                            <h4 class="page-title">Change Password</h4>
                        </div>
                    </div>
                </div>
                <!-- end page title end breadcrumb -->
                
                <div class="row">
                    <div class="col-lg-12">
                        <div class="portlet">
                            <div class="portlet-heading bg-success">
                                <h3 class="portlet-title" id="scheme">
                                    Change Password
                                </h3>
                                <div class="portlet-widgets">
                                    <!-- <a href="javascript:;" data-toggle="reload"><i class="ion-refresh"></i></a>
                                    <span class="divider"></span> -->
                                    <a data-toggle="collapse" data-parent="#accordion1" href="#bg-success"><i class="ion-minus-round"></i></a>
                                    <!-- <span class="divider"></span>
                                    <a href="#" data-toggle="remove"><i class="ion-close-round"></i></a> -->
                                </div>
                                <div class="clearfix"></div>
                            </div>
                            <div id="bg-success" class="panel-collapse collapse in">
                                <div class="portlet-body">
                                
						<!-- Change Password Form Starts Here -->
                    	<form:form id="change" commandName="packCmd" method="post" class="form-horizontal" role="form">
									<form:hidden path="id" />
									<div class="form-group">
										<label for="inputEmail3" class="col-sm-4 control-label">Current Password <span style="color: red;">*</span>:</label>
										<div class="col-sm-5">											
											<form:input path="oldPassword" type ="password"  tabindex="1" placeholder="Enter Current Password" class="form-control" onkeydown="removeBorder(this.id);" required="true"/>
										</div>
									</div>
									<div class="form-group">
										<label for="inputEmail3" class="col-sm-4 control-label">New Password <span style="color: red;">*</span>:</label>
										<div class="col-sm-5">											
											<form:input path="password" type ="password" tabindex="2" placeholder="Enter New Password" class="form-control" onkeydown="removeBorder(this.id);" required="true"/>
										</div>
									</div>
					            	<div class="form-group">
					                	<label for="inputEmail3" class="col-sm-4 control-label">Confirm Password <span style="color: red;">*</span>:</label>
					              		<div class="col-sm-5">
					                    	<form:input path="retypePassword" type ="password" tabindex="3" placeholder="Retype Password" class="form-control" onkeydown="removeBorder(this.id);" required="true"/>
					                  	</div>
					             	</div>
									<div class="form-group">
										<div class="col-sm-8 col-sm-offset-4">
											<input type="submit" id="submitform" class="btn btn-success" tabindex="4" value="Submit"/>
											<input type="button" class="btn btn-danger" id="cancel" tabindex="5" onclick="resetForm()" value="Reset"/>
										</div>
									</div>
						</form:form>
						<!-- Change Password Form Ends Here -->

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                

            </div>
        </div>
	<!-- Body ends Here -->


<!-- <script src="assets/js/jquery.min.js"></script> -->
<script type="text/javascript">


$('#submitform').click(function(e){
if( $('#oldPassword').val().length == 0 || $('#oldPassword').val() ==" " || $('#oldPassword').val()=="undefined" || $('#password').val().length == 0 || $('#password').val() == "" || $('#password').val()=="undefined" || $('#retypePassword').val().length == 0 || $('#retypePassword').val() == "" || $('#retypePassword').val()=="undefined")
{												   
	if($('#oldPassword').val().length == 0 ) {
	    $('#oldPassword').css('color','red');
	    $("#oldPassword").css("border-color","#e73d4a");
	    $("#oldPassword").attr("placeholder","Please Enter Current Password");
	    $('#oldPassword').addClass('your-class');
    }
	if($('#password').val() ==  null || $('#password').val() == ""  || $('#password').val()=="undefined" ) {
	    $('#password').css('color','red');
	    $("#password").css("border-color","#e73d4a");
	    $("#password").attr("placeholder","Please Enter New Password");
	    $('#password').addClass('your-class');
    }
	if($('#retypePassword').val() ==  null || $('#retypePassword').val() == ""  || $('#retypePassword').val()=="undefined" ) {
	    $('#retypePassword').css('color','red');
	    $("#retypePassword").css("border-color","#e73d4a");
	    $("#retypePassword").attr("placeholder","Please retype new Password");
	    $('#retypePassword').addClass('your-class');
    }
	return false;
}
    
$('#change').attr('action',"adminChangePassword");
$("#change").submit();											
event.preventDefault();	
});

function resetForm()
{
	removeBorder('oldPassword');
	$('#oldPassword').val("");
	$('#oldPassword').removeClass('your-class default-class');
	
	removeBorder('password');
	$('#password').val("");
	$('#password').removeClass('your-class default-class');
	
	removeBorder('retypePassword');
	$('#retypePassword').val("");
	$('#retypePassword').removeClass('your-class default-class');
}

</script>
</body>
</html>