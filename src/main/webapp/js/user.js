/*
 * Start Display None option
 *
 * If any table comes with no records than it will not display print, send and check options
 */
    var val = $(".stable >tbody >tr").length;
    var count=$.find("#mines_rec").length;
    if(count == 1 || val == 1)
    {
      $(".dis_none").hide();
    }
/*
 * End here Display None option
 */

/*
 * Start Check All option
 *
 * If any checked on Group checkbox than it will check the all check boxes in present page same vice verse remove option
 */
    $("#mno_grp_cb").click(function(){
      if($(this).is(":checked"))
      {
        $("input:checkbox").attr("checked","checked");
        $(".mno_cb").attr('id','mno_cb');
      }
      if($(this).is(":not(:checked)"))
      {
        $("input:checkbox").removeAttr('checked');
        $(".mno_cb").attr('id','');
      }
    });
/*
 * End Check All option
 */

/* Start
 * Checked all checkboxes then Group checkbox will be checked....
 * If atleast one checkbox is unchecked than group check box is unchecked
 */
    $(".mno_cb").click(function(){
        var arr = [];
        var a = $(".mno_cb").length;
        var b = $(".mno_cb:checked").length;
      if(a == b)
        $("#mno_grp_cb").attr("checked","checked");
      else
        $("#mno_grp_cb").removeAttr("checked");
      
      if($(this).is(":checked"))
      {
        $(this).attr('id','mno_cb');
      }
      else $(this).attr('id','');
    });

    $('#sendsms').click(function(){
      var no=[];
      $('.mno_cb').each(function()
      {
        if($(this).is(":checked"))
        {
          if($(this).val() != "")
            no.push($(this).val());
        }
      });
      $("#list_mno").val(no);
    });

/*End
 * Checked all checkboxes Group checkbox will be checked
 */

/*
 * Atleast Check one Checkbox option
 *
 */
    function check_msg()
    {
      var a = $("[name='mno_cb[]']:checked").length;
      if(a == 0)
      {
        alert("Please select atleast one checkbox");
         return false;
      }
      else
      {
        $('.login-window').trigger("click");
      }
    }
/*
 * End Atleast Check one Checkbox option 
 */

/*Start
 * Check validation on sms content
 *
 */
 function check_message_content()
 {
  var val = $("#sms_content").val();
    if(val.trim().length == 0 )
    {
      alert("Please Enter Message");
      return false;
    }
    else
    {
      return true;
    }
 }
/*End
 * Check validation on sms content   
 */