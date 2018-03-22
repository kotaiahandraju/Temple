<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

	<!-- Body Starts Here -->
        <div class="wrapper">
									<% 	
	                                	String tcreated = null;
	                                	tcreated = (String)session.getAttribute("tcreated");
						            	if(tcreated != null)
						            	{
						            		out.println("<div class='alert alert-success alert-dismissable alert-dismissable fadeIn animated infinite' style='z-index: 10006;'><a class='close' data-dismiss='alert' aria-label='close'>×</a>"+tcreated+"</div>");
						                	session.setAttribute("tcreated", null);
									 	}
						            	
						            	String tupdated = null;
						            	tupdated = (String)session.getAttribute("tupdated");
						            	if(tupdated != null)
						            	{
						            		out.println("<div class='alert alert-warning alert-dismissable alert-dismissable fadeIn animated infinite' style='z-index: 10006;'><a class='close' data-dismiss='alert' aria-label='close'>×</a>"+tupdated+"</div>");
						                	session.setAttribute("tupdated", null);
									 	}
                                		
						            	String terror = null;
						            	terror = (String)session.getAttribute("terror");
						            	if(terror != null)
						            	{
						            		out.println("<div class='alert alert-danger alert-dismissable alert-dismissable fadeIn animated infinite' style='z-index: 10006;'><a class='close' data-dismiss='alert' aria-label='close'>×</a>"+terror+"</div>");
						                	session.setAttribute("terror", null);
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
                                    <li class="active">Thidi</li>
                                </ol>
                            </div>
                            <h4 class="page-title">Thidi</h4>
                        </div>
                    </div>
                </div>
                <!-- end page title end breadcrumb -->
                
                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="portlet">
                            <div class="portlet-heading bg-success">
                                <h3 class="portlet-title" id="thidis">
                                    Create Festival/Thidi
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
                                
						<!-- Add Thidi Form Starts Here -->
                    	<form:form id="createthidi" commandName="packCmd" method="post" class="form-horizontal" role="form">
                        	<div class="form-group">
								<form:hidden path="tid" />
                            	<label for="inputEmail3" class="col-md-3 col-sm-3 control-label">Festival/Thidi Name<span style="color: red;">*</span>:</label>
                                <div class="col-md-6 col-sm-9">
                            		<form:input path="tname" placeholder="Enter Festival/Thidi Name" class="form-control onlyCharacters" onkeydown="removeBorder(this.id);" required="true"/>
                                </div>
                            </div>
							<div class="form-group">
                            	<div class="col-sm-offset-3 col-sm-9">
                                	<input type="submit" id="thidiform" class="btn btn-success" value="Add"/>
									<input type="button" class="btn btn-danger" id="cancel" onclick="resetThidiForm()" value="Reset"/>
                                </div>
                        	</div>
						</form:form>
						<!-- Add Thidi Form Ends Here -->

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                
                <div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<!-- Thidi DataTable Starts Here -->
									<div class="card-box table-responsive">
										<h4 style="color: red;">List of Thidi's</h4>
			                            <div id="tableId2">
			                            <table id="datatable-buttons" class="table table-striped table-bordered">
			                                <thead>
				                                <tr>
				                                    <th>Thidi Name</th>
													<th style="text-align: center;">Action</th>
				                                </tr>
			                                </thead>
			                                <tbody>
			                                
			                                </tbody>
			                            </table>
			                            </div>
									</div>
						<!-- Thidi DataTable ends Here -->
						
					</div>
                </div>

            </div>
        </div>
	<!-- Body ends Here -->


<!-- <script src="assets/js/jquery.min.js"></script> -->
<script type="text/javascript">

var serviceUnitArray ={};
var serviceUnitArray1 ={};


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

	
	var listOrders2 = ${allOrders2};
	if (listOrders2 != "") {
		displayTable2(listOrders2);
	}
	
	function displayTable2(listOrders2) {
		$('#tableId2').html('');
		var tableHead2 = '<table class="table table-striped table-hover table-bordered"	id="datatable-buttons">'
				+ '<thead><tr><th style="text-align: center;">Thidi Name</th><th style="text-align: center;"></th>	</tr></thead><tbody></tbody></table>';
		$('#tableId2').html(tableHead2);
		serviceUnitArray1 = {};
		$
				.each(
						listOrders2,
						function(i, orderObj2) {
														
							serviceUnitArray1[orderObj2.tid] = orderObj2;
							var tblRow2 = "<tr role='row' class='odd'>" 
									+ "<td title='"+orderObj2.thidiname+"'>"+ orderObj2.thidiname+ "</td>"
									+ "<td style='text-align: center;'>"
									+ '<a href="javascript:void(0)" onclick=editThidi('+ orderObj2.tid + ')'
									+ '  ><i style="color: green;" class="fa fa-edit"></i></a>' + '&nbsp;  &nbsp;'
									+ '<a style="color: red;" href="javascript:void(0)" onclick=deleteThidi('+ orderObj2.tid + ')'
									+ '  ><i class="fa fa-trash-o"></i></a>' + '</td>'
									+ '</tr>';
									$(tblRow2).appendTo("#datatable-buttons tbody");
							
							//$("#imageId1").attr('src', "@Url.Content("~/Content/images/ajax_activity.gif)")
						});
// 		$('#datatable-buttons').dataTable();
		$('#datatable-buttons').DataTable({
	        dom: 'Bfrtip',
			buttons: [{extend:"print",className:"btn default"},{extend:"pdf",className:"btn default"},{extend:"csv",className:"btn default"}]
		});
	}
	
	function editThidi(tid) {
		var transactionId1 = serviceUnitArray1[tid].tid;
		$("#tid").val(serviceUnitArray1[tid].tid);
		$('#tname').val(serviceUnitArray1[tid].thidiname);
		$('#thidiform').val('Update');
		$('#thidis').text('Edit Thidi');
		$(window).scrollTop($('.wrapper').offset().top);
	}
	
	function deleteThidi(tid){
		
		var tid = tid;
		/* $('input[name=checkboxName]:checked').map(function() {
			id.push($(this).val());
		}); */
		var count = 0;
		var checkstr =  confirm('Are you sure..!\n You want to delete this record?');
		if(checkstr == true){
		  // do your code
		  
		  $.ajax({
					type : "POST",
					url : "deleteThidi.json",
					data : "tid=" + tid ,
					success : function(response) {
						window.location.href='ThidiHome';
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