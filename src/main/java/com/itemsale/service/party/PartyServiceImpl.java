package com.itemsale.service.party;


import com.itemsale.domain.Party;
import com.itemsale.dto.PartyDetailDto;
import com.itemsale.dto.PartyDto;
import com.itemsale.exception.PartyException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class PartyServiceImpl implements PartyService {

    private MapperFacade mapperFacade;

    @PersistenceContext
    EntityManager entityManager;


    @Override
    @Transactional(readOnly = true)
    public List<PartyDto> getListOfParty() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Party> cq = criteriaBuilder.createQuery(Party.class);
        Root<Party> rootEntry = cq.from(Party.class);
        CriteriaQuery<Party> all = cq.select(rootEntry);
        TypedQuery<Party> allQuery = entityManager.createQuery(all);

        List<Party> partyList = allQuery.getResultList();
        List<PartyDto> partyDto = mapperFacade.mapAsList(partyList, PartyDto.class);
        return partyDto;
    }


    @Override
    @Transactional
    public PartyDto saveParty(final PartyDto partyDto) {

        final Party party = mapperFacade.map(partyDto, Party.class);
        final Party savedParty = entityManager.merge(party);
        final PartyDto dto = mapperFacade.map(savedParty, PartyDto.class);
        return dto;
    }


    @Override
    @Transactional(readOnly = true)
    public PartyDetailDto getPartyDetailById(Long id) {
      final  Party party = entityManager.find(Party.class, id);
        if (party == null) {
            throw new PartyException(HttpStatus.NOT_FOUND, "Party not found");
        }
        PartyDetailDto partyDetailDto = mapperFacade.map(party, PartyDetailDto.class);
        return partyDetailDto;
    }
}
