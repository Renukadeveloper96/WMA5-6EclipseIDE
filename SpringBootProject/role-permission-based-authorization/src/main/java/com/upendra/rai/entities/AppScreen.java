package com.upendra.rai.entities;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "mst_app_screen")
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class AppScreen extends BaseEntity {

  @Column(name = "app_screen_name", unique = true, nullable = false,
      length = 100)
  private String appScreenName;

}
