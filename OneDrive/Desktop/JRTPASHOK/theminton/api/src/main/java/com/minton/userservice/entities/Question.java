
package com.minton.userservice.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "questions")
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Question extends BaseEntity {

	@Column(nullable = false)
	private String question;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "engagementId")
	private Engagement engagement;

	@Column(nullable = false)
	private String choice1;

	@Column(nullable = false)
	private String choice2;

	@Column(nullable = false)
	private String choice3;
	
	private String tidbitLink;

	private String tidbitText;

	private Long correctChoice;

	private String tags;

}