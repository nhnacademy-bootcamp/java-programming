package com.nhnacademy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 유리수
 *
 * @author xtra
 */
public class Rational {
    // 분자
    int numerator;
    // 분모
    int denominator;
    Logger logger = LogManager.getLogger(Rational.class.getName());

    /**
     * 정수를 받아 생성
     *
     * @param n 정수
     */
    public Rational(int n) {
        numerator = n;
        denominator = 1;
        logger.trace("new rational : [{}/{}]", numerator, denominator);
    }

    /**
     * 분수의 분자와 분모를 받아서 생성
     *
     * @param numerator   분자(정수)
     * @param denominator 분모 (정수)
     * @throws ArithmeticException  분모가 0인 경우
     * @throws OutOfBoundsException 분모가 Integer.MAX_VALUE를 넘을 경우
     */
    public Rational(int numerator, int denominator) {
        if (denominator == 0) {
            throw new ArithmeticException();
        }

        long g = gcd(Math.abs((long) numerator), Math.abs((long) denominator));

        this.numerator = (int) (numerator / g);
        this.denominator = (int) (denominator / g);

        if (this.denominator < 0) {
            if ((this.numerator == Integer.MIN_VALUE) || (this.denominator == Integer.MIN_VALUE)) {
                throw new OutOfBoundsException();
            }

            this.numerator *= -1;
            this.denominator *= -1;
        }
    }

    /**
     * long형 분수의 분자와 분모를 받아서 생성
     * 내부 연산에서만 사용됨
     *
     * @param numerator   분자(정수)
     * @param denominator 분모 (정수)
     * @throws ArithmeticException  분모가 0인 경우
     * @throws OutOfBoundsException 분모가 Integer.MAX_VALUE를 넘을 경우
     */
    Rational(long numerator, long denominator) {
        if (denominator == 0) {
            throw new ArithmeticException();
        }

        long g = gcd(Math.abs(numerator), Math.abs(denominator));

        numerator = numerator / g;
        denominator = denominator / g;

        this.numerator = (int) numerator;
        this.denominator = (int) denominator;

        if ((this.numerator != numerator) || (this.denominator != denominator)) {
            throw new OutOfBoundsException();
        }

        if (this.denominator < 0) {
            if ((this.numerator == Integer.MIN_VALUE) || (this.denominator == Integer.MIN_VALUE)) {
                throw new OutOfBoundsException();
            }

            this.numerator *= -1;
            this.denominator *= -1;
        }
    }

    /**
     * Copy constructor
     *
     * @param other Source rational
     */
    public Rational(Rational other) {
        this.numerator = other.getNumerator();
        this.denominator = other.getDenominator();
    }

    int getNumerator() {
        return numerator;
    }

    int getDenominator() {
        return denominator;
    }

    @Override
    public String toString() {
        if (getDenominator() == 1) {
            return "[" + getNumerator() + "]";
        } else {
            return "[" + getNumerator() + "/" + getDenominator() + "]";
        }
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof Rational)
                && (getNumerator() == ((Rational) other).getNumerator())
                && (getDenominator() == ((Rational) other).getDenominator());
    }

    /**
     * 두개의 rational을 받아서 더한 후 새로운 rational 생성
     *
     * @param x rational
     * @param y rational
     * @return x + y
     */
    public static Rational add(Rational x, Rational y) {
        return new Rational(
                (long) x.getNumerator() * y.getDenominator() + (long) x.getDenominator() * y.getNumerator(),
                (long) x.getDenominator() * y.getDenominator());
    }

    public static Rational subtract(Rational x, Rational y) {
        return new Rational(
                (long) x.getNumerator() * y.getDenominator() - (long) x.getDenominator() * y.getNumerator(),
                (long) x.getDenominator() * y.getDenominator());
    }

    public static Rational multiply(Rational x, Rational y) {
        return new Rational(
                (long) x.getNumerator() * y.getNumerator(),
                (long) x.getDenominator() * y.getDenominator());
    }

    public static Rational divide(Rational x, Rational y) {
        return Rational.multiply(x, y.reciprocal());
    }

    public Rational opposite() {
        return new Rational(-getNumerator(), getDenominator());
    }

    public Rational reciprocal() {
        return new Rational(getDenominator(), getNumerator());
    }

    public Rational pow(int n) {
        return new Rational((int) Math.pow(getNumerator(), n), (int) Math.pow(getDenominator(), n));
    }

    long gcd(long x, long y) {
        if ((x < 0) || (y < 0)) {
            throw new ArithmeticException();
        }

        if (y == 0) {
            return x;
        }

        return gcd(y, x % y);
    }
}
