package com.itemsale.service.partypage;

import com.itemsale.domain.PartyPage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Slf4j
@Service
@AllArgsConstructor
public class PartyPageServiceImpl implements PartyPageService {

    @PersistenceContext
    EntityManager entityManager;


    @Override
    @Transactional
    public void createPage() {
        entityManager.persist(new PartyPage());
    }
}
