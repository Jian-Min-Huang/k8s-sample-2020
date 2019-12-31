package org.yfr.sample.item.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.yfr.sample.common.entity.Item;
import org.yfr.sample.item.data.dao.ItemRepository;
import org.yfr.sample.item.service.ItemService;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class ItemServiceImpl implements ItemService {

    @Resource
    private ItemRepository itemRepository;

    @Override
    public Item parse() throws Exception {
        Item item = Item.builder()
                .code("2330")
                .createTime(LocalDateTime.now())
                .price(new Random(new Date().getTime()).nextInt(200) + 100f)
                .build();

        Item save = itemRepository.save(item);
        log.info("save {}", save.toString());

        return save;
    }

    @Override
    public List<Item> findByCode(String code) throws Exception {
        return itemRepository.findFirst3ByCodeOrderByIdDesc(code);
    }

}
