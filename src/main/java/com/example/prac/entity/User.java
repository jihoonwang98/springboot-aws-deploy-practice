package com.example.prac.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;


    private String firstName;
    private String lastName;
    private String email;
}
