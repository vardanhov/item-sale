package com.itemsale.service.party;

import com.itemsale.dto.PartyDetailDto;
import com.itemsale.dto.PartyDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PartyService {
     List<PartyDto> getListOfParty();

    PartyDto saveParty(PartyDto partyDto);

     PartyDetailDto getPartyDetailById (Long id);
}
