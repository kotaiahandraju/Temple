<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

	<!-- Body Starts Here -->
        <div class="wrapper">
									<% 	
	                                	String created = null;
	                                	created = (String)session.getAttribute("created");
						            	if(created != null)
						            	{
						            		out.println("<div class='alert alert-success alert-dismissable alert-dismissable fadeIn animated infinite' style='z-index: 10006;'>"+created+"</div>");
						                	session.setAttribute("created", null);
									 	}
						            	
						            	String updated = null;
						            	updated = (String)session.getAttribute("updated");
						            	if(updated != null)
						            	{
						            		out.println("<div class='alert alert-warning alert-dismissable alert-dismissable fadeIn animated infinite' style='z-index: 10006;'>"+updated+"</div>");
						                	session.setAttribute("updated", null);
									 	}
                                		
						            	String error = null;
						            	error = (String)session.getAttribute("error");
						            	if(error != null)
						            	{
						            		out.println("<div class='alert alert-danger alert-dismissable alert-dismissable fadeIn animated infinite' style='z-index: 10006;'>"+error+"</div>");
						                	session.setAttribute("error", null);
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
                                    <li class="active">Schemes</li>
                                </ol>
                            </div>
                            <h4 class="page-title">Schemes</h4>
                        </div>
                    </div>
                </div>
                <!-- end page title end breadcrumb -->
                
                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="portlet">
                            <div class="portlet-heading bg-success">
                                <h3 class="portlet-title" id="scheme">
                                    Create Scheme
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
                                
						<!-- Add Scheme Form Starts Here -->
                    	<form:form id="create" commandName="packCmd" method="post" class="form-horizontal" role="form">
                        	<div class="form-group">
								<form:hidden path="id" />
                            	<label for="inputEmail3" class="col-md-3 col-sm-3 control-label">Scheme Title<span style="color: red;">*</span>:</label>
                                <div class="col-md-6 col-sm-9">
                            		<form:input path="name" placeholder="Enter Scheme Title" class="form-control onlyCharacters" onkeydown="removeBorder(this.id);" required="true"/>
                                </div>
                            </div>
                            <div class="form-group">
                            	<label for="inputPassword4" class="col-md-3 col-sm-3 control-label">Select Date/Thidi<span style="color: red;">*</span>:</label>
                            	<div class="col-md-6 col-sm-6">
									<select name="date" id="date" class="form-control" onchange="removeBorder(this.id);" required>
										<option value="">-- Select Type --</option>
										<option value="1">Date</option>
										<option value="2">Thidi</option>
										<option value="3">Both</option>
									</select>
                                </div>
                            </div>
							<div class="form-group m-b-0">
                            	<div class="col-sm-offset-3 col-sm-9">
                                	<input type="submit" id="submitform" class="btn btn-success" value="Create"/>
									<input type="button" class="btn btn-danger" id="cancel" onclick="resetForm()" value="Reset"/>
                                </div>
                        	</div>
						</form:form>
						<!-- Add Scheme Form Ends Here -->

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                
                <div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<!-- Schemes DataTable Starts Here -->
									<div class="card-box table-responsive">
										<h4 style="color: red;">List of Schemes</h4>
			                            <div id="tableId">
			                            <table id="datatable-buttons" class="table table-striped table-bordered">
			                                <thead>
				                                <tr>
				                                    <th>Scheme title</th>
													<th>Date/Thidi</th>
													<th style="text-align: center;">Action</th>
				                                </tr>
			                                </thead>
			                                <tbody>
			                                
			                                </tbody>
			                            </table>
			                            </div>
									</div>
						<!-- Schemes DataTable ends Here -->
						
					</div>
                </div>

            </div>
        </div>
	<!-- Body ends Here -->


<!-- <script src="assets/js/jquery.min.js"></script> -->
<script type="text/javascript">

var serviceUnitArray ={};
var serviceUnitArray1 ={};

//Create Scheme Form Validation Starts here
$('#submitform').click(function(e){
if( $('#name').val().length == 0 || $('#name').val() ==" " || $('#name').val()=="undefined"|| $('#date').val() == null || $('#date').val() == "" || $('#date').val()=="undefined")
{												   
	if($('#name').val().length == 0 || $('#name').val()=="" || $('#name').val()=="undefined" ) {
	    $('#name').css('color','red');
	    $("#name").css("border-color","#e73d4a");
	    $("#name").attr("placeholder","Please Enter scheme title");
	    $('#name').addClass('your-class');
    }
	if($('#date').val() ==  null || $('#date').val() == ""  || $('#date').val()=="undefined" ) {
	    $('#date').css('color','red');
	    $("#date").css("border-color","#e73d4a");
	    $('#date').addClass('your-class');
    }
	return false;												  
} 
    
$('#create').attr('action',"AddScheme");
$("#create").submit();											
event.preventDefault();	
});

function resetForm()
{
	removeBorder('name');
	$('#name').val("");
	$('#name').removeClass('your-class default-class');
	
	removeBorder('date');
	$('#date').val("");
	$('#date').removeClass('your-class default-class');
}
//Create Scheme Form Validation ends here

// Add Thidi Form Validation Starts here
$('#thidiform').click(function(e){
	if( $('#tname').val().length == 0 || $('#tname').val() ==" " || $('#tname').val()=="undefined")
	{												   
		if($('#tname').val().length == 0 || $('#tname').val()=="undefined") {
		    $('#tname').css('color','red');
		    $("#tname").css("border-color","#e73d4a");
		    $("#tname").attr("placeholder","Please Enter Thidi Name");
		    $('#tname').addClass('your-class');
	    }
		return false;												  
	} 
	    
	$('#createthidi').attr('action',"AddThidi");
	$("#createthidi").submit();											
	event.preventDefault();	
	});

function resetThidiForm()
{
	removeBorder('tname');
	$('#tname').val("");
	$('#tname').removeClass('your-class default-class');
	
}
//Add Thidi Form Validation Ends here

	var listOrders1 = ${allOrders1};
	if (listOrders1 != "") {
		displayTable(listOrders1);
	}
	
	function displayTable(listOrders) {
		$('#tableId').html('');
		var tableHead = '<table class="table table-striped table-hover table-bordered"	id="datatable-buttons">'
				+ '<thead><tr><th style="text-align: center;">Scheme Title</th><th style="text-align: center;">Date/Thidi</th><th style="text-align: center;"></th>	</tr></thead><tbody></tbody></table>';
		$('#tableId').html(tableHead);
		serviceUnitArray = {};
		$
				.each(
						listOrders,
						function(i, orderObj) {
														
							serviceUnitArray[orderObj.id] = orderObj;
							var tblRow = "<tr role='row' class='odd'>" 
									+ "<td title='"+orderObj.name+"'>"+ orderObj.name+ "</td>"
									+ "<td>"+ orderObj.date+ "</td>"
									+ "<td style='text-align: center;'>"
									+ '<a href="javascript:void(0)" onclick=editPack('+ orderObj.id + ')'
									+ '  ><i style="color: green;" class="fa fa-edit"></i></a>' + '&nbsp;  &nbsp;'
									+ '<a style="color: red;" href="javascript:void(0)" onclick=deleteScheme('+ orderObj.id + ')'
									+ '  ><i class="fa fa-trash-o"></i></a>' + '</td>'
									+ '</tr>';
									$(tblRow).appendTo("#datatable-buttons tbody");
							
							//$("#imageId1").attr('src', "@Url.Content("~/Content/images/ajax_activity.gif)")
						});
// 		$('#datatable-buttons').dataTable();
		$('#datatable-buttons').DataTable({
	        dom: 'Bfrtip',
			buttons: [{extend:"print",className:"btn default"},{extend:"pdf",className:"btn default"},{extend:"csv",className:"btn default"}]
		});
} 

	
	function editPack(id) {
		var transactionId = serviceUnitArray[id].id;
		$("#id").val(serviceUnitArray[id].id);
		$('#name').val(serviceUnitArray[id].name);
		$('#date').val(serviceUnitArray[id].schemetype);
		$('#date').trigger("chosen:updated");
		$('#submitform').val('Update');
		$('#scheme').text('Edit Scheme');
		$(window).scrollTop($('.wrapper').offset().top);
// 		var body = $("html, body");
// 		body.stop().animate({scrollTop:0}, 100, 'swing', function() { 
//   	alert("Finished animating");
// });
	}
	
	function deleteScheme(id){
		
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
					url : "deleteScheme.json",
					data : "id=" + id ,
					success : function(response) {
// 						window.location.href='SchemeHome';
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
</script>
</body>
</html>