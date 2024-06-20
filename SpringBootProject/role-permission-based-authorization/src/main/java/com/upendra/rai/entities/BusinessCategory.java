package com.upendra.rai.entities;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "business_category",
    uniqueConstraints = @UniqueConstraint(columnNames = {"client_id", "category"}))
@Getter
@Setter
@ToString(callSuper = true, exclude = {"client"})
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class BusinessCategory extends BaseEntity {

  @Column(name = "category", length = 100, nullable = false)
  private String category;

  @Deprecated(since = "2.0")
  @Column(name = "updated_date")
  private LocalDateTime updatedDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "client_id")
  private Client client;

  @Column(name = "icon_image_path", length = 100)
  private String iconImagePath;

  @Column(name = "sort_order", nullable = false)
  private Integer sortOrder;

}
