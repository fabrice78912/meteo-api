package com.meteo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Ville {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "The name field cannot be blank")
    private String name;

    @OneToMany(mappedBy = "ville", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //@JsonIgnore
    private List<Climate> climates = new ArrayList<>();
}
