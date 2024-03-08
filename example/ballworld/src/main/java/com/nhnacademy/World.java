package com.nhnacademy;

import java.awt.Graphics;
import java.util.List;
import java.util.LinkedList;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.util.Random;

import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class World extends JPanel implements MouseListener {
    List<Regionable> regionableList = new LinkedList<>();
    Logger logger = LogManager.getLogger(this.getClass().getSimpleName());
    Random random = new Random();

    public World() {
        super();

        addMouseListener(this);

    }

    /**
     *
     * @param object
     * @throw IllegalArgumentException 공간을 벗어나거나, null인 경우, 볼간 충돌된 경우
     */
    public void add(Regionable object) {
        if (object == null) {
            throw new IllegalArgumentException();
        }

        // if ((object.getX() - object.getRegion().getWidth() / 2 < 0)
        // || (object.getX() + object.getRegion().getWidth() / 2 > getWidth())
        // || (object.getRegion().getMinY() < 0)
        // || (object.getRegion().getMaxY() > getHeight())) {
        // throw new IllegalArgumentException();
        // }

        for (Regionable item : regionableList) {
            if (((object instanceof Bounded) || (item instanceof Bounded))
                    && (object.getRegion().intersects(item.getRegion()))) {
                throw new IllegalArgumentException();
            }
        }

        regionableList.add(object);
    }

    public void remove(Regionable object) {
        regionableList.remove(object);
    }

    @Override
    public void remove(int index) {
        regionableList.remove(index);
    }

    public int getCount() {
        return regionableList.size();
    }

    public Regionable get(int index) {
        return regionableList.get(index);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (Regionable object : regionableList) {
            if (object instanceof Paintable) {
                ((Paintable) object).paint(g);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {

        MovableBall ball = new MovableBall(arg0.getX(), arg0.getY(), 50, Color.BLUE);
        ball.setDX(-10 + random.nextInt(20));
        ball.setDY(-10 + random.nextInt(20));

        add(ball);
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        //
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        //
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        //
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        //
    }
}
