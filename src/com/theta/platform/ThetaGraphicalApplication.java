package com.theta.platform;

public abstract class ThetaGraphicalApplication extends ThetaGame {

  public ThetaGraphicalApplication(int width, int height, String title) {
    super(width, height, title);
    super.printFramesToConsole(false);
    super.printFramesToTitle(false);
    super.startGame();
  }
}
