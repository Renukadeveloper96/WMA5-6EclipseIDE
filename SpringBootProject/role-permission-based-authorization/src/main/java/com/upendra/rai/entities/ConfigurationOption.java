package com.upendra.rai.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "configuration_options",
    indexes = {@Index(name = "configuration_options_idx1_label_value",
        columnList = "configuration_id,value,label", unique = true)})
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConfigurationOption extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "configuration_id", nullable = false)
  private Configuration configuration;

  @Column(name = "value", nullable = false)
  private String value;

  @Column(name = "label", nullable = false)
  private String label;

}
