package de.uni.stuttgart.ipvs.em.entities.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.uni.stuttgart.ipvs.em.entities.service.EntityService;
import de.uni.stuttgart.ipvs.em.response.ResultsSet;

@RestController
@RequestMapping("/api/consumer/entities")
public class ConsumerEntitiesController {

    private final EntityService entityService;

    @Autowired
    public ConsumerEntitiesController(EntityService entityService) {
        this.entityService = entityService;
    }

    @PostMapping("search")
    public ResultsSet searchEntitiesUsingTerms(@RequestParam("search_query") String termsText) {
        return this.entityService.getAllEntitiesUsingTermsText(termsText);
    }

}
