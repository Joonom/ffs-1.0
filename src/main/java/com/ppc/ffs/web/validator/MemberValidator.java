package com.ppc.ffs.web.validator;

import com.ppc.ffs.web.form.MemberForm;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class MemberValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        MemberForm form = (MemberForm) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "","이름을 입력해 주세요");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "loginId", "","아이디를 입력해 주세요");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "loginPassword", "","비밀번호를 입력해 주세요");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "branchId", "","지점키를 입력해 주세요");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "employeeId", "","트레이너ID를 입력해 주세요");

    }
}
