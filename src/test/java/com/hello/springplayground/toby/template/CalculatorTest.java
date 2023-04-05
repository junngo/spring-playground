package com.hello.springplayground.toby.template;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    Calculator calculator;
    String numFilepath;

    @BeforeEach
    public void setUp() {
        this.calculator = new Calculator();
        this.numFilepath = getClass()
                .getClassLoader()
                .getResource("numbers.txt")
                .getPath();
    }

    @Test
    public void sumOfNumbers() throws IOException {
        int sum = calculator.calcSum(this.numFilepath);
        assertEquals(sum, 10);
    }

    @Test
    public void multiplyOfNumbers() throws IOException {
        assertEquals(calculator.calcMultiply(this.numFilepath), 24);
    }

    @Test
    public void concatenateStrings() throws IOException {
        assertEquals(calculator.concatenate(this.numFilepath), "1234");
    }
}