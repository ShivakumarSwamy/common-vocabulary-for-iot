package de.uni.stuttgart.ipvs.em.entities.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import de.uni.stuttgart.ipvs.em.entities.dto.EntityEditDTO;
import de.uni.stuttgart.ipvs.em.entities.service.EntityService;
import de.uni.stuttgart.ipvs.em.response.ResultsSet;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/admin/entities")
public class AdminEntitiesController {

    private final EntityService entityService;

    @Autowired
    public AdminEntitiesController(EntityService entityService) {
        this.entityService = entityService;
    }

    @GetMapping
    public ResultsSet getAllEntities() {
        return this.entityService.getAllEntities();
    }

    @GetMapping(path = "/{id}")
    public ResultsSet getEntity(@PathVariable("id") String entityId) {
        return this.entityService.getEntityForAdmin(entityId);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(NO_CONTENT)
    public void editEntity(@PathVariable("id") String entityId,
                          @RequestBody EntityEditDTO entityEditDTO) {
        this.entityService.updateEntity(entityEditDTO, entityId);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteEntity(@PathVariable("id") String entityId) {
        this.entityService.deleteEntity(entityId);
    }

}
