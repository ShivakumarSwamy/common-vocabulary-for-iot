package de.uni.stuttgart.ipvs.em.entities.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import de.uni.stuttgart.ipvs.em.entities.dto.EntityEditDto;
import de.uni.stuttgart.ipvs.em.entities.service.EntityService;
import de.uni.stuttgart.ipvs.em.response.ResultsSet;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/admin/entities")
public class AdminEntitiesController {

    private final EntityService entityService;

    @Autowired
    public AdminEntitiesController(EntityService entityService) {
        this.entityService = entityService;
    }

    // READ: 1
    @GetMapping(path = "/{id}")
    @ResponseStatus(OK)
    public ResultsSet getEntity_roleAdmin(@PathVariable("id") String entityId) {
        return this.entityService.getEntity_roleAdmin(entityId);
    }

    // READ: 0 - n
    @GetMapping
    @ResponseStatus(OK)
    public ResultsSet getAllEntities() {
        return this.entityService.getAllEntities_roleAdmin();
    }

    // READ: 0 - n
    @PostMapping("search")
    @ResponseStatus(OK)
    public ResultsSet searchEntitiesUsingTerms(@RequestParam("search_query") String termsText) {
        return this.entityService.getAllEntitiesUsingTerms_rolesConsumerAdmin(termsText);
    }

    // UPDATE: 1
    @PutMapping(path = "/{id}")
    @ResponseStatus(NO_CONTENT)
    public void editEntity(@PathVariable("id") String entityId,
                           @RequestBody EntityEditDto entityEditDto) {
        this.entityService.updateEntity_roleAdmin(entityEditDto, entityId);
    }

    // DELETE: 1
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteEntity(@PathVariable("id") String entityId) {
        this.entityService.deleteEntity_roleAdmin(entityId);
    }

}
