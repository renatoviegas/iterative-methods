package br.uerj.ime.iterativemethods.classes;

import br.uerj.ime.iterativemethods.exception.ElementByMainDiagonalMatrixHasZeroException;
import br.uerj.ime.iterativemethods.helper.HelperMatrix;
import br.uerj.ime.iterativemethods.interfaces.IterativeMethods;

public class GaussSeidel implements IterativeMethods {

	@Override
	public void calculate(Matrix matrix, double precision, int maxIterations) {

		double A[][] = matrix.getMatrix();
		double b[] = matrix.getConstants();
		int n = A.length;

		double previousResult[] = new double[n];
		double currentResults[] = new double[n];
		int iterations = 0;

		// valida a matriz
		int element = HelperMatrix.getElementWithZeroInDiagonalMain(A);
		if (element > -1) {
			new ElementByMainDiagonalMatrixHasZeroException(element);
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				System.out.print("[" + A[i][j] + "]  ");
			System.out.println("[x" + i + "] = [" + b[i] + "]");
		}

		System.out.println("");
		System.out.println("Iniciando o cálculo utilizando o metódo de Gauss-Seidel com precisão: " + precision);

		System.out.println("");

		for (int i = 0; i < n; i++)
			previousResult[i] = 0;

		while (true) {

			iterations++;

			for (int i = 0; i < n; i++) {

				currentResults[i] = b[i];

				for (int j = 0; j < n; j++) {

					if (i > j) {
						currentResults[i] -= A[i][j] * currentResults[j];
					}

					if (i < j) {
						currentResults[i] -= A[i][j] * previousResult[j];
					}
				}

				currentResults[i] /= A[i][i];
			}

			double error = 0;

			for (int i = 0; i < n; i++) {

				double errorTemp = 0;
				for (int j = 0; j < n; j++) {
					errorTemp += A[i][j] * currentResults[j];
				}

				error += Math.abs(errorTemp - b[i]);
			}

			if (error < precision) {
				break;
			}

			previousResult = currentResults;

		}

		HelperMatrix.print(A);
		HelperMatrix.print(previousResult);
		System.out.println(iterations);

		for (double[] a : A) {
			double errorTemp = 0;
			for (int j = 0; j < n; j++) {
				errorTemp += a[j] * previousResult[j];
			}

			System.out.print(errorTemp + " ");
		}

	}

}
