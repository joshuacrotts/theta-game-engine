package com.theta.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;

import javax.swing.JFrame;

import com.theta.platform.ThetaGame;

/**
 * This class is the class that instantiates the Screen. It takes four
 * parameters: width, height, a title, and the Game class itself that will
 * automatically be added to the FRAME as a component due to it extending class
 * once it is instantiated.
 */
public class ThetaWindowView {
  
  /* */
  private final JFrame FRAME;
  
  /* */
  private final String TITLE;

  /* */
  private int width;
  private int height;

  public ThetaWindowView(int width, int height, String title, ThetaGame game, GraphicsConfiguration gc) {
    this.FRAME = new JFrame(title, gc);

    this.width = width;
    this.height = height;
    this.TITLE = title;

    this.FRAME.setMinimumSize(new Dimension(width, height));
    this.FRAME.setMaximumSize(new Dimension(width, height));
    this.FRAME.setPreferredSize(new Dimension(width, height));
    this.FRAME.getContentPane().setSize(new Dimension(width, height));

    this.FRAME.setResizable(false);
    this.FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.FRAME.setLocationRelativeTo(null);

    this.FRAME.add(game);
    this.FRAME.pack();
  }

  public ThetaWindowView(int width, int height, String title,  ThetaGame game) {
    this.FRAME = new JFrame(title);

    this.TITLE = title;

    // Sets dimensions.
    this.width = width;
    this.height = height;

    // Sets frame information.
    this.FRAME.setMinimumSize(new Dimension(width, height));
    this.FRAME.setMaximumSize(new Dimension(width, height));
    this.FRAME.setPreferredSize(new Dimension(width, height));
    this.FRAME.getContentPane().setSize(new Dimension(width, height));

    this.FRAME.setResizable(false);
    this.FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.FRAME.setLocationRelativeTo(null);

    this.FRAME.add(game);
    this.FRAME.pack();
  }

  /**
   * 
   * @param color
   */
  public void setBackgroundColor(Color color) {
    this.FRAME.setBackground(color);
  }

  /**
   * @return the provided width of the frame.
   */
  public int getWidth() {
    return width;
  }

  /**
   *
   * @return the provided height of the frame.
   */
  public int getHeight() {
    return height;
  }
  
  public void setVisible(boolean visible) {
    this.FRAME.setVisible(visible);
  }

  public JFrame getFrame() {
    return FRAME;
  }

  public void setWidth(short width) {
    this.width = width;
  }

  public void setHeight(short height) {
    this.height = height;
  }

  public String getTitle() {
    return TITLE;
  }

  public void setTitle(String title) {
    this.FRAME.setTitle(title);
  }
}
