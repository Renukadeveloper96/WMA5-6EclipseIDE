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
@Table(name = "dua")
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Dua extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "client_id")
  private Client client;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dua_category_id", nullable = false)
  private DuaCategory duaCategory;

  @Column(name = "dua_title", length = 100, nullable = false)
  private String duaTitle;

  @Column(name = "banner_image_path")
  private String bannerImagePath;

  @Column(name = "sort_order")
  private Integer sortOrder;

  @Lob
  @Column(name = "arabic_dua", nullable = false)
  private String arabicDua;

  @Lob
  @Column(name = "transliteration")
  private String transliteration;

  @Lob
  @Column(name = "translation")
  private String translation;

  @Lob
  @Column(name = "reference")
  private String reference;

  @Column(name = "master_dua_id")
  private Long masterDuaId;

  @Deprecated(since = "2.0", forRemoval = true)
  @Column(name = "status", length = 25)
  private String status;

  @Deprecated(since = "2.0", forRemoval = true)
  @Column(name = "updated_time")
  private LocalDateTime updatedTime;
}
