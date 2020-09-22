package com.theta.view;

import java.awt.Graphics2D;

public interface ScreenObject extends Renderable, Updatable {

  public void update();
  
  public void render(Graphics2D g2);
}
