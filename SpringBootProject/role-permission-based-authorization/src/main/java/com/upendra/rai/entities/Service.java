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
@Table(name = "service")
@Getter
@Setter
@ToString(callSuper = true, exclude = {"client"})
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Service extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "client_id", nullable = false)
  private Client client;

  @Column(name = "service_name", length = 200)
  private String serviceName;

  @Column(name = "description", length = 10000)
  private String description;

  @Column(name = "banner_image_path", length = 200)
  private String bannerImagePath;

  @Column(name = "icon_image_path", length = 200)
  private String iconImagePath;

  @Column(name = "sort_order", nullable = false)
  private Integer sortOrder;

  @Deprecated(since = "2.0", forRemoval = true)
  @Column(name = "status", length = 20)
  private String status;

  @Deprecated(since = "2.0", forRemoval = true)
  @Column(name = "last_updated_date")
  private LocalDateTime lastUpdated;

}
