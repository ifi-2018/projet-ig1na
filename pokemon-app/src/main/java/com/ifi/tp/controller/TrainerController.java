package com.ifi.tp.controller;

import com.ifi.tp.trainers.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/trainers")
public class TrainerController {

    private final TrainerService trainerService;

    @Autowired
    TrainerController(TrainerService trainerService){
        this.trainerService = trainerService;
    }

    @GetMapping
    public ModelAndView getAllTrainers() {
        ModelAndView mv = new ModelAndView("trainers");
        mv.addObject("trainers", this.trainerService.getAllTrainers());
        return mv;
    }

    @GetMapping("/{name}")
    public ModelAndView getTrainerByName(@PathVariable String name) {
        ModelAndView mv = new ModelAndView("trainer-details");
        mv.addObject("trainer", this.trainerService.getTrainer(name));
        return mv;
    }

}
