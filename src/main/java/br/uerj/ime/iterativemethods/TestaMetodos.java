package br.uerj.ime.iterativemethods;

import br.uerj.ime.iterativemethods.classes.Jacobi;
import br.uerj.ime.iterativemethods.classes.Matrix;
import br.uerj.ime.iterativemethods.interfaces.IterativeMethods;

public class TestaMetodos {

	public static void main(String[] args) {
		
		double precisao = 0.01;
		double matriz[][] = new double[3][3];
		double constants[] = new double[3];

		matriz[0][0] = 10;
		matriz[0][1] = 2;
		matriz[0][2] = 3;
		matriz[1][0] = 1;
		matriz[1][1] = 5;
		matriz[1][2] = 1;
		matriz[2][0] = 2;
		matriz[2][1] = 3;
		matriz[2][2] = 10;
		
		constants[0] = 7;
		constants[1] = -8;
		constants[2] = 6;
		
		Matrix m = new Matrix(matriz, constants);
		
		IterativeMethods jacobi = new Jacobi();		
		jacobi.calculate(m, precisao, 100);
	}

}
