package com.ppc.ffs.web.controller;

import com.ppc.ffs.domain.MemberInfo;
import com.ppc.ffs.service.MemberService;
import com.ppc.ffs.web.form.MemberForm;
import com.ppc.ffs.web.form.MemberPasswordForm;
import com.ppc.ffs.web.util.APIError;
import com.ppc.ffs.web.util.APIResponse;
import com.ppc.ffs.web.validator.MemberValidator;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("add")
    public ResponseEntity<Object> addMember(@RequestBody MemberForm form, BindingResult bindingResult) throws Exception{

        MemberValidator validator = new MemberValidator();
        validator.validate(form,bindingResult);
        if(bindingResult.hasErrors()){
            return APIResponse.error(APIError.INVALID_PARAMETER);
        }

        memberService.addMember(form);

        return APIResponse.success();
    }

    @GetMapping("list")
    public ResponseEntity<Object> searchMember(@RequestParam(name = "name",required = false) String name,
                                               @RequestParam(name = "loginId",required = false) String loginId,
                                               @PageableDefault(size = 15, sort = "regDate", direction = Sort.Direction.DESC) Pageable pageable) {
        List<MemberInfo> memberInfoList = memberService.searchMemberList(name,loginId,pageable);

        return APIResponse.success();
    }

    @GetMapping("{memberId}")
    public ResponseEntity<Object> searchMember(@PathVariable("memberId") Long memberId) throws Exception{

        MemberInfo memberInfo = memberService.getMember(memberId);

        return APIResponse.success();
    }

    @PutMapping("edit/{memberId}")
    public ResponseEntity<Object> editMember(@PathVariable("memberId") Long memberId,@RequestBody MemberForm form, BindingResult bindingResult) throws Exception {
        MemberValidator validator = new MemberValidator();
        validator.validate(form,bindingResult);
        if(bindingResult.hasErrors()){
            return APIResponse.error(APIError.INVALID_PARAMETER);
        }
        memberService.updateMember(form);

        return  APIResponse.success();
    }

    @PostMapping("changePassword/{memberId}")
    public ResponseEntity<Object> changePassword(@PathVariable("memberId") Long memberId, @RequestBody MemberPasswordForm form) {

        if(!StringUtils.equals(form.getNewPassword(), form.getNewPasswordConfirm())){
            return APIResponse.error(100,"패스워드 확인 불일치",400);
        }

        try{
            MemberInfo memberInfo = memberService.getMemberByIdAndPassword(memberId, form.getLoginId(), form.getOriginPassword());
        }catch (Exception e){
            return APIResponse.error(101,"기존 패스워드 불일치",400);
        }
        return APIResponse.success();
    }
}
