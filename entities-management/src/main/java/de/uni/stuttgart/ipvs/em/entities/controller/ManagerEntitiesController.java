package de.uni.stuttgart.ipvs.em.entities.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import de.uni.stuttgart.ipvs.em.auth.UserDetailsImpl;
import de.uni.stuttgart.ipvs.em.entities.dto.EntityCreateDto;
import de.uni.stuttgart.ipvs.em.entities.dto.EntityEditDto;
import de.uni.stuttgart.ipvs.em.entities.service.EntityService;
import de.uni.stuttgart.ipvs.em.response.ResultsSet;
import de.uni.stuttgart.ipvs.ilv.ResponseEntityUtils;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/manager/entities")
public class ManagerEntitiesController {

    private final EntityService entityService;

    @Autowired
    public ManagerEntitiesController(EntityService entityService) {
        this.entityService = entityService;
    }

    // CREATE: 1
    @PostMapping
    public ResponseEntity createEntity_roleManager(@RequestBody EntityCreateDto entityCreateDto,
                                                   @AuthenticationPrincipal UserDetailsImpl userDetails) {

        String ownerId = userDetails.getId();
        String entityId = entityService.createEntity_roleManager(entityCreateDto, ownerId);

        return ResponseEntityUtils
                .getResponseEntityWithMessageKey(CREATED, "Created entity with ID '" + entityId + "'");
    }

    // READ: 1
    @GetMapping(path = "/{id}")
    @ResponseStatus(OK)
    public ResultsSet getEntity_roleManager(@PathVariable("id") String entityId,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        String ownerId = userDetails.getId();
        return this.entityService.getEntity_roleManager(ownerId, entityId);
    }

    // READ: 0 - n
    @GetMapping
    @ResponseStatus(OK)
    public ResultsSet getAllEntities_roleManager(@AuthenticationPrincipal UserDetailsImpl userDetails) {

        String ownerId = userDetails.getId();
        return this.entityService.getAllEntities_roleManager(ownerId);
    }

    // READ: 0 - n
    @PostMapping("/search")
    @ResponseStatus(OK)
    public ResultsSet searchEntitiesUsingTerms_roleManager(@RequestParam("search_query") String termsText,
                                                           @AuthenticationPrincipal UserDetailsImpl userDetails) {

        String ownerId = userDetails.getId();
        return this.entityService.getAllEntitiesUsingTerms_roleManager(ownerId, termsText);
    }

    // UPDATE: 1
    @PutMapping(path = "/{id}")
    @ResponseStatus(NO_CONTENT)
    public void editEntityRoleManager(@PathVariable("id") String entityId,
                                      @RequestBody EntityEditDto entityEditDto,
                                      @AuthenticationPrincipal UserDetailsImpl userDetails) {

        String ownerId = userDetails.getId();
        this.entityService.updateEntity_roleManager(entityEditDto, ownerId, entityId);
    }

    // DELETE: 1
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteEntity(@PathVariable("id") String entityId,
                             @AuthenticationPrincipal UserDetailsImpl userDetails) {

        String ownerId = userDetails.getId();
        this.entityService.deleteEntity_roleManager(ownerId, entityId);
    }
}
