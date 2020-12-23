package com.itemsale.controller;


import com.itemsale.service.partypage.PartyPageService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PartyPageController {

    @Autowired
    private PartyPageService partyPageService;


    @GetMapping("/partyPage/open")
    @ApiOperation(value = "Нажатие кнопки сохранено")
    public void createPartyPage(){
        partyPageService.createPage();
    }
}
