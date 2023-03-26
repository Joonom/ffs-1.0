package com.ppc.ffs.web.form;


import lombok.Data;


import javax.validation.constraints.Pattern;

@Data
public class BranchForm{

	private Long branchId;

	private String name;
	
	private String address;
	
	@Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$")
	private String phoneNumber;
}
