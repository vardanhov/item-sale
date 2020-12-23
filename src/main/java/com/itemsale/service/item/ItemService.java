package com.itemsale.service.item;

import com.itemsale.dto.ItemDetailDto;
import com.itemsale.dto.ItemDto;
import org.springframework.stereotype.Component;

import java.util.List;


public interface ItemService {

    List<ItemDto> getListOfPacks();

    ItemDetailDto getItemById(Long id);

    ItemDto saveItem(ItemDto itemDto);

    void saleItem(Long id);
}


