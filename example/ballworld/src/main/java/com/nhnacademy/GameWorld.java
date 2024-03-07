package com.nhnacademy;

import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class GameWorld extends MovableWorld {
    static final int WALL_THICKNESS = 200;
    final BounceableBox bar;

    public GameWorld(int width, int height) {
        super();

        setBounds(-WALL_THICKNESS, -WALL_THICKNESS, width + 2 * WALL_THICKNESS,
                height + 2 * WALL_THICKNESS);

        add(new PaintableBox(-WALL_THICKNESS / 2, height / 2,
                WALL_THICKNESS, height + 2 * WALL_THICKNESS));
        add(new PaintableBox(width / 2, -WALL_THICKNESS / 2,
                width + 2 * WALL_THICKNESS, WALL_THICKNESS));
        add(new PaintableBox(width + WALL_THICKNESS / 2, height / 2,
                WALL_THICKNESS, height + 2 * WALL_THICKNESS));
        add(new PaintableBox(width / 2, height + WALL_THICKNESS / 2,
                width + 2 * WALL_THICKNESS, WALL_THICKNESS));

        bar = new BounceableBox(width / 2, height - 20, 100, 20, Color.BLUE);
        add(bar);

        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent event) {
                logger.info(event);
                add(new BrittleBox((int) event.getPoint().getX(), (int) event.getPoint().getY(), 10, 10));
            }

            @Override
            public void mouseEntered(MouseEvent event) {
            }

            @Override
            public void mouseExited(MouseEvent event) {
            }

            @Override
            public void mousePressed(MouseEvent event) {
            }

            @Override
            public void mouseReleased(MouseEvent event) {
            }
        });

        addMouseMotionListener(new MouseMotionListener() {

            public void mouseDragged(MouseEvent event) {
                logger.info("{}", event.getX());

                bar.setLocation(new Point(event.getX(), bar.getY()));
            }

            public void mouseMoved(MouseEvent event) {
            }
        });

        addKeyListener(new KeyListener() {

            @Override
            public void keyPressed(KeyEvent event) {
                logger.info("{}", event.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent event) {
            }

            @Override
            public void keyTyped(KeyEvent event) {
            }

        });

    }

}
