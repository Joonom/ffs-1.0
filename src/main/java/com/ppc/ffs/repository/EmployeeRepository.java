package com.ppc.ffs.repository;

import com.ppc.ffs.entity.Employee;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface EmployeeRepository extends Repository<Employee,Long> {
    Optional<Employee> findById(Long employeeId);

    void save(Employee employee);
}
