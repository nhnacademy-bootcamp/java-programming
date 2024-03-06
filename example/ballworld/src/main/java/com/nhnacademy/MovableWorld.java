package com.nhnacademy;

public class MovableWorld extends World {
    int moveCount;
    int maxMoveCount = 0;

    public void reset() {
        moveCount = 0;
    }

    public void move() {
        if ((getMaxMoveCount() == 0) || (getMoveCount() < getMaxMoveCount())) {
            for (int i = 0; i < getCount(); i++) {
                Ball ball = get(i);
                if (ball instanceof MovableBall) {
                    ((MovableBall) ball).move();
                }
            }

            moveCount++;
            repaint();
        }
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            move();
        }
    }

    public int getMoveCount() {
        return moveCount;
    }

    public int getMaxMoveCount() {
        return maxMoveCount;
    }

    public void setMaxMoveCount(int count) {
        if (count < 0) {
            throw new IllegalArgumentException();
        }

        maxMoveCount = count;
    }

}
