package com.ppc.ffs.web.controller;

import com.ppc.ffs.domain.BranchInfo;
import com.ppc.ffs.domain.EmployeeInfo;
import com.ppc.ffs.service.BranchService;
import com.ppc.ffs.web.form.BranchForm;
import com.ppc.ffs.web.util.APIError;
import com.ppc.ffs.web.util.APIResponse;
import com.ppc.ffs.web.validator.BranchValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/branch")
public class BranchController {

    @Autowired
    private BranchService branchService;


    @GetMapping("list")
    public ResponseEntity<Object> selectAllBranch() {
        List<BranchInfo> branchInfoList = branchService.getBranchList();

        return APIResponse.success();
    }

    @GetMapping("/{branchId}")
    public ResponseEntity<Object> selectOneBranch(@PathVariable("branchId") Long branchId) {
        BranchInfo branchInfo = branchService.getBranch(branchId);
        return APIResponse.success();
    }

    @GetMapping("/{branchId}/employee")
    public ResponseEntity<Object> selectBranchEmployee(@PathVariable("branchId") Long branchId) {
        List<EmployeeInfo> employeeInfoList = branchService.selectBranchEmployee(branchId);
        return APIResponse.success();
    }

    @PostMapping("/add")
    public ResponseEntity<Object> registerBranch(@RequestBody BranchForm form, BindingResult bindingResult) {
        BranchValidator validation = new BranchValidator();
        validation.validate(form,bindingResult);
        if(bindingResult.hasErrors()){
            APIResponse.error(APIError.INVALID_PARAMETER);
        }

        branchService.addBranch(form);
        return APIResponse.success();

    }
    @PutMapping("edit/{branchId}")
    public ResponseEntity<Object> modifyBranch(@PathVariable("branchId") Long branchId, @RequestBody BranchForm form, BindingResult bindingResult) throws Exception {

        BranchValidator validation = new BranchValidator();
        validation.validate(form,bindingResult);
        if(bindingResult.hasErrors()){
            APIResponse.error(APIError.INVALID_PARAMETER);
        }

        branchService.editBranch(branchId,form);
        return APIResponse.success();
    }
}
