package com.upendra.rai.entities;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;;

@Entity
@Table(name = "sms_configuration",
    uniqueConstraints = @UniqueConstraint(columnNames = {"client_id", "profile_name"}))
public class SmsConfiguration extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "client_id", nullable = false)
  private Client client;

  @Column(name = "profile_name", length = 100, nullable = false)
  private String profileName;

  @Column(name = "service_sid", length = 40, nullable = false)
  private String serviceSid;

  @Column(name = "phone_number", length = 15, nullable = false)
  private String phoneNumber;

  @Column(name = "phone_sid", length = 40, nullable = false)
  private String phoneSid;

  @Column(name = "is_default", nullable = false)
  private Boolean isDefault;

  @Column(name = "sub_account_sid", length = 40)
  private String subAccountSid;

  @Deprecated(since = "2.0", forRemoval = true)
  @Column(name = "updated_time")
  private LocalDateTime updatedTime;

}
