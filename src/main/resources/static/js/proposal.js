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
	$("#generalInfo2").show();
	$("#aPaso1").show();
	$(".paso2").hide();
	$(".paso3").hide();
	
	$(".first").click(function(){
	        $("#generalInfo").show();
	        $("#generalInfo2").show();
	        $("#detalleCoti").hide();
	        $("#facturaEscena").hide();
	        $("#aPaso1").show();
	    	$("#aPaso2").show();
    });
	
	$(".second").click(function(){  
        $("#detalleCoti").show();
        $("#generalInfo").hide();
        $("#generalInfo2").hide();
        $("#facturaEscena").hide();
        $("#aPaso1").hide();
    	$("#aPaso2").hide();
	});
	
	$(".third").click(function(){
        $("#facturaEscena").show();  
        $("#detalleCoti").hide();
        $("#generalInfo").hide();
        $("#generalInfo2").hide();
        $("#aPaso1").hide();
    	$("#aPaso2").hide();
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
	
	 
	 $("#aPaso1").click(function(){
			$(".paso1").show();
			$(".paso2").hide();
			$(".paso3").hide();
	 });
	 
	 $("#aPaso2").click(function(){
			$(".paso1").hide();
			$(".paso2").show();
			$(".paso3").hide();
	 });
	 
	 
});

