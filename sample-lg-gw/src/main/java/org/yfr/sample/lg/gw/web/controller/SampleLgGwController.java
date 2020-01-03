package org.yfr.sample.lg.gw.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.yfr.sample.common.api.ItemApi;
import org.yfr.sample.common.api.MemberApi;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
public class SampleLgGwController {

    @Value("${host.sample.item}")
    private String itemHost;

    @Value("${host.sample.member}")
    private String memberHost;

    @Resource
    private ItemApi itemApi;

    @Resource
    private MemberApi memberApi;

    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/item")
    List findByCode(@RequestParam("code") String code, @RequestHeader HttpHeaders headers) throws Exception {
//        HttpHeaders newHeaders = new HttpHeaders();
//        newHeaders.addAll(headers);
//        HttpEntity entity = new HttpEntity(newHeaders);
//
//        return restTemplate.exchange(itemHost + "/item?code=" + code, HttpMethod.GET, entity, List.class).getBody();
        return itemApi.findByCode(code);
    }

    @GetMapping("/member")
    List findByAccount(@RequestParam("account") String account, @RequestHeader HttpHeaders headers) throws Exception {
//        HttpHeaders newHeaders = new HttpHeaders();
//        newHeaders.addAll(headers);
//        HttpEntity entity = new HttpEntity(newHeaders);
//
//        return restTemplate.exchange(memberHost + "/member?account=" + account, HttpMethod.GET, entity, List.class).getBody();
        return memberApi.findByAccount(account);
    }
}
