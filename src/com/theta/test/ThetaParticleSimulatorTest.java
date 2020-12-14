package com.theta.test;

import java.awt.Color;

import com.theta.controller.ThetaFadeController;
import com.theta.graphic.ThetaGraphics;
import com.theta.handler.ThetaParticleHandler;
import com.theta.model.ThetaParticle;
import com.theta.platform.ThetaGraphicalApplication;

public class ThetaParticleSimulatorTest extends ThetaGraphicalApplication {
  
  /* */
  private ThetaParticleHandler particleHandler;
  
  /* */
  private ThetaFadeController sfc;
  
  public ThetaParticleSimulatorTest(int width, int height, String title) {
    super(width, height, title);
    super.setVisible(true);
    
    this.particleHandler = new ThetaParticleHandler(1000);
    this.sfc = new ThetaFadeController(Color.red, Color.blue, 0.05);
  }

  @Override
  public void update() {
    if (this.getMouse().alerted()) {
      this.particleHandler.insertParticle(
          new ThetaRotatingParticle(this.getMouse().getMouseX(), 
          this.getMouse().getMouseY()));
    }
    
    this.particleHandler.update();
    this.sfc.combine();
  }

  @Override
  public void render() {
    this.particleHandler.render(ThetaGraphics.GFXContext);
    ThetaGraphics.rect(100, 100, 50, 50, this.sfc.combine(), true, 0);
  }
  
  public static void main(String[] args) {
    ThetaGraphicalApplication theta = new ThetaParticleSimulatorTest(600, 600, "Test");
  }
}
