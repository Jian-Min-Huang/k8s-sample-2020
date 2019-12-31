package org.yfr.sample.member.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.yfr.sample.common.entity.Member;

import java.util.Optional;

@RepositoryRestResource
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findTopByAccountOrderByCreateTimeDesc(String account);

}
