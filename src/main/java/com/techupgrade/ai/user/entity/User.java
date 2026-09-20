package com.techupgrade.ai.user.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name= "users",uniqueConstraints =
        { @UniqueConstraint(name="uk_users_email",columnNames="email")})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 100)
    private String firstName;

    @Column(nullable = false,length = 100)
    private String lastName;

    @Column(nullable = false,length = 255)
    private String email;

    @Column(nullable = false,length = 255)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 20)
    private UserStatus status;

    @Column(nullable = false,updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate(){
        LocalDateTime now=LocalDateTime.now();
        createdAt=now;
        updatedAt=now;

        if(status==null){
            status=UserStatus.ACTIVE;
        }
    }
    @PreUpdate
    protected void onUpdate(){
        updatedAt=LocalDateTime.now();

    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

}
