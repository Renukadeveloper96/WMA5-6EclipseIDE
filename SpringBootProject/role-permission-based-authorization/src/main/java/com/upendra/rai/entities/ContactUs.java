package com.upendra.rai.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "contact_us")
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class ContactUs extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "contact_us_category_id", nullable = false)
	private ContactUsCategory contactUsCategory;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "contact_us_person_id", nullable = false)
	private ContactUsPerson contactUsPerson;

	@Column(name = "subject", length = 100, nullable = false)
	private String subject;

	@Column(name = "body", length = 100, nullable = false)
	private String body;

	@Deprecated(since = "2.0", forRemoval = true)
	@Column
	private LocalDateTime dateTime;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "contactUs")
	private Set<ContactUsAttachment> attachments = new HashSet<>();

}
