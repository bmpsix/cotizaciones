/**
 * Created by Justin Torres
 * 
 */

var email;


$( document ).ready(function(){
	
	$("#addAssessment").click(function(){
		$("#divShared").hide("slow");
		$("#divAssign").hide("slow");
		$(".form-assessment").toggle("slow");
		
		var f = new Date();
		var date = (f.getMonth() + 1) + '/' + f.getDate() + '/' +  f.getFullYear();
		
		$("#idAssessment").val("0");
		$("#creationDate").val(date);
		$("#detail").val("");
		
		
	});
	
	$(".ui-filterable").focusin(function(){
		$(".form-assessment").hide("slow");
	});
	
	
	$(function () {
	    var token = $("input[name='_csrf']").val();
	    var header = "X-CSRF-TOKEN";
	    $(document).ajaxSend(function(e, xhr, options) {
	        xhr.setRequestHeader(header, token);
	    });
	});
	
	$("#sendAssessment").click(function(){
		
		$("#assessmentCancel").hide();
		var div = document.getElementById('msg');
		var div2 = document.getElementById('msg2');
		var msg="";
		var idAssessment = $("#idAssessment").val();
		var creationDate = $("#creationDate").val();
		var detail = $("#detail").val();
		var idCurrencyExchange = $("#idCurrencyExchange").val();
		var idSaClient = $("#idSaClient").val();
		var idStatus = $("#idStatus").val();
		var idUser = $("#idUser").val();
		
		
		if(detail==null || detail=="")
		{
			msg = "<p style='color:#800000'>Debe completar el formulario<p>";
 		   div2.innerHTML = msg;
		}
		else
		{
			var url = "/admin/addassessment"; // El script a dónde se realizará la petición.
		    $.ajax({
		           type: "POST",
		           cache: false,
		           url: url,
		           data: { 
		        	   'idAssessment': idAssessment,
		        	   'creationDate': creationDate,
		        	   'detail' : detail,
		        	   'idCurrencyExchange' : idCurrencyExchange,
		        	   'idSaClient' : idSaClient,
		        	   'idStatus' : idStatus
		        		 },  // Adjuntar los campos del formulario enviado.

		           success: function(data)
		           {
		        	   if(data != null){
		        		  location.reload();
		        	   }else{
		        		   msg = "<p style='color:#800000'>Ha ocurrido un error inesperado!<p>";
		        		   div.innerHTML = msg;
		        		   }
		        	   }
		         });
			}
		 });
	
	
	
	
	$(".assessmentCancel").click(function(){
		$(".form-assessment").hide("slow");
		$("#assessmentCancel").hide();
		$("#idAssessment").val(0);
		$("#creationDate").val("");
		$("#detail").val("");
		$("#idCurrencyExchange").val(1).change();
		$("#idSaClient").val(1).change();
		$("#idStatus").val(0).change();

	});
	
});




function editAssessment(x)
{
	$(".form-assessment").show("slow");
	$("#divShared").hide("slow");
	var id = $(x).parents("tr").find("#idAssessment span").eq(0).html();
	var creationDate = $(x).parents("tr").find("#creationDate span").eq(0).html();
	var detail = $(x).parents("tr").find("#detail span").eq(0).html();
	var currencyExchange = $(x).parents("tr").find("#idCurrencyExchange span").eq(0).html();
	var saClient = $(x).parents("tr").find("#idSaClient span").eq(0).html();
	var name = $(x).parents("tr").find("#name span").eq(0).html();
	var status = $(x).parents("tr").find("#idStatus span").eq(0).html();
	
	$("#assessmentCancel").show();
	$("#idAssessment").val(id);
	$("#creationDate").val(creationDate);
	$("#detail").val(detail);
	$("#idCurrencyExchange").val(currencyExchange).change();
	$("#idSaClient").val(saClient).change();
	$("#idStatus").val(status).change();

};

function chargeUserSharedByCountry()
{
	var country = $("#country").val();
	var div = document.getElementById("userShared");
	var table = document.getElementById("userTable");
	var select="";

	for(count=0;count<=table.rows.length-1;count++)
	{
		
		if(country == table.rows[count].cells[1].innerText)
		{
			select=select+"<option value='"+table.rows[count].cells[2].innerText+"'>"+table.rows[count].cells[0].innerText+"</option>";
		}
	}
	
	div.innerHTML = select;
	$("#userShared").val("");
}



//--------------------------------------------AssessmentShared--------------------------------------------------------------------------------------------------------



function sendformShared()
{
		var div = document.getElementById('msgShared');
		var tbodyShared = document.getElementById('tbodyShared');
		var msgShared="";
		var form = $("#formShared").serialize();
		var url = "/assessment/addassessmentshared"; 
		    $.ajax({
		           type: "POST",
		           cache: false,
		           url: url,
		           data: form,

		           success: function(data)
		           {
		        	   if(data != null){
		        		  tbodyShared.innerHTML = data;
		        		  if($("#msgAction").val()==1)  msgShared = "<p style='color: #800000'>Ya se le ha compartido el proyecto a este usuario<p>";
		        		  else msgShared = "<p style='color: hsl(153,80%,40%)'>Se compartió el proyecto correctamente <p>";
		        		  div.innerHTML = msgShared;
		        		  $("#msgAction").val("");
		        	   }else{
		        		   msg = "<p style='color:#800000'>Ha ocurrido un error inesperado!<p>";
		        		   div.innerHTML = msgShared;
		        		   }
		           }
		    	
		         });
}

