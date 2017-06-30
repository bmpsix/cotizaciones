/**
 * Created by Justin Torres
 * 
 */

$( document ).ready(function(){
	
	$("#addAssessment").click(function(){
		$(".form-assessment").toggle("slow");
		
		var f = new Date();
		var date = (f.getMonth() + 1) + '/' + f.getDate() + '/' +  f.getFullYear();
		
		$("#idAssessment").val("0");
		$("#creationDate").val(date);
		$("#detail").val('');
		$("#idCurrencyExchange").val('').change();
		$("#idSaClient").val('').change();
		$("#idStatus").val('').change();
		
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
		
		var idAssessment = $("#idAssessment").val();
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
		        	   'idAssessment': idAssessment,
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
		
	$(".edit").click(function(){
		$(".form-assessment").show("slow");
		
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