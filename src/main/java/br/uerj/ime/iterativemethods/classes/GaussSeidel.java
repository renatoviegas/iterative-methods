package br.uerj.ime.iterativemethods.classes;

import br.uerj.ime.iterativemethods.helper.HelperMatrix;
import br.uerj.ime.iterativemethods.interfaces.IterativeMethods;

public class GaussSeidel implements IterativeMethods {

	private double A[][];
	private double b[];
	private int order;

	public GaussSeidel(double A[][], double b[]) {
		if (A == null || b == null)
			throw new NullPointerException();

		if (A.length != b.length) {
			throw new IllegalArgumentException();
		}

		this.A = A;
		this.b = b;
		this.order = A.length;
	}

	@Override
	public void calculate(double precision, int maxIterations) {

		double[] result = solveSystem(maxIterations);

		HelperMatrix.print(result);

	}

	private double[] solveSystem(int maxIterations) {

		if (!converges()) {
			System.err.println("A solução não é possível, pois a matriz não converge!");
		}

		double x[] = initialize(new double[this.order]);
		int k = 0;

		while (k < maxIterations) {

			for (int i = 0; i < this.order; i++) {
				double x0 = 0;
				for (int j = 0; j < this.order; j++)
					if (i != j)
						x0 += A[i][j] * x[j];
				x[i] = (b[i] - x0) / A[i][i];
			}

			k++;
		}

		return x;
	}

	private boolean converges() {

		for (int i = 0; i < this.order; i++) {

			double diagonal = Math.abs(A[i][i]);
			double sum = 0;

			for (int j = 0; j < this.order; j++)
				if (i != j)
					sum += Math.abs(A[i][j]);

			if (sum >= diagonal)
				return false;
		}

		return true;
	}

	private double[] initialize(double[] m) {
		for (int i = 0; i < this.order; i++)
			m[i] = 0;

		return m;
	}

}
