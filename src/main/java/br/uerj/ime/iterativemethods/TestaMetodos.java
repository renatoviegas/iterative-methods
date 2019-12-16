package br.uerj.ime.iterativemethods;

import br.uerj.ime.iterativemethods.classes.GaussSeidel;
import br.uerj.ime.iterativemethods.classes.Jacobi;
import br.uerj.ime.iterativemethods.classes.Matrix;
import br.uerj.ime.iterativemethods.interfaces.IterativeMethods;

public class TestaMetodos {

	public static void main(String[] args) {

		double precisao = 0.01;
		double matriz[][] = { { 10, 2, 3 }, { 1, 5, 1 }, { 2, 3, 10 } };
		double constants[] = {7, -8, 6};

		Matrix m = new Matrix(matriz, constants);

		/*
		 * IterativeMethods jacobi = new Jacobi(); jacobi.calculate(m, precisao, 100);
		 */

		IterativeMethods gaussSeidel = new GaussSeidel();
		gaussSeidel.calculate(m, precisao, 100);
	}

}
