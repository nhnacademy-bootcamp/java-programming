package com.nhnacademy;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TestRational {
    @Test
    void testValidConstructor() {
        assertDoesNotThrow(() -> {
            new Rational(1, 2);
        });
    }

    @Test
    void testInvalidConstructor() {
        assertThrowsExactly(ArithmeticException.class, () -> {
            new Rational(1, 0);
        });
    }

    @ParameterizedTest
    @MethodSource("validConsturctorProvider")
    void testValidConstructor2(int numerator, int denominator) {
        assertDoesNotThrow(() -> {
            new Rational(numerator, denominator);
        });
    }

    static Stream<Arguments> validConsturctorProvider() {
        return Stream.of(
                Arguments.arguments(1, 2),
                Arguments.arguments(100, 10),
                Arguments.arguments(12, 45),
                Arguments.arguments(-10, -4));
    }

    @ParameterizedTest
    @MethodSource("irreducibleProvider")
    void testIrreducible(int numerator, int denominator, Rational target) {
        assertDoesNotThrow(() -> {
            assertEquals(target, new Rational(numerator, denominator));
        });
    }

    static Stream<Arguments> irreducibleProvider() {
        return Stream.of(
                Arguments.arguments(1, 2, new Rational(1, 2)),
                Arguments.arguments(100, 10, new Rational(10)),
                Arguments.arguments(12, 45, new Rational(4, 15)),
                Arguments.arguments(-10, -4, new Rational(5, 2)));
    }

    @Test
    void testAdd() {
        Rational r1 = new Rational(Integer.MAX_VALUE, 2);
        Rational r2 = new Rational(Integer.MAX_VALUE, 4);

        Rational result = Rational.add(r1, r2);

        long numerator = (long) r1.getNumerator() * r2.getDenominator()
                + (long) r1.getDenominator() * r2.getNumerator();
        int denominator = r1.getDenominator() * r2.getDenominator();

        System.out.println("" + result);
    }
}