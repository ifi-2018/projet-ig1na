package com.ifi.tp.service;


import com.ifi.tp.config.RestConfiguration;
import com.ifi.tp.pokemonTypes.bo.PokemonType;
import com.ifi.tp.pokemonTypes.service.PokemonServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class PokemonServiceImplTest {

    @Test
    void listPokemonsTypes_shouldCallTheRemoteService() {
        var url = "http://localhost:8080";

        var restTemplate = mock(RestTemplate.class);
        var pokemonServiceImpl = new PokemonServiceImpl(restTemplate);
        pokemonServiceImpl.setPokemonServiceUrl(url);

        var pikachu = new PokemonType();
        pikachu.setName("pikachu");
        pikachu.setId(25);

        var expectedUrl = "http://localhost:8080/pokemons";
        when(restTemplate.getForObject(expectedUrl, PokemonType[].class)).thenReturn(new PokemonType[]{pikachu});

        var pokemons = pokemonServiceImpl.listPokemonsTypes();

        assertNotNull(pokemons);
        assertEquals(1, pokemons.size());

        verify(restTemplate).getForObject(expectedUrl, PokemonType[].class);
    }

    @Test
    void pokemonServiceImpl_shouldBeAnnotatedWithService(){
        assertNotNull(PokemonServiceImpl.class.getAnnotation(Service.class));
    }

    @Test
    void pokemonServiceImplConstructor_shouldBeAnnotatedWithAutowired() throws NoSuchMethodException {
        var constructor = PokemonServiceImpl.class.getConstructor(RestTemplate.class);
        assertNotNull(constructor.getAnnotation(Autowired.class));
    }

    @Test
    void restTemplate_shouldBeInjected(){
        var applicationContext = new AnnotationConfigApplicationContext(RestConfiguration.class, PokemonServiceImpl.class);
        assertNotNull(applicationContext.getBean(RestTemplate.class));
        assertNotNull(applicationContext.getBean(RestConfiguration.class));
        assertNotNull(applicationContext.getBean(PokemonServiceImpl.class));
    }

    @Test
    void setPokemonServiceUrl_shouldBeAnnotatedWithValue() throws NoSuchMethodException {
        var setter = PokemonServiceImpl.class.getDeclaredMethod("setPokemonServiceUrl", String.class);
        var valueAnnotation = setter.getAnnotation(Value.class);
        assertNotNull(valueAnnotation);
        assertEquals("${pokemon.service.url}", valueAnnotation.value());
    }



}


