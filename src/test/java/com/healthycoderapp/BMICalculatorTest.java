package com.healthycoderapp;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class BMICalculatorTest {
	
	@ParameterizedTest(name = "weight={0}, height={1}")
	@CsvFileSource(resources = "/diet-recommended-input-data.txt", numLinesToSkip = 1)
	void should_ReturnTrue_When_DietRecommended(Double weight, Double height) {
		
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
	
	@Test
	void should_ReturnCoderWithWorstBMI_When_CoderListNotEmpty() {
		//arrange
		List<Coder> coders = new ArrayList<>();
		coders.add(new Coder(1.80, 60.0));
		coders.add(new Coder(1.82, 98.0));
		coders.add(new Coder(1.82, 64.7));
		
		//act
		Coder coderWithWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);
		
		//assert
		assertAll(
				() -> assertEquals(1.82, coderWithWorstBMI.getHeight()),
				() -> assertEquals(98.0, coderWithWorstBMI.getWeight()));
		
	}
	
	@Test
	void should_ReturnNullWorstBMICoder_When_CoderListEmpty() {
		//arrange
		List<Coder> coders = new ArrayList<>();
		
		//act
		Coder coderWithWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);
		
		//assert
		assertNull(coderWithWorstBMI);
	}
	
	@Test
	void should_ReturnCorrectBMIScoreArray_When_CoderListNotEmpty() {
		//arrange
		List<Coder> coders = new ArrayList<>();
		coders.add(new Coder(1.80, 60.0));
		coders.add(new Coder(1.82, 98.0));
		coders.add(new Coder(1.82, 64.7));
		double[] expected = {18.52, 29.59, 19.53};
		
		//act
		double[] bmiScores = BMICalculator.getBMIScores(coders);
		
		//asssert
		assertArrayEquals(expected, bmiScores);
	}

}
