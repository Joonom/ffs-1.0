package com.ppc.ffs.web.form;


import lombok.Data;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Data
public class MemberForm implements Validator {

    private Long memberId;
    private String name;

    private String status;

    private String loginId;

    private String loginPassword;

    /** 회원 추가 시 필요한 지점정보와 직원 정보의 키값은 화면으로부터 받아온다.*/
    private Long branchId;

    private Long employeeId;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
