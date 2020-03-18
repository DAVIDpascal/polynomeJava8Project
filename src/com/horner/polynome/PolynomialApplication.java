package com.horner.polynome;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Pascal David, 2020-03-18
 */
public class PolynomialApplication {
	public enum Coefficients {
	    RANGE_COEFFICIENT, 
	    RANDOM_COEFFICIENT 
	}
	
	private HornerPolynomial polynomial;
	private static final long DEGRE = 10000;
		
	// Constructor
	public PolynomialApplication (HornerPolynomial polynomial) {
			this.polynomial = polynomial;
	}
	
	/*
	 * 
	 * 1 - Générer une liste de coefficients allant de 1 à 10000 et calculer P(0), P(100) et P(9999).
	 * 2 - Générer une liste de 10000 coefficients prenant des valeurs aléatoires entre 0 et 1. Calculer P(0), P(100) et P(9999).
	 *
	 */
	public static void main(String[] args) {
		System.out.println("Starting process for polynomial application");
		
		PolynomialApplication app =  new PolynomialApplication (
				new HornerPolynomial(
						Stream.iterate(1, n -> n + 1)
							  .limit(DEGRE)
                              .collect(Collectors.toList()),
				        new Random().doubles (DEGRE, 0, 1)
				        .boxed().collect(Collectors.toList())
				    )
				); 
		final List<Integer> rangeCoefficientsList = app.getPolynomial().getRangeCoefficientsList();
		final List<Double> randomCoefficientsList = app.getPolynomial().getRandomCoefficientsList();
		app.getPolynomial().runProcess(rangeCoefficientsList, Coefficients.RANGE_COEFFICIENT);
		app.getPolynomial().runProcess(randomCoefficientsList, Coefficients.RANDOM_COEFFICIENT);
		
		System.out.println("Ending process for polynomial application");
	}
	
	

	private HornerPolynomial getPolynomial() {
		return polynomial;
	}

}
