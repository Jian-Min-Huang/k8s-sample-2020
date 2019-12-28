package org.yfr.sample.common.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.yfr.sample.common.entity.Item;

import java.util.List;

@FeignClient(name = "ItemApi", url = "${host.sample.item}")
public interface ItemApi {

    @PostMapping("/item")
    Item parse(@RequestHeader HttpHeaders headers) throws Exception;

    @GetMapping("/item")
    List<Item> findByCode(@RequestHeader HttpHeaders headers, @RequestParam("code") String code) throws Exception;

}
