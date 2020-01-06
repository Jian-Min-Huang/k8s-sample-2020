package org.yfr.sample.member.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.yfr.sample.common.entity.Member;
import org.yfr.sample.member.data.dao.MemberRepository;
import org.yfr.sample.member.service.MemberService;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

    @Resource
    private MemberRepository memberRepository;

    @Override
    @RabbitListener(queues = "member")
    public void saveMember(String password) {
        Member save = memberRepository.save(Member.builder().account("Vincent").alias("111").password(password).createTime(LocalDateTime.now()).build());
        log.info("save member : {}", save);
    }

    @Override
    public List<Member> findByAccount(String account) throws Exception {
        return memberRepository.findTop15ByAccountOrderByCreateTimeDesc(account);
    }
}
