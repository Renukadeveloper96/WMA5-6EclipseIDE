package com.upendra.rai.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "contact_us_attachments")
@Getter
@Setter
@ToString(callSuper = true)
public class ContactUsAttachment extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "contact_us_id", nullable = false)
  private ContactUs contactUs;

  @Column(nullable = false)
  private String attachmentPath;

  @Column(nullable = false)
  private String contentType;

  @Column(nullable = false)
  private String fileName;

}
