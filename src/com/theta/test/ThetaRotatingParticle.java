package com.theta.test;

import java.awt.Color;
import java.awt.Graphics2D;

import com.theta.graphic.ThetaGraphics;
import com.theta.model.ThetaParticle;
import com.theta.util.Constants;
import com.theta.util.ThetaUtils;

public class ThetaRotatingParticle extends ThetaParticle {

  /* */
  public int life;
  
  /* */
  private int deltaSize;

  /* */
  private double deltaAngle;
  
  public ThetaRotatingParticle(double x, double y) {
    super(x, y);
    this.setVelX(ThetaUtils.randomDoubleBounds(-7.0, -0.5, 0.5, 7.0));
    this.setVelY(ThetaUtils.randomDoubleBounds(-7.0, -0.5, 0.5, 7.0));
    
    this.setWidth(ThetaUtils.randomInt(50, 80));
    this.setHeight(this.getWidth());
    this.life = ThetaUtils.randomInt(100, 300);
    this.deltaAngle = ThetaUtils.randomDouble(-3.0, 3.0);
    this.deltaSize = -1;
  }
  
  @Override
  public void update() {
    if (this.life <= 0) {
      this.setFlags(Constants.DEATH_MASK);
      return;
    }
    this.life--;
    
    super.updatePosition();
    this.setAngle(this.getAngle() + this.deltaAngle);
    this.setWidth(this.getWidth() + this.deltaSize);
    this.setHeight(this.getHeight() + this.deltaSize);
  }
  
  @Override
  public void render(Graphics2D g2) {
    ThetaGraphics.rect((int) this.getX(), 
                       (int) this.getY(), 
                       (int) this.getWidth(), 
                       (int) this.getHeight(), 
                       new Color(0xf5, 0xf5, 0xdc, 128), 
                       true, this.getAngle());
  }
}
