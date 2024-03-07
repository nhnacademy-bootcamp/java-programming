package com.nhnacademy;

import java.awt.Color;

public class MovableBall extends PaintableBall {
    public static final int DEFAULT_DX = 0;
    public static final int DEFAULT_DY = 0;
    int dx = 0;
    int dy = 0;

    public MovableBall(int x, int y, int radius, Color color) {
        super(x, y, radius, color);
    }

    public int getDX() {
        return dx;
    }

    public int getDY() {
        return dy;
    }

    public void setDX(int dx) {
        this.dx = dx;
    }

    public void setDY(int dy) {
        this.dy = dy;
    }

    public void move() {
        moveTo(getX() + getDX(), getY() + getDY());
        logger.trace("{} : {}, {}, {}, {}", getId(), getX(), getY(), getRegion().getX(), getRegion().getY());
    }

    public void moveTo(int x, int y) {
        setX(x);
        setY(y);
    }
}
