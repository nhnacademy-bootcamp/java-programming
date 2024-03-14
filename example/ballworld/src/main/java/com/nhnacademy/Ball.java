package com.nhnacademy;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Ball implements Bounded, HitListener {
    final String id = UUID.randomUUID().toString();
    final Logger logger = LogManager.getLogger(this.getClass().getSimpleName());
    final Bounds bounds;
    HitListener hitListener;

    public Ball(Point location, int radius) {
        this(location.getX(), location.getY(), radius);
    }

    public Ball(int x, int y, int radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("반지름은 0보다 커야 합니다.");
        }

        if ((x + (long) radius > Integer.MAX_VALUE)
                || (x - (long) radius < Integer.MIN_VALUE)
                || (y + (long) radius > Integer.MAX_VALUE)
                || (y - (long) radius < Integer.MIN_VALUE)) {
            throw new IllegalArgumentException("볼이 정수 공간을 벗어납니다.");
        }

        bounds = new Bounds(x - radius, y - radius, 2 * radius, 2 * radius);
        logger.trace("Ball created : {}, {}, {}", x, y, radius);
    }

    public String getId() {
        return id;
    }

    public int getX() {
        return bounds.getCenterX();
    }

    public int getMinX() {
        return bounds.getMinX();
    }

    public int getCenterX() {
        return bounds.getCenterX();
    }

    public int getMaxX() {
        return bounds.getMaxX();
    }

    public int getY() {
        return bounds.getCenterY();
    }

    public int getMinY() {
        return bounds.getMinY();
    }

    public int getCenterY() {
        return bounds.getCenterY();
    }

    public int getMaxY() {
        return bounds.getMaxY();
    }

    public int getWidth() {
        return bounds.getWidth();
    }

    public int getHeight() {
        return bounds.getHeight();
    }

    public Point getLocation() {
        return new Point(bounds.getCenterX(), bounds.getCenterY());
    }

    void setLocation(Point location) {
        bounds.setLocation(location.getX() - getRadius(), location.getY() - getRadius());
    }

    public int getRadius() {
        return bounds.getWidth() / 2;
    }

    public Bounds getBounds() {
        return new Bounds(bounds);
    }

    public boolean isCollision(Bounds other) {
        return bounds.isCollision(other);
    }

    public boolean isInclude(Bounds other) {
        return bounds.isInclude(other);
    }

    @Override
    public String toString() {
        return String.format("(%d,%d, %d)", getX(), getY(), getRadius());
    }

    @Override
    public void hit(Bounded other) {
        if (hitListener != null) {
            hitListener.hit(other);
        }
    }

    @Override
    public void setHitListener(HitListener listener) {
        this.hitListener = listener;
    }
}
