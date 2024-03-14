package com.nhnacademy;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
    static final int DT = 50;
    static final int BLOCK_WIDTH = 80;
    static final Color[] COLOR_TABLE = {
            Color.BLACK,
            Color.RED,
            Color.BLUE,
            Color.YELLOW
    };

    Logger logger = LogManager.getLogger();

    CannonWorld world;

    public CannonGame() {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(this);

        setLayout(null);

        world = new CannonWorld(300, 0, FRAME_WIDTH - 300, FRAME_HEIGHT - 200);
        world.setDT(DT);
        world.setBackground(Color.WHITE);
        add(world);

        JButton button = new JButton();

        button.setBounds(50, 50, 200, 100);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.start();
            }
        });

        add(button);

        JSlider windSpeed = new JSlider(-10, 10, 0);

        windSpeed.setBounds(50, 200, 200, 100);

        windSpeed.setPaintTrack(true);
        windSpeed.setPaintTicks(true);
        windSpeed.setPaintLabels(true);

        windSpeed.setMajorTickSpacing(5);
        windSpeed.setMinorTickSpacing(2);

        windSpeed.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                world.setWindSpeed(windSpeed.getValue());
            }

        });

        add(windSpeed);
    }

    public void start() {
        setVisible(true);
        setEnabled(true);

        world.run();
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
