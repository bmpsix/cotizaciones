/**
 * Justin Zuniga Torres
 */

$( document ).ready(function() {
	
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
  
});