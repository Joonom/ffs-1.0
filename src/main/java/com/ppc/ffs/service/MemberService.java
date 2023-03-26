package com.ppc.ffs.service;

import com.ppc.ffs.common.CommonService;
import com.ppc.ffs.domain.BranchInfo;
import com.ppc.ffs.domain.MemberInfo;
import com.ppc.ffs.entity.Branch;
import com.ppc.ffs.entity.Employee;
import com.ppc.ffs.entity.Member;
import com.ppc.ffs.repository.BranchRepository;
import com.ppc.ffs.repository.EmployeeRepository;
import com.ppc.ffs.repository.MemberRepository;
import com.ppc.ffs.web.form.MemberForm;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final EmployeeRepository employeeRepository;

    private final BranchRepository branchRepository;

    public void addMember(MemberForm form) throws Exception {
        Optional<Branch> branchOptional = branchRepository.findById(form.getBranchId());
        Optional<Employee> employeeOptional = employeeRepository.findById(form.getEmployeeId());

        if(branchOptional.isEmpty() || employeeOptional.isEmpty()){
            throw new Exception();
        }
        Member existMember = memberRepository.findByLoginId(form.getLoginId());

        if(existMember != null){
            throw new Exception();
        }
        Member member = Member.builder()
                .name(form.getName())
                .loginId(form.getLoginId())
                .loginPassword(form.getLoginPassword())
                .passwordType("SHA-256")
                .passwordSalt(CommonService.makeKey(24))
                .status(form.getStatus())
                .regDate(new Date())
                .build();
        memberRepository.save(member);
    }
    public List<MemberInfo> selectMemberList(String name, String loginId) {
        List<Member> memberList = memberRepository.findByNameOrLoginId(name,loginId);
        return mapMemberListToMemberInfoList(memberList);
    }
    public List<MemberInfo> searchMemberList(String name, String loginId, Pageable pageable) {
        List<Member> memberList = memberRepository.findByNameOrLoginId(name,loginId,pageable);
        return mapMemberListToMemberInfoList(memberList);
    }

    public MemberInfo getMember(Long memberId) throws Exception {
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        if(memberOptional.isEmpty()){
            throw new Exception();
        }
        return mapMemberToMemberInfo(memberOptional.get());
    }

    public void updateMember(MemberForm form) throws Exception {
        Optional<Member> memberOptional = memberRepository.findById(form.getMemberId());
        if( memberOptional.isEmpty()){
            throw new Exception();
        }
        Member member = memberOptional.get();
        member.updateMember(form.getLoginId(),form.getName(),form.getStatus());
        memberRepository.save(member);
    }

    public MemberInfo getMemberByIdAndPassword(Long memberId, String loginId, String originPassword) throws Exception {
        Member member = memberRepository.findByMemberIdAndLoginIdAndLoginPassword(memberId,loginId,originPassword);
        if(member == null){
            throw new Exception();
        }
        return mapMemberToMemberInfo(member);
    }



    public MemberInfo mapMemberToMemberInfo(Member member){
        MemberInfo memberInfo = MemberInfo.builder()
                .memberId(member.getMemberId())
                .name(member.getName())
                .employeeId(member.getEmployee().getEmployeeId())
                .status(member.getStatus())
                .branchId(member.getBranch().getBranchId())
                .passwordType(member.getPasswordType())
                .loginPassword(member.getLoginPassword())
                .loginId(member.getLoginId())
                .passwordSalt(member.getPasswordSalt())
                .regDate(member.getRegDate())
                .build();
        return memberInfo;
    }

    public List<MemberInfo> mapMemberListToMemberInfoList(List<Member> memberList) {
        List<MemberInfo> memberInfoList = new ArrayList<>();

        if(memberList == null){
            return memberInfoList;
        }

        for(Member member: memberList){
            MemberInfo memberInfo = mapMemberToMemberInfo(member);
            memberInfoList.add(memberInfo);
        }

        return memberInfoList;
    }
}
