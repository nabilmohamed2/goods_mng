package com.projects.management_sys.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table( name = "users" )
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String mail;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Set<Role> roles = new HashSet<>();

//    public Set<Item> getItem() {
//        return items;
//    }
//
//    public void setItem(Set<Item> item) {
//        items = item;
//    }
//
//    public void appendItem(Item item) {
//        items.add(item);
//    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;  // Should match the field name
    }

    public void setUsername(String username) {
        this.username = username;  // Should match the field name
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(Long id, String username, String mail, String password, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.roles = roles;
    }

//    public User(Long id, String username, String mail, String password, Set<Role> roles, Set<Item> items) {
//        this.id = id;
//        this.username = username;
//        this.mail = mail;
//        this.password = password;
//        this.roles = roles;
//        this.items = items;
//    }

    public User() {
    }
}
