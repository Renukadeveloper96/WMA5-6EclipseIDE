package com.upendra.rai.entities;

import java.time.LocalDate;

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
@Table(name = "client_job")
@Getter
@Setter
@ToString(callSuper = true, exclude = "client")
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class ClientJob extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "client_id")
  private Client client;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "job_category_id", nullable = false)
  private JobCategory jobCategory;

  @Column(name = "job_title", length = 255)
  private String jobTitle;

  @Column(name = "description", length = 255)
  private String description;

  @Column(name = "contact_email", length = 255)
  private String contactEmail;

  @Column(name = "contact_number", length = 255)
  private String contactNumber;

  @Column(name = "other_contact_detail", length = 255)
  private String otherContactDetail;

  @Column(name = "posted_date")
  private LocalDate postedDate;

  @Column(name = "expiry_date")
  private LocalDate expiryDate;

}
