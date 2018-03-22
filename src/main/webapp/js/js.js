function trimString(str)
{
	while (str.charAt(0) ==' ')
	str = str.substring(1);
	while (str.charAt(str.length - 1) == ' ')
	str = str.substring(0, str.length - 1);
	return str;
}

function showDateThidi(){
	
	var dnr_date = $('#date').val('Date');
	var dnr_thidi = $('#date').val('Thidi');
	
//	var rdt_date			=	document.getElementById('rdt_date');
//	var rdt_thidi			=	document.getElementById('rdt_thidi');
	var day_pan				=	document.getElementById('day_pan');
	var day					=	document.getElementById('day');
	var thidi_name_pan		=	document.getElementById('thidi_name_pan');
	var thidi_name			=	document.getElementById('thidi_name');
	var month				=	document.getElementById('month');
	var maasamu				=	document.getElementById('maasamu');
	
	
if(dnr_date){
		
		day_pan.style.display='';
		month.style.display='';
		thidi_name_pan.style.display='none';
		maasamu.style.display='none';		
		day.required=true;
		thidi_name.required=false;
		maasamu.required=false;
		month.required=true;
	}
	
	else if(dnr_thidi){
		
		day_pan.style.display='none';
		month.style.display='none';
		thidi_name_pan.style.display='';
		maasamu.style.display='';
		day.required=false;
		thidi_name.required=true;
		maasamu.required=true;
		month.required=false;
	}
	
	
	/*if(rdt_date.checked==true){
		
		day_pan.style.display='';
		month.style.display='';
		thidi_name_pan.style.display='none';
		maasamu.style.display='none';		
		day.required=true;
		thidi_name.required=false;
		maasamu.required=false;
		month.required=true;
	}
	
	else if(rdt_thidi.checked==true){
		
		day_pan.style.display='none';
		month.style.display='none';
		thidi_name_pan.style.display='';
		maasamu.style.display='';
		day.required=false;
		thidi_name.required=true;
		maasamu.required=true;
		month.required=false;
	}*/
}

function showHideThidi(cntl){
	
	var thidi_lab_pan	=	document.getElementById('thidi_lab_pan');
	var thidi_cont_pan	=	document.getElementById('thidi_cont_pan');
	var day_lab_pan		=	document.getElementById('day_lab_pan');
	var day_cont_pan	=	document.getElementById('day_cont_pan');
 
	if(cntl.value=='thidi'){
		
		thidi_lab_pan.style.display='';
		thidi_cont_pan.style.display='';
		day_lab_pan.style.display='none';
		day_cont_pan.style.display='none';
	}
	
	else if(cntl.value=='date'){
		
		thidi_lab_pan.style.display='none';
		thidi_cont_pan.style.display='none';
		day_lab_pan.style.display='';
		day_cont_pan.style.display='';
	}
	
	else{
		
		thidi_lab_pan.style.display='none';
		thidi_cont_pan.style.display='none';
		day_lab_pan.style.display='none';
		day_cont_pan.style.display='none';
	}
}

function checkMonth(){
	
	var fmMonth				=	document.getElementById('fmMonth');
	var toMonth				=	document.getElementById('toMonth');
	var to_month_lab_pan	=	document.getElementById('to_month_lab_pan');
	var to_month_cont_pan	=	document.getElementById('to_month_cont_pan');
	
	if(fmMonth.value==''){
		
		to_month_lab_pan.style.display='none';
		to_month_cont_pan.style.display='none';
	}
	
	else{
		
		to_month_lab_pan.style.display='';
		to_month_cont_pan.style.display='';
	}
	
	if(fmMonth.value!='' && toMonth.value!='' ){
		
		fmCont=parseInt(fmMonth.value);
		toCont=parseInt(toMonth.value);		
		
		if(fmCont>toCont){
			
			alert('From month must not be greater than the To month');
			fmMonth.value="01";
		}
	}
	
}

function checkCDateThidi(){	
	
	var ctitle		=	document.getElementById('ctitle');
	var c_date		=	document.getElementById('c_date');
	var c_thidi		=	document.getElementById('c_thidi');
	
	if(ctitle.value!='' && c_date.checked==false && c_thidi.checked==false){
		
		alert("Please check the Date / Thidi ");
		c_date.focus();
		return false;
	}
}