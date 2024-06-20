package com.upendra.rai.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "country")
@Getter
@Setter
@ToString
public class Country {

  @Id
  @Column(name = "code", unique = true, nullable = false, length = 3)
  private String code;

  @Column(name = "name", nullable = false, length = 50)
  private String name;

  @Column(name = "short_code", unique = true, length = 2)
  private String shortCode;

}
