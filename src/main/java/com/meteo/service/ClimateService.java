package com.meteo.service;

import com.meteo.dto.ClimateDto;
import com.meteo.model.Climate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;

public interface ClimateService {
    Climate createClimateAndLinkToVille();

    Map<String, List<ClimateDto>> mapVilleListClimateDto();
    List<ClimateDto> getClimateByVille(String villeName);

    ClimateDto getLastMeteoByVille(String ville);

    List<ClimateDto> getClimateByVille(String villeName , Integer page, Integer pageSize);

    Page<ClimateDto> getPaginatedAndSortedClimates(String villeName, Integer page, Integer pageSize, String sortField, Sort.Direction sortOrder);

    }
