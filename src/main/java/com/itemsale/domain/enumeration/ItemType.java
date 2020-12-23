package com.itemsale.domain.enumeration;

public enum ItemType {
    PACK(1, "PACK"),
    ITEM(2, "ITEM");



    private int value;

    private String name;


    ItemType(int value, String name) {
        this.value = value;
        this.name = name;
    }



    public static ItemType ofValue(final int value) {
        for (ItemType item : ItemType.values()) {
            if (item.getValue() == value) {
                return item;
            }
        }

        return null;
    }

    public static ItemType ofName(final String name) {
        for (ItemType item : ItemType.values()) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }

        return null;
    }


    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
