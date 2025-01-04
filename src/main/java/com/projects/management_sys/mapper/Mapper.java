package com.projects.management_sys.mapper;

import com.projects.management_sys.dto.ItemDto;
import com.projects.management_sys.dto.ItemTrackerDto;
import com.projects.management_sys.entity.Item;
import com.projects.management_sys.entity.ItemTracker;

public class Mapper {
    public static Item itemDtoToItem (ItemDto itemDto){
        return new Item(
                itemDto.getId(),
                itemDto.getName(),
                itemDto.getQuantity(),
                itemDto.getPrice(),
                itemDto.getUsername()
        );
    }

    public static ItemDto itemToItemDto (Item item){
        return new ItemDto(
                item.getId(),
                item.getName(),
                item.getQuantity(),
                item.getPrice(),
                item.getUsername()
        );
    }

    public static ItemTracker itemTrackerDtoToItemTracker (ItemTrackerDto itemTrackerDto) {
        return new ItemTracker(
                itemTrackerDto.getTrackerId(),
                itemTrackerDto.getItem(),
                itemTrackerDto.getAction(),
                itemTrackerDto.getQuantity(),
                itemTrackerDto.getTimestamp(),
                itemTrackerDto.getUsername()
        );
    }

    public static ItemTrackerDto itemTrackerToItemTrackerDto (ItemTracker itemTracker) {
        return new ItemTrackerDto(
                itemTracker.getTrackerId(),
                itemTracker.getItem(),
                itemTracker.getAction(),
                itemTracker.getQuantity(),
                itemTracker.getTimestamp(),
                itemTracker.getUsername()
        );
    }
}
