package com.nhnacademy;

import java.awt.Graphics;
import java.util.List;
import java.util.LinkedList;

import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class World extends JPanel {
    List<Ball> ballList = new LinkedList<>();
    List<Box> boxList = new LinkedList<>();
    Logger logger = LogManager.getLogger(this.getClass().getSimpleName());

    /**
     *
     * @param ball
     * @throw IllegalArgumentException 공간을 벗어나거나, null인 경우, 볼간 충돌된 경우
     */
    public void add(Ball ball) {
        if (ball == null) {
            throw new IllegalArgumentException();
        }

        if ((ball.getX() - ball.getRadius() < 0)
                || (ball.getX() + ball.getRadius() > getWidth())
                || (ball.getY() - ball.getRadius() < 0)
                || (ball.getY() + ball.getRadius() > getHeight())) {
            throw new IllegalArgumentException();
        }

        for (Ball existBall : ballList) {

            if (ball.getRegion().intersects(existBall.getRegion())) {
                throw new IllegalArgumentException();
            }
        }

        if (ball instanceof BoundedBall) {
            ((BoundedBall) ball).setBounds(getBounds());
        }
        ballList.add(ball);
    }

    public void add(Box box) {
        if (box == null) {
            throw new IllegalArgumentException();
        }

        if ((box.getX() - box.getWidth() / 2 < 0)
                || (box.getX() + box.getWidth() / 2 > getWidth())
                || (box.getY() - box.getHeight() / 2 < 0)
                || (box.getY() + box.getHeight() / 2 > getHeight())) {
            throw new IllegalArgumentException();
        }

        for (Box existBox : boxList) {
            if (box.getRegion().intersects(existBox.getRegion())) {
                throw new IllegalArgumentException();
            }
        }

        // if (box instanceof BoundedBox) {
        // ((BoundedBox) box).setBounds(getBounds());
        // }
        boxList.add(box);
    }

    public void remove(Ball ball) {
        ballList.remove(ball);
    }

    public void remove(Box box) {
        boxList.remove(box);
    }

    public void removeBall(int index) {
        ballList.remove(index);
    }

    public void removeBox(int index) {
        boxList.remove(index);
    }

    public int getBallCount() {
        return ballList.size();
    }

    public int getBoxCount() {
        return boxList.size();
    }

    public Ball getBall(int index) {
        return ballList.get(index);
    }

    public Box getBox(int index) {
        return boxList.get(index);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (Ball ball : ballList) {
            if (ball instanceof Paintable) {
                ((Paintable) ball).paint(g);
            }
        }

        for (Box box : boxList) {
            if (box instanceof Paintable) {
                ((Paintable) box).paint(g);
            }
        }
    }
}
