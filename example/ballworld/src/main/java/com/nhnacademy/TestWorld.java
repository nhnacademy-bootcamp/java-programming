package com.nhnacademy;

import java.awt.Color;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class TestWorld {
    static final int FRAME_WIDTH = 500;
    static final int FRAME_HEIGHT = 400;
    static final int MIN_RADIUS = 20;
    static final int MAX_RADIUS = 50;
    static final int BALL_COUNT = 10;
    static final Color[] COLOR_TABLE = {
            Color.BLACK,
            Color.RED,
            Color.BLUE,
            Color.YELLOW
    };

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        World world = new World();
        world.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.add(world);

        Random random = new Random();

        while (world.getCount() < BALL_COUNT) {
            try {
                world.add(new MovableBall(random.nextInt(FRAME_WIDTH), random.nextInt(FRAME_HEIGHT),
                        MIN_RADIUS + random.nextInt(MAX_RADIUS - MIN_RADIUS + 1),
                        COLOR_TABLE[random.nextInt(COLOR_TABLE.length)]));
            } catch (IllegalArgumentException ignore) {
                //
            }
        }

        frame.setVisible(true);
    }
}
