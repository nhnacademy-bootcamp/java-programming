package com.nhnacademy;

import java.awt.Color;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class TestWorld {
    static final int FRAME_WIDTH = 500;
    static final int FRAME_HEIGHT = 400;
    static final int MIN_RADIUS = 10;
    static final int MAX_RADIUS = 50;
    static final int FIXED_BALL_COUNT = 0;
    static final int BOUNDED_BALL_COUNT = 5;
    static final int BOUNDED_BOX_COUNT = 5;
    static final int MIN_DELTA = 5;
    static final int MAX_DELTA = 7;
    static final int MAX_MOVE_COUNT = 0;
    static final int DT = 10;
    static final int WALL_THICKNESS = 100;
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

        MovableWorld world = new MovableWorld();
        world.setBounds(-WALL_THICKNESS, -WALL_THICKNESS,
                FRAME_WIDTH + WALL_THICKNESS * 2,
                FRAME_HEIGHT + WALL_THICKNESS * 2);

        frame.add(world);

        world.add(new PaintableBox(-WALL_THICKNESS / 2, FRAME_HEIGHT / 2,
                WALL_THICKNESS, FRAME_HEIGHT + 2 * WALL_THICKNESS));
        world.add(new PaintableBox(FRAME_WIDTH / 2, -WALL_THICKNESS / 2,
                FRAME_WIDTH + 2 * WALL_THICKNESS, WALL_THICKNESS));
        world.add(new PaintableBox(FRAME_WIDTH + WALL_THICKNESS / 2, FRAME_HEIGHT / 2,
                WALL_THICKNESS, FRAME_HEIGHT + 2 * WALL_THICKNESS));
        world.add(new PaintableBox(FRAME_WIDTH / 2, FRAME_HEIGHT + WALL_THICKNESS / 2,
                FRAME_WIDTH + 2 * WALL_THICKNESS, WALL_THICKNESS));

        Random random = new Random();

        world.add(new PaintableBox(200, 200, 100, 150));
        world.add(new PaintableBox(200, 200, 100, 150));

        while (world.getCount() < FIXED_BALL_COUNT) {
            try {
                PaintableBall ball = new PaintableBall(random.nextInt(FRAME_WIDTH),
                        random.nextInt(FRAME_HEIGHT),
                        MIN_RADIUS + random.nextInt(MAX_RADIUS - MIN_RADIUS + 1),
                        COLOR_TABLE[random.nextInt(COLOR_TABLE.length)]);

                world.add(ball);
            } catch (IllegalArgumentException ignore) {
            }
        }

        frame.setVisible(true);

        world.setMaxMoveCount(MAX_MOVE_COUNT);
        world.setDT(DT);
        world.run();
    }
}
