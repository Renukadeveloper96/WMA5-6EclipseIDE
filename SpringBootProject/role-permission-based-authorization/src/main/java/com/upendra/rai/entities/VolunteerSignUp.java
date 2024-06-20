package com.upendra.rai.entities;


import java.time.ZonedDateTime;
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
@Table(name = "volunteer_signup")
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class VolunteerSignUp extends BaseEntity {

  @Column(name = "signup_date")
  @Deprecated(forRemoval = true, since = "2.0")
  private ZonedDateTime signUpDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "volunteer_id")
  @Deprecated(forRemoval = true, since = "2.0")
  private Volunteer volunteer;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "volunteer_for_id", nullable = false)
  private VolunteerFor volunteerFor;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "volunteerSignUp", cascade = CascadeType.ALL,
      orphanRemoval = true)
  private Set<VolunteerAvailability> volunteerAvailabilities = new HashSet<>(0);

}
