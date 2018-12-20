package com.ifi.tp.controller;

import com.ifi.tp.fights.bo.Fight;
import com.ifi.tp.fights.service.FightService;
import com.ifi.tp.trainers.bo.Trainer;
import com.ifi.tp.trainers.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FightController {
    private FightService fightService;
    private TrainerService trainerService;

    @Autowired
    FightController(FightService fightService, TrainerService trainerService) {
        this.fightService = fightService;
        this.trainerService = trainerService;
    }

    @GetMapping("/fight/{name1}/{name2}")
    public ModelAndView fight(@PathVariable String name1, @PathVariable String name2) {
        ModelAndView mv = new ModelAndView("fight");
        Trainer trainer1 = trainerService.getTrainer(name1);
        Trainer trainer2 = trainerService.getTrainer(name2);
        mv.addObject("fight", fightService.fight(trainer1, trainer2));
        return mv;
    }

    @GetMapping("/fights/{id}")
    public ModelAndView fight(@PathVariable int id) {
        ModelAndView mv = new ModelAndView("fight");
        Fight f = fightService.getFightFromId(id);
        System.out.println(f);
        mv.addObject("fight", f);
        return mv;
    }

    @GetMapping("/fights")
    public ModelAndView fights() {
        ModelAndView mv = new ModelAndView("fights");
        mv.addObject("fights", fightService.getFights());
        return mv;
    }

    @GetMapping("/make-fight/{name}")
    public ModelAndView makeFight(@PathVariable String name) {
        ModelAndView mv = new ModelAndView("make-fight");
        mv.addObject("trainer", trainerService.getTrainer(name));
        mv.addObject("trainers", trainerService.getAllTrainers());
        return mv;
    }
}
