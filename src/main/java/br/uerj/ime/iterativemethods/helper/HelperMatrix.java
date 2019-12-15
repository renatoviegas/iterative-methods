package br.uerj.ime.iterativemethods.helper;

public class HelperMatrix {

	public static boolean diagonalHasZero(double m[][]) {

		int orderMatrix = order(m);

		for (int i = 0; i < orderMatrix; i++) {
			if (m[i][i] == 0)
				return true;
		}

		return false;

	}

	public static int getElementWithZeroInDiagonalMain(double m[][]) {

		int orderMatrix = order(m);

		for (int i = 0; i < orderMatrix; i++) {
			if (m[i][i] == 0)
				return i;
		}

		return -1;
	}

	public static int order(double m[][]) {
		return m.length;
	}

	public static void print(double m[][]) {

		int n = m.length;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				System.out.print("[" + m[i][j] + "]  ");
		}
	}

	public static void print(double m[]) {

		int n = m.length;
		
		System.out.println("");
		for (int i = 0; i < n; i++) {
				System.out.print("[" + m[i] + "]  ");
		}
	}


}
