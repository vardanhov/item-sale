package com.itemsale.controller;


import com.itemsale.dto.ItemDetailDto;
import com.itemsale.dto.ItemDto;
import com.itemsale.service.item.ItemService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class ItemController {

    @Autowired
    private ItemService itemService;


    @GetMapping("/item")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @ApiOperation(value = "список упаковок, возвращать только те,у которых parentId = null и type=PACK и owner!=null")
    public ResponseEntity<List<ItemDto>> listOfPacks() {
        List<ItemDto> itemList = itemService.getListOfPacks();
        return new ResponseEntity<List<ItemDto>>(itemList, HttpStatus.OK);
    }


    @PostMapping("/item")
    @ApiOperation(value = "Создать Item")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = String.class),
            @ApiResponse(code = 500, message = "Internal server error")})
    public ResponseEntity<ItemDto> createItem(@Valid @RequestBody ItemDto itemDto) {
        ItemDto resultItemDto = itemService.saveItem(itemDto);
        return new ResponseEntity<ItemDto>(resultItemDto, HttpStatus.CREATED);
    }



    @GetMapping("/item/{id}")
    @ApiOperation(value = "Информация по item")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public ResponseEntity<ItemDetailDto> getItemById(@PathVariable(value = "id") Long id) {

        ItemDetailDto itemDetailDto = itemService.getItemById(id);
        return new ResponseEntity<ItemDetailDto>(itemDetailDto, HttpStatus.OK);

    }

    @PostMapping("/item/{id}/sale")
    @ApiOperation(value = "Продажа item. Означает, что необходимо в поле ownerid поставить значение null и item исключается из всех вышестояших item - parent_id. Продавать можно только item.type=item. После продажи необходимо проверить если этот Item был последний в агрегации вышестоящего уровня (parent, parentId). Если был последним, то необходимо для вышестоящего item проставть owner=null, parentId=null и так до самого верха иерархии. + необходимо скорректировать все childrenCount по иерархии.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public void saleItem(@PathVariable(value = "id") Long id) {
        itemService.saleItem(id);
    }
}
