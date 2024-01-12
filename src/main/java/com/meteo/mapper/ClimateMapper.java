package com.meteo.mapper;


import com.meteo.dto.ClimateDto;
import com.meteo.model.Climate;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClimateMapper {

    ClimateDto entityToDto(Climate entity);

    List<ClimateDto> listEntityToListDto(List<Climate> entities);


    Climate dtoToEntity(ClimateDto dto);

    List<Climate> listDtoToEntities(List<ClimateDto> listDto);
}
