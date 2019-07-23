package de.uni.stuttgart.ipvs.em.entities.controller;

import io.swagger.annotations.*;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import de.uni.stuttgart.ipvs.em.entities.service.EntityService;
import de.uni.stuttgart.ipvs.em.response.ResultsSet;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/consumer/entities")
@Api(tags = "Consumer Users")
public class ConsumerEntitiesController {

    private final EntityService entityService;

    @Autowired
    public ConsumerEntitiesController(EntityService entityService) {
        this.entityService = entityService;
    }

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

}
