package com.boolsazo.bankchall.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "user")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(nullable = false)
    private String id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String gender;

    @Column(name="birthyear", nullable = false)
    private String birthYear;
    @Column(nullable = false)
    private String age;
    @Column(name="profile_image")
    private String profileImage;

    @Column(name="financial_type")
    private String financialType;

    public User() {
    }

    public User(String id) {
        this.id = id;
    }

    public User(String name, String email, String gender, String birthYear, String age,
        String profileImage) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.birthYear = birthYear;
        this.age = age;
        this.profileImage = profileImage;
    }

}
