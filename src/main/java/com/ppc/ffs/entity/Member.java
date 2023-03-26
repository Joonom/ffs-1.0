package com.ppc.ffs.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MEMBER", uniqueConstraints = {@UniqueConstraint(name = "LOGINID_UNIQUE", columnNames = {"LOGIN_ID"})})
public class Member {

    @Id
    @Column(name = "MEMBER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @ManyToOne
    @JoinColumn(name = "BRANCH_ID")
    private Branch branch;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "LOGIN_ID", nullable = false)
    private String loginId;

    @Column(name = "LOGIN_PASSWORD", nullable = false)
    private String loginPassword;

    @Column(name = "PASSWORD_TYPE")
    private String passwordType;

    @Column(name = "PASSWORD_SALT")
    private String passwordSalt;

    @Column(name = "REG_DATE")
    private Date regDate;


    public void updateMember(String loginId,String name,String status ){
        this.name = name;
        this.status = status;
        this.loginId = loginId;
    }
}
