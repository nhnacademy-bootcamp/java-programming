package com.nhnacademy;

import javax.swing.JFrame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class TestEventFrame extends JFrame implements KeyListener, MouseMotionListener {

   public TestEventFrame() {

      addKeyListener(this);
      addMouseMotionListener(this);
   }

   public static void main(String[] args) {
      TestEventFrame frame = new TestEventFrame();

      frame.setSize(600, 500);

      frame.setVisible(true);
   }

   @Override
   public void keyPressed(KeyEvent arg0) {
      // TODO Auto-generated method stub
      System.out.println("key pressed : " + arg0.getKeyCode());
   }

   @Override
   public void keyReleased(KeyEvent arg0) {
      // TODO Auto-generated method stub
   }

   @Override
   public void keyTyped(KeyEvent arg0) {
      // TODO Auto-generated method stub
   }

   @Override
   public void mouseDragged(MouseEvent arg0) {
      // TODO Auto-generated method stub
      System.out.println("mouse dragged : " + arg0.getX() + "," + arg0.getY());
   }

   @Override
   public void mouseMoved(MouseEvent arg0) {
      System.out.println("mouse moved : " + arg0.getX() + "," + arg0.getY());
   }
}
