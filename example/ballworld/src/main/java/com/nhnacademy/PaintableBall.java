package com.nhnacademy;

import java.awt.Color;
import java.awt.Graphics;

public class PaintableBall extends Ball {
    public static final Color DEFAULT_COLOR = Color.BLACK;

    Color color;

    public PaintableBall(Point location, int radius) {
        this(location, radius, DEFAULT_COLOR);
    }

    public PaintableBall(Point location, int radius, Color color) {
        super(location, radius);

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
        g.fillOval(getRegion().getMinX(), getRegion().getMinY(), getRegion().getWidth(), getRegion().getHeight());
        g.setColor(Color.GRAY);
        g.drawRect(getRegion().getMinX(), getRegion().getMinY(), getRegion().getWidth(),
                getRegion().getHeight());

        g.setColor(originalColor);
    }
}
