package com.theta.controller;

import java.awt.Color;

import com.theta.util.ThetaUtils;

public class ThetaFadeController {

  /* */
  private final Color COLOR_ONE;
  private final Color COLOR_TWO;

  /* */
  private final double ALPHA;

  /* */
  private double time;
  
  /* */
  private boolean firstColor;

  public ThetaFadeController(Color c1, Color c2, double alpha) {
    this.time = 0.0F;
    this.firstColor = true;

    this.COLOR_ONE = c1;
    this.COLOR_TWO = c2;

    this.ALPHA = alpha;
  }

  /**
   * 
   * @return
   */
  public Color combine() {
    if (this.time <= 1.0F && this.firstColor) {
      this.time = (float) (this.time + this.ALPHA);
    } else {
      this.firstColor = false;
    }
    if (this.time >= 0.0F && !this.firstColor) {
      this.time = (float) (this.time - this.ALPHA);
    } else {
      this.firstColor = true;
    }

    int r = (int) (this.time * this.COLOR_TWO.getRed() + (1.0F - this.time) * this.COLOR_ONE.getRed());
    int g = (int) (this.time * this.COLOR_TWO.getGreen() + (1.0F - this.time) * this.COLOR_ONE.getGreen());
    int b = (int) (this.time * this.COLOR_TWO.getBlue() + (1.0F - this.time) * this.COLOR_ONE.getBlue());

    return new Color(ThetaUtils.clamp(r, 0, 0xff), ThetaUtils.clamp(g, 0, 0xff), ThetaUtils.clamp(b, 0, 0xff));
  }
}
