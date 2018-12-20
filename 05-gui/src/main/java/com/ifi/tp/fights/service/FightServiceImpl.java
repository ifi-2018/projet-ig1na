package com.ifi.tp.fights.service;

import com.ifi.tp.fights.bo.Fight;
import com.ifi.tp.trainers.bo.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class FightServiceImpl implements FightService {

    private RestTemplate restTemplate;
    private String fightServiceUrl;

    @Autowired
    public FightServiceImpl(RestTemplate restTemplate) { this.restTemplate = restTemplate; }

    @Override
    public Fight fight(Trainer trainer1, Trainer trainer2) {
        var url = fightServiceUrl + "/fight/" + trainer1.getName() + "/" + trainer2.getName();
        return this.restTemplate.getForObject(url, Fight.class);
    }

    @Override
    public List<Fight> getFights() {
        var url = fightServiceUrl + "/fights";
        return this.restTemplate.getForObject(url, List.class);
    }

    @Override
    public Fight getFightFromId(int id) {
        var url = fightServiceUrl + "/fights/" + id;
        return this.restTemplate.getForObject(url, Fight.class);
    }

    @Override
    public List<Fight> getFightsFromTrainerName(String name) {
        var url = fightServiceUrl + "/fightsof/" + name;
        return this.restTemplate.getForObject(url, List.class);
    }

    @Value("${fight.service.url}")
    void setFightServiceUrl(String fightServiceUrl) { this.fightServiceUrl = fightServiceUrl; }

}
