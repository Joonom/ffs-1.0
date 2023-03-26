package com.ppc.ffs.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EmployeeInfo {
    private Long employeeId;
    private String branchName;
    private String employeeName;
    private String responsibility;
    private String address;
    private String phoneNumber;
    private String status;
    private String loginId;
    private String loginPassword;
    private String passwordType;
    private String passwordSalt;
}
