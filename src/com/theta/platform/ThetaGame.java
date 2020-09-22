package com.theta.platform;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import com.theta.graphic.ThetaGraphics;
import com.theta.input.Command;
import com.theta.input.Keyboard;
import com.theta.input.Mouse;
import com.theta.view.ThetaWindowView;

/**
 * 
 */
public abstract class ThetaGame extends Canvas implements Runnable {

  /* Default screen size */
  private static final Dimension SCREEN_DIMENSION = Toolkit.getDefaultToolkit().getScreenSize();

  /* Window for the game (encapsulates a JFrame). */
  private ThetaWindowView window;
  
  /* BufferStrategy for double-buffering the JFrame. */
  private BufferStrategy bufferStrategy;

  /* Game loop thread. */
  private Thread thread;
  
  /* Input devices. */
  private Keyboard keyboard;
  private Mouse mouse;

  /* Debugging variables. */
  private int currentFPS;
  private boolean isRunning;
  private boolean showConsoleFPS;
  private boolean showTitleFPS;

  /**
   * 
   * @param width
   * @param height
   * @param title
   */
  public ThetaGame(int width, int height, String title) {
    System.setProperty("sun.java2d.opengl", "true");
    this.thread = null;
    this.isRunning = false;
    this.currentFPS = 0;
    this.showConsoleFPS = true;
    this.showTitleFPS = true;
    this.window = new ThetaWindowView(width, height, title, this);

    this.createBufferStrategy(3);

    this.bufferStrategy = this.getBufferStrategy();

    this.mouse = new Mouse();
    this.keyboard = new Keyboard();

    this.addMouseListener(this.mouse);
    this.addMouseMotionListener(this.mouse);
    this.addKeyListener(this.keyboard);
    
    this.setVisible(true);
  }

  /**
   * 
   * @param width
   * @param title
   */
  public ThetaGame(int width, String title) {
    this(width, width / 16 * 9, title);
  }

  /**
   * 
   * @param title
   */
  public ThetaGame(String title) {
    this(ThetaGame.getScreenWidth(), ThetaGame.getScreenHeight(), title);
  }

  /**
   * 
   */
  public void startGame() {
    if (this.isRunning) {
      return;
    }
    
    this.thread = new Thread(this);
    this.thread.start();
    this.isRunning = true;
  }

  /**
   * Halts the thread, stops the game.
   */
  public void stopGame() {
    if (!this.isRunning) {
      return;
    }
    
    try {
      this.thread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    
    this.isRunning = false;
    System.exit(0);
  }

  /**
   * The game loop.
   */
  @Override
  public void run() {
    super.requestFocus();
    long lastTime = System.nanoTime();
    double ns = 1.6666666666666666E7D;
    double delta = 0.0D;
    long timer = System.currentTimeMillis();
    int frames = 0;
    int updates = 0;
    while (this.isRunning) {
      boolean renderable = false;

      long now = System.nanoTime();
      delta += (now - lastTime) / ns;
      lastTime = now;

      while (delta >= 1.0D) {
        Command.update((float) delta);

        this.update();

        delta--;
        updates++;

        renderable = true;
      }

      if (renderable) {
        frames++;
        this.bufferStrategy = getBufferStrategy();
        ThetaGraphics.GFXContext = (Graphics2D) this.bufferStrategy.getDrawGraphics();

        ThetaGraphics.GFXContext.setColor(Color.BLACK);
        ThetaGraphics.GFXContext.fillRect(0, 0, this.getGameWidth(), this.getGameHeight());

        this.render();

        ThetaGraphics.GFXContext.dispose();
        this.bufferStrategy.show();
      }

      if (System.currentTimeMillis() - timer > 1000L) {
        timer += 1000L;

        this.currentFPS = frames;

        if (this.showTitleFPS) {
          this.window.setTitle(String.valueOf(this.window.getTitle()) + " | " + updates + " ups, " + frames + " fps");
        }
        if (this.showConsoleFPS) {
          System.out.println(String.valueOf(this.window.getTitle()) + " | " + updates + " ups, " + frames + " fps");
        }

        updates = 0;
        frames = 0;
      }
    }

    this.stopGame();
  }

  /**
   * Handles all physics/game state/object state updates.
   */
  public abstract void update();

  /**
   * Renders graphics, text, sprites, etc. to the ThetaWindowView. To use this,
   * call ThetaGraphics.GFXContext to reference the G2D object.
   */
  public abstract void render();

  public ThetaGame getGame() {
    return this;
  }

  public int getFPS() {
    return this.currentFPS;
  }

  public int getGameWidth() {
    return this.window.getWidth();
  }

  public int getGameHeight() {
    return this.window.getHeight();
  }

  public void printFramesToConsole(boolean print) {
    this.showConsoleFPS = print;
  }

  public void printFramesToTitle(boolean print) {
    this.showTitleFPS = print;
  }

  public Keyboard getKeyboard() {
    return this.keyboard;
  }

  public void setKeyboard(Keyboard keyboard) {
    this.keyboard = keyboard;
  }

  public Mouse getMouse() {
    return this.mouse;
  }

  public void setMouse(Mouse mouse) {
    this.mouse = mouse;
  }
  
  @Override
  public void setVisible(boolean visible) {
    super.setVisible(visible);
    this.window.setVisible(visible);
  }

  public static int getScreenWidth() {
    return (int) ThetaGame.SCREEN_DIMENSION.getWidth();
  }

  public static int getScreenHeight() {
    return (int) ThetaGame.SCREEN_DIMENSION.getHeight();
  }
}
