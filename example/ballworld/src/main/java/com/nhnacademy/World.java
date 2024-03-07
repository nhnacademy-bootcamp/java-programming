package com.nhnacademy;

import java.awt.Graphics;
import java.util.List;
import java.util.LinkedList;

import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class World extends JPanel {
    List<Ball> ballList = new LinkedList<>();
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

    public void remove(Ball ball) {
        ballList.remove(ball);
    }

    @Override
    public void remove(int index) {
        ballList.remove(index);
    }

    public int getCount() {
        return ballList.size();
    }

    public Ball get(int index) {
        return ballList.get(index);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (Ball ball : ballList) {
            if (ball instanceof PaintableBall) {
                ((PaintableBall) ball).paint(g);
            }
        }
    }
}
