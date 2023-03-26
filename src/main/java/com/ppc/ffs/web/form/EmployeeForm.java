package com.ppc.ffs.web.form;

import lombok.Data;

@Data
public class EmployeeForm {
    private Long branchId;
    private String employeeName;
    private String responsibility;
    private String address;
    private String phoneNumber;
    private String loginId;
    private String password;
}
