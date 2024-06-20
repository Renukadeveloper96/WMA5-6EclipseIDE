package com.upendra.rai.entities;

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
@Table(name = "mst_app_sidebar", uniqueConstraints = @UniqueConstraint(columnNames = {"code"}))
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class AppSidebar extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "app_icon_id", nullable = false)
  private AppIcon appIcon;

  @Column(name = "code", length = 100, nullable = false)
  private String code;

  @Column(name = "label", length = 100, nullable = false)
  private String label;

  @Column(name = "sort_order", nullable = false)
  private Integer sortOrder;

}
