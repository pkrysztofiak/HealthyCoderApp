package com.healthycoderapp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class BMICalculatorTest {

	@Test
	void should_ReturnTrue_When_DietRecommended() {
		//arrange
		double weight = 89.0;
		double height = 1.72;
		
		//act
		boolean recommended = BMICalculator.isDietRecommended(weight, height);
		
		//assert
		assertTrue(recommended);
	}
	
	@Test
	void should_ReturnFalse_When_DietNotRecommended() {
		//arrange
		double weight = 50.0;
		double height = 1.92;
		
		//act
		boolean recommended = BMICalculator.isDietRecommended(weight, height);
		
		//assert
		assertFalse(recommended);
	}
	
	@Test
	void should_ThrowArithmeticException_When_HeightZero() {
		//arrange
		double weight = 50.0;
		double height = 0.0;
		
		//act
		Executable executable = () -> BMICalculator.isDietRecommended(weight, height);
		
		//assert
		assertThrows(ArithmeticException.class, executable);
	}

}