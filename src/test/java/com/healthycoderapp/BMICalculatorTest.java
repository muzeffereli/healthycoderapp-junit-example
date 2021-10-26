package com.healthycoderapp;

import com.healthycoderapp.BMICalculator;
import com.healthycoderapp.Coder;
import org.junit.jupiter.api.*;

import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;


class BMICalculatorTest {

    private String environment="dev";


    @BeforeAll
    static void beforeAll(){
        System.out.println("Before All Unit Tests");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("After All unit tests.");
    }

    @Nested
    class IsDietRecommendedTests{
        //@ParameterizedTest
        //@ValueSource(doubles = {89.0,95.0,110.0})
        //@ParameterizedTest(name="weight={0}, height={1}")
        //@CsvSource(value = {"89.0, 1.72","95.0,1.75","110.0, 1.78"})
        @ParameterizedTest(name="weight={0}, height={1}")
        @CsvFileSource(resources = "/diet-recommended-input-data.csv",numLinesToSkip = 1)
        void should_ReturnTrue_When_DietRecommended(Double coderWeight,Double coderHeight) {
            //Given
            double weight = coderWeight;
            double height = coderHeight;

            //When
            boolean recommended = BMICalculator.isDietRecommended(weight, height);

            //Then
            assertTrue(recommended);
        }

        @Test
        void should_ReturnFalse_When_DietNotRecommended() {
            //Given
            double weight = 50.0;
            double height = 1.92;

            //When
            boolean recommended = BMICalculator.isDietRecommended(weight, height);

            //Then
            assertFalse(recommended);
        }

        @Test
        void should_ThrowArithmeticException_When_HeightZero() {
            //given
            double weight = 50.0;
            double height = 0.0;

            //when
            Executable executable = () -> BMICalculator.isDietRecommended(weight, height);

            //Then
            assertThrows(ArithmeticException.class, executable);

        }
    }

    @Nested
    class FindCoderWithWorstBMITests{
        @Test
        void should_ReturnCoderWithWorstBMI_When_CoderListNotEmpty() {
            //given
            List<Coder> coders = new ArrayList<>();
            coders.add(new Coder(1.80, 60.0));
            coders.add(new Coder(1.82, 98.0));
            coders.add(new Coder(1.82, 64.7));


            //when
            Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);


            //Then
            assertAll(
                    () -> assertEquals(1.82, coderWorstBMI.getHeight()),
                    () -> assertEquals(98.0, coderWorstBMI.getWeight())
            );

        }

        @Test
        void should_ReturnNullWorstBMICoder_When_CoderListEmpty() {
            //given
            List<Coder> coders = new ArrayList<>();

            //when
            Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);


            //then
            assertNull(coderWorstBMI);
        }
        @Test
        void should_ReturnCoderWithWorstBMIIn1Ms_When_CoderListHas10000Elements(){
            //given
            assumeTrue(BMICalculatorTest.this.environment.equals("prod"));
            List<Coder> coders=new ArrayList<>();
            for (int i = 0; i < 10000; i++) {
                coders.add(new Coder(1.0+i,10.0+i));
            }

            //when
            Executable executable= ()-> BMICalculator.findCoderWithWorstBMI(coders);

            //then
            assertTimeout(Duration.ofMillis(500),executable);
        }

    }

    @Nested
    @DisplayName("sample inner class test")
    class GetBMIScoreTests{

        @Test
        @DisplayName("sample junit test for bmi")
        @Disabled
        void should_ReturnCorrectBMIScoreArray_When_CoderListNotEmpty(){
            //given
            List<Coder> coders = new ArrayList<>();
            coders.add(new Coder(1.80, 60.0));
            coders.add(new Coder(1.82, 98.0));
            coders.add(new Coder(1.82, 64.7));
            double[] expected={18.52,29.59,19.53};

            //when
            double[] bmiscores=BMICalculator.getBMIScores(coders);

            //then
            assertArrayEquals(expected,bmiscores);

        }

    }




}