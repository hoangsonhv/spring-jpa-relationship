package com.example.demospring.library.account;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private int status;
    @OneToOne
    @JoinColumn(name = "identity_id") // chủ thể chứa FK
    private IdentityCard identityCard;

}
