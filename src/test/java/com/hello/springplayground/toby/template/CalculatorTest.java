package com.hello.springplayground.toby.template;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    public void sumOfNumbers() throws IOException {
        Calculator calculator = new Calculator();
        int sum = calculator.calcSum(getClass()
                        .getClassLoader()
                        .getResource("numbers.txt")
                        .getPath());
        assertEquals(sum, 10);
    }
}