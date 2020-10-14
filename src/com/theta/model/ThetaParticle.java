package com.theta.model;

import java.awt.Graphics2D;

public abstract class ThetaParticle extends ThetaGameObject {

  public ThetaParticle(double x, double y) {
    super(x, y);
  }

  @Override
  public abstract void update();

  @Override
  public abstract void render(Graphics2D g2);
}
