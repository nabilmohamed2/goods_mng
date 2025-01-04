package com.projects.management_sys.dto;

import com.projects.management_sys.entity.Item;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

public class ItemTrackerDto {

    private long trackerId;
    private Item item;
    private String action;
    private int quantity;
    private String timestamp;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getTrackerId() {
        return trackerId;
    }

    public void setTrackerId(long trackerId) {
        this.trackerId = trackerId;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public ItemTrackerDto(long trackerId, Item item, String action, int quantity, String timestamp, String username) {
        this.trackerId = trackerId;
        this.item = item;
        this.action = action;
        this.quantity = quantity;
        this.timestamp = timestamp;
        this.username = username;
    }

    public ItemTrackerDto() {}
}
