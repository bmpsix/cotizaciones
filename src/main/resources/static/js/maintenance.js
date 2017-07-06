/**
 * Justin Zúñiga Torres
 * Marcos Zúñiga Vega
 */


//----------------------------changePassword--------------------------------------------------------------------------------------------------------------

function changePassword()
{
		var currentPassword =$("#currentPassword").val();
		var newPassword =$("#newPassword").val();
		var div = document.getElementById('msg');
		var msg="";
		
		var url = "/admin/changepassword"; 
		    $.ajax({
		           type: "POST",
		           cache: false,
		           url: url,
		           data: { 
		        	  'currentPassword':currentPassword, 
		   			 'newPassword': newPassword
		        		 },

		           success: function(data)
		           {
		        	   if(data != null){
		        		   $("#currentPassword").val("");
		        		   $("#newPassword").val("");
		        		   msg = "<p style='color: hsl(153,80%,40%)'>Se guardó la información correctamente <p>";
		        		   div.innerHTML = msg;
		        	   }else{
		        		   alert("false");
		        		   }
		        	   }
		         });
}

//----------------------------------------------------------------------------------------------------------------------------------------------------------




//--------------------------------------------Client--------------------------------------------------------------------------------------------------------



function sendformClient()
{
		var div = document.getElementById('msg');
		var tbodyClient = document.getElementById('tbodyClient');
		var msg="";
		var form = $("#formClient").serialize();
		
		var url = "/admin/addclient"; 
		    $.ajax({
		           type: "POST",
		           cache: false,
		           url: url,
		           data: form,

		           success: function(data)
		           {
		        	   if(data != null){
		        		  tbodyClient.innerHTML = data;
		        		  if($("#idClient").val()==0) msg = "<p style='color: hsl(153,80%,40%)'>Se guardó la información correctamente <p>";
		        		  else  if($("#idClient").val()!=0) msg = "<p style='color: hsl(153,80%,40%)'>Se actualizó la información correctamente <p>";
		        		  clearClient(); 
		        		  div.innerHTML = msg;
		        	   }else{
		        		   msg = "<p style='color:#800000'>Ha ocurrido un error inesperado!<p>";
		        		   div.innerHTML = msg;
		        		   }
		           }
		    	
		         });
}

//----------------------------------------------------------------------------------------------------------------------------------------------------------

//----------------------------editClient--------------------------------------------------------------------------------------------------------------------

function editClient(row){
	
	var idClient = $(row).parents("tr").find("#idClient span").eq(0).html();
	var SaClient = $(row).parents("tr").find("#SaClient span").eq(0).html();
	var Country = $(row).parents("tr").find("#Country span").eq(0).html();
	var detail = $(row).parents("tr").find("#detail span").eq(0).html();
	var email = $(row).parents("tr").find("#email span").eq(0).html();
	var phone = $(row).parents("tr").find("#phone span").eq(0).html();
	var fax = $(row).parents("tr").find("#fax span").eq(0).html();
	var ClientTypeDetail = $(row).parents("tr").find("#ClientTypeDetail span").eq(0).html();
	var status = $(row).parents("tr").find("#status span").eq(0).html();
	var div = document.getElementById('msg');
	var msg="";
	div.innerHTML = msg;
	$("#clientCancel").show();
	
	$("#idClient").val(idClient);
	$("#saClient").val(SaClient).change();
	$("#country").val(Country).change();
	$("#detail").val(detail);
	$("#email").val(email);
	$("#phone").val(phone);
	$("#fax").val(fax);
	$("#clientType").val(ClientTypeDetail).change();
	
	if(status == "Activo"){
		$("#status").val("1").change();
	}else{
		$("#status").val("0").change();
	}
};

//----------------------------------------------------------------------------------------------------------------------------------------------------------
//----------------------------clearClient--------------------------------------------------------------------------------------------------------------------

function clearClient(){
	$("#clientCancel").hide();
	$("#idClient").val(0);
	$("#saClient").val(1).change();
	$("#country").val(1).change();
	$("#detail").val("");
	$("#email").val("");
	$("#phone").val("");
	$("#fax").val("");
	$("#clientType").val(1).change();
	$("#status").val(0).change();
	var div = document.getElementById('msg');
	var msg="";
	div.innerHTML = msg;
};
//----------------------------------------------------------------------------------------------------------------------------------------------------------





//--------------------------------------------ClientContact--------------------------------------------------------------------------------------------------------

function sendformClientContact()
{
		var div = document.getElementById('msg');
		var tbodyClientContact = document.getElementById('tbodyClientContact');
		var msg="";
		var form = $("#formClientContact").serialize();
		
		var url = "/admin/addclientcontact"; 
		    $.ajax({
		           type: "POST",
		           cache: false,
		           url: url,
		           data: form,

		           success: function(data)
		           {
		        	   if(data != null){
		        		  tbodyClientContact.innerHTML = data;
		        		  if($("#idClientContact").val()==0) msg = "<p style='color: hsl(153,80%,40%)'>Se guardó la información correctamente <p>";
		        		  else  if($("#idClientContact").val()!=0) msg = "<p style='color: hsl(153,80%,40%)'>Se actualizó la información correctamente <p>";
		        		  clearClientContact(); 
		        		  div.innerHTML = msg;
		        	   }else{
		        		   msg = "<p style='color:#800000'>Ha ocurrido un error inesperado!<p>";
		        		   div.innerHTML = msg;
		        		   }
		           }
		    	
		         });
}

//----------------------------------------------------------------------------------------------------------------------------------------------------------

//----------------------------editClientContact--------------------------------------------------------------------------------------------------------------------

function editClientContact(row){
	
	var idClientContact = $(row).parents("tr").find("#idClientContact span").eq(0).html();
	var idClient = $(row).parents("tr").find("#idClient span").eq(0).html();
	var idCountry = $(row).parents("tr").find("#idCountry span").eq(0).html();
	var name = $(row).parents("tr").find("#name span").eq(0).html();
	var phone = $(row).parents("tr").find("#phone span").eq(0).html();
	var ext = $(row).parents("tr").find("#ext span").eq(0).html();
	var email = $(row).parents("tr").find("#email span").eq(0).html();
	var status = $(row).parents("tr").find("#status span").eq(0).html();
	var div = document.getElementById('msg');
	var msg="";
	div.innerHTML = msg;
	$("#clientContactCancel").show();
	
	$("#idClientContact").val(idClientContact);
	$("#client").val(idClient).change();
	$("#country").val(idCountry).change();
	$("#name").val(name);
	$("#phone").val(phone);
	$("#ext").val(ext);
	$("#email").val(email);
	if(status == "Activo"){
		$("#status").val(1).change();
	}else{
		$("#status").val(0).change();
	}
};

//----------------------------------------------------------------------------------------------------------------------------------------------------------
//----------------------------clearClientContact--------------------------------------------------------------------------------------------------------------------

