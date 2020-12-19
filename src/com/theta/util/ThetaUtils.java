package com.theta.util;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;

public abstract class ThetaUtils {

  /**
   * Returns a random integer between min and max.
   *
   * @param min
   * @param max
   * @return random integer
   */
  public static int randomInt(int min, int max) {
    if (min >= max) {
      throw new IllegalArgumentException(" Max must be smaller than min ");
    }
    
    return ThreadLocalRandom.current().nextInt(min, max + 1);
  }

  /**
   * Generates an integer between [min, minUpperBound) U (maxLowerBound, max)
   *
   * For instance to generate a number between -10 and 10, but no lower than -5 or
   * 5, do randBounds( -10, -5, 5, 10). Precision doesn't really matter;
   *
   * @param min
   * @param minUpperBound
   * @param maxLowerBound
   * @param max
   *
   *                      In the end, min leq x leq minUpperBound OR maxLowerBound
   *                      leq x leq max;
   * @return
   */
  public static double randomIntBounds(int min, int minUpperBound, int maxLowerBound, int max) {
    double n;

    do {
      n = ThetaUtils.randomInt(min, max);
    } while ((n < min || n > minUpperBound) && (n < maxLowerBound || n > max));

    return n;
  }

  /**
   * Returns a random double between min and max.
   *
   * @param min
   * @param max
   * @return
   */
  public static double randomDouble(double min, double max) {
    if (min >= max) {
      throw new IllegalArgumentException(" Max must be smaller than min ");
    }

    return ThreadLocalRandom.current().nextDouble(min, max + 1);
  }

  /**
   * Returns a random double between [0, 1).
   * 
   * @return
   */
  public static double randomDouble() {
    return ThreadLocalRandom.current().nextDouble();
  }

  /**
   * Generates a double between [min, minUpperBound) U (maxLowerBound, max)
   *
   * For instance to generate a number between -10 and 10, but no lower than -5 or
   * 5, do randBounds( -10, -5, 5, 10). Precision doesn't really matter;
   *
   * @param min
   * @param minUpperBound
   * @param maxLowerBound
   * @param max
   *
   *                      In the end, min leq x leq minUpperBound OR maxLowerBound
   *                      leq x leq max;
   * @return
   */
  public static double randomDoubleBounds(double min, double minUpperBound, double maxLowerBound, double max) {
    double n;

    do {
      n = ThetaUtils.randomDouble(min, max);
    } while ((n < min || n > minUpperBound) && (n < maxLowerBound || n > max));

    return n;
  }

  /**
   * Returns true if the mouse coordinates are within a specified
   * bounds/rectangle, false otherwise,
   *
   * @param mx     - mouse x coordinate
   * @param my     - mouse y coordinate
   * @param x      - x position of rectangle
   * @param y      - y position of rectangle
   * @param width  - width of rectangle
   * @param height - height of rectangle
   * @return
   */
  public static boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
    return ((mx > x) && (mx < x + width)) && ((my > y) && (my < y + height));
  }

  /**
   * Clamps num between min and max.
   *
   * @param num
   * @param min
   * @param max
   */
  public static int clamp(int num, int min, int max) {
    if (num < min) {
      return min;
    } else if (num > max) {
      return max;
    }

    return num;
  }

  /**
   * 
   * @param n
   * @param oldMin
   * @param oldMax
   * @param newMin
   * @param newMax
   * @return
   */
  public static double normalize(double n, double oldMin, double oldMax, double newMin, double newMax) {
    return (((n - oldMin) * (newMax - newMin)) / (oldMax - oldMin)) + newMin;
  }

  /**
   * Normalizes a number between 0.0 and 1.0.
   * 
   * @param n
   * @param oldMin
   * @param oldMax
   * @return
   */
  public static double normalize(double n, double oldMin, double oldMax) {
    return normalize(n, oldMin, oldMax, 0.0, 1.0);
  }

  /**
   * 
   * @param path
   * @param size
   * @return
   */
  public static Font initFont(String path, float size) {
    Font f = null;

    try {
      f = Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(size);
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      ge.registerFont(f);
    } catch (FontFormatException | IOException e) {
      e.printStackTrace();
      return null;
    }
    return f;
  }

  /**
   * 
   * @param path
   * @param size
   * @return
   */
  public static Font initFont(InputStream path, float size) {
    Font f = null;

    try {
      f = Font.createFont(Font.TRUETYPE_FONT, path).deriveFont(size);
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      ge.registerFont(f);
    } catch (FontFormatException | IOException e) {
      e.printStackTrace();
      return null;
    }
    return f;
  }

  /**
   * 
   * @param path
   * @return
   */
  public static BufferedImage loadImage(String path) {
    BufferedImage sprite = null;
    try {
      sprite = ImageIO.read(new File(path));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return sprite;
  }

  /**
   * 
   * @param path
   * @return
   */
  public static BufferedImage loadImage(InputStream path) {
    BufferedImage sprite = null;
    try {
      sprite = ImageIO.read(path);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return sprite;
  }
}
