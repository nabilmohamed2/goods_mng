package com.projects.management_sys.service;

import com.projects.management_sys.dto.ItemDto;

import java.util.List;

public interface ItemService {
    ItemDto createItem(ItemDto itemDto);

    ItemDto getItem(Long id);

    List<ItemDto> getAllItems();

    ItemDto updateItem(long itemId, ItemDto updatedDto);

    void deleteItem(long itemId);
}
