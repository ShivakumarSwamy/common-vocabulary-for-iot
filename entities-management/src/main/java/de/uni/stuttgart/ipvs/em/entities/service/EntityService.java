package de.uni.stuttgart.ipvs.em.entities.service;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import de.uni.stuttgart.ipvs.em.entities.dto.EntityCreateDTO;
import de.uni.stuttgart.ipvs.em.entities.dto.EntityEditDTO;
import de.uni.stuttgart.ipvs.em.entities.form.EntityFormModelValidation;
import de.uni.stuttgart.ipvs.em.entities.persistence.EntityRepository;
import de.uni.stuttgart.ipvs.em.response.ResultsSet;

import java.util.*;

@Service
@Slf4j
public class EntityService {

    private final EntityRepository entityRepository;
    private final EntityFormModelValidation eFMV;

    @Autowired
    public EntityService(EntityRepository entityRepository,
                         EntityFormModelValidation eFMV) {

        this.entityRepository = entityRepository;
        this.eFMV = eFMV;
    }

    public ResultsSet getAllEntities(String ownerId) {
        return new ResultsSet<>(this.entityRepository.findAllEntities(ownerId));
    }

    public ResultsSet getAllEntities() {
        return new ResultsSet<>(this.entityRepository.findAllEntities());
    }

    public ResultsSet getEntity(String ownerId, String entityId) {
        return new ResultsSet<>(this.entityRepository.findEntity(ownerId, entityId));
    }

    public ResultsSet getEntityForAdmin(String entityId) {
        return new ResultsSet<>(this.entityRepository.findEntity(entityId));
    }


    public ResultsSet getAllEntitiesUsingTermsText(String termsText) {
        if (!StringUtils.hasLength(termsText)) return ResultsSet.EMPTY;
        return new ResultsSet<>(this.entityRepository.findAllEntities(termsTextToTerms(termsText)));
    }

    public ResultsSet getAllEntitiesUsingTermsTextForOwner(String ownerId, String termsText) {

        if (!StringUtils.hasLength(termsText)) return ResultsSet.EMPTY;
        return new ResultsSet<>(this.entityRepository.findAllEntities(ownerId, termsTextToTerms(termsText)));
    }

    public String createEntity(EntityCreateDTO entityCreateDTO, String ownerID) {

        this.eFMV.validate(entityCreateDTO);

        var entity = Entity.buildFromEntityDTO(entityCreateDTO, ownerID);
        entityRepository.save(entity);
        return entity.getId();
    }

    public void updateEntity(EntityEditDTO entityEditDTO, String entityId) {
        this.eFMV.validate(entityEditDTO);

        var entity = Entity.buildFromEntityDTO(entityEditDTO, entityId);
        this.entityRepository.update(entity);
    }

    public void deleteEntity(String id) {
        this.entityRepository.delete(id);
    }

    public void deleteEntity(String ownerID, String id) {
        this.entityRepository.delete(ownerID, id);
    }

    private static Collection<String> termsTextToTerms(String termsText) {
        String[] terms = termsText.strip()
                .toLowerCase().split("\\s+");

        List<String> finalTerms = new ArrayList<>(Arrays.asList(terms));
        finalTerms.add("entity");

        return new HashSet<>(finalTerms);
    }

}

