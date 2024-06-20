package com.upendra.rai.entities;


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
@Table(name = "locations")
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Location extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "client_id", nullable = false)
  private Client client;

  @Column(name = "location_name", length = 100)
  private String locationName;

  @Column(name = "logo_url", length = 100)
  private String logoUrl;

  @Column(name = "street", length = 100)
  private String street;

  @Column(name = "city", length = 100)
  private String city;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "state_code")
  private State state;

  @Column(name = "zipcode", length = 100)
  private String zipcode;

  @Column(name = "time_zone", length = 100)
  private String timeZone;

  @Column(name = "timezone_offset")
  private Integer timeZoneOffset;

  @Deprecated(since = "2.0", forRemoval = true)
  @Column(name = "status")
  private String status;

  @Column(name = "website", length = 500)
  private String website;

}
