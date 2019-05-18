package de.uni.stuttgart.ipvs.em.entities.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import de.uni.stuttgart.ipvs.em.auth.UserDetailsImpl;
import de.uni.stuttgart.ipvs.em.entities.dto.EntityCreateDTO;
import de.uni.stuttgart.ipvs.em.entities.dto.EntityEditDTO;
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

    @GetMapping
    public ResultsSet getAllEntities(@AuthenticationPrincipal UserDetailsImpl user) {
        return this.entityService.getAllEntities(user.getId());
    }

    @PostMapping
    public ResponseEntity createEntity(@RequestBody EntityCreateDTO entityCreateDTO,
                                       @AuthenticationPrincipal UserDetailsImpl user) {

        var entityId = entityService.createEntity(entityCreateDTO, user.getId());
        return ResponseEntityUtils
                .getResponseEntityWithMessageKey(CREATED, "Created entity with ID '" + entityId + "'");
    }

    @GetMapping(path = "/{id}")
    public ResultsSet getEntity(@PathVariable("id") String entityId,
                                @AuthenticationPrincipal UserDetailsImpl user) {
        return this.entityService.getEntity(user.getId(), entityId);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity editEntity(@PathVariable("id") String entityId,
                                     @RequestBody EntityEditDTO entityEditDTO,
                                     @AuthenticationPrincipal UserDetailsImpl user) {


        if (!user.getId().equals(entityEditDTO.getOwner()))
            return ResponseEntity.status(FORBIDDEN).build();

        this.entityService.updateEntity(entityEditDTO, entityId);

        return ResponseEntity.status(NO_CONTENT).build();
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteEntity(@PathVariable("id") String entityId,
                             @AuthenticationPrincipal UserDetailsImpl user) {

        this.entityService.deleteEntity(user.getId(), entityId);
    }


    @PostMapping("/search")
    public ResultsSet searchEntitiesUsingTerms(@RequestParam("search_query") String termsText,
                                               @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return this.entityService.getAllEntitiesUsingTermsTextForOwner(userDetails.getId(), termsText);
    }

}
