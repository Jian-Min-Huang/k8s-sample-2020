package org.yfr.sample.item.service;

import org.yfr.sample.common.entity.Item;

import java.util.List;

public interface ItemService {

    Item parse() throws Exception;

    List<Item> findByCode(String code) throws Exception;
}
