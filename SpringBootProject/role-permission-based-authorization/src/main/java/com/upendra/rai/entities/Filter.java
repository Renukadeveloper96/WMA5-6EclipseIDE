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
@Table(name = "filter", uniqueConstraints = @UniqueConstraint(columnNames = {"client_id", "name"}))
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Filter extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "client_id", nullable = false)
  private Client client;

  @Column(name = "name", length = 100)
  private String name;

  @Column(name = "description", length = 200)
  private String description;

  @Deprecated(since = "2.0", forRemoval = true)
  @Column(name = "status", length = 25)
  private String status;

  @Deprecated(since = "2.0", forRemoval = true)
  @Column(name = "updated_date")
  private LocalDateTime updatedDate;

}
