package com.ifi.tp.trainers.service;

import com.ifi.tp.pokemonTypes.service.PokemonService;
import com.ifi.tp.trainers.bo.Pokemon;
import com.ifi.tp.trainers.bo.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class TrainerServiceImpl implements TrainerService {

   private PokemonService pokemonService;
   private String trainerServiceUrl;
   private RestTemplate restTemplate;

   @Override
    public List<Trainer> getAllTrainers() {
       Trainer[] trainers = restTemplate.getForObject(trainerServiceUrl + "/trainers", Trainer[].class);
       return Arrays.asList(trainers);
   }

   @Override
    public Trainer getTrainer(String name) {
       Trainer trainer = restTemplate.getForObject(trainerServiceUrl + "/trainers/{name}", Trainer.class, name);
       for(Pokemon p : trainer.getTeam()) {
           p.setPokemonType(this.pokemonService.getPokemonType(p.getPokemonNumber()));
       }
       return trainer;
   }

   @Autowired
    public void setPokemonService(PokemonService pokemonService) {
       this.pokemonService = pokemonService;
   }

   @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
       this.restTemplate = restTemplate;
   }

   @Value("${trainer.service.url}")
    public void setTrainerServiceUrl(String trainerServiceUrl) {
       this.trainerServiceUrl = trainerServiceUrl;
   }


}
