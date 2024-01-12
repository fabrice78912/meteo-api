package com.meteo.job;

import com.meteo.model.Climate;
import com.meteo.model.Ville;
import com.meteo.repo.ClimateRepo;
import com.meteo.repo.VilleRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.common.exception.UserExistException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
@Slf4j
public class SaveClimateJob {

    private final ClimateRepo climateRepo;
    private final VilleRepo villeRepo;


    // Create an instance of Random class
    private Random random;

    {
        try {
            random = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }


    @Scheduled(cron = "0 */1 * * * *", zone = "America/Montreal") // Run every 1 minutes
    //@Scheduled(fixedDelay = 60000)
    public void saveToDatabase() {
        // Your database saving logic goes here
        log.info("Saving data to the database...");
        Climate climate = new Climate();
        climate.setTemperature(getRandomDouble(-20, 40));
        climate.setHumidity(getRandomDouble(0, 100));
        climate.setCreatedAt(LocalDateTime.now());

        List<Ville> villes = villeRepo.findAll();
        //assign to a ville
        climate.setVille(getRandomCity(villes));
        log.info("Data save :  {} ", climate.getTemperature());
        climateRepo.save(climate);
    }


    private double getRandomDouble(double min, double max) {
        // Generate a random double value within the specified range
        return min + (max - min) * random.nextDouble();
    }


    private Ville getRandomCity(List<Ville> cityList) {
        // Check if the list is not empty
        if (cityList.isEmpty()) {
            throw new UserExistException("The city list is empty.");
        }
        // Generate a random index within the range of the list size
        int randomIndex = random.nextInt(cityList.size());

        // Retrieve the City object at the random index
        return cityList.get(randomIndex);
    }

}
