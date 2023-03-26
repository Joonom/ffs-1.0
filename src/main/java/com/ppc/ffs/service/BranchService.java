package com.ppc.ffs.service;

import com.ppc.ffs.domain.BranchInfo;
import com.ppc.ffs.domain.EmployeeInfo;
import com.ppc.ffs.entity.Branch;
import com.ppc.ffs.entity.Employee;
import com.ppc.ffs.repository.BranchRepository;
import com.ppc.ffs.web.form.BranchForm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BranchService {

    @Autowired
    private EmployeeService employeeService;

    private final BranchRepository branchRepository;

    public List<BranchInfo> getBranchList() {
        List<Branch> branchList = branchRepository.findAll();
        List<BranchInfo> branchInfoList = mapBranchListToBranchInfoList(branchList);

        return branchInfoList;
    }


    public BranchInfo getBranch(Long branchId) {
        Optional<Branch> branchOptional = branchRepository.findById(branchId);
        if(branchOptional.isEmpty()) return null;

        BranchInfo branchInfo = mapBranchToBranchInfo(branchOptional.get());
        return branchInfo;
    }

    public List<EmployeeInfo> selectBranchEmployee(Long branchId) {
        List<EmployeeInfo> employeeInfoList = new ArrayList<>();

        Optional<Branch> branchOptional = branchRepository.findById(branchId);
        if(branchOptional.isEmpty()) return employeeInfoList;

        Branch branch = branchOptional.get();
        List<Employee> employeeList = branch.getEmployeeList();
        employeeInfoList = employeeService.mapEmployeeListToEmployeeInfoList(employeeList);
        return employeeInfoList;

    }

    public void addBranch(BranchForm form) {
        Branch branch = Branch.builder()
                .name(form.getName())
                .address(form.getAddress())
                .phoneNumber(form.getPhoneNumber())
                .build();
        branchRepository.save(branch);
    }

    public void editBranch(Long branchId, BranchForm form) throws Exception{
        Optional<Branch> branchOptional = branchRepository.findById(branchId);
        if(branchOptional.isEmpty()){
            throw new Exception();
        }
        Branch branch = branchOptional.get();
        branch.updateBranch(form.getName(),form.getAddress(),form.getPhoneNumber());
        branchRepository.save(branch);
    }


    public BranchInfo mapBranchToBranchInfo(Branch branch) {
        BranchInfo branchInfo = BranchInfo.builder()
                .branchId(branch.getBranchId())
                .name(branch.getName())
                .address(branch.getAddress())
                .phoneNumber(branch.getPhoneNumber())
                .build();
        return branchInfo;
    }

    public List<BranchInfo> mapBranchListToBranchInfoList(List<Branch> branchList) {
        List<BranchInfo> branchInfoList = new ArrayList<>();

        if(branchList.isEmpty()) {
            return branchInfoList;
        }

        for(Branch branch : branchList) {
            BranchInfo branchInfo = mapBranchToBranchInfo(branch);
            branchInfoList.add(branchInfo);
        }

        return branchInfoList;
    }
}
