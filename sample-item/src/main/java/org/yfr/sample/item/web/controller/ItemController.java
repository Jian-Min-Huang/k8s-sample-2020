package org.yfr.sample.item.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RestController;
import org.yfr.sample.common.api.ItemApi;
import org.yfr.sample.common.entity.Item;
import org.yfr.sample.item.service.ItemService;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
public class ItemController implements ItemApi {

    @Resource
    private ItemService itemService;

    @Override
    public Item parse(HttpHeaders headers) throws Exception {
        return itemService.parse();
    }

    @Override
    public List<Item> findByCode(HttpHeaders headers, String code) throws Exception {
        return itemService.findByCode(code);
    }
}
