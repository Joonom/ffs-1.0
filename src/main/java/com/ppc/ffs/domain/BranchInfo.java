package com.ppc.ffs.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BranchInfo {
    private Long branchId;
    private String name;
    private String address;
    private String phoneNumber;
}