//----------------------------------------------------------------------------------------------------------------------------------------------------------

//----------------------------deleteShared--------------------------------------------------------------------------------------------------------------------

function deleteShared(x){
	
	var div = document.getElementById('msgShared');
	var tbodyShared = document.getElementById('tbodyShared');
	var msgShared="";
	var idAssessmentShared = $(x).parents("tr").find("#idAssessmentShared span").eq(0).html();
	var msgAction = $("#msgAction").val();
	
	var url = "/assessment/deleteassessmentshared"; 
	    $.ajax({
	           type: "POST",
	           cache: false,
	           url: url,
	           data: {'idAssessmentShared': idAssessmentShared},

	           success: function(data)
	           {
	        	   if(data != null){
	        		   tbodyShared.innerHTML = data;
	        		   if($("#msgAction").val()==2) msgShared = "<p style='color: #800000'>Acción inválida, el usuario tiene propuestas asociadas a este proyecto.<p>";
	        		   else msgShared = "<p style='color: hsl(153,80%,40%)'>Se dejó de compartir el proyecto con el usuario <p>";
	        		  div.innerHTML = msgShared;
	        		  $("#msgAction").val("");
	        	   }else{
	        		   msgShared = "<p style='color:#800000'>Ha ocurrido un error inesperado!<p>";
	        		   div.innerHTML = msgShared;
	        		   }
	           }
	    	
	         });
};

//----------------------------------------------------------------------------------------------------------------------------------------------------------
//----------------------------clearShared--------------------------------------------------------------------------------------------------------------------

function clearShared(){
	$("#divShared").hide("slow");
	$("#country").val(1).change();
	var div = document.getElementById('msgShared');
	var msgShared="";
	div.innerHTML = msgShared;
};
//------------------------------------------sharedAssessmentButton----------------------------------------------------------------------------------------------------------------


function sharedProject(x)
{
	var idAssessment = $(x).parents("tr").find("#idAssessment span").eq(0).html();
	var detail = $(x).parents("tr").find("#detail span").eq(0).html();
	$(".form-assessment").hide("slow");
	$("#idAssessmentToShared").val(idAssessment);
	$("#assessmentDetail").val(detail);
	$("#divAssign").hide("slow");
	$("#divShared").show("slow");
};

//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



//--------------------------------------------------------AssignAssessment--------------------------------------------------------------------------------------------------------------------------


function sendformAssign()
{
		var div = document.getElementById('msgAssign');
		var msgAssign="";
		var form = $("#formAssign").serialize();
		
		var url = "/assessment/assign"; 
		    $.ajax({
		           type: "POST",
		           cache: false,
		           url: url,
		           data: form,

		           success: function(data)
		           {
		        	   
		        	   if(data != null){
		        		
		        		  location.reload();
		        		
		        	   }else{
		        		   msgAssign = "<p style='color:#800000'>Ha ocurrido un error inesperado!<p>";
		        		   div.innerHTML = msg;
		        	   }
		           }
		    	
		         });
}

//----------------------------clearAssign--------------------------------------------------------------------------------------------------------------------

function clearAssign(){
	$("#divAssign").hide("slow");
	$("#countryAssign").val(1).change();
	var div = document.getElementById('msgAssign');
	var msgAssign="";
	div.innerHTML = msgAssign;
};
//------------------------------------------assignAssessmentButton----------------------------------------------------------------------------------------------------------------


function assignProject(x)
{
	var idAssessment = $(x).parents("tr").find("#idAssessment span").eq(0).html();
	var detail = $(x).parents("tr").find("#detail span").eq(0).html();
	$(".form-assessment").hide("slow");
	$("#idAssessmentToAssign").val(idAssessment);
	$("#assessmentDetailAssign").val(detail);
	$("#divShared").hide("slow");
	$("#divAssign").show("slow");
};
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------




//---------------------------------------------------userByCountryMethod-------------------------------------------------------------------------------------------------------------------------------


function chargeUserByCountryAssign()
{
	var country = $("#countryAssign").val();
	var div = document.getElementById("userAssign");
	var table = document.getElementById("userTable");
	var select="";

	for(count=0;count<=table.rows.length-1;count++)
	{
		
		if(country == table.rows[count].cells[1].innerText)
		{
			select=select+"<option value='"+table.rows[count].cells[2].innerText+"'>"+table.rows[count].cells[0].innerText+"</option>";
		}
	}
	
	div.innerHTML = select;
	$("#userAssign").val("");
}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



//--------------------------------------------functionBodyLoad---------------------------------------------------------------------------------------------------------------------------------
function onLoadProjects()
{
	chargeUserSharedByCountry(); 
	chargeUserByCountryAssign();
};

//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------




//---------------------------------------------------projectProposalList-------------------------------------------------------------------------------------------------------------------------------
function projectProposal(x)
{
	var idAssessment = $(x).parents("tr").find("#idAssessment span").eq(0).html();
	
	var url = "/assessment/proposal"; 
	    $.ajax({
	           type: "POST",
	           cache: false,
	           url: url,
	           data: {
	        	   'idAssessment' : idAssessment
	           },

	           success: function(data)
	           {
	        	   if(data != null){
	        		
	        		 location="/admin/proposal";
	        	   }
	           }
	    	
	         });
};




