package com.upendra.rai.enums;

public enum HigherLatitudesMethod {

  NONE("No Adjustment"),

  MID_NIGHT("Middle of the night"),

  ONE_SEVENTH("1/7th of the night"),

  ANGLE_BASED("Angle/60th of the night");

  private String displayName;

  private HigherLatitudesMethod(String displayName) {
    this.displayName = displayName;
  }

  public String getDisplayName() {
    return this.displayName;
  }
}
