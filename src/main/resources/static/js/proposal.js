

//----------------------------------------FUNCIONES POR ID DE ELEMENTO DE HTML--------------------------------------------------------------------
$( document ).ready(function() {
	
//------------------------------------------------Manejo del diseño de proposal y proposaldetails------------------------------------------------
	
	
	
	
	$("#generalInfo").show();
	//$("#generalInfo2").show();
	$("#aPaso1").show();
	$("#aPaso2").show();
	$(".paso1").show();
	$(".paso2").hide();
	$(".paso3").hide();
	$(".detailTable").hide();
	$(".columnHide").show();
	$("#hideDetails").hide();
	$(".parametersTable").hide();
	$(".columnHide2").show();
	$("#hideParameters").hide();
	
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
	 
  
	$("#addProposalDetail").click(function(){
		$(".form-proposaldetails").toggle("slow");
	});
		
	$(".ui-filterable").click(function(){
		$(".form-proposaldetails").hide("slow");
	});
	
	
	$("#showDetails").click(function(){
		$(".detailTable").toggle();
		$(".columnHide").hide();
		$("#showDetails").hide("slow");
		$("#hideDetails").toggle("slow");
		
	});

	$("#hideDetails").click(function(){
		$(".detailTable").hide();
		$(".columnHide").toggle();
		$("#hideDetails").hide("slow");
		$("#showDetails").toggle("slow");
	});
	
	$("#showParameters").click(function(){
		$(".parametersTable").toggle();
		$(".columnHide2").hide();
		$("#showParameters").hide("slow");
		$("#hideParameters").toggle("slow");
		
	});

	$("#hideParameters").click(function(){
		$(".parametersTable").hide();
		$(".columnHide2").toggle();
		$("#hideParameters").hide("slow");
		$("#showParameters").toggle("slow");
	});
	
//------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------Parametros----------Limpiar valores y asignar por defecto---------------------------------------------------------------------------------------------------------

	//Elimina el contenido del input al tocarlo
	$("#imprevisto").focusin(function(){$("#imprevisto").val("");});
	$("#factor1").focusin(function(){$("#factor1").val("");});
	$("#factor2").focusin(function(){$("#factor2").val("");});
	$("#aporteFijo").focusin(function(){$("#aporteFijo").val("");});
	
	// Si se sale del input y el mismo está en blanco, se coloca el valor por defecto
	$("#factor1").focusout(function(){ if( $("#factor1").val()==null ||  $("#factor1").val()=="") $("#factor1").val(formatNumberProposal($("#defaultFactor1").val()));});
	$("#imprevisto").focusout(function(){ if( $("#imprevisto").val()==null ||  $("#imprevisto").val()==""  ) $("#imprevisto").val(formatNumberProposal($("#defaultImprevisto").val()));});
	$("#factor2").focusout(function(){  if( $("#factor2").val()==null ||  $("#factor2").val()=="") $("#factor2").val(formatNumberProposal($("#defaultFactor2").val()));});
	$("#aporteFijo").focusout(function(){  if( $("#aporteFijo").val()==null ||  $("#aporteFijo").val()=="") $("#aporteFijo").val(formatNumberProposal($("#defaultAporteFijo").val()));});
	
//------------------------------------------------------------------------------------------------------------------------------------------------	
	
//------------------------------------------------Calcular el total de presupuesto de cada detalle------------Partida - precio - numero - dias/veces ------------------------------------
	
	$("#price").focusin(function(){$("#price").val("");});
	$("#price").focusout(function()
	{
		if($("#price").val()=="" || $("#price").val()==null || isNaN(unFormatNumberProposal($("#price").val())/1))
		{
			changeDeparture();
			$("#price").change();
		}
	});
		
	$("#price").change(function(){$("#price").val(formatNumberProposal(unFormatNumberProposal($("#price").val())))});
	
	$(".cal").change(function(){
		
		 var total = 0;
		 var price = $("#price").val();
		 var point = price.split(".").length;
		 
		 if(point>=3)price=unFormatNumberProposal(replacePointProposal(price));
		 else price=unFormatNumberProposal((price));
		 var number = $("#number").val();
		 var daysTimes = $("#daysTimes").val();
		 if(number!=null && number!="" && daysTimes!=null && daysTimes!="")
		 {
			 total= price * number* daysTimes;
			 $("#totalBudget").val(formatNumberProposal(total));
		 }
	 });
	
//------------------------------------------------------------------------------------------------------------------------------------------------
	 

	 
	 
//------------------------------------------------Actualizar y recalcular factor 1, 2, imprevisto y aporte fijo------------------------------------------------
	 $(".param").change(function(){
		 
		
		if(isNaN(unFormatNumberProposal($("#aporteFijo").val())/1) || isNaN(unFormatNumberProposal($("#factor1").val())/1) || isNaN(unFormatNumberProposal($("#factor2").val())/1) || isNaN(unFormatNumberProposal($("#imprevisto").val())/1))
		{
			if(isNaN($("#aporteFijo").val()/1)) $("#aporteFijo").val(formatNumberProposal($("#defaultAporteFijo").val()));
			if(isNaN($("#factor1").val()/1)) $("#factor1").val(formatNumberProposal($("#defaultFactor1").val()));
			if(isNaN($("#factor2").val()/1)) $("#factor2").val(formatNumberProposal($("#defaultFactor2").val()));
			if(isNaN($("#imprevisto").val()/1)) $("#imprevisto").val(formatNumberProposal($("#defaultImprevisto").val()));
		}
		else if($("#aporteFijo").val()=="" || $("#aporteFijo").val()== null || unFormatNumberProposal($("#aporteFijo").val())< 0 ) $("#aporteFijo").val(formatNumberProposal($("#defaultAporteFijo").val()));
		else if( $("#factor1").val()=="" ||  $("#factor1").val()== null || unFormatNumberProposal($("#factor1").val())<1 )  $("#factor1").val(formatNumberProposal($("#defaultFactor1").val()));
		else if($("#factor2").val()=="" || $("#factor2").val()== null || unFormatNumberProposal($("#factor2").val())<1 ) $("#factor2").val(formatNumberProposal($("#defaultFactor2").val()));
		else if($("#imprevisto").val()=="" || $("#imprevisto").val()== null || unFormatNumberProposal($("#imprevisto").val())<0 || unFormatNumberProposal($("#imprevisto").val())>100) $("#imprevisto").val(formatNumberProposal($("#defaultImprevisto").val()));
		else
		{
			var aporteFijo =$("#aporteFijo").val();
			var factor1 = unFormatNumberProposal($("#factor1").val());
			var factor2 = unFormatNumberProposal($("#factor2").val());
			var imprevisto = unFormatNumberProposal($("#imprevisto").val());
			
			var pointV = aporteFijo.split(".").length;
			if(pointV>=3)aporteFijo=unFormatNumberProposal(replacePointProposal(aporteFijo));
			else aporteFijo=unFormatNumberProposal(aporteFijo);
	
	
		
			var url = "/proposal/customizeparameters"; // El script a dónde se realizará la petición.
			$.ajax({
	           type: "POST",
	           cache: false,
	           url: url,
	           data: { 
	        	  'aporteFijo':aporteFijo, 
	   			 'factor1':factor1,
	   			 'factor2':factor2,
	   			 'imprevisto': imprevisto
	        		 },  // Adjuntar los campos del formulario enviado.

	           success: function(data)
	           {
	        	   //Actualiza valres por defecto
	        	   	$("#defaultAporteFijo").val(aporteFijo);
	       			$("#defaultFactor1").val(factor1);
	       			$("#defaultFactor2").val(factor2);
	       			$("#defaultImprevisto").val(imprevisto);
	       			
	       			//Formateamos los números actuales
	       			
	       			formatParameters();
	       			
	       			
	       			//Recalculamos
	       			totalcharge();
	           }
	         });
		}
		
	 });
	
//------------------------------------------------------------------------------------------------------------------------------------------------
	 

//----------------------------------------Validar los datos de proposal: informacion general antes del submit----------------------------------------------------------------------
	 $("#proposalSubmit").click(function(){
		var div = document.getElementById('msgAddProposal');
		var msg="";
		var initialDate = $("#initialDate").val()
		var endDate = $("#endDate").val()
		var targetText =$("#targetText").val();
		var observations =$("#observations").val();
		var idProposal =$("#idProposal").val();
		var idCurrencyType =$("#idCurrencyType").val();
		var idClientContact =$("#idClientContact").val();
		var idCollectMethod =$("#idCollectMethod").val();
		var idCountry =$("#idCountry").val();
		var idExecutionType =$("#idExecutionType").val();
		var idIndustrySector =$("#idIndustrySector").val();
		var idOperation =$("#idOperation").val();
		var idProposalType =$("#idProposalType").val();
		var idStatus =$("#idStatus").val();
		var idStudyCategory =$("#idStudyCategory").val();
		var idStudyType =$("#idStudyType").val();
		var techniques = [];
		var tracker =$("#tracker").val();
		var projectType =$("#projectType").val();
		var listTechniques = document.getElementById("listTechniques");
		
		$(listTechniques).find('input:checkbox').each(function(){
			if($(this).prop('checked')) techniques.push($(this).val());
		});
	 
		if(techniques.length>0 && initialDate!=null && endDate!=null && (new Date(initialDate).getTime() <= new Date(endDate).getTime()) && targetText!=null && observations!=null && initialDate!="" && endDate!="" && targetText!="" && observations!="")
		{
			
			var url = "/assessment/addproposal"; 
		    $.ajax({
		           type: "POST",
		           cache: false,
		           url: url,
		           data: { 
		        	   
		        	   'idProposal' : idProposal,
						'idCurrencyType' : idCurrencyType,
						'endDate' : endDate,
						'initialDate' : initialDate,
						'observations' : observations,
						'targetText' : targetText,
						'idClientContact' : idClientContact,
						'idCollectMethod' : idCollectMethod,
						'idCountry' : idCountry,
						'idExecutionType' : idExecutionType,
						'idIndustrySector' : idIndustrySector,
						'idOperation' : idOperation,
						'idProposalType' : idProposalType,
						'idStatus' : idStatus,
						'idStudyCategory' : idStudyCategory,
						'idStudyType' : idStudyType,
						'techniques[]' : techniques,
						'tracker' : tracker,
						'projectType' : projectType
		        	},

		           success: function(data)
		           {
		        	   if(data != null){
		        		   
		        		   if($("#idProposal").val()==0) msg = "<p style='color: hsl(153,80%,40%)'>Se guardó la información correctamente <p>";
		        		   else  if($("#idProposal").val()!=0) msg = "<p style='color: hsl(153,80%,40%)'>Se actualizó la información correctamente <p>";
		        		   
		        		   div.innerHTML = msg;
		        		   location="/assessment/proposal";
		        	   }else{
		        		   alert("false");
		        		   }
		        	   }
		         });
		}
		else 
		{
			if(initialDate>endDate) msg+="<p style='color:#800000'>La fecha inicial debe ser mayor que la fecha final <p>";
			if(initialDate=="") msg+="<p style='color:#800000'>Debe ingresar la fecha inicial en el Paso 1 <p>";
			if(endDate=="") msg+="<p style='color:#800000'>Debe ingresar la fecha final en el Paso 1 <p>";
			if(targetText=="") msg+="<p style='color:#800000'>Debe ingresar el objetivo o muestra en el Paso 2 <p>";
			if(observations=="") msg+="<p style='color:#800000'>Debe Ingresar las observaciones en el Paso 2 <p>";
			if(techniques.length<=0) msg+="<p style='color:#800000'>Debe seleccionar las técnicas y modelos en el Paso 3 <p>";
		}
		div.innerHTML = msg;
	 });
	 
//------------------------------------------------------------------------------------------------------------------------------------------------
	
//------------------------------Token para el submit de formularios-------------------------------------------------------------------------------	
		$(function () {
		    var token = $("input[name='_csrf']").val();
		    var header = "X-CSRF-TOKEN";
		    $(document).ajaxSend(function(e, xhr, options) {
		        xhr.setRequestHeader(header, token);
		    });
		});
//------------------------------------------------------------------------------------------------------------------------------------------------	
		

		
		
//----------------------Enviar formulario de proposaldetails con ajax--------------------------------------------------------------------------------------
		
		$("#sendProposalDetail").click(function(){
			
			
			var idProposalDetails =$("#idProposalDetails").val();
			var detail = $("#detail").val();
			var parameters = $("#parameters").val();
			var idDeparture = $("#idDeparture").val();
			var price = $("#price").val();
			var commissionable = $("#commissionable").val();
			var number = $("#number").val();
			var daysTimes = $("#daysTimes").val();
			var totalBudget = $("#totalBudget").val();
			var idPriceCurrencyType = $("#idPriceCurrencyType").val();
			var div = document.getElementById('infoDetail');
			var validateForm = document.getElementById('validateForm');
			var tbody = document.getElementById('tableBodyProposalDetail');
			var msg="";
			
			if(idProposalDetails=="" || idProposalDetails==null) idProposalDetails=0;

			if(price!=null && price!="" && number!=null && number!="" && daysTimes!=null && daysTimes!="" && totalBudget!=null && totalBudget!="")
			{
			
				
				 var pointT = totalBudget.split(".").length;
				 if(pointT>=3)totalBudget=unFormatNumberProposal(replacePointProposal(totalBudget));
				 else totalBudget=unFormatNumberProposal((totalBudget));
				
				 var pointP = price.split(".").length;
				 if(pointP>=3)price=unFormatNumberProposal(replacePointProposal(price));
				 else price=unFormatNumberProposal((price));
				var url = "/proposal/addproposaldetails"; // El script a dónde se realizará la petición.
			    $.ajax({
			           type: "POST",
			           cache: false,
			           url: url,
			           data: { 
			        	  'idProposalDetails':idProposalDetails, 
			   			 'detail':detail,
			   			 'parameters':parameters,
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
			        		   
			        		   
			        		   //Diseño de tabla
			        		    $(".parametersTable").hide();
			        			$(".columnHide2").show();
			        			$("#hideParameters").hide();
			        			$("#showParameters").show();
			        			$(".detailTable").hide();
			        			$(".columnHide").show();
			        			$("#hideDetails").hide();
			        			$("#showDetails").show();
			        		   totalcharge();
			        		   ClearForm();
			        		   changeDeparture();
			        		   
			        		   $(".form-proposaldetails").hide("slow");
			        		   div.innerHTML = msg;
			        		   validateForm.innerHTML = "";
			        	   }else{
			        		   alert("false");
			        		   }
			        	   }
			         });
				}
				else
					{
					 	msg = "<p style='color:#800000; text-align:center;'>Debe completar la totalidad del formulario para continuar<p>";
					 	validateForm.innerHTML = msg;
					}
		});
//------------------------------------------------------------------------------------------------------------------------------------------------	 
		
		
//--------------------------------Cierra el formulario y limpia los valores-----------------------------------------------------------------------
		$("#cancelProposalDetail").click(function(){
			
			ClearForm();
			changeDeparture();
		});
		
		
		
//------------------------------------------------------------------------------------------------------------------------------------------------		
		

});

