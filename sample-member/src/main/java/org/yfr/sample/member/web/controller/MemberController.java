package org.yfr.sample.member.web.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import org.yfr.sample.common.api.MemberApi;
import org.yfr.sample.common.entity.Member;
import org.yfr.sample.member.service.MemberService;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
public class MemberController implements MemberApi {

    @Resource
    private MemberService memberService;

    @Override
    public List<Member> findByAccount(String account) throws Exception {
        return memberService.findByAccount(account);
    }
}
