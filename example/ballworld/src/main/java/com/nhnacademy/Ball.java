package com.nhnacademy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Ball {
    static int getRegionCallCount = 0;
    static int count = 0;
    int id = ++count;
    Bounds region;
    Logger logger = LogManager.getLogger(this.getClass().getSimpleName());

    public Ball(Point location, int radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("반지름은 0보다 커야 합니다.");
        }

        if ((location.getX() + (long) radius > Integer.MAX_VALUE)
                || (location.getX() - (long) radius < Integer.MIN_VALUE)
                || (location.getY() + (long) radius > Integer.MAX_VALUE)
                || (location.getY() - (long) radius < Integer.MIN_VALUE)) {
            throw new IllegalArgumentException("볼이 정수 공간을 벗어납니다.");
        }

        region = new Bounds(location, 2 * radius, 2 * radius);
        logger.trace("Ball created : {}, {}", location, radius);
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return region.getCenterX();
    }

    public int getY() {
        return region.getCenterY();
    }

    void setX(int x) {
        region.setLocation(x, getY());
    }

    void setY(int y) {
        region.setLocation(getX(), y);
    }

    public int getRadius() {
        return region.getWidth() / 2;
    }

    public Bounds getRegion() {
        return region;
    }

    @Override
    public String toString() {
        return String.format("(%d,%d,%d)", getX(), getY(), getRadius());
    }
}
