package com.nhnacademy;

import java.awt.Color;

public class BoundedBall extends MovableBall {

    Bounds bounds;

    public BoundedBall(Point location, int radius, Color color) {
        super(location, radius, color);

        bounds = new Bounds(location, 2 * radius, 2 * radius);
    }

    public void setBounds(Bounds bounds) {
        this.bounds = bounds;
    }

    public Bounds getBounds() {
        return bounds;
    }

    public boolean isOutOfBounds() {
        Bounds intersection = getBounds().intersection(getRegion());

        return (intersection.getWidth() != region.getWidth()) || (intersection.getHeight() != region.getHeight());
    }

    @Override
    public void move() {
        super.move();

        if (isOutOfBounds()) {
            bounce();
        }
    }

    public void bounce() {
        if ((getX() - getRadius() < getBounds().getMinX())
                || (getX() + getRadius() > getBounds().getMaxX())) {

            setDX(-getDX());
        }

        if ((getY() - getRadius() < getBounds().getMinY())
                || (getY() + getRadius() > getBounds().getMaxY())) {
            setDY(-getDY());
        }
    }

    public void bounce(Ball other) {
        Bounds intersection = getRegion().intersection(other.getRegion());

        if (getRegion().getHeight() != intersection.getHeight()) {
            setDY(-getDY());
        }

        if (getRegion().getWidth() != intersection.getWidth()) {
            setDX(-getDX());
        }

    }
}
