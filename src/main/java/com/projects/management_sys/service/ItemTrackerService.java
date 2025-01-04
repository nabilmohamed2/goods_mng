package com.projects.management_sys.service;

import com.projects.management_sys.dto.ItemTrackerDto;

import java.util.List;

public interface ItemTrackerService {
    ItemTrackerDto trackItemAction(ItemTrackerDto itemTrackerDto);

    ItemTrackerDto getItemTracker ( Long trackerId ) ;

    List<ItemTrackerDto> getAllItems();
}