//------------------------------------------------------------------------------------------------------------------------------------------------





//-----------------------------------FUNCIONES UTILIZADAS EN LOS EVENTOS DE LOS ELEMENTOS DEL HTML------------------------------------------------

//-----------------------------------Obtener valores de la tabla de partidas para saber el precio sugerido y el tipo de moneda------------------
function changeDeparture()
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
	
	$("#price").val(formatNumberProposal(unFormatNumberProposal(price)));
	$("#idPriceCurrencyType").val(parseInt(idPriceCurrencyType)).change();
	
};


//------------------------------------------------------------------------------------------------------------------------------------------------		 

//-----------------------------------Limpiar formulario-------------------------------------------------------------------------------------------
function ClearForm()
{
	var div = document.getElementById('infoDetail');
	var validateForm = document.getElementById('validateForm');
	$("#idProposalDetails").val(0);
	$("#price").val("");
	$("#commissionable").val(1).change();
	$("#number").val("");
	$("#daysTimes").val("");
	$("#totalBudget").val("");
	$("#detail").val("");
	$("#parameters").val("");
	$(".form-proposaldetails").hide("slow");
	div.innerHTML = "";
	validateForm.innerHTML = "";
};
//-------------------------------------------------------------------------------------------------------------------------------------------------


//-----------------------------------Cargar la pantalla de detalles al ingresar a proposaldetails--------------------------------------------------
function proposalDetailsLoad(){  
    $("#detalleCoti").show();
    $("#generalInfo").hide();
    $("#generalInfo2").hide();
    $("#facturaEscena").hide();
    $("#aPaso1").hide();
	$("#aPaso2").hide();
};

