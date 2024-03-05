package com.nhnacademy;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FrameDemo01 {
    public static class MyCanvas extends JPanel {
        public static void drawBox(Graphics g, int x, int y, int width, int height) {
            if ((width > 5) && (height > 5)) {
                g.drawRect(x, y, width, height);

                MyCanvas.drawBox(g, x, y, width / 3, height / 3);
                MyCanvas.drawBox(g, x, y + height - height / 3, width / 3, height / 3);
                MyCanvas.drawBox(g, x + width - width / 3, y, width / 3, height / 3);
                MyCanvas.drawBox(g, x + width - width / 3, y + height - height / 3, width / 3, height / 3);
            }
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            System.out.println("paint mycanvas : " + getWidth() + ", " + getHeight());
            g.setColor(Color.RED);
            MyCanvas.drawBox(g, 100, 100, 400, 400);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Demo01");

        frame.setSize(500, 500);

        System.out.println(frame.getInsets().top + "," + frame.getInsets().left);

        MyCanvas panel = new MyCanvas();
        frame.add(panel);
        frame.setVisible(true);
    }
}
