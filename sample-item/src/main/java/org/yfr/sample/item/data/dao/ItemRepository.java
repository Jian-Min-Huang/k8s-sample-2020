package org.yfr.sample.item.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.yfr.sample.common.entity.Item;

import java.util.List;

@RepositoryRestResource
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findFirst3ByCodeOrderByIdDesc(String code);

}
