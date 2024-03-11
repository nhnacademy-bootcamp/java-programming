package com.nhnacademy;

public interface Bounded {
    public Bounds getBounds();

    public int getMinX();

    public int getMaxX();

    public int getCenterX();

    public int getMinY();

    public int getMaxY();

    public int getCenterY();

    public int getWidth();

    public int getHeight();

    public boolean isCollision(Bounds other);

    public boolean isInclude(Bounds other);

    public void hit(Bounded other);

    public void setHitListener(HitListener listener);
}
