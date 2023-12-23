package com.minton.userservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserType {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NonNull
    private String name;


    private boolean deleteFlag;
    private Date deletedOn;

    @CreationTimestamp
    @Column(updatable = false)
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;
}
