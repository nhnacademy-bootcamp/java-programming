package com.nhnacademy;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Box implements Bounded, HitListener {
    final String id = UUID.randomUUID().toString();
    final Logger logger = LogManager.getLogger(this.getClass().getSimpleName());
    final Bounds bounds;
    HitListener hitListener;

    public Box(Point location, int width, int height) {
        this(location.getX(), location.getY(), width, height);
    }

    public Box(int x, int y, int width, int height) {
        if ((width <= 0) || (height <= 0)) {
            throw new IllegalArgumentException("반지름은 0보다 커야 합니다.");
        }

        if ((x + (long) width / 2 > Integer.MAX_VALUE)
                || (x - (long) width / 2 < Integer.MIN_VALUE)
                || (y + (long) height / 2 > Integer.MAX_VALUE)
                || (y - (long) height / 2 < Integer.MIN_VALUE)) {
            throw new IllegalArgumentException("볼이 정수 공간을 벗어납니다.");
        }

        bounds = new Bounds(x - width / 2, y - height / 2, width / 2 * 2, height / 2 * 2);
        logger.trace("Box created : {}, {}, {}, {}", x, y, width, height);
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
        bounds.setLocation(location.getX() - getWidth() / 2, location.getY() - getHeight() / 2);
    }

    public Bounds getBounds() {
        return new Bounds(bounds);
    }

    public void setBounds(Bounds bounds) {
        this.bounds.set(bounds);
    }

    public boolean isCollision(Bounds other) {
        return bounds.isCollision(other);
    }

    public boolean isInclude(Bounds other) {
        return bounds.isInclude(other);
    }

    @Override
    public String toString() {
        return String.format("(%d,%d, %d, %d)", getX(), getY(), getWidth(), getHeight());
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
