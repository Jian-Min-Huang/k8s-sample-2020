package org.yfr.sample.member.service;


import org.yfr.sample.common.entity.Member;

import java.util.List;

public interface MemberService {

    void saveMember(String password);

    List<Member> findByAccount(String account) throws Exception;

}
