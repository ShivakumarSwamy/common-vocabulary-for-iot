package de.uni.stuttgart.ipvs.em.entities.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import de.uni.stuttgart.ipvs.em.entities.service.EntityService;
import de.uni.stuttgart.ipvs.em.response.ResultsSet;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/consumer/entities")
public class ConsumerEntitiesController {

    private final EntityService entityService;

    @Autowired
    public ConsumerEntitiesController(EntityService entityService) {
        this.entityService = entityService;
    }

    @PostMapping("search")
    @ResponseStatus(OK)
    public ResultsSet searchEntitiesUsingTerms(@RequestParam("search_query") String termsText) {
        return this.entityService.getAllEntitiesUsingTerms_rolesConsumerAdmin(termsText);
    }

}
