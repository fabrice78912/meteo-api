package com.meteo.repo;

import com.meteo.model.Climate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClimateRepo extends JpaRepository<Climate, Long> {

    Optional<List<Climate>> findAllByVille_NameOrderByCreatedAtDesc(String villeName);

}
