package com.projects.management_sys.service;

import com.projects.management_sys.dto.ItemDto;
import com.projects.management_sys.entity.Item;
import com.projects.management_sys.exception.ResourceNotFoundException;
import com.projects.management_sys.mapper.Mapper;
import com.projects.management_sys.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService{
    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl (ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    @Override
    public ItemDto createItem(ItemDto itemDto) {
        System.out.println("........."+itemDto.getName()+"...........");
        Item item = Mapper.itemDtoToItem(itemDto);
        System.out.println(item);
        Item savedItem = itemRepository.save(item);
        return Mapper.itemToItemDto(savedItem);
    }

    @Override
    public ItemDto getItem(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item with specified id doesn't exist"));
        return Mapper.itemToItemDto(item);
    }

    @Override
    public List<ItemDto> getAllItems() {
        List<ItemDto> allItems = itemRepository.findAll().stream().map((item)->Mapper.itemToItemDto(item)).collect(Collectors.toList());
        return allItems;
    }

    @Override
    public ItemDto updateItem(long itemId, ItemDto updatedDto) {
        Item item = itemRepository.findById(itemId).orElseThrow(()
                -> new ResourceNotFoundException("Item with id no."+itemId+" not found"));
        item.setName(updatedDto.getName());
        item.setQuantity(updatedDto.getQuantity());
        item.setPrice(updatedDto.getPrice());

        Item savedItem = itemRepository.save(item);
        return Mapper.itemToItemDto(savedItem);
    }

    @Override
    public void deleteItem(long itemId) {
        System.out.println("DELETE: "+itemId);
        Item item = itemRepository.findById(itemId).orElseThrow(()
                -> new ResourceNotFoundException("Item with id no."+itemId+" doesn't exist"));
        itemRepository.deleteById(itemId);
    }
}