function clearClientContact(){
	$("#clientContactCancel").hide();
	$("#idClientContact").val(0);
	$("#client").val(1).change();
	$("#country").val(1).change();
	$("#name").val("");
	$("#phone").val("");
	$("#ext").val("");
	$("#email").val("");
	$("#status").val(0).change();
	var div = document.getElementById('msg');
	var msg="";
	div.innerHTML = msg;
};


//----------------------------------------------------------------------------------------------------------------------------------------------------------




//--------------------------------------------ClientType--------------------------------------------------------------------------------------------------------

function sendFormClientType()
{
		var div = document.getElementById('msg');
		var tbody = document.getElementById('tbodyClientType');
		var msg="";
		var form = $("#formClientType").serialize();
		
		var url = "/admin/addclienttype"; 
		    $.ajax({
		           type: "POST",
		           cache: false,
		           url: url,
		           data: form,

		           success: function(data)
		           {
		        	   if(data != null){
		        	
		        		  tbody.innerHTML = data;
		        		  if($("#idClientType").val()==0) msg = "<p style='color: hsl(153,80%,40%)'>Se guardó la información correctamente <p>";
		        		  else  if($("#idClientType").val()!=0) msg = "<p style='color: hsl(153,80%,40%)'>Se actualizó la información correctamente <p>";
		        		  clearClientType(); 
		        		  div.innerHTML = msg;
		        	   }else{
		        		   msg = "<p style='color:#800000'>Ha ocurrido un error inesperado!<p>";
		        		   div.innerHTML = msg;
		        		   }
		           }
		    	
		         });
}

//----------------------------------------------------------------------------------------------------------------------------------------------------------

//----------------------------editClientType--------------------------------------------------------------------------------------------------------------------

function editClientType(row){
	
	var idClientType = $(row).parents("tr").find("#idClientType span").eq(0).html();
	var detail = $(row).parents("tr").find("#detail span").eq(0).html();
	var status = $(row).parents("tr").find("#status span").eq(0).html();
	var div = document.getElementById('msg');
	var msg="";
	div.innerHTML = msg;
	$("#clientTypeCancel").show();
	
	$("#idClientType").val(idClientType);
	$("#detail").val(detail);
	if(status == "Activo"){
		$("#status").val(1).change();
	}else{
		$("#status").val(0).change();
	}
};

//----------------------------------------------------------------------------------------------------------------------------------------------------------
//----------------------------clearClientType--------------------------------------------------------------------------------------------------------------------

function clearClientType(){
	
	$("#clientTypeCancel").hide();
	
	$("#idClientType").val("");
	$("#detail").val("");
	$("#status").val(0).change();
	var div = document.getElementById('msg');
	var msg="";
	div.innerHTML = msg;
};


//----------------------------------------------------------------------------------------------------------------------------------------------------------






//--------------------------------------------CollectMethod--------------------------------------------------------------------------------------------------------

function sendFormCollectMethod()
{
		var div = document.getElementById('msg');
		var tbody = document.getElementById('tbodyCollectMethod');
		var msg="";
		var form = $("#formCollectMethod").serialize();
		
		var url = "/admin/addcollectmethod"; 
		    $.ajax({
		           type: "POST",
		           cache: false,
		           url: url,
		           data: form,

		           success: function(data)
		           {
		        	   if(data != null){
		        	
		        		  tbody.innerHTML = data;
		        		  if($("#idCollectMethod").val()==0) msg = "<p style='color: hsl(153,80%,40%)'>Se guardó la información correctamente <p>";
		        		  else  if($("#idCollectMethod").val()!=0) msg = "<p style='color: hsl(153,80%,40%)'>Se actualizó la información correctamente <p>";
		        		  clearCollectMethod(); //clear
		        		  div.innerHTML = msg;
		        	   }else{
		        		   msg = "<p style='color:#800000'>Ha ocurrido un error inesperado!<p>";
		        		   div.innerHTML = msg;
		        		   }
		           }
		    	
		         });
}

//----------------------------------------------------------------------------------------------------------------------------------------------------------

//----------------------------editCollectMethod--------------------------------------------------------------------------------------------------------------------

function editCollectMethod(row){
	
	var idCollectMethod = $(row).parents("tr").find("#idCollectMethod span").eq(0).html();
	var detail = $(row).parents("tr").find("#detail span").eq(0).html();
	var div = document.getElementById('msg');
	var msg="";
	div.innerHTML = msg;
	$("#collectMethodCancel").show();
	
	$("#idCollectMethod").val(idCollectMethod);
	$("#detail").val(detail);
};

//----------------------------------------------------------------------------------------------------------------------------------------------------------
//----------------------------clearCollectMethod--------------------------------------------------------------------------------------------------------------------

function clearCollectMethod(){
	
	$("#collectMethodCancel").hide();
	$("#idCollectMethod").val(0);
	$("#detail").val("");
	var div = document.getElementById('msg');
	var msg="";
	div.innerHTML = msg;
};


//----------------------------------------------------------------------------------------------------------------------------------------------------------




//--------------------------------------------CountryByCurrencyType--------------------------------------------------------------------------------------------------------

function sendFormCountryByCurrencyType()
{
		
		var tbody = document.getElementById('countryByCurrencyTypeTbody');
		var form = $("#formCountryByCurrencyType").serialize();
		
		var url = "/admin/addcountrybycurrencytype"; 
		    $.ajax({
		           type: "POST",
		           cache: false,
		           url: url,
		           data: form,

		           success: function(data)
		           {
		        	   if(data != null){
		        	
		        		  tbody.innerHTML = data;
		        	   }else{ }
		           }
		    	
		         });
}

//----------------------------------------------------------------------------------------------------------------------------------------------------------

//----------------------------deleteCountryByCurrencyType--------------------------------------------------------------------------------------------------------------------

function deleteCountryByCurrencyType(row){
	
	var idCountry = $(row).parents("tr").find("#idCountry span").eq(0).html();
	var idCurrencyType = $(row).parents("tr").find("#idCurrencyType span").eq(0).html();
	var tbody = document.getElementById('countryByCurrencyTypeTbody');
	
	
	var url = "/admin/deletecountrybycurrencytype"; 
	    $.ajax({
	           type: "POST",
	           cache: false,
	           url: url,
	           data:{ 
		        	  	'idCountry':idCountry, 
			   			'idCurrencyType': idCurrencyType
			        },

	           success: function(data)
	           {
	        	   if(data != null){
	        	
	        		  tbody.innerHTML = data;
	        	   }else{ }
	           }
	    	
	         });
};
//----------------------------------------------------------------------------------------------------------------------------------------------------------





//--------------------------------------------Country--------------------------------------------------------------------------------------------------------

