package com.nhnacademy;

import java.awt.Color;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;
import java.util.List;

public class BreakBricksWorld extends MovableWorld implements MouseMotionListener, KeyListener, ComponentListener {
    static final int WALL_THICKNESS = 200;
    static final int BAR_WIDTH = 100;
    static final int BAR_THICKNESS = 20;
    static final int BAR_SPEED = 10;
    static final int MIN_HEIGHT = WALL_THICKNESS * 2 + BAR_THICKNESS;
    static final int MIN_WIDTH = WALL_THICKNESS * 2 + BAR_WIDTH;
    int blockHeight = 20;
    int blockWidth = 40;

    final Box leftWall = new PaintableBox(-WALL_THICKNESS / 2, BAR_THICKNESS / 2, WALL_THICKNESS,
            MIN_HEIGHT);
    final Box rightWall = new PaintableBox(WALL_THICKNESS / 2, BAR_THICKNESS / 2, WALL_THICKNESS, MIN_HEIGHT);
    final Box topWall = new PaintableBox(BAR_WIDTH / 2, -WALL_THICKNESS / 2, MIN_WIDTH, WALL_THICKNESS);
    final Box bottomWall = new PaintableBox(BAR_WIDTH / 2, WALL_THICKNESS / 2, MIN_WIDTH, WALL_THICKNESS);
    final BounceableBox bar;
    final List<Box> boxList = new LinkedList<>();
    final List<Ball> ballList = new LinkedList<>();

    final Color[] colors = { Color.YELLOW, Color.WHITE, Color.BLUE, Color.GREEN };

    public BreakBricksWorld() {
        super();

        setBounds(-WALL_THICKNESS, -WALL_THICKNESS, MIN_WIDTH, MIN_HEIGHT);

        add(leftWall);
        add(rightWall);
        add(topWall);
        add(bottomWall);

        bottomWall.setHitListener(other -> remove(other));

        bar = new BounceableBox(BAR_WIDTH / 2, BAR_THICKNESS / 2, BAR_WIDTH, BAR_THICKNESS, Color.BLUE);
        add(bar);

        setFocusable(true);
        addKeyListener(this);
        addMouseMotionListener(this);
        addComponentListener(this);

    }

    public void init() {
        int y = blockHeight / 2;
        for (int line = 0; line < 4; line++) {
            int x = blockWidth / 2;

            while (x + blockWidth / 2 <= getWidth()) {
                Box box = new BrittleBox(x, y, blockWidth, blockHeight, colors[line]);
                boxList.add(box);
                add(box);
                box.setHitListener(other -> remove(box));
                x += blockWidth;
            }
            y += blockHeight;
        }
    }

    public void start() {
        BounceableBall ball = new BounceableBall(bar.getX(), bar.getY() - BAR_THICKNESS / 2 - 10, 10, Color.RED);
        ball.setMotion(5, -4);

        add(ball);

    }

    @Override
    public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_LEFT) {
            bar.move(new Vector(-BAR_SPEED, 0));
            if (bar.getMinX() < 0) {
                bar.setLocation(new Point(bar.getWidth() / 2, bar.getCenterY()));
            }
        } else if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
            bar.move(new Vector(BAR_SPEED, 0));
            if (bar.getMaxX() > getWidth()) {
                bar.setLocation(new Point(getWidth() - bar.getWidth() / 2, bar.getCenterY()));
            }
        } else if (event.getKeyCode() == KeyEvent.VK_R) {
            init();
        } else if (event.getKeyCode() == KeyEvent.VK_S) {
            start();
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
        //
        logger.info("{}", event.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent event) {
        //
        logger.info("{}", event.getKeyCode());
    }

    @Override
    public void componentHidden(ComponentEvent event) {
        logger.info("Hidden");
    }

    @Override
    public void componentMoved(ComponentEvent event) {
        logger.info("Moved");
    }

    @Override
    public void componentResized(ComponentEvent event) {
        if (ballList.isEmpty() && (getWidth() > BAR_WIDTH) && (getHeight() > BAR_THICKNESS)) {
            leftWall.setBounds(new Bounds(-WALL_THICKNESS, -WALL_THICKNESS,
                    WALL_THICKNESS, WALL_THICKNESS * 2 + getHeight()));
            rightWall.setBounds(new Bounds(getWidth(), -WALL_THICKNESS,
                    WALL_THICKNESS, WALL_THICKNESS * 2 + getHeight()));
            topWall.setBounds(new Bounds(-WALL_THICKNESS, -WALL_THICKNESS,
                    getWidth() + WALL_THICKNESS * 2, WALL_THICKNESS));
            bottomWall.setBounds(new Bounds(-WALL_THICKNESS, getHeight(),
                    getWidth() + WALL_THICKNESS * 2, WALL_THICKNESS));
            bar.moveTo(new Point(getWidth() / 2, getHeight() - BAR_THICKNESS / 2));
        }

    }

    @Override
    public void componentShown(ComponentEvent event) {
        logger.info("Shown");
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        if (event.getX() > bar.getWidth() / 2 && event.getX() < getWidth() - bar.getWidth() / 2) {
            bar.setLocation(new Point(event.getX(), bar.getCenterY()));
        }
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        //
    }
}
