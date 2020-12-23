package com.itemsale.config;

import com.itemsale.domain.Item;
import com.itemsale.domain.Party;
import com.itemsale.domain.enumeration.ItemType;
import com.itemsale.dto.ItemDetailDto;
import com.itemsale.dto.ItemDto;
import com.itemsale.dto.PartyDto;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.ConfigurableMapper;
import net.rakugakibox.spring.boot.orika.OrikaMapperFactoryConfigurer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.itemsale"})
@RequiredArgsConstructor
public class MapperConfig extends ConfigurableMapper implements OrikaMapperFactoryConfigurer {

    @Override
    public void configure(MapperFactory mapperFactory) {
        mapperFactory.classMap(Item.class, ItemDto.class)
                .field("serial", "serialNumber")
                .customize(new CustomMapper<Item, ItemDto>() {

                    @Override
                    public void mapAtoB(Item a, ItemDto b, MappingContext context) {
                        b.setType(a.getType().getName());
                        b.setOwner(new PartyDto(a.getOwner().getId(), a.getOwner().getName()));
                    }

                    @Override
                    public void mapBtoA(ItemDto itemDto, Item item, MappingContext context) {
                        item.setType(ItemType.ofName(itemDto.getType()));
                        item.setOwner(new Party(itemDto.getOwner().getId(), itemDto.getOwner().getName()));
                    }
                })
                .byDefault()
                .register();


        mapperFactory.classMap(Item.class, ItemDetailDto.class)
                .field("serial", "serialNumber")
                .customize(new CustomMapper<Item, ItemDetailDto>() {

                    @Override
                    public void mapAtoB(Item a, ItemDetailDto b, MappingContext context) {
                        b.setType(a.getType().getName());
                        b.setOwner(new PartyDto(a.getOwner().getId(), a.getOwner().getName()));
                    }

                    @Override
                    public void mapBtoA(ItemDetailDto ItemDetailDto, Item item, MappingContext context) {
                        item.setType(ItemType.ofName(ItemDetailDto.getType()));
                        item.setOwner(new Party(ItemDetailDto.getOwner().getId(), ItemDetailDto.getOwner().getName()));
                    }
                })
                .byDefault()
                .register();
    }
}
