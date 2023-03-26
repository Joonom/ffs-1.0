package com.ppc.ffs.web.form;

import lombok.Data;

@Data
public class MemberPasswordForm {
    private Long memberId;

    private String loginId;

    private String originPassword;

    private String newPassword;

    private String newPasswordConfirm;

}
