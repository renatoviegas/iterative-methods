package br.uerj.ime.iterativemethods.exception;

public class ElementByMainDiagonalMatrixHasZeroException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ElementByMainDiagonalMatrixHasZeroException(String message) {
		super(message);		
	}
	
	public ElementByMainDiagonalMatrixHasZeroException(int element) {
		String message = "O elemento da matriz[" + element + "][" + element + "] deve ser diferente de zero!";
		throw new ElementByMainDiagonalMatrixHasZeroException(message);
	}

	
}
