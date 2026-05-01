package com.chirayu.flowgrid.entity;

import com.chirayu.flowgrid.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "owner_table")
@Getter
@Setter
@Entity
public class Owner extends Base {
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(name = "state", nullable = false)
    private String state;
    @Column(name = "mobile_number", nullable = false, unique = true, length = 10)
    private String mobileNumebr;
    @Column(name = "email_id", nullable = false, unique = true, length = 50)
    private String emailId;
    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    @JoinColumn(name = "pet_id", referencedColumnName = "id", nullable = false, unique = true)
    private Pet pet;
}
