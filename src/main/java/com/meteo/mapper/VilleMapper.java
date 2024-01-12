package com.meteo.mapper;

import com.meteo.dto.VilleDto;
import com.meteo.model.Ville;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VilleMapper {


    VilleDto entityToDto(Ville entity);

    List<VilleDto> listEntityToListDto(List<Ville> entities);


    Ville dtoToEntity(VilleDto dto);

    List<Ville> listDtoToEntities(List<VilleDto> listDto);
}
