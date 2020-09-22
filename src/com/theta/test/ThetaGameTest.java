package com.theta.test;

import com.theta.platform.ThetaGraphicalApplication;

public class ThetaGameTest extends ThetaGraphicalApplication {
  
  public ThetaGameTest(int width, int height, String title) {
    super(width, height, title);
    super.setVisible(true);
  }

  @Override
  public void update() {
    
  }

  @Override
  public void render() {
    // TODO Auto-generated method stub
    
  }
  
  public static void main(String[] args) {
    ThetaGameTest theta = new ThetaGameTest(600, 480, "Test");
  }
}
