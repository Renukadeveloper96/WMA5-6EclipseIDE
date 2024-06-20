package com.upendra.rai.entities;

import java.time.LocalDateTime;

import com.upendra.rai.enums.CalculationMethod;
import com.upendra.rai.enums.HigherLatitudesMethod;
import com.upendra.rai.enums.JuristicMethod;
import com.upendra.rai.enums.TimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "default_setting")
@Getter
@Setter
@ToString(callSuper = true)
@Deprecated(forRemoval = true, since = "2.0")
public class DefaultSetting {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private Long id;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @Column(name = "payment_success_message", length = 200)
  private String paymentSuccessMessage;

  @Lob
  @Column(name = "payment_success_email_template")
  private String paymentSuccessEmailTemplate;

  @Lob
  @Column(name = "payment_success_email_message1")
  private String paymentSuccessEmailMessage1;

  @Lob
  @Column(name = "payment_success_email_message2")
  private String paymentSuccessEmailMessage2;

  @Column(name = "payment_success_email_end_greeting", length = 50)
  private String paymentSuccessEmailEndGreeting;

  @Column(name = "payment_success_email_from_name", length = 50)
  private String paymentSuccessEmailFromName;

  @Column(name = "payment_failure_message", length = 200)
  private String paymentFailureMessage;

  @Column(name = "payment_failure_email_template", length = 10000)
  private String paymentFailureEmailTemplate;

  @Column(name = "kiosk_settings_password", length = 200)
  private String kioskSettingsPassword;

  @Column(name = "kiosk_button1_display")
  private Boolean kioskButton1Display;

  @Column(name = "kiosk_button1_text", length = 100)
  private String kioskButton1Text;

  @Column(name = "kiosk_button1_url", length = 200)
  private String kioskButton1Url;

  @Column(name = "kiosk_button2_display")
  private Boolean kioskButton2Display;

  @Column(name = "kiosk_button2_text", length = 100)
  private String kioskButton2Text;

  @Column(name = "kiosk_button2_url", length = 200)
  private String kioskButton2Url;

  @Column(name = "kiosk_button3_display")
  private Boolean kioskButton3Display;

  @Column(name = "kiosk_button3_text", length = 100)
  private String kioskButton3Text;

  @Column(name = "kiosk_button3_url", length = 200)
  private String kioskButton3Url;

  @Column(name = "kiosk_default_bg_color", length = 25)
  private String kioskDefaultBgColor;

  @Column(name = "kiosk_default_text_color", length = 25)
  private String kioskDefaultTextColor;

  @Column(name = "kiosk_default_secondary_text_color", length = 25)
  private String kioskDefaultSecondaryTextColor;

  @Column(name = "currency", length = 3)
  private String currency;

  @Enumerated(EnumType.STRING)
  @Column(name = "prayer_time_format")
  private TimeFormat prayerTimeFormat;

  @Enumerated(EnumType.STRING)
  @Column(name = "calculation_method")
  private CalculationMethod calculationMethod;

  @Enumerated(EnumType.STRING)
  @Column(name = "juristic_method")
  private JuristicMethod juristicMethod;

  @Enumerated(EnumType.STRING)
  @Column(name = "higher_latitudes_method")
  private HigherLatitudesMethod higherLatitudesMethod;

  @Column(name = "fajr_label", length = 15)
  private String fajrLabel;

  @Column(name = "dhuhr_label", length = 15)
  private String dhuhrLabel;

  @Column(name = "asr_label", length = 15)
  private String asrLabel;

  @Column(name = "maghrib_label", length = 15)
  private String maghribLabel;

  @Column(name = "isha_label", length = 15)
  private String ishaLabel;

  @Column(name = "iqamah_label", length = 15)
  private String iqamahLabel;

  @Column(name = "adhaan_label", length = 15)
  private String adhaanLabel;

  @Column(name = "sunrise_label", length = 15)
  private String sunriseLabel;

  @Column(name = "juma_label", length = 15)
  private String jumaLabel;

  @Column(name = "sahoor_label", length = 15)
  private String sahoorLabel;

  @Column(name = "iftar_label", length = 15)
  private String iftarLabel;

  @Column(name = "taraweeh_label", length = 15)
  private String taraweehLabel;

  @Column(name = "hijri_date_offset")
  private Integer hijriDateOffset;

  @Column(name = "show_hijri_date")
  private Boolean showHijriDate;

  @Column(name = "social_facebook_url", length = 200)
  private String socialFacebookUrl;

  @Column(name = "social_twitter_url", length = 200)
  private String socialTwitterUrl;

  @Column(name = "social_google_plus_url", length = 200)
  private String socialGooglePlusUrl;

  @Column(name = "social_whatsapp", length = 200)
  private String socialWhatsapp;

  @Column(name = "countdown_show")
  private Boolean showCountDown;

  @Column(name = "countdown_text", length = 50)
  private String countDownText;

  @Column(name = "countdown_date")
  private LocalDateTime countDownDate;

  @Column(name = "kiosk_scrolling_text", length = 500)
  private String kioskScrollingText;

  @Column(name = "prayer_notification_on")
  private String prayerNotificationOn;

  @Column(name = "prayer_notification_before")
  private Integer prayerNotificationBefore;

