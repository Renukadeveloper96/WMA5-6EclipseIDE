
package com.minton.userservice.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "promotions")
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Promotion extends BaseEntity {

	@Column(nullable = false)
	private String promotionName;

	private String promotionText;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "engagementId")
	private Engagement engagement;

	private String image;

	@Lob
	private String imageBase64;

	private String url;

	private String tags;

}