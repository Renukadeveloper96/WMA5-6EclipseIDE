
package com.minton.userservice.entities;

import lombok.*;


import javax.persistence.*;

@Entity
@Table(name = "engagements")
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Engagement extends BaseEntity {

	@Column(nullable = false)
	private String name;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "engagementTypeId")
	private EngagementType engagementType;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "templateTypeId")
	private TemplateType templateType;
}