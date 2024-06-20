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
@Table(name = "tasbihat")
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Tasbihat extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "client_id", nullable = false)
  private Client client;

  @Column(name = "title", length = 200)
  private String title;

  @Column(name = "arabicTitle", length = 200)
  private String arabicTitle;

  @Column(name = "sort_order", nullable = false)
  private Integer sortOrder;

  @Deprecated(since = "2.0", forRemoval = true)
  @Column(name = "status")
  private String status;

  @Deprecated(since = "2.0", forRemoval = true)
  @Column(name = "last_updated_date")
  private LocalDateTime lastUpdated;

}
