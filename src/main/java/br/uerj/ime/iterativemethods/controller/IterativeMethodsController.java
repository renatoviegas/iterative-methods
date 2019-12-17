package br.uerj.ime.iterativemethods.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.uerj.ime.iterativemethods.classes.Matrix;
import br.uerj.ime.iterativemethods.classes.MethodsEnum;
import br.uerj.ime.iterativemethods.payload.CalculateResponse;
import br.uerj.ime.iterativemethods.service.IterativeMethodsService;
import br.uerj.ime.iterativemethods.service.MatrixService;

@RestController
public class IterativeMethodsController {
	
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);	

	@Autowired
	private MatrixService matrixService;
	
	@Autowired
	private IterativeMethodsService service;

	@PostMapping("/calculate")
	public CalculateResponse calculate(@RequestParam("fileName") String fileName, @RequestParam("method") String method, @RequestParam("precision") double precision, @RequestParam("maxIterations") int maxIterations) {
						
		logger.info("FileName: " + fileName);
		logger.info("Method: " + method);
		logger.info("Precision: " + precision);
		logger.info("MaxIterations: " + maxIterations);
		
		Matrix matrix = matrixService.loadMatrixFromFile(fileName);
		
		service.calculate(MethodsEnum.valueOf(method), matrix, precision, maxIterations);
		
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/").path(fileName).toUriString();
					
		return new CalculateResponse(method, precision, fileName, fileDownloadUri);				
	}

}
