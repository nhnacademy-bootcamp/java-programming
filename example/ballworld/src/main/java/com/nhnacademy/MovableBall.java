package com.nhnacademy;

import java.awt.Color;

public class MovableBall extends PaintableBall implements Movable {
    public static final Vector DEFAULT_MOTION = new Vector(0, 0);

    final Vector motion = new Vector();
    long dt = 0;
    boolean stopped = true;
    StartedActionListener startedActionListener;
    MovedActionListener movedActionListener;
    Thread thread;

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
