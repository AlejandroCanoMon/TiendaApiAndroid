package com.example.tiendaapi.model;

public class Category {
    int id;
    Boolean pack;
    String description;
    String name;
    Integer price;



    public Category() {
    }

    public Category(int id, Boolean pack, String description, String name, Integer price) {
        this.id = id;
        this.id = id;
        this.pack = pack;
        this.description = description;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public Boolean getPack() {
        return pack;
    }

    public void setPack(Boolean pack) {
        this.pack = pack;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}



