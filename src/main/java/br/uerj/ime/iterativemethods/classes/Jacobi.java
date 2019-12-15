package br.uerj.ime.iterativemethods.classes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.uerj.ime.iterativemethods.controller.FileController;
import br.uerj.ime.iterativemethods.exception.ElementByMainDiagonalMatrixHasZeroException;
import br.uerj.ime.iterativemethods.helper.HelperMatrix;
import br.uerj.ime.iterativemethods.interfaces.IterativeMethods;

public class Jacobi implements IterativeMethods {

	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	private static int STEP = 10;

	@Override
	public void calculate(Matrix matrix, double precision, int maxIterations) {

		int n = matrix.getOrder();

		double A[][] = matrix.getMatrix();
		double b[] = matrix.getConstants();
		double previousResult[] = new double[n];
		double currentResults[] = new double[n];
		double delta, sum;
		int iterations = 0;

		// valida a matriz
		int element = HelperMatrix.getElementWithZeroInDiagonalMain(A);
		if (element > -1) {
			new ElementByMainDiagonalMatrixHasZeroException(element);
		}

		
		for (int i = 0; i < n; i++) {			
			for (int j = 0; j < n; j++) System.out.print("[" + A[i][j] + "]  ");			
			System.out.println("[x" + i + "] = [" + b[i] + "]");
		}
		
		System.out.println("");
		System.out.println("Iniciando o cálculo utilizando o metódo de Jacobi com precisão: " + precision);

		System.out.println("");
		System.out.print("[" + ++iterations + "] ");
		
		for (int i = 0; i < n; i++) {
			previousResult[i] = b[i] / A[i][i];
			System.out.print("(" + previousResult[i] + ")  ");
		}

		delta = calculateError(currentResults, previousResult, n);
		System.out.print(" [erro = " + delta);
		
		while (iterations < maxIterations) {
			
			System.out.print("[" + ++iterations + "] ");
			
			for (int i = 0; i < n; i++) {
				sum = b[i];
				System.out.println("");
				
				for (int j = 0; j < n; j++)
					if (i != j) sum -= (A[i][j] * previousResult[j]);
				
				
				currentResults[i] = sum / A[i][i];
				System.out.print("(" + currentResults[i] + ")  ");
				
			}
			
			if (iterations % STEP == 0) {
				delta = calculateError(currentResults, previousResult, n);
				System.out.print(" [erro = " + delta);
				
				if (delta < precision) {
					System.out.println("Sair");
				}
			}
			
			previousResult = currentResults.clone();
		}
		
		HelperMatrix.print(currentResults);

	}

	private double calculateError(double[] currentResults, double[] previousResult, int n) {
		double result[] = new double[n];

		for (int i = 0; i < n; i++) {
			result[i] = Math.abs(currentResults[i] - previousResult[i]);
		}

		int qty = 0;
		double max = 0;

		while (qty < n) {
			max = Math.max(max, result[qty]);
			qty++;
		}

		return max;
	}

}
