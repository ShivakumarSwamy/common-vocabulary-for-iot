package de.uni.stuttgart.ipvs.em.controller;

import io.swagger.annotations.*;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
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
@Api(tags = "Manager Users")
public class ManagerComponentTypesController {

    private final CviService cviService;

    @Autowired
    public ManagerComponentTypesController(CviService cviService) {
        this.cviService = cviService;
    }

    // CREATE: 1
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Create a component type")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Component Type search id is returned in the message key. " +
                    "Message Format 'Component Type created with search id: \'<SEARCH_ID>\' '"),
            @ApiResponse(code = 400, message = ""),
            @ApiResponse(code = 500, message = "")
    })
    public ResponseEntity createComponentType_roleManager(@ApiParam("Component Type Properties") @RequestBody ComponentTypeCreateDto ctDTO) {
        var searchId = this.cviService.createComponentType_roleManagerAdmin(ctDTO);

        return ResponseEntityUtils.getResponseEntityWithMessageKey(
                CREATED, "Component Type created with search id: '" + searchId + "'");
    }

    // READ: 0 - n
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Get all component types")
    @ApiResponses({
            @ApiResponse(code = 200, message = "A array list with each element a map with component types properties in the results key"),
            @ApiResponse(code = 500, message = "")
    })
    @ResponseStatus(OK)
    public ResultsSet getAllComponentTypes_roleManager() {
        return this.cviService.getAllComponentTypes_roleManagerAdmin();
    }

}
