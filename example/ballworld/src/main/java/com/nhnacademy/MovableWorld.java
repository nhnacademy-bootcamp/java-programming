package com.nhnacademy;

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
            for (int i = 0; i < getCount(); i++) {
                Regionable object = get(i);
                if (object instanceof Movable) {
                    ((Movable) object).move();

                    if (object instanceof Bounded) {
                        for (int j = 0; j < getCount(); j++) {
                            Regionable other = get(j);

                            if ((object != other) && (object.getRegion().intersects(other.getRegion()))) {
                                ((Bounded) object).bounce(other);
                                logger.info("ball({})와 ball({})이 충돌하였습니다.", object.getId(), other.getId());
                            }
                        }
                    }
                }
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
