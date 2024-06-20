package com.upendra.rai.entities;



import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "event")
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Event extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "client_id", nullable = false)
  private Client client;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "event_category_id", nullable = false)
  private EventCategory eventCategory;

  @Column(name = "event_title", length = 100, nullable = false)
  private String eventTitle;

  @Column(name = "event_description", length = 100)
  private String eventDescription;

  @Column(name = "event_banner_image_path", length = 100)
  private String eventBannerImagePath;

  @Column(name = "start_date", nullable = false)
  private LocalDate startDate;

  @Column(name = "end_date", nullable = false)
  private LocalDate endDate;

  @Column(name = "from_time", length = 8, nullable = false)
  private String fromTime;

  @Column(name = "to_time", length = 8, nullable = false)
  private String toTime;

  @Column(name = "is_repeat")
  private Boolean isRepeat;

  @Column(name = "is_mon_repeat")
  private Boolean isMonRepeat;

  @Column(name = "is_tue_repeat")
  private Boolean isTueRepeat;

  @Column(name = "is_wed_repeat")
  private Boolean isWedRepeat;

  @Column(name = "is_thu_repeat")
  private Boolean isThuRepeat;

  @Column(name = "is_fri_repeat")
  private Boolean isFriRepeat;

  @Column(name = "is_sat_repeat")
  private Boolean isSatRepeat;

  @Column(name = "is_sun_repeat")
  private Boolean isSunRepeat;

  @Lob
  @Column(name = "pre_requisite")
  private String preRequisite;

  @Column(name = "presenter")
  private String presenter;

  @Lob
  @Column(name = "presenter_bio")
  private String presenterBio;

  @Column(name = "action_url")
  private String actionUrl;

  @Column(name = "action_text")
  private String actionText;

  @Column(name = "fees")
  private String fees;

  @Column(name = "offered_by")
  private String offeredBy;

  @Column(name = "original_fees")
  private String originalFees;

  @Column(name = "discount_percentage")
  private String discountPercentage;

  @Column(name = "event_location")
  private String eventLocation;

  @Column(name = "registration_open")
  private Boolean registrationOpen;

  @Deprecated(since = "2.0", forRemoval = true)
  @Column(name = "status")
  private String status;

}
