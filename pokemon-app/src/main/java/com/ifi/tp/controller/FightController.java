package com.ifi.tp.controller;

import com.ifi.tp.fights.service.FightService;
import com.ifi.tp.trainers.bo.Trainer;
import com.ifi.tp.trainers.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FightController {
    private final FightService fightService;
    private final TrainerService trainerService;

    @Autowired
    FightController(FightService fightService, TrainerService trainerService) {
        this.fightService = fightService;
        this.trainerService = trainerService;
    }

    @GetMapping("/fights")
    public ModelAndView showFights() {
        ModelAndView mv = new ModelAndView("fight");
        mv.addObject("fight", fightService.getFights());
        return mv;
    }

    @PostMapping("/fight")
    public void fight(@RequestParam String name1, @RequestParam String name2) {
        Trainer trainer1 = trainerService.getTrainer(name1);
        Trainer trainer2 = trainerService.getTrainer(name2);
        fightService.fight(trainer1, trainer2);
    }


}