function sendFormCountry()
{
		var div = document.getElementById('msg');
		var tbody = document.getElementById('tbodyCountry');
		var msg="";
		var form = $("#formCountry").serialize();
		
		var url = "/admin/addcountry"; 
		    $.ajax({
		           type: "POST",
		           cache: false,
		           url: url,
		           data: form,

		           success: function(data)
		           {
		        	   if(data != null){
		        	
		        		  tbody.innerHTML = data;
		        		  if($("#idCountry").val()==0) msg = "<p style='color: hsl(153,80%,40%)'>Se guardó la información correctamente <p>";
		        		  else  if($("#idCountry").val()!=0) msg = "<p style='color: hsl(153,80%,40%)'>Se actualizó la información correctamente <p>";
		        		  clearCountry(); 
		        		  div.innerHTML = msg;
		        	   }else{
		        		   msg = "<p style='color:#800000'>Ha ocurrido un error inesperado!<p>";
		        		   div.innerHTML = msg;
		        		   }
		           }
		    	
		         });
}

//----------------------------------------------------------------------------------------------------------------------------------------------------------

//----------------------------editCountry--------------------------------------------------------------------------------------------------------------------

function editCountry(row){
	
	var idCountry = $(row).parents("tr").find("#idCountry span").eq(0).html();
	var detail = $(row).parents("tr").find("#detail span").eq(0).html();
	var cod = $(row).parents("tr").find("#cod span").eq(0).html();
	var div = document.getElementById('msg');
	var msg="";
	div.innerHTML = msg;
	$("#countryCancel").show();
	
	$("#idCountry").val(idCountry);
	$("#detail").val(detail);
	$("#cod").val(cod);
};

//----------------------------------------------------------------------------------------------------------------------------------------------------------
//----------------------------clearCountry--------------------------------------------------------------------------------------------------------------------

function clearCountry(){
	
	$("#countryCancel").hide();
	
	$("#idCountry").val(0);
	$("#detail").val("");
	$("#cod").val("");
	var div = document.getElementById('msg');
	var msg="";
	div.innerHTML = msg;
};
//----------------------------------------------------------------------------------------------------------------------------------------------------------






//--------------------------------------------CurrencyExchange--------------------------------------------------------------------------------------------------------

function sendFormCurrencyExchange()
{
		var div = document.getElementById('msg');
		var tbody = document.getElementById('tbodyCurrencyExchange');
		var msg="";
		var form = $("#formCurrencyExchange").serialize();
		
		var url = "/admin/addcurrencyexchange"; 
		    $.ajax({
		           type: "POST",
		           cache: false,
		           url: url,
		           data: form,

		           success: function(data)
		           {
		        	   if(data != null){
		        	
		        		  tbody.innerHTML = data;
		        		  if($("#idCurrencyExchange").val()==0) msg = "<p style='color: hsl(153,80%,40%)'>Se guardó la información correctamente <p>";
		        		  else  if($("#idCurrencyExchange").val()!=0) msg = "<p style='color: hsl(153,80%,40%)'>Se actualizó la información correctamente <p>";
		        		  clearCurrencyExchange(); //clear
		        		  div.innerHTML = msg;
		        	   }else{
		        		   msg = "<p style='color:#800000'>Ha ocurrido un error inesperado!<p>";
		        		   div.innerHTML = msg;
		        		   }
		           }
		    	
		         });
}

//----------------------------------------------------------------------------------------------------------------------------------------------------------

//----------------------------editCurrencyExchange--------------------------------------------------------------------------------------------------------------------

function editCurrencyExchange(row){
	
	var idCurrencyExchange = $(row).parents("tr").find("#idCurrencyExchange span").eq(0).html();
	var idCountry = $(row).parents("tr").find("#idCountry span").eq(0).html();
	var idCurrencyType = $(row).parents("tr").find("#idCurrencyType span").eq(0).html();
	var buy = $(row).parents("tr").find("#buy span").eq(0).html();
	var sell = $(row).parents("tr").find("#sell span").eq(0).html();
	var div = document.getElementById('msg');
	var msg="";
	div.innerHTML = msg;
	$("#currencyExchangeCancel").show();
	
	$("#idCurrencyExchange").val(idCurrencyExchange);
	$("#country").val(idCountry).change();
	$("#currencyType").val(idCurrencyType).change();
	$("#buy").val(buy);
	$("#sell").val(sell);
	
};

//----------------------------------------------------------------------------------------------------------------------------------------------------------
//----------------------------clearCurrencyExchange--------------------------------------------------------------------------------------------------------------------

function clearCurrencyExchange(){
	
	$("#currencyExchangeCancel").hide();
	
	$("#idCurrencyExchange").val(0);
	$("#country").val(1).change();
	$("#currencyType").val(1).change();
	$("#buy").val("");
	$("#sell").val("");
	
	var div = document.getElementById('msg');
	var msg="";
	div.innerHTML = msg;
};


//----------------------------------------------------------------------------------------------------------------------------------------------------------




//--------------------------------------------CurrencyType--------------------------------------------------------------------------------------------------------

function sendFormCurrencyType()
{
		var div = document.getElementById('msg');
		var tbody = document.getElementById('tbodyCurrencyType');
		var msg="";
		var form = $("#formCurrencyType").serialize();
		
		var url = "/admin/addcurrencytype"; 
		    $.ajax({
		           type: "POST",
		           cache: false,
		           url: url,
		           data: form,

		           success: function(data)
		           {
		        	   if(data != null){
		        	
		        		  tbody.innerHTML = data;
		        		  if($("#idCurrencyType").val()==0) msg = "<p style='color: hsl(153,80%,40%)'>Se guardó la información correctamente <p>";
		        		  else  if($("#idCurrencyType").val()!=0) msg = "<p style='color: hsl(153,80%,40%)'>Se actualizó la información correctamente <p>";
		        		  clearCurrencyType(); //clear
		        		  div.innerHTML = msg;
		        	   }else{
		        		   msg = "<p style='color:#800000'>Ha ocurrido un error inesperado!<p>";
		        		   div.innerHTML = msg;
		        		   }
		           }
		    	
		         });
}

//----------------------------------------------------------------------------------------------------------------------------------------------------------

//----------------------------editCurrencyType--------------------------------------------------------------------------------------------------------------------

function editCurrencyType(row){
	
	var idCurrencyType = $(row).parents("tr").find("#idCurrencyType span").eq(0).html();
	var detail = $(row).parents("tr").find("#detail span").eq(0).html();
	var favorite = $(row).parents("tr").find("#favorite span").eq(0).html();
	var status = $(row).parents("tr").find("#status span").eq(0).html();
	var symbol = $(row).parents("tr").find("#symbol span").eq(0).html();
	var div = document.getElementById('msg');
	var msg="";
	div.innerHTML = msg;
	$("#currencyTypeCancel").show();
	
	$("#idCurrencyType").val(idCurrencyType);
	$("#detail").val(detail);
	$("#symbol").val(symbol);
	if(favorite == "Activo")$("#favorite").val(1).change();
	else $("#favorite").val(0).change();
	if(status == "Activo")$("#status").val(1).change();
	else $("#status").val(0).change();
	
};

