package com.upendra.rai.entities;


import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "event_category",
    uniqueConstraints = @UniqueConstraint(columnNames = {"client_id", "category"}))
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class EventCategory extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "client_id", nullable = false)
  private Client client;

  @Column(name = "category", length = 100, nullable = false)
  private String category;

  @Deprecated(since = "2.0", forRemoval = true)
  @Column(name = "status")
  private String status;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "eventCategory")
  private Set<Event> events = new HashSet<>(0);

}
