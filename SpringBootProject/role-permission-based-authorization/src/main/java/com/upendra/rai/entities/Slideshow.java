package com.upendra.rai.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "slideshow")
@Getter
@Setter
@ToString(callSuper = true, exclude = {"client"})
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Slideshow extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "client_id", nullable = false)
  private Client client;

  @Column(name = "slideshow_name", length = 100)
  private String slideShowName;

  @Deprecated(since = "2.0", forRemoval = true)
  @Column(name = "status", length = 20)
  private String status;

  @Deprecated(since = "2.0", forRemoval = true)
  @Column(name = "updated_time", nullable = false)
  private LocalDateTime updatedTime;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "slideshow")
  private Set<Slide> slides = new HashSet<>(0);

}
