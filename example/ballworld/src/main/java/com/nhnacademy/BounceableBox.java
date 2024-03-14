package com.nhnacademy;

import java.awt.Color;
import java.util.List;

public class BounceableBox extends MovableBox implements Bounceable {

    public BounceableBox(Point location, int width, int height, Color color) {
        this(location.getX(), location.getY(), width, height, color);
    }

    public BounceableBox(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
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
                    setLocation(new Point(getX(), other.getMinY() - getHeight() / 2));
                } else {
                    setLocation(new Point(getX(), other.getMaxY() + getHeight() / 2));
                }

                newMotion.turnDY();
            }

            if ((getBounds().getWidth() != intersection.getWidth())
                    && (other.getWidth() != intersection.getWidth())) {

                if (getMinX() < other.getMinX()) {
                    setLocation(new Point(other.getMinX() - getWidth() / 2, getY()));
                } else {
                    setLocation(new Point(other.getMaxX() + getWidth() / 2, getY()));
                }

                newMotion.turnDX();
            }

            if (!getMotion().equals(newMotion)) {
                setMotion(newMotion);
            }
        }

    }
}
