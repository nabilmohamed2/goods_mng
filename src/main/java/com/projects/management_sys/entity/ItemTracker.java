package com.projects.management_sys.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="item_tracker")
public class ItemTracker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long trackerId;

    @ManyToOne
    @JoinColumn(name="id", nullable = false)
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

    public ItemTracker(long trackerId, Item item, String action, int quantity, String timestamp, String username) {
        this.trackerId = trackerId;
        this.item = item;
        this.action = action;
        this.quantity = quantity;
        this.timestamp = timestamp;
        this.username = username;
    }

    public ItemTracker(){}
}
