package com.upendra.rai.entities;


import java.time.ZonedDateTime;

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
@Table(name = "local_business")
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)

public class LocalBusiness extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "client_id", nullable = false)
  private Client client;

  @Column(name = "business_name", length = 100)
  private String businessName;

  @Column(name = "description", length = 100)
  private String description;

  @Column(name = "banner_image_path", length = 100)
  private String bannerImagePath;

  @Deprecated(since = "2.0", forRemoval = true)
  @Column(name = "status", length = 20)
  private String status;

  @Deprecated(since = "2.0", forRemoval = true)
  @Column(name = "last_updated_date")
  private ZonedDateTime lastUpdated;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "business_category_id", nullable = false)
  private BusinessCategory businessCategory;

  @Column(name = "contact_number", length = 100)
  private String contactNumber;

  @Column(name = "email", length = 100)
  private String email;

  @Column(name = "website", length = 100)
  private String website;

  @Column(name = "address", length = 100)
  private String address;

  @Column(name = "city", length = 100)
  private String city;

  @Column(name = "state", length = 100)
  private String state;

  @Column(name = "zipcode", length = 100)
  private String zipcode;

  @Column(name = "timing", length = 100)
  private String timing;

}
