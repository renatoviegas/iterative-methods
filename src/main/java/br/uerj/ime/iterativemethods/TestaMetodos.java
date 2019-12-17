package br.uerj.ime.iterativemethods;

import br.uerj.ime.iterativemethods.classes.GaussSeidel;
import br.uerj.ime.iterativemethods.interfaces.IterativeMethods;

public class TestaMetodos {

	public static void main(String[] args) {

		double precision = 0.01;
		double matrix[][] = { { 10, 2, 3 }, { 1, 5, 1 }, { 2, 3, 10 } };
		double constants[] = {7, -8, 6};
		int maxIterations = 100;

		/*
		 * IterativeMethods jacobi = new Jacobi(matriz, constants); jacobi.calculate(precision, 100);
		 */

		IterativeMethods gaussSeidel = new GaussSeidel(matrix, constants);
		gaussSeidel.calculate(precision, maxIterations);
	}

}
