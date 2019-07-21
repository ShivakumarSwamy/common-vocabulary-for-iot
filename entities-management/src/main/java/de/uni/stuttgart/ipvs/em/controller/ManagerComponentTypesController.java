package de.uni.stuttgart.ipvs.em.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import de.uni.stuttgart.ipvs.em.dto.ComponentTypeCreateDto;
import de.uni.stuttgart.ipvs.em.response.ResultsSet;
import de.uni.stuttgart.ipvs.em.service.CviService;
import de.uni.stuttgart.ipvs.ilv.ResponseEntityUtils;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/manager/component-types")
public class ManagerComponentTypesController {

    private final CviService cviService;

    @Autowired
    public ManagerComponentTypesController(CviService cviService) {
        this.cviService = cviService;
    }

    // CREATE: 1
    @PostMapping
    public ResponseEntity createComponentType_roleManager(@RequestBody ComponentTypeCreateDto ctDTO) {
        var searchId = this.cviService.createComponentType_roleManagerAdmin(ctDTO);

        return ResponseEntityUtils.getResponseEntityWithMessageKey(
                CREATED, "Component Type created with search id: '" + searchId + "'");
    }

    // READ: 0 - n
    @GetMapping
    @ResponseStatus(OK)
    public ResultsSet getAllComponentTypes_roleManager() {
        return this.cviService.getAllComponentTypes_roleManagerAdmin();
    }

}
