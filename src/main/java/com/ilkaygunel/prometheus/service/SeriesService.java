package com.ilkaygunel.prometheus.service;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ilkay.gunel
 */
@Service
public class SeriesService {

    @Autowired
    CollectorRegistry registry;

    Counter characterNameCounter;

    Map<String, String> seriesAndCharactersMap = new HashMap<String, String>();

    @PostConstruct
    public void init() {
        seriesAndCharactersMap = new HashMap<>();
        seriesAndCharactersMap.put("Leslie_Knope", "Parks And Recreation");
        seriesAndCharactersMap.put("Michael_Scott", "The Office");
        seriesAndCharactersMap.put("Joey_Tribbiani", "Friends");
        seriesAndCharactersMap.put("Sheldon_Cooper", "The Big Bang Theory");

        characterNameCounter = Counter.build()
                .name("character_name_count")
                .help("Number of character name request")
                .labelNames("character_name")
                .register(registry);
    }

    public String getSeriesNameOfCharacter(String characterName) {
        if (seriesAndCharactersMap.entrySet().stream().anyMatch(entry -> entry.getKey().equals(characterName))) {
            characterNameCounter.labels(characterName).inc();
            return seriesAndCharactersMap.entrySet().stream().filter(entry -> entry.getKey().equals(characterName)).findAny().get().getValue();
        } else {
            characterNameCounter.labels("NOT_FOUND").inc();
            return "NOT_FOUND";
        }
    }

}
