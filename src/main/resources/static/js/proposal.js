/**
 * Justin Zuniga Torres
 */


$( document ).ready(function() {
	$("#generalInfo").show();
	$("#generalInfo2").show();
	$("#aPaso1").show();
	$("#aPaso2").show();
	$(".paso1").show();
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
	 $("#proposalSubmit").click(function(){
		var div = document.getElementById('msg');
		var msg="";
		var initialDate = $("#initialDate").val();
		var endDate = $("#endDate").val();
		var proposalName =$("#proposalName").val();
		var targetText =$("#targetText").val();
		var observations =$("#observations").val();
		
		if(initialDate!=null && endDate!=null && initialDate<endDate && proposalName!=null && targetText!=null && observations!=null && initialDate!="" && endDate!="" && proposalName!="" && targetText!="" && observations!="")
		{
			
			msg = "<p style='color: hsl(153,80%,40%)'>Se guardó la información correctamente <p>";
			document.getElementById("proposalForm").submit();
		}
		else 
		{
			if(initialDate>endDate) msg+="<p style='color:#800000'>La fecha inicial debe ser mayor que la fecha final <p>";
			if(initialDate=="") msg+="<p style='color:#800000'>Debe ingresar la fecha inicial en el Paso 1 <p>";
			if(endDate=="") msg+="<p style='color:#800000'>Debe ingresar la fecha final en el Paso 1 <p>";
			if(proposalName=="") msg+="<p style='color:#800000'>Debe ingresar el nombre de la propuesta <p>";
			if(targetText=="") msg+="<p style='color:#800000'>Debe ingresar el objetivo o muestra en el Paso 2 <p>";
			if(observations=="") msg+="<p style='color:#800000'>Debe Ingresar las observaciones en el Paso 2 <p>";
		}
		div.innerHTML = msg;
	 });
	 
	 
	 
	 
	 
});

