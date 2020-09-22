package com.theta.test;

import java.awt.Color;

import com.theta.graphic.ThetaGraphics;
import com.theta.platform.ThetaGraphicalApplication;

public class ThetaGameTest extends ThetaGraphicalApplication {
  
  private double angle = 0;
  
  public ThetaGameTest(int width, int height, String title) {
    super(width, height, title);
    super.setVisible(true);
  }

  @Override
  public void update() {
    angle+=0.02;
  }

  @Override
  public void render() {
    ThetaGraphics.text("Text", 50, 50, "", 32f, Color.white, angle);
    ThetaGraphics.rect(100, 100, 50, 50, Color.red, true, angle);
  }
  
  public static void main(String[] args) {
    ThetaGameTest theta = new ThetaGameTest(600, 480, "Test");
  }
}
