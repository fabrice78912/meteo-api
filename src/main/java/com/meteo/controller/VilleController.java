package com.meteo.controller;

import com.meteo.dto.ClimateDto;
import com.meteo.model.Ville;
import com.meteo.service.ClimateService;
import com.meteo.service.VilleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/meteo")
public class VilleController {

    private final VilleService villeService;
    private final ClimateService climateService;


    @PostMapping("/add")
    public ResponseEntity<Ville> createVille(@RequestBody Ville ville){
        return new ResponseEntity<>(villeService.createVille(ville), HttpStatus.OK);
    }


    @GetMapping("/climate/list")
    public ResponseEntity<Page<ClimateDto>> getAllClimateByVilleOrderByCreatedDatei(@RequestParam("ville") @Valid() String ville ,
                                                                                    @RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size){
        return new ResponseEntity<>(climateService.getPaginatedAndSortedClimates(ville, page.orElse(0), size.orElse(5) , "createdAt", Sort.Direction.DESC), HttpStatus.OK);
        }

    @GetMapping("/last")
    public ResponseEntity<ClimateDto> getLastMeteoByVille(@RequestParam("ville") @Valid() String ville){
        return new ResponseEntity<>(climateService.getLastMeteoByVille(ville), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String,List<ClimateDto>>> getAllClimateByVilleOrderByCreatedDatei(){
        return new ResponseEntity<>(climateService.mapVilleListClimateDto(), HttpStatus.OK);}


}