//--------------------------------------------------------------------------------------------------------------------------------------------------


//---------------------------------------Calculo de totales-----------------------------------------------------------------------------------------
function totalcharge()
{
	var sub1 =0;
	var imprevisto = unFormatNumberProposal($("#imprevisto").val());
	var totalImprevisto=0;
	var sub2=0;
	var factor1 = unFormatNumberProposal($("#factor1").val());
	var sub3=0;
	var aporteFijo =$("#aporteFijo").val();
	var total1=0;
	var sub4=0;
	var factor2 = unFormatNumberProposal($("#factor2").val());
	var sub5=0;
	var total2=0;
	var nacional2=0;
	var currencyExchange =unFormatNumberProposal($("#currencyExchange").val());
	var montoAporteFijo =0;
	var table = document.getElementById("proposalDetailsTable");
	var idCurrencyTypeInternational =$("#idCurrencyTypeInternational").val();
	var totalSumBudget =0;
	
	// -2 debido a que la ultima fila es la del total de presupuesto
	for(contador=1;contador<=table.rows.length-2;contador++)
	{
		
		var valor = table.rows[contador].cells[5].innerText;
		var cms = table.rows[contador].cells[10].innerText;
		var crrtype = table.rows[contador].cells[11].innerText;
		valor = valor.split(" ");
		valor = valor[valor.length-1];
		var point = valor.split(".").length;
		if(point>=3)valor=unFormatNumberProposal(replacePointProposal(valor));
		else valor=unFormatNumberProposal((valor));
		var pointV = aporteFijo.split(".").length;
		if(pointV>=3)aporteFijo=unFormatNumberProposal(replacePointProposal(aporteFijo));
		else aporteFijo=unFormatNumberProposal(aporteFijo);
		
		if(cms==1)
		{
			if(idCurrencyTypeInternational==crrtype) valor=valor*currencyExchange;
			sub1=sub1+parseFloat(valor);
		}
		else 
		{
			if(idCurrencyTypeInternational==crrtype) valor=valor*currencyExchange;	
			sub4=sub4+parseFloat(valor);
		}
	}
	
	
	totalImprevisto= sub1*(imprevisto/100);
	sub2 = (sub1*1)+(totalImprevisto*1);
	sub3 =  sub2*factor1;
	total1 = sub3;
	sub5= sub4*factor2;
	total2=sub5;
	if(isNaN(aporteFijo*1))nacional2 = 0;
	else nacional2 = (total1*1)+(total2*1)+(aporteFijo*1);
	totalSumBudget =  (sub1*1)+(sub4*1);
	
	
	$("#sub1").val(formatNumberProposal(parseFloat(sub1/currencyExchange).toFixed(2)));
	$("#totalImprevisto").val(formatNumberProposal(parseFloat(totalImprevisto/currencyExchange).toFixed(2)));
	$("#sub2").val(formatNumberProposal(parseFloat(sub2/currencyExchange).toFixed(2)));
	$("#sub3").val(formatNumberProposal(parseFloat(sub3/currencyExchange).toFixed(2)));
	$("#total1").val(formatNumberProposal(parseFloat(total1/currencyExchange).toFixed(2)));
	$("#sub4").val(formatNumberProposal(parseFloat(sub4/currencyExchange).toFixed(2)));
	$("#sub5").val(formatNumberProposal(parseFloat(sub5/currencyExchange).toFixed(2)));
	$("#total2").val(formatNumberProposal(parseFloat(total2/currencyExchange).toFixed(2)));
	$("#nacional1").val(formatNumberProposal(parseFloat(nacional2/currencyExchange).toFixed(2)));
	$("#nacional2").val(formatNumberProposal(parseFloat(nacional2).toFixed(2)));
	$("#totalSumBudget").val(formatNumberProposal(parseFloat(totalSumBudget).toFixed(2)));

	
	
};
//--------------------------------------------------------------------------------------------------------------------------------------------------

