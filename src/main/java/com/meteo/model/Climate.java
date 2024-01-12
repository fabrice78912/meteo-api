package com.meteo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class Climate {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double temperature;
    private double humidity;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "climate_id", nullable = false)
    @JsonIgnore
    private Ville ville;


}
