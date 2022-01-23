package com.cts.entitiy;

import com.cts.enums.UserType;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    private String phonenumber;
    private String licensenumber;
    private String address;
    private String password;
    private boolean blocked;
    @Enumerated(EnumType.STRING)
    private UserType userType;
}
