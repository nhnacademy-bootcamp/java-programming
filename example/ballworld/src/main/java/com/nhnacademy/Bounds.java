package com.nhnacademy;

import java.awt.Rectangle;

public class Bounds {
    final Rectangle rectangle;

    public Bounds(Point location, int width, int height) {
        rectangle = new Rectangle(location.getX() - width / 2, location.getY() - height / 2);
    }

    Bounds(Rectangle rectangle) {
        this.rectangle = (Rectangle) rectangle.clone();
    }

    Rectangle getRectangle() {
        return rectangle;
    }

    public Point getLocation() {
        return new Point((int) rectangle.getCenterX(), (int) rectangle.getCenterY());
    }

    public void setLocation(Point location) {
        rectangle.setLocation(location.getX() - getWidth() / 2, location.getY() - getHeight() / 2);
    }

    public void setLocation(int x, int y) {
        rectangle.setLocation(x - getWidth() / 2, y - getHeight() / 2);
    }

    public void translate(Vector motion) {
        rectangle.translate(motion.getDX(), motion.getDY());
    }

    public int getCenterX() {
        return (int) rectangle.getCenterX();
    }

    public int getCenterY() {
        return (int) rectangle.getCenterY();
    }

    public int getMinX() {
        return (int) rectangle.getMinX();
    }

    public int getMinY() {
        return (int) rectangle.getMinY();
    }

    public int getMaxX() {
        return (int) rectangle.getMaxX();
    }

    public int getMaxY() {
        return (int) rectangle.getMaxY();
    }

    public int getWidth() {
        return (int) rectangle.getWidth();
    }

    public int getHeight() {
        return (int) rectangle.getHeight();
    }

    public boolean intersects(Bounds other) {
        return getRectangle().intersects(other.getRectangle());
    }

    public Bounds intersection(Bounds other) {
        return new Bounds(getRectangle().intersection(other.getRectangle()));
    }
}
