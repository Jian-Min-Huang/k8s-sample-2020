package org.yfr.sample.member.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.yfr.sample.common.entity.Member;
import org.yfr.sample.member.data.dao.MemberRepository;
import org.yfr.sample.member.service.MemberService;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

    @Resource
    private MemberRepository memberRepository;

    @Override
    public Member findByAccount(String account) throws Exception {
        // bad pattern, just for test
        if (!memberRepository.findById(1L).isPresent()) {
            memberRepository.save(Member.builder().account("Vincent").alias("abc").password("123456").createTime(LocalDateTime.now()).build());
        }

        return memberRepository.findByAccount(account).orElseThrow(RuntimeException::new);
    }
}
