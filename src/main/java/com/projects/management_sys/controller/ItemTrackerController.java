package com.projects.management_sys.controller;

import com.projects.management_sys.dto.ItemTrackerDto;
import com.projects.management_sys.service.ItemTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/itemTracker")
public class ItemTrackerController {

    public final ItemTrackerService itemTrackerService;

    @Autowired
    public ItemTrackerController(ItemTrackerService itemTrackerService) {
        this.itemTrackerService = itemTrackerService;
    }

    @PostMapping
    public ResponseEntity<ItemTrackerDto> addTracker (@RequestBody ItemTrackerDto itemTrackerDto ) {
        ItemTrackerDto savedItem = itemTrackerService.trackItemAction(itemTrackerDto);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<ItemTrackerDto> getTracker ( @PathVariable long id ) {
        ItemTrackerDto getItem = itemTrackerService.getItemTracker(id);
        return new ResponseEntity<>(getItem, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ItemTrackerDto>> getAllTrackers () {
        List<ItemTrackerDto> allItems = itemTrackerService.getAllItems();
        return new ResponseEntity<>(allItems,HttpStatus.OK);
    }
}
