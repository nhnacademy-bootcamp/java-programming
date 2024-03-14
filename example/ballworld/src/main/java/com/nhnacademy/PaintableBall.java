package com.nhnacademy;

import java.awt.Color;
import java.awt.Graphics;

public class PaintableBall extends Ball implements Paintable {
    public static final Color DEFAULT_COLOR = Color.BLACK;

    Color color;

    public PaintableBall(Point location, int radius) {
        this(location, radius, DEFAULT_COLOR);
    }

    public PaintableBall(Point location, int radius, Color color) {
        this(location.getX(), location.getY(), radius, color);
    }

    public PaintableBall(int x, int y, int radius, Color color) {
        super(x, y, radius);

        if (color == null) {
            throw new IllegalArgumentException();
        }

        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    /**
     *
     * @param color
     * @throws IllegalArgumentException color는 null 허용하지 않습니다
     */
    public void setColor(Color color) {
        if (color == null) {
            throw new IllegalArgumentException();
        }

        this.color = color;
    }

    public void paint(Graphics g) {
        if (g == null) {
            throw new IllegalArgumentException();
        }

        Color originalColor = g.getColor();

        g.setColor(getColor());
        g.fillOval(getBounds().getMinX(), getBounds().getMinY(),
                getBounds().getWidth(), getBounds().getHeight());
        g.setColor(Color.GRAY);
        g.drawRect(getBounds().getMinX(), getBounds().getMinY(),
                getBounds().getWidth(), getBounds().getHeight());

        g.setColor(originalColor);
    }
}
