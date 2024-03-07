package com.nhnacademy;

import java.awt.Graphics;
import java.util.LinkedList;

import javax.swing.JPanel;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class World extends JPanel {
    final List<Bounded> boundedList = new LinkedList<>();
    final Logger logger = LogManager.getLogger(this.getClass().getSimpleName());

    /**
     *
     * @param ball
     * @throw IllegalArgumentException 공간을 벗어나거나, null인 경우, 볼간 충돌된 경우
     */
    public void add(Bounded object) {
        if (object == null) {
            throw new IllegalArgumentException();
        }

        Bounds area = new Bounds(getBounds());
        if (!area.isInclude(object.getBounds())) {
            logger.error("World : {}, Object : {}", area, object.getBounds());
            throw new IllegalArgumentException();
        }

        for (Bounded item : boundedList) {
            if ((item instanceof Bounceable) && (object instanceof Bounceable)
                    && (object.isCollision(item.getBounds()))) {
                throw new IllegalArgumentException();
            }
        }

        boundedList.add(object);

    }

    public void remove(Bounded item) {
        boundedList.remove(item);
    }

    @Override
    public void remove(int index) {
        boundedList.remove(index);
    }

    public int getCount() {
        return boundedList.size();
    }

    public Bounded get(int index) {
        return boundedList.get(index);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (Bounded item : boundedList) {
            if (item instanceof Paintable) {
                ((Paintable) item).paint(g);
            }
        }
    }
}
