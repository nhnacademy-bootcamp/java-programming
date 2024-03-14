package com.nhnacademy;

import java.awt.Color;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CannonGame extends JFrame implements ComponentListener {
    static final int FRAME_WIDTH = 1500;
    static final int FRAME_HEIGHT = 700;
    static final int MIN_RADIUS = 10;
    static final int MAX_RADIUS = 50;
    static final int MIN_WIDTH = 10;
    static final int MAX_WIDTH = 50;
    static final int MIN_HEIGHT = 10;
    static final int MAX_HEIGHT = 50;
    static final int FIXED_BALL_COUNT = 0;
    static final int FIXED_BOX_COUNT = 3;
    static final int BOUNDED_BALL_COUNT = 5;
    static final int MIN_DELTA = 5;
    static final int MAX_DELTA = 7;
    static final int MAX_MOVE_COUNT = 0;
    static final int DT = 10;
    static final int BLOCK_WIDTH = 80;
    static final Color[] COLOR_TABLE = {
            Color.BLACK,
            Color.RED,
            Color.BLUE,
            Color.YELLOW
    };

    Logger logger = LogManager.getLogger();

    CannonGameWorld world;

    public CannonGame() {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(this);

        world = new CannonGameWorld();

        add(world);

    }

    public void start() {
        setVisible(true);
        setEnabled(true);

        world.start();
    }

    public static void main(String[] args) {
        CannonGame frame = new CannonGame();

        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

        frame.start();
    }

    public void componentHidden(ComponentEvent event) {
        //
    }

    @Override
    public void componentMoved(ComponentEvent event) {
        //
    }

    @Override
    public void componentResized(ComponentEvent event) {
        if (getWidth() % BLOCK_WIDTH != 0) {
            setSize(getWidth() / BLOCK_WIDTH * BLOCK_WIDTH, getHeight());
        }
    }

    @Override
    public void componentShown(ComponentEvent event) {
        //
    }
}
