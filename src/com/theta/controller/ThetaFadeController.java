package com.theta.controller;

import java.awt.Color;

import com.theta.util.ThetaUtils;

public class ThetaFadeController {

  /* Colors that this FadeController swaps between. */
  private final Color COLOR_ONE;
  private final Color COLOR_TWO;

  /* The alpha speed swap between the two colors. */
  private final double ALPHA;

  /* Current time of the swap. */
  private double currentTime;
  
  /* Boolean that controls which color we're on. */
  private boolean firstColor;

  public ThetaFadeController(Color c1, Color c2, double alpha) {
    this.currentTime = 0.0F;
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
    if (this.currentTime <= 1.0F && this.firstColor) {
      this.currentTime = (float) (this.currentTime + this.ALPHA);
    } else {
      this.firstColor = false;
    }
    if (this.currentTime >= 0.0F && !this.firstColor) {
      this.currentTime = (float) (this.currentTime - this.ALPHA);
    } else {
      this.firstColor = true;
    }

    int r = (int) (this.currentTime * this.COLOR_TWO.getRed() + (1.0F - this.currentTime) * this.COLOR_ONE.getRed());
    int g = (int) (this.currentTime * this.COLOR_TWO.getGreen() + (1.0F - this.currentTime) * this.COLOR_ONE.getGreen());
    int b = (int) (this.currentTime * this.COLOR_TWO.getBlue() + (1.0F - this.currentTime) * this.COLOR_ONE.getBlue());

    return new Color(ThetaUtils.clamp(r, 0, 0xff), ThetaUtils.clamp(g, 0, 0xff), ThetaUtils.clamp(b, 0, 0xff));
  }
}
