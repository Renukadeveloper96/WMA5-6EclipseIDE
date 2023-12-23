package com.minton.userservice.entities;

import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class EngagementType extends BaseEntity {

	@Column(unique = true, nullable = false)
	private String type;
}