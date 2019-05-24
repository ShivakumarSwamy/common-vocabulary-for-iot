package de.uni.stuttgart.ipvs.em.entities.service;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import de.uni.stuttgart.ipvs.em.entities.dto.EntityCreateDto;
import de.uni.stuttgart.ipvs.em.entities.dto.EntityEditDto;
import de.uni.stuttgart.ipvs.em.entities.form.EntityFormModelValidation;
import de.uni.stuttgart.ipvs.em.entities.persistence.EntityRepository;
import de.uni.stuttgart.ipvs.em.response.ResultsSet;

import java.util.*;

import static org.springframework.http.HttpStatus.FORBIDDEN;

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

    // MANAGER - CREATE
    public String createEntity_roleManager(EntityCreateDto entityCreateDTO, String ownerID) {

        this.eFMV.validate(entityCreateDTO);

        var entity = Entity.buildFromEntityDTO(entityCreateDTO, ownerID);
        this.entityRepository.save(entity);

        return entity.getId();
    }

    // MANAGER - READ
    public ResultsSet getEntity_roleManager(String ownerId, String entityId) {
        return new ResultsSet<>(this.entityRepository.findEntity(ownerId, entityId));
    }

    // MANAGER - READ
    public ResultsSet getAllEntities_roleManager(String ownerId) {
        return new ResultsSet<>(this.entityRepository.findAllEntities(ownerId));
    }

    // MANAGER - READ
    public ResultsSet getAllEntitiesUsingTerms_roleManager(String ownerId, String termsText) {

        if (!StringUtils.hasLength(termsText)) return ResultsSet.EMPTY;
        return new ResultsSet<>(this.entityRepository.findAllEntities(ownerId, termsTextToTerms(termsText)));
    }

    // MANAGER - UPDATE
    public void updateEntity_roleManager(EntityEditDto entityEditDto, String ownerId, String entityId) {

        String entityEditOwnerId = entityEditDto.getOwner();
        if (isOwnerIdNotEqualToEntityEditDtoId(entityEditOwnerId, ownerId))
            throw new EntityServiceException(FORBIDDEN);

        this.updateEntity_roleAdmin(entityEditDto, entityId);
    }

    // MANAGER - DELETE
    public void deleteEntity_roleManager(String ownerId, String entityId) {
        this.entityRepository.delete(ownerId, entityId);
    }

    // ADMIN - READ
    public ResultsSet getEntity_roleAdmin(String entityId) {
        return new ResultsSet<>(this.entityRepository.findEntity(entityId));
    }

    // ADMIN - READ
    public ResultsSet getAllEntities_roleAdmin() {
        return new ResultsSet<>(this.entityRepository.findAllEntities());
    }

    // ADMIN - UPDATE
    public void updateEntity_roleAdmin(EntityEditDto entityEditDto, String entityId) {

        this.eFMV.validate(entityEditDto);

        var entity = Entity.buildFromEntityDTO(entityEditDto, entityId);
        this.entityRepository.update(entity);
    }

    // ADMIN - DELETE
    public void deleteEntity_roleAdmin(String id) {
        this.entityRepository.delete(id);
    }

    // ADMIN, CONSUMER - READ
    public ResultsSet getAllEntitiesUsingTerms_rolesConsumerAdmin(String termsText) {

        if (!StringUtils.hasLength(termsText)) return ResultsSet.EMPTY;
        return new ResultsSet<>(this.entityRepository.findAllEntities(termsTextToTerms(termsText)));
    }

    private static Collection<String> termsTextToTerms(String termsText) {

        String[] terms = termsText.strip().toLowerCase().split("\\s+");

        List<String> finalTerms = new ArrayList<>(Arrays.asList(terms));
        finalTerms.add("entity");

        return new HashSet<>(finalTerms);
    }

    private static boolean isOwnerIdNotEqualToEntityEditDtoId(String entityEditOwnerId, String ownerId) {
        return entityEditOwnerId == null || !entityEditOwnerId.equals(ownerId);
    }
}