//----------------------------------------Obtener valores de la fila para actualizar----------------------------------------------------------------
function chargeTheDetailForUpdate(row) {
	
		
		
	$(".form-proposaldetails").hide("slow");
	var idProposalDetailsTable = $(row).parents("tr").find("#idProposalDetailsTable span").eq(0).html();
	var departureDetailTable = $(row).parents("tr").find("#departureDetailTable span").eq(0).html();
	var priceTable = $(row).parents("tr").find("#priceTable span").eq(1).html();
	var numberTable = $(row).parents("tr").find("#numberTable span").eq(0).html();
	var daysTimesTable = $(row).parents("tr").find("#daysTimesTable span").eq(0).html();
	var totalBudgetTable = $(row).parents("tr").find("#totalBudgetTable span").eq(1).html();
	var detailTable = $(row).parents("tr").find("#detailTable textarea").eq(0).html();
	var parametersTable = $(row).parents("tr").find("#parametersTable textarea").eq(0).html();
	var commissionableTable = $(row).parents("tr").find("#commissionableTable span").eq(0).html();
	var idCurrencyTypeTable = $(row).parents("tr").find("#idCurrencyTypeTable span").eq(0).html();
	var idDepartureTable = $(row).parents("tr").find("#idDepartureTable span").eq(0).html();
	
	
	$("#idDeparture").val(idDepartureTable).change();
	$("#idProposalDetails").val(idProposalDetailsTable);
	$("#price").val(priceTable);
	$("#number").val(numberTable);
	$("#daysTimes").val(daysTimesTable);
	$("#totalBudget").val(totalBudgetTable);
	$("#detail").val(detailTable);
	$("#parameters").val(parametersTable);
	$("#commissionable").val(commissionableTable).change();
	$("#idPriceCurrencyType").val(idCurrencyTypeTable).change();
	$(".form-proposaldetails").toggle("slow");
};	

