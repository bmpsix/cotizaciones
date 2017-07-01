/**
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
		        		  data=null;
		        		   msg = "<p style='color: hsl(153,80%,40%)'>Se guardó la información correctamente <p>";
		        		   div.innerHTML = msg;
		        	   }else{
		        		   alert("false");
		        		   }
		        	   }
		         });
}

//----------------------------------------------------------------------------------------------------------------------------------------------------------

$( document ).ready(function() {
	$(".edit").click(function(){
	
	var id = $(this).parents("tr").find("#idAssessment span").eq(0).html();
	var creationDate = $(this).parents("tr").find("#creationDate span").eq(0).html();
	var detail = $(this).parents("tr").find("#detail span").eq(0).html();
	var currencyExchange = $(this).parents("tr").find("#idCurrencyExchange span").eq(0).html();
	var saClient = $(this).parents("tr").find("#idSaClient span").eq(0).html();
	var name = $(this).parents("tr").find("#name span").eq(0).html();
	var status = $(this).parents("tr").find("#idStatus span").eq(0).html();
	
	$("#idAssessment").val(id);
	$("#creationDate").val(creationDate);
	$("#detail").val(detail);
	$("#idCurrencyExchange").val(currencyExchange).change();
	$("#idSaClient").val(saClient).change();
	$("#idStatus").val(status).change();

	});


});

