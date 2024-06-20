package com.upendra.rai.entities;




import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "dua_category",
    uniqueConstraints = @UniqueConstraint(columnNames = {"client_id", "category"}))
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class DuaCategory extends BaseEntity {

  @Column(name = "category", length = 100, nullable = false)
  private String category;

  @Column(length = 100)
  private String description;

  @Deprecated(since = "2.0")
  @Column
  private LocalDateTime updatedDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "client_id")
  private Client client;

  @Column(length = 100)
  private String iconImagePath;

  @Deprecated(since = "2.0")
  @Column
  private String status;

  @Column
  private Long masterCategoryId;

}
