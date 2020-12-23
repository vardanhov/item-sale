package com.itemsale.dto;



public class ItemDto {

    private Long id;

    private PartyDto owner;

    private Long parentId;

    private String serialNumber;

    private String type;

    private Integer childrenCount;

    public ItemDto() {
    }

    public ItemDto(Long id, Long parentId, String serialNumber, PartyDto owner, String type, Integer childrenCount) {
        this.id = id;
        this.parentId = parentId;
        this.serialNumber = serialNumber;
        this.owner = owner;
        this.type = type;
        this.childrenCount = childrenCount;
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
}
