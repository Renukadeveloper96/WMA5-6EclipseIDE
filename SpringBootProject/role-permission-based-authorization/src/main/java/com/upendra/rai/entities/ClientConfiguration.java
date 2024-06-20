package com.upendra.rai.entities;

import com.upendra.rai.enums.EntityType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "client_configurations")
@Getter
@Setter
@ToString(callSuper = true, exclude = "client")
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class ClientConfiguration extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "client_id")
  private Client client;

  @ManyToOne
  @JoinColumn(name = "configuration_id")
  private Configuration configuration;

  @Lob
  private String value;

  @Enumerated(EnumType.STRING)
  @Column(name = "entity_type")
  private EntityType entityType;

  @Column(name = "entity_id")
  private String entityId;

}
