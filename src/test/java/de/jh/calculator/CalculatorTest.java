package de.jh.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    private Calculator _calculator;

    @BeforeEach
    public void setUp() {
        _calculator = new Calculator(null);
    }

    @Test
    public void testAddition() {
        double result = _calculator.calculate(12.5, 7.5, "+");
        assertEquals(20.0, result, 0.0001);
    }

    @Test
    public void testSubtraction() {
        double result = _calculator.calculate(50.0, 18.5, "-");
        assertEquals(31.5, result, 0.0001);
    }

    @Test
    public void testMultiplication() {
        double result = _calculator.calculate(6.0, 7.0, "*");
        assertEquals(42.0, result, 0.0001);
    }

    @Test
    public void testDivision() {
        double result = _calculator.calculate(144.0, 12.0, "/");
        assertEquals(12.0, result, 0.0001);
    }

    @Test
    public void testDivisionByZero() {
        double result = _calculator.calculate(10.0, 0.0, "/");
        assertTrue(Double.isNaN(result), "Division durch 0 sollte Double.NaN zurückliefern");
    }

    @Test
    public void testInvalidOperator() {
        assertThrows(IllegalArgumentException.class, () -> {
            _calculator.calculate(5.0, 2.0, "%");
        });
    }

    @Test
    public void testSolutionGetterSetter() {
        _calculator.set_solution(42.0);
        assertEquals(42.0, _calculator.get_solution(), 0.0001);
    }
}
