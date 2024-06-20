package com.upendra.rai.entities;

import java.time.ZonedDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter
@Setter
@ToString(exclude = {"createdBy", "lastModifiedBy"})
@EntityListeners(AuditingEntityListener.class)
public class Audit {

  @CreatedDate
  private ZonedDateTime createdDate;

  @LastModifiedDate
  private ZonedDateTime lastModifiedDate;

  @CreatedBy
  @ManyToOne
  @JoinColumn(name = "created_by")
  private User createdBy;

  @LastModifiedBy
  @ManyToOne
  @JoinColumn(name = "last_modified_by")
  private User lastModifiedBy;

}