//--------------------------------------------------------------------------------------------------------------------------------------------------

function methodLoad()
{
	if($("#idProposal").val()==0)
	{
		document.getElementById("firstLink").click();
	}
	else
	{
		document.getElementById("secondLink").click();
		formatParametersOnLoad();
		totalcharge(); 
		changeDeparture();
	}
	
};



//--------------------------------------------------------------------------------------------------------------------------------------------------




//------------------------------------------------editCurrencyType in the rowProposalDetails--------------------------------------------------------------------------------------------------
function changeCurrencyTypeInRowTable(row)
{
	var tbody = document.getElementById('tableBodyProposalDetail');
	var idCurrencyTypeFavorite =$("#idCurrencyTypeFavorite").val();
	var idProposalDetailsTable = $(row).parents("tr").find("#idProposalDetailsTable span").eq(0).html();
	var idCurrencyTypeInternational =$("#idCurrencyTypeInternational").val();
	var idCurrencyTypeTable = $(row).parents("tr").find("#idCurrencyTypeTable span").eq(0).html();
		
		if(idCurrencyTypeTable==idCurrencyTypeFavorite) idCurrencyTypeTable=idCurrencyTypeInternational;
		else idCurrencyTypeTable=idCurrencyTypeFavorite;
		
			var url = "/proposal/editrowcurrencytype"; 
		    $.ajax({
		           type: "POST",
		           cache: false,
		           url: url,
		           data: { 
		        	 'idProposalDetails':idProposalDetailsTable,
		        	 'idCurrencyType':idCurrencyTypeTable 
		        		 },

		           success: function(data)
		           {
		        	   if(data != null){
		        		tbody.innerHTML = data;
		        		//Diseño de tabla
	        		    $(".parametersTable").hide();
	        			$(".columnHide2").show();
	        			$("#hideParameters").hide();
	        			$("#showParameters").show();
	        			$(".detailTable").hide();
	        			$(".columnHide").show();
	        			$("#hideDetails").hide();
	        			$("#showDetails").show();
		        		totalcharge();
		        		
		        	   }else{
		        		   alert("false");
		        		   }
		        	   }
		         });

};

