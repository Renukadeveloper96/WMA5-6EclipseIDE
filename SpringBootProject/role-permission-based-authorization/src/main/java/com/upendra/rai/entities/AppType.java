package com.upendra.rai.entities;


import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "mst_app_type")
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class AppType extends BaseEntity {

  @Column(unique = true, nullable = false, length = 100)
  private String appTypeLabel;

  @Column(length = 100)
  private String description;

  @Column(unique = true, nullable = false, length = 100)
  private String appTypeCode;

//  @OneToMany(mappedBy = "appType")
//  private Set<AppVersion> appVersions = new HashSet<>(0);
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "app_version_id", nullable = false)
  private AppVersion appVersions;

//  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//  @JoinTable(name = "mst_app_type__configurations",
//      joinColumns = {@JoinColumn(name = "app_type_id")},
//      inverseJoinColumns = {@JoinColumn(name = "configuration_id")})
//  private Set<Configuration> configurations = new HashSet<>(0);

}
