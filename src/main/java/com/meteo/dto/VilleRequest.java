package com.meteo.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VilleRequest {
    private String name;
}
