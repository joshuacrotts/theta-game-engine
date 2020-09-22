package com.theta.model;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.theta.controller.ThetaAnimationController;
import com.theta.view.ScreenObject;

public abstract class ThetaGameObject implements ScreenObject {

  /* */
  private ThetaAnimationController activeAnimation;

  /* */
  private Rectangle bounds;

  /* X and Y coordinate positions. */
  private double x = 0;
  private double y = 0;

  /* X and Y velocities. */
  private double velX;
  private double velY;

  //
  // Object dimensions - these can either be preset by the user,
  // or the library can automatically set these to the size of the
  // sprite the user chooses.
  //
  private int width;
  private int height;

  /*
   * States for the object; they can either be alive or dead, and they can have a
   * timer to determine when they should be removed from the handler.
   */
  private int flags = 0;
  private int idFlags = 0;

  public ThetaGameObject() {
  }

  public ThetaGameObject(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public ThetaGameObject(double x, double y, ThetaAnimationController animation) {
    this(x, y);
    this.activeAnimation = animation;
  }

  /**
   * Updates the position, state, actions, etc. of the ThetaGameObject. Any logic
   * or physics should be done within the tick() method. Do not put drawing
   * functions in here; it will mess up the physics loop.
   *
   * This method must be implemented in any subclass of TGO.
   */
  @Override
  public abstract void update();

  /**
   * Draws the ThetaGameObject to the screen however the user defines it as. Do
   * not update any game/object logic in here.
   *
   * This method must be implemented in any subclass of TGO.
   *
   * @param paramGraphics2D
   */
  @Override
  public abstract void render(Graphics2D paramGraphics2D);

  /**
   * Short-hand way of typing x += velX; y += velY.
   */
  public void updatePosition() {
    this.setX(this.getX() + this.getVelX());
    this.setY(this.getY() + this.getVelY());
  }
  
  public ThetaAnimationController getActiveAnimation() {
    return activeAnimation;
  }

  public void setActiveAnimation(ThetaAnimationController activeAnimation) {
    this.activeAnimation = activeAnimation;
  }

  public double getX() {
    return x;
  }

  public void setX(double x) {
    this.x = x;
  }

  public double getY() {
    return y;
  }

  public void setY(double y) {
    this.y = y;
  }

  public double getVelX() {
    return velX;
  }

  public void setVelX(double velX) {
    this.velX = velX;
  }

  public double getVelY() {
    return velY;
  }

  public void setVelY(double velY) {
    this.velY = velY;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public int getFlags() {
    return flags;
  }

  public void setFlags(int flags) {
    this.flags = flags;
  }

  public int getIdFlags() {
    return idFlags;
  }

  public void setIdFlags(int idFlags) {
    this.idFlags = idFlags;
  }

  public void setBounds(Rectangle bounds) {
    this.bounds = bounds;
  }

  public ThetaAnimationController getAnimationController() {
    return this.activeAnimation;
  }

  public void setAnimation(ThetaAnimationController animation) {
    this.activeAnimation = animation;
  }

  public Rectangle getBounds(int nX, int nY, int nW, int nH) {
    this.bounds = new Rectangle((int) this.x + nX, (int) this.y + nY, this.width + nW, this.height + nH);
    return this.bounds;
  }

  public Rectangle getBounds() {
    this.bounds = new Rectangle((int) this.x, (int) this.y, this.width, this.height);
    return this.bounds;
  }

  public Rectangle getLeftBounds() {
    return new Rectangle((int) this.x, (int) this.y, 1, this.height);
  }

  public Rectangle getRightBounds() {
    return new Rectangle((int) this.x + this.width, (int) this.y, 1, this.height);
  }

  public Rectangle getTopBounds() {
    return new Rectangle((int) this.x, (int) this.y, this.width, 3);
  }

  public Rectangle getBottomBounds() {
    return new Rectangle((int) this.x, (int) this.y + this.height, this.width, 1);
  }
}
