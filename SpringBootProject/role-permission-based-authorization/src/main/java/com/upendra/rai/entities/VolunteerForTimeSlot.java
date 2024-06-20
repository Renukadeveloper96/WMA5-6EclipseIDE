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
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "volunteer_for_time_slot")
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class VolunteerForTimeSlot extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "volunteer_for_id", nullable = false)
  private VolunteerFor volunteerFor;

  @Column(name = "from_time", length = 8, nullable = false)
  private String fromTime;

  @Column(name = "to_time", length = 8, nullable = false)
  private String toTime;

  @Enumerated(EnumType.STRING)
  @Column(name = "day", length = 10, nullable = false)
  private DayOfWeek day;

}
