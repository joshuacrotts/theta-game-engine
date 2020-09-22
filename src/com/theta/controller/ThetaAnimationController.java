package com.theta.controller;

import java.awt.Graphics2D;

import com.theta.model.ThetaAnimation;
import com.theta.model.ThetaGameObject;

public class ThetaAnimationController {

  /* Model modified by this controller */
  private final ThetaAnimation ANIMATION;

  public ThetaAnimationController(ThetaAnimation animation) {
    this.ANIMATION = animation;
    this.ANIMATION.setCurrentFrameIndex(0);
  }

  /**
   * Increments the timer based on the delay. If the current animation timer is
   * greater than the delay, we move to the next frame of animation.
   */
  public void update() {
    if (System.nanoTime() > this.ANIMATION.getLastTime() 
        + (1_000_000_000 / this.ANIMATION.getFrameSpeed())) {
      this.ANIMATION.advanceFrame();
      this.ANIMATION.setLastTime(System.nanoTime());
    }
  }

  /**
   * Contacts the StandardAnimationView and renders the current frame referenced
   * by the model.
   *
   * @param g2
   */
  public void renderFrame(Graphics2D g2) {
    ThetaGameObject tgo = this.ANIMATION.getThetaGameObject();

    if (this.ANIMATION.isYMirrored()) {
      this.ANIMATION.getView().render(g2, tgo.getX(), tgo.getY(), -tgo.getWidth(), tgo.getHeight(),
          this.ANIMATION.getRotation());

    } else {
      this.ANIMATION.getView().render(g2, tgo.getX(), tgo.getY(), tgo.getWidth(), tgo.getHeight(),
          this.ANIMATION.getRotation());
    }
  }

  /**
   * 
   */
  public void stopAnimation() {
    this.ANIMATION.stopAnimation();
  }

  public ThetaAnimation getThetaAnimation() {
    return this.ANIMATION;
  }
}
