package com.nhnacademy;

import java.awt.Color;

public class MovableBox extends PaintableBox implements Movable {
    public static final int DEFAULT_DX = 0;
    public static final int DEFAULT_DY = 0;

    final Vector motion = new Vector();

    public MovableBox(Point location, int width, int height, Color color) {
        this(location.getX(), location.getY(), width, height, color);
    }

    public MovableBox(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

    public Vector getMotion() {
        return new Vector(motion);
    }

    public void setMotion(int dx, int dy) {
        motion.set(dx, dy);
    }

    public void setMotion(Vector newMotion) {
        motion.set(newMotion);
    }

    public void move() {
        move(motion);
    }

    public void move(Vector motion) {
        Point origin = getLocation();
        origin.translate(motion);
        setLocation(origin);
    }

    public void moveTo(Point location) {
        setLocation(location);
    }
}
