package com.upendra.rai.enums;

public enum TimeFormat {
  TIME_12("12 Hour Format", "hh:mm a"), TIME_24("24 Hour Format", "HH:mm"), TIME_12_NS(
      "12 Hour Format with no suffix", "hh:mm"), FLOATING("Floating Point", "");

  private String displayName;

  private String timeFormat;

  private TimeFormat(String displayName, String timeFormat) {
    this.displayName = displayName;
    this.timeFormat = timeFormat;
  }

  public String getDisplayName() {
    return this.displayName;
  }

  public String getTimeFormat() {
    return this.timeFormat;
  }
}
