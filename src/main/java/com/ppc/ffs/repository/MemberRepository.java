package com.ppc.ffs.repository;

import com.ppc.ffs.entity.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends Repository<Member,Long> {
    void save(Member member);

    Member findByLoginId(String loginId);

    List<Member> findByNameOrLoginId(String name, String loginId);

    List<Member> findByNameOrLoginId(String name, String loginId, Pageable pageable);

    Member findByMemberIdAndLoginIdAndLoginPassword(Long memberId, String loginId, String loginPassword);

    Optional<Member> findById(Long memberId);
}
