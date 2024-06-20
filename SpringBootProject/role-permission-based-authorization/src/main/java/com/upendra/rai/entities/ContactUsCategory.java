package com.upendra.rai.entities;



import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "contact_us_category")
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class ContactUsCategory extends BaseEntity {


  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "client_id", nullable = false)
  private Client client;

  @Column(name = "category", length = 100, nullable = false)
  private String category;

  @Lob
  @Column(name = "emails", nullable = false)
  private String emails;

  @Deprecated(since = "2.0", forRemoval = true)
  @Column(name = "status")
  private String status;

  @Deprecated(since = "2.0", forRemoval = true)
  @Column(name = "last_updated_date")
  private LocalDateTime lastUpdated;

}
