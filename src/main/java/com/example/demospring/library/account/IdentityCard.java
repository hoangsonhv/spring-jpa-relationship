package com.example.demospring.library.account;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "identity_cards")
public class IdentityCard {
    @Id
    private String number;
    private String fullname;
    private int gender;
    private String address;
    private String description;
    private Date birthday;
    private int status;
    private String createBy;
    private Date createAt;
    private Date updateAt;
    @OneToOne(mappedBy = "identityCard") // đối tượng tham chiếu
    private Account account;

}