//--------------------------------------------------------------------------------------------------------------------------------------------------


//-------------------------------------------------------CalRow change and charge to editRow-------------------------------------------------------------------------------------------
function calRow(row)
{
	var tbody = document.getElementById('tableBodyProposalDetail');
	var div = document.getElementById('infoDetail');
	var msg="";
	var idProposalDetailsTable = $(row).parents("tr").find("#idProposalDetailsTable span").eq(0).html();
	var priceTable = $(row).parents("tr").find("#priceTable span").eq(1).html();
	var numberTable = $(row).parents("tr").find("#numberTable span").eq(0).html();
	var daysTimesTable = $(row).parents("tr").find("#daysTimesTable span").eq(0).html();
	var totalBudgetTable = 0;
	try
	{
		
		
		
		var pointP = priceTable.split(".").length;
		if(pointP>=3)priceTable=unFormatNumberProposal(replacePointProposal(priceTable));
		else priceTable=unFormatNumberProposal((priceTable));
		numberTable=parseInt(unFormatNumberProposal(numberTable));
		daysTimesTable=parseInt(unFormatNumberProposal(daysTimesTable));
		if(priceTable=="" || priceTable==null || priceTable<1 || isNaN(priceTable/1)) msg+="<p style='color:#800000'>Debe ingresar un precio válido<p>";
		else if(numberTable=="" || numberTable==null || numberTable<1 || isNaN(numberTable/1)) msg+="<p style='color:#800000'>Debe ingresar un numero o cantidad y debe ser un valor entero<p>";
		else if(daysTimesTable=="" || daysTimesTable==null || daysTimesTable<1 || isNaN(daysTimesTable/1)) msg+="<p style='color:#800000'>Debe ingresar la cantidad de días o veces y debe ser un valor entero<p>";
		else
		{
			
			var url = "/proposal/editrowdetail"; 
		    $.ajax({
		           type: "POST",
		           cache: false,
		           url: url,
		           data: { 
		        	 'idProposalDetails':idProposalDetailsTable,
		        	 'price':priceTable , 
		   			 'number': numberTable,
		   			 'daysTimes': daysTimesTable
		        		 },

		           success: function(data)
		           {
		        	   if(data != null){
		        		tbody.innerHTML = data;
		        		//Diseño de tabla
	        		    $(".parametersTable").hide();
	        			$(".columnHide2").show();
	        			$("#hideParameters").hide();
	        			$("#showParameters").show();
	        			$(".detailTable").hide();
	        			$(".columnHide").show();
	        			$("#hideDetails").hide();
	        			$("#showDetails").show();
		        		totalcharge();
		        		
		        	   }else{
		        		   alert("false");
		        		   }
		        	   }
		         });
		}
		div.innerHTML = msg;
		
	}
	catch(err)
	{
		msg+="<p style='color:#800000'>El formato de los datos es incorrecto, ingrese solo números.<p>";
		div.innerHTML = msg;
	}

};

