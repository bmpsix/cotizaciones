/**
 * Justin Zuniga Torres
 */
/*
$( document ).ready(function() {
	$("#generalInfo").show();
	$(".first").click(function(){
	        $("#generalInfo").show();
	        $("#detalleCoti").hide();
	        $("#facturaEscena").hide();
    });
	
	$(".second").click(function(){  
        $("#detalleCoti").show();
        $("#generalInfo").hide();
        $("#facturaEscena").hide();
	});
	
	$(".third").click(function(){
        $("#facturaEscena").show();  
        $("#detalleCoti").hide();
        $("#generalInfo").hide();
	});
  
	 $('#getId a').click(function(){
		   var a = $(this);
		  $("#idTechnique").val(a.attr("value"));
		 });
	
});*/


$( document ).ready(function() {
	$("#generalInfo").show();
	$("#generalInfo2").hide();
	$("#detalleCoti2").show();
	$(".first").click(function(){
	        $("#generalInfo").show();
	        $("#generalInfo2").show();
	        $("#detalleCoti").hide();
	        $("#detalleCoti2").hide();
	        $("#facturaEscena").hide();
    });
	
	$(".second").click(function(){  
        $("#detalleCoti").show();
        $("#detalleCoti2").show();
        $("#generalInfo").hide();
        $("#generalInfo2").hide();
        $("#facturaEscena").hide();
	});
	
	$(".third").click(function(){
        $("#facturaEscena").show();  
        $("#detalleCoti").hide();
        $("#generalInfo").hide();
	});
  
	 $('#getId a').click(function(){
		   var a = $(this);
		  $("#idTechnique").val(a.attr("value"));
		 });
	 
	 $(".cal").change(function(){
		 var total = 0;
		 total=$("#price").val() * $("#number").val() * $("#daysTimes").val();
		 $("#totalBudget").text(total);
		//alert(total);
	 });
	
});

