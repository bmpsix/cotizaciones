/**
 * Justin Zuniga Torres
 */



//FUNCIONES POR ID DE ELEMENTO DE HTML
$( document ).ready(function() {

	//Manejo de los div de proposal y proposaldetails
	$("#generalInfo").show();
	//$("#generalInfo2").show();
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
			var idPriceCurrencyType = $("#idPriceCurrencyType").val();
			var div = document.getElementById('infoDetail');
			var tbody = document.getElementById('tableBodyProposalDetail');
			var msg="";
			
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
			   			 'totalBudget': totalBudget,
			   			 'idPriceCurrencyType':idPriceCurrencyType
			          
			        		 },  // Adjuntar los campos del formulario enviado.

			           success: function(data)
			           {
			        	   if(data != null){
			        		   tbody.innerHTML = data;
			        		   if($("#idProposalDetails").val()==0) msg = "<p style='color: hsl(153,80%,40%)'>Se guardó la información correctamente <p>";
			        		   else  if($("#idProposalDetails").val()!=0) msg = "<p style='color: hsl(153,80%,40%)'>Se actualizó la información correctamente <p>";
			        		   ClearForm();
			        		   
			        		   totalcharge();
			        		   //location.reload();
			        		   $(".form-proposaldetails").hide("slow");
			        		   div.innerHTML = msg;
			        	   }else{
			        		   alert("false");
			        		   }
			        	   }
			         });
		});
	 
		$("#cancelProposalDetail").click(function(){
			
			ClearForm();
		
		});
		
		
		//obtener valores de la tabla de partidas para obtener el precio sugerido y el tipo de moneda
		$("#idDeparture").change(function()
		{
			
			var table = document.getElementById("departureTable");
			var idDeparture = $("#idDeparture").val();
			var price=0;
			var idPriceCurrencyType=0;
			    
			for(contador=0;contador<=table.rows.length-1;contador++)
			{
				if(table.rows[contador].cells[0].innerText==idDeparture)
				{
			    	idPriceCurrencyType=table.rows[contador].cells[1].innerText;
			    	price=table.rows[contador].cells[2].innerText;
			    	break;
			    }
			}
			$("#price").val(price);
			$("#idPriceCurrencyType").val(parseInt(idPriceCurrencyType)).change();
			
		});
		
		 
	
		
});







//FUNCIONES UTILIZADAS EN LOS EVENTOS DE LOS ELEMENTOS DEL HTML



function ClearForm()
{
	$("#idProposalDetails").val(0);
	$("#price").val("");
	$("#commissionable").val(1);
	$("#number").val("");
	$("#daysTimes").val("");
	$("#totalBudget").val("");
	$("#detail").val("");
	$("#parameters").val("");
	$(".form-proposaldetails").hide("slow");
};

//Cargar la pantalla de detalles al ingresar a proposaldetails
function proposalDetailsLoad(){  
    $("#detalleCoti").show();
    $("#generalInfo").hide();
    $("#generalInfo2").hide();
    $("#facturaEscena").hide();
    $("#aPaso1").hide();
	$("#aPaso2").hide();
};



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
	var idCurrencyTypeFavorite =$("#idCurrencyTypeFavorite").val();
	var totalSumBudget =0;
	
	// -2 debido a que la ultima fila es la del total de presupuesto
	for(contador=1;contador<=table.rows.length-2;contador++)
	{
		var valor = table.rows[contador].cells[5].innerText;
		var cms = table.rows[contador].cells[8].innerText;
		var crrtype = table.rows[contador].cells[9].innerText;
		valor = valor.split(" ");
		valor = valor[valor.length-1];
		if(cms==1)
		{
			if(idCurrencyTypeFavorite==crrtype) valor=valor/currencyExchange;
			sub1=sub1+parseInt(valor);
		}
		else 
		{
			if(idCurrencyTypeFavorite==crrtype) valor=valor/currencyExchange;
			
			sub4=sub4+parseInt(valor);
		}
	}
	totalImprevisto=sub1*(imprevisto/100);
	sub2 = sub1+totalImprevisto;
	sub3 = sub2*factor1;
	montoImprevisto =  sub3*aporteFijo;
	total1 = sub3 + montoImprevisto;
	sub5=sub4*factor2;
	total2=sub5;
	nacional1= total1 + total2;
	nacional2 = nacional1*currencyExchange;
	totalSumBudget = (sub1+sub4)*currencyExchange;
	$("#sub1").val( parseFloat(sub1).toFixed(2));
	$("#totalImprevisto").val(parseFloat(totalImprevisto).toFixed(2));
	$("#sub2").val(parseFloat(sub2).toFixed(2));
	$("#sub3").val(parseFloat(sub3).toFixed(2));
	$("#total1").val(parseFloat(total1).toFixed(2));
	$("#sub4").val(parseFloat(sub4).toFixed(2));
	$("#sub5").val(parseFloat(sub5).toFixed(2));
	$("#total2").val(parseFloat(total2).toFixed(2));
	$("#nacional1").val(parseFloat(nacional1).toFixed(2));
	$("#nacional2").val(parseFloat(nacional2).toFixed(2));
	$("#totalSumBudget").val(parseFloat(totalSumBudget).toFixed(2));

	
	
};

// obtener valores de la fila para actualizar
function chargeTheDetailForUpdate(x) {
	
		
	    var table = document.getElementById("proposalDetailsTable");
	    var idDeparture = document.getElementById("idDeparture");
	    var partida = (table.rows[x.rowIndex].cells[1].innerText).toString();
		var price =table.rows[x.rowIndex].cells[2].innerText;
		var valor = table.rows[x.rowIndex].cells[5].innerText;
		var comm = table.rows[x.rowIndex].cells[8].innerText;
		var idCurrencyTypeFavorite =$("#idCurrencyTypeFavorite").val();
		var idPriceCurrencyType = table.rows[x.rowIndex].cells[9].innerText;
		var idDeparture = table.rows[x.rowIndex].cells[10].innerText;
		
		price = price.split(" ");
		price = price[price.length-1];
		
		valor = valor.split(" ");
		valor = valor[valor.length-1];

		$("#idDeparture").val(parseInt(idDeparture)).change();
		$(".form-proposaldetails").hide("slow");
		$("#idProposalDetails").val(table.rows[x.rowIndex].cells[0].innerText);
		$("#price").val(price);
		$("#number").val(table.rows[x.rowIndex].cells[3].innerText);
		$("#daysTimes").val(table.rows[x.rowIndex].cells[4].innerText);
		$("#totalBudget").val(valor);
		$("#detail").val(table.rows[x.rowIndex].cells[6].innerText);
		$("#parameters").val(table.rows[x.rowIndex].cells[7].innerText);
		$("#commissionable").val(parseInt(comm)).change();
		$("#idPriceCurrencyType").val(parseInt(idPriceCurrencyType)).change();
		$(".form-proposaldetails").toggle("slow");
};




