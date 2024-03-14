package com.nhnacademy;

import java.awt.Color;
import java.awt.Graphics;

public class MovableBall extends PaintableBall implements Movable {
    public static final Vector DEFAULT_MOTION = new Vector(0, 0);

    final Vector motion = new Vector();
    boolean stopped = true;
    int dt = 100;
    StartedActionListener startedListener;
    MovableActionListener actionListener;

    public MovableBall(int x, int y, int radius, Color color) {
        super(x, y, radius, color);
    }

    public MovableBall(Point location, int radius, Color color) {
        this(location.getX(), location.getY(), radius, color);
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

    public void setDT(int dt) {
        this.dt = dt;
    }

    public int getDT() {
        return dt;
    }

    public void stop() {
        stopped = true;
        Thread.currentThread().interrupt();
    }

    public void move() {
        move(motion);
    }

    public void move(Vector motion) {
        Point origin = getLocation();
        origin.translate(motion);
        setLocation(origin);

        if (actionListener != null) {
            actionListener.moved();
        }
    }

    public void moveTo(Point location) {
        setLocation(location);
    }

    public void run() {
        if (startedListener != null) {
            startedListener.started();
        }

        stopped = false;
        while (!stopped) {
            move();

            try {
                Thread.sleep(dt);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public void addStartedActionListener(StartedActionListener listener) {
        startedListener = listener;
    }

    @Override
    public void addMovableActionListener(MovableActionListener listener) {
        actionListener = listener;
    }

    @Override
    public void paint(Graphics g) {
        if (Thread.currentThread().isAlive()) {
            super.paint(g);
        }
    }
}
