package com.nhnacademy;

public interface Movable extends Runnable {

    public Vector getMotion();

    public void setMotion(int dx, int dy);

    public void setMotion(Vector newMotion);

    public void move();

    public void move(Vector motion);

    public void moveTo(Point location);

    public void setDT(long dt);

    public long getDT();

    public void stop();

    public default void addStartedActionListener(StartedActionListener listener) {

    }

    public default void addMovedActionListener(MovedActionListener listener) {

    }
}
