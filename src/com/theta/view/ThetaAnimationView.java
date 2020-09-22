/*
 * ===========================================================================
 * Standards Java Game Library Source Code
 * Copyright (C) 2017-2019 Joshua Crotts & Andrew Matzureff
 * Standards is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * Standards Source Code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Standards Source Code. If not, see <http://www.gnu.org/licenses/>.
 *
 * Standards is the long-overdue update to the everlasting Standards 2.0 library
 * Andrew Matzureff and I created two years ago. I am including it in this project
 * to simplify the rendering and logic pipeline, but with a focus on the MVC
 * paradigm.
 * ===========================================================================
 */
package com.theta.view;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import com.theta.model.ThetaAnimation;
import com.theta.model.ThetaGameObject;

public class ThetaAnimationView implements Renderable {

  /* */
  private final BufferedImage[] animationFrames;

  /* */
  private final ThetaAnimation obj;

  public ThetaAnimationView(BufferedImage[] frames, ThetaAnimation obj) {
    this.animationFrames = frames;
    this.obj = obj;
  }

  @Override
  public void render(Graphics2D g2) {
    BufferedImage currentFrame = this.animationFrames[this.obj.getCurrentFrameIndex()];
    ThetaGameObject tgo = this.obj.getThetaGameObject();
    
    g2.drawImage(currentFrame, (int) tgo.getX(), (int) tgo.getY(), 
        currentFrame.getWidth(),
        currentFrame.getHeight(), null);
  }

  /**
   * Renders the current frame at the parent's x and y position. Theta is a
   * rotation factor in degrees.
   *
   * @param g2
   * @param theta
   */
  public void render(Graphics2D g2, double theta) {
    this.render(g2, 
        this.obj.getThetaGameObject().getX(), 
        this.obj.getThetaGameObject().getY(), theta);
  }

  /**
   * Renders the current frame at a specific x and y location other than the
   * parent's x and y. Theta is a rotation factor in degrees.
   *
   * @param g2
   * @param x
   * @param y
   * @param theta
   */
  public void render(Graphics2D g2, double x, double y, double theta) {
    this.render(g2, x, y, 
        this.obj.getThetaGameObject().getWidth(), 
        this.obj.getThetaGameObject().getHeight(), theta);
  }

  /**
   * 
   * @param g2
   * @param x
   * @param y
   * @param width
   * @param height
   * @param theta
   */
  public void render(Graphics2D g2, double x, double y, double width, double height, double theta) {
    AffineTransform backup = g2.getTransform();
    AffineTransform transform = new AffineTransform();
    
    BufferedImage currentFrame = this.animationFrames[this.obj.getCurrentFrameIndex()];
    ThetaGameObject tgo = this.obj.getThetaGameObject();
    
    transform.rotate(theta, (x + currentFrame.getWidth() / 2.0f), (y + currentFrame.getHeight() / 2.0f));

    g2.transform(transform);
    g2.drawImage(currentFrame, (int) x, (int) y, (int) width, (int) height, null);
    g2.setTransform(backup);
  }

  public BufferedImage[] getFrames() {
    return this.animationFrames;
  }
}
