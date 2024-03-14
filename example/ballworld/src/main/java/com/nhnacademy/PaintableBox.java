package com.nhnacademy;

import java.awt.Color;
import java.awt.Graphics;

public class PaintableBox extends Box implements Paintable {
    public static final Color DEFAULT_COLOR = Color.BLACK;

    Color color;

    public PaintableBox(Point location, int width, int height) {
        this(location.getX(), location.getY(), width, height);
    }

    public PaintableBox(int x, int y, int width, int height) {
        this(x, y, width, height, DEFAULT_COLOR);
    }

    public PaintableBox(Point location, int width, int height, Color color) {
        this(location.getX(), location.getY(), width, height, color);
    }

    public PaintableBox(int x, int y, int width, int height, Color color) {
        super(x, y, width, height);

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
        g.fillRect(getBounds().getMinX(), getBounds().getMinY(),
                getBounds().getWidth(), getBounds().getHeight());
        g.setColor(Color.GRAY);
        g.drawRect(getBounds().getMinX(), getBounds().getMinY(),
                getBounds().getWidth(), getBounds().getHeight());

        g.setColor(originalColor);
    }
}
