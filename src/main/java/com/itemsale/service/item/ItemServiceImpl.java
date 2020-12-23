package com.itemsale.service.item;

import com.itemsale.domain.Item;
import com.itemsale.domain.enumeration.ItemType;
import com.itemsale.dto.ItemDetailDto;
import com.itemsale.dto.ItemDto;
import com.itemsale.exception.ItemException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

    private MapperFacade mapperFacade;

    @PersistenceContext
    EntityManager entityManager;


    @Override
    @Transactional(readOnly = true)
    public List<ItemDto> getListOfPacks() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Item> itemCriteriaQuery = criteriaBuilder.createQuery(Item.class);
        Root<Item> rootEntry = itemCriteriaQuery.from(Item.class);
        CriteriaQuery<Item> all = itemCriteriaQuery.select(rootEntry);

        Predicate predicateParent = criteriaBuilder.isNull(rootEntry.get("parentId"));
        Predicate predicateOwner = criteriaBuilder.isNotNull(rootEntry.get("owner"));
        Predicate predicateItemType = criteriaBuilder.equal(rootEntry.get("type"), ItemType.ofName("PACK"));

        Predicate predicateForColor = criteriaBuilder.and(predicateParent, predicateOwner, predicateItemType);
        all.where(predicateForColor);
        TypedQuery<Item> allQuery = entityManager.createQuery(all);
        List<Item> itemList = allQuery.getResultList();
        List<ItemDto> itemDtoList = mapperFacade.mapAsList(itemList, ItemDto.class);
        return itemDtoList;
    }


    @Override
    @Transactional(readOnly = true)
    public ItemDetailDto getItemById(Long id) {

        Item response = Optional.of(entityManager.find(Item.class, id)).orElseThrow(() -> new ItemException(HttpStatus.NOT_FOUND, " There is not Item with such id"));
        ItemDetailDto childDto = mapperFacade.map(response, ItemDetailDto.class);
        ItemDetailDto itemDetailDto = getChildren(response,childDto);
        itemDetailDto.setChildren(itemDetailDto);
        return itemDetailDto;
    }

    @Override
    @Transactional
    public ItemDto saveItem(final ItemDto itemDto) {

        final Item item = mapperFacade.map(itemDto, Item.class);
        final Item savedItem = entityManager.merge(item);
        final ItemDto returnItemDto = mapperFacade.map(savedItem, ItemDto.class);
        return returnItemDto;
    }

    @Override
    @Transactional
    public void saleItem(Long id) {

        Item item = entityManager.find(Item.class, id);

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Item> criteriaUpdate = cb.createCriteriaUpdate(Item.class);
        Root<Item> root = criteriaUpdate.from(Item.class);
        criteriaUpdate.set("owner", null);
        criteriaUpdate.set("parent", null);
        criteriaUpdate.where(cb.equal(root.get("id"), id));
        criteriaUpdate.where(cb.equal(root.get("type"), ItemType.ofName("ITEM")));
        //   entityManager.createQuery(criteriaUpdate).executeUpdate();

        CriteriaQuery<Item> itemCriteriaQuery = cb.createQuery(Item.class);
        Root<Item> rootEntry = itemCriteriaQuery.from(Item.class);
        CriteriaQuery<Item> all = itemCriteriaQuery.select(rootEntry);
//        List<Item> Itemnewfh = Stream.concat(Stream.of(item), flattened(item.getParentId()).stream().filter(Objects::nonNull)).collect(toList());

//        for (Item fhs : Itemnewfh) {
//            fhs.setParent(null);
//            entityManager.persist(fhs);
//        }
    }



    public ItemDetailDto getChildren(Item item,ItemDetailDto itemDetailDto) {
        ItemDetailDto dto = itemDetailDto;
        if (itemDetailDto.getChildren() != null) {
            while (dto != null) {
                dto = itemDetailDto.getChildren();
            }


        } else {

        }
        List<Item> persons = entityManager.createQuery("SELECT i FROM Item i WHERE i.parentId IN :parentId").setParameter("parentId", item.getId()).getResultList();
        if (!persons.isEmpty()) {
            Item child = persons.get(0);

            itemDetailDto.setChildren(mapperFacade.map(child, ItemDetailDto.class));
            getChildren(child,itemDetailDto);
        }
        return itemDetailDto;
    }

}
