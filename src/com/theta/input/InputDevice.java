package com.theta.input;

public abstract class InputDevice {

  /* */
  private final BitArray keys;
  private final String name;
  
  /* */
  private final int id;
  private short alert;

  public InputDevice(String name, int size) {
    keys = new BitArray(size);
    alert = 0;
    id = Command.register(this);
    this.name = name;
  }

  public boolean alerted() {
    return alert != 0;
  }

  public int alerts() {
    return alert & 0xffff;
  }

  protected void alert(int k, boolean v) {
    if (keys.get(k) != v) {
      if (v) {
        alert++;
      } else {
        alert--;
      }
      keys.set(k, v);
    }
  }

  @Override
  public String toString() {
    return "InputDevice[" + id + "]: " + (name == null ? "Unknown" : name);
  }

  public boolean get(int key) {
    if (key >= 0 && key < keys.size()) {
      return keys.get(key);
    }
    return false;
  }

  protected void set(int k, boolean v) {
    if (k >= 0 && k < keys.size()) {
      alert(k, v);
    }
  }
}
