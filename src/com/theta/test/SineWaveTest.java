package com.theta.test;

import java.awt.Dimension;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import com.theta.platform.ThetaSwingApplication;

public class SineWaveTest extends ThetaSwingApplication {

  private JScrollPane scroll;
  private SineWavePanel panel;
  
  public SineWaveTest() {
    super(400, 400, 1, "Sine Wave");
    this.panel = new SineWavePanel("100011111000111111001000111111001000111111001000111111001000111111001000111111001000111111001100101");
    scroll = new JScrollPane(panel);
    scroll.setPreferredSize(new Dimension(800, 180));
    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

    this.addComponent(scroll);
    super.packComponents();
    //super.setVisible(true);
  }
  
  @Override
  public void run() {
    JOptionPane.showMessageDialog(this.getFrame(), this.scroll, "Sine", JOptionPane.PLAIN_MESSAGE, null);
  }

  public static void main(String[] args) {
    new SineWaveTest();
  }
}
