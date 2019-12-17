'use strict';

var singleUploadForm = $("#singleUploadForm");
var singleFileUploadInput = $("#singleFileUploadInput");
let fileUploadError = $("#fileUploadError");
let fileUploadSuccess = $("#fileUploadSuccess");

let btnCalcJacobi = $("#calc-jacobi");
let btnCalcGaussSeidel = $("#calc-gauss-seidel");

let fileDownloadError = $("#fileDownloadError");
let fileDownloadSuccess = $("#fileDownloadSuccess");

let fileName;

$('#singleUploadForm').submit(
		function(event) {
			var formElement = this;
			// You can directly create form data from the form element
			// (Or you could get the files from input element and append them to
			// FormData as we did in vanilla javascript)
			var formData = new FormData(formElement);

			$.ajax({
				type : "POST",
				enctype : 'multipart/form-data',
				url : "/uploadFile",
				data : formData,
				processData : false,
				contentType : false,
				success : function(response) {
					console.log(response);
					fileUploadError.hide();
					fileUploadSuccess
							.html("<p>Arquivo carregado com sucesso!</p>");
					fileUploadSuccess.show();
					fileName = response.fileName; 
					enableMethods();
				},
				error : function(error) {
					console.log(error);
					fileUploadSuccess.hide();
					fileUploadError.text((response && response.message)
							|| " Ocorreu algum erro");
					fileUploadError.show();
				}
			});

			event.preventDefault();
		});

function enableMethods() {
	btnCalcJacobi.prop("disabled", false);
	btnCalcGaussSeidel.prop("disabled", false);
}

btnCalcJacobi.click(function(event) {
	let precision = $("#numPrecision").val();
	let maxIterations = $("#maxIterations").val();
	
	let params = { fileName, method: "Jacobi", precision, maxIterations };
	
	calculate(params);
});

function calculate(params) {
	console.log(params.method);
	
	$.post("/calculate", params, function() {
		fileDownloadError.html("<p>Iniciando cálculo de " + params.method + " com precisão de " + params.precision + "...</p>");		
	}).done(function(data) {
		fileDownloadSuccess.html("<p>Cálculo realizado com sucesso!</p>" +
				"<p>DownloadUrl : <a href='" + data.fileDownloadUri + "' target='_blank'>" + data.fileDownloadUri + "</a></p>");		
		console.log(data);
	}).fail(function(data) {
		fileDownloadError.html("<p>Erro ao tentar realizar o cálculo</p>");
	});	
}

btnCalcGaussSeidel.click(function(event) {
	let precision = $("#numPrecision").val();
	let maxIterations = $("#maxIterations").val();

	let params = { fileName, method: "GaussSeidel", precision, maxIterations };
	
	calculate(params);
});