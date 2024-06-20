package com.upendra.rai.enums;

public enum Category {
  ABOUT_US("About Us"), //
  APP_THEME("App Theme"), //
  APP_HEADER("App Header"), //
  APP_FOOTER("App Footer"), //
  COUNT_DOWN_DISPLAY("Count Down Display"), //
  DEVICE("Device"), //
  EMAIL("Email"), //
  FLAG("Flag"), //
  GENERAL_PAYMENT("General Payment"), //
  KIOSK_DISPLAY("Kiosk Display"), //
  PRAYER_TIME("Prayer Time"), //
  PRAYER_LABEL("Prayer Labels"), //
  PRAYER_ICON("Prayer Icons"), //
  PORTAL_THEME("Portal Theme"), //
  PAYMENT_EMAIL_RECEIPT_TEMPLATE("Payment Email Receipt Template"), //
  SOCIAL_MEDIA("Social Media"), // //
  YEAR_END_TAX_RECEIPT("Year End Tax Receipt"), //
  ;

  private String displayName;

  private Category(String displayName) {
    this.displayName = displayName;
  }

  public String getDisplayName() {
    return this.displayName;
  }
}
