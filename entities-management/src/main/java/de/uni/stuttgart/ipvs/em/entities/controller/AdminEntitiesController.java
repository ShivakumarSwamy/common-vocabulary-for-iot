package de.uni.stuttgart.ipvs.em.entities.controller;

import io.swagger.annotations.*;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import de.uni.stuttgart.ipvs.em.entities.dto.EntityEditDto;
import de.uni.stuttgart.ipvs.em.entities.service.EntityService;
import de.uni.stuttgart.ipvs.em.response.ResultsSet;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/admin/entities")
@Api(tags = "Admin Users")
public class AdminEntitiesController {

    private final EntityService entityService;

    @Autowired
    public AdminEntitiesController(EntityService entityService) {
        this.entityService = entityService;
    }

    // READ: 1
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Get a entity using entity id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "A map with entities properties in the results key"),
            @ApiResponse(code = 500, message = "")
    })
    @ResponseStatus(OK)
    public ResultsSet getEntity_roleAdmin(@ApiParam("Entity ID") @PathVariable("id") String entityId) {
        return this.entityService.getEntity_roleAdmin(entityId);
    }

    // READ: 0 - n
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Get all entities")
    @ApiResponses({
            @ApiResponse(code = 200, message = "A array list with each element a map with entities properties in the results key"),
            @ApiResponse(code = 500, message = "")
    })
    @ResponseStatus(OK)
    public ResultsSet getAllEntities() {
        return this.entityService.getAllEntities_roleAdmin();
    }

    // READ: 0 - n
    @PostMapping(value = "search", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Search entities using terms")
    @ApiResponses({
            @ApiResponse(code = 200, message = "A array list with each element a map with entities properties in the results key"),
            @ApiResponse(code = 500, message = "")
    })
    @ResponseStatus(OK)
    public ResultsSet searchEntitiesUsingTerms(@ApiParam("Search Terms") @RequestParam("search_query") String termsText) {
        return this.entityService.getAllEntitiesUsingTerms_rolesConsumerAdmin(termsText);
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
    public void editEntity(@ApiParam("Entity ID") @PathVariable("id") String entityId,
                           @ApiParam("Entity Properties") @RequestBody EntityEditDto entityEditDto) {
        this.entityService.updateEntity_roleAdmin(entityEditDto, entityId);
    }

    // DELETE: 1
    @DeleteMapping(path = "/{id}")
    @ApiOperation("Delete entity using entity ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Entity with all its properties is deleted"),
            @ApiResponse(code = 500, message = "")
    })
    @ResponseStatus(NO_CONTENT)
    public void deleteEntity(@ApiParam("Entity ID") @PathVariable("id") String entityId) {
        this.entityService.deleteEntity_roleAdmin(entityId);
    }

}
