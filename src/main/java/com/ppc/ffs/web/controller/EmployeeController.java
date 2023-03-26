package com.ppc.ffs.web.controller;

import com.ppc.ffs.domain.EmployeeInfo;
import com.ppc.ffs.service.EmployeeService;
import com.ppc.ffs.web.form.EmployeeForm;
import com.ppc.ffs.web.util.APIError;
import com.ppc.ffs.web.util.APIResponse;
import com.ppc.ffs.web.validator.EmployeeValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/{employeeId}")
    public ResponseEntity<Object> getEmployeeInfo(@PathVariable("employeeId") Long employeeId) {

        EmployeeInfo employeeInfo = employeeService.getEmployee(employeeId);

        return APIResponse.success();
    }

    @PostMapping("/add")
    public ResponseEntity<Object> registerEmployee(@RequestBody EmployeeForm form, BindingResult bindingResult) throws Exception{
        EmployeeValidator validator = new EmployeeValidator();
        validator.validate(form,bindingResult);
        if(bindingResult.hasErrors()){
            return APIResponse.error(APIError.INVALID_PARAMETER);
        }

        employeeService.addEmployee(form);
        return APIResponse.success();
    }
}
