package org.yfr.sample.member.service;


import org.yfr.sample.common.entity.Member;

public interface MemberService {

    void saveMember(String password);

    Member findByAccount(String account) throws Exception;

}
