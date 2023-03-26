package com.ppc.ffs.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "EMPLOYEE")
public class Employee {
    @Id
    @Column(name = "EMPLOYEE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BRANCH_ID")
    private Branch branch;

    @Column(name = "NAME")
    private String name;

    @Column(name = "RESPONSIBILITY")
    private String responsibility;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "LOGIN_ID")
    private String loginId;

    @Column(name = "LOGIN_PASSWORD")
    private String password;

    @Column(name = "PASSWORD_TYPE")
    private String passwordType;

    @Column(name = "PASSWORD_SALT")
    private String passwordSalt;
}
