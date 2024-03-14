package com.nhnacademy;

import java.awt.Color;

public class BrittleBox extends PaintableBox implements Brittle, HitInterface {
    public static final Color DEFAULT_COLOR = Color.BLACK;

    public BrittleBox(int x, int y, int width, int height) {
        super(x, y, width, height, DEFAULT_COLOR);
    }

    public BrittleBox(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

}
