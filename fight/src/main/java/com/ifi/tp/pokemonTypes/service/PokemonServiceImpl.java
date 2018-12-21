package com.ifi.tp.pokemonTypes.service;

import com.ifi.tp.pokemonTypes.bo.PokemonType;
import com.ifi.tp.trainers.bo.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class PokemonServiceImpl implements PokemonService {

    private String pokemonServiceUrl;
    private RestTemplate restTemplate;

    @Autowired
    public PokemonServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<PokemonType> listPokemonsTypes() {
        PokemonType[] pokemons = this.restTemplate.getForObject(this.pokemonServiceUrl+"/pokemons", PokemonType[].class);
        return Arrays.asList(pokemons);
    }

    public PokemonType getPokemonType(int number) {
        PokemonType pokemon = this.restTemplate.getForObject(this.pokemonServiceUrl + "/pokemons/{number}", PokemonType.class, number);
        return pokemon;
    }

    @Value("${pokemon.service.url}")
    public void setPokemonServiceUrl(String pokemonServiceUrl) {
        this.pokemonServiceUrl = pokemonServiceUrl;
    }
}