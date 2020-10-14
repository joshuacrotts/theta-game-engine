package com.theta.handler;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

import com.theta.model.ThetaCamera2D;
import com.theta.view.Renderable;
import com.theta.view.ScreenObject;
import com.theta.view.Updatable;

public class ThetaHandler<T extends ScreenObject> implements Renderable, Updatable {

  /* List of entities for the handler. */
  protected ArrayList<T> entities;

  /* ThetaCamera for the ThetaHandler. */
  private ThetaCamera2D thetaCamera;

  /**
   * 
   * @param stdCamera
   */
  public ThetaHandler(ThetaCamera2D thetaCamera) {
    this.entities = new ArrayList<T>();
    this.thetaCamera = thetaCamera;
  }
  
  /**
   * 
   */
  public ThetaHandler(int size) {
    this.entities = new ArrayList<T>(size);
  }

  /**
   * 
   */
  public ThetaHandler() {
    this.entities = new ArrayList<T>();
  }

  /**
   * 
   */
  public void update() {
    Iterator<T> it = this.entities.iterator();

    while (it.hasNext()) {
      T next = it.next();
      next.update();
    }
  }

  /**
   * 
   */
  public void render(Graphics2D g2) {
    Iterator<T> it = this.entities.iterator();
    while (it.hasNext()) {
      T next = it.next();
      next.render(g2);
    }
  }

  /**
   * Calls tick on the respective object.
   *
   * The purpose of this is to only update one object.
   *
   * @param obj
   */
  public static <T extends ScreenObject> void updateObject(ThetaHandler<T> obj) {
    obj.update();
  }

  /**
   * Calls tick() on the supplied handler.
   *
   * @param handler
   */
  public static <T extends ScreenObject> void updateHandler(ThetaHandler<T> handler) {
    handler.update();
  }
}
