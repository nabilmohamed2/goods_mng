package com.projects.management_sys.service;

import com.projects.management_sys.dto.ItemDto;
import com.projects.management_sys.dto.ItemTrackerDto;
import com.projects.management_sys.entity.ItemTracker;
import com.projects.management_sys.mapper.Mapper;
import com.projects.management_sys.repository.ItemRepository;
import com.projects.management_sys.repository.ItemTrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemTrackerServiceImpl implements ItemTrackerService{

    private final ItemTrackerRepository itemTrackerRepository;
    private final ItemRepository itemRepository;
    @Autowired
    public ItemTrackerServiceImpl (ItemTrackerRepository itemTrackerRepository, ItemRepository itemRepository) {
        this.itemTrackerRepository = itemTrackerRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public ItemTrackerDto trackItemAction(ItemTrackerDto itemTrackerDto) {
        ItemTracker itemTracker = Mapper.itemTrackerDtoToItemTracker(itemTrackerDto);
        System.out.println("-----------"+itemTracker.getAction()+"---------");
        System.out.println("------------"+itemTracker.getItem().getName()+"----------");
        System.out.println("------------"+itemTracker.getItem().getQuantity()+"----------");
        int quantity = itemTracker.getItem().getQuantity() - itemTracker.getQuantity();
        itemTracker.getItem().setQuantity(quantity);

        itemRepository.save(itemTracker.getItem());
        ItemTracker savedItemTracker = itemTrackerRepository.save(itemTracker);
        System.out.println("------------"+itemTracker.getItem().getQuantity()+"----------");
        ItemTrackerDto savedItemTrackerDto = Mapper.itemTrackerToItemTrackerDto(savedItemTracker);
        return savedItemTrackerDto;
    }

    @Override
    public ItemTrackerDto getItemTracker ( Long trackerId ) {
        ItemTracker item = itemTrackerRepository.getReferenceById(trackerId);
        ItemTrackerDto itemTrackerDto = Mapper.itemTrackerToItemTrackerDto(item);
        return itemTrackerDto;
    }

    @Override
    public List<ItemTrackerDto> getAllItems() {
        List<ItemTrackerDto> allItems = itemTrackerRepository.findAll().stream().map((itemTracker) -> Mapper.itemTrackerToItemTrackerDto(itemTracker)).collect(Collectors.toList());
        return allItems;
    }
}
