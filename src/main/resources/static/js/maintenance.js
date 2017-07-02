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
	//$("#clientCancel").hide();
	
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
	
	$("#idClient").val(0);
	$("#saClient").val(1).change();
	$("#country").val(1).change();
	$("#detail").val("");
	$("#email").val("");
	$("#phone").val("");
	$("#fax").val("");
	$("#clientType").val(1).change();
	$("#status").val(1).change();
	var div = document.getElementById('msg');
	var msg="";
	div.innerHTML = msg;
};

//----------------------------------------------------------------------------------------------------------------------------------------------------------


