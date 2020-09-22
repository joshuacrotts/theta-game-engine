package com.theta.test;

import java.awt.event.KeyEvent;

import com.theta.input.Command;
import com.theta.platform.ThetaSwingApplication;

public class ThetaSwingTest extends ThetaSwingApplication {

  /* A test command to instantiate. */
  private final UpCommand CMD_UP;
  
  public ThetaSwingTest() {
    super(1280, 720, 60, "Swing Test");
    
    this.CMD_UP = new UpCommand();
    this.CMD_UP.bind(super.getKeyboard(), KeyEvent.VK_W);
    
    super.setVisible(true);
  }
  
  @Override
  public void run() {
    
  }
  
  public static void main(String[] args) {
    new ThetaSwingTest();
  }
  
  /** 
   * Demonstrates how to use the Command framework. 
   */
  private class UpCommand extends Command {

    public UpCommand() {
    }
    
    @Override
    public void pressed(float dt) {
      System.out.println("PRESSED UP.");
    }
  }
}
