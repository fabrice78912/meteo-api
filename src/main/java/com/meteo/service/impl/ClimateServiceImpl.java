package com.meteo.service.impl;

import com.meteo.dto.ClimateDto;
import com.meteo.mapper.ClimateMapper;
import com.meteo.model.Climate;
import com.meteo.repo.ClimateRepo;
import com.meteo.service.ClimateService;
import lombok.RequiredArgsConstructor;
import org.example.common.exception.UserNotFoundException;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClimateServiceImpl implements ClimateService {

    private final ClimateRepo climateRepo;
    private final ClimateMapper climateMapper;

    @Override
    public Climate createClimateAndLinkToVille() {
        return null;
    }

    @Override
    public Map<String, List<ClimateDto>> mapVilleListClimateDto() {
        return climateRepo.findAll()
                .stream()
                .collect(Collectors.groupingBy(
                        c -> c.getVille().getName().toLowerCase(),
                        Collectors.mapping(climateMapper::entityToDto, Collectors.toList())
                ));
    }

    @Override
    public List<ClimateDto> getClimateByVille(String villeName) {
        if(!mapVilleListClimateDto().containsKey(villeName.toLowerCase())){
            throw new UserNotFoundException("Not exit");
        }
        List<ClimateDto> climateDtos = mapVilleListClimateDto().get(villeName.toLowerCase()).stream()
                .sorted(Comparator.comparing(ClimateDto::getCreatedAt).reversed())
                .collect(Collectors.toList());
        return CollectionUtils.isEmpty(climateDtos)?new ArrayList<>():climateDtos;
    }

    @Override
    public List<ClimateDto> getClimateByVille(String villeName , Integer page, Integer pageSize) {
        if(!mapVilleListClimateDto().containsKey(villeName.toLowerCase())){
            throw new UserNotFoundException("Not exit");
        }
        List<ClimateDto> climateDtos = mapVilleListClimateDto().get(villeName.toLowerCase())
                .stream()
                .sorted(Comparator.comparing(ClimateDto::getCreatedAt).reversed())
                .skip((long) page * pageSize) // Skip records for pagination
                .limit(pageSize) // Limit the number of records per page
                .collect(Collectors.toList());
        return CollectionUtils.isEmpty(climateDtos)?new ArrayList<>():climateDtos;
    }



    public Page<ClimateDto> getPaginatedAndSortedClimates(String villeName, Integer page, Integer pageSize, String sortField, Sort.Direction sortOrder) {
        // Create a Pageable object for pagination and sorting
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(sortOrder, sortField));

        if(!mapVilleListClimateDto().containsKey(villeName.toLowerCase())){
            throw new UserNotFoundException("Ville "+villeName +" not found ...");
        }

        // Fetch all climates for the specified villeName
        List<ClimateDto> allClimates = mapVilleListClimateDto().get(villeName.toLowerCase());


        // Perform pagination and sorting using the Pageable object
        List<ClimateDto> paginatedAndSortedClimates = allClimates.stream()
                .sorted(Comparator.comparing(ClimateDto::getCreatedAt).reversed())
                .skip((long) pageable.getPageNumber() * pageable.getPageSize())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());

        // Create a Page object with the paginated and sorted climates
        return new PageImpl<>(paginatedAndSortedClimates, pageable, allClimates.size());

    }


    public Map<String, List<Climate>> mapVilleListClimate() {
        return climateRepo.findAll()
                .stream()
                .collect(Collectors.groupingBy(
                        c -> c.getVille().getName().toLowerCase()
                ));
    }


    @Override
    public ClimateDto getLastMeteoByVille(String villeName) {
        return getClimateByVille(villeName).stream()
                .max(Comparator.comparing(ClimateDto::getCreatedAt))
                .orElse(null);
    }
}
