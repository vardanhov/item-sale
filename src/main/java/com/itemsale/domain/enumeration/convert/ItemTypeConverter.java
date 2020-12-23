package com.itemsale.domain.enumeration.convert;

import com.itemsale.domain.enumeration.ItemType;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;

@Convert
public class ItemTypeConverter implements AttributeConverter<ItemType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(ItemType value) {
        if (value == null) {
            return null;
        }
        return value.getValue();
    }

    @Override
    public ItemType convertToEntityAttribute(Integer value) {
        if (value == null) {
            return null;
        }
        return ItemType.ofValue(value);
    }
}