//----------------------------------------------------------------------------------------------------------------------------------------------------------
//----------------------------clearCurrencyType--------------------------------------------------------------------------------------------------------------------

function clearCurrencyType(){
	
	$("#currencyTypeCancel").hide();
	
	$("#idCurrencyType").val(0);
	$("#detail").val("");
	$("#symbol").val("");
	$("#status").val(0).change();
	$("#favorite").val(0).change();
	
	var div = document.getElementById('msg');
	var msg="";
	div.innerHTML = msg;
};


//----------------------------------------------------------------------------------------------------------------------------------------------------------





//--------------------------------------------Departure--------------------------------------------------------------------------------------------------------

function sendFormDeparture()
{
		var div = document.getElementById('msg');
		var tbody = document.getElementById('tbodyDeparture');
		var msg="";
		var form = $("#formDeparture").serialize();
		
		var url = "/admin/adddeparture"; 
		    $.ajax({
		           type: "POST",
		           cache: false,
		           url: url,
		           data: form,

		           success: function(data)
		           {
		        	   if(data != null){
		        		  tbody.innerHTML = data;
		        		  if($("#idDeparture").val()==0) msg = "<p style='color: hsl(153,80%,40%)'>Se guardó la información correctamente <p>";
		        		  else  if($("#idDeparture").val()!=0) msg = "<p style='color: hsl(153,80%,40%)'>Se actualizó la información correctamente <p>";
		        		  clearDeparture(); //clear
		        		  div.innerHTML = msg;
		        	   }else{
		        		   msg = "<p style='color:#800000'>Ha ocurrido un error inesperado!<p>";
		        		   div.innerHTML = msg;
		        		   }
		           }
		    	
		         });
}

//----------------------------------------------------------------------------------------------------------------------------------------------------------

//----------------------------editDeparture--------------------------------------------------------------------------------------------------------------------

function editDeparture(row){
	
	var idDeparture = $(row).parents("tr").find("#idDeparture span").eq(0).html();
	var detail = $(row).parents("tr").find("#detail span").eq(0).html();
	var price = $(row).parents("tr").find("#price span").eq(0).html();
	var status = $(row).parents("tr").find("#status span").eq(0).html();
	var div = document.getElementById('msg');
	var msg="";
	div.innerHTML = msg;
	$("#departureCancel").show();
	
	$("#idDeparture").val(idDeparture);
	$("#detail").val(detail);
	$("#price").val(price);
	if(status == "Activo")$("#status").val(1).change();
	else $("#status").val(0).change();
	
};

//----------------------------------------------------------------------------------------------------------------------------------------------------------
//----------------------------clearDeparture--------------------------------------------------------------------------------------------------------------------

function clearDeparture(){
	
	$("#departureCancel").hide();
	
	$("#idDeparture").val(0);
	$("#detail").val("");
	$("#price").val("");
	$("#status").val(0).change();
	
	var div = document.getElementById('msg');
	var msg="";
	div.innerHTML = msg;
};


//----------------------------------------------------------------------------------------------------------------------------------------------------------





//--------------------------------------------ExecutionType--------------------------------------------------------------------------------------------------------

function sendFormExecutionType()
{
		var div = document.getElementById('msg');
		var tbody = document.getElementById('tbodyExecutionType');
		var msg="";
		var form = $("#formExecutionType").serialize();
		
		var url = "/admin/addexecutiontype"; 
		    $.ajax({
		           type: "POST",
		           cache: false,
		           url: url,
		           data: form,

		           success: function(data)
		           {
		        	   if(data != null){
		        	
		        		  tbody.innerHTML = data;
		        		  if($("#idExecutionType").val()==0) msg = "<p style='color: hsl(153,80%,40%)'>Se guardó la información correctamente <p>";
		        		  else  if($("#idExecutionType").val()!=0) msg = "<p style='color: hsl(153,80%,40%)'>Se actualizó la información correctamente <p>";
		        		  clearExecutionType(); //Clear
		        		  div.innerHTML = msg;
		        	   }else{
		        		   msg = "<p style='color:#800000'>Ha ocurrido un error inesperado!<p>";
		        		   div.innerHTML = msg;
		        		   }
		           }
		    	
		         });
}

//----------------------------------------------------------------------------------------------------------------------------------------------------------

//----------------------------editExecutionType--------------------------------------------------------------------------------------------------------------------

function editExecutionType(row){
	
	var idExecutionType = $(row).parents("tr").find("#idExecutionType span").eq(0).html();
	var detail = $(row).parents("tr").find("#detail span").eq(0).html();
	var div = document.getElementById('msg');
	var msg="";
	div.innerHTML = msg;
	$("#executionTypeCancel").show();
	
	$("#idExecutionType").val(idExecutionType);
	$("#detail").val(detail);
};

//----------------------------------------------------------------------------------------------------------------------------------------------------------
//----------------------------clearExecutionType--------------------------------------------------------------------------------------------------------------------

function clearExecutionType(){
	
	$("#executionTypeCancel").hide();
	
	$("#idExecutionType").val(0);
	$("#detail").val("");
	var div = document.getElementById('msg');
	var msg="";
	div.innerHTML = msg;
};
//----------------------------------------------------------------------------------------------------------------------------------------------------------






//--------------------------------------------IndustrySector--------------------------------------------------------------------------------------------------------

function sendFormIndustrySector()
{
		var div = document.getElementById('msg');
		var tbody = document.getElementById('tbodyInsdustrySector');
		var msg="";
		var form = $("#formIndustrySector").serialize();
		
		var url = "/admin/addindustrysector"; 
		    $.ajax({
		           type: "POST",
		           cache: false,
		           url: url,
		           data: form,

		           success: function(data)
		           {
		        	   if(data != null){
		        		  tbody.innerHTML = data;
		        		  if($("#idIndustrySector").val()==0) msg = "<p style='color: hsl(153,80%,40%)'>Se guardó la información correctamente <p>";
		        		  else  if($("#idIndustrySector").val()!=0) msg = "<p style='color: hsl(153,80%,40%)'>Se actualizó la información correctamente <p>";
		        		  clearIndustrySector(); //Clear
		        		  div.innerHTML = msg;
		        	   }else{
		        		   msg = "<p style='color:#800000'>Ha ocurrido un error inesperado!<p>";
		        		   div.innerHTML = msg;
		        		   }
		           }
		    	
		         });
}

//----------------------------------------------------------------------------------------------------------------------------------------------------------

//----------------------------editIndustrySector--------------------------------------------------------------------------------------------------------------------

