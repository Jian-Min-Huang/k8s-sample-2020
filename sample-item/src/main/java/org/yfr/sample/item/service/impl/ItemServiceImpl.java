package org.yfr.sample.item.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.yfr.sample.common.entity.Item;
import org.yfr.sample.item.data.dao.ItemRepository;
import org.yfr.sample.item.service.ItemService;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ItemServiceImpl implements ItemService {

    @Resource
    private ItemRepository itemRepository;

    @Override
    public Item parse() throws Exception {
        Document doc = Jsoup.connect("https://www.wantgoo.com/global/stockindex?StockNo=0000").get();
        Elements price = doc.select(".price");

        Item item = Item.builder()
                .code("0000")
                .createTime(LocalDateTime.now())
                .price(Float.parseFloat(((TextNode) price.get(0).childNodes().get(0)).text()))
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
