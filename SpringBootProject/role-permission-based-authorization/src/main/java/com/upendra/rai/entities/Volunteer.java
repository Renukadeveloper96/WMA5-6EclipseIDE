package com.upendra.rai.entities;




import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "volunteer")
@Getter
@Setter
@ToString(callSuper = true)
@Deprecated(forRemoval = true, since = "2.0")
public class Volunteer {

  @Id
  @GeneratedValue(strategy =GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "client_id", nullable = false)
  private Client client;

  @Column(name = "name", length = 50)
  private String name;

  @Column(name = "contact_number", length = 20)
  private String contactNumber;

  @Column(name = "email", length = 128)
  private String email;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "volunteer", cascade = CascadeType.ALL,
      orphanRemoval = true)
  private Set<VolunteerSignUp> volunteerSignUps = new HashSet<>(0);

}
