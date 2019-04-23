package de.uni.stuttgart.ipvs.tm.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import de.uni.stuttgart.ipvs.ilv.ResponseEntityUtils;
import de.uni.stuttgart.ipvs.tm.dto.HardwareTypeCreateDTO;
import de.uni.stuttgart.ipvs.tm.form.HardwareTypeFormControlErrorException;
import de.uni.stuttgart.ipvs.tm.response.ResultsSet;
import de.uni.stuttgart.ipvs.tm.service.CviService;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/manager/hardware-types")
public class ManagerHardwareTypeController {

    private final CviService cviService;

    @Autowired
    public ManagerHardwareTypeController(CviService cviService) {
        this.cviService = cviService;
    }

    @PostMapping
    public ResponseEntity createHardwareType(@RequestBody HardwareTypeCreateDTO htDTO) {
        var searchId = this.cviService.createHardwareType(htDTO);

        return ResponseEntityUtils.getResponseEntityWithMessageKey(
                CREATED, "Hardware Type created with search id: '" + searchId + "'");
    }

    @GetMapping
    public ResultsSet getAllHardwareTypes() {
        return this.cviService.getAllHardwareTypes();
    }

    @ExceptionHandler
    public ResponseEntity handlerHardwareTypeFormControlErrorException(
            HardwareTypeFormControlErrorException failedFormControl) {
        return ResponseEntityUtils.getResponseEntityWithMessageKey(BAD_REQUEST, failedFormControl.getMessage());
    }

}
