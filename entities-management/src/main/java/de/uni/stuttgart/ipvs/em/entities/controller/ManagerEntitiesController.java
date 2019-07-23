package de.uni.stuttgart.ipvs.em.entities.controller;

import io.swagger.annotations.*;
import springfox.documentation.annotations.ApiIgnore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.http.MediaType;
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
@Api(tags = "Manager Users")
public class ManagerEntitiesController {

    private final EntityService entityService;

    @Autowired
    public ManagerEntitiesController(EntityService entityService) {
        this.entityService = entityService;
    }

    // CREATE: 1
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Create a new entity")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Entity id is returned in the message key. " +
                    "Message Format 'Created entity with ID \'<ENTITY_ID>\' '"),
            @ApiResponse(code = 400, message = ""),
            @ApiResponse(code = 500, message = "")
    })
    public ResponseEntity createEntity_roleManager(@ApiParam("Entity Properties") @RequestBody EntityCreateDto entityCreateDto,
                                                   @ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails) {

        String ownerId = userDetails.getId();
        String entityId = entityService.createEntity_roleManager(entityCreateDto, ownerId);

        return ResponseEntityUtils
                .getResponseEntityWithMessageKey(CREATED, "Created entity with ID '" + entityId + "'");
    }

    // READ: 1
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Get a entity using entity id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "A map with entities properties in the results key"),
            @ApiResponse(code = 500, message = "")
    })
    @ResponseStatus(OK)
    public ResultsSet getEntity_roleManager(@ApiParam("Entity ID") @PathVariable("id") String entityId,
                                            @ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails) {

        String ownerId = userDetails.getId();
        return this.entityService.getEntity_roleManager(ownerId, entityId);
    }

    // READ: 0 - n
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Get all entities")
    @ApiResponses({
            @ApiResponse(code = 200, message = "A array list with each element a map with entities properties in the results key"),
            @ApiResponse(code = 500, message = "")
    })
    @ResponseStatus(OK)
    public ResultsSet getAllEntities_roleManager(@ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails) {

        String ownerId = userDetails.getId();
        return this.entityService.getAllEntities_roleManager(ownerId);
    }

    // READ: 0 - n
    @PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Search entities using terms")
    @ApiResponses({
            @ApiResponse(code = 200, message = "A array list with each element a map with entities properties in the results key"),
            @ApiResponse(code = 500, message = "")
    })
    @ResponseStatus(OK)
    public ResultsSet searchEntitiesUsingTerms_roleManager(@ApiParam("search terms") @RequestParam("search_query") String termsText,
                                                           @ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails) {

        String ownerId = userDetails.getId();
        return this.entityService.getAllEntitiesUsingTerms_roleManager(ownerId, termsText);
    }

    // UPDATE: 1
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Update entity properties using entity ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Entity is updated with new properties"),
            @ApiResponse(code = 400, message = ""),
            @ApiResponse(code = 500, message = "")
    })
    @ResponseStatus(NO_CONTENT)
    public void editEntityRoleManager(@ApiParam("Entity ID") @PathVariable("id") String entityId,
                                      @ApiParam("Entity Properties") @RequestBody EntityEditDto entityEditDto,
                                      @ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails) {

        String ownerId = userDetails.getId();
        this.entityService.updateEntity_roleManager(entityEditDto, ownerId, entityId);
    }

    // DELETE: 1
    @DeleteMapping(path = "/{id}")
    @ApiOperation("Delete entity using entity ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Entity with all its properties is deleted"),
            @ApiResponse(code = 500, message = "")
    })
    @ResponseStatus(NO_CONTENT)
    public void deleteEntity(@ApiParam("Entity ID") @PathVariable("id") String entityId,
                             @ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails) {

        String ownerId = userDetails.getId();
        this.entityService.deleteEntity_roleManager(ownerId, entityId);
    }
}
