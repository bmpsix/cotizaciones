/**
 * Justin Zuniga Torres
 */


$( document ).ready(function() {

	//Manejo de los div de proposal y proposaldetails
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
	 
	 //Calcular el total de presupuesto de cada detalle
	 $(".cal").change(function(){
		 var total = 0;
		 total=$("#price").val() * $("#number").val() * $("#daysTimes").val();
		 $("#totalBudget").val(total);
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
	 
	 //Validar los datos antes del submit
	 $("#proposalSubmit").click(function(){
		var div = document.getElementById('msg');
		var msg="";
		var initialDate = $("#initialDate").val()
		var endDate = $("#endDate").val()
		var proposalName =$("#proposalName").val();
		var targetText =$("#targetText").val();
		var observations =$("#observations").val();
		
		
		if(initialDate!=null && endDate!=null && (new Date(initialDate).getTime() <= new Date(endDate).getTime())&& proposalName!=null && targetText!=null && observations!=null && initialDate!="" && endDate!="" && proposalName!="" && targetText!="" && observations!="")
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
	 
		$("#addProposalDetail").click(function(){
			$(".form-proposaldetails").toggle("slow");
		});
		
		$(".ui-filterable").focusin(function(){
			$(".form-proposaldetails").hide("slow");
		});
		
		
		$(function () {
		    var token = $("input[name='_csrf']").val();
		    var header = "X-CSRF-TOKEN";
		    $(document).ajaxSend(function(e, xhr, options) {
		        xhr.setRequestHeader(header, token);
		    });
		});
		
		$("#sendProposalDetail").click(function(){
			
			
			var idProposalDetails =$("#idProposalDetails").val();
			var aporteFijo =$("#aporteFijo").val();
			var factor1 = $("#factor1").val();
			var factor2 = $("#factor2").val();
			var detail = $("#detail").val();
			var parameters = $("#parameters").val();
			var imprevisto = $("#imprevisto").val();
			var idDeparture = $("#idDeparture").val();
			var price = $("#price").val();
			var commissionable = $("#commissionable").val();
			var number = $("#number").val();
			var daysTimes = $("#daysTimes").val();
			var totalBudget = $("#totalBudget").val();
			
			if(idProposalDetails=="" || idProposalDetails==null) idProposalDetails=0;


			var url = "/admin/addproposaldetails"; // El script a dónde se realizará la petición.
			    $.ajax({
			           type: "POST",
			           cache: false,
			           url: url,
			           data: { 
			        	  'idProposalDetails':idProposalDetails, 
			   			 'aporteFijo': aporteFijo,
			   			 'factor1': factor1,
			   			'factor2': factor2,
			   			 'detail':detail,
			   			 'parameters':parameters,
			   			 'imprevisto': imprevisto,
			   			 'idDeparture': idDeparture,
			   			 'price': price,
			   			 'commissionable': commissionable,
			   			 'number': number,
			   			 'daysTimes': daysTimes,
			   			 'totalBudget': totalBudget
			   			
			          
			        		 },  // Adjuntar los campos del formulario enviado.

			           success: function(data)
			           {
			        	   if(data != null){
			        		   $("#idProposalDetails").val(0);
			        		   totalcharge();
			        		   location.reload();
			        	   }else{
			        		   alert("false");
			        		   }
			        	   }
			         });
		
		});
	 
		$("#cancelProposalDetail").click(function(){
			
			
			$("#idProposalDetails").val(0);
			$("#price").val("");
			$("#commissionable").val(1);
			$("#number").val("");
			$("#daysTimes").val("");
			$("#totalBudget").val("");
			$("#detail").val("");
			$("#parameters").val("");
			$(".form-proposaldetails").hide("slow");
		});
		
		$("#idDeparture").change(function()
		{
			
			$("#searchForSelect2").val($("#idDeparture").val()).change();
			/*$("#listDeparture option").each(function(){
			   alert('opcion '+$(this).text()+' valor '+ $(this).attr('value'))
			});*/
			
			
			
		});
		
		
		
		//METODO PARA SUBTOTALES Y TOTALES
		
		
		
});

// Calculo de totales
function totalcharge()
{
	var sub1 =0;
	var imprevisto = $("#imprevisto").val();
	var totalImprevisto=0;
	var sub2=0;
	var factor1 = $("#factor1").val();
	var sub3=0;
	var aporteFijo =$("#aporteFijo").val();
	var total1=0;
	var sub4=0;
	var factor2 = $("#factor2").val();
	var sub5=0;
	var total2=0;
	var nacional1=0;
	var nacional2=0;
	var currencyExchange =$("#currencyExchange").val();
	var montoImprevisto =0;
	var table = document.getElementById("proposalDetailsTable");
	
	for(contador=1;contador<=table.rows.length-1;contador++)
	{
		var valor = table.rows[contador].cells[5].innerText;
		var cms = table.rows[contador].cells[8].innerText;
		valor = (valor.split(table.rows[1].cells[5].children[0].innerText).toString()).substring(1);
		if(cms==1)sub1=sub1+parseInt(valor);
		else sub4=sub4+parseInt(valor);
	}
	sub1=sub1/currencyExchange;
	totalImprevisto=sub1*(imprevisto/100);
	sub2 = sub1+totalImprevisto;
	sub3 = sub2*factor1;
	montoImprevisto =  sub3*aporteFijo;
	total1 = sub3 + montoImprevisto;
	sub5=sub4*factor2;
	total2=sub5;
	nacional1= total1 + total2;
	nacional2 = nacional1*currencyExchange;
	
	$("#sub1").val(sub1);
	$("#totalImprevisto").val(totalImprevisto);
	$("#sub2").val(sub2);
	$("#sub3").val(sub3);
	$("#total1").val(total1);
	$("#sub4").val(sub4);
	$("#sub5").val(sub5);
	$("#total2").val(total2);
	$("#nacional1").val(nacional1);
	$("#nacional2").val(nacional2);
	
	
	
	
}

// obtener valores de la fila para actualizar
function myFunction(x) {
	
		$(".form-proposaldetails").hide("slow");
	    var table = document.getElementById("proposalDetailsTable");
	    var idDeparture = document.getElementById("idDeparture");
	    var partida = (table.rows[x.rowIndex].cells[1].innerText).toString();
		$("#idProposalDetails").val(table.rows[x.rowIndex].cells[0].innerText);
		//$("#idDeparture option").val(table.rows[x.rowIndex].cells[0].innerText).change();
		//alert(table.rows[x.rowIndex].cells[0].innerText);
		$("#price").val(table.rows[x.rowIndex].cells[2].innerText);
		$("#number").val(table.rows[x.rowIndex].cells[3].innerText);
		$("#daysTimes").val(table.rows[x.rowIndex].cells[4].innerText);
		$("#totalBudget").val(table.rows[x.rowIndex].cells[5].innerText);
		$("#detail").val(table.rows[x.rowIndex].cells[6].innerText);
		$("#parameters").val(table.rows[x.rowIndex].cells[7].innerText);
		$("#commissionable").val(table.rows[x.rowIndex].cells[8].innerText).change();
		$(".form-proposaldetails").toggle("slow");
		
		
	}