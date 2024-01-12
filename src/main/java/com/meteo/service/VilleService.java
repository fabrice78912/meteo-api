package com.meteo.service;

import com.meteo.dto.VilleDto;
import com.meteo.model.Ville;

public interface VilleService {


    VilleDto getAllClimateByVilleOrderByCreatedDate(String villeName);

    Ville createVille(Ville villeRequest);
}
