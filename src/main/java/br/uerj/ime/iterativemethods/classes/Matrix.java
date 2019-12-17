package br.uerj.ime.iterativemethods.classes;

import br.uerj.ime.iterativemethods.helper.HelperMatrix;

public class Matrix {

	private double matrix[][];
	private double constants[];
	
	public Matrix(double matrix[][], double constants[]) {
		this.matrix = matrix;
		this.constants = constants;
	}
	
	public int getOrder() {
		return HelperMatrix.order(this.matrix);
	}
	
	public void loadMatrixFromFile() {
		
	}
	
	public double[][] getMatrix() {
		return this.matrix;
	}
	
	public double[] getConstants() {
		return this.constants;
	}	
	
	@Override
	public String toString() {		
		int n = this.matrix.length;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				System.out.print("[" + this.matrix[i][j] + "]  ");
			System.out.print(" = [" + this.constants[i] + "]");
			System.out.println("");
		}
		
		return "";
	}
}
