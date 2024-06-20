package com.upendra.rai.entities;


import java.time.ZonedDateTime;

import com.upendra.rai.enums.SlideType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "slide")
@Setter
@Getter
@ToString(callSuper = true, exclude = {"slideshow"})
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Slide extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "slideshow_id", nullable = false)
  private Slideshow slideshow;

  @Column(name = "image_path", length = 150, nullable = false)
  private String imagePath;

  @Column(name = "sort_order", nullable = false)
  private Integer sortOrder;

  @Column(name = "start_date")
  private ZonedDateTime startDate;

  @Column(name = "expiry_date")
  private ZonedDateTime expiryDate;

  @Column(name = "title", length = 256)
  private String title;

  @Column(name = "description", length = 255)
  private String description;

  @Column(name = "button_text", length = 100)
  private String buttonText;

  @Column(name = "url_type", length = 25)
  private String urlType;

  @Column(name = "url", length = 200)
  private String url;

  @Deprecated(since = "2.0", forRemoval = true)
  @Column(name = "status", length = 20)
  private String status;

  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private SlideType slideType;

}
