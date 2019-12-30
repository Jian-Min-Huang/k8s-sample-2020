package org.yfr.sample.common.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.yfr.sample.common.entity.Item;

import java.util.List;

@FeignClient(name = "ItemApi", url = "${host.sample.item}")
public interface ItemApi {

    @PostMapping("/item")
    Item parse() throws Exception;

    @GetMapping("/item")
    List<Item> findByCode(@RequestParam("code") String code) throws Exception;

}
