package br.uerj.ime.iterativemethods.exception;

public class TypeOfMethodNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TypeOfMethodNotFoundException(String message) {
		super(message);
	}

	public TypeOfMethodNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
