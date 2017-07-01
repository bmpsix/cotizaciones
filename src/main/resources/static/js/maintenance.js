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
		        		  alert(data);
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
	
	var idClient = $(this).parents("tr").find("#idClient span").eq(0).html();
	var SaClient = $(this).parents("tr").find("#SaClient span").eq(0).html();
	var Country = $(this).parents("tr").find("#Country span").eq(0).html();
	var detail = $(this).parents("tr").find("#detail span").eq(0).html();
	var email = $(this).parents("tr").find("#email span").eq(0).html();
	var phone = $(this).parents("tr").find("#phone span").eq(0).html();
	var fax = $(this).parents("tr").find("#fax span").eq(0).html();
	var ClientTypeDetail = $(this).parents("tr").find("#ClientTypeDetail span").eq(0).html();
	var status = $(this).parents("tr").find("#status span").eq(0).html();
	
	/*alert(idClient+SaClient+Country+detail+email+phone+fax+ClientTypeDetail+status);*/
	
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
	});


});

