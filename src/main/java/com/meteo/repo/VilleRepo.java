package com.meteo.repo;

import com.meteo.model.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VilleRepo extends JpaRepository<Ville, Long> {

    Ville findByName(String ville);
}
