package com.upendra.rai.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false, exclude = {"isActive"})
public class  BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private Long id;

  @Column(name = "uuid", unique = true, nullable = false)
  private String uuid = UUID.randomUUID().toString();

  @Column(name = "is_active", nullable = false, columnDefinition = "boolean default true")
  private Boolean isActive = true;

}
