package com.upendra.rai.enums;

public enum JuristicMethod {

  SHAFII("Shafii (standard)", 0),

  HANAFI("Hanafi", 1);

  private String displayName;
  private int step;

  private JuristicMethod(String displayName, int step) {
    this.displayName = displayName;
    this.step = step;
  }

  public String getDisplayName() {
    return this.displayName;
  }

  public int getStep() {
    return this.step;
  }
}
