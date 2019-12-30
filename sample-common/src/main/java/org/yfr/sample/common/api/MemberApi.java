package org.yfr.sample.common.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.yfr.sample.common.entity.Member;

@FeignClient(name = "MemberApi", url = "${host.sample.member}")
public interface MemberApi {

    @GetMapping("/member")
    Member findByAccount(@RequestParam("account") String account) throws Exception;

}
