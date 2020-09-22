package com.theta.view;

import java.awt.Graphics2D;

/**
 * Renderable is the interface designating which objects can render information
 * to the screen, such as text, sprites, geometry, etc.
 */
public interface Renderable {

  public void render(Graphics2D g2);
}
