package com.upendra.rai.entities;

import java.time.DayOfWeek;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "volunteer_availability")
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class VolunteerAvailability extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "volunteer_signup_id", nullable = false)
  private VolunteerSignUp volunteerSignUp;

  @Column(name = "from_time", length = 8)
  private String fromTime;

  @Column(name = "to_time", length = 8)
  private String toTime;

  @Enumerated(EnumType.STRING)
  @Column(name = "day")
  private DayOfWeek day;

}
