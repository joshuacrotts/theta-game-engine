/*
 * ===========================================================================
 * Theta Java Game Library Source Code
 * Copyright (C) 2020 Joshua Crotts & Andrew Matzureff
 * Theta is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * Theta Source Code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Theta Source Code. If not, see <http://www.gnu.org/licenses/>.
 * ===========================================================================
 */
package com.theta.model;

import java.awt.Graphics2D;

import com.theta.platform.ThetaGame;

public class ThetaCamera2D extends ThetaGameObject {

  /* Instance of ThetaGame object. */
  private final ThetaGame THETA_GAME;
  
  /* Object that this camera snaps to/follows.*/
  private ThetaGameObject SUBJECT;

  /* Creates a new ThetaCamera2D object that follows the subject. */
  public ThetaCamera2D(ThetaGame thetaGame, ThetaGameObject subject) {
    this.THETA_GAME = thetaGame;
    this.SUBJECT = subject;
  }

  /**
   * Updates the ThetaCamera2D object. The camera is centered over the subject.
   */
  public void update() {
    if (this.SUBJECT != null) {
      this.setX((this.SUBJECT.getX() + this.SUBJECT.getWidth() / 2) - this.THETA_GAME.getWidth());
      this.setY((this.SUBJECT.getY() + this.SUBJECT.getHeight() / 2) - this.THETA_GAME.getHeight());
      this.setWidth(this.THETA_GAME.getWidth());
      this.setHeight(this.THETA_GAME.getHeight());
    }
  }
  
  /**
   * Updtes the ThetaCamera2D object without directly centering the camera over
   * the object. Rather, the programmer supplies the coordinates. 
   * 
   * @param x
   * @param y
   * @param width
   * @param height
   */
  public void update(double x, double y, int width, int height) {
    this.setX(x);
    this.setY(y);
    this.setWidth(width);
    this.setHeight(height);
  }

  /**
   * 
   * @param g2
   */
  public void render(Graphics2D g2) {
  }
}
