package de.uni.stuttgart.ipvs.em.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import de.uni.stuttgart.ipvs.em.dto.ComponentTypeCreateDTO;
import de.uni.stuttgart.ipvs.em.form.ComponentTypeFormControlErrorException;
import de.uni.stuttgart.ipvs.em.response.ResultsSet;
import de.uni.stuttgart.ipvs.em.service.CviService;
import de.uni.stuttgart.ipvs.ilv.ResponseEntityUtils;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/manager/component-types")
public class ManagerComponentTypesController {

    private final CviService cviService;

    @Autowired
    public ManagerComponentTypesController(CviService cviService) {
        this.cviService = cviService;
    }

    @PostMapping
    public ResponseEntity createComponentType(@RequestBody ComponentTypeCreateDTO ctDTO) {
        var searchId = this.cviService.createComponentType(ctDTO);

        return ResponseEntityUtils.getResponseEntityWithMessageKey(
                CREATED, "Component Type created with search id: '" + searchId + "'");
    }

    @GetMapping
    public ResultsSet getAllComponentTypes() {
        return this.cviService.getAllComponentTypes();
    }

    @ExceptionHandler
    public ResponseEntity handlerComponentTypeFormControlErrorException(
            ComponentTypeFormControlErrorException failedFormControl) {
        return ResponseEntityUtils.getResponseEntityWithMessageKey(BAD_REQUEST, failedFormControl.getMessage());
    }

}
