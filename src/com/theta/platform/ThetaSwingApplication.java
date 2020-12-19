package com.theta.platform;

import java.awt.Component;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import com.theta.input.Command;
import com.theta.input.InputDevice;
import com.theta.input.Keyboard;
import com.theta.input.Mouse;

/**
 * This is essentially a wrapper for a Swing JFrame and a Thread
 * starter, so the user doesn't have to mess with it. ThetaGraphics
 * methods such as circle, rect, etc. cannot be used with this class.
 * Those are only usable with ThetaGraphicalApplication and ThetaGame.
 */
public abstract class ThetaSwingApplication {

  /* Number of milliseconds per second. */
  private static final int SECONDS_TO_MS = 1000;
  
  /** Number of milliseconds dedicated to the command update timer. */
  private static final int CMD_TIMER_MS = 17;
  
  /* JFrame to add content to. */
  private final JFrame FRAME;
  
  /* Mouse and Keyboard objects by the command framework. */
  private final Mouse MOUSE;
  private final Keyboard KEYBOARD;
  
  /* Timer for updating the JFrame. */
  private Timer timer;
  
  /* Key timer. */
  private Timer commandTimer;

  /* Frames per second to run the timer at. */
  private int fps;

  /* Milliseconds to specify the speed of our timer. */
  private int ms;
  
  /* Time spent from the last frame to the current frame. */
  private long lastTime;
  private long dt;

  /* Boolean to set the timer to run or not. */
  private boolean isRunning = false;

  public ThetaSwingApplication(int width, int height, int fps, String title) {
    System.setProperty("sun.java2d.opengl", "true");
    
    /* Create the JFrame. */
    this.FRAME = new JFrame(title);
    this.FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.FRAME.setSize(width, height);
    this.FRAME.setResizable(false);
    this.FRAME.setLocationRelativeTo(null);

    /* Create the Mouse & Keyboard listeners and assign them
     * to the parent JFrame. */
    this.MOUSE = new Mouse();
    this.KEYBOARD = new Keyboard();
    this.FRAME.addMouseListener(this.MOUSE);
    this.FRAME.addMouseMotionListener(this.MOUSE);
    this.FRAME.addKeyListener(this.KEYBOARD);
    
    /* Sets the current FPS and the millisecond update time for the 
     * Swing timer. */
    this.fps = fps;
    this.ms = SECONDS_TO_MS / fps;

    /* Starts the timer. */
    SwingUtilities.invokeLater(() -> {
      this.start();
    });
  }

  /**
   * 
   * @param layout
   */
  public void setFrameLayout(LayoutManager layout) {
    this.FRAME.setLayout(layout);
  }

  /**
   * 
   * @param component
   */
  public void addComponent(Component component) {
    this.FRAME.getContentPane().add(component);
  }

  /**
   * 
   * @param component
   */
  public void addComponent(Component component, int index) {
    this.FRAME.getContentPane().add(component, index);
  }

  /**
   * 
   * @param component
   */
  public void addComponent(Component component, Object constraints) {
    this.FRAME.getContentPane().add(component, constraints);
  }

  /**
   * 
   * @param component
   */
  public void addComponent(Component component, Object constraints, int index) {
    this.FRAME.getContentPane().add(component, constraints, index);
  }

  /**
   * Sets the location of the frame to the center of the screen, and packs all
   * components together, updating their dimensions.
   */
  public void packComponents() {
    this.FRAME.pack();
    this.FRAME.setLocationRelativeTo(null);
  }

  /**
   * 
   */
  public abstract void run();

  /**
   * 
   */
  private synchronized void start() {
    if (this.isRunning) {
      return;
    }

    this.isRunning = true;
    this.update();
  }

  /**
   * 
   */
  private synchronized void stop() {
    this.timer.stop();
    this.FRAME.dispose();
    System.exit(0);
  }

  /**
   * 
   */
  private void update() {
    this.setupCommandTimer();
    this.setupAppTimer();
    this.commandTimer.start();
    this.timer.start();
  }
  
  /**
   * Sets up the command timer - this runs on a different interval than the render/app timer.
   */
  private void setupCommandTimer() {
    this.commandTimer = new Timer(CMD_TIMER_MS, timerListener -> {
      if (!this.isRunning) {
        this.stop();
      }
      
      /* Compute the delta time. */
      long time = System.nanoTime();
      this.dt = ((time - this.lastTime) / 1_000_000);
      this.lastTime = time;
      
      /* Now call the Command framework's update method. */
      Command.update(this.dt);
    });
  }
  
  /**
   * Sets up the application and render timer.
   */
  private void setupAppTimer() {
    this.timer = new Timer(this.ms, timerListener -> {
      this.run();
      this.FRAME.repaint();
    });
  }

// =================== GETTERS AND SETTERS ====================== //

  public void setFPS(int fps) {
    this.fps = fps;
    this.ms = SECONDS_TO_MS / fps;
    this.timer.setDelay(this.ms);
  }

  public int getFPS() {
    return this.fps;
  }

  public boolean isRunning() {
    return this.isRunning;
  }

  public void setRunning(boolean running) {
    this.isRunning = running;
  }

  public void isVisible() {
    this.FRAME.isVisible();
  }

  public void setVisible(boolean visible) {
    this.FRAME.setVisible(true);
  }

  public JFrame getFrame() {
    return this.FRAME;
  }

  public InputDevice getKeyboard() {
    return this.KEYBOARD;
  }
  
  public InputDevice getMouse() {
    return this.MOUSE;
  }
}
