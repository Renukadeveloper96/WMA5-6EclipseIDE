package com.upendra.rai.entities;


import java.time.LocalDateTime;
import java.util.TimeZone;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "client")
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Client extends BaseEntity {

  @Column(name = "code", length = 20, nullable = false)
  private String code;

  @Column(name = "name", nullable = false, length = 50, unique = true)
  private String name;

  @Column(name = "logo_url", length = 200)
  private String logoUrl;

  @Column(name = "street", length = 50, nullable = false)
  private String street;

  @Column(name = "city", length = 50, nullable = false)
  private String city;

  @Column(name = "zipcode", length = 100, nullable = false)
  private String zipcode;


  @Column(name = "joined_time", nullable = false)
  private LocalDateTime joinedTime;

  @Column(name = "updated_time", nullable = false)
  private LocalDateTime updatedTime;


  @Column(name = "website", length = 50)
  private String website;

  @Column(name = "remark", length = 100)
  private String remark;

  @Column(name = "alias", nullable = false, length = 100, unique = true)
  private String alias;

  @Column(length = 100)
  private String apiSecret;

  @PrePersist
  @PreUpdate
  public void prePersistAndUpdate() {
      this.updatedTime = LocalDateTime.now();
  }

}
