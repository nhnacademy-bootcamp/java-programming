package com.nhnacademy;

public interface Movable extends Runnable {

    public Vector getMotion();

    public void setMotion(int dx, int dy);

    public void setMotion(Vector newMotion);

    public void setDT(int dt);

    public int getDT();

    public void move();

    public void move(Vector motion);

    public void moveTo(Point location);

    public default void addStartedActionListener(StartedActionListener listener) {
    }

    public default void addMovableActionListener(MovableActionListener listener) {
    }
}
