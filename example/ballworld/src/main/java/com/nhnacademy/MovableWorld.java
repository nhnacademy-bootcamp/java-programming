package com.nhnacademy;

import java.util.LinkedList;
import java.util.List;

public class MovableWorld extends World {
    static final int DEFAULT_DT = 10;
    int moveCount;
    int maxMoveCount = 0;
    int dt = DEFAULT_DT;

    public void setDT(int dt) {
        if (dt < 0) {
            throw new IllegalArgumentException();
        }
        this.dt = dt;
    }

    public int getDT() {
        return dt;
    }

    public void reset() {
        moveCount = 0;
    }

    public void move() {
        if ((getMaxMoveCount() == 0) || (getMoveCount() < getMaxMoveCount())) {
            List<Bounded> removeList = new LinkedList<>();

            for (int i = 0; i < getCount(); i++) {
                Bounded item = get(i);
                if (item instanceof MovableBall) {
                    ((Movable) item).move();

                    if (item instanceof Bounceable) {
                        for (int j = 0; j < getCount(); j++) {
                            Bounded other = get(j);

                            if (item != other && item.isCollision(other.getBounds())) {
                                ((Bounceable) item).bounce(other);

                                if (other instanceof HitListener) {
                                    ((HitListener) other).hit(item);
                                }
                            }
                        }
                    }
                }
            }

            for (Bounded item : removeList) {
                remove(item);
            }

            moveCount++;
            repaint();
        }
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            move();
            try {
                Thread.sleep(getDT());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
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
