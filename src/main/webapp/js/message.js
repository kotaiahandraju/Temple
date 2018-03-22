$(function(){
	$('#sendSMS').hide();
	$("#checkAll").change(function () {
		$("input:checkbox").prop('checked', $(this).prop("checked"));
		var len=$("[name='checkboxName']:checked").length;
		if(len!=0){ $('#sendSMS').show(); }
		else{ $('#sendSMS').hide(); }
	});

	/*$(".checkall").click(function () {
		var len=$("[name='checkboxName']:checked").length;
		if(len!=0){ $('#sendSMS').show(); }
		else{ $('#sendSMS').hide(); }
	});*/
});

function sendSms(){
	
	/*  $("#studentId_error").text('');
	 $("#messageId_error").text('');
	 $("#notificatinId_error").text('');
	 $("#absentId_error").text(''); */
		var donorId = [];
		$('input[name=checkboxName]:checked').map(function() {
			donorId.push($(this).val());
		});
//			var message="AAAAAAAA";
		var message=$("#message").val();
		if(donorId.length == 0 || donorId.length == "undefined" || message.length == 0 || message.length == "undefined"){
			
			if(donorId.length == 0 || donorId.length == "undefined"){
				alert("Please Select atleast One Donor");
				return false;
			}
			if(message.length == 0 || message.length == "undefined"){
				$('#message').css('color','red');
			    $("#message").css("border-color","#e73d4a");
			    $("#message").attr("placeholder","Please Enter Message");
			    $('#message').addClass('your-class');
			    $('#message').focus();
//					alert("Please Enter Message");
				return false;
			}
		}else{
			 $("#sendBtn").attr("disabled",true);
			 $("#sendBtn").val("Please wait...");
		 $.ajax({
				type : "POST",
				url : "sendSms.json",
				 dataType: 'text',
				data : "donorId=" + donorId+"&message="+message,
				success : function(response) {
//					alert(response);
					console.log(response);
					var yourval = jQuery.parseJSON(response);
//					var yourval1  = JSON.stringify(response);
					alert(yourval.message);
					/*$("textarea#message").val("");
					$('input[type=checkbox]').each(function() 
					{ 
						this.checked = false; 
					});*/
					$('#smsForm').trigger("reset");
					location.reload();
//					window.location.href='';
					
				},
				/*error : function(e) {
					$('#smsForm').trigger("reset");
					alert(e);
				}*/
			});
		 $('#smsForm').trigger("reset");
		}
		$('#smsForm').trigger("reset");
 }

function resetFunction()
{
	removeBorder('message');
	$('#message').val("");
	$('#message').removeClass('your-class default-class');
}

function selectCheckbox(){
	var len=$("[name='checkboxName']:checked").length;
	if(len!=0){ $('#sendSMS').show(); }
	else{ $('#sendSMS').hide(); }
}