//--------------------------------------------------------------------------------------------------------------------------------------------------

//-----------------------------------------------deleteProposalDetail---------------------------------------------------------------------------------------------------

function deleteProposalDetail(row){
	
	var div = document.getElementById('infoDetail');
	var tbodyShared = document.getElementById('tableBodyProposalDetail');
	var msg="";
	var idProposalDetailsTable = $(row).parents("tr").find("#idProposalDetailsTable span").eq(0).html();
	
	var url = "/proposal/deleteproposaldetail"; 
	    $.ajax({
	           type: "POST",
	           cache: false,
	           url: url,
	           data: {'idProposalDetails': idProposalDetailsTable},

	           success: function(data)
	           {
	        	   if(data != null){
	        		   tbodyShared.innerHTML = data;
	        		   //Diseño de tabla
	        		    $(".parametersTable").hide();
	        			$(".columnHide2").show();
	        			$("#hideParameters").hide();
	        			$("#showParameters").show();
	        			$(".detailTable").hide();
	        			$(".columnHide").show();
	        			$("#hideDetails").hide();
	        			$("#showDetails").show();
	        			 totalcharge();
		        		 ClearForm();
		        		 changeDeparture();
	        		   msg = "<p style='color: hsl(153,80%,40%)'>Se eliminó el detalle correctamente. <p>";
	        		  div.innerHTML = msg;
	        	   }else{
	        		   msgShared = "<p style='color:#800000'>Ha ocurrido un error inesperado!<p>";
	        		   div.innerHTML = msg;
	        		   }
	           }
	    	
	         });
};

//--------------------------------------------------------------------------------------------------------------------------------------------------


//-------------------------------------------------LIST PROPOSAL------------------------------------------------------------------------------------

//---------------------------------------------------loadListProposal-------------------------------------------------------------------------------

function loadListProposal()
{
	noSelectInLoad();
}


//--------------------------------------------------------------------------------------------------------------------------------------------------


//-------------------------------------------------------noSelectInLoad---------------------------------------------------------------------------

function noSelectInLoad()
{
	 $("#idClient").val("").change();
	 $("#idStatus").val("").change();
	
}


