package com.ifi.tp.controller;

import com.ifi.tp.fights.bo.Fight;
import com.ifi.tp.fights.service.FightService;
import com.ifi.tp.trainers.bo.Trainer;
import com.ifi.tp.trainers.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class FightController {
    private final FightService fightService;
    private final TrainerService trainerService;

    @Autowired
    FightController(FightService fightService, TrainerService trainerService) {
        this.fightService = fightService;
        this.trainerService = trainerService;
    }

    @RequestMapping("/fights")
    public List<Fight> getFights() {
        return fightService.getFights();
    }

    @RequestMapping("/fights/{id}")
    public Fight getFightFromId(@PathVariable int id) {
        return fightService.getFightFromId(id);
    }

    @RequestMapping("/fight/{name1}/{name2}")
    public Fight fight(@PathVariable String name1, @PathVariable String name2) {
        Trainer trainer1 = trainerService.getTrainer(name1);
        Trainer trainer2 = trainerService.getTrainer(name2);
        Fight f = fightService.fight(trainer1, trainer2);
        System.out.println(f);
        return f;
    }


}
