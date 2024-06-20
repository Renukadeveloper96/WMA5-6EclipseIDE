package com.upendra.rai.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "state")
@Getter
@Setter
@ToString(exclude = "country")
public class State {

  @Id
  @Column(name = "code", unique = true, nullable = false, length = 6)
  private String code;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "country_code")
  private Country country;

  @Column(name = "name", nullable = false, length = 50)
  private String name;

}
