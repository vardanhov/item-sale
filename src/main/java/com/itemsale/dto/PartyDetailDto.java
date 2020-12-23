package com.itemsale.dto;

import java.util.List;

public class PartyDetailDto {

    private Long id;

    private String name;

    private List<ItemDto> itemDtoList;

    public PartyDetailDto() {
    }

    public PartyDetailDto(Long id, String name, List<ItemDto> itemDtoList) {
        this.id = id;
        this.name = name;
        this.itemDtoList = itemDtoList;
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

    public List<ItemDto> getItemDtoList() {
        return itemDtoList;
    }

    public void setItemDtoList(List<ItemDto> itemDtoList) {
        this.itemDtoList = itemDtoList;
    }
}


