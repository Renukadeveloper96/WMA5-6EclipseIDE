package com.upendra.rai.entities;

import com.upendra.rai.enums.Source;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "customer")
@Getter
@Setter
@ToString(callSuper = true, exclude = {"client"})
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Customer extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "client_id", nullable = false)
  private Client client;

  @Column(name = "first_name", length = 100)
  private String firstName;

  @Column(name = "middle_name", length = 20)
  private String middleName;

  @Column(name = "last_name", length = 20)
  private String lastName;

  @Column(name = "email", length = 100)
  private String email;

  @Column(name = "contact_number", length = 20)
  private String contactNumber;

  @Enumerated(EnumType.STRING)
  @Column(name = "source")
  private Source source;

}
