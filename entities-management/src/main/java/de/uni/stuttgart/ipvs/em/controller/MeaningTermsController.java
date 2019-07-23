package de.uni.stuttgart.ipvs.em.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.uni.stuttgart.ipvs.em.response.TermsMeaningResultsSet;
import de.uni.stuttgart.ipvs.em.service.CviService;

@RestController
@RequestMapping("/api/meaning/terms")
@Api(tags = "All Users")
public class MeaningTermsController {

    private final CviService cviService;

    @Autowired
    public MeaningTermsController(CviService cviService) {
        this.cviService = cviService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "A JSON Object with two keys(exactResults[exact term match], relatedResults[related terms of search terms]), " +
                    "having a array list as value. Each element in array is a SearchItemDetails(searchId, label and comment)"),
            @ApiResponse(code = 500, message = "")
    })
    public TermsMeaningResultsSet searchMeaningOfTermsText_allRoles(@ApiParam("Search Terms") @RequestParam("search_query") String termsText) {
        return this.cviService.searchMeaningOfTermsText_allRoles(termsText);
    }

}
