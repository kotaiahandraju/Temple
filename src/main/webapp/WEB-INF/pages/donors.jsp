<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<!-- <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-2.1.3.min.js"></script> -->
<link href="assets/css/datepicker1.css" rel="stylesheet">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<script src="assets/js/jquery-ui.min.js"></script>
	<!-- Body Starts Here -->
        <div class="wrapper">
									<% 	
									String d_created = null;
									String d_error = null;
									d_created = (String)session.getAttribute("d_created");
									d_error = (String)session.getAttribute("d_error");
									if(d_error != null && d_created != null)
									{
						            		out.println("<div class='alert alert-danger alert-dismissable alert-dismissable fadeIn animated infinite' style='z-index: 10006;'>"+d_error+"<br/>"+ d_created+"</div>");
						                	session.setAttribute("d_error", null);
						                	session.setAttribute("d_created", null);
						            }
									
									
										/* String sms = null;
										sms = (String)session.getAttribute("sms");
						            	if(sms != null)
						            	{
						            		out.println("<div class='alert alert-success alert-dismissable alert-dismissable fadeIn animated infinite' style='z-index: 10006;'>"+sms+"</div>");
						                	session.setAttribute("sms", null);
									 	} */
										
						            	
 	                                	
 						            	if(d_created != null)
 						            	{
 						            		out.println("<div class='alert alert-success alert-dismissable alert-dismissable fadeIn animated infinite' style='z-index: 10006;'>"+d_created+"</div>");
 						                	session.setAttribute("d_created", null);
 									 	}
						            	
 						            	String d_updated = null;
 						            	d_updated = (String)session.getAttribute("d_updated");
 						            	if(d_updated != null)
 						            	{
 						            		out.println("<div class='alert alert-warning alert-dismissable alert-dismissable fadeIn animated infinite' style='z-index: 10006;'>"+d_updated+"</div>");
 						                	session.setAttribute("d_updated", null);
 									 	}
 						            	
 						            	String error = null;
 						            	error = (String)session.getAttribute("error");
 						            	if(error != null)
 						            	{
 						            		out.println("<div class='alert alert-danger alert-dismissable alert-dismissable fadeIn animated infinite' style='z-index: 10006;'>"+error+"</div>");
 						                	session.setAttribute("error", null);
 									 	}
 						            	
 						            	
 						            	
 						            	if(d_error != null)
 						            	{
 						            		out.println("<div class='alert alert-danger alert-dismissable alert-dismissable fadeIn animated infinite' style='z-index: 10006;'>"+d_error+"</div>");
 						                	session.setAttribute("d_error", null);
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
                                    <li class="active">Donors</li>
                                </ol>
                            </div>
                            <h4 class="page-title">Donors</h4>
                        </div>
                    </div>
                </div>
                <!-- end page title end breadcrumb -->
                
                <div class="row" id="view_list">
                    <div class="col-lg-12">
                        <div class="portlet">
                            <div class="portlet-heading bg-success">
                                <h3 class="portlet-title" id="donor">
                                    Add Donor
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
                                <div class="portlet-body  w3-animate-zoom">
                                
						<!-- Add Scheme Form Starts Here -->
						
<div class="row table-responsive">
	<div class="col-md-12">
<!-- <script language="JavaScript">incfont("Code2000","CODETelugu")</script> -->
		<form name="post" method="post">
		<table style="border-top: 1px solid cornflowerblue;border: 1px solid cornflowerblue;" class="table table-responsive">
			<tbody>
				<tr>
                	<td><input type="radio" name="keybrd" value="english" id="eng" onclick="toggleKBMode(event,this)">&nbsp;English </td>
                    <td><input type="radio" name="keybrd" value="applete" onclick="toggleKBMode(event,this)">&nbsp;Telugu Apple Keyboard</td>
					<td><input type="radio" name="keybrd" value="roman" onclick="toggleKBMode(event,this)">&nbsp;Phonetic Telugu </td>
					<td><input type="checkbox" name="showmap" onclick="showMap(this)">&nbsp;Show Keymap<br></td>
<!-- 					<td><input type="button" style="font-family: Tahoma,Arial,Verdana,Helvetica,sans-serif;font-size: 11px;" name="savepref" value="Save Settings"></td> -->
            	</tr>
			</tbody>
		</table>
		<br>
		</form>
	</div>
</div>
						
                    	<form:form id="create" commandName="packCmd" method="post" class="form-horizontal" accept-charset="UTF-8" role="form">
                    	<div class="row">
	                        <div class="col-md-4">
	                        	<div class="form-group">
	                            	<label for="inputEmail3" class="col-md-4 col-sm-3 control-label">Full Name <span style="color: red;">*</span>:</label>
	                                <div class="col-md-8 col-sm-9">
	                            		<form:input path="name" placeholder="Enter Full Name" class="form-control onlyCharacters" tabindex="1" onkeypress="javascript:convertThis(event)" onkeydown="removeBorder(this.id);" required="true"/>
	                                </div>
	                            </div>
                            </div>
                        	<div class="col-md-4">
	                    		<form:hidden path="id" />
		                    	<div class="form-group">
		                            <label for="inputPassword4" class="col-md-4 col-sm-3 control-label">Scheme Title<span style="color: red;">*</span>:</label>
		                            <div class="col-md-8 col-sm-6">
										<form:select path="schemeTitle" class="form-control" required="true" tabindex="2" onchange="removeBorder(this.id),getSchemeType()" >
											<form:option value="" >-- Choose Scheme Title --</form:option>
											<form:options items="${schemeTitle}"></form:options>
										</form:select>
		                            </div>
		                        </div>
	                        </div>
	                        <div class="col-md-4">
	                        	<div class="form-group">
	                            	<label for="inputEmail3" class="col-md-4 col-sm-3 control-label">Reg. Date<span style="color: red;">*</span>:</label>
	                                <div class="col-md-8 col-sm-9">
	                            		<%-- <form:input path="dor" placeholder="Registration Date" class="form-control" tabindex="3" onkeydown="removeBorder(this.id);" required="true"/> --%>
	                            		<form:input path="dor" data-format="dd-MM-yyyy" placeholder="Registration Date" class="form-control" onkeydown="removeBorder(this.id);" tabindex="3" required="true" readonly="true"/>
	                                </div>
	                            </div>
                            </div>
						</div>
						<div class="row">
                            <div class="col-md-4">
	                        	<div class="form-group">
	                            	<label for="inputEmail3" class="col-md-4 col-sm-3 control-label">Name of Datha<span style="color: red;">*</span>:</label>
	                                <div class="col-md-8 col-sm-9">
	                            		<form:input path="datha" placeholder="Enter Name of Datha" class="form-control onlyCharacters" tabindex="4" onkeypress="javascript:convertThis(event)" onkeydown="removeBorder(this.id);" required="true"/>
	                                </div>
	                            </div>
                            </div>
                            <div class="col-md-4">
	                            <div class="form-group">
	                            	<label for="inputEmail3" class="col-md-4 col-sm-3 control-label">Gotram <span style="color: red;">*</span>:</label>
	                                <div class="col-md-8 col-sm-9">
	                            		<form:input path="gotram" placeholder="Enter Gotram" class="form-control onlyCharacters" tabindex="5" onkeypress="javascript:convertThis(event)" onkeydown="removeBorder(this.id);" required="true"/>
	                                </div>
	                            </div>
                            </div>
                        	<div class="col-md-4">
	                            <div class="form-group">
	                            	<label for="inputEmail3" class="col-md-4 col-sm-3 control-label">Scheme Type<span style="color: red;">*</span>:</label>
	                                <div class="col-md-8 col-sm-9">
	                            		<form:select path="schemeType" class="form-control" required="true" tabindex="6" onchange="removeBorder(this.id),changeDayMonth()" >
											<form:option value="" >-- Choose Scheme Type --</form:option>
											<form:options items="${schemetype}"></form:options>
										</form:select>
	                            		<form:select path="schemeType1" class="form-control" onchange="removeBorder(this.id);" style="display:none">
											<form:option value="" >-- Choose Scheme Type --</form:option>
											<form:options items="${schemetype}"></form:options>
										</form:select>
	                                </div>
	                            </div>
                            </div>
						</div>
						<div class="row">
                            <div class="col-md-4">
	                            <div class="form-group">
	                            	<label for="inputEmail3" class="col-md-4 col-sm-3 control-label">Month<span style="color: red;">*</span>:</label>
	                                <div class="col-md-8 col-sm-9">
	                            		<form:select path="month" class="form-control" required="true" tabindex="7" onchange="removeBorder(this.id);" >
											<form:option value="" >-- Choose Month --</form:option>
											<form:options items="${telugumasalu}"></form:options>
										</form:select>
										<form:select path="month2" class="form-control" onchange="removeBorder(this.id);" style="display:none">
											<form:option value="" >-- Choose Month --</form:option>
											<form:options items="${telugumasalu}"></form:options>
										</form:select>
	                           			<form:select path="month1" class="form-control" onchange="removeBorder(this.id);" style="display:none">
											<form:option value="" >-- Choose Month --</form:option>
											<form:options items="${englishmonths}"></form:options>
										</form:select>
	                                </div>
	                            </div>
							</div>
							<div class="col-md-4">
	                            <div class="form-group">
	                            	<label for="inputEmail3" class="col-md-4 col-sm-3 control-label">Day<span style="color: red;">*</span>:</label>
	                                <div class="col-md-8 col-sm-9">
	                            		<form:select path="day" class="form-control" tabindex="8" onchange="removeBorder(this.id);" >
											<form:option value="" >-- Choose Day --</form:option>
											<form:options items="${telugudays}"></form:options>
										</form:select>
										<form:select path="day2" class="form-control" onchange="removeBorder(this.id);" style="display:none">
											<form:option value="" >-- Choose Day --</form:option>
											<form:options items="${telugudays}"></form:options>
										</form:select>
										<form:select path="day1" class="form-control" onchange="removeBorder(this.id);" style="display:none">
											<form:option value="" >-- Choose Day --</form:option>
											<form:options items="${englishdays}"></form:options>
										</form:select>
	                                </div>
	                            </div>
							</div>
							<div class="col-md-4">
	                            <div class="form-group">
	                            	<label for="inputEmail3" class="col-md-4 col-sm-3 control-label">Mobile<span style="color: red;">*</span>:</label>
	                                <div class="col-md-8 col-sm-9">
	                            		<form:input path="mobile" placeholder="Enter Mobile Number" class="form-control numericOnly" maxlength="10" tabindex="9" onkeydown="removeBorder(this.id);" required="true"/>
	                                </div>
	                            </div>
                            </div>
						</div>
						<div class="row">
                            <div class="col-md-4">
	                            <div class="form-group">
	                            	<label for="inputEmail3" class="col-md-4 col-sm-3 control-label">Other Info:</label>
	                                <div class="col-md-8 col-sm-9">
	                            		<%-- <form:input path="otherInformation" placeholder="Enter Other Information" class="form-control onlyCharacters" tabindex="7" onkeypress="javascript:convertThis(event)" onkeydown="removeBorder(this.id);"/> --%>
	                            		<form:textarea path="otherInformation" placeholder="Enter Other Information" rows="4" cols="6" class="form-control onlyCharacters" tabindex="10" onkeypress="javascript:convertThis(event)" onkeydown="removeBorder(this.id);"/>
	                                </div>
	                            </div>
                            </div>
                            <div class="col-md-4">
	                            <div class="form-group">
	                            	<label for="inputEmail3" class="col-md-4 col-sm-3 control-label">Address <span style="color: red;">*</span>:</label>
	                                <div class="col-md-8 col-sm-9">
	                            		<%-- <form:input path="address" placeholder="Enter Address" required="true" class="form-control onlyCharacters" tabindex="8" onkeypress="javascript:convertThis(event)" onblur="removeBorder(this.id);" onfocus="removeBorder(this.id);"/> --%>
	                            		<form:textarea path="address" placeholder="Enter Address" rows="4" cols="6" class="form-control" tabindex="11" onkeypress="javascript:convertThis(event)" onkeydown="removeBorder(this.id);"/>
	                                </div>
	                            </div>
                            </div>
                        	<div class="col-md-4">
	                            <div class="form-group">
	                            	<label for="inputEmail3" class="col-md-4 col-sm-3 control-label">Email:</label>
	                                <div class="col-md-8 col-sm-9">
	                            		<form:input path="email" placeholder="Enter Email-ID" class="form-control" tabindex="12" onkeydown="removeBorder(this.id);"/>
	                                </div>
	                            </div>
							</div>
						</div>
						<div class="row">
                        	<div class="col-md-4">
	                            <div class="form-group">
	                            	<label for="inputEmail3" class="col-md-4 col-sm-3 control-label">Donation Amount:</label>
	                                <div class="col-md-8 col-sm-9">
	                            		<form:input path="amount" placeholder="Enter Donation Amount" class="form-control numericOnly" tabindex="13" onkeydown="removeBorder(this.id);"/>
	                                </div>
	                            </div>
							</div>
                        	<div class="col-md-4">
	                            <div class="form-group">
	                            	<label for="inputEmail3" class="col-md-4 col-sm-3 control-label">Receipt No:</label>
	                                <div class="col-md-8 col-sm-9">
	                            		<form:input path="receiptNo" placeholder="Enter Receipt Number" class="form-control" tabindex="14" onkeydown="removeBorder(this.id);"/>
	                                </div>
	                            </div>
							</div>
						</div>
						
						<div class="row">
                        	<div class="col-md-offset-4 col-md-8">
								<div class="form-group">
	                            	<div class="col-md-offset-2 col-md-8">
	                                	<input type="submit" id="submitform" class="btn btn-success" tabindex="15" value="Add Donor"/>
										<input type="button" class="btn btn-danger" id="cancel" tabindex="16" onclick="resetForm()" value="Reset"/>
	                                </div>
	                        	</div>
	                        </div>
	                    </div>
						</form:form>
						<!-- Add Scheme Form Ends Here -->

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                
                <div class="row" id="view_list2">
					<div class="col-md-12">

<script type="text/javascript">
$(function(){
	$('input[type=file]').change(function () 
	{
		var val = $(this).val().toLowerCase();
		var regex = new RegExp("(.*?)\.(xls)$");
		if(!(regex.test(val))) 
		{
			$(this).val('');
			alert('Please Select .xls format only..!');
		} 
	});
});
</script>
					
						<form:form id="mform" action="processDonorList.htm" method="post" class="form-horizontal" enctype="multipart/form-data">
							<!-- <div class="form-group">
                            	<label class="col-sm-2 col-xs-12 control-label">Select File: <span style="color: red;">*</span></label>
                            	<input type="file" class="filestyle" name="excelfile2007" accept=".xls" data-placeholder="Please Choose Excel File(.xls)" data-size="sm" id="filestyle-5" tabindex="-1" style="position: absolute; clip: rect(0px 0px 0px 0px);">
                            	<div class="col-sm-4 col-xs-12 bootstrap-filestyle input-group">
                            		<input type="text" class="form-control input-sm" placeholder="Please Choose Excel File(.xls)">
                            		<span class="group-span-filestyle input-group-btn" tabindex="0">
	                            		<label for="filestyle-5" class="btn btn-default btn-sm">
		                            		<span class="icon-span-filestyle glyphicon glyphicon-folder-open"></span>
		                            		<span class="buttonText">Choose file</span>
	                            		</label>
                            		</span>
                            	</div>
                            </div> -->
                            
							<div class="form-group">
								<label for="inputPassword3" class="col-sm-2 col-xs-12 control-label">Select File: <span style="color: red;">*</span></label>
								<div class="col-sm-4 col-xs-12">
									<input class="form-control" name="excelfile2007" id="excelfile2007" type="file" accept=".xls" required>
									<span class="excelfile2007-error" id="excelfile2007-error"></span>		
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-2 col-sm-offset-2 col-xs-12">
									<input type="submit" class="btn btn-success" value="Upload">
									<input type="reset" class="btn btn-danger" value="Reset"/>
								</div>
							</div>
						</form:form>
					</div>
				</div>
                
                <div class="row" id="view_list1">
					<div class="col-md-12">
						
						<!-- DataTable Starts Here -->
									<div class="card-box table-responsive">

	<div style="padding-bottom: 1px;">
		<input id="checkAll"  type='checkbox'/><label for="checkAll" style="cursor: pointer;">&nbsp; Select All</label>
							
							<!-- Send Message Form Starts Here -->
							<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" data-backdrop="static" data-keyboard="false" aria-hidden="true" style="display: none;">
                                <div class="modal-dialog modal-sm">
                                    <div class="modal-content" style="background-color: #14F !important;">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span style="color: black !important;">x</span></button>
                                            <h4 class="modal-title" id="mySmallModalLabel" style="color: white !important;">Send SMS</h4>
                                        </div>
                                        <div class="modal-body">
                                        	<form action="" method="post">
                                        		<textarea id="message" class="form-control" name="message" placeholder="Please Enter Message" onkeydown="removeBorder(this.id);" rows="5" cols="5" required="required">Dear #1, Your occasion of #2 is during #3 #4 #5.</textarea>
                                        		<br/>
                                        		<p style="color: white !important;">
	                                        		<span style="">#1</span> -- <span style="color: #f5707a !important">Donor Name,</span><br/>
	                                        		<span style="">#2</span> -- <span style="color: #f5707a !important">Scheme Title,</span><br/>
	                                        		<span style="">#3</span> -- <span style="color: #f5707a !important">Month/Maasamu,</span><br/>
	                                        		<span style="">#4</span> -- <span style="color: #f5707a !important">Day/Thidi,</span><br/>
	                                        		<span style="">#5</span> -- <span style="color: #f5707a !important">Year</span>
                                        		</p>
                                        		<input type="submit" class="btn btn-success" id="sendBtn" value="Send" onclick="sendSms()"/>
                                        		<input type="button" class="btn btn-warning" onclick="resetFunction()" value="Reset"/>
                                        		<button type="button" class="btn btn-danger waves-effect" data-dismiss="modal">Cancel</button>
                                        	</form>
                                        </div>
                                    </div><!-- /.modal-content -->
                                </div><!-- /.modal-dialog -->
                            </div><!-- /.modal -->
                            <!-- Send Message Form Ends Here -->
                            
		<!-- Sms Button -->
		<button class="btn btn-primary waves-effect waves-light" data-toggle="modal" data-target=".bs-example-modal-sm" id="sendSMS" style="position: absolute;top: 1em;left: 10em;">Send SMS</button>
                            
	</div>

									<h5 style="color: red;">Donor's List</h5>
			                            <div id="tableId">
			                            <table id="datatable-buttons" class="table table-striped table-bordered">
			                                <thead>
				                                <tr>
				                                    <th class="printbtn" style='text-align: center;'>#</th>
				                                    <th>Name</th>
													<!-- <th>Address</th> -->
													<th>Pooja Type</th>
													<th>Month</th>
													<th>Day/Thidi</th>
													<th>Gotram</th>
													<th>Mobile</th>
													<!-- <th>Other.Info</th> -->
													<th style="text-align: center;">Action</th>
				                                </tr>
			                                </thead>
			                                <tbody>
			                                
			                                </tbody>
			                            </table>
			                            </div>
									</div>
						<!-- DataTable ends Here -->
						
					</div>
                </div>
                
                <div class="row" id="showData">
                	<div class="col-md-12">
                		<!-- View Starts Here -->
                		
                		<!-- View Starts Here -->
                	</div>
                </div>

            </div>
        </div>
	<!-- Body ends Here -->


<script type="text/javascript">

$(document).ready(function(){
	$('#eng').trigger('click');
	
	$("#amount").val("");
});

$("#dor").datepicker({
	changeDate : true,
	changeMonth : true,
	changeYear : true,
	yearRange: "-50:+0",
	showButtonPanel : false,
//		minDate: '-50Y',
    maxDate: '0', 
	dateFormat : 'dd-MM-yy'
});

$('#submitform').click(function(e){
	if( $('#name').val().length == 0 || $('#name').val() ==" " || $('#name').val()=="undefined"|| $('#gotram').val() == null || $('#gotram').val() == "" || $('#gotram').val()=="undefined" || $('#schemeTitle').val() == null || $('#schemeTitle').val() ==" " || $('#schemeTitle').val()=="undefined" || $('#schemeType').val() == null || $('#schemeType').val() =="" || $('#schemeType').val()=="undefined" || $('#month').val() == null || $('#month').val() =="" || $('#month').val()=="undefined" || $('#day').val() == null || $('#day').val() =="" || $('#day').val()=="undefined" || $('#mobile').val().length== 0 || $('#mobile').val() =="" || $('#mobile').val()=="undefined" || $('textarea#address').val() ==  null || $('textarea#address').val() == ""  || $('textarea#address').val()=="undefined")
	{	
		
		if($('#name').val().length == 0 || $('#name').val() ==" " || $('#name').val()=="undefined") {
		    $('#name').css('color','red');
		    $("#name").css("border-color","#e73d4a");
		    $("#name").attr("placeholder","Please Enter Full name");
		    $('#name').addClass('your-class');
	    }
		if($('#schemeTitle').val() == null || $('#schemeTitle').val() =="" || $('#schemeTitle').val()=="undefined") {
		    $('#schemeTitle').css('color','red');
		    $("#schemeTitle").css("border-color","#e73d4a");
		    $('#schemeTitle').addClass('your-class');
	    }
		if($('#dor').val().length == 0 || $('#dor').val() ==" " || $('#dor').val()=="undefined")
		{
		    $('#dor').css('color','red');
		    $("#dor").css("border-color","#e73d4a");
		    $("#dor").attr("placeholder","Please Enter Registration Date");
		    $('#dor').addClass('your-class');
	    }
		if($('#datha').val().length == 0 || $('#datha').val() ==" " || $('#datha').val()=="undefined") {
		    $('#datha').css('color','red');
		    $("#datha").css("border-color","#e73d4a");
		    $("#datha").attr("placeholder","Please Enter Name of Datha");
		    $('#datha').addClass('your-class');
	    }
		if($('#gotram').val() ==  null || $('#gotram').val() == ""  || $('#gotram').val()=="undefined" ) {
		    $('#gotram').css('color','red');
		    $("#gotram").css("border-color","#e73d4a");
		    $("#gotram").attr("placeholder","Please Enter Gotram");
		    $('#gotram').addClass('your-class');
	    }
		if($('#schemeType').val() == null || $('#schemeType').val() =="" || $('#schemeType').val()=="undefined") {
		    $('#schemeType').css('color','red');
		    $("#schemeType").css("border-color","#e73d4a");
		    $('#schemeType').addClass('your-class');
	    }
		if($('#month').val() == null || $('#month').val() =="" || $('#month').val()=="undefined") {
		    $('#month').css('color','red');
		    $("#month").css("border-color","#e73d4a");
		    $('#month').addClass('your-class');
	    }
		if($('#day').val() == null || $('#day').val() =="" || $('#day').val()=="undefined") {
		    $('#day').css('color','red');
		    $("#day").css("border-color","#e73d4a");
		    $('#day').addClass('your-class');
	    }
		if($('#mobile').val().length == 0|| $('#mobile').val() =="" || $('#mobile').val()=="undefined") {
		    $('#mobile').css('color','red');
		    $("#mobile").css("border-color","#e73d4a");
		    $("#mobile").attr("placeholder","Please Enter Mobile number");
		    $('#mobile').addClass('your-class');
	    }
		if($('textarea#address').val() ==  null || $('textarea#address').val() == ""  || $('textarea#address').val()=="undefined" ) {
		    $('#address').css('color','red');
		    $("#address").css("border-color","#e73d4a");
		    $("#address").attr("placeholder","Please Enter Address");
		    $('#address').addClass('your-class');
	    }
		return false;												  
	}
	else
	{
		 $("#submitForm").attr("disabled",true);
		 $("#submitForm").val("Please wait...");
	}
	    
	$('#create').attr('action',"AddDonor");
	$("#create").submit();											
	event.preventDefault();	
});

function resetForm()
{
	removeBorder('schemeTitle');
	$('#schemeTitle').val("");
	$('#schemeTitle').removeClass('your-class default-class');
	
	removeBorder('name');
	$('#name').val("");
	$('#name').removeClass('your-class default-class');
	
	removeBorder('dor');
	$('#dor').val("");
	$('#dor').removeClass('your-class default-class');
	
	removeBorder('gotram');
	$('#gotram').val("");
	$('#gotram').removeClass('your-class default-class');
	
	removeBorder('datha');
	$('#datha').val("");
	$('#datha').removeClass('your-class default-class');
	
	removeBorder('schemeType');
	$('#schemeType').val("");
	$('#schemeType').removeClass('your-class default-class');
	
	removeBorder('schemeType1');
	$('#schemeType1').val("");
	$('#schemeType1').removeClass('your-class default-class');
	
	removeBorder('month');
	$('#month').val("");
	$('#month').removeClass('your-class default-class');
	
	removeBorder('month1');
	$('#month1').val("");
	$('#month1').removeClass('your-class default-class');
	
	removeBorder('month2');
	$('#month2').val("");
	$('#month2').removeClass('your-class default-class');
	
	removeBorder('day');
	$('#day').val("");
	$('#day').removeClass('your-class default-class');
	
	removeBorder('day1');
	$('#day1').val("");
	$('#day1').removeClass('your-class default-class');
	
	removeBorder('day2');
	$('#day2').val("");
	$('#day2').removeClass('your-class default-class');
	
	removeBorder('otherInformation');
	$('#otherInformation').val("");
	$('#otherInformation').removeClass('your-class default-class');
	
	removeBorder('address');
	$('#address').val("");
	$('#address').removeClass('your-class default-class');
	
	removeBorder('mobile');
	$('#mobile').val("");
	$('#mobile').removeClass('your-class default-class');
	
	removeBorder('email');
	$('#email').val("");
	$('#email').removeClass('your-class default-class');
	
	removeBorder('amount');
	$('#amount').val("");
	$('#amount').removeClass('your-class default-class');
	
	removeBorder('receiptno');
	$('#receiptNo').val("");
	$('#receiptNo').removeClass('your-class default-class');
	
}

function getSchemeType(){
	var schemeTitle = $("#schemeTitle").val();
	$.ajax({
			type : "POST",
			url : "getSchemeType.json",
			data : "schemeTitle=" + schemeTitle ,
			success : function(response) {
				if(response != null){
					if(response != 3){
						var dummy = $("#schemeType1").html();
						$("#schemeType").empty();
						$(dummy).appendTo("#schemeType");	
						$("#schemeType").val(response);
						$('#schemeType').trigger("chosen:updated");
						if(response == 2){
							$("#schemeType option[value="+1+"]").hide();
							
							var dummy2 = $("#month2").html();
							$("#month").empty();
							$(dummy2).appendTo("#month");
							
							var dummy1 = $("#day2").html();
							$("#day").empty();
							$(dummy1).appendTo("#day");
							
						}else{
							$("#schemeType option[value="+2+"]").hide();
							var dummy2 = $("#month1").html();
							$("#month").empty();
							$(dummy2).appendTo("#month");
							
							var dummy1 = $("#day1").html();
							$("#day").empty();
							$(dummy1).appendTo("#day");
							
							
						}
					}else{
						var dummy = $("#schemeType1").html();
						$("#schemeType").empty();
						$(dummy).appendTo("#schemeType");
					}
					
				}
				
				
				/* var dummy = $("#parentid1").html();
				$("#parentid").empty();
				$(dummy).appendTo("#parentid");	
				$("#parentid option[value="+serviceUnitArray[id].departmentId+"]").hide();
				$("#id").val(serviceUnitArray[id].departmentId);
				$("#name").val(serviceUnitArray[id].name);
				$('#email').val(serviceUnitArray[id].email);
				$('#departmentHead').val(serviceUnitArray[id].departmentHead);
				$('#parentid').val(serviceUnitArray[id].parentid);
				$('#parentid').trigger("chosen:updated");
				$(window).scrollTop($('#addForm').offset().top); */
				/* var dummy = $("#parentid1").html();
// 				$("#schemeType").empty();
				$("#parentid option[value="+serviceUnitArray[id].departmentId+"]").hide();
				$('#schemeType').val(serviceUnitArray[id].parentid);
				$('#schemeType').trigger("chosen:updated"); */
			},
			error : function(e) {
			},
			statusCode : {
				406 : function() {
				}
			}
		});
}



	var listOrders1 = ${allOrders1};
	if (listOrders1 != "") {
		displayTable(listOrders1);
	}
	function displayTable(listOrders) {
		$('#tableId').html('');
		var tableHead = '<table class="table table-striped table-hover table-bordered"	id="datatable-buttons">'
			+ '<thead><tr><th class="printbtn" style="text-align: center;"></th><th style="text-align: center;">Name</th><th style="text-align: center;">Pooja-Type</th><th style="text-align: center;">Month</th><th style="text-align: center;">Day/Thidi</th><th style="text-align: center;">Gotram</th><th style="text-align: center;">Mobile</th><th class="printbtn" style="text-align: center;"></th></tr></thead><tbody></tbody></table>';
		$('#tableId').html(tableHead);
		serviceUnitArray = {};
		$
				.each(
						listOrders,
						function(i, orderObj) {
// 							var utf8 = unescape(encodeURIComponent(orderObj.gotram));	
														
							serviceUnitArray[orderObj.id] = orderObj;
							var tblRow = "<tr role='row' class='odd'>" 
									+ "<td class='printbtn'><input class='checkall' onclick='selectCheckbox()' type='checkbox' name='checkboxName' id='"+orderObj.id+"' value='"+orderObj.id+"'/></td>"
									+ "<td><a style='cursor: pointer;' href='javascript:void(0)' onclick=viewDonor("+ orderObj.id + ")>"+ orderObj.donorName+ "</a></td>"
//									+ "<td title='"+orderObj.address+"'>"+ orderObj.address+ "</td>"
									+ "<td title='"+orderObj.schemetitle+"'>"+ orderObj.schemetitle+ "</td>"
									+ "<td title='"+orderObj.monthname+"'>"+ orderObj.monthname+ "</td>"
									+ "<td title='"+orderObj.dayname+"'>"+ orderObj.dayname+ "</td>"
									+ "<td title='"+orderObj.gotram+"'>"+ orderObj.gotram+ "</td>"
									+ "<td title='"+orderObj.mobile+"'>"+ orderObj.mobile+ "</td>"
//									+ "<td title='"+orderObj.otherinformation+"'>"+ orderObj.otherinformation+ "</td>"
									+ "<td class='printbtn' style='text-align: center;'>"
									+ '<a href="javascript:void(0)" onclick=editPack('+ orderObj.id + ')'
									+ '  ><i style="color: green;" class="fa fa-edit"></i></a>' + '&nbsp; &nbsp;'
									+ '<a style="color: red;" href="javascript:void(0)" onclick=deleteDonor('+ orderObj.id + ')'
									+ '  ><i class="fa fa-trash-o"></i></a>' + '</td>'
									+ '</tr>';
									$(tblRow).appendTo("#datatable-buttons tbody");
							
							//$("#imageId1").attr('src', "@Url.Content("~/Content/images/ajax_activity.gif)")
						});
		$('#datatable-buttons').DataTable({
	        dom: 'Bfrtip',
			buttons: [{extend:"print",className:"btn default"},{extend:"pdf",className:"btn default"},{extend:"csv",className:"btn default"}]
		});
}

	
	function editPack(id) {
		
// 		address: "Vijayawada"
// 			dayId: "4"
// 			dayname: "4"
// 			donorName: "Sai Chinta"
// 			email: "s@gmail.com"
// 			gotram: "Sai"
// 			id: "5"
// 			mobile: "8143342442"
// 			monthId: "10"
// 			monthname: "October"
// 			otherinformation: "vijayawada"
// 			receiptno: "520007"
// 			schemetipe: "Date"
// 			schemetitle: "Annadanam"
// 			schemetypeId: "1"
		
		
		
		$("#id").val(serviceUnitArray[id].id);
		$('#schemeTitle').val(serviceUnitArray[id].schemetitleId);
		$('#name').val(serviceUnitArray[id].donorName);
		$('#dor').val(serviceUnitArray[id].doreg);
		$('#datha').val(serviceUnitArray[id].datha);
		$('#gotram').val(serviceUnitArray[id].gotram);
		$('#mobile').val(serviceUnitArray[id].mobile);
		$('#email').val(serviceUnitArray[id].email);
		$('#amount').val(serviceUnitArray[id].amount);
		$('#receiptNo').val(serviceUnitArray[id].receiptno);
		$('#schemeType').val(serviceUnitArray[id].schemetypeId);
		
		
		var schemetypeId = serviceUnitArray[id].schemetypeId;
		if(schemetypeId ==2 ){
			var dummy2 = $("#month2").html();
			$("#month").empty();
			$(dummy2).appendTo("#month");
			
			var dummy1 = $("#day2").html();
			$("#day").empty();
			$(dummy1).appendTo("#day");
		}
		if(schemetypeId ==1 ){
			var dummy2 = $("#month1").html();
			$("#month").empty();
			$(dummy2).appendTo("#month");
			
			var dummy1 = $("#day1").html();
			$("#day").empty();
			$(dummy1).appendTo("#day");
		}
		
		$('#month').val(serviceUnitArray[id].monthId);
		$('#day').val(serviceUnitArray[id].dayId);
		$('#otherInformation').val(serviceUnitArray[id].otherinformation);
		$('#address').val(serviceUnitArray[id].address);
		$('#submitform').val('Update');
		$('#donor').text('Edit Donor');
		$(window).scrollTop($('.wrapper').offset().top);
		
	}
	
	function deleteDonor(id){
		
		var id = id;
		/* $('input[name=checkboxName]:checked').map(function() {
			id.push($(this).val());
		}); */
		var count = 0;
		var checkstr =  confirm('Are you sure..!\n You want to delete this record?');
		if(checkstr == true){
		  // do your code
		  
		  $.ajax({
					type : "POST",
					url : "deleteDonor.json",
					data : "id=" + id ,
					success : function(response) {
// 						window.location.href='DonorHome';
						displayTable(response);
					},
					error : function(e) {
					},
					statusCode : {
						406 : function() {
						}
					}
				});
			
		}else{
		return false;
		}
	}
	
	function changeDayMonth(){
	var schemetypeId = 	$('#schemeType').val();
	if(schemetypeId ==2 ){
		var dummy2 = $("#month2").html();
		$("#month").empty();
		$(dummy2).appendTo("#month");
		
		var dummy1 = $("#day2").html();
		$("#day").empty();
		$(dummy1).appendTo("#day");
	}
	if(schemetypeId ==1 ){
		var dummy2 = $("#month1").html();
		$("#month").empty();
		$(dummy2).appendTo("#month");
		
		var dummy1 = $("#day1").html();
		$("#day").empty();
		$(dummy1).appendTo("#day");
	}
	
	}
	
</script>
</body>
</html>