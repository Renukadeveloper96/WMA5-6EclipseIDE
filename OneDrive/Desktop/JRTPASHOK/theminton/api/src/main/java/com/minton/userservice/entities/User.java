package com.minton.userservice.entities;
import com.minton.userservice.constants.AuthProvider;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Data
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
@NoArgsConstructor
public class User  {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String firstName;
    @Column(nullable = false)
    private String name;

    private String lastName;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )private Set<Role> roles = new HashSet<>();

    @Email(regexp = ".+[@].+[\\.].+")
    @Column(nullable = false,length = 50, unique = true)
    private String email;

    private String imageUrl;

    @Column(nullable = false)
    private boolean emailVerified = false;

    private boolean isAuthenticatedFromSocialMedia;

    @JsonIgnore
    private String password;

    private String phoneNumber;
    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;
    private boolean isActive = true;
    private boolean deleteFlag = false;


    @JoinColumn(name = "userType",referencedColumnName = "id")
    @OneToOne
    private UserType userType;

   private Date deletedOn;

    private String providerId;

    private String mediaMetaData;
    @CreationTimestamp
    @Column(updatable = false)
    private Date createdOn;


    @UpdateTimestamp
    private Date updatedOn;
}