//--------------------------------------------------------------------------------------------------------------------------------------------------

//-------------------------------------------------addProposalLink--------------------------------------------------------------------------------------

function addProposal()
{ 
	location="/listproposal/newproposal";
}

//--------------------------------------------------------------------------------------------------------------------------------------------------


//-------------------------------------------------editProposalLink--------------------------------------------------------------------------------------

function editProposal(row)
{ 
var idProposal = $(row).parents("tr").find("#idProposal span").eq(0).html();
	
	var url = "/assessment/proposal"; 
	    $.ajax({
	           type: "POST",
	           cache: false,
	           url: url,
	           data: {
	        	   'idProposal' : idProposal
	           },

	           success: function(data)
	           {
	        	   if(data != null){
	        		
	        		 location="/assessment/proposal";
	        	   }
	           }
	    	
	         });
}

//------------------------------------------------------Cambia el formato del tipo de moneda--------------------------------------------------------------------

function formatNumberProposal(num)
{
	return ((parseFloat(num)).toLocaleString(undefined, {minimumFractionDigits: 2}));
}

//----------------------------------------------------------------------------------------------------------------------------------------------------------------

//-----------------------------------------Restaura el formato de texto a numero-----------------------------------------------------------------------------------------------------------------------
function unFormatNumberProposal(text)
{
	return parseFloat(String(text).replace(".","").replace(",",".")).toFixed(2);
}

//----------------------------------------------------------------------------------------------------------------------------------------------------------------



//-----------------------Da formato a los input al cargase la página-----------------------------------------------------------------------------------------------------------------------------------------
function formatParametersOnLoad()
{
	$("#currencyExchange").val(formatNumberProposal($("#currencyExchange").val()));
	$("#aporteFijo").val( formatNumberProposal($("#aporteFijo").val()));
	$("#factor1").val(formatNumberProposal($("#factor1").val()));
	$("#factor2").val(formatNumberProposal($("#factor2").val()));
	$("#imprevisto").val(formatNumberProposal($("#imprevisto").val()));
}

//----------------------------------------------------------------------------------------------------------------------------------------------------------------


//-----------------------------Da formato a los inputs después de cada cambio-----------------------------------------------------------------------------------------------------------------------------------
function formatParameters()
{
		var aporteFijo=$("#aporteFijo").val();
		var pointV = aporteFijo.split(".").length;
		if(pointV>=3)$("#aporteFijo").val(formatNumberProposal(unFormatNumberProposal(replacePointProposal(aporteFijo))));
		else $("#aporteFijo").val(formatNumberProposal(unFormatNumberProposal(aporteFijo)));
		$("#factor1").val(formatNumberProposal(unFormatNumberProposal($("#factor1").val())));
		$("#factor2").val(formatNumberProposal(unFormatNumberProposal($("#factor2").val())));
		$("#imprevisto").val(formatNumberProposal(unFormatNumberProposal($("#imprevisto").val())));
}

//----------------------------------------------------------------------------------------------------------------------------------------------------------------


//-----------------------------------------Redireccionamiento a lista de proyectos-----------------------------------------------------------------------------------------------------------------------
function backToListProposal()
{
	location="/assessment/listproposal";
}


//----------------------------------------------------------------------------------------------------------------------------------------------------------------
function replacePointProposal(text)
{
	return String(text).replace(".","").replace(",",".");
}

/* value means the number od the textbox 

function convertCurrrency(value){
	var total =  value.replace(/\D/g, "")
    .replace(/([0-9])([0-9]{3})$/, '$1.$2') /* si reemplzas el numero $1$2 por $1.$2 se mantendra con dos digitos al final no se como sera el mejor ahi 
    .replace(/\B(?=(\d{3})+(?!\d)\.?)/g, ",");
    
	return total; /* devuelve el valor ya formateado no se cualquiera que ocupes aqui
}

$( document ).ready(function(){

$("#price").on({
	  "focus": function(event) {
	    $(event.target).select();
	  },
	  "keyup": function(event) {
	    $(event.target).val(function(index, value) {
	      return value.replace(/\D/g, "")
	        .replace(/([0-9])([0-9]{3})$/, '$1.$2')
	        .replace(/\B(?=(\d{3})+(?!\d)\.?)/g, ",");
	    });
	  }
});



});

*/


