/**
 * Created by Justin Torres
 * 
 */

$( document ).ready(function(){
	
	$("#addAssessment").click(function(){
		$(".form-assessment").toggle("slow");
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
		
		var creationDate = $("#creationDate").val();
		var detail = $("#detail").val();
		var idCurrencyExchange = $("#idCurrencyExchange").val();
		var idSaClient = $("#idSaClient").val();
		var idStatus = $("#idStatus").val();
		var idUser = $("#idUser").val();
		

		var url = "/admin/addassessment"; // El script a dónde se realizará la petición.
		    $.ajax({
		           type: "POST",
		           cache: false,
		           url: url,
		           data: { 
		        	   'creationDate': creationDate, 
		        	   'detail' : detail,
		        	   'idCurrencyExchange' : idCurrencyExchange,
		        	   'idSaClient' : idSaClient,
		        	   'idStatus' : idStatus,
		        	   'idUser' : idUser
		        		 },  // Adjuntar los campos del formulario enviado.

		           success: function(data)
		           {
		        	   if(data != null){
		        		   location.reload();
		        	   }else{
		        		   alert("false");
		        		   }
		        	   }
		         });
		 });
	
	/*th:value="${#dates.format(updateAssessment.creationDate, 'dd-MMM-yyyy')}" formatear la fecha en html5*/
});