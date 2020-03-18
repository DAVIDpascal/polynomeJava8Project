package com.horner.polynome;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.horner.polynome.PolynomialApplication.Coefficients;

public class HornerPolynomial {
	
	private List<Integer> rangeCoefficientsList;
	private List<Double> randomCoefficientsList;
	private Map<Double, Double>	mapResults = new HashMap<>();
	private Number[] numberCoefficientsArray;
	
	
	protected HornerPolynomial (final List<Integer> rangeCoefficientsList, 
			final List<Double> randomCoefficientsList) {
		this.rangeCoefficientsList = rangeCoefficientsList;
		this.randomCoefficientsList = randomCoefficientsList;
	}
	
	protected void setCoefficientsList (final List<? extends Number> coefficientsList){
		Number[] numberCoefficientsArray = new Number[coefficientsList.size()];
		this.numberCoefficientsArray = coefficientsList.toArray(numberCoefficientsArray);
	}
	
	protected void computePolynomialValue (double value){
		// Convert Array to a List
		List<Number> coefficients = Arrays.stream(this.numberCoefficientsArray).collect(Collectors.toList());
		
		// Reverse Coefficients List
		Collections.reverse(coefficients);
		
		// Retrieve first value
		Double accumulator = coefficients.get(0).doubleValue();
		
		// Convert List to Array
		this.numberCoefficientsArray = coefficients.stream().toArray(Number[]::new);
	    
		// Generate new list excluding first member 
	    coefficients = IntStream
	    	      .range(1, this.numberCoefficientsArray.length)
	    	      .mapToObj(i -> this.numberCoefficientsArray[i])
	    	      .collect(Collectors.toList());
	    
	    // Loop through coefficients list 
	    for (Number coefficient : coefficients) {
            accumulator = (accumulator * value) + coefficient.doubleValue();
        }
	    
	    // Store results into a Map
		mapResults.put(value, accumulator);
	}
	
	protected void runProcess(final List<? extends Number> coefficientsList, final Coefficients coefficients) {
		initializeResults(coefficients);
		setCoefficientsList(coefficientsList);
		computePolynomialValue(0);
		computePolynomialValue(100);
		computePolynomialValue(9999);
		displayResults();
	}
	
	protected void displayResults() {
		mapResults.entrySet().stream().forEach(entry -> System.out.println("For polynomial function input : " + entry.getKey() + " , result value is : " + entry.getValue()));
		
	}
	
	protected void initializeResults(final Coefficients coefficients) {
		
		switch (coefficients) {
	        case RANGE_COEFFICIENT:
	            System.out.println("Running process for coefficients list with value range");
	            break;
	                
	        case RANDOM_COEFFICIENT:
	            System.out.println("Running process for coefficients list with random value");
	            break;
	                     
	       default:
	            break;
       }
	   mapResults.clear();
	}
	
	protected List<Integer> getRangeCoefficientsList() {
		return rangeCoefficientsList;
	}

	protected List<Double> getRandomCoefficientsList() {
		return randomCoefficientsList;
	}

	   
}
