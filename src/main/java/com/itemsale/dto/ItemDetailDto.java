package com.itemsale.dto;

import org.springframework.stereotype.Component;

@Component
public class ItemDetailDto {

    private Long id;

    private Long parentId;

    private String serialNumber;

    private PartyDto owner;

    private String type;

    private Integer childrenCount;

    private ItemDetailDto children;

    public ItemDetailDto() {
    }

    public ItemDetailDto(Long id, Long parentId, String serialNumber, PartyDto owner, String type, Integer childrenCount, ItemDetailDto children) {
        this.id = id;
        this.parentId = parentId;
        this.serialNumber = serialNumber;
        this.owner = owner;
        this.type = type;
        this.childrenCount = childrenCount;
        this.children = children;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public PartyDto getOwner() {
        return owner;
    }

    public void setOwner(PartyDto owner) {
        this.owner = owner;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getChildrenCount() {
        return childrenCount;
    }

    public void setChildrenCount(Integer childrenCount) {
        this.childrenCount = childrenCount;
    }

    public ItemDetailDto getChildren() {
        return children;
    }

    public void setChildren(ItemDetailDto children) {
        this.children = children;
    }
}