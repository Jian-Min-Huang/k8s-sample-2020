package org.yfr.sample.member.service;


import org.yfr.sample.common.entity.Member;

public interface MemberService {

    Member findByAccount(String account) throws Exception;
}
