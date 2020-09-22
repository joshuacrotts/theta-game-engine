package com.theta.util;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComplexNumber {

  public static final ComplexNumber ZERO = new ComplexNumber(0, 0);
  public static final ComplexNumber ONE = new ComplexNumber(1, 0);
  public static final int I = -1; // i^2 = -1

  private double real;
  private double imaginary;

  /**
   * 
   * @param real
   * @param imaginary
   */
  public ComplexNumber(double real, double imaginary) {
    this.real = real;
    this.imaginary = imaginary;
  }

  /**
   * 
   * @param c
   * @return
   */
  public ComplexNumber add(ComplexNumber c) {
    double real = this.real + c.real;
    double imaginary = this.imaginary + c.imaginary;

    return new ComplexNumber(real, imaginary);
  }

  /**
   * 
   * @param c
   * @return
   */
  public ComplexNumber subtract(ComplexNumber c) {
    double real = this.real - c.real;
    double imaginary = this.imaginary - c.imaginary;

    return new ComplexNumber(real, imaginary);
  }

  /**
   * 
   * @param c
   * @return
   */
  public ComplexNumber multiply(ComplexNumber c) {
    double real = ((this.real * c.real) + ((this.imaginary * c.imaginary) * I));
    double imaginary = ((this.real * c.imaginary) + (this.imaginary * c.real));

    return new ComplexNumber(real, imaginary);
  }

  /**
   * 
   * @param c
   * @return
   */
  public ComplexNumber divide(ComplexNumber c) {
    return this.multiply(c.reciprocal());
  }

  /**
   * 
   * @return
   */
  public ComplexNumber reciprocal() {
    double scale = this.real * this.real + this.imaginary * this.imaginary;
    return new ComplexNumber(this.real / scale, this.imaginary / scale);
  }

  /**
   * 
   * @param n
   * @return
   */
  public ComplexNumber pow(int n) {
    if (n < 0) {
      throw new IllegalArgumentException("Cannot compute a negative exponent!");
    } else if (n == 0) {
      return ONE;
    } else if (n == 1) {
      return this;
    }

    ComplexNumber complex = new ComplexNumber(this.real, this.imaginary);

    for (int i = 2; i <= n; i++) {
      complex = complex.multiply(this);
    }

    return complex;
  }

  /**
   * 
   * @return
   */
  public ComplexNumber conjugate() {
    return new ComplexNumber(this.real, -this.imaginary);
  }

  /**
   * 
   * @return
   */
  public ComplexNumber abs() {
    double absReal = Math.sqrt(this.real * this.real + this.imaginary * this.imaginary);
    return new ComplexNumber(absReal, 1);
  }
  
  /**
   * 
   * @param str
   * @return
   */
  public static ComplexNumber parseComplexNumber(String str) {
    Pattern pattern = Pattern.compile("(-?\\d*(.\\d+)?)\\s?([+-])\\s?(-?[\\d]*(.[\\d]*)?)i");
    Matcher matcher = null;

    // Verify that we can actually parse this string.
    try {
      matcher = pattern.matcher(str);

      if (!matcher.matches()) {
        throw new IllegalArgumentException("Cannot match this as a complex number!");
      }

    } catch (IllegalArgumentException ex) {
      Logger.getLogger(ComplexNumber.class.getName()).log(Level.SEVERE, null, ex);
      System.exit(1);
    }

    // Pull the tokens from the matcher.
    double real = Double.parseDouble(matcher.group(1));
    String sign = matcher.group(3);
    double imaginary = Double.parseDouble(matcher.group(4));

    // If we have a negative as the operator, flip the imaginary number
    // to fit a + bi.
    if (sign.equals("-")) {
      imaginary *= -1;
    }

    return new ComplexNumber(real, imaginary);
  }

  public double getReal() {
    return this.real;
  }

  public double getImaginary() {
    return this.imaginary;
  }

  /**
   * 
   */
  @Override
  public String toString() {
    return this.real + " + " + this.imaginary + "i";
  }
}
