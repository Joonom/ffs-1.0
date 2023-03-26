package com.ppc.ffs.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class MemberInfo {

    /** 사용자 기본 정보 시작 */
    private Long memberId;

    private String name;

    private String status;

    private String loginId;

    private String loginPassword;

    private String passwordType;

    private String passwordSalt;

    private Date regDate;

    /** 사용자 기본 정보 끝 */

    /** 추가 데이터 시작 */

    private Long branchId;

    private Long employeeId;
    /** 추가 데이터 끝 */
}
