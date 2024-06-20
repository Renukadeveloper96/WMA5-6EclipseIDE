package com.upendra.rai.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "mst_app_icon111")
@Getter
@Setter
@ToString(callSuper = true)
public class AppIcon  {

	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "id", unique = true, nullable = false)
	  private Long id;
  
	  @Column(name = "icon_name", unique = true, nullable = false, length = 200)
	  private String iconName;
	
	  @Column(name = "icon_url", nullable = false, length = 100)
	  private String iconUrl;

}
