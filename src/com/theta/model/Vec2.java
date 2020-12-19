package com.theta.model;

public class Vec2 implements Comparable {

  private double x;
  private double y;

  /**
   * Creates a Vec2 object with the specified positions.
   * 
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
  public Vec2 clone() {
    return new Vec2(this.x, this.y);
  }

  /**
   * 
   * @param u
   * @param v
   * @return
   */
  public void add(Vec2 v) {
    this.x += v.x;
    this.y += v.y;
  }

  /**
   * 
   * @param u
   * @param v
   * @return
   */
  public void add(double d) {
    this.x += d;
    this.y += d;
  }

  /**
   * 
   * @param u
   * @param v
   * @return
   */
  public void sub(Vec2 v) {
    this.x -= v.x;
    this.y -= v.y;
  }

  /**
   * 
   * @param u
   * @param v
   * @return
   */
  public void sub(double d) {
    this.x -= d;
    this.y -= d;
  }

  /**
   * 
   * @param d
   */
  public void multiply(double d) {
    this.x *= d;
    this.y *= d;
  }

  /**
   * 
   * @param d
   */
  public void divide(double d) {
    this.x /= d;
    this.y /= d;
  }

  /**
   * 
   * @param v
   * @return
   */
  public double dot(Vec2 v) {
    return this.x * v.x + this.y * v.y;
  }

  /**
   * 
   */
  public void normalize() {
    double m = this.mag();
    this.divide(m);
  }

  /**
   * 
   * @return
   */
  public double mag() {
    return Math.sqrt(x * x + y * y);
  }

  /**
   * 
   * @param u
   * @param v
   * @return
   */
  public void rotateVec2(double theta) {
    this.x = this.x * Math.cos(theta) - this.y * Math.sin(theta);
    this.y = this.x * Math.sin(theta) + this.y * Math.cos(theta);
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
