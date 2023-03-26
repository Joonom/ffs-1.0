package com.ppc.ffs.repository;

import com.ppc.ffs.entity.Branch;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface BranchRepository extends Repository<Branch,Long> {
    List<Branch> findAll();

    Optional<Branch> findById(Long branchId);

    void save(Branch branch);
}
