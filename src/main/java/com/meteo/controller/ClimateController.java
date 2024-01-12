package com.meteo.controller;

import com.meteo.model.Climate;
import com.meteo.service.ClimateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ClimateController {

    private final ClimateService climateService;

    @PostMapping
    public ResponseEntity<Climate> createClimateAndLinkToVille(){
        return new ResponseEntity<>(climateService.createClimateAndLinkToVille(), HttpStatus.OK);
    }


}
