package com.nhnacademy;

import java.awt.Color;

public class MovableBox extends PaintableBox implements Movable {
    public static final int DEFAULT_DX = 0;
    public static final int DEFAULT_DY = 0;

    final Vector motion = new Vector();
    long dt = 0;
    boolean stopped = true;
    StartedActionListener startedActionListener;
    MovedActionListener movedActionListener;
    Thread thread;

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

    public void stop() {
        stopped = true;
        if (thread != null) {
            thread.interrupt();
        }
    }

    public void move() {
        move(motion);
    }

    public void move(Vector motion) {
        Point origin = getLocation();
        origin.translate(motion);
        setLocation(origin);

        if (movedActionListener != null) {
            movedActionListener.action();
        }
    }

    public void moveTo(Point location) {
        setLocation(location);

        if (movedActionListener != null) {
            movedActionListener.action();
        }
    }

    @Override
    public void setDT(long dt) {
        this.dt = dt;
    }

    @Override
    public long getDT() {
        return dt;
    }

    @Override
    public void run() {
        thread = Thread.currentThread();
        stopped = false;

        if (startedActionListener != null) {
            startedActionListener.action();
        }

        while (!stopped) {
            try {
                move();

                Thread.sleep(dt);
            } catch (InterruptedException ignore) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public void addStartedActionListener(StartedActionListener listener) {
        this.startedActionListener = listener;
    }

    @Override
    public void addMovedActionListener(MovedActionListener listener) {
        this.movedActionListener = listener;
    }
}
