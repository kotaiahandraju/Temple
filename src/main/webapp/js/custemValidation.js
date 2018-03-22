/*  $('.nospecialCharacter').bind('keypress', function(e) {
	    console.log( e.which );
	        var k = e.which;
	        var ok = k >= 65 && k <= 90 || // A-Z
	            k >= 97 && k <= 123 || // a-z
	            k >= 48 && k <= 57; // 0-9
	        
	        if (!ok){
	            e.preventDefault();
	        }
	});*/
  
 /* if(!(inputValue >= 65 && inputValue <= 123) && (inputValue != 32 && inputValue != 0)) { 
      event.preventDefault(); 
  }
 function changetext(){
$(".capsOnly").val(function () {
	    return this.value.toUpperCase();
	}); 
}*/


$(".capsOnly").keyup(function () {
this.value= this.value.toUpperCase();
}); 

/*$(".capsOnly").keypress(function (e) {
 if (String.fromCharCode(e.keyCode).match(/[^A-Z]/g)) return false;
});*/


/*$(".numericOnly").keydown(function (e) {
 if (String.fromCharCode(e.keyCode).match(/[^0-9]/g)) return false;
});
*/

jQuery('.numericOnly').keyup(function () {  
    this.value = this.value.replace(/[^0-9\.]/g,''); 
});

/*$('.numericOnly').keydown(function (event) {

    var keycode = event.which;   

    if (!/[\d\t\b+]/.test(String.fromCharCode(event.which)) || event.shiftKey ) {
        return false;
    } else {
        return true;
    }
});*/

$(".numericOnly").keydown(function (e) {
    // Allow: backspace, delete, tab, escape, enter and .
    if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
         // Allow: Ctrl+A, Command+A
        (e.keyCode === 65 && (e.ctrlKey === true || e.metaKey === true)) || 
         // Allow: home, end, left, right, down, up
        (e.keyCode >= 35 && e.keyCode <= 40)) {
             // let it happen, don't do anything
             return;
    }
    // Ensure that it is a number and stop the keypress
    if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
        e.preventDefault();
    }
});


$(".onlyCharacters").on("keypress", function(event) {

    // Disallow anything not matching the regex pattern (A to Z uppercase, a to z lowercase and white space)
    var englishAlphabetAndWhiteSpace = /[A-Za-z. ]/g;
   
    // Retrieving the key from the char code passed in event.which
    var key = String.fromCharCode(event.which);
    
    //alert(event.keyCode);
    
    if (event.keyCode == 8 || event.keyCode == 9 || event.keyCode == 37 || event.keyCode == 39 || englishAlphabetAndWhiteSpace.test(key)) {
        return true;
    }

    // If we got this far, just return false because a disallowed key was typed.
    return false;
});

$('.onlyCharacters').on("paste",function(e)
{
    e.preventDefault();
});

var mailId = true;

$('.emailOnly').on('blur', function() {
    var re = /([A-Z0-9a-z_-][^@])+?@[^$#<>?]+?\.[\w]{2,4}/.test(this.value);
    if(!re) {
//         $('#error').show();
    	$('#email').css('color','red');
	    $("#email").css("border-color","#e73d4a");
	    $("#email").attr("placeholder","Please Enter valid Email-ID");
	    $('#email').addClass('your-class');
//	    $('#email').focus();
	    mailId = false;
	    return false;
    } else {
//         $('#error').hide();
    	mailId = true;
		return true;
    }
})


/*
$(document).ready(function () {
$('#txtDesc').bind('keyup', function () {
 var txtToMatch = /article/gi;
 var iLimit = 1;
 var sMatch = $(this).val().match(txtToMatch);      
 if (sMatch !== null && sMatch.length > iLimit) {
     $(".error").html("The word 'article' can occur only once.");
 } else {
     $(".error").html("");
 }
});
});

*/