package com.theta.model;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * ThetaAudio is a defined object that has an audio clip. These objects
 * should never be directly instantiated by the programmer; they should only be
 * instantiated by the controller, via load().
 */
public final class ThetaAudio {

  /* */
  public static final int INFINITELY = Clip.LOOP_CONTINUOUSLY;
  
  /** Javax clip object holding audio information. */
  private Clip audioClip;

  /** Path and name of audiofile. */
  private String fileName;

  public ThetaAudio(String fileLocation) {
    this.load(fileLocation);
  }

  /**
   * Loads an audio track from the system into the audio buffer and prepares it
   * for play.
   *
   * If the user attempts to load in a clip when the audio buffer is full, an
   * IllegalStateException is thrown.
   *
   * @param fileName
   */
  public void load(String fileName) {
    this.fileName = fileName;

    try {
      // Find the file and load it into an Audio Input Stream
      File audioFile = new File(this.fileName);
      AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
      AudioFormat format = audioStream.getFormat();
      DataLine.Info info = new DataLine.Info(Clip.class, format);
      this.audioClip = (Clip) AudioSystem.getLine(info);

      // Open the lip and load samples from the AIS
      this.audioClip.open(audioStream);
    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
      e.printStackTrace();
    }
  }

  /**
   * Starts the clip.
   */
  public void start() {
    if (!this.audioClip.isRunning()) {
      this.audioClip.start();
    }
  }

  /**
   * Stops the current audio track.
   */
  public void stop() {
    if (this.audioClip.isRunning()) {
      this.audioClip.stop();
    }
  }

  /**
   * Loops a clip n times. If n is StandardAudio.INFINITELY, the file is looped
   * forever.
   *
   * @param n
   */
  public void loop(int n) {
    if (n < 0) {
      throw new IllegalArgumentException("Cannot loop for less than 0 times.");
    } else if (n >= 0 && n < ThetaAudio.INFINITELY) {
      this.audioClip.loop(n);
    } else {
      this.audioClip.loop(Clip.LOOP_CONTINUOUSLY);
    }
  }

  /**
   * Sets the frame position of the current audio clip to 0.
   */
  public void resetFramePosition() {
    this.audioClip.setFramePosition(0);
  }

  /**
   * Sets the frame position of the current audio clip to x.
   *
   * @param x
   */
  public void setFramePosition(int x) {
    this.audioClip.setFramePosition(x);
  }

  public boolean isPlaying() {
    return this.audioClip.isRunning();
  }

  public String getFileName() {
    return this.fileName;
  }
}
