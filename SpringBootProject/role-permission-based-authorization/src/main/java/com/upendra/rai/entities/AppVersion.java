package com.upendra.rai.entities;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "app_version")
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class AppVersion extends BaseEntity {

  @Column(name = "version", length = 100, nullable = false)
  private String version;

  @Column(name = "description", length = 100)
  private String description;

  @Column(name = "is_default")
  private Boolean isDefault;

  @Column(name = "status", length = 100)
  private String status;

}
