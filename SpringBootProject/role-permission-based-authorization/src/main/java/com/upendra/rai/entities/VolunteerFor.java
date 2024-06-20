package com.upendra.rai.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "volunteer_for")
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class VolunteerFor extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "client_id", nullable = false)
  private Client client;

  @Column(name = "volunteer_for", length = 100)
  private String volunteerFor;

  @Column(name = "start_date")
  private LocalDate startDate;

  @Column(name = "end_date")
  private LocalDate endDate;

  @Column(name = "description", length = 500)
  private String description;

  @Column(name = "gender_restriction", length = 20)
  private String genderRestriction;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "event_id")
  private Event event;

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

  @Deprecated(since = "2.0", forRemoval = true)
  @Column(name = "last_updated_date")
  private LocalDateTime lastUpdated;

  @Deprecated(since = "2.0", forRemoval = true)
  @Column(name = "status")
  private String status;

  @Column(name = "notificationEmail")
  private String notificationEmail;

  @OneToMany(mappedBy = "volunteerFor", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<VolunteerForTimeSlot> volunteerForTimeSlots = new HashSet<>(0);

}
