'use strict';

var singleUploadForm = $("#singleUploadForm");
var singleFileUploadInput = $("#singleFileUploadInput");
let fileUploadError = $("#fileUploadError");
let fileUploadSuccess = $("#fileUploadSuccess");

let btnCalcJacobi = $("#calc-jacobi");
let btnCalcGaussSeidel = $("#calc-gauss-seidel");

let fileDownloadError = $("#fileDownloadError");
let fileDownloadSuccess = $("#fileDownloadSuccess");

let fileDownloadUri;

$('#singleUploadForm').submit(function(event) {
    var formElement = this;
    // You can directly create form data from the form element
    // (Or you could get the files from input element and append them to FormData as we did in vanilla javascript)
    var formData = new FormData(formElement);

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/uploadFile",
        data: formData,
        processData: false,
        contentType: false,
        success: function (response) {
            console.log(response);
            fileUploadError.hide();
            fileUploadSuccess.html("<p>Arquivo carregado com sucesso!</p>");
            fileUploadSuccess.show();
            fileDownloadUri = response.fileDownloadUri;
            enableMethods();
        },
        error: function (error) {
            console.log(error);
            fileUploadSuccess.hide();
            fileUploadError.text((response && response.message) || " Ocorreu algum erro");
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
	console.log("Calculando Jacobi...");
	
	fileDownloadSuccess.text("Cálculo realizado com sucesso!");
	fileDownloadSuccess.html("<p>DownloadUrl : <a href='" + fileDownloadUri + "' target='_blank'>" + fileDownloadUri + "</a></p>");
});

btnCalcGaussSeidel.click(function(event) {
	console.log("Calculando Gauss-Seidel...")
});