function editIndustrySector(row){
	
	var idIndustrySector = $(row).parents("tr").find("#idIndustrySector span").eq(0).html();
	var detail = $(row).parents("tr").find("#detail span").eq(0).html();
	var div = document.getElementById('msg');
	var msg="";
	div.innerHTML = msg;
	$("#industrySectorCancel").show();
	
	$("#idIndustrySector").val(idIndustrySector);
	$("#detail").val(detail);
};

//----------------------------------------------------------------------------------------------------------------------------------------------------------
//----------------------------clearIndustrySector--------------------------------------------------------------------------------------------------------------------

function clearIndustrySector(){
	
	$("#industrySectorCancel").hide();
	
	$("#idIndustrySector").val(0);
	$("#detail").val("");
	var div = document.getElementById('msg');
	var msg="";
	div.innerHTML = msg;
};
//----------------------------------------------------------------------------------------------------------------------------------------------------------



//--------------------------------------------Operation--------------------------------------------------------------------------------------------------------

function sendFormOperation()
{
		var div = document.getElementById('msg');
		var tbody = document.getElementById('tbodyOperation');
		var msg="";
		var form = $("#formOperation").serialize();
		
		var url = "/admin/addoperation"; 
		    $.ajax({
		           type: "POST",
		           cache: false,
		           url: url,
		           data: form,

		           success: function(data)
		           {
		        	   if(data != null){
		        		  tbody.innerHTML = data;
		        		  if($("#idOperation").val()==0) msg = "<p style='color: hsl(153,80%,40%)'>Se guardó la información correctamente <p>";
		        		  else  if($("#idOperation").val()!=0) msg = "<p style='color: hsl(153,80%,40%)'>Se actualizó la información correctamente <p>";
		        		  clearOperation(); //Clear
		        		  div.innerHTML = msg;
		        	   }else{
		        		   msg = "<p style='color:#800000'>Ha ocurrido un error inesperado!<p>";
		        		   div.innerHTML = msg;
		        		   }
		           }
		    	
		         });
}

//----------------------------------------------------------------------------------------------------------------------------------------------------------

//----------------------------editOperation--------------------------------------------------------------------------------------------------------------------

function editOperation(row){
	
	var idOperation = $(row).parents("tr").find("#idOperation span").eq(0).html();
	var detail = $(row).parents("tr").find("#detail span").eq(0).html();
	var idOperationType = $(row).parents("tr").find("#idOperationType span").eq(0).html();
	var div = document.getElementById('msg');
	var msg="";
	div.innerHTML = msg;
	$("#operationCancel").show();
	
	$("#idOperation").val(idOperation);
	$("#detail").val(detail);
	$("#operationType").val(idOperationType).change();
};

//----------------------------------------------------------------------------------------------------------------------------------------------------------
//----------------------------clearOperation--------------------------------------------------------------------------------------------------------------------

function clearOperation(){
	
	$("#operationCancel").hide();
	
	$("#idOperation").val(0);
	$("#detail").val("");
	$("#operationType").val(1).change();
	var div = document.getElementById('msg');
	var msg="";
	div.innerHTML = msg;
};
//----------------------------------------------------------------------------------------------------------------------------------------------------------







//--------------------------------------------OperationType--------------------------------------------------------------------------------------------------------

function sendFormOperationType()
{
		var div = document.getElementById('msg');
		var tbody = document.getElementById('tbodyOperationType');
		var msg="";
		var form = $("#formOperationType").serialize();
		
		var url = "/admin/addoperationtype"; 
		    $.ajax({
		           type: "POST",
		           cache: false,
		           url: url,
		           data: form,

		           success: function(data)
		           {
		        	   if(data != null){
		        		  tbody.innerHTML = data;
		        		  if($("#idOperationType").val()==0) msg = "<p style='color: hsl(153,80%,40%)'>Se guardó la información correctamente <p>";
		        		  else  if($("#idOperationType").val()!=0) msg = "<p style='color: hsl(153,80%,40%)'>Se actualizó la información correctamente <p>";
		        		  clearOperationType(); //Clear
		        		  div.innerHTML = msg;
		        	   }else{
		        		   msg = "<p style='color:#800000'>Ha ocurrido un error inesperado!<p>";
		        		   div.innerHTML = msg;
		        		   }
		           }
		    	
		         });
}

//----------------------------------------------------------------------------------------------------------------------------------------------------------

//----------------------------editOperationType--------------------------------------------------------------------------------------------------------------------

function editOperationType(row){
	
	var idOperationType = $(row).parents("tr").find("#idOperationType span").eq(0).html();
	var detail = $(row).parents("tr").find("#detail span").eq(0).html();
	var div = document.getElementById('msg');
	var msg="";
	div.innerHTML = msg;
	$("#operationTypeCancel").show();
	
	$("#idOperationType").val(idOperationType);
	$("#detail").val(detail);
};

//----------------------------------------------------------------------------------------------------------------------------------------------------------
//----------------------------clearOperationType--------------------------------------------------------------------------------------------------------------------

function clearOperationType(){
	
	$("#operationTypeCancel").hide();
	
	$("#idOperationType").val(0);
	$("#detail").val("");
	var div = document.getElementById('msg');
	var msg="";
	div.innerHTML = msg;
};
//----------------------------------------------------------------------------------------------------------------------------------------------------------








//--------------------------------------------ProposalType--------------------------------------------------------------------------------------------------------

function sendFormProposalType()
{
		var div = document.getElementById('msg');
		var tbody = document.getElementById('tbodyProposalType');
		var msg="";
		var form = $("#formProposalType").serialize();
		
		var url = "/admin/addproposaltype"; 
		    $.ajax({
		           type: "POST",
		           cache: false,
		           url: url,
		           data: form,

		           success: function(data)
		           {
		        	   if(data != null){
		        		  tbody.innerHTML = data;
		        		  if($("#idProposalType").val()==0) msg = "<p style='color: hsl(153,80%,40%)'>Se guardó la información correctamente <p>";
		        		  else  if($("#idProposalType").val()!=0) msg = "<p style='color: hsl(153,80%,40%)'>Se actualizó la información correctamente <p>";
		        		  clearProposalType(); //Clear
		        		  div.innerHTML = msg;
		        	   }else{
		        		   msg = "<p style='color:#800000'>Ha ocurrido un error inesperado!<p>";
		        		   div.innerHTML = msg;
		        		   }
		           }
		    	
		         });
}

//----------------------------------------------------------------------------------------------------------------------------------------------------------

//----------------------------editProposalType--------------------------------------------------------------------------------------------------------------------

function editProposalType(row){
	
	var idProposalType = $(row).parents("tr").find("#idProposalType span").eq(0).html();
	var detail = $(row).parents("tr").find("#detail span").eq(0).html();
	var div = document.getElementById('msg');
	var msg="";
	div.innerHTML = msg;
	$("#proposalTypeCancel").show();
	
	$("#idProposalType").val(idProposalType);
	$("#detail").val(detail);
};

