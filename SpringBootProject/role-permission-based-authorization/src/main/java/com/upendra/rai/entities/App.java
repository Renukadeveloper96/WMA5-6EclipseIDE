package com.upendra.rai.entities;

import java.time.LocalDate;

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
@Table(name = "app")
@Getter
@Setter
@ToString(callSuper = true, exclude = {"client"})
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
//github
public class App extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "client_id", nullable = false)
  private Client client;

  @Column(name = "app_type", length = 25)
  private String appType;

  @Column(name = "release_date")
  private LocalDate releaseDate;

  @Deprecated(since = "2.0", forRemoval = true)
  @Column(name = "status", length = 25)
  private String status;

  @Column(name = "remarks", length = 200)
  private String remarks;

  @Column(name = "app_key", length = 1000)
  private String appKey;

  @Column(name = "device_type", length = 25)
  private String deviceType;

  @Column(name = "push_notification")
  private Boolean pushNotification;

}
