package com.upendra.rai.entities;


import java.time.LocalDateTime;

import com.upendra.rai.enums.ImamQuestionAnswerStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "imam_question")
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class ImamQuestion extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "client_id", nullable = false)
  private Client client;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "imam_question_category_id", nullable = false)
  private ImamQuestionCategory imamQuestionCategory;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "imam_id", nullable = false)
  private Imam imam;

  @Lob
  @Column(name = "question")
  private String question;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @Deprecated(since = "2.0", forRemoval = true)
  @Column(name = "question_asked_by_email", length = 100)
  private String questionAskedByEmail;

  @Deprecated(since = "2.0", forRemoval = true)
  @Column(name = "question_asked_by_name", length = 50)
  private String questionAskedByName;

  @Deprecated(since = "2.0", forRemoval = true)
  @Column(name = "question_asked_by_phone", length = 20)
  private String questionAskedByPhone;

  @Column(name = "is_public")
  private Boolean isPublic;

  @Deprecated(since = "2.0", forRemoval = true)
  @Column(name = "quastion_date_time")
  private LocalDateTime questionDateTime;

  @Lob
  @Column(name = "answer")
  private String answer;

  @Column(name = "answered_by", length = 50)
  private String answeredBy;

  @Column(name = "answer_date_time")
  private LocalDateTime answerDateTime;

  @Enumerated(EnumType.STRING)
  @Column(name = "answer_status")
  private ImamQuestionAnswerStatus answerStatus;

}