//----------------------------------------------------------------------------------------------------------------------------------------------------------
//----------------------------clearProposalType--------------------------------------------------------------------------------------------------------------------

function clearProposalType(){
	
	$("#proposalTypeCancel").hide();
	
	$("#idProposalType").val(0);
	$("#detail").val("");
	var div = document.getElementById('msg');
	var msg="";
	div.innerHTML = msg;
};
//----------------------------------------------------------------------------------------------------------------------------------------------------------







//--------------------------------------------Role--------------------------------------------------------------------------------------------------------

function sendFormRole()
{
		var div = document.getElementById('msg');
		var tbody = document.getElementById('tbodyRole');
		var msg="";
		var form = $("#formRole").serialize();
		
		var url = "/admin/addrole"; 
		    $.ajax({
		           type: "POST",
		           cache: false,
		           url: url,
		           data: form,

		           success: function(data)
		           {
		        	   if(data != null){
		        		  tbody.innerHTML = data;
		        		  if($("#idRole").val()==0) msg = "<p style='color: hsl(153,80%,40%)'>Se guardó la información correctamente <p>";
		        		  else  if($("#idRole").val()!=0) msg = "<p style='color: hsl(153,80%,40%)'>Se actualizó la información correctamente <p>";
		        		  clearRole(); //Clear
		        		  div.innerHTML = msg;
		        	   }else{
		        		   msg = "<p style='color:#800000'>Ha ocurrido un error inesperado!<p>";
		        		   div.innerHTML = msg;
		        		   }
		           }
		    	
		         });
}

//----------------------------------------------------------------------------------------------------------------------------------------------------------

//----------------------------editRole--------------------------------------------------------------------------------------------------------------------

function editRole(row){
	
	var idRole = $(row).parents("tr").find("#idRole span").eq(0).html();
	var detail = $(row).parents("tr").find("#detail span").eq(0).html();
	var status = $(row).parents("tr").find("#status span").eq(0).html();
	var div = document.getElementById('msg');
	var msg="";
	div.innerHTML = msg;
	$("#roleCancel").show();
	
	$("#idRole").val(idRole);
	$("#detail").val(detail);
	if(status == "Activo")$("#status").val(1).change();
	else $("#status").val(0).change();
};

//----------------------------------------------------------------------------------------------------------------------------------------------------------
//----------------------------clearRole--------------------------------------------------------------------------------------------------------------------

function clearRole(){
	
	$("#roleCancel").hide();
	
	$("#idRole").val(0);
	$("#detail").val("");
	$("#status").val(0).change();
	var div = document.getElementById('msg');
	var msg="";
	div.innerHTML = msg;
};
//----------------------------------------------------------------------------------------------------------------------------------------------------------







//--------------------------------------------SaClient--------------------------------------------------------------------------------------------------------

function sendFormSaClient()
{
		var div = document.getElementById('msg');
		var tbody = document.getElementById('tbodySaClient');
		var msg="";
		var form = $("#formSaClient").serialize();
		
		var url = "/admin/addsaclient"; 
		    $.ajax({
		           type: "POST",
		           cache: false,
		           url: url,
		           data: form,

		           success: function(data)
		           {
		        	   if(data != null){
		        		  tbody.innerHTML = data;
		        		  if($("#idSaClient").val()==0) msg = "<p style='color: hsl(153,80%,40%)'>Se guardó la información correctamente <p>";
		        		  else  if($("#idSaClient").val()!=0) msg = "<p style='color: hsl(153,80%,40%)'>Se actualizó la información correctamente <p>";
		        		  clearSaClient(); //Clear
		        		  div.innerHTML = msg;
		        	   }else{
		        		   msg = "<p style='color:#800000'>Ha ocurrido un error inesperado!<p>";
		        		   div.innerHTML = msg;
		        		   }
		           }
		    	
		         });
}

//----------------------------------------------------------------------------------------------------------------------------------------------------------

//----------------------------editSaClient--------------------------------------------------------------------------------------------------------------------

function editSaClient(row){
	
	var idSaClient = $(row).parents("tr").find("#idSaClient span").eq(0).html();
	var detail = $(row).parents("tr").find("#detail span").eq(0).html();
	var status = $(row).parents("tr").find("#status span").eq(0).html();
	var div = document.getElementById('msg');
	var msg="";
	div.innerHTML = msg;
	$("#saClientCancel").show();
	
	$("#idSaClient").val(idSaClient);
	$("#detail").val(detail);
	if(status == "Activo")$("#status").val(1).change();
	else $("#status").val(0).change();
};

//----------------------------------------------------------------------------------------------------------------------------------------------------------
//----------------------------clearSaClient--------------------------------------------------------------------------------------------------------------------

function clearSaClient(){
	
	$("#saClientCancel").hide();
	
	$("#idSaClient").val(0);
	$("#detail").val("");
	$("#status").val(0).change();
	var div = document.getElementById('msg');
	var msg="";
	div.innerHTML = msg;
};
//----------------------------------------------------------------------------------------------------------------------------------------------------------







//--------------------------------------------Settings--------------------------------------------------------------------------------------------------------

function sendFormSettings()
{
		var div = document.getElementById('msg');
		var tbody = document.getElementById('tbodySettings');
		var msg="";
		var form = $("#formSettings").serialize();
		
		var url = "/admin/addsettings"; 
		    $.ajax({
		           type: "POST",
		           cache: false,
		           url: url,
		           data: form,

		           success: function(data)
		           {
		        	   if(data != null){
		        		  tbody.innerHTML = data;
		        		  if($("#idSettings").val()==0) msg = "<p style='color: hsl(153,80%,40%)'>Se guardó la información correctamente <p>";
		        		  else  if($("#idSettings").val()!=0) msg = "<p style='color: hsl(153,80%,40%)'>Se actualizó la información correctamente <p>";
		        		  clearSettings(); //Clear
		        		  div.innerHTML = msg;
		        	   }else{
		        		   msg = "<p style='color:#800000'>Ha ocurrido un error inesperado!<p>";
		        		   div.innerHTML = msg;
		        		   }
		           }
		    	
		         });
}

//----------------------------------------------------------------------------------------------------------------------------------------------------------

//----------------------------editSettings--------------------------------------------------------------------------------------------------------------------

