
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%@ include file="userHeader.jsp" %>

	<!-- Body Starts Here -->
        <div class="wrapper">
						
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
				                                 <th class="printbtn" style="text-align: center;"></th>
				                                    <th>Name</th>
													<!-- <th>Address</th> -->
													<th>Pooja Type</th>
													<th>Month</th>
													<th>Day/Thidi</th>
													<th>Gotram</th>
													<th>Mobile</th>
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


<script src="assets/js/jquery.min.js"></script>
<script type="text/javascript">



	var listOrders1 = ${allOrders1};
	if (listOrders1 != "") {
		displayTable(listOrders1);
	}
	
	function displayTable(listOrders) {
		$('#tableId').html('');
		var tableHead = '<table class="table table-striped table-hover table-bordered"	id="datatable-buttons">'
			+ '<thead><tr><th class="printbtn" style="text-align: center;"></th><th>Name</th><th>Pooja Type</th><th>Month</th><th>Day/Thidi</th><th>Gotram</th><th>Mobile</th></tr></thead><tbody></tbody></table>';
		$('#tableId').html(tableHead);
		serviceUnitArray = {};
		$
				.each(
						listOrders,
						function(i, orderObj) {
							
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
									
									+ '</tr>';
									$(tblRow).appendTo("#datatable-buttons tbody");
							
							//$("#imageId1").attr('src', "@Url.Content("~/Content/images/ajax_activity.gif)")
						});
		$('#datatable-buttons').DataTable({
	        dom: 'Bfrtip',
			buttons: [{extend:"print",className:"btn default"},{extend:"pdf",className:"btn default"},{extend:"csv",className:"btn default"}]
		});
}
</script>

<%@ include file="userFooter.jsp" %>