  @Column(name = "header_bg_color", length = 25)
  private String headerBgColor;

  @Column(name = "header_text_color", length = 25)
  private String headerTextColor;

  @Column(name = "top_menu_bg_color", length = 25)
  private String topMenuBgColor;

  @Column(name = "top_menu_text_color", length = 25)
  private String topMenuTextColor;

  @Column(name = "prayer_header_bg_color", length = 25)
  private String prayerHeaderBgColor;

  @Column(name = "prayer_header_text_color", length = 25)
  private String prayerHeaderTextColor;

  @Column(name = "remaining_bg_color", length = 25)
  private String remainingBgColor;

  @Column(name = "remaining_text_color", length = 25)
  private String remainingTextColor;

  @Column(name = "prayer_bg_color", length = 25)
  private String prayerBgColor;

  @Column(name = "prayer_text_color", length = 25)
  private String prayerTextColor;

  @Column(name = "juma_bg_color", length = 25)
  private String jumaBgColor;

  @Column(name = "juma_text_color", length = 25)
  private String jumaTextColor;

  @Column(name = "footer_bg_color", length = 25)
  private String footerBgColor;

  @Column(name = "footer_text_color", length = 25)
  private String footerTextColor;

  @Column(name = "updated_time", nullable = false)
  private transient LocalDateTime updatedTime;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "app_top_menu_button1_icon_id")
  private AppIcon appTopMenuButton1Icon;

  @Column(name = "app_top_menu_button1_text", length = 50)
  private String appTopMenuButton1Text;

  @Column(name = "app_top_menu_button1_url_type", length = 10)
  private String appTopMenuButton1UrlType;

  @Column(name = "app_top_menu_button1_url", length = 200)
  private String appTopMenuButton1Url;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "app_top_menu_button2_icon_id")
  private AppIcon appTopMenuButton2Icon;

  @Column(name = "app_top_menu_button2_text", length = 50)
  private String appTopMenuButton2Text;

  @Column(name = "app_top_menu_button2_url_type", length = 10)
  private String appTopMenuButton2UrlType;

  @Column(name = "app_top_menu_button2_url", length = 200)
  private String appTopMenuButton2Url;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "app_top_menu_button3_icon_id")
  private AppIcon appTopMenuButton3Icon;

  @Column(name = "app_top_menu_button3_text", length = 50)
  private String appTopMenuButton3Text;

  @Column(name = "app_top_menu_button3_url_type", length = 10)
  private String appTopMenuButton3UrlType;

  @Column(name = "app_top_menu_button3_url", length = 200)
  private String appTopMenuButton3Url;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "app_footer_button1_icon_id")
  private AppIcon appFooterButton1Icon;

  @Column(name = "app_footer_button1_text", length = 50)
  private String appFooterButton1Text;

  @Column(name = "app_footer_button1_url_type", length = 10)
  private String appFooterButton1UrlType;

  @Column(name = "app_footer_button1_url", length = 200)
  private String appFooterButton1Url;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "app_footer_button2_icon_id")
  private AppIcon appFooterButton2Icon;

  @Column(name = "app_footer_button2_text", length = 50)
  private String appFooterButton2Text;

  @Column(name = "app_footer_button2_url_type", length = 10)
  private String appFooterButton2UrlType;

  @Column(name = "app_footer_button2_url", length = 200)
  private String appFooterButton2Url;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "app_footer_button3_icon_id")
  private AppIcon appFooterButton3Icon;

  @Column(name = "app_footer_button3_text", length = 50)
  private String appFooterButton3Text;

  @Column(name = "app_footer_button3_url_type", length = 10)
  private String appFooterButton3UrlType;

  @Column(name = "app_footer_button3_url", length = 200)
  private String appFooterButton3Url;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "app_footer_button4_icon_id")
  private AppIcon appFooterButton4Icon;

  @Column(name = "app_footer_button4_text", length = 50)
  private String appFooterButton4Text;

  @Column(name = "app_footer_button4_url_type", length = 10)
  private String appFooterButton4UrlType;

  @Column(name = "app_footer_button4_url", length = 200)
  private String appFooterButton4Url;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "fajr_icon_id")
  private AppIcon fajrIcon;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "sunrise_icon_id")
  private AppIcon sunriseIcon;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "zuhr_icon_id")
  private AppIcon zuhrIcon;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "asr_icon_id")
  private AppIcon asrIcon;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "maghrib_icon_id")
  private AppIcon maghribIcon;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "isha_icon_id")
  private AppIcon ishaIcon;

  @Column(name = "portal_theme_name", length = 30)
  private String portalThemeName;

  @Column(name = "portal_theme_type", length = 10)
  private String portalThemeType;

  @Lob
  @Column(name = "one_time_payment_email_template")
  private String oneTimePaymentEmailTemplate;

  @Lob
  @Column(name = "recurring_payment_email_template")
  private String recurringPaymentEmailTemplate;

  @Column(name = "show_newsletter_signup")
  private Boolean showNewsletterSignup;

  @Column(name = "show_volunteer_signup")
  private Boolean showVolunteerSignup;

  @Column(name = "show_employee_signup")
  private Boolean showEmployeeSignup;
}
