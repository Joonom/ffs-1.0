package com.ppc.ffs.service;

import com.ppc.ffs.common.CommonService;
import com.ppc.ffs.domain.EmployeeInfo;
import com.ppc.ffs.entity.Branch;
import com.ppc.ffs.entity.Employee;
import com.ppc.ffs.repository.BranchRepository;
import com.ppc.ffs.repository.EmployeeRepository;
import com.ppc.ffs.web.form.EmployeeForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final BranchRepository branchRepository;

    public EmployeeInfo getEmployee(Long employeeId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if(employeeOptional.isEmpty()) return null;

        EmployeeInfo employeeInfo = mapEmployeeToEmployeeInfo(employeeOptional.get());
        return employeeInfo;
    }

    public void addEmployee(EmployeeForm form) throws Exception {
        Long branchId = form.getBranchId();
        Optional<Branch> branchOptional = branchRepository.findById(branchId);
        if(branchOptional.isEmpty()){
            throw new Exception();
        }
        Employee employee = Employee.builder()
                .name(form.getEmployeeName())
                .branch(branchOptional.get())
                .loginId(form.getLoginId())
                .password(form.getPassword())
                .passwordType("SHA-256")
                .passwordSalt(CommonService.makeKey(24))
                .responsibility(form.getResponsibility())
                .status("정상")
                .phoneNumber(form.getPhoneNumber())
                .address(form.getAddress())
                .build();

        employeeRepository.save(employee);
    }

    public EmployeeInfo mapEmployeeToEmployeeInfo(Employee employee) {
        EmployeeInfo employeeInfo = EmployeeInfo.builder()
                .employeeId(employee.getEmployeeId())
                .branchName(employee.getBranch().getName())
                .employeeName(employee.getName())
                .responsibility(employee.getResponsibility())
                .address(employee.getAddress())
                .phoneNumber(employee.getPhoneNumber())
                .status(employee.getStatus())
                .loginId(employee.getLoginId())
                .loginPassword(employee.getPassword())
                .passwordType(employee.getPasswordType())
                .passwordSalt(employee.getPasswordSalt())
                .build();
        return employeeInfo;
    }

    public List<EmployeeInfo> mapEmployeeListToEmployeeInfoList(List<Employee> employeeList) {
        List<EmployeeInfo> employeeInfoList = new ArrayList<>();

        if(employeeList.isEmpty()) {
            return employeeInfoList;
        }

        for(Employee employee : employeeList) {
            EmployeeInfo employeeInfo = mapEmployeeToEmployeeInfo(employee);
            employeeInfoList.add(employeeInfo);
        }

        return employeeInfoList;
    }

}
