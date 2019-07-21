package de.uni.stuttgart.ipvs.em.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.uni.stuttgart.ipvs.em.response.TermsMeaningResultsSet;
import de.uni.stuttgart.ipvs.em.service.CviService;

@RestController
@RequestMapping("/api/meaning/terms")
public class MeaningTermsController {

    private final CviService cviService;

    @Autowired
    public MeaningTermsController(CviService cviService) {
        this.cviService = cviService;
    }

    @PostMapping
    public TermsMeaningResultsSet searchMeaningOfTermsText_allRoles(@RequestParam("search_query") String termsText) {
        return this.cviService.searchMeaningOfTermsText_allRoles(termsText);
    }

}
