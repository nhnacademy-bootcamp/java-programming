package com.nhnacademy;

public interface Movable {

    public Vector getMotion();

    public void setMotion(int dx, int dy);

    public void setMotion(Vector newMotion);

    public void move();

    public void move(Vector motion);

    public void moveTo(Point location);
}
