package com.itemsale.dto;

public class PartyDto {

    private Long id;

    private String name;

    public PartyDto() {
    }

    public PartyDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
