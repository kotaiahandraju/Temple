<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

	<!-- Body Starts Here -->
        <div class="wrapper">
        
<%

String login = null;
login = (String)session.getAttribute("login");
if(login != null)
{
	out.println("<div class='alert alert-success alert-dismissable alert-dismissable fadeIn animated infinite' style='z-index: 10006;'><a class='close' data-dismiss='alert' aria-label='close'>×</a>"+login+"</div>");
	session.setAttribute("login", null);
}

/* String sms = null;
sms = (String)session.getAttribute("sms");
if(sms != null)
{
	out.println("<div class='alert alert-success alert-dismissable alert-dismissable fadeIn animated infinite' style='z-index: 10006;'><a class='close' data-dismiss='alert' aria-label='close'>×</a>"+sms+"</div>");
	session.setAttribute("sms", null);
} */
%>
        
            <div class="container">

                <!-- Page-Title -->
                <div class="row">
                    <div class="col-sm-12">
                        <div class="page-title-box">
                            <div class="btn-group pull-right">
                                <ol class="breadcrumb hide-phone p-0 m-0">
                                    <li><a href="">Temple</a></li>
                                    <li class="active">Home</li>
                                </ol>
                            </div>
                            <h4 class="page-title">Home</h4>
                        </div>
                    </div>
                </div>
                <!-- end page title end breadcrumb -->
                
                <div class="row" id="view_list">
                
                	<div class="col-lg-12">
                        <div class="portlet">
                            <div class="portlet-heading bg-success">
                                <h3 class="portlet-title" id="scheme">
                                    Search Form
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
                                
									<!-- Search Form Starts Here -->
			                    	<form:form id="search" commandName="packCmd" method="post" class="form-horizontal" role="form">
			                    		<div class="row">
	                        				<div class="col-md-12">
												<div class="form-group">
							                    	<label for="inputEmail3" class="col-md-4 col-sm-12 col-xs-12 control-label">Current And Next Month Records By:</label>
							                    	<div class="col-md-6 col-sm-12 col-xs-12">
							                        	<form:select path="schemeType" class="form-control" required="true" onchange="removeBorder(this.id)" >
															<form:option value="" >-- Choose Scheme Type --</form:option>
															<form:options items="${schemetype}"></form:options>
														</form:select>
							                        </div>
							                    </div>
						                    </div>
						                </div>
						                <div class="row">
                        					<div class="col-md-offset-3 col-md-6">
												<div class="form-group">
				                            		<div class="col-md-offset-2 col-md-8">
							                        	<input type="button" id="submitform" class="btn btn-success" onclick="searchSchemeType()" value="Search"/>
														<!-- <input type="reset" class="btn btn-danger" value="Reset"/> -->
														<a href="Home" class="btn btn-danger" id="cancel">Cancel</a>
							                        </div>
							                    </div>
							                </div>
							            </div>
									</form:form>
									<!-- Search Form Ends Here -->

                                </div>
                            </div>
                        </div>
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
	
			                            <h5 style="color: red;">Search Results: </h5>
			                            <div id="tableId">
			                            <table id="datatable-buttons" class="table table-striped table-bordered">
			                                <thead>
				                                <tr>
				                                    <th class='printbtn' style='text-align: center;display:none'></th>
				                                    <th>Name</th>
													<!-- <th>Address</th> -->
													<th>Pooja Type</th>
													<th>Month</th>
													<th>Day/Thidi</th>
													<th>Gotram</th>
													<th>Mobile</th>
													<!-- <th>Other.Info</th> -->
													<th class='printbtn' style='text-align: center;'></th>
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
	<!-- Body Ends Here -->

<!-- 	<script src="assets/js/jquery.min.js"></script> -->
<script type="text/javascript">


	var listOrders1 = ${allOrders1};
	if (listOrders1 != "") {
		displayTable(listOrders1);
	}
	
	function displayTable(listOrders) {
		$('#tableId').html('');
		var tableHead = '<table class="table table-striped table-hover table-bordered showData"	id="datatable-buttons">'
			+ '<thead><tr><th class="printbtn" style="text-align: center;"></th><th style="text-align: center;">Name</th><th style="text-align: center;">Pooja-Type</th><th style="text-align: center;">Month</th><th style="text-align: center;">Day/Thidi</th style="text-align: center;"><th>Gotram</th style="text-align: center;"><th>Mobile</th><th class="printbtn" style="text-align: center;"></th></tr></thead><tbody></tbody></table>';
		$('#tableId').html(tableHead);
		serviceUnitArray = {};
		$
				.each(
						listOrders,
						function(i, orderObj) {
							
// 							 "id","donorName","monthname","schemetype","schemetitle"
							serviceUnitArray[orderObj.id] = orderObj;
							var tblRow = "<tr role='row' class='odd'>" 
									+ "<td class='printbtn'><input class='checkall' onclick='selectCheckbox()' type='checkbox' name='checkboxName' id='"+orderObj.id+"' value='"+orderObj.id+"'/></td>"
									+ "<td title='"+orderObj.donorName+"'>"+ orderObj.donorName+ "</td>"
// 									+ "<td title='"+orderObj.address+"'>"+ orderObj.address+ "</td>"
									+ "<td title='"+orderObj.schemetitle+"'>"+ orderObj.schemetitle+ "</td>"
									+ "<td title='"+orderObj.monthname+"'>"+ orderObj.monthname+ "</td>"
									+ "<td title='"+orderObj.dayname+"'>"+ orderObj.dayname+ "</td>"
									+ "<td title='"+orderObj.gotram+"'>"+ orderObj.gotram+ "</td>"
									+ "<td title='"+orderObj.mobile+"'>"+ orderObj.mobile+ "</td>"
// 									+ "<td title='"+orderObj.otherinformation+"'>"+ orderObj.otherinformation+ "</td>"
									+ "<td class='printbtn' style='text-align: center;'>"
									+ '<a href="javascript:void(0)" onclick=viewDonor('+ orderObj.id + ')'
									+ '  ><i style="color: green;" class="fa fa-eye"></i></a>'
									+ '</td>'
									+ '</tr>';
									$(tblRow).appendTo("#datatable-buttons tbody");
							
						});
		 $('#datatable-buttons').DataTable({
		        dom: 'Bfrtip',		        
				buttons: [{extend:"print",className:"btn default"},{extend:"pdf",className:"btn default"},{extend:"csv",className:"btn default"}]
			});
	        
} 
	
	function searchSchemeType(){
		var id =$("#schemeType").val();
		if (id.length != 0){
		$.ajax({
			type : "POST",
			url : "searchSchemeType.json",
			data : "id=" + id ,
			success : function(response) {
				
//					window.location.href='SchemeHome';
				displayTable(response);
			},
			error : function(e) {
			},
			statusCode : {
				406 : function() {
				}
			}
		});
		}
	}
	
	</script>
	