package com.ppc.ffs.web.validator;

import com.ppc.ffs.web.form.BranchForm;
import com.ppc.ffs.web.util.CheckUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class BranchValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        BranchForm form = (BranchForm) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "","이름을 입력해 주세요");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "","주소를 입력해 주세요");

        String phoneNumber = form.getPhoneNumber();
        if(StringUtils.isNotEmpty(phoneNumber) && !CheckUtil.isPhoneNumber(phoneNumber)){
            errors.rejectValue("phoneNumber","","전화번호 형식이 맞지 않습니다.");
        }
    }
}
