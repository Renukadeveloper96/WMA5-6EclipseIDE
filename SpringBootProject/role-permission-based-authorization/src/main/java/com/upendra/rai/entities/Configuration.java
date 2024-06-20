package com.upendra.rai.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.upendra.rai.enums.AccessLevel;
import com.upendra.rai.enums.Category;
import com.upendra.rai.enums.DataType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Index;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "configurations",
    indexes = {@Index(name = "configurations_idx1_code", columnList = "code", unique = true)})
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Configuration extends BaseEntity {

  @Column(nullable = false)
  private String code;

  @Column(nullable = false)
  private String label;

  @Column(name = "placeholder")
  private String placeholder;

  @Lob
  @Column(name = "description")
  private String description;

  @Lob
  @Column(name = "default_value")
  private String defaultValue;

  @Column(nullable = false)
  private Boolean isSuperAdminManaged;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private AccessLevel accessLevel;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private DataType dataType;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Category category;

  @Column(nullable = false)
  private Integer displayOrder;

  @Column(name = "is_required")
  private Boolean isRequired;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "configuration")
  private List<ConfigurationOption> options = new ArrayList<>();

//  @ManyToMany(mappedBy = "configurations")
//  private Set<AppType> appTypes;

}
