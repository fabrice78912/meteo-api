package com.meteo.service.impl;

import com.meteo.dto.ClimateDto;
import com.meteo.dto.VilleDto;
import com.meteo.mapper.ClimateMapper;
import com.meteo.model.Climate;
import com.meteo.model.Ville;
import com.meteo.repo.ClimateRepo;
import com.meteo.repo.VilleRepo;
import com.meteo.service.VilleService;
import lombok.RequiredArgsConstructor;
import org.example.common.exception.UserExistException;
import org.example.common.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VilleServiceImpl implements VilleService {

    private final VilleRepo villeRepo;
    private final ClimateRepo climateRepo;
    private final ClimateMapper climateMapper;


    @Override
    public VilleDto getAllClimateByVilleOrderByCreatedDate(String villeName) {

        Ville ville= villeRepo.findByName(villeName);

        if(ville== null){
            throw new UserNotFoundException("Ville not exist");
        }
        Optional<List<Climate>> climateList= climateRepo.findAllByVille_NameOrderByCreatedAtDesc(villeName);
        VilleDto villeDto= new VilleDto();
        if(climateList.isPresent()){
            List<Climate> climates= climateList.get();
            List<ClimateDto> climateDtos= climateMapper.listEntityToListDto(climates);
            villeDto.setClimates(climateDtos);
        }
        return villeDto;
    }

    @Override
    public Ville createVille(Ville villeRequest) {
        Ville ville = villeRepo.findByName(villeRequest.getName());
        if (Objects.isNull(ville)) {
           return villeRepo.save(villeRequest);
        } else {
            throw new UserExistException("Ville already exist");
        }
    }



}
