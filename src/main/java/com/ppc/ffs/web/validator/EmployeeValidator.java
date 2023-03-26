package com.ppc.ffs.web.validator;

import com.ppc.ffs.web.form.EmployeeForm;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class EmployeeValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        EmployeeForm form = (EmployeeForm) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "employeeName", "","트레이너 이름을 입력해 주세요");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "","주소를 입력해 주세요");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "loginId", "","아이디를 입력해 주세요");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "","비밀번호를 입력해 주세요");

    }
}
