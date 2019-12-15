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
	
}
