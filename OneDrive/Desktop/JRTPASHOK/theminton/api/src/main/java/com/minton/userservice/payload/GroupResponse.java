package com.minton.userservice.payload;

import java.time.LocalDateTime;

import javax.persistence.Column;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupResponse {

	private Long id;

	private String groupName;

	private Boolean isActive;

	private LocalDateTime createdOn;

	private Long createdBy;

	private Long modifiedBy;

	private LocalDateTime modifiedAt;

}
