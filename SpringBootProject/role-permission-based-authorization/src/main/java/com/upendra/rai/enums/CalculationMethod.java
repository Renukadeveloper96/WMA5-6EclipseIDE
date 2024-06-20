package com.upendra.rai.enums;

public enum CalculationMethod {

  JAFARI("Ithna Ashari (Jafari)"),

  KARACHI("University of Islamic Sciences, Karachi"),

  ISNA("Islamic Society of North America (ISNA)"),

  MWL("Muslim World League (MWL)"),

  MAKKAH("Umm al-Qura, Makkah"),

  EGYPT("Egyptian General Authority of Survey"),

  CUSTOM("Custom"),

  TEHRAN("Institute of Geophysics, University of Tehran");

  private String displayName;

  private CalculationMethod(String displayName) {
    this.displayName = displayName;
  }

  public String getDisplayName() {
    return this.displayName;
  }
}
