package com.projects.management_sys.controller;

import com.projects.management_sys.dto.ItemDto;
import com.projects.management_sys.repository.ItemRepository;
import com.projects.management_sys.repository.UserRepository;
import com.projects.management_sys.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*") 
@RestController
@RequestMapping("/api/items")
public class ItemController {
    private final ItemService itemService;
    private final ItemRepository itemRepository;

    @Autowired
    public ItemController (ItemService itemService, ItemRepository itemRepository) {
        this.itemService = itemService;
        this.itemRepository = itemRepository;
    }

    @PostMapping
    public ResponseEntity<ItemDto> addItem(@RequestBody ItemDto itemDto) {
        ItemDto savedItemDto = itemService.createItem(itemDto);
        return new ResponseEntity<>(savedItemDto, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<ItemDto> getItem(@PathVariable Long id){
        ItemDto itemDto = itemService.getItem(id);
        return new ResponseEntity<>(itemDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ItemDto>> getAllItems(){
        List<ItemDto> items = itemService.getAllItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }


    @PutMapping("{itemId}")
    public ResponseEntity<ItemDto> updateItem ( @PathVariable Long itemId, @RequestBody ItemDto updateItem ) {
        ItemDto updatedItem = itemService.updateItem(itemId, updateItem);
        return new ResponseEntity<>(updatedItem, HttpStatus.OK);
    }

    @DeleteMapping("{itemId}")
    public ResponseEntity<String> deleteItem ( @PathVariable Long itemId ){
        itemService.deleteItem(itemId);
        System.out.println("DELETE MAPPER");
        return new ResponseEntity<>("Item deleted successfully! ", HttpStatus.OK);
    }

}
