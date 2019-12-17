package br.uerj.ime.iterativemethods.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import br.uerj.ime.iterativemethods.classes.Matrix;
import br.uerj.ime.iterativemethods.exception.MyFileNotFoundException;

@Service
public class MatrixService {

	@Autowired
	private FileStorageService fileStorageService;

	@Autowired
	public MatrixService(FileStorageService fileStorageService) {
		this.fileStorageService = fileStorageService;
	}

	public Matrix loadMatrixFromFile(String fileName) {

		if (fileName == null) {
			throw new MyFileNotFoundException("Matriz não carregada!");
		}
		
		Resource resource = fileStorageService.loadFileAsResource(fileName);
		int n = 0;
		BufferedReader br = null;

		try {
			br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
			n = (int) br.lines().count() - 1;
			br.close();
		} catch (Exception e) {
			System.err.println("Error: " + e);
		}

		try {
			br = new BufferedReader(new InputStreamReader(resource.getInputStream()));

			double A[][] = new double[n][n];
			double b[] = new double[n];

			// -- Carregando a matriz A

			for (int i = 0; i < n; i++) {
				A[i] = extractedArrayDoubleFromLineString(br.readLine());
			}

			// -- Carregando o vetor de constantes b
			b = extractedArrayDoubleFromLineString(br.readLine());

			br.close();
			
			return new Matrix(A, b);

		} catch (IOException e) {
			System.err.println("Error: " + e);
			return null;
		}
	}

	private double[] extractedArrayDoubleFromLineString(String line) {
		String arr[] = line.split(";");
		int n = arr.length;
		double arrDouble[] = new double[n];

		for (int i = 0; i < n; i++) {
			arrDouble[i] = Double.parseDouble(arr[i]);
		}

		return arrDouble;
	}

}
