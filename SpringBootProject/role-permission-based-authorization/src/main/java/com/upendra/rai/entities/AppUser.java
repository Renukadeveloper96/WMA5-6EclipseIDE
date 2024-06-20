package com.upendra.rai.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "app_user")
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class AppUser extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "app_id", nullable = false)
  private App app;

  @Column(name = "device_registration_id", length = 200, nullable = false)
  private String deviceRegistrationId;

  @Column(name = "registration_date", nullable = false)
  private LocalDateTime registrationDate;

  @Deprecated(since = "2.0", forRemoval = true)
  @Column(name = "status", length = 25, nullable = false)
  private String status;

  @Column(name = "full_name", length = 50)
  private String fullName;

  @Column(name = "email", length = 100)
  private String email;

  @Column(name = "phone_number", length = 15)
  private String phoneNumber;

  @Column(name = "device_type", length = 15)
  private String deviceType;

}
