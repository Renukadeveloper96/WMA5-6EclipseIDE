package com.upendra.rai.entities;



import java.time.LocalDateTime;
import java.time.ZonedDateTime;

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
@Table(name = "contact_us_person")
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class ContactUsPerson extends BaseEntity {


  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "client_id", nullable = false)
  private Client client;

  @Column(name = "name", length = 100, nullable = false)
  private String name;

  @Column(name = "email", length = 100, nullable = false)
  private String email;

  @Column(name = "mobile_number", length = 100)
  private String mobileNumber;

  @Deprecated(since = "2.0", forRemoval = true)
  @Column(name = "last_updated_date")
  private LocalDateTime lastUpdated;

  @Column(name = "is_blocked")
  private Boolean isBlocked = Boolean.FALSE;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "blocked_by")
  private User blockedBy;

  @Lob
  @Column(name = "reason")
  private String reason;

  @Column(name = "blocked_on")
  private ZonedDateTime blockedOn;

}