function editSettings(row){
	
	var idSettings = $(row).parents("tr").find("#idSettings span").eq(0).html();
	var imprevisto = $(row).parents("tr").find("#imprevisto span").eq(0).html();
	var factor1 = $(row).parents("tr").find("#factor1 span").eq(0).html();
	var aporteFijo = $(row).parents("tr").find("#aporteFijo span").eq(0).html();
	var factor2 = $(row).parents("tr").find("#factor2 span").eq(0).html();
	var idCurrencyTypeFavorite = $(row).parents("tr").find("#idCurrencyTypeFavorite span").eq(0).html();
	var idCurrencyTypeInternational = $(row).parents("tr").find("#idCurrencyTypeInternational span").eq(0).html();
	var div = document.getElementById('msg');
	var msg="";
	
	div.innerHTML = msg;
	imprevisto = imprevisto.substring(0,imprevisto.length-1);
	$("#settingsCancel").show();
	
	$("#idSettings").val(idSettings);
	$("#imprevisto").val(imprevisto);
	$("#factor1").val(factor1);
	$("#aporteFijo").val(aporteFijo);
	$("#factor2").val(factor2);
	$("#currencyTypeFavorite").val(idCurrencyTypeFavorite).change();
	$("#currencyTypeInternational").val(idCurrencyTypeInternational).change();
	
};

//----------------------------------------------------------------------------------------------------------------------------------------------------------
//----------------------------clearSettings--------------------------------------------------------------------------------------------------------------------

function clearSettings(){
	
	$("#settingsCancel").hide();
	
	$("#idSettings").val(0);
	$("#imprevisto").val("");
	$("#factor1").val("");
	$("#aporteFijo").val("");
	$("#factor2").val("");
	$("#country").val(1).change();
	$("#currencyTypeFavorite").val(1).change();
	$("#currencyTypeInternational").val(1).change();
	var div = document.getElementById('msg');
	var msg="";
	div.innerHTML = msg;
};
//----------------------------------------------------------------------------------------------------------------------------------------------------------





//--------------------------------------------Status--------------------------------------------------------------------------------------------------------

function sendFormStatus()
{
		var div = document.getElementById('msg');
		var tbody = document.getElementById('tbodyStatus');
		var msg="";
		var form = $("#formStatus").serialize();
		
		var url = "/admin/addstatus"; 
		    $.ajax({
		           type: "POST",
		           cache: false,
		           url: url,
		           data: form,

		           success: function(data)
		           {
		        	   if(data != null){
		        		  tbody.innerHTML = data;
		        		  if($("#idStatus").val()==0) msg = "<p style='color: hsl(153,80%,40%)'>Se guardó la información correctamente <p>";
		        		  else  if($("#idStatus").val()!=0) msg = "<p style='color: hsl(153,80%,40%)'>Se actualizó la información correctamente <p>";
		        		  clearStatus(); //Clear
		        		  div.innerHTML = msg;
		        	   }else{
		        		   msg = "<p style='color:#800000'>Ha ocurrido un error inesperado!<p>";
		        		   div.innerHTML = msg;
		        		   }
		           }
		    	
		         });
}

//----------------------------------------------------------------------------------------------------------------------------------------------------------

//----------------------------editStatus--------------------------------------------------------------------------------------------------------------------

function editStatus(row){
	
	var idStatus = $(row).parents("tr").find("#idStatus span").eq(0).html();
	var detail = $(row).parents("tr").find("#detail span").eq(0).html();
	var div = document.getElementById('msg');
	var msg="";
	div.innerHTML = msg;
	$("#statusCancel").show();
	
	$("#idStatus").val(idStatus);
	$("#detail").val(detail);
};

//----------------------------------------------------------------------------------------------------------------------------------------------------------
//----------------------------clearStatus--------------------------------------------------------------------------------------------------------------------

function clearStatus(){
	
	$("#statusCancel").hide();
	
	$("#idStatus").val(0);
	$("#detail").val("");
	var div = document.getElementById('msg');
	var msg="";
	div.innerHTML = msg;
};
//----------------------------------------------------------------------------------------------------------------------------------------------------------





//--------------------------------------------StudyCategory--------------------------------------------------------------------------------------------------------

function sendFormStudyCategory()
{
		var div = document.getElementById('msg');
		var tbody = document.getElementById('tbodyStudyCategory');
		var msg="";
		var form = $("#formStudyCategory").serialize();
		
		var url = "/admin/addStudyCategory"; 
		    $.ajax({
		           type: "POST",
		           cache: false,
		           url: url,
		           data: form,

		           success: function(data)
		           {
		        	   if(data != null){
		        		  tbody.innerHTML = data;
		        		  if($("#idStudyCategory").val()==0) msg = "<p style='color: hsl(153,80%,40%)'>Se guardó la información correctamente <p>";
		        		  else  if($("#idStudyCategory").val()!=0) msg = "<p style='color: hsl(153,80%,40%)'>Se actualizó la información correctamente <p>";
		        		  clearStudyCategory(); //Clear
		        		  div.innerHTML = msg;
		        	   }else{
		        		   msg = "<p style='color:#800000'>Ha ocurrido un error inesperado!<p>";
		        		   div.innerHTML = msg;
		        		   }
		           }
		    	
		         });
}

//----------------------------------------------------------------------------------------------------------------------------------------------------------

//----------------------------editStudyCategory--------------------------------------------------------------------------------------------------------------------

function editStudyCategory(row){
	
	var idStudyCategory = $(row).parents("tr").find("#idStudyCategory span").eq(0).html();
	var detail = $(row).parents("tr").find("#detail span").eq(0).html();
	var div = document.getElementById('msg');
	var msg="";
	div.innerHTML = msg;
	$("#studyCategoryCancel").show();
	
	$("#idStudyCategory").val(idStudyCategory);
	$("#detail").val(detail);
};

//----------------------------------------------------------------------------------------------------------------------------------------------------------
//----------------------------clearStudyCategory--------------------------------------------------------------------------------------------------------------------

function clearStudyCategory(){
	
	$("#studyCategoryCancel").hide();
	$("#idStudyCategory").val(0);
	$("#detail").val("");
	var div = document.getElementById('msg');
	var msg="";
	div.innerHTML = msg;
};
//----------------------------------------------------------------------------------------------------------------------------------------------------------







//--------------------------------------------StudyType--------------------------------------------------------------------------------------------------------

function sendFormStudyType()
{
		var div = document.getElementById('msg');
		var tbody = document.getElementById('tbodyStudyType');
		var msg="";
		var form = $("#formStudyType").serialize();
		
		var url = "/admin/addstudytype"; 
		    $.ajax({
		           type: "POST",
		           cache: false,
		           url: url,
		           data: form,

		           success: function(data)
		           {
		        	   if(data != null){
		        		  tbody.innerHTML = data;
		        		  if($("#idStudyType").val()==0) msg = "<p style='color: hsl(153,80%,40%)'>Se guardó la información correctamente <p>";
		        		  else  if($("#idStudyType").val()!=0) msg = "<p style='color: hsl(153,80%,40%)'>Se actualizó la información correctamente <p>";
		        		  clearStudyType(); //Clear
		        		  div.innerHTML = msg;
		        	   }else{
		        		   msg = "<p style='color:#800000'>Ha ocurrido un error inesperado!<p>";
		        		   div.innerHTML = msg;
		        		   }
		           }
		    	
		         });
}

