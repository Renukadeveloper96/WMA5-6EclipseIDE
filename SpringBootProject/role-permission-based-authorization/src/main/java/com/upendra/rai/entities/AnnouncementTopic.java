package com.upendra.rai.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "announcement_topic",
    uniqueConstraints = @UniqueConstraint(columnNames = {"client_id", "topic_name"}))
@Getter
@Setter
@ToString(callSuper = true, exclude = {"client"})
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class AnnouncementTopic extends BaseEntity {

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "client_id")
  private Client client;

  @Column(name = "topic_name", length = 100, nullable = false)
  private String topicName;

  @Column(name = "status", length = 20)
  private String status;

  @Column(name = "is_primary", columnDefinition = "TINYINT(1)")
  private Boolean isPrimary;

  @Column(name = "updated_time", nullable = false)
  private LocalDateTime updatedTime;
  
  @PrePersist
  @PreUpdate
  public void prePersistAndUpdate() {
      this.updatedTime = LocalDateTime.now();
  }

}

