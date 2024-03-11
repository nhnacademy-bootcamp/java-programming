package com.nhnacademy;

import java.awt.Color;
import java.util.List;

public class BounceableBall extends MovableBall implements Bounceable {

    public BounceableBall(int x, int y, int radius, Color color) {
        super(x, y, radius, color);
    }

    public BounceableBall(Point location, int radius, Color color) {
        this(location.getX(), location.getY(), radius, color);
    }

    public void move(List<Bounded> boundedList) {
        super.move();

        for (Bounded bounded : boundedList) {
            if (bounded != this) {
                bounce(bounded);
            }
        }
    }

    public void bounce(Bounded other) {
        if (isCollision(other.getBounds())) {
            Bounds intersection = getBounds().intersection(other.getBounds());

            Vector newMotion = getMotion();

            if ((getBounds().getHeight() != intersection.getHeight())
                    && (other.getHeight() != intersection.getHeight())) {

                if (getMinY() < other.getMinY()) {
                    setLocation(new Point(getX(), other.getMinY() - getRadius()));
                } else {
                    setLocation(new Point(getX(), other.getMaxY() + getRadius()));
                }

                newMotion.turnDY();
            }

            if ((getBounds().getWidth() != intersection.getWidth())
                    && (other.getWidth() != intersection.getWidth())) {

                if (getMinX() < other.getMinX()) {
                    setLocation(new Point(other.getMinX() - getRadius(), getY()));
                } else {
                    setLocation(new Point(other.getMaxX() + getRadius(), getY()));
                }

                newMotion.turnDX();
            }

            if (!getMotion().equals(newMotion)) {
                setMotion(newMotion);
            }
        }

    }
}
