package com.nhnacademy;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TestBall {
    @ParameterizedTest
    @MethodSource("constructorProvider")
    void testConstructor(Point location, int radius) {
        assertDoesNotThrow(() -> {
            Ball ball = new Ball(location, radius);

            assertEquals(location, ball.getLocation());
            assertEquals(radius, ball.getRadius());
        });
    }

    static Stream<Arguments> constructorProvider() {
        return Stream.of(
                Arguments.arguments(0, 0, 1),
                Arguments.arguments(0, 0, Integer.MAX_VALUE),
                Arguments.arguments(Integer.MAX_VALUE / 2, Integer.MAX_VALUE / 2, Integer.MAX_VALUE / 2),
                Arguments.arguments(Integer.MIN_VALUE / 2, Integer.MIN_VALUE / 2, Integer.MAX_VALUE / 2));
    }

    @ParameterizedTest
    @MethodSource("illegalArgumentExceptionProvider")
    void testConstructorWidthIllegalArgumentException(int x, int y, int radius) {
        assertThrowsExactly(IllegalArgumentException.class, () -> new Ball(x, y, radius));
    }

    static Stream<Arguments> illegalArgumentExceptionProvider() {
        return Stream.of(
                Arguments.arguments(1, 2, 0),
                Arguments.arguments(1, 2, Integer.MIN_VALUE),
                Arguments.arguments(1, 2, Integer.MAX_VALUE));
    }

    @ParameterizedTest
    @MethodSource("toStringProvider")
    void testToString(int x, int y, int radius, String target) {
        assertDoesNotThrow(() -> {
            Ball ball = new Ball(x, y, radius);

            assertEquals(target, ball.toString());
        });
    }

    static Stream<Arguments> toStringProvider() {
        return Stream.of(
                Arguments.arguments(1, 1, 1, "(1,1,1)"),
                Arguments.arguments(0, 0, Integer.MAX_VALUE, String.format("(%d,%d,%d)", 0, 0, Integer.MAX_VALUE)),
                Arguments.arguments(Integer.MAX_VALUE / 2, Integer.MAX_VALUE / 2, Integer.MAX_VALUE / 2,
                        String.format("(%d,%d,%d)", Integer.MAX_VALUE / 2, Integer.MAX_VALUE / 2,
                                Integer.MAX_VALUE / 2)));
    }
}