function viewDonor(id){
	
	var name = null; name = serviceUnitArray[id].donorName;
	if(name == null || name == ""){name = "---";}
	
	var doreg = null; doreg = serviceUnitArray[id].doreg;
	if(doreg == null || doreg == ""){doreg = "---";}
	
	var schemetitle = null; schemetitle = serviceUnitArray[id].schemetitle;
	if(schemetitle == null || schemetitle == ""){schemetitle = "---";}
	
	var gotram = null; gotram = serviceUnitArray[id].gotram;
	if(gotram == null || gotram == ""){gotram = "---";}
	
	var datha = null; datha = serviceUnitArray[id].datha;
	if(datha == null || datha == ""){datha = "---";}
	
	var mobile = null; mobile = serviceUnitArray[id].mobile;
	if(mobile == null || mobile == ""){mobile = "---";}
	
	var monthname = null; monthname = serviceUnitArray[id].monthname;
	if(monthname == null || monthname == ""){monthname = "---";}
	
	var dayname = null; dayname = serviceUnitArray[id].dayname;
	if(dayname == null || dayname == ""){dayname = "---";}
	
	var email = null; email = serviceUnitArray[id].email;
	if(email == null || email == ""){email = "---";}

	var otherinformation = null; otherinformation = serviceUnitArray[id].otherinformation;
	if(otherinformation == null || otherinformation == ""){otherinformation = "---";}
	
	var address = null; address = serviceUnitArray[id].address;
	if(address == null || address == ""){address = "---";}
	
	var amount = null; amount = serviceUnitArray[id].amount;
	if(amount == null || amount == ""){amount = "---";}
	
	var receiptno = null; receiptno = serviceUnitArray[id].receiptno;
	if(receiptno == null || receiptno == ""){receiptno = "---";}
	
	var tblRow = "<table class='table table-condensed' border='1' style='border-collapse:collapse;background-color: #DEE2EA;font-size: 12px;width: 100%;'>"
		+"<tr class='printbtn'>"
		+	"<td style='border: none;'><input id='printbtn' style='float: left;' class='btn btn-primary' type='button' value='Print' onclick=PrintElem('#showData') /></td>"
		+	"<td style='border: none;'>"
		+		"<a style='cursor: pointer;float: right;color: red;' onclick='getBack()'><i class='fa fa-2x fa-close'></i></a>&nbsp;&nbsp;"
		+	"</td>"
		+"</tr>"
		+"<tr><th colspan='2' style='border-top: 1px solid;'><img src='assets/images/header.jpg' style='width: 100%;'/></th></tr>"
		+"<tr><td style='padding: 5px;font-weight: bold;'>Registration Date</td><td style='padding: 5px;'>"+doreg+"</td></tr>"
		+"<tr><td style='padding: 5px;font-weight: bold;'>Name</td><td style='padding: 5px;'>"+name+"</td></tr>"
		+"<tr><td style='padding: 5px;font-weight: bold;'>Scheme Title</td><td style='padding: 5px;'>"+schemetitle+"</td></tr>"
		+"<tr><td style='padding: 5px;font-weight: bold;'>Name of the Donor</td><td style='padding: 5px;'>"+datha+"</td></tr>"
		+"<tr><td style='padding: 5px;font-weight: bold;'>Gotram</td><td style='padding: 5px;'>"+gotram+"</td></tr>"
		+"<tr><td style='padding: 5px;font-weight: bold;'>Month/Maasam</td><td style='padding: 5px;'>"+monthname+"</td></tr>"
		+"<tr><td style='padding: 5px;font-weight: bold;'>Day/Thidi</td><td style='padding: 5px;'>"+dayname+"</td></tr>"
		+"<tr><td style='padding: 5px;font-weight: bold;'>Mobile</td><td style='padding: 5px;'>"+mobile+"</td></tr>"
		+"<tr><td style='padding: 5px;font-weight: bold;'>Other Information</td><td style='padding: 5px;'>"+otherinformation+"</td></tr>"
//			+"<tr><th style=''>Address</th><td style='padding: 5px;font-weight: bold;'>"+address+"</td></tr>"
		+"<tr><td style='padding: 5px;font-weight: bold;'>Email</td><td style='padding: 5px;'>"+email+"</td></tr>"
		+"<tr><td style='padding: 5px;font-weight: bold;'>Donation Amount</td><td style='padding: 5px;'>"+amount+"</td></tr>"
		+"<tr><td style='padding: 5px;font-weight: bold;'>Receipt Number</td><td style='padding: 5px;'>"+receiptno+"</td></tr>"
		+"</table>"
		
		+"<table style='width: 35%;' align='center'>"
		+"<tr><th colspan='2'><br/><br/><br/><br/><br/><br/></th><tr>"
//		+"<tr><th colspan='2' style='font-size: 17px;'>To Address:</th><tr>"
		+"<tr><td colspan='2' style='border: none;padding: 5px;font-size: 13px;'>"+name+"</td></tr>"
		+"<tr><td colspan='2' style='border: none;padding: 5px;font-size: 13px;'>"+address+"</td></tr>"
		+"<tr><td colspan='2' style='border: none;padding: 5px;font-size: 13px;'>"+mobile+"</td></tr>"
		+"</table>";
	$(tblRow).appendTo("#showData");
	$(window).scrollTop($('.wrapper').offset().top);
	$("#view_list").hide();
	$('#view_list1').hide();
//	$('#view_list2').hide();
}

function getBack()
{
	$('#showData').html('');
	$('#view_list').show();
	$('#view_list1').show();
//	$('#view_list2').show();
}

function PrintElem(elem)
{
	$(".printbtn").hide();
    Popup($(elem).html());
}


function Popup(data)
{
	var mywindow = window.open('','new div');

    var is_chrome = Boolean(mywindow.chrome);
    var isPrinting = false;
    mywindow.document.write('<html><head><title>Donor Details</title></head><body>');
    mywindow.document.write(data);
    mywindow.document.write('</body></html>');
    mywindow.document.close(); // necessary for IE >= 10 and necessary before onload for chrome

$(".printbtn").show();
    if (is_chrome) {
        mywindow.onload = function() { // wait until all resources loaded 
            mywindow.focus(); // necessary for IE >= 10
            mywindow.print();  // change window to mywindow
            mywindow.close();// change window to mywindow
        };
    
    
   } else {
        mywindow.document.close(); // necessary for IE >= 10
        mywindow.focus(); // necessary for IE >= 10

        mywindow.print();
        mywindow.close();
        $(".printbtn").show();
   }
	
	
	
   /* var mywindow = window.open('', 'new div');
    mywindow.document.write('<html><head><title>Donor Details</title></head><body>');
    mywindow.document.write(data);
    mywindow.document.write('</body></html>');
    mywindow.print();
    mywindow.close();
    $(".printbtn").show();*/
    return true;
}