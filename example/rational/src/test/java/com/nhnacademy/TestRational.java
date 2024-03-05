package com.nhnacademy;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TestRational {
    static final int RANDOM_TEST_COUNT = 100;

    @ParameterizedTest
    @MethodSource("legalIntegerArgumentProvider")
    public void testConstructorWithInteger(int n) {
        assertDoesNotThrow(() -> {
            Rational r = new Rational(n);

            assertEquals(n, r.getNumerator());
            assertEquals(1, r.getDenominator());
        });
    }

    public static Stream<Arguments> legalIntegerArgumentProvider() {
        List<Arguments> argumentsList = new LinkedList<>();

        argumentsList.add(Arguments.arguments(Integer.MIN_VALUE));
        argumentsList.add(Arguments.arguments(Integer.MAX_VALUE));
        argumentsList.add(Arguments.arguments(1));
        argumentsList.add(Arguments.arguments(-1));
        argumentsList.add(Arguments.arguments(0));

        return argumentsList.stream();
    }

    @ParameterizedTest
    @MethodSource("legalFractionArgumentProvider")
    public void testConstructorWithFraction(int numerator, int denominator, int targetNumerator,
            int targetDenominator) {
        assertDoesNotThrow(() -> {
            Rational r = new Rational(numerator, denominator);

            assertEquals(targetNumerator, r.getNumerator());
            assertEquals(targetDenominator, r.getDenominator());
        });
    }

    public static Stream<Arguments> legalFractionArgumentProvider() {
        List<Arguments> argumentsList = new LinkedList<>();

        argumentsList.add(Arguments.arguments(0, Integer.MIN_VALUE, 0, 1));
        argumentsList.add(Arguments.arguments(0, Integer.MAX_VALUE, 0, 1));
        argumentsList.add(Arguments.arguments(Integer.MIN_VALUE, Integer.MIN_VALUE, 1, 1));
        argumentsList.add(Arguments.arguments(Integer.MAX_VALUE, Integer.MAX_VALUE, 1, 1));
        argumentsList.add(Arguments.arguments(Integer.MIN_VALUE, 1, Integer.MIN_VALUE, 1));
        argumentsList.add(Arguments.arguments(Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 1));
        argumentsList.add(Arguments.arguments(Integer.MAX_VALUE, -1, -1 * Integer.MAX_VALUE, 1));

        return argumentsList.stream();
    }

    @ParameterizedTest
    @MethodSource("arithmeticExceptionProvider")
    public void testCosntructorWithArithmeticExeption(int numerator, int denominator) {
        assertThrowsExactly(ArithmeticException.class, () -> new Rational(numerator, denominator));
    }

    public static Stream<Arguments> arithmeticExceptionProvider() {
        List<Arguments> argumentsList = new LinkedList<>();

        argumentsList.add(Arguments.arguments(1, 0));

        return argumentsList.stream();
    }

    @ParameterizedTest
    @MethodSource("outOfBoundsExceptionProvider")
    public void testConstructorWithOutOfBoundsException(int numerator, int denominator) {
        assertThrowsExactly(OutOfBoundsException.class, () -> new Rational(numerator, denominator));
    }

    public static Stream<Arguments> outOfBoundsExceptionProvider() {
        List<Arguments> argumentsList = new LinkedList<>();

        argumentsList.add(Arguments.arguments(1, Integer.MIN_VALUE));

        return argumentsList.stream();
    }

    @ParameterizedTest
    @MethodSource("addProvider")
    void testAdd(Rational r1, Rational r2, Rational result) {
        assertDoesNotThrow(() -> assertEquals(result, Rational.add(r1, r2)));
    }

    static Stream<Arguments> addProvider() {
        return Stream.of(
                Arguments.arguments(new Rational(0, 2), new Rational(1, 2), new Rational(1, 2)),
                Arguments.arguments(new Rational(1, 2), new Rational(-1, 2), new Rational(0, 1)),
                Arguments.arguments(new Rational(1, 2), new Rational(1, 2), new Rational(1)));
    }

    @ParameterizedTest
    @MethodSource("subtractProvider")
    void testSubtract(Rational r1, Rational r2, Rational result) {
        assertDoesNotThrow(() -> assertEquals(result, Rational.subtract(r1, r2)));
    }

    static Stream<Arguments> subtractProvider() {
        return Stream.of(
                Arguments.arguments(new Rational(0, 2), new Rational(1, 2), new Rational(-1, 2)),
                Arguments.arguments(new Rational(0, 2), new Rational(1, -2), new Rational(1, 2)),
                Arguments.arguments(new Rational(1, Integer.MAX_VALUE), new Rational(1),
                        new Rational(1 - Integer.MAX_VALUE, Integer.MAX_VALUE)),
                Arguments.arguments(new Rational(1), new Rational(1, Integer.MAX_VALUE),
                        new Rational(Integer.MAX_VALUE - 1, Integer.MAX_VALUE)),
                Arguments.arguments(new Rational(3, 2), new Rational(1, 2), new Rational(1)));
    }

    @ParameterizedTest
    @MethodSource("multiplyProvider")
    void testMultiply(Rational r1, Rational r2, Rational result) {
        assertDoesNotThrow(() -> assertEquals(result, Rational.multiply(r1, r2)));
    }

    static Stream<Arguments> multiplyProvider() {
        return Stream.of(
                Arguments.arguments(new Rational(0), new Rational(Integer.MAX_VALUE), new Rational(0)),
                Arguments.arguments(new Rational(Integer.MAX_VALUE), new Rational(0), new Rational(0)),
                Arguments.arguments(new Rational(1), new Rational(Integer.MAX_VALUE), new Rational(Integer.MAX_VALUE)),
                Arguments.arguments(new Rational(Integer.MAX_VALUE), new Rational(1), new Rational(Integer.MAX_VALUE)),
                Arguments.arguments(new Rational(-1), new Rational(Integer.MAX_VALUE),
                        new Rational(Integer.MIN_VALUE + 1)),
                Arguments.arguments(new Rational(Integer.MAX_VALUE), new Rational(-1),
                        new Rational(Integer.MIN_VALUE + 1)),
                Arguments.arguments(new Rational(1, Integer.MAX_VALUE), new Rational(Integer.MAX_VALUE),
                        new Rational(1)),
                Arguments.arguments(new Rational(-1, Integer.MAX_VALUE), new Rational(-Integer.MAX_VALUE),
                        new Rational(1)),
                // Arguments.arguments(new Rational(-1, Integer.MIN_VALUE), new
                // Rational(-Integer.MIN_VALUE), new Rational(1)),
                Arguments.arguments(new Rational(Integer.MAX_VALUE / 7 * 7), new Rational(4, 7),
                        new Rational(Integer.MAX_VALUE / 7 * 4)));
    }

    @ParameterizedTest
    @MethodSource("multiplyWithOutOfBoundsExceptionProvider")
    void testMultiplyWidthOutOfBoundsException(Rational r1, Rational r2) {
        assertThrowsExactly(OutOfBoundsException.class, () -> Rational.multiply(r1, r2));
    }

    static Stream<Arguments> multiplyWithOutOfBoundsExceptionProvider() {
        return Stream.of(
                Arguments.arguments(new Rational(1, Integer.MAX_VALUE), new Rational(1, Integer.MAX_VALUE)),
                Arguments.arguments(new Rational(1, Integer.MIN_VALUE + 1), new Rational(1, Integer.MAX_VALUE)),
                Arguments.arguments(new Rational(1, Integer.MIN_VALUE + 1), new Rational(1, Integer.MIN_VALUE + 1)),
                Arguments.arguments(new Rational(1, Integer.MAX_VALUE), new Rational(1, 2)),
                Arguments.arguments(new Rational(1, (Integer.MAX_VALUE / 2) + 1), new Rational(1, 2)));
    }

    @ParameterizedTest
    @MethodSource("divideProvider")
    void testDivide(Rational r1, Rational r2, Rational result) {
        assertDoesNotThrow(() -> assertEquals(result, Rational.divide(r1, r2)));
    }

    static Stream<Arguments> divideProvider() {
        return Stream.of(
                Arguments.arguments(new Rational(2, 3), new Rational(3, 2),
                        new Rational(4, 9)));
    }

    @ParameterizedTest
    @MethodSource("divideByZeroProvider")
    void testDivideByZero(Rational r1, Rational r2) {
        assertThrowsExactly(ArithmeticException.class, () -> Rational.divide(r1, r2));
    }

    static Stream<Arguments> divideByZeroProvider() {
        return Stream.of(
                Arguments.arguments(new Rational(0), new Rational(0)),
                Arguments.arguments(new Rational(2, 3), new Rational(0)),
                Arguments.arguments(new Rational(2, 3), new Rational(0, 2)));
    }

    @ParameterizedTest
    @MethodSource("irreducibleProvider")
    void testIrreducible(int numbrator, int denominator, int numeratorOfIrreducible, int denominatorOfIrreducible) {
        Rational r = new Rational(numbrator, denominator);

        assertEquals(numeratorOfIrreducible, r.getNumerator());
        assertEquals(denominatorOfIrreducible, r.getDenominator());
    }

    static Stream<Arguments> irreducibleProvider() {
        return Stream.of(
                Arguments.arguments(1, 2, 1, 2),
                Arguments.arguments(2, 4, 1, 2),
                Arguments.arguments(Integer.MAX_VALUE, Integer.MAX_VALUE, 1, 1),
                Arguments.arguments(Integer.MIN_VALUE, Integer.MIN_VALUE, 1, 1),
                Arguments.arguments(1, -1 * Integer.MAX_VALUE, -1, Integer.MAX_VALUE));
    }

    @ParameterizedTest
    @MethodSource("oppositeProvider")
    void testOpposite(int numbrator, int denominator, int targetNumerator, int targetDenominator) {
        Rational r = new Rational(numbrator, denominator).opposite();

        assertEquals(targetNumerator, r.getNumerator());
        assertEquals(targetDenominator, r.getDenominator());
    }

    static Stream<Arguments> oppositeProvider() {
        return Stream.of(
                Arguments.arguments(1, 2, -1, 2),
                Arguments.arguments(2, 4, -1, 2),
                Arguments.arguments(Integer.MAX_VALUE, Integer.MAX_VALUE, -1, 1),
                Arguments.arguments(Integer.MIN_VALUE, Integer.MIN_VALUE, -1, 1),
                Arguments.arguments(1, -1 * Integer.MAX_VALUE, 1, Integer.MAX_VALUE));
    }

    @ParameterizedTest
    @MethodSource("reciprocalProvider")
    void testReciprocal(int numbrator, int denominator, int targetNumerator, int targetDenominator) {
        Rational r = new Rational(numbrator, denominator).reciprocal();

        assertEquals(targetNumerator, r.getNumerator());
        assertEquals(targetDenominator, r.getDenominator());
    }

    static Stream<Arguments> reciprocalProvider() {
        return Stream.of(
                Arguments.arguments(1, 2, 2, 1),
                Arguments.arguments(2, 4, 2, 1),
                Arguments.arguments(Integer.MAX_VALUE, Integer.MAX_VALUE, 1, 1),
                Arguments.arguments(Integer.MIN_VALUE, Integer.MIN_VALUE, 1, 1),
                Arguments.arguments(1, -1 * Integer.MAX_VALUE, -1 * Integer.MAX_VALUE, 1));
    }

    @ParameterizedTest
    @MethodSource("toStringProvider")
    void testToString(int numbrator, int denominator, String output) {
        Rational r = new Rational(numbrator, denominator);

        assertEquals(output, r.toString());
    }

    static Stream<Arguments> toStringProvider() {
        List<Arguments> argumentList = new LinkedList<>();

        argumentList.add(Arguments.arguments(0, Integer.MAX_VALUE, "[0]"));
        argumentList.add(Arguments.arguments(0, Integer.MIN_VALUE, "[0]"));
        argumentList.add(Arguments.arguments(Integer.MAX_VALUE, Integer.MAX_VALUE, "[1]"));
        argumentList.add(Arguments.arguments(Integer.MIN_VALUE, Integer.MIN_VALUE, "[1]"));
        argumentList.add(Arguments.arguments(1, 2, "[1/2]"));
        argumentList.add(Arguments.arguments(-1, 2, "[-1/2]"));
        argumentList.add(Arguments.arguments(2, 4, "[1/2]"));
        argumentList.add(Arguments.arguments(2, -4, "[-1/2]"));
        argumentList.add(Arguments.arguments(-2, 4, "[-1/2]"));
        argumentList.add(Arguments.arguments(-2, -4, "[1/2]"));

        return argumentList.stream();
    }

    @Test
    void testEquality() {
        Rational r = new Rational(1);

        assertEquals(null, r);
    }
}
