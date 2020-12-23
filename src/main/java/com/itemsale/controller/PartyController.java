package com.itemsale.controller;


import com.itemsale.dto.PartyDetailDto;
import com.itemsale.dto.PartyDto;
import com.itemsale.service.party.PartyService;
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
public class PartyController {

    @Autowired
    private PartyService partyService;



    @GetMapping("/party")
    @ApiOperation(value = "список Party")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public ResponseEntity<List<PartyDto>> listOfPacks() {
        List<PartyDto> partyList = partyService.getListOfParty();
        return new ResponseEntity<List<PartyDto>>(partyList, HttpStatus.OK);
    }


    @PostMapping("/party")
    @ApiOperation(value = "Создать party")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = String.class),
            @ApiResponse(code = 500, message = "Internal server error")})
    public ResponseEntity<PartyDto> createParty(@Valid @RequestBody PartyDto party) {
        PartyDto partyDto = partyService.saveParty(party);
        return new ResponseEntity<PartyDto>(partyDto, HttpStatus.CREATED);

    }



    @GetMapping("/party/{id}")
    @ApiOperation(value = "Информация по Party")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public ResponseEntity<PartyDetailDto> getPartyById(@PathVariable(value = "id") Long id) {

        PartyDetailDto partyDetailDto  = partyService.getPartyDetailById(id);
        return new ResponseEntity<PartyDetailDto>(partyDetailDto, HttpStatus.OK);
    }
}