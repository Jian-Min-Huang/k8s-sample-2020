package org.yfr.sample.member.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.yfr.sample.common.entity.Member;
import org.yfr.sample.member.data.dao.MemberRepository;
import org.yfr.sample.member.service.MemberService;

import javax.annotation.Resource;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

    @Resource
    private MemberRepository memberRepository;

    @Override
    public Member findByAccount(String account) throws Exception {
        return memberRepository.findByAccount(account).orElseThrow(RuntimeException::new);
    }
}
