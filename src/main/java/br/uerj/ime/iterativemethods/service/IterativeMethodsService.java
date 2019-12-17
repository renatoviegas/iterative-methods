package br.uerj.ime.iterativemethods.service;

import org.springframework.stereotype.Service;

import br.uerj.ime.iterativemethods.classes.GaussSeidel;
import br.uerj.ime.iterativemethods.classes.Jacobi;
import br.uerj.ime.iterativemethods.classes.Matrix;
import br.uerj.ime.iterativemethods.classes.MethodsEnum;
import br.uerj.ime.iterativemethods.exception.TypeOfMethodNotFoundException;
import br.uerj.ime.iterativemethods.interfaces.IterativeMethods;

@Service
public class IterativeMethodsService {

	public IterativeMethods calculate(MethodsEnum typeOfMethod, Matrix matrix, double precision, int maxIterations) {
		
		IterativeMethods iterativeMethods = null;
				
		System.out.println("Calculando " + typeOfMethod + " com precisão de " + precision);		
		System.out.println(matrix.toString());
		
		switch (typeOfMethod) {
		case Jacobi:
			iterativeMethods = new Jacobi(matrix.getMatrix(), matrix.getConstants());
			break;
		case GaussSeidel:
			iterativeMethods = new GaussSeidel(matrix.getMatrix(), matrix.getConstants());
			break;
		default:
			throw new TypeOfMethodNotFoundException("Tipo de método não informado");
		}
		
		System.out.println(maxIterations);
		
		iterativeMethods.calculate(precision, maxIterations);
		
		return iterativeMethods;
	}
	
}
