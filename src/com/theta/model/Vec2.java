package com.theta.model;

public class Vec2 implements Comparable {

  private double x;
  private double y;
  
  /**
   * Creates a Vec2 object with the specified positions.
   * @param x
   * @param y
   */
  public Vec2(double x, double y) {
    this.x = x;
    this.y = y;
  }
  
  /**
   * 
   * @param u
   * @return
   */
  public Vec2 cloneVec2(Vec2 u) {
    return new Vec2(u.x, u.y);
  }
  
  /**
   * 
   * @param u
   * @param v
   * @return
   */
  public Vec2 addVec2(Vec2 u, Vec2 v) {
    Vec2 w = new Vec2(u.x, u.y);
    w.x += v.x;
    w.y += v.y;
    return w;
  }
  
  /**
   * 
   * @param u
   * @param v
   * @return
   */
  public Vec2 subVec2(Vec2 u, Vec2 v) {
    Vec2 w = new Vec2(u.x, u.y);
    w.x -= v.x;
    w.y -= v.y;
    return w;
  }
  
  /**
   * 
   * @param u
   * @param v
   * @return
   */
  public Vec2 rotateVec2(Vec2 u, double theta) {
    Vec2 w = new Vec2(u.x, u.y);
    w.x = w.x * Math.cos(theta) - u.y * Math.sin(theta);
    w.y = w.x * Math.sin(theta) + u.y * Math.cos(theta);
    return w;
  }
  
  /**
   * 
   */
  @Override
  public boolean equals(Object obj) {
    Vec2 v = (Vec2) obj;
    
    return this.x == v.x && this.y == v.y;
  }
  
  /**
   * 
   */
  @Override
  public int compareTo(Object obj) {
    Vec2 v = (Vec2) obj;
    
    if (v.equals(this)) {
      return 0;
    } else if (this.x < v.x || this.y < v.y) {
      return -1;
    } else {
      return 1;
    }
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
}