//----------------------------------------------------------------------------------------------------------------------------------------------------------

//----------------------------editStudyType--------------------------------------------------------------------------------------------------------------------

function editStudyType(row){
	
	var idStudyType = $(row).parents("tr").find("#idStudyType span").eq(0).html();
	var detail = $(row).parents("tr").find("#detail span").eq(0).html();
	var div = document.getElementById('msg');
	var msg="";
	div.innerHTML = msg;
	$("#studyTypeCancel").show();
	
	$("#idStudyType").val(idStudyType);
	$("#detail").val(detail);
};

//----------------------------------------------------------------------------------------------------------------------------------------------------------
//----------------------------clearStudyType--------------------------------------------------------------------------------------------------------------------

function clearStudyType(){
	
	$("#studyTypeCancel").hide();
	$("#idStudyType").val(0);
	$("#detail").val("");
	var div = document.getElementById('msg');
	var msg="";
	div.innerHTML = msg;
};
//----------------------------------------------------------------------------------------------------------------------------------------------------------









//--------------------------------------------Technique--------------------------------------------------------------------------------------------------------

function sendFormTechnique()
{
		var div = document.getElementById('msg');
		var tbody = document.getElementById('tbodyTechnique');
		var msg="";
		var form = $("#formTechnique").serialize();
		
		var url = "/admin/addtechnique"; 
		    $.ajax({
		           type: "POST",
		           cache: false,
		           url: url,
		           data: form,

		           success: function(data)
		           {
		        	   if(data != null){
		        		  tbody.innerHTML = data;
		        		  if($("#idTechnique").val()==0) msg = "<p style='color: hsl(153,80%,40%)'>Se guardó la información correctamente <p>";
		        		  else  if($("#idTechnique").val()!=0) msg = "<p style='color: hsl(153,80%,40%)'>Se actualizó la información correctamente <p>";
		        		  clearTechnique(); //Clear
		        		
		        		  div.innerHTML = msg;
		        	   }else{
		        		   msg = "<p style='color:#800000'>Ha ocurrido un error inesperado!<p>";
		        		   div.innerHTML = msg;
		        		   }
		           }
		    	
		         });
}

//----------------------------------------------------------------------------------------------------------------------------------------------------------

//----------------------------editTechnique--------------------------------------------------------------------------------------------------------------------

function editTechnique(row){
	
	var idTechnique = $(row).parents("tr").find("#idTechnique span").eq(0).html();
	var detail = $(row).parents("tr").find("#detail span").eq(0).html();
	var div = document.getElementById('msg');
	var msg="";
	div.innerHTML = msg;
	$("#techniqueCancel").show();
	
	$("#idTechnique").val(idTechnique);
	$("#detail").val(detail);
};

//----------------------------------------------------------------------------------------------------------------------------------------------------------
//----------------------------clearTechnique--------------------------------------------------------------------------------------------------------------------

function clearTechnique(){
	
	$("#techniqueCancel").hide();
	$("#idTechnique").val(0);
	$("#detail").val("");
	var div = document.getElementById('msg');
	var msg="";
	div.innerHTML = msg;
};
//----------------------------------------------------------------------------------------------------------------------------------------------------------








//--------------------------------------------User--------------------------------------------------------------------------------------------------------

function sendFormUser()
{
		var div = document.getElementById('msg');
		var tbody = document.getElementById('tbodyUser');
		var msg="";
		var form = $("#formUser").serialize();
		
		var url = "/admin/adduser"; 
		    $.ajax({
		           type: "POST",
		           cache: false,
		           url: url,
		           data: form,

		           success: function(data)
		           {
		        	   if(data != null){
		        		  tbody.innerHTML = data;
		        		  if($("#idUser").val()==0) msg = "<p style='color: hsl(153,80%,40%)'>Se guardó la información correctamente <p>";
		        		  else  if($("#idUser").val()!=0) msg = "<p style='color: hsl(153,80%,40%)'>Se actualizó la información correctamente <p>";
		        		   $(".detailTable").hide();
		        			$(".columnHide").show();
		        			$("#hideDetails").hide();
		        			$("#showDetails").show();
		        		  clearUser(); //Clear
		        		  div.innerHTML = msg;
		        	   }else{
		        		   msg = "<p style='color:#800000'>Ha ocurrido un error inesperado!<p>";
		        		   div.innerHTML = msg;
		        		   }
		           }
		    	
		         });
}

//----------------------------------------------------------------------------------------------------------------------------------------------------------

//----------------------------editUser--------------------------------------------------------------------------------------------------------------------

function editUser(row){
	
   	var fullName = $(row).parents("tr").find("#fullName span").eq(0).html();
   
   	fullName= fullName.split(" ");
   	
   	var name = fullName[0];
	var midname = fullName[1];
	var lastname = fullName[2];
	var email = $(row).parents("tr").find("#email span").eq(0).html();
	var accountBank = $(row).parents("tr").find("#accountBank span").eq(0).html();
	var status = $(row).parents("tr").find("#status span").eq(0).html();
	var expired = $(row).parents("tr").find("#expired span").eq(0).html();
	var credentialExpired = $(row).parents("tr").find("#credentialExpired span").eq(0).html();
	var idRol = $(row).parents("tr").find("#idRol span").eq(0).html();
	var idUser = $(row).parents("tr").find("#idUser span").eq(0).html();
	var div = document.getElementById('msg');
	var msg="";
	div.innerHTML = msg;
	$("#userCancel").show();
	
	$("#name").val(name);
	$("#midname").val(midname);
	$("#lastname").val(lastname);
	$("#email").val(email);
	$("#accountBank").val(accountBank);
	$("#status").val(status).change();
	$("#expired").val(expired).change();;
	$("#credentialExpired").val(credentialExpired).change();
	$("#idRol").val(idRol).change();;
	$("#idUser").val(idUser);
};

//----------------------------------------------------------------------------------------------------------------------------------------------------------
//----------------------------clearUser--------------------------------------------------------------------------------------------------------------------

function clearUser(){
	
	$("#userCancel").hide();
	$("#name").val("");
	$("#midname").val("");
	$("#lastname").val("");
	$("#email").val("");
	$("#accountBank").val("");
	$("#status").val(0).change();
	$("#expired").val(0).change();;
	$("#credentialExpired").val(0).change();
	$("#password").val("");
	$("#idRol").val(1).change();;
	$("#idUser").val(0);
	var div = document.getElementById('msg');
	var msg="";
	div.innerHTML = msg;
};
//-----------------------------------------------Asigned Head contributor-----------------------------------------------------------------------------------


function asignedHead(){

	alert($("#rol").val());
	if($("#rol").val()!="Boss_contributor")
	{
		$("#asignedHead").toggle();
	}
	else
	{
		$("#asignedHead").hide()
	}
	
};


//----------------------------------------------------------------------------------------------------------------------------------------------